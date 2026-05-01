package freedrive.panel;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import freedrive.panel.esay_chest_menu.BigChestMenu;

public class MyChestMenu {
    public static void test1(Player player) {
        BigChestMenu menu = new BigChestMenu("test", true, true);
        menu.add(0, Item.get(Item.STONE_SWORD), () -> player.sendMessage("clicked 1"));
        menu.add(1, Item.get(Item.STONE_SWORD), () -> player.sendMessage("clicked 2"));
        menu.add(2, Item.get(Item.STONE_SWORD), () -> player.sendMessage("clicked 3"));
        menu.show(player);
    }
}
