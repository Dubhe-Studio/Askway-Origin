package dev.dubhe.askway.origin.utils;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Loots {
    public static void dropSelf(RegistrateBlockLootTables lootTables, Block block) {
        lootTables.dropSelf(block);
    }

    public static void dropDoorSelf(RegistrateBlockLootTables lootTables, Block block) {
        lootTables.add(block, lootTables.createDoorTable(block));
    }

    public static void dropOther(RegistrateBlockLootTables lootTables, Block block, Item item) {
        lootTables.dropOther(block, item);
    }

    public static void dropWhenSilkTouch(RegistrateBlockLootTables lootTables, Block block) {
        lootTables.dropWhenSilkTouch(block);
    }

    public static void createLeavesDrops(RegistrateBlockLootTables lootTables, Block block, Block sapling, float... chances) {
        lootTables.add(block, lootTables.createLeavesDrops(block, sapling, chances));
    }

}
