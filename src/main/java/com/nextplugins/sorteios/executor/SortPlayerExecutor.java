package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.utils.ColorUtils;
import com.nextplugins.sorteios.utils.SoundUtils;
import com.nextplugins.sorteios.utils.TitleUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@RequiredArgsConstructor
public final class SortPlayerExecutor implements Runnable {

    private static int taskId;

    private final int maxExecutes;
    private int executes = 0;
    private boolean sound;

    private final TitleUtils titleUtils;

    public static void createDefault(Plugin plugin, int maxExecutes) throws IllegalStateException {

        if (taskId != 0) throw new IllegalStateException(ColorUtils.colored("&cJá existe um sorteio acontecendo."));

        taskId = Bukkit.getScheduler().runTaskTimer(
            plugin, new SortPlayerExecutor(maxExecutes, new TitleUtils()),
            0L, 10L
        ).getTaskId();

    }

    @Override
    public void run() {

        if (executes > this.maxExecutes) {

            SelectWinnerExecutor.createDefault(titleUtils).run();

            val scheduler = Bukkit.getScheduler();
            scheduler.cancelTask(taskId);

            taskId = 0;
            return;

        }

        val time = MessagesValue.get(MessagesValue::sortingTime) / 3;
        val message = MessagesValue.get(MessagesValue::sortingTitle);
        final String[] messageSplit = message.split("<nl>");
        val title = ColorUtils.colored(messageSplit[0]);
        val subtitle = ColorUtils.colored(messageSplit[1]);

        // Don't flood player with sounds (one sound per second)
        if (sound) {
            val sortingSound = SoundUtils.typeOf(ConfigValue.get(ConfigValue::sortingSound));

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                titleUtils.getMethod().send(onlinePlayer, title, subtitle, time, time, time);
                onlinePlayer.playSound(onlinePlayer.getLocation(), sortingSound, 1, 1);
            }

        } else for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            titleUtils.getMethod().send(onlinePlayer, title, subtitle, time, time, time);
        }

        sound ^= true;
        ++executes;

    }

}
