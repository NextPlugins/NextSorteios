package com.nextplugins.sorteios.parser;

import com.nextplugins.sorteios.api.Sort;
import org.bukkit.configuration.ConfigurationSection;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

public class SortParser {

    public static Sort parseSection(ConfigurationSection section) {

        return Sort.builder()
                .coloredName(section.getString("coloredName"))
                .commands(section.getStringList("commands"))
                .build();

    }

}
