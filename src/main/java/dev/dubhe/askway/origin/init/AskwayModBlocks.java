package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.init.blocks.WillowTreeGrower;
import dev.dubhe.askway.origin.utils.Loots;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

@SuppressWarnings("unused")
public class AskwayModBlocks {

    static {
        if (AskwayModCreativeModeTabs.ORIGIN.getKey() != null) {
            REGISTRATE.defaultCreativeTab(AskwayModCreativeModeTabs.ORIGIN.getKey());
        }
    }


    public static final RegistryEntry<RotatedPillarBlock> WILLOW_LOG = REGISTRATE.block("willow_log", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.WILLOW_LOGS)
            .initialProperties(() -> Blocks.OAK_LOG)
            .blockstate((ctx, provider) -> provider.logBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .item()
            .tag(AskwayModTags.Items.WILLOW_LOGS)
            .build()
            .register();

    public static final RegistryEntry<RotatedPillarBlock> WILLOW_WOOD = REGISTRATE.block("willow_wood", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.WILLOW_LOGS)
            .initialProperties(() -> Blocks.OAK_WOOD)
            .blockstate((ctx, provider) -> provider.axisBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName()),
                    provider.modLoc("block/" + ctx.getName())
            ))
            .loot(Loots::dropSelf)
            .item()
            .tag(AskwayModTags.Items.WILLOW_LOGS)
            .build()
            .register();

    public static final RegistryEntry<Block> WILLOW_PLANKS = REGISTRATE.block("willow_planks", Block::new)
            .tag(BlockTags.PLANKS)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .blockstate((ctx, provider) -> provider.simpleBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 4)
                    .requires(Ingredient.of(AskwayModTags.Items.WILLOW_LOGS))
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(AskwayModTags.Items.WILLOW_LOGS))
                    .save(provider))
            .item()
            .tag(ItemTags.PLANKS)
            .build()
            .register();

    public static final RegistryEntry<SaplingBlock> WILLOW_SAPLING = REGISTRATE
            .block("willow_sapling", p -> new SaplingBlock(new WillowTreeGrower(), p))
            .tag(BlockTags.SAPLINGS)
            .initialProperties(() -> Blocks.OAK_SAPLING)
            .blockstate((ctx, provider) -> {

                provider.simpleBlock(ctx.get());
                provider.models().cross(ctx.getName(), provider.modLoc("block/" + ctx.getName())).renderType("cutout");
            })
            .item()
            .model((ctx, provider) -> provider.generated(ctx, provider.modLoc("block/" + ctx.getName())))
            .build()
            .register();
    public static final RegistryEntry<LeavesBlock> WILLOW_LEAVES = REGISTRATE.block("willow_leaves", LeavesBlock::new)
            .tag(BlockTags.LEAVES)
            .initialProperties(() -> Blocks.OAK_LEAVES)
            .blockstate((ctx, provider) -> {
                provider.simpleBlock(ctx.get());
                provider.models().cubeAll(ctx.getName(), provider.modLoc("block/" + ctx.getName())).renderType("cutout");
            })
            .loot((lootTables, block) -> Loots.createLeavesDrops(lootTables, block, WILLOW_SAPLING.get(), 0.05f, 0.075f, 0.85f, 0.1f))
            .item()
            .tag(ItemTags.LEAVES)
            .build()
            .register();



    public static void register() {
    }

}
