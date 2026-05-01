package freedrive.config;

import java.util.ArrayList;

import static freedrive.FreeDriveMain.ec;
import static freedrive.FreeDriveMain.plugin;

public class MyConfig {
    // 马的属性（从配置文件读取）
    public static double horseSpeed = 0.25;
    public static double horseJumpHeight = 0.5;

    public static void initConfig() {
        ec = new EasyConfig("config.yml", plugin);
        ec.add("command", "drive");
        ec.add("touch-stairs-popup", "双击可骑马经过台阶");
        ec.add("command-enabled", "骑马功能已启用");
        ec.add("command-disabled", "骑马功能已禁用");
        ec.add("disabled", new ArrayList<String>());
        // 马匹属性配置
        ec.add("horse-speed", 0.25);
        ec.add("horse-jump-height", 0.5);
        ec.load();

        // 读取马匹属性到静态变量
        horseSpeed = ec.getDouble("horse-speed");
        horseJumpHeight = ec.getDouble("horse-jump-height");
    }
}
