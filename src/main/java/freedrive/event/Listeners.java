package freedrive.event;

import cn.nukkit.event.Listener;

import java.util.List;

import static freedrive.FreeDriveMain.ec;

public class Listeners implements Listener {
    public static final List<String> disabled = ec.get("disabled");
}
