package com.nextplugins.sorteios.command;

import com.nextplugins.sorteios.NextSorteios;
import com.nextplugins.sorteios.configuration.values.ConfigValue;
import com.nextplugins.sorteios.executor.SortPlayerExecutor;
import com.nextplugins.sorteios.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Logger;

/**
 * @author Yuhtin
 * Github: https://github.com/Yuhtin
 */
public class SortCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("nextsorteios.sort")) {

            sender.sendMessage(ColorUtils.colored("&cSem permissão para fazer isto."));
            return false;

        }

        if (args.length < 1
                || !args[0].contains("force")
                && Bukkit.getOnlinePlayers().size() < ConfigValue.get(ConfigValue::minPlayers)) {

            sender.sendMessage(ColorUtils.colored("&cO sorteio não foi iniciado por falta de jogadores."));
            return false;

        }


        Logger logger = NextSorteios.getInstance().getLogger();

        try {

            SortPlayerExecutor.createDefault(
                    NextSorteios.getInstance(),
                    ConfigValue.get(ConfigValue::executes)
            );

        } catch (IllegalStateException exception) {

            logger.info(exception.getMessage());
            return false;

        }

        sender.sendMessage(ColorUtils.colored("&aSorteio iniciado com sucesso."));
        logger.info("O jogador " + sender.getName() + " forçou o inicio de um sorteio.");

        return true;

    }

}
