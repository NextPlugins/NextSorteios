package com.nextplugins.sorteios.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

public final class SortTimeCheckerTask implements Runnable {

    public static void createDefault(Plugin plugin) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new SortTimeCheckerTask());
    }

    @Override
    public void run() {
        // TODO
    }

}
