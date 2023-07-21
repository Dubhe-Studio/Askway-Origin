package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.AskwayOrigin;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class AskwayModCreativeModeTabs {

    static {
        REGISTRATE.addRawLang("tab.askway_origin.origin", "Askway Origin");
    }
    public static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, AskwayOrigin.MODID);

    public static final RegistryObject<CreativeModeTab> ORIGIN = TAB_REGISTER.register("origin",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.askway_origin.origin"))
                    .icon(() -> AskwayModItems.MAGIC_TEST.get().getDefaultInstance())
                    .build()
    );
}
