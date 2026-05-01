package freedrive.config;

import cn.nukkit.plugin.Plugin;
import cn.nukkit.utils.Config;

import java.util.HashMap;
import java.util.Map;

public class EasyConfig {
    private final Plugin plugin;
    private final String configFileName;
    private final Map<String, Object> defaults = new HashMap<>();
    private Config config;

    public EasyConfig(String configFileName, Plugin plugin) {
        this.configFileName = configFileName;
        this.plugin = plugin;
    }

    /**
     * 添加配置项及其默认值
     *
     * @param key          配置项名称
     * @param defaultValue 默认值
     */
    public void add(String key, Object defaultValue) {
        defaults.put(key, defaultValue);
    }

    /**
     * 获取配置项的值，自动推断类型
     *
     * @param key 配置项名称
     * @param <T> 返回值类型
     * @return 配置项的值
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        Object value = config.get(key, defaults.get(key));
        return (T) value;
    }

    public String getString(String key) {
        return get(key);
    }

    public int getInt(String key) {
        return get(key);
    }

    public boolean getBoolean(String key) {
        return get(key);
    }

    public double getDouble(String key) {
        return get(key);
    }


    /**
     * 设置配置项的值
     *
     * @param key   配置项名称
     * @param value 配置项的值
     */
    public void set(String key, Object value) {
        config.set(key, value);
    }

    /**
     * 加载配置文件
     */
    public void load() {
        // 保存默认配置文件
        plugin.saveResource(configFileName);
        // 加载配置文件
        config = new Config(plugin.getDataFolder().getPath() + "/" + configFileName, Config.YAML);
        // 确保所有默认值都存在
        for (Map.Entry<String, Object> entry : defaults.entrySet()) {
            if (!config.exists(entry.getKey())) {
                config.set(entry.getKey(), entry.getValue());
            }
        }
        config.save();
    }

    /**
     * 保存配置文件
     */
    public void save() {
        config.save();
    }
}