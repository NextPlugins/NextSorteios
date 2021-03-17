package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.events.sorted.SortedPlayerEvent;
import com.nextplugins.sorteios.api.prize.Prize;
import com.nextplugins.sorteios.manager.PrizeManager;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.Random;

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

        int randomNumberPlayer = RANDOM.nextInt(Bukkit.getOnlinePlayers().size());
        int randomNumberPrizes = RANDOM.nextInt(prizeManager.getPrizes().size());

        Player player = (Player) Bukkit.getOnlinePlayers().toArray()[randomNumberPlayer];
        Prize prize = prizeManager.getPrizes().get(randomNumberPrizes);

        if (player == null || prize == null) return;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.callEvent(new SortedPlayerEvent(player, prize));
    }

}
