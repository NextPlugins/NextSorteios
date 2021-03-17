package com.nextplugins.sorteios.configuration.values;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigSection("configuration")
@ConfigFile("config.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigValue implements ConfigurationInjectable {

    @Getter private static final ConfigValue instance = new ConfigValue();

    @ConfigField("minPlayers") private int minPlayers;
    @ConfigField("executes") private int executes;

    @ConfigField("sound") private String sortingSound;
    @ConfigField("winSound") private String winSound;

    public static <T> T get(Function<ConfigValue, T> function) {
        return function.apply(instance);
    }

}
