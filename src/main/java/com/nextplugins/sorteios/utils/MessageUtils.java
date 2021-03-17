package com.nextplugins.sorteios.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class MessageUtils {

    public static void sendSoundAndTitle(String message, Sound sound, int time) {
        Object[] titlePackets = TitleUtils.buildTitlePackets(message, time, time, time);

        Bukkit.getOnlinePlayers().forEach(target -> {
            TitleUtils.sendTitlePacket(target, titlePackets);
            SoundUtils.sendSound(target, sound);
        });
    }

    public static void sendSoundAndTitle(Object[] titlePackets, Sound sound) {
        Bukkit.getOnlinePlayers().forEach(target -> {
            TitleUtils.sendTitlePacket(target, titlePackets);
            SoundUtils.sendSound(target, sound);
        });
    }

}
