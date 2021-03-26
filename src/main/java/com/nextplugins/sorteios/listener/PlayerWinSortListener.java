package com.nextplugins.sorteios.listener;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.prize.Prize;
import com.nextplugins.sorteios.api.events.sorted.AsyncSortedPlayerEvent;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class PlayerWinSortListener implements Listener {

    @EventHandler
    public void onPlayerWin(AsyncSortedPlayerEvent event) {

        Player player = event.getPlayer();
        Prize prize = event.getPrize();

        Bukkit.getScheduler().runTask(NextSorteios.getInstance(), () -> {

            for (String command : prize.getCommands()) {

                Bukkit.dispatchCommand(
                        Bukkit.getConsoleSender(),
                        command.replace("@player", player.getName())
                );

            }

        });

        MessagesValue.get(MessagesValue::winMessage).stream()
                .map(line -> line.replace("@prize", prize.getColoredName()))
                .forEach(player::sendMessage);

        int time = MessagesValue.get(MessagesValue::sortedTime) / 3;
        String title = MessagesValue.get(MessagesValue::sortedTitle)
                .replace("@player", player.getName())
                .replace("@prize", prize.getColoredName());

        Sound sound = Sound.valueOf(ConfigValue.get(ConfigValue::winSound));

        MessageUtils.sendSoundAndTitle(title, sound, time);

    }

}
