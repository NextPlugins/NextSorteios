package com.nextplugins.sorteios;

import com.nextplugins.sorteios.metric.MetricProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextSorteios extends JavaPlugin {

    @Override
    public void onEnable() {

        MetricProvider.of(this).setup();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
