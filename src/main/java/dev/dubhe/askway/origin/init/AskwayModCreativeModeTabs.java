package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.AskwayOrigin;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AskwayModCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, AskwayOrigin.MODID);

    public static final RegistryObject<CreativeModeTab> ORIGIN = TAB_REGISTER.register("origin",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.askway_origin.origin"))
                    .icon(() -> new ItemStack(AskwayModItems.MAGIC_TEST))
                    .build());

    public static void register() {}
}
