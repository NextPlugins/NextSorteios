package com.nextplugins.sorteios.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.configuration.values.MessagesValue;
import com.nextplugins.sorteios.configuration.values.PrizesValue;
import lombok.Data;
import org.bukkit.plugin.Plugin;

@Data(staticConstructor = "of")
public final class ConfigurationRegistry {

    private final Plugin plugin;

    public void setup() {
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(
                plugin,
                "config.yml",
                "prizes.yml"
        );

        configurationInjector.injectConfiguration(
                ConfigValue.instance(),
                MessagesValue.instance(),
                PrizesValue.instance()
        );
    }

}
