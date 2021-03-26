package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.events.sorted.AsyncSortedPlayerEvent;
import com.nextplugins.sorteios.api.prize.Prize;
import com.nextplugins.sorteios.manager.PrizeManager;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@NoArgsConstructor(staticName = "createDefault")
public final class SelectWinnerExecutor implements Runnable {

    private static final Random RANDOM = new Random();

    @Override
    public void run() {
        PrizeManager prizeManager = NextSorteios.getInstance().getPrizeManager();

        int randomNumberPrizes = RANDOM.nextInt(prizeManager.getPrizes().size());

        Prize prize = prizeManager.getPrizes().get(randomNumberPrizes);

        final List<Player> onlinePlayers = Bukkit.getOnlinePlayers()
                .stream()
                .filter(value -> !value.hasPermission(prize.getSortBanPermission()))
                .collect(Collectors.toList());

        int randomNumberPlayer = RANDOM.nextInt(onlinePlayers.size());
        Player player = (Player) onlinePlayers.toArray()[randomNumberPlayer];

        if (player == null || prize == null) return;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.callEvent(new AsyncSortedPlayerEvent(player, prize, true));
    }

}
