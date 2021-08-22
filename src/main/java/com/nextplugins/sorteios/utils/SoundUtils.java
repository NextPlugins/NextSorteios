package com.nextplugins.sorteios.utils;

import com.nextplugins.sorteios.NextSorteios;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SoundUtils {

    public static void sendSound(Player player, Sound sound) {
        sendSound(player, sound, 0.5f, 1f);
    }

    public static void sendSound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    public static Sound typeOf(String soundName) {
        try {
            return Sound.valueOf(soundName);
        } catch (Exception exception) {
            NextSorteios.getInstance().getLogger().severe("Você está usando o som '" + soundName + "', porém ele não existe nesta versão, mude-o na config.yml");
            return Sound.BLOCK_NOTE_BLOCK_PLING;
        }
    }

}
