package freedrive.tools;

import cn.nukkit.utils.TextFormat;
import io.leego.banana.BananaUtils;
import io.leego.banana.Font;
import lombok.SneakyThrows;
import freedrive.panel.esay_chest_menu.lib.AbstractFakeInventory;

import static freedrive.FreeDriveMain.plugin;

public class pluginUtil {
    public static void checkServer(){
        boolean ver = false;
        //双核心兼容
        try {
            Class<?> c = Class.forName("cn.nukkit.Nukkit");
            c.getField("NUKKIT_PM1E");
            ver = true;

        } catch (ClassNotFoundException | NoSuchFieldException ignore) { }
        try {
            Class<?> c = Class.forName("cn.nukkit.Nukkit");
            "Nukkit PetteriM1 Edition".equalsIgnoreCase(c.getField("NUKKIT").get(c).toString());
            ver = true;
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignore) {
        }

        AbstractFakeInventory.IS_PM1E = ver;
        if(ver){
            nkConsole("当前插件运行在: Nukkit PM1E 核心上");
        }else{
            nkConsole("当前插件运行在: Nukkit 核心上");
        }
    }
    //Banana
    @SneakyThrows
    public static void pluginNameLineConsole() {
        lineConsole(BananaUtils.bananaify(plugin.getName(), Font.SMALL));
    }

    //将输入的字符串按行打印到控制台。
    public static void lineConsole(String s) {
        String[] lines = s.split("\n");
        for (String line : lines) {
            nkConsole(line);
        }
    }

    //使用nk插件的控制台输出
    public static void nkConsole(String msg) {
        plugin.getLogger().info(TextFormat.colorize('&', msg));
    }

    public static void nkConsole(String msg, int typeNum) {
        if (typeNum == 1) {
            plugin.getLogger().warning(TextFormat.colorize('&', msg));
        } else if (typeNum == 2) {
            plugin.getLogger().error(TextFormat.colorize('&', msg));
        } else {
            plugin.getLogger().info(TextFormat.colorize('&', msg));
        }
    }
}
