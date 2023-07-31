package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.entities.MagicEntity;
import dev.dubhe.askway.origin.entities.SpiritEntity;
import dev.dubhe.askway.origin.entities.renderer.SpiritRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class AskwayModEntities {
    public static final RandomSource RANDOM = RandomSource.create();
    public static final RegistryEntry<EntityType<MagicEntity>> MAGIC = REGISTRATE
            .entity("magic", MagicEntity::new, MobCategory.MISC)
            .renderer(() -> ThrownItemRenderer::new)
            .register();

    public static final RegistryEntry<EntityType<SpiritEntity>> SPIRIT = REGISTRATE
            .entity("spirit", SpiritEntity::new, MobCategory.MONSTER)
            .loot(((tables, type) -> tables.add(type, new LootTable.Builder().withPool(new LootPool.Builder()))))
            .renderer(() -> (pContext) -> new SpiritRenderer(pContext, RANDOM.nextBoolean()))
            .register();

    public static void register() {
    }
}
