package com.nextplugins.sorteios.manager;

import com.nextplugins.sorteios.api.Prize;
import com.nextplugins.sorteios.configuration.values.PrizesValue;
import com.nextplugins.sorteios.parser.PrizeParser;
import lombok.Getter;

import java.util.List;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class PrizeManager {

    @Getter private List<Prize> prizes;

    public void init() {
        prizes = PrizeParser.parseListSection(PrizesValue.get(PrizesValue::prizeSection));
    }

}
