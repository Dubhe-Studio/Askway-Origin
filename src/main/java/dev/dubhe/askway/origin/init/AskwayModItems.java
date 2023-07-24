package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.init.items.ScraperItem;
import dev.dubhe.askway.origin.items.MagicTestItem;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
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
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(AskwayModBlocks.PEACH_PLANKS.get()))
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
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(AskwayModBlocks.LIGHTNING_PEACH_PLANKS.get()))
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
                    .unlockedBy("hasitem", RegistrateRecipeProvider.has(COPPER_CASH.get()))
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
            .register();

    public static final RegistryEntry<Item> TALISMAN_INK = REGISTRATE
            .item("talisman_ink", Item::new)
            .register();

    public static final RegistryEntry<Item> TALISMAN_BRUSH = REGISTRATE
            .item("talisman_brush", Item::new)
            .register();

    public static final RegistryEntry<ScraperItem> SCRAPER = REGISTRATE
            .item("scraper", ScraperItem::new)
            .properties(p -> p.stacksTo(1).durability(64).defaultDurability(64))
            .register();


    public static @NotNull ItemStack withEnergy(@NotNull ItemStack stack, int energy) {
        stack.getOrCreateTag().putInt("energy", energy);
        return stack;
    }

    public static void register() {
    }

}
