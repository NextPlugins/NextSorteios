package com.nextplugins.sorteios.configuration.registry;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import com.nextplugins.sorteios.configuration.values.MessageValue;
import com.nextplugins.sorteios.configuration.values.PrizesValue;
import lombok.Data;
import org.bukkit.plugin.Plugin;

@Data(staticConstructor = "of")
public final class ConfigurationRegistry {

    private final Plugin plugin;

    public void register() {
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(plugin);

        configurationInjector.saveDefaultConfiguration(
                plugin,
                "messages.yml",
                "inventories.yml"
        );

        configurationInjector.injectConfiguration(
                MessageValue.instance(),
                PrizesValue.instance()
        );
    }

}
