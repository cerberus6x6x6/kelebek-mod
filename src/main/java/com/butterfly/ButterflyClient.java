package com.butterfly; // Eğer generator'da farklı bir package yazdıysan burayı ona göre düzenle

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class ButterflyClient implements ClientModInitializer {
    public static KeyBinding toggleBinding;
    public static boolean toggleStatus = false;

    @Override
    public void onInitializeClient() {
        toggleBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.butterfly.toggle",
                InputUtil.Type.KEYSYM,
                86, // V tuşu (GLFW_KEY_V)
                "category.butterfly.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            while (toggleBinding.wasPressed()) {
                toggleStatus = !toggleStatus;
                String status = toggleStatus ? "§aAKTIF" : "§cKAPALI";
                client.player.sendMessage(Text.literal("§b[Kelebek] §7Durumu: " + status), true);
            }
        });
    }
}