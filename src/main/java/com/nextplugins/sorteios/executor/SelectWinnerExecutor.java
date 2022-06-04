package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.events.sorted.SortedPlayerEvent;
import com.nextplugins.sorteios.api.prize.Prize;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.manager.PrizeManager;
import com.nextplugins.sorteios.utils.ColorUtils;
import com.nextplugins.sorteios.utils.TitleUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@RequiredArgsConstructor(staticName = "createDefault")
public final class SelectWinnerExecutor implements Runnable {

    private static final Random RANDOM = new Random();

    private final TitleUtils titleUtils;

    @Override
    public void run() {
        PrizeManager prizeManager = NextSorteios.getInstance().getPrizeManager();

        int randomNumberPrizes = RANDOM.nextInt(prizeManager.getPrizes().size());

        Prize prize = prizeManager.getPrizes().get(randomNumberPrizes);

        final List<Player> onlinePlayers = Bukkit.getOnlinePlayers()
            .stream()
            .filter(value -> !value.hasPermission(prize.getSortBanPermission()))
            .collect(Collectors.toList());

        if (onlinePlayers.isEmpty()) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                titleUtils.getMethod().send(onlinePlayer,
                    ColorUtils.colored("&c&lERRO"),
                    ColorUtils.colored("&fNão tem nenhum jogador elegível para o prêmio"),
                    20,
                    20,
                    20
                );
                onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.valueOf(ConfigValue.get(ConfigValue::errorSound)), 1, 1);
            }

            return;
        }

        int randomNumberPlayer = RANDOM.nextInt(onlinePlayers.size());
        Player player = (Player) onlinePlayers.toArray()[randomNumberPlayer];

        if (player == null || prize == null) return;

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.callEvent(new SortedPlayerEvent(player, prize));
    }

}
