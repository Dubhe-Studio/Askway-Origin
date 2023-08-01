package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import dev.dubhe.askway.origin.items.*;
import dev.dubhe.askway.origin.magical.effects.IEffect;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.modes.IMode;
import dev.dubhe.askway.origin.magical.visuals.IVisual;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

@SuppressWarnings("unused")
public class AskwayModItems {

    static {
        if (AskwayModCreativeModeTabs.ORIGIN.getKey() != null) {
            REGISTRATE.defaultCreativeTab(AskwayModCreativeModeTabs.ORIGIN.getKey());
        }
    }

    public static final RegistryEntry<SwordItem> PEACH_WOODEN_SWORD = REGISTRATE
            .item("peach_wooden_sword", p -> new SwordItem(Tiers.WOOD, 3, -2.4F, p))
            .properties(p -> p.stacksTo(1).durability(56).defaultDurability(56))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ctx.get())
                    .pattern("A")
                    .pattern("A")
                    .pattern("B")
                    .define('A', AskwayModBlocks.PEACH_PLANKS.get())
                    .define('B', Items.STICK)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(AskwayModBlocks.PEACH_PLANKS.get()))
                    .save(provider))
            .register();

    public static final RegistryEntry<SwordItem> LIGHTNING_PEACH_WOODEN_SWORD = REGISTRATE
            .item("lightning_peach_wooden_sword", p -> new SwordItem(Tiers.WOOD, 3, -2.4F, p))
            .properties(p -> p.stacksTo(1).durability(56).defaultDurability(56))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ctx.get())
                    .pattern("A")
                    .pattern("A")
                    .pattern("B")
                    .define('A', AskwayModBlocks.LIGHTNING_PEACH_PLANKS.get())
                    .define('B', Items.STICK)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(AskwayModBlocks.LIGHTNING_PEACH_PLANKS.get()))
                    .save(provider))
            .register();

    public static final RegistryEntry<Item> COPPER_COIN = REGISTRATE.item("copper_coin", Item::new)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ctx.get(), 4)
                    .pattern("AAA")
                    .pattern("A A")
                    .pattern("AAA")
                    .define('A', Items.COPPER_INGOT)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(Items.COPPER_INGOT))
                    .save(provider))
            .register();

    public static final RegistryEntry<SwordItem> COPPER_COIN_SWORD = REGISTRATE
            .item("copper_coin_sword", p -> new SwordItem(Tiers.IRON, 3, -2.4F, p))
            .properties(p -> p.stacksTo(1).durability(156).defaultDurability(156))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ctx.get())
                    .pattern("A")
                    .pattern("A")
                    .pattern("B")
                    .define('A', COPPER_COIN.get())
                    .define('B', Items.STICK)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(COPPER_COIN.get()))
                    .save(provider))
            .register();

    public static final RegistryEntry<Item> BARK = REGISTRATE
            .item("bark", Item::new)
            .register();

    public static final RegistryEntry<Item> LANA = REGISTRATE
            .item("lana", Item::new)
            .register();

    public static final RegistryEntry<Item> TALISMAN_PAPER = REGISTRATE
            .item("talisman_paper", Item::new)
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ctx.get())
                    .requires(BARK.get())
                    .requires(Items.PAPER)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(BARK.get()))
                    .save(provider))
            .properties(p -> p.stacksTo(16))
            .register();

    public static final RegistryEntry<Item> TALISMAN_INK = REGISTRATE
            .item("talisman_ink", Item::new)
            .properties(p -> p.stacksTo(1).durability(64).defaultDurability(64))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ctx.get())
                    .requires(Items.GLOWSTONE_DUST)
                    .requires(Items.INK_SAC)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(Items.GLOWSTONE_DUST))
                    .save(provider))
            .register();

    public static final RegistryEntry<Item> TALISMAN_BRUSH = REGISTRATE
            .item("talisman_brush", Item::new)
            .properties(p -> p.stacksTo(1).durability(64).defaultDurability(64))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
                    .pattern("A")
                    .pattern("B")
                    .pattern("C")
                    .define('A', LANA.get())
                    .define('B', Items.HONEYCOMB)
                    .define('C', Items.BAMBOO)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(LANA.get()))
                    .save(provider))
            .register();

    public static final RegistryEntry<ScraperItem> SCRAPER = REGISTRATE
            .item("scraper", ScraperItem::new)
            .properties(p -> p.stacksTo(1).durability(64).defaultDurability(64))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
                    .pattern("A")
                    .pattern("B")
                    .define('A', Items.IRON_INGOT)
                    .define('B', Items.STICK)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(Items.IRON_INGOT))
                    .save(provider))
            .register();

    public static final RegistryEntry<Item> EMPTY_RUNE = REGISTRATE
            .item("empty_rune", Item::new)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                    .pattern("AAA")
                    .pattern("A A")
                    .pattern("AAA")
                    .define('A', Items.STONE)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(Items.STONE))
                    .save(provider))
            .register();

    public static final RegistryEntry<ElementRuneItem> METAL_ELEMENT_RUNE = REGISTRATE
            .item("metal_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.METAL))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Items.IRON_INGOT))
            .register();

    public static final RegistryEntry<ElementRuneItem> BOTANY_ELEMENT_RUNE = REGISTRATE
            .item("botany_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.BOTANY))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Items.STICK))
            .register();

    public static final RegistryEntry<ElementRuneItem> WATER_ELEMENT_RUNE = REGISTRATE
            .item("water_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.WATER))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Items.WATER_BUCKET))
            .register();

    public static final RegistryEntry<ElementRuneItem> FIRE_ELEMENT_RUNE = REGISTRATE
            .item("fire_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.FIRE))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Items.BLAZE_POWDER))
            .register();

    public static final RegistryEntry<ElementRuneItem> EARTH_ELEMENT_RUNE = REGISTRATE
            .item("earth_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.EARTH))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Items.DIRT))
            .register();

    public static final RegistryEntry<ElementRuneItem> THUNDER_ELEMENT_RUNE = REGISTRATE
            .item("thunder_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.THUNDER))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Blocks.LIGHTNING_ROD))
            .register();

    public static final RegistryEntry<ElementRuneItem> FREEZE_ELEMENT_RUNE = REGISTRATE
            .item("freeze_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.FREEZE))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Items.ICE))
            .register();

    public static final RegistryEntry<ElementRuneItem> WIND_ELEMENT_RUNE = REGISTRATE
            .item("wind_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.WIND))
            .color(() -> () -> (pStack, pTintIndex) -> {
                if (!(pStack.getItem() instanceof ElementRuneItem elemRune)) return 0;
                return elemRune.getData().getColor().getInt() >> 8;
            })
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "element_rune")))
            .recipe(new RuneRecipe<>(Items.PHANTOM_MEMBRANE))
            .register();

    public static final RegistryEntry<GoalRuneItem> EXACT_GOAL_RUNE = REGISTRATE
            .item("exact_target_rune", properties -> new GoalRuneItem(properties, IGoal.EXACT))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "target_rune")))
            .recipe(new RuneRecipe<>(Items.ENDER_PEARL))
            .register();

    public static final RegistryEntry<GoalRuneItem> RANGE_GOAL_RUNE = REGISTRATE
            .item("range_target_rune", properties -> new GoalRuneItem(properties, IGoal.RANGE))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "target_rune")))
            .recipe(new RuneRecipe<>(Items.ARROW))
            .register();

    public static final RegistryEntry<ModeRuneItem> TOUCH_MODE_RUNE = REGISTRATE
            .item("touch_mode_rune", properties -> new ModeRuneItem(properties, IMode.TOUCH))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "mode_rune")))
            .recipe(new RuneRecipe<>(Items.SLIME_BALL))
            .register();

    public static final RegistryEntry<ModeRuneItem> SELF_MODE_RUNE = REGISTRATE
            .item("self_mode_rune", properties -> new ModeRuneItem(properties, IMode.SELF))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "mode_rune")))
            .recipe(new RuneRecipe<>(Items.GLASS_BOTTLE))
            .register();

    public static final RegistryEntry<ModeRuneItem> THROW_MODE_RUNE = REGISTRATE
            .item("throw_mode_rune", properties -> new ModeRuneItem(properties, IMode.THROW))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "mode_rune")))
            .recipe(new RuneRecipe<>(Items.SUGAR))
            .register();

    public static final RegistryEntry<ModeRuneItem> SHOOT_MODE_RUNE = REGISTRATE
            .item("shoot_mode_rune", properties -> new ModeRuneItem(properties, IMode.SHOOT))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "mode_rune")))
            .recipe(new RuneRecipe<>(Items.GUNPOWDER))
            .register();

    public static final RegistryEntry<EffectRuneItem> BREAK_EFFECT_RUNE = REGISTRATE
            .item("break_effect_rune", properties -> new EffectRuneItem(properties, IEffect.BREAK))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "effect_rune")))
            .recipe(new RuneRecipe<>(Items.WOODEN_PICKAXE))
            .register();

    public static final RegistryEntry<EffectRuneItem> TELEPORT_EFFECT_RUNE = REGISTRATE
            .item("teleport_effect_rune", properties -> new EffectRuneItem(properties, IEffect.TELEPORT))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "effect_rune")))
            .recipe(new RuneRecipe<>(Items.WOODEN_PICKAXE))
            .register();

    public static final RegistryEntry<EffectRuneItem> EXPLOSION_EFFECT_RUNE = REGISTRATE
            .item("explosion_effect_rune", properties -> new EffectRuneItem(properties, IEffect.EXPLOSION))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "effect_rune")))
            .recipe(new RuneRecipe<>(Items.WOODEN_PICKAXE))
            .register();

    public static final RegistryEntry<EffectRuneItem> LIGHTING_EFFECT_RUNE = REGISTRATE
            .item("lightning_effect_rune", properties -> new EffectRuneItem(properties, IEffect.LIGHTING))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "effect_rune")))
            .recipe(new RuneRecipe<>(Items.WOODEN_PICKAXE))
            .register();

    public static final RegistryEntry<EffectRuneItem> TREAT_EFFECT_RUNE = REGISTRATE
            .item("treat_effect_rune", properties -> new EffectRuneItem(properties, IEffect.LIGHTING))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "effect_rune")))
            .recipe(new RuneRecipe<>(Items.WOODEN_PICKAXE))
            .register();

    public static final RegistryEntry<VisualRuneItem> STRAIGHT_LINE_VISUAL_RUNE = REGISTRATE
            .item("straight_line_visual_rune", properties -> new VisualRuneItem(properties, IVisual.STRAIGHT_LINE))
            .model((ctx, provider) -> provider.withExistingParent(ctx.getName(), provider.modLoc("item/" + "visual_rune")))
            .recipe(new RuneRecipe<>(Items.STICK))
            .register();

    public static final RegistryEntry<TalismanItem> TALISMAN = REGISTRATE
            .item("talisman", TalismanItem::new)
            .register();

    public static void register() {
    }

    public static List<ElementRuneItem> elems() {
        List<ElementRuneItem> elems = new ArrayList<>();
        elems.add(METAL_ELEMENT_RUNE.get());
        elems.add(BOTANY_ELEMENT_RUNE.get());
        elems.add(WATER_ELEMENT_RUNE.get());
        elems.add(FIRE_ELEMENT_RUNE.get());
        elems.add(EARTH_ELEMENT_RUNE.get());
        elems.add(THUNDER_ELEMENT_RUNE.get());
        elems.add(FREEZE_ELEMENT_RUNE.get());
        elems.add(WIND_ELEMENT_RUNE.get());
        return elems;
    }

    public static class RuneRecipe<T extends Item> implements NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> {
        private final ItemLike item;

        public RuneRecipe(ItemLike item) {
            this.item = item;
        }

        @Override
        public void accept(@NotNull DataGenContext<Item, T> ctx, @NotNull RegistrateRecipeProvider provider) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                    .pattern("A")
                    .pattern("B")
                    .define('A', EMPTY_RUNE.get())
                    .define('B', this.item)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(EMPTY_RUNE.get()))
                    .save(provider);
        }
    }
}
