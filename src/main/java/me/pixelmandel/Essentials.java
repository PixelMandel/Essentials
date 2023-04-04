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

        saveDefaultConfig();

        getServer().getLogger().info(this.getConfig().getString("messages.prefix") + "§aPlugin gestartet!");

        CommandMap map = getServer().getCommandMap();
        map.register("fly", new FlyCommand());
        map.register("broadcast", new BroadcastCommand());
        map.register("heal", new HealCommand());
        map.register("feed", new FeedCommand());

    }
}