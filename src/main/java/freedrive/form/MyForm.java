package freedrive.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementToggle;
import freedrive.form.easy_form.Custom;
import freedrive.form.easy_form.Simple;

import java.util.Arrays;

import static freedrive.FreeDriveMain.ec;
import static freedrive.event.Listeners.disabled;
import static freedrive.form.easy_form.Modal.tipsModal;


public class MyForm {
    public static void Form(Player player) {
        Custom form = new Custom("FreeDrive设置", true);
        form.add("骑马功能", new ElementToggle("骑马功能", !disabled.contains(player.getName())));
        form.setSubmit(() -> {
            boolean doubleClick = form.getToggleRes("骑马功能");
            if (doubleClick) {
                disabled.remove(player.getName());
                ec.save();
                player.sendMessage(ec.getString("command-enabled"));
            } else {
                disabled.add(player.getName());
                ec.save();
                player.sendMessage(ec.getString("command-disabled"));
            }
        });
        form.show(player);
    }
}
