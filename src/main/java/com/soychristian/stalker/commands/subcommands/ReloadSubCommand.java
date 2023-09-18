package com.soychristian.stalker.commands.subcommands;

import com.soychristian.stalker.Stalker;
import com.soychristian.stalker.commands.SubCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ReloadSubCommand extends SubCommand {
    private Stalker plugin;
    public ReloadSubCommand(Stalker plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Refresh the configuration plugin file.";
    }

    @Override
    public String getSyntax() {
        return "/stalker reload";
    }
    // TODO: Que reciba la instancia del plugin principal por medio de perform.
    @Override
    public void perform(Player player, String[] args) {
        plugin.reloadConfig();
        player.sendMessage(Stalker.getPluginNameColor() + "El archivo de configuración ha sido recargado.");
    }
}
