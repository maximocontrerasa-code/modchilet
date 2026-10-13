package com.chiletime;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

import java.time.LocalTime;
import java.time.ZoneId;

public class ChileRealTimeMod implements ModInitializer {
    public static final String MOD_ID = "chile_realtime";
    public static final ZoneId CHILE_ZONE = ZoneId.of("America/Santiago");

    private int tickCounter = 0;

    @Override
    public void onInitialize() {
        // Desactiva el ciclo normal para evitar interferencias de avance de ticks vainilla
        ServerWorldEvents.LOAD.register((server, world) -> {
            if (world.getRegistryKey() == ServerWorld.OVERWORLD) {
                world.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(false, server);
            }
        });

        // Sincroniza el tiempo en cada segundo (cada 20 ticks)
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world.getRegistryKey() != ServerWorld.OVERWORLD) {
                return;
            }

            tickCounter++;
            if (tickCounter < 20) {
                return;
            }
            tickCounter = 0;

            // Hora actual en Chile (maneja automaticamente horario de verano/invierno)
            LocalTime now = LocalTime.now(CHILE_ZONE);
            int secondOfDay = now.toSecondOfDay();

            // 06:00 AM = 0 ticks, 12:00 PM = 6000 ticks, 18:00 = 12000 ticks, 00:00 = 18000 ticks
            long shiftedSeconds = (secondOfDay - 21600L + 86400L) % 86400L;
            long targetDayTicks = (shiftedSeconds * 24000L) / 86400L;

            // Mantener el conteo acumulativo de dias de Minecraft
            long currentDay = world.getTimeOfDay() / 24000L;
            long calculatedTime = (currentDay * 24000L) + targetDayTicks;

            // Manejo de transicion de medianoche
            if (calculatedTime < world.getTimeOfDay() && (world.getTimeOfDay() - calculatedTime) > 12000L) {
                calculatedTime += 24000L;
            }

            world.setTimeOfDay(calculatedTime);
        });
    }
}
