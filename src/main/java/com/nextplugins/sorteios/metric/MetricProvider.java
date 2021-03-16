package com.nextplugins.sorteios.metric;

import lombok.Data;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.Plugin;

@Data(staticConstructor = "of")
public final class MetricProvider {

    private final Plugin plugin;

    private final int PLUGIN_ID = 10682;

    public void setup() {
        new Metrics(plugin, PLUGIN_ID);
    }

}
