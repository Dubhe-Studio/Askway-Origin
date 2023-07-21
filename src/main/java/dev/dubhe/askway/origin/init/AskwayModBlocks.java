package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.AskwayOrigin;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;

public class AskwayModBlocks {
    private static final Registrate REGISTRATE = AskwayOrigin.getRegistrate();

    static {
        if (AskwayModCreativeModeTabs.ORIGIN.getKey() != null) {
            REGISTRATE.defaultCreativeTab(AskwayModCreativeModeTabs.ORIGIN.getKey());
        }
    }

    public static final RegistryEntry<LeavesBlock> WILLOW_LEAVES = REGISTRATE.block("willow_leaves", LeavesBlock::new)
            .tag(BlockTags.LEAVES)
            .properties(p -> p.sound(SoundType.GRASS).noOcclusion())
            .simpleItem()
            .register();

    public static void register() {}

}
