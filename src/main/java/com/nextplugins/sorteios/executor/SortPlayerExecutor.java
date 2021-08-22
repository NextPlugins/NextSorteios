package com.nextplugins.sorteios.executor;

import com.nextplugins.sorteios.utils.SoundUtils;
import com.nextplugins.sorteios.utils.TitleUtils;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.utils.ColorUtils;
import com.nextplugins.sorteios.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Bukkit;
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

            val scheduler = Bukkit.getScheduler();
            scheduler.cancelTask(taskId);

            taskId = 0;
            return;

        }

        val time = MessagesValue.get(MessagesValue::sortingTime) / 3;
        val message = MessagesValue.get(MessagesValue::sortingTitle);
        val titlePackets = TitleUtils.buildTitlePackets(message, time, time, time);

        // Don't flood player with sounds (one sound per second)
        if (sound) {

            val sortingSound = SoundUtils.typeOf(ConfigValue.get(ConfigValue::sortingSound));
            MessageUtils.sendSoundAndTitle(titlePackets, sortingSound);

        } else Bukkit.getOnlinePlayers().forEach(target -> TitleUtils.sendTitlePacket(target, titlePackets));

        sound ^= true;
        ++executes;

    }

}
