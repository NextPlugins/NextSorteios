package com.nextplugins.sorteios.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class TitleAPI {

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

    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String message) {
        try {

            String[] split = message.split("<nl>");
            String title = ColorUtils.colored(split[0]);
            String subtitle = ColorUtils.colored(split[1]);

            //sendTitlePacket(player, fadeIn, stay, fadeOut, title, "TIMES");

            sendTitlePacket(player, fadeIn, stay, fadeOut, title, "TITLE");
            sendTitlePacket(player, fadeIn, stay, fadeOut, subtitle, "SUBTITLE");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void sendTitlePacket(Player player, int fadeIn, int stay, int fadeOut, String message, String type) {

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

            if (player == null) Bukkit.getOnlinePlayers().forEach(target -> sendPacket(target, packet));
            else sendPacket(player, packet);

        }catch (Exception ingored) {}
    }

}
