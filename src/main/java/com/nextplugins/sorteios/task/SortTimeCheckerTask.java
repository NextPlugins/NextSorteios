package com.nextplugins.sorteios.task;

import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.executor.SortPlayerExecutor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class SortTimeCheckerTask implements Runnable {

    private final Plugin plugin;

    public static void createDefault(Plugin plugin, int minutes) {

        SortTimeCheckerTask task = new SortTimeCheckerTask(plugin);
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, 20L * 60L * minutes, 20L * 60L * minutes);

    }

    @Override
    public void run() {

        if (Bukkit.getOnlinePlayers().size() < ConfigValue.get(ConfigValue::minPlayers)) return;

        SortPlayerExecutor.createDefault(plugin, ConfigValue.get(ConfigValue::executes));
        plugin.getLogger().info("Sorteando um jogador aleatório");
    }

}
