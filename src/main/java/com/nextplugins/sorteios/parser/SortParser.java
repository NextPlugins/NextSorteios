package com.nextplugins.sorteios.parser;

import com.nextplugins.sorteios.api.Sort;
import com.nextplugins.sorteios.utils.ColorUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

public class SortParser {

    public static Set<Sort> parseListSection(ConfigurationSection section) {

        return section.getKeys(false)
                .stream()
                .map(section::getConfigurationSection)
                .filter(Objects::nonNull)
                .map(SortParser::parseSection)
                .collect(Collectors.toSet());

    }
    
    public static Sort parseSection(ConfigurationSection section) {

        return Sort.builder()
                .coloredName(ColorUtils.colored(section.getString("coloredName")))
                .commands(section.getStringList("commands"))
                .build();

    }

}
