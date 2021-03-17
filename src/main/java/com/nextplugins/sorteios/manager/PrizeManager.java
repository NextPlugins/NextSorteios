package com.nextplugins.sorteios.manager;

import com.nextplugins.sorteios.api.prize.Prize;
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

        PrizeParser prizeParser = new PrizeParser();
        prizes = prizeParser.parseListSection(PrizesValue.get(PrizesValue::prizeSection));

    }

}
