package com.nextplugins.sorteios.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class MessageUtils {

    public static void sendSoundAndTitle(String message, Sound sound, int time) {
        Object[] titlePackets = TitleAPI.buildTitlePackets(message, time, time, time);

        Bukkit.getOnlinePlayers().forEach(target -> {
            TitleAPI.sendTitlePacket(target, titlePackets);
            SoundAPI.sendSound(target, sound);
        });
    }

}
