package com.nextplugins.sorteios.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class TtileAPI {

    @Deprecated
    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, message, null);
    }

    @Deprecated
    public static void sendSubtitle(Player player, int fadeIn, int stay, int fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, null, message);
    }

    @Deprecated
    public static void sendFullTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        try {
            if (title != null) {

                title = ColorUtils.colored(title.replaceAll("@player", player.getDisplayName()));
                sendTitlePacket(player, fadeIn, stay, fadeOut, title, "TIMES");
                sendTitlePacket(player, fadeIn, stay, fadeOut, title, "TITLE");

            }

            if (subtitle != null) {

                subtitle = ColorUtils.colored(subtitle.replaceAll("@player", player.getDisplayName()));
                sendTitlePacket(player, fadeIn, stay, fadeOut, title, "TIMES");
                sendTitlePacket(player, fadeIn, stay, fadeOut, subtitle, "SUBTITLE");

            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }

    private static void sendTitlePacket(Player player, int fadeIn, int stay, int fadeOut, String message, String type) {

        try {
            Object chatSubtitle = getNMSClass("IChatBaseComponent")
                    .getDeclaredClasses()[0]
                    .getMethod("a", String.class)
                    .invoke(null, "{\"text\":\"" + message + "\"}");

            Constructor subtitleConstructor = getNMSClass("PacketPlayOutTitle")
                    .getConstructor(
                            getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                            getNMSClass("IChatBaseComponent"),
                            Integer.TYPE,
                            Integer.TYPE,
                            Integer.TYPE
                    );

            Object subtitlePacket = subtitleConstructor.newInstance(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField(type).get(null),
                    chatSubtitle, fadeIn, stay, fadeOut
            );

            sendPacket(player, subtitlePacket);
        }catch (Exception ingored) {}
    }

}
