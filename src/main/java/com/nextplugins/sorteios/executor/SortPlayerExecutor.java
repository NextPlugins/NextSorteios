package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.utils.TitleUtils;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.utils.ColorUtils;
import com.nextplugins.sorteios.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

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

    public static void createDefault(Plugin plugin, int maxExecutes) throws IllegalStateException {

        if (taskId != 0) throw new IllegalStateException(ColorUtils.colored("&cJá existe um sorteio acontecendo."));

        taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(
                plugin, new SortPlayerExecutor(maxExecutes),
                0L, 10L
        ).getTaskId();

    }

    @Override
    public void run() {

        if (executes > this.maxExecutes) {

            SelectWinnerExecutor.createDefault().run();

            BukkitScheduler scheduler = Bukkit.getScheduler();
            scheduler.cancelTask(taskId);
            taskId = 0;

            return;

        }

        int time = MessagesValue.get(MessagesValue::sortingTime) / 3;
        String message = MessagesValue.get(MessagesValue::sortingTitle);
        Object[] titlePackets = TitleUtils.buildTitlePackets(message, time, time, time);

        // Don't flood player with sounds (one sound per second)
        if (sound) {

            Sound sound = Sound.valueOf(ConfigValue.get(ConfigValue::sortingSound));
            MessageUtils.sendSoundAndTitle(titlePackets, sound);

        } else Bukkit.getOnlinePlayers().forEach(target -> TitleUtils.sendTitlePacket(target, titlePackets));

        sound ^= true;
        ++executes;

    }

}
