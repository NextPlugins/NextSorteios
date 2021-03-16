package com.nextplugins.sorteios.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class TitleAPI {

    public static void sendTitle(Player player, String message, int fadeIn, int stay, int fadeOut) {
        try {
            sendTitlePacket(player, buildTitlePackets(message, fadeIn, stay, fadeOut));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void sendTitleToAll(String message, int fadeIn, int stay, int fadeOut) {

        Object[] packets = buildTitlePackets(message, fadeIn, stay, fadeOut);
        try {

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                sendTitlePacket(onlinePlayer, packets);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static Object[] buildTitlePackets(String message, int fadeIn, int stay, int fadeOut) {

        String[] split = message.split("<nl>");
        String title = ColorUtils.colored(split[0]);
        String subtitle = ColorUtils.colored(split[1]);

        //sendTitlePacket(player, fadeIn, stay, fadeOut, title, "TIMES");

        return new Object[] {
                buildPacket(title, "TITLE", fadeIn, stay, fadeOut),
                buildPacket(subtitle, "SUBTITLE", fadeIn, stay, fadeOut)
        };

    }

    public static void sendTitlePacket(Player player, Object[] packets) {
        Arrays.stream(packets).forEach(packet -> sendPacket(player, packet));
    }

    private static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object buildPacket(String message, String type, int fadeIn, int stay, int fadeOut) {

        try {
            Object component = getNMSClass("IChatBaseComponent")
                    .getDeclaredClasses()[0]
                    .getMethod("a", String.class)
                    .invoke(null, "{\"text\":\"" + message + "\"}");

            Constructor constructor = getNMSClass("PacketPlayOutTitle")
                    .getConstructor(
                            getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                            getNMSClass("IChatBaseComponent"),
                            Integer.TYPE,
                            Integer.TYPE,
                            Integer.TYPE
                    );

            Object packet = constructor.newInstance(
                    getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField(type).get(null),
                    component, fadeIn, stay, fadeOut
            );

            return packet;

        } catch (Exception ignored) {
        }

        return null;
    }

    private static Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
