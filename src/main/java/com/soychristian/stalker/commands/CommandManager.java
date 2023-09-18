package com.soychristian.stalker.commands;

import com.soychristian.stalker.Stalker;
import com.soychristian.stalker.commands.subcommands.ReloadSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {
    private Stalker plugin;
    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    private CommandSender consoleSender;
    // /stalker <subcomando>
    public CommandManager(Stalker plugin){
        this.plugin = plugin;
        this.consoleSender = plugin.getServer().getConsoleSender();
        subCommands.add(new ReloadSubCommand(plugin));
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            this.consoleSender.sendMessage(Stalker.getPluginNameColor() + "Por el momento no hay comandos habilitados para consola ni bloques de comandos.");
            return true;
        }
        Player playerSender = (Player) commandSender;
        if (args.length > 0){
            String subcommand = args[0];
            // TODO: Agregar forma funcional para reemplazar el for.
            for(int i = 0; i < this.subCommands.size(); i++){
                SubCommand subCommand = this.subCommands.get(i);
                String subcommandName = subCommand.getName();
                if (subcommandName.equalsIgnoreCase(subcommand)){
                    subCommand.perform(playerSender, args);
                }
            }
        } else {
            playerSender.sendMessage(Stalker.getPluginNameColor() + "Ingresa un argumento para continuar.");
            for (int i = 0; i < this.subCommands.size(); i++){
                playerSender.sendMessage("- " + this.subCommands.get(i).getSyntax());
            }
        }

        return true;
    }
}
