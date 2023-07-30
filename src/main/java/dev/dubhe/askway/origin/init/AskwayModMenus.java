package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.util.entry.MenuEntry;
import dev.dubhe.askway.origin.menu.TalismanTableMenu;
import dev.dubhe.askway.origin.menu.screen.TalismanTableScreen;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

@SuppressWarnings("unused")
public class AskwayModMenus {
    public static final MenuEntry<TalismanTableMenu> TALISMAN_TABLE = REGISTRATE
            .menu("talisman_table", (pType, pId, pInv) -> new TalismanTableMenu(pType, pId, pInv), () -> TalismanTableScreen::new)
            .register();

    public static void register() {
    }
}
