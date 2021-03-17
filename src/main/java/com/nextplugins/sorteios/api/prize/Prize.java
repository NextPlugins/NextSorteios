package com.nextplugins.sorteios.api.prize;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Data
@Builder
public class Prize {

    private final String coloredName;
    private final List<String> commands;

}
