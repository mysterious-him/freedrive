package freedrive.config;

import java.util.ArrayList;

import static freedrive.FreeDriveMain.ec;
import static freedrive.FreeDriveMain.plugin;

public class MyConfig {
    public static void initConfig() {
        ec = new EasyConfig("config.yml", plugin);
        ec.add("command", "drive");
        ec.add("touch-stairs-popup", "双击可骑马经过台阶");
        ec.add("command-enabled", "骑马功能已启用");
        ec.add("command-disabled", "骑马功能已禁用");
        ec.add("disabled", new ArrayList<String>());
        ec.load();
    }
}
