package com.nextplugins.sorteios;

import com.nextplugins.sorteios.configuration.registry.ConfigurationRegistry;
import com.nextplugins.sorteios.manager.PrizeManager;
import com.nextplugins.sorteios.metric.MetricProvider;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class NextSorteios extends JavaPlugin {

    private final PrizeManager prizeManager = new PrizeManager();

    public static NextSorteios getInstance() {
        return getPlugin(NextSorteios.class);
    }

    @Override
    public void onEnable() {

        ConfigurationRegistry.of(this).setup();
        MetricProvider.of(this).setup();

        this.prizeManager.init();

    }

}
