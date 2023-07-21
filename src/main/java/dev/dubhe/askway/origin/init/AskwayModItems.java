package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.items.MagicTestItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class AskwayModItems {


    static {
        if (AskwayModCreativeModeTabs.ORIGIN.getKey() != null) {
            REGISTRATE.defaultCreativeTab(AskwayModCreativeModeTabs.ORIGIN.getKey());
        }
    }

    public static final RegistryEntry<MagicTestItem> MAGIC_TEST = REGISTRATE
            .item("magic_test", MagicTestItem::new)
            .register();


    public static @NotNull ItemStack withEnergy(@NotNull ItemStack stack, int energy) {
        stack.getOrCreateTag().putInt("energy", energy);
        return stack;
    }

    public static void register() {
    }

}
