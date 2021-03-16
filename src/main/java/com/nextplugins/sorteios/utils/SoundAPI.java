package com.nextplugins.sorteios.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SoundAPI {

    public static void sendSound(Player player, Sound sound) {
        sendSound(player, sound, 1f, 0.5f);
    }

    public static void sendSound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

}
