package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.items.MagicTestItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AskwayModItems {
    public static final Map<String, Item> ITEM_MAP = new HashMap<>();
    public static final CreativeModeTab ORIGIN = CreativeModeTab.builder()
            .title(Component.translatable("tab.askway_origin.origin"))
            .icon(() -> new ItemStack(AskwayModItems.MAGIC_TEST))
            .displayItems((parameters, output) -> {
                output.accept(AskwayModItems.withEnergy(new ItemStack(AskwayModItems.MAGIC_TEST), 10));
                output.accept(AskwayModItems.withEnergy(new ItemStack(AskwayModItems.MAGIC_TEST), 30));
                output.accept(AskwayModItems.withEnergy(new ItemStack(AskwayModItems.MAGIC_TEST), 50));
                output.accept(AskwayModItems.withEnergy(new ItemStack(AskwayModItems.MAGIC_TEST), 60));
            }).build();

    public static final MagicTestItem MAGIC_TEST = register("magic_test", new MagicTestItem(defaultProperties()));

    public static <T extends Item> T register(String name, T item) {
        AskwayModItems.ITEM_MAP.put(name, item);
        return item;
    }

    public static @NotNull ItemStack withEnergy(@NotNull ItemStack stack, int energy) {
        stack.getOrCreateTag().putInt("energy", energy);
        return stack;
    }

    public static Item.@NotNull Properties defaultProperties() {
        return new Item.Properties();
    }
}
