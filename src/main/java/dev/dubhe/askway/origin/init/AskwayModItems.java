package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import dev.dubhe.askway.origin.init.items.ScraperItem;
import dev.dubhe.askway.origin.items.*;
import dev.dubhe.askway.origin.magical.effects.IEffect;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.modes.IMode;
import dev.dubhe.askway.origin.magical.visuals.IVisual;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

@SuppressWarnings("unused")
public class AskwayModItems {

    static {
        if (AskwayModCreativeModeTabs.ORIGIN.getKey() != null) {
            REGISTRATE.defaultCreativeTab(AskwayModCreativeModeTabs.ORIGIN.getKey());
        }
    }

    public static final RegistryEntry<MagicTestItem> MAGIC_TEST = REGISTRATE
            .item("magic_test", MagicTestItem::new)
            .register();

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

    public static final RegistryEntry<Item> COPPER_CASH = REGISTRATE.item("copper_cash", Item::new)
            .register();

    public static final RegistryEntry<SwordItem> COPPER_CASH_SWORD = REGISTRATE
            .item("copper_cash_sword", p -> new SwordItem(Tiers.IRON, 3, -2.4F, p))
            .properties(p -> p.stacksTo(1).durability(156).defaultDurability(156))
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ctx.get())
                    .pattern("A")
                    .pattern("A")
                    .pattern("B")
                    .define('A', COPPER_CASH.get())
                    .define('B', Items.STICK)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(COPPER_CASH.get()))
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
            .properties(p -> p.stacksTo(1).durability(64).defaultDurability(64))
            .register();

    public static final RegistryEntry<Item> TALISMAN_INK = REGISTRATE
            .item("talisman_ink", Item::new)
            .properties(p -> p.stacksTo(1).durability(64).defaultDurability(64))
            .recipe((ctx, provider) -> ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ctx.get())
                    .requires(Items.GLOWSTONE_DUST)
                    .requires(Items.INK_SAC)
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(Items.GLOWSTONE_DUST))
                    .unlockedBy("has_item", RegistrateRecipeProvider.has(Items.INK_SAC))
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

    public static final RegistryEntry<ElementRuneItem> FIRE_ELEMENT_RUNE = REGISTRATE
            .item("fire_element_rune", properties -> new ElementRuneItem(properties, AbstractElement.FIRE))
            .recipe(new RuneRecipe<>(Items.BLAZE_POWDER))
            .register();

    public static final RegistryEntry<GoalRuneItem> EXACT_GOAL_RUNE = REGISTRATE
            .item("exact_goal_rune", properties -> new GoalRuneItem(properties, IGoal.EXACT))
            .recipe(new RuneRecipe<>(Items.ENDER_PEARL))
            .register();

    public static final RegistryEntry<GoalRuneItem> RANGE_GOAL_RUNE = REGISTRATE
            .item("range_goal_rune", properties -> new GoalRuneItem(properties, IGoal.RANGE))
            .recipe(new RuneRecipe<>(Items.ARROW))
            .register();

    public static final RegistryEntry<ModeRuneItem> TOUCH_MODE_RUNE = REGISTRATE
            .item("touch_mode_rune", properties -> new ModeRuneItem(properties, IMode.TOUCH))
            .recipe(new RuneRecipe<>(Items.SLIME_BALL))
            .register();

    public static final RegistryEntry<ModeRuneItem> SELF_MODE_RUNE = REGISTRATE
            .item("self_mode_rune", properties -> new ModeRuneItem(properties, IMode.SELF))
            .recipe(new RuneRecipe<>(Items.GLASS_BOTTLE))
            .register();

    public static final RegistryEntry<ModeRuneItem> THROW_MODE_RUNE = REGISTRATE
            .item("throw_mode_rune", properties -> new ModeRuneItem(properties, IMode.THROW))
            .recipe(new RuneRecipe<>(Items.SUGAR))
            .register();

    public static final RegistryEntry<ModeRuneItem> SHOOT_MODE_RUNE = REGISTRATE
            .item("shoot_mode_rune", properties -> new ModeRuneItem(properties, IMode.SHOOT))
            .recipe(new RuneRecipe<>(Items.GUNPOWDER))
            .register();

    public static final RegistryEntry<EffectRuneItem> BREAK_EFFECT_RUNE = REGISTRATE
            .item("break_effect_rune", properties -> new EffectRuneItem(properties, IEffect.BREAK))
            .recipe(new RuneRecipe<>(Items.WOODEN_PICKAXE))
            .register();

    public static final RegistryEntry<VisualRuneItem> STRAIGHT_LINE_VISUAL_RUNE = REGISTRATE
            .item("straight_line_visual_rune", properties -> new VisualRuneItem(properties, IVisual.STRAIGHT_LINE))
            .recipe(new RuneRecipe<>(Items.STICK))
            .register();

    public static final RegistryEntry<TalismanItem> TALISMAN = REGISTRATE
            .item("talisman", TalismanItem::new)
            .register();

    public static void register() {
    }

    public static class RuneRecipe<T extends Item> implements NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> {
        private final Item item;

        public RuneRecipe(Item item) {
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
