package com.nextplugins.sorteios.api.events.sorted;

import com.nextplugins.sorteios.api.prize.Prize;
import com.nextplugins.sorteios.api.events.SortEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.entity.Player;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AsyncSortedPlayerEvent extends SortEvent {

    private final Player player;
    private final Prize prize;
    private final boolean async;

}
