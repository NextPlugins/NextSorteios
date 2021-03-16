package com.nextplugins.sorteios.parser;

import com.nextplugins.sorteios.api.Prize;
import com.nextplugins.sorteios.utils.ColorUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

public class PrizeParser {

    public static List<Prize> parseListSection(ConfigurationSection section) {

        return section.getKeys(false)
                .stream()
                .map(section::getConfigurationSection)
                .filter(Objects::nonNull)
                .map(PrizeParser::parseSection)
                .collect(Collectors.toList());

    }

    public static Prize parseSection(ConfigurationSection section) {

        return Prize.builder()
                .coloredName(ColorUtils.colored(section.getString("coloredName")))
                .commands(section.getStringList("commands"))
                .build();

    }

}
