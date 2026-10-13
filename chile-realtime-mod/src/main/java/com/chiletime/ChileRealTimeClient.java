package com.chiletime;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChileRealTimeClient implements ClientModInitializer {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();

            // No mostrar si la interfaz esta oculta (F1) o si esta abierta la pantalla F3
            if (client.options.hudHidden || client.getDebugHud().shouldShowDebugHud()) {
                return;
            }

            TextRenderer textRenderer = client.textRenderer;
            String timeString = "Chile: " + LocalTime.now(ChileRealTimeMod.CHILE_ZONE).format(FORMATTER);

            // Dibuja en la esquina superior izquierda (X=10, Y=10) en color blanco con sombra
            drawContext.drawTextWithShadow(textRenderer, Text.literal(timeString), 10, 10, 0xFFFFFF);
        });
    }
}
