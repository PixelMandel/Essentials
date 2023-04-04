package me.pixelmandel.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.defaults.PluginsCommand;
import me.pixelmandel.Essentials;

public class FeedCommand extends PluginsCommand {

    public FeedCommand() {
        super("feed");
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
        this.setDescription("Feed yourself or another player");
        this.setPermission("essentials.feed");
        this.setUsage("/feed [player]");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (sender.hasPermission("essentials.feed")){

            if (args.length == 0){

                Player player = (Player) sender;

                player.getFoodData().setLevel(20);

                player.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.feed-message"));
                return true;

            } else {

                if (sender.hasPermission("essentials.feed.other")){

                    if (Server.getInstance().getPlayer(args[0]) == null){

                        sender.sendMessage(Essentials.instance.getConfig().getString("messages.player-not-found"));

                    } else {

                        sender.sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.feed.other").replace("{player}", Server.getInstance().getPlayer(args[0]).getName()));
                        Server.getInstance().getPlayer(args[0]).sendMessage(Essentials.instance.getConfig().getString("messages.prefix") + Essentials.instance.getConfig().getString("messages.feed"));
                        Server.getInstance().getPlayer(args[0]).getFoodData().setLevel(20);

                        return true;


                    }

                }

            }

        } else {

            sender.sendMessage(Essentials.instance.getConfig().getString("messages.no-permission"));
            return true;

        }

        return true;

    }
}

