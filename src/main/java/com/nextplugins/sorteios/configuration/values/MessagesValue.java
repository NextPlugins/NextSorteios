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

import java.util.List;
import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigSection("messages")
@ConfigFile("config.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessagesValue implements ConfigurationInjectable {

    @Getter private static final MessagesValue instance = new MessagesValue();

    @ConfigField("sorting.title") private String sortingTitle;
    @ConfigField("sorting.time") private int sortingTime;

    @ConfigField("sorted.title") private String sortedTitle;
    @ConfigField("sorted.time") private int sortedTime;

    @ConfigField("winMessage") private List<String> winMessage;

    public static <T> T get(Function<MessagesValue, T> function) {
        return function.apply(instance);
    }

}
