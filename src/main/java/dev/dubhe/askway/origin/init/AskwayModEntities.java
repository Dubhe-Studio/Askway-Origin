package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.EntityBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.entities.MagicEntity;
import dev.dubhe.askway.origin.entities.SpiritEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class AskwayModEntities {
    public static final EntityBuilder<MagicEntity, Registrate> MAGIC_BUILDER = REGISTRATE
            .entity("magic", MagicEntity::new, MobCategory.MISC);
    public static final EntityBuilder<SpiritEntity, Registrate> SPIRIT_BUILDER = REGISTRATE
            .entity("spirit", SpiritEntity::new, MobCategory.MONSTER)
            .loot(((tables, type) -> tables.add(type, new LootTable.Builder().withPool(new LootPool.Builder()))))
            .attributes(SpiritEntity::createAttributes);
    static {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> AskwayModClientInit::registerEntityRenderer);
    }
    public static final RegistryEntry<EntityType<MagicEntity>> MAGIC = MAGIC_BUILDER.register();
    public static final RegistryEntry<EntityType<SpiritEntity>> SPIRIT = SPIRIT_BUILDER.register();

    public static void register() {
    }
}
