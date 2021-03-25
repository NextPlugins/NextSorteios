package com.nextplugins.sorteios.parser;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.api.prize.Prize;
import com.nextplugins.sorteios.utils.ColorUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

public final class PrizeParser {

    public List<Prize> parseListSection(ConfigurationSection section) {

        if (section == null) throw new IllegalArgumentException("Section inexistente!");

        return section.getKeys(false)
                .stream()
                .map(section::getConfigurationSection)
                .filter(Objects::nonNull)
                .map(this::parseSection)
                .collect(Collectors.toList());

    }

    public Prize parseSection(ConfigurationSection section) {

        return Prize.builder()
                .coloredName(ColorUtils.colored(section.getString("coloredName")))
                .commands(section.getStringList("commands"))
                .sortBanPermission(section.getString("cantSortPermission", "nextsorteios.sort"))
                .build();

    }

}
