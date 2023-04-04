package me.pixelmandel;

import cn.nukkit.command.CommandMap;
import cn.nukkit.plugin.PluginBase;
import me.pixelmandel.commands.BroadcastCommand;
import me.pixelmandel.commands.FeedCommand;
import me.pixelmandel.commands.FlyCommand;
import me.pixelmandel.commands.HealCommand;

public class Essentials extends PluginBase {

    public static Essentials instance;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        getServer().getLogger().info(this.getConfig().getString("messages.prefix") + "§aPlugin gestartet!");

        //CommandMap map = getServer().getCommandMap();
        getServer().getCommandMap().register("fly", new FlyCommand());
        getServer().getCommandMap().register("broadcast", new BroadcastCommand());
        getServer().getCommandMap().register("heal", new HealCommand());
        getServer().getCommandMap().register("feed", new FeedCommand());


    }
}