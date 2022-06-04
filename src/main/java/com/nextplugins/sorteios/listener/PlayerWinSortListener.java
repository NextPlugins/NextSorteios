package com.nextplugins.sorteios.listener;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.events.sorted.SortedPlayerEvent;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.utils.ColorUtils;
import com.nextplugins.sorteios.utils.SoundUtils;
import com.nextplugins.sorteios.utils.TitleUtils;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class PlayerWinSortListener implements Listener {

    private final TitleUtils titleUtils;

    public PlayerWinSortListener(TitleUtils titleUtils) {
        this.titleUtils = titleUtils;
    }

    @EventHandler
    public void onPlayerWin(SortedPlayerEvent event) {
        val player = event.getPlayer();
        val prize = event.getPrize();

        Bukkit.getScheduler().runTask(NextSorteios.getInstance(), () -> {
            for (val command : prize.getCommands()) {
                Bukkit.dispatchCommand(
                    Bukkit.getConsoleSender(),
                    command.replace("@player", player.getName())
                );
            }
        });

        MessagesValue.get(MessagesValue::winMessage).stream()
            .map(line -> line.replace("@prize", prize.getColoredName()))
            .forEach(player::sendMessage);

        val time = MessagesValue.get(MessagesValue::sortedTime) / 3;
        val titleRaw = MessagesValue.get(MessagesValue::sortedTitle)
            .replace("@player", player.getName())
            .replace("@prize", prize.getColoredName())
            .split("<nl>");

        val title = ColorUtils.colored(titleRaw[0]);
        val subtitle = ColorUtils.colored(titleRaw[1]);

        val sound = SoundUtils.typeOf(ConfigValue.get(ConfigValue::winSound));

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.playSound(onlinePlayer.getLocation(), sound, 1, 1);
            titleUtils.getMethod().send(onlinePlayer, title, subtitle, time, time, time);
        }
    }

}
