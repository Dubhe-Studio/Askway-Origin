package dev.dubhe.askway.origin.init;

import dev.dubhe.askway.origin.AskwayOrigin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AskwayModTags {
    public static final class Blocks {
        public static final TagKey<Block> WILLOW_LOGS = mod("willow_logs");
        public static final TagKey<Block> PEACH_LOGS = mod("peach_logs");
    }

    private static TagKey<Block> forge(String path) {
        return BlockTags.create(new ResourceLocation("forge", path));
    }

    private static TagKey<Block> mod(String path) {
        return BlockTags.create(AskwayOrigin.of(path));
    }

    public static final class Items {

        public static final TagKey<Item> WILLOW_LOGS = mod("willow_logs");
        public static final TagKey<Item> PEACH_LOGS = mod("peach_logs");
        private static TagKey<Item> forge(String path) {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> mod(String path) {
            return ItemTags.create(AskwayOrigin.of(path));
        }
    }
}
