package com.nextplugins.sorteios.listener;

import com.nextplugins.sorteios.api.Prize;
import com.nextplugins.sorteios.api.events.sorted.SortedPlayerEvent;
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
    public void onPlayerWin(SortedPlayerEvent event) {

        Player player = event.getPlayer();
        Prize prize = event.getPrize();

        for (String command : prize.getCommands()) {

            Bukkit.dispatchCommand(
                    Bukkit.getConsoleSender(),
                    command.replace("@player", player.getName())
            );

        }

        int time = MessagesValue.get(MessagesValue::sortedTime) / 3;
        String message = MessagesValue.get(MessagesValue::sortedTitle).replace("@player", player.getName());
        Sound sound = Sound.valueOf(ConfigValue.get(ConfigValue::winSound));

        MessageUtils.sendSoundAndTitle(message, sound, time);

    }

}
