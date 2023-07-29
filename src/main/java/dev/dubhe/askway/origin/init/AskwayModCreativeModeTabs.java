package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.items.TalismanItem;
import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.modes.IMode;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class AskwayModCreativeModeTabs {

    static {
        REGISTRATE.addRawLang("tab.askway_origin.origin", "Askway Origin");
        REGISTRATE.addRawLang("tab.askway_origin.origin_blocks", "Askway Origin Blocks");
    }

    public static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, AskwayOrigin.MODID);

    public static final RegistryObject<CreativeModeTab> ORIGIN = TAB_REGISTER.register("origin",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .title(Component.translatable("tab.askway_origin.origin"))
                    .icon(() -> AskwayModItems.MAGIC_TEST.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(TalismanItem.create(
                                new MagicGroup(AbstractElement.FIRE, 20), IMode.SHOOT, IGoal.RANGE
                        ));
                        output.accept(TalismanItem.create(
                                new MagicGroup(AbstractElement.FIRE, 20), IMode.THROW, IGoal.RANGE
                        ));
                    })
                    .build()
    );

    public static final RegistryObject<CreativeModeTab> ORIGIN_BLOCKS = TAB_REGISTER.register("origin_blocks",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(ORIGIN.getKey())
                    .title(Component.translatable("tab.askway_origin.origin_blocks"))
                    .icon(() -> AskwayModItems.MAGIC_TEST.get().getDefaultInstance())
                    .build()
    );
}
