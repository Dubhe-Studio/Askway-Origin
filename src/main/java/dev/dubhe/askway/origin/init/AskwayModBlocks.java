package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.utils.Loots;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

@SuppressWarnings("unused")
public class AskwayModBlocks {

    static {
        if (AskwayModCreativeModeTabs.ORIGIN.getKey() != null) {
            REGISTRATE.defaultCreativeTab(AskwayModCreativeModeTabs.ORIGIN.getKey());
        }
    }

    public static final RegistryEntry<LeavesBlock> WILLOW_LEAVES = REGISTRATE.block("willow_leaves", LeavesBlock::new)
            .tag(BlockTags.LEAVES)
            .initialProperties(() -> Blocks.OAK_LEAVES)
            .blockstate((genContext, provider) -> provider.simpleBlock(genContext.get()))
            .loot(Loots::dropSelf)
            .simpleItem()
            .register();

    public static final RegistryEntry<RotatedPillarBlock> WILLOW_LOG = REGISTRATE.block("willow_log", RotatedPillarBlock::new)
            .tag(BlockTags.LOGS)
            .initialProperties(() -> Blocks.OAK_LOG)
            .blockstate((genContext, provider) -> provider.logBlock(genContext.get()))
            .loot(Loots::dropSelf)
            .simpleItem()
            .register();

//    public static final RegistryEntry<SaplingBlock> WILLOW_SAPLING = REGISTRATE
//            .block("willow_sapling", p -> new SaplingBlock())

    public static void register() {}

}
