package com.nextplugins.sorteios.manager;

import com.nextplugins.sorteios.api.Prize;
import com.nextplugins.sorteios.configuration.values.PrizesValue;
import com.nextplugins.sorteios.parser.PrizeParser;
import lombok.Getter;

import java.util.Set;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class PrizeManager {

    @Getter private static final PrizeManager instance = new PrizeManager().init();
    @Getter private Set<Prize> prizes;

    public PrizeManager init() {

        prizes = PrizeParser.parseListSection(PrizesValue.get(PrizesValue::prizeSection));
        return this;

    }

}
