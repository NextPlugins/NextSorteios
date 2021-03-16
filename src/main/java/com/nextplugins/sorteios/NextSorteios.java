package com.nextplugins.sorteios;

import com.nextplugins.sorteios.configuration.registry.ConfigurationRegistry;
import com.nextplugins.sorteios.manager.PrizeManager;
import com.nextplugins.sorteios.metric.MetricProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextSorteios extends JavaPlugin {

    private final PrizeManager prizeManager = new PrizeManager();

    @Override
    public void onEnable() {

        ConfigurationRegistry.of(this).setup();
        MetricProvider.of(this).setup();

        this.prizeManager.init();

    }

}
