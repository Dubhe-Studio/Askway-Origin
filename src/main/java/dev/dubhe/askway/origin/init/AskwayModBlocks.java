package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.init.blocks.PeachTreeGrower;
import dev.dubhe.askway.origin.init.blocks.StrippableRotatedPillarBlock;
import dev.dubhe.askway.origin.init.blocks.WillowTreeGrower;
import dev.dubhe.askway.origin.utils.Loots;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

@SuppressWarnings("unused")
public class AskwayModBlocks {

    static {
        if (AskwayModCreativeModeTabs.ORIGIN_BLOCKS.getKey() != null) {
            REGISTRATE.defaultCreativeTab(AskwayModCreativeModeTabs.ORIGIN_BLOCKS.getKey());
        }
    }

    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_WILLOW_LOG = REGISTRATE
            .block("stripped_willow_log", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.WILLOW_LOGS)
            .initialProperties(() -> Blocks.STRIPPED_OAK_LOG)
            .blockstate((ctx, provider) -> provider.logBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .item()
            .tag(AskwayModTags.Items.WILLOW_LOGS)
            .build()
            .register();


    public static final RegistryEntry<StrippableRotatedPillarBlock> WILLOW_LOG = REGISTRATE
            .block("willow_log", p -> new StrippableRotatedPillarBlock(p, STRIPPED_WILLOW_LOG.get()))
            .tag(AskwayModTags.Blocks.WILLOW_LOGS)
            .initialProperties(() -> Blocks.OAK_LOG)
            .blockstate((ctx, provider) -> provider.logBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .item()
            .tag(AskwayModTags.Items.WILLOW_LOGS)
            .build()
            .register();

    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_WILLOW_WOOD = REGISTRATE
            .block("stripped_willow_wood", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.WILLOW_LOGS)
            .initialProperties(() -> Blocks.STRIPPED_OAK_WOOD)
            .blockstate((ctx, provider) -> provider.axisBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log")),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 3)
                    .pattern("XX")
                    .pattern("XX")
                    .define('X', STRIPPED_WILLOW_LOG.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(STRIPPED_WILLOW_LOG.get()))
                    .save(provider))
            .item()
            .tag(AskwayModTags.Items.WILLOW_LOGS)
            .build()
            .register();
    public static final RegistryEntry<StrippableRotatedPillarBlock> WILLOW_WOOD = REGISTRATE
            .block("willow_wood", p -> new StrippableRotatedPillarBlock(p, STRIPPED_WILLOW_WOOD.get()))
            .tag(AskwayModTags.Blocks.WILLOW_LOGS)
            .initialProperties(() -> Blocks.OAK_WOOD)
            .blockstate((ctx, provider) -> provider.axisBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log")),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 3)
                    .pattern("XX")
                    .pattern("XX")
                    .define('X', WILLOW_LOG.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_LOG.get()))
                    .save(provider))
            .item()
            .tag(AskwayModTags.Items.WILLOW_LOGS)
            .build()
            .register();

    public static final RegistryEntry<Block> WILLOW_PLANKS = REGISTRATE
            .block("willow_planks", Block::new)
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

    public static final RegistryEntry<StairBlock> WILLOW_STAIRS = REGISTRATE
            .block("willow_stairs", p -> new StairBlock(() -> WILLOW_PLANKS.get().defaultBlockState(), p))
            .tag(BlockTags.WOODEN_STAIRS)
            .initialProperties(() -> Blocks.OAK_STAIRS)
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.get(), provider.modLoc("block/" + ctx.getName().replace("_stairs", "_planks"))))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 4)
                    .pattern("A  ")
                    .pattern("AA ")
                    .pattern("AAA")
                    .define('A', WILLOW_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_STAIRS)
            .build()
            .register();

    public static final RegistryEntry<SlabBlock> WILLOW_SLAB = REGISTRATE
            .block("willow_slab", SlabBlock::new)
            .tag(BlockTags.WOODEN_SLABS)
            .initialProperties(() -> Blocks.OAK_SLAB)
            .blockstate((ctx, provider) -> provider.slabBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_slab", "_planks")),
                    provider.modLoc("block/" + ctx.getName().replace("_slab", "_planks"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 6)
                    .pattern("AAA")
                    .define('A', WILLOW_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_SLABS)
            .build()
            .register();

    public static final RegistryEntry<FenceBlock> WILLOW_FENCE = REGISTRATE
            .block("willow_fence", FenceBlock::new)
            .tag(BlockTags.WOODEN_FENCES)
            .initialProperties(() -> Blocks.OAK_FENCE)
            .blockstate((ctx, provider) -> {
                provider.fenceBlock(
                        ctx.get(),
                        provider.modLoc("block/" + ctx.getName().replace("_fence", "_planks"))
                );
                provider.models().fenceInventory(
                        ctx.getName(),
                        provider.modLoc("block/" + ctx.getName().replace("_fence", "_planks"))
                );
            })
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get())
                    .pattern("ABA")
                    .pattern("ABA")
                    .define('A', Items.STICK)
                    .define('B', WILLOW_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_FENCES)
            .build()
            .register();

    public static final RegistryEntry<FenceGateBlock> WILLOW_FENCE_GATE = REGISTRATE
            .block("willow_fance_gate", p -> new FenceGateBlock(p, AskwayModWoodTypes.WILLOW))
            .tag(BlockTags.FENCE_GATES)
            .initialProperties(() -> Blocks.OAK_FENCE_GATE)
            .blockstate((ctx, provider) -> provider.fenceGateBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_fance_gate", "_planks"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get())
                    .pattern("ABA")
                    .pattern("ABA")
                    .define('A', Items.STICK)
                    .define('B', WILLOW_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.FENCE_GATES)
            .build()
            .register();

    public static final RegistryEntry<DoorBlock> WILLOW_DOOR = REGISTRATE
            .block("willow_door", p -> new DoorBlock(p, AskwayModBlockSetTypes.WILLOW))
            .tag(BlockTags.WOODEN_DOORS)
            .initialProperties(() -> Blocks.OAK_DOOR)
            .blockstate((ctx, provider) -> provider.doorBlockWithRenderType(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName() + "_bottom"),
                    provider.modLoc("block/" + ctx.getName() + "_top"),
                    "cutout"
            ))
            .loot(Loots::dropDoorSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get())
                    .pattern("AA")
                    .pattern("AA")
                    .pattern("AA")
                    .define('A', WILLOW_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_DOORS)
            .model((ctx, provider) -> provider.generated(ctx))
            .build()
            .register();

    public static final RegistryEntry<TrapDoorBlock> WILLOW_TRAPDOOR = REGISTRATE
            .block("willow_trapdoor", p -> new TrapDoorBlock(p, AskwayModBlockSetTypes.WILLOW))
            .tag(BlockTags.WOODEN_TRAPDOORS)
            .initialProperties(() -> Blocks.OAK_TRAPDOOR)
            .blockstate((ctx, provider) -> provider.trapdoorBlockWithRenderType(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName()),
                    true,
                    "cutout"
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get(), 2)
                    .pattern("AAA")
                    .pattern("AAA")
                    .define('A', WILLOW_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_TRAPDOORS)
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("block/" + ctx.getName() + "_bottom")))
            .build()
            .register();

    public static final RegistryEntry<PressurePlateBlock> WILLOW_PRESSURE_PLATE = REGISTRATE
            .block("willow_pressure_plate", p -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p, AskwayModBlockSetTypes.WILLOW))
            .tag(BlockTags.WOODEN_PRESSURE_PLATES)
            .initialProperties(() -> Blocks.OAK_PRESSURE_PLATE)
            .blockstate((ctx, provider) -> provider.pressurePlateBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_pressure_plate", "_planks"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get())
                    .pattern("AA")
                    .define('A', WILLOW_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_PRESSURE_PLATES)
            .build()
            .register();

    public static final RegistryEntry<ButtonBlock> WILLOW_BUTTON = REGISTRATE
            .block("willow_button", p -> new ButtonBlock(p, AskwayModBlockSetTypes.WILLOW, 20, true))
            .tag(BlockTags.WOODEN_BUTTONS)
            .initialProperties(() -> Blocks.OAK_BUTTON)
            .blockstate((ctx, provider) -> {
                provider.buttonBlock(
                        ctx.get(),
                        provider.modLoc("block/" + ctx.getName().replace("_button", "_planks"))
                );
                provider.models().singleTexture(
                        ctx.getName() + "_inventory",
                        provider.mcLoc("block/button_inventory"),
                        provider.modLoc("block/" + ctx.getName().replace("_button", "_planks"))
                );
            })
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ctx.get())
                    .requires(Ingredient.of(WILLOW_PLANKS.get()))
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(WILLOW_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_BUTTONS)
            .model((ctx, provider) -> provider.withExistingParent(
                    ctx.getName(),
                    provider.modLoc("block/" + ctx.getName() + "_inventory")
            ))
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
            .tag(ItemTags.SAPLINGS)
            .build()
            .register();
    public static final RegistryEntry<LeavesBlock> WILLOW_LEAVES = REGISTRATE
            .block("willow_leaves", LeavesBlock::new)
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

    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_PEACH_LOG = REGISTRATE
            .block("stripped_peach_log", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.PEACH_LOGS)
            .initialProperties(() -> Blocks.STRIPPED_OAK_LOG)
            .blockstate((ctx, provider) -> provider.logBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .item()
            .tag(AskwayModTags.Items.PEACH_LOGS)
            .build()
            .register();
    public static final RegistryEntry<StrippableRotatedPillarBlock> PEACH_LOG = REGISTRATE
            .block("peach_log", p -> new StrippableRotatedPillarBlock(p, STRIPPED_PEACH_LOG.get()))
            .tag(AskwayModTags.Blocks.PEACH_LOGS)
            .initialProperties(() -> Blocks.OAK_LOG)
            .blockstate((ctx, provider) -> provider.logBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .item()
            .tag(AskwayModTags.Items.PEACH_LOGS)
            .build()
            .register();

    public static final RegistryEntry<RotatedPillarBlock> STRIPPED_PEACH_WOOD = REGISTRATE
            .block("stripped_peach_wood", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.PEACH_LOGS)
            .initialProperties(() -> Blocks.STRIPPED_OAK_WOOD)
            .blockstate((ctx, provider) -> provider.axisBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log")),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 3)
                    .pattern("XX")
                    .pattern("XX")
                    .define('X', STRIPPED_PEACH_LOG.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(STRIPPED_PEACH_LOG.get()))
                    .save(provider))
            .item()
            .tag(AskwayModTags.Items.PEACH_LOGS)
            .build()
            .register();

    public static final RegistryEntry<StrippableRotatedPillarBlock> PEACH_WOOD = REGISTRATE
            .block("peach_wood", p -> new StrippableRotatedPillarBlock(p, STRIPPED_PEACH_WOOD.get()))
            .tag(AskwayModTags.Blocks.PEACH_LOGS)
            .initialProperties(() -> Blocks.OAK_WOOD)
            .blockstate((ctx, provider) -> provider.axisBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log")),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 3)
                    .pattern("XX")
                    .pattern("XX")
                    .define('X', PEACH_LOG.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_LOG.get()))
                    .save(provider))
            .item()
            .tag(AskwayModTags.Items.PEACH_LOGS)
            .build()
            .register();

    public static final RegistryEntry<Block> PEACH_PLANKS = REGISTRATE
            .block("peach_planks", Block::new)
            .tag(BlockTags.PLANKS)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .blockstate((ctx, provider) -> provider.simpleBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 4)
                    .requires(Ingredient.of(AskwayModTags.Items.PEACH_LOGS))
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(AskwayModTags.Items.PEACH_LOGS))
                    .save(provider)
            )
            .item()
            .tag(ItemTags.PLANKS)
            .build()
            .register();

    public static final RegistryEntry<StairBlock> PEACH_STAIRS = REGISTRATE
            .block("peach_stairs", p -> new StairBlock(() -> PEACH_PLANKS.get().defaultBlockState(), p))
            .tag(BlockTags.WOODEN_STAIRS)
            .initialProperties(() -> Blocks.OAK_STAIRS)
            .blockstate((ctx, provider) -> provider.stairsBlock(ctx.get(), provider.modLoc("block/" + ctx.getName().replace("_stairs", "_planks"))))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 4)
                    .pattern("A  ")
                    .pattern("AA ")
                    .pattern("AAA")
                    .define('A', PEACH_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_STAIRS)
            .build()
            .register();

    public static final RegistryEntry<SlabBlock> PEACH_SLAB = REGISTRATE
            .block("peach_slab", SlabBlock::new)
            .tag(BlockTags.WOODEN_SLABS)
            .initialProperties(() -> Blocks.OAK_SLAB)
            .blockstate((ctx, provider) -> provider.slabBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_slab", "_planks")),
                    provider.modLoc("block/" + ctx.getName().replace("_slab", "_planks"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 6)
                    .pattern("AAA")
                    .define('A', PEACH_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_SLABS)
            .build()
            .register();

    public static final RegistryEntry<FenceBlock> PEACH_FENCE = REGISTRATE
            .block("peach_fence", FenceBlock::new)
            .tag(BlockTags.WOODEN_FENCES)
            .initialProperties(() -> Blocks.OAK_FENCE)
            .blockstate((ctx, provider) -> {
                provider.fenceBlock(
                        ctx.get(),
                        provider.modLoc("block/" + ctx.getName().replace("_fence", "_planks"))
                );
                provider.models().fenceInventory(
                        ctx.getName(),
                        provider.modLoc("block/" + ctx.getName().replace("_fence", "_planks"))
                );
            })
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get())
                    .pattern("ABA")
                    .pattern("ABA")
                    .define('A', Items.STICK)
                    .define('B', PEACH_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_FENCES)
            .build()
            .register();

    public static final RegistryEntry<FenceGateBlock> PEACH_FENCE_GATE = REGISTRATE
            .block("peach_fance_gate", p -> new FenceGateBlock(p, AskwayModWoodTypes.PEACH))
            .tag(BlockTags.FENCE_GATES)
            .initialProperties(() -> Blocks.OAK_FENCE_GATE)
            .blockstate((ctx, provider) -> provider.fenceGateBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_fance_gate", "_planks"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get())
                    .pattern("ABA")
                    .pattern("ABA")
                    .define('A', Items.STICK)
                    .define('B', PEACH_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.FENCE_GATES)
            .build()
            .register();

    public static final RegistryEntry<DoorBlock> PEACH_DOOR = REGISTRATE
            .block("peach_door", p -> new DoorBlock(p, AskwayModBlockSetTypes.PEACH))
            .tag(BlockTags.WOODEN_DOORS)
            .initialProperties(() -> Blocks.OAK_DOOR)
            .blockstate((ctx, provider) -> provider.doorBlockWithRenderType(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName() + "_bottom"),
                    provider.modLoc("block/" + ctx.getName() + "_top"),
                    "cutout"
            ))
            .loot(Loots::dropDoorSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get())
                    .pattern("AA")
                    .pattern("AA")
                    .pattern("AA")
                    .define('A', PEACH_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_DOORS)
            .model((ctx, provider) -> provider.generated(ctx))
            .build()
            .register();

    public static final RegistryEntry<TrapDoorBlock> PEACH_TRAPDOOR = REGISTRATE
            .block("peach_trapdoor", p -> new TrapDoorBlock(p, AskwayModBlockSetTypes.PEACH))
            .tag(BlockTags.WOODEN_TRAPDOORS)
            .initialProperties(() -> Blocks.OAK_TRAPDOOR)
            .blockstate((ctx, provider) -> provider.trapdoorBlockWithRenderType(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName()),
                    true,
                    "cutout"
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get(), 2)
                    .pattern("AAA")
                    .pattern("AAA")
                    .define('A', PEACH_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_TRAPDOORS)
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("block/" + ctx.getName() + "_bottom")))
            .build()
            .register();

    public static final RegistryEntry<PressurePlateBlock> PEACH_PRESSURE_PLATE = REGISTRATE
            .block("peach_pressure_plate", p -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p, AskwayModBlockSetTypes.PEACH))
            .tag(BlockTags.WOODEN_PRESSURE_PLATES)
            .initialProperties(() -> Blocks.OAK_PRESSURE_PLATE)
            .blockstate((ctx, provider) -> provider.pressurePlateBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_pressure_plate", "_planks"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ctx.get())
                    .pattern("AA")
                    .define('A', PEACH_PLANKS.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_PRESSURE_PLATES)
            .build()
            .register();

    public static final RegistryEntry<ButtonBlock> PEACH_BUTTON = REGISTRATE
            .block("peach_button", p -> new ButtonBlock(p, AskwayModBlockSetTypes.PEACH, 20, true))
            .tag(BlockTags.WOODEN_BUTTONS)
            .initialProperties(() -> Blocks.OAK_BUTTON)
            .blockstate((ctx, provider) -> {
                provider.buttonBlock(
                        ctx.get(),
                        provider.modLoc("block/" + ctx.getName().replace("_button", "_planks"))
                );
                provider.models().singleTexture(
                        ctx.getName() + "_inventory",
                        provider.mcLoc("block/button_inventory"),
                        provider.modLoc("block/" + ctx.getName().replace("_button", "_planks"))
                );
            })
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ctx.get())
                    .requires(Ingredient.of(PEACH_PLANKS.get()))
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(PEACH_PLANKS.get()))
                    .save(provider))
            .item()
            .tag(ItemTags.WOODEN_BUTTONS)
            .model((ctx, provider) -> provider.withExistingParent(
                    ctx.getName(),
                    provider.modLoc("block/" + ctx.getName() + "_inventory")
            ))
            .build()
            .register();

    public static final RegistryEntry<SaplingBlock> PEACH_SAPLING = REGISTRATE
            .block("peach_sapling", p -> new SaplingBlock(new PeachTreeGrower(), p))
            .tag(BlockTags.SAPLINGS)
            .initialProperties(() -> Blocks.OAK_SAPLING)
            .blockstate((ctx, provider) -> {
                provider.simpleBlock(ctx.get());
                provider.models().cross(ctx.getName(), provider.modLoc("block/" + ctx.getName())).renderType("cutout");
            })
            .item()
            .model((ctx, provider) -> provider.generated(ctx, provider.modLoc("block/" + ctx.getName())))
            .tag(ItemTags.SAPLINGS)
            .build()
            .register();

    public static final RegistryEntry<LeavesBlock> PEACH_LEAVES = REGISTRATE
            .block("peach_leaves", LeavesBlock::new)
            .tag(BlockTags.LEAVES)
            .initialProperties(() -> Blocks.OAK_LEAVES)
            .blockstate((ctx, provider) -> {
                provider.simpleBlock(ctx.get());
                provider.models().cubeAll(ctx.getName(), provider.modLoc("block/" + ctx.getName())).renderType("cutout");
            })
            .loot((lootTables, block) -> Loots.createLeavesDrops(lootTables, block, PEACH_SAPLING.get(), 0.05f, 0.075f, 0.85f, 0.1f))
            .item()
            .tag(ItemTags.LEAVES)
            .build()
            .register();


    public static final RegistryEntry<RotatedPillarBlock> LIGHTNING_PEACH_LOG = REGISTRATE
            .block("lightning_peach_log", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.LIGHTNING_PEACH_LOGS)
            .initialProperties(() -> Blocks.OAK_LOG)
            .blockstate((ctx, provider) -> provider.logBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .item()
            .tag(AskwayModTags.Items.LIGHTNING_PEACH_LOGS)
            .build()
            .register();

    public static final RegistryEntry<RotatedPillarBlock> LIGHTNING_PEACH_WOOD = REGISTRATE
            .block("lightning_peach_wood", RotatedPillarBlock::new)
            .tag(AskwayModTags.Blocks.LIGHTNING_PEACH_LOGS)
            .initialProperties(() -> Blocks.OAK_WOOD)
            .blockstate((ctx, provider) -> provider.axisBlock(
                    ctx.get(),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log")),
                    provider.modLoc("block/" + ctx.getName().replace("_wood", "_log"))
            ))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 3)
                    .pattern("XX")
                    .pattern("XX")
                    .define('X', LIGHTNING_PEACH_LOG.get())
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(LIGHTNING_PEACH_LOG.get()))
                    .save(provider))
            .item()
            .tag(AskwayModTags.Items.LIGHTNING_PEACH_LOGS)
            .build()
            .register();

    public static final RegistryEntry<Block> LIGHTNING_PEACH_PLANKS = REGISTRATE
            .block("lightning_peach_planks", Block::new)
            .tag(BlockTags.PLANKS)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .blockstate((ctx, provider) -> provider.simpleBlock(ctx.get()))
            .loot(Loots::dropSelf)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ctx.get(), 4)
                    .requires(Ingredient.of(AskwayModTags.Items.LIGHTNING_PEACH_LOGS))
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(AskwayModTags.Items.LIGHTNING_PEACH_LOGS))
                    .save(provider)
            )
            .item()
            .tag(ItemTags.PLANKS)
            .build()
            .register();


    public static void register() {
    }

}
