package com.nextplugins.sorteios.configuration.values;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bukkit.configuration.ConfigurationSection;

import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("prizes.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PrizesValue implements ConfigurationInjectable {

    @Getter private static final PrizesValue instance = new PrizesValue();

    @ConfigField("premios") private ConfigurationSection prizeSection;

    public static <T> T get(Function<PrizesValue, T> function) {
        return function.apply(instance);
    }

}
