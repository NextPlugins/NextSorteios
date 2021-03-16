package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.Prize;
import com.nextplugins.sorteios.api.events.sorted.SortedPlayerEvent;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.manager.PrizeManager;
import com.nextplugins.sorteios.utils.MessageUtils;
import com.nextplugins.sorteios.utils.SoundAPI;
import com.nextplugins.sorteios.utils.TitleAPI;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@RequiredArgsConstructor
public final class SortPlayerExecutor implements Runnable {

    private static final Random RANDOM = new Random();

    private final int maxExecutes;
    private int executes = 0;

    public static void createDefault(Plugin plugin, int maxExecutes) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new SortPlayerExecutor(maxExecutes), 0L, 10L * maxExecutes);
    }

    @Override
    public void run() {

        if (executes > this.maxExecutes) {

            PrizeManager prizeManager = NextSorteios.getInstance().getPrizeManager();

            int randomNumberPlayer = RANDOM.nextInt(Bukkit.getOnlinePlayers().size());
            int randomNumberPrizes = RANDOM.nextInt(prizeManager.getPrizes().size());

            Player player = (Player) Bukkit.getOnlinePlayers().toArray()[randomNumberPlayer];
            Prize prize = prizeManager.getPrizes().get(randomNumberPrizes);

            if (player == null || prize == null) return;

            Bukkit.getPluginManager().callEvent(new SortedPlayerEvent(player, prize));
            return;

        }

        int time = MessagesValue.get(MessagesValue::sortingTime) / 3;
        String message = MessagesValue.get(MessagesValue::sortingTitle);
        Sound sound = Sound.valueOf(ConfigValue.get(ConfigValue::sortingSound));

        MessageUtils.sendSoundAndTitle(message, sound, time);

        ++executes;

    }

}
