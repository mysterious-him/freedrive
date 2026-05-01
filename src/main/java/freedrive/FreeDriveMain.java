package freedrive;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import freedrive.command.MyCommand;
import freedrive.config.EasyConfig;
import freedrive.event.Listeners;
import freedrive.mcEntity.ChairEntity;

import static freedrive.config.MyConfig.initConfig;
import static freedrive.tools.pluginUtil.checkServer;
import static freedrive.tools.pluginUtil.nkConsole;


public class FreeDriveMain extends PluginBase {
    public static Plugin plugin;
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String ConfigPath;
    public static EasyConfig ec;

    //插件读取
    @Override
    public void onLoad() {
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        ConfigPath = getDataFolder().getPath();
        ec = new EasyConfig("config.yml", plugin);
        initConfig();
        nkConsole("&b" + plugin.getName() + "插件读取...");
    }

    //插件开启
    @Override
    public void onEnable() {
        checkServer();
        Entity.registerEntity("DriveHorse", ChairEntity.class);
        //注册监听器
        nkServer.getPluginManager().registerEvents(new Listeners(), this);
        //注册命令
        nkServer.getCommandMap().register(this.getName(), new MyCommand());
        nkConsole("&b" + plugin.getName() + "插件开启");
    }

    //插件关闭
    @Override
    public void onDisable() {
        nkConsole("&b" + plugin.getName() + "插件关闭");
    }

}
