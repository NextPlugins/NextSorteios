package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.Prize;
import com.nextplugins.sorteios.api.events.sorted.SortedPlayerEvent;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.manager.PrizeManager;
import com.nextplugins.sorteios.task.SortTimeCheckerTask;
import com.nextplugins.sorteios.utils.TitleAPI;
import lombok.Builder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Builder
public final class SortPlayerExecutor implements Runnable {

    public static void createDefault(Plugin plugin, int maxExecutes) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new SortTimeCheckerTask(), 0L, 10L * maxExecutes);
    }

    private static final Random RANDOM = new Random();
    private final int maxExecutes;

    private int executes;

    @Override
    public void run() {

        if (executes > this.maxExecutes) {

            int randomNumber = RANDOM.nextInt(Bukkit.getOnlinePlayers().size());

            Player player = (Player) Bukkit.getOnlinePlayers().toArray()[randomNumber];
            if (player == null) return;

            PrizeManager prizeManager = NextSorteios.getInstance().getPrizeManager();

            Prize prize = prizeManager.getPrizes().stream().findAny().orElse(null);
            if (prize == null) return;

            int time = MessagesValue.get(MessagesValue::sortedTime) / 3;
            String message = MessagesValue.get(MessagesValue::sortedTitle).replace("@player", player.getName());

            TitleAPI.sendTitle(null, time, time, time, message);
            
            Bukkit.getPluginManager().callEvent(new SortedPlayerEvent(player, prize));
            return;

        }

        int time = MessagesValue.get(MessagesValue::sortingTime) / 3;
        String message = MessagesValue.get(MessagesValue::sortingTitle);

        TitleAPI.sendTitle(null, time, time, time, message);
        ++executes;

    }

}
