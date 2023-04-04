package me.pixelmandel.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.defaults.PluginsCommand;
import me.pixelmandel.Essentials;

public class FlyCommand extends PluginsCommand {
    public FlyCommand() {
        super("fly");
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
        this.setDescription("Enabling or disabling fly-mode");
        this.setPermission("essentials.fly");
        this.setUsage("/fly [player]");

    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (args.length == 0){

            Player player = (Player) sender;

            if (player.hasPermission("essentials.fly")){

                if (!player.getAllowFlight()){

                    player.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.fly-mode").replace("{flymode}", "§2ENABLED"));

                    player.setAllowFlight(true);

                } else {

                    player.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.fly-mode").replace("{flymode}", "§cDISABLED"));

                    player.setAllowFlight(false);

                }

                return true;

            } else {

                player.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("no-permission"));

            }

        } else {

            if (sender.hasPermission("essentials.fly.other")){

                if (Server.getInstance().getPlayer(args[0]) == null){

                    sender.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.player-not-found"));

                } else {

                    Player player = Server.getInstance().getPlayer(args[0]);

                    if (!player.getAllowFlight()){

                        player.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.fly-mode").replace("{flymode}", "§2ENABLED"));

                        sender.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.fl-mode-other").replace("{flymode}", "§2ENABLED").replace("{player}", Server.getInstance().getPlayer(args[0]).getName()));

                        player.setAllowFlight(true);

                    } else {

                        player.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.fly-mode").replace("{flymode}", "§cDISABLED"));

                        sender.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.fly-mode-other").replace("{flymode}", "§cDISABLED").replace("{player}", Server.getInstance().getPlayer(args[0]).getName()));

                        player.setAllowFlight(false);

                    }

                    return true;

                }

            } else {

                sender.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("no-permission"));

            }

        }

        return false;

    }
}
