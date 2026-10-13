# Chile RealTime - Mod Fabric para Minecraft

Este mod sincroniza el ciclo de dia y noche de tu mundo de Minecraft con la hora real de Chile (`America/Santiago`), ajustando automaticamente los cambios de horario de verano e invierno, y muestra un reloj en tiempo real en la pantalla (HUD).

---

## Opcion A: Compilar en la nube gratis (Sin instalar nada)
1. Crea un repositorio vacio en GitHub (publico o privado).
2. Sube todos los archivos de esta carpeta al repositorio.
3. Entra a la pestaña **Actions** en tu repositorio de GitHub.
4. Veras que el flujo `Build Fabric Mod` se ejecuta solo.
5. Al finalizar (tarda ~1 minuto), haz clic en el build y descarga el archivo `.jar` en la seccion **Artifacts**.
6. Coloca ese `.jar` en tu carpeta `.minecraft/mods` junto con Fabric API.

---

## Opcion B: Compilar en tu PC (Con Gradle / IntelliJ IDEA)
1. Instala **Java 17 o superior** si aun no lo tienes.
2. Abre una terminal dentro de esta carpeta y ejecuta:
   - Si tienes Gradle instalado: `gradle build`
   - O abre la carpeta directamente en **IntelliJ IDEA** como un proyecto Gradle y ejecuta la tarea `build`.
3. El archivo `.jar` compilado quedara en `build/libs/chile-realtime-1.0.0.jar`.

---

## Requisitos en Minecraft
- Minecraft Java Edition 1.20 o superior (ej. 1.20.4).
- Fabric Loader instalado.
- Fabric API colocado en la carpeta `.minecraft/mods`.
