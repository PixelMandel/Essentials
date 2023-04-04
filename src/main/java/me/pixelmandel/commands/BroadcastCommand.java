package me.pixelmandel.commands;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.defaults.PluginsCommand;
import me.pixelmandel.Essentials;

public class BroadcastCommand extends PluginsCommand {

    public BroadcastCommand() {
        super("broadcast");
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("message", CommandParamType.TEXT, false)
        });
        this.setDescription("Broadcast a message to all players");
        this.setPermission("essentials.broadcast");
        this.setAliases(new String[]{"bc"});
        this.setUsage("/fly <message>");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (sender.hasPermission("essential.broadcast")) {

            if (args.length == 0) {

                sender.sendMessage(this.usageMessage);

            } else {

                String message = "";
                for (String part : args) {
                    if (message != "") message += " ";
                    message += part;
                }

                Server.getInstance().broadcastMessage(Essentials.instance.getConfig().getString("messages.prefix") + message);
                return true;

            }

        } else {

            sender.sendMessage(Essentials.instance.getConfig().getString("messages.no-permission"));
            return true;

        }

        return false;
    }
}
