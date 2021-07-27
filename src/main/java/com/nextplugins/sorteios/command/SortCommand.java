package com.nextplugins.sorteios.command;

import static com.nextplugins.sorteios.utils.ColorUtils.colored;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.executor.SortPlayerExecutor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Logger;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
@Data
public class SortCommand implements CommandExecutor {

    private final NextSorteios plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("nextsorteios.sort")) {

            sender.sendMessage(colored("&cSem permissão para fazer isto."));

            return false;
        }

        if ((args.length < 1
             || !args[0].contains("force"))
             && Bukkit.getOnlinePlayers().size() < ConfigValue.get(ConfigValue::minPlayers)) {

            sender.sendMessage(colored("&cO sorteio não foi iniciado por falta de jogadores."));

            return false;
        }

        Logger logger = plugin.getLogger();

        try {

            SortPlayerExecutor.createDefault(
                    plugin,
                    ConfigValue.get(ConfigValue::executes)
            );

        } catch (IllegalStateException exception) {
            logger.info(exception.getMessage());

            return false;
        }

        sender.sendMessage(colored("&aSorteio iniciado com sucesso."));
        logger.info("O jogador " + sender.getName() + " forçou o inicio de um sorteio.");

        return true;
    }

}
