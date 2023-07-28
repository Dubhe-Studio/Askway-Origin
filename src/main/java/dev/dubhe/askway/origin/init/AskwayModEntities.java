package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.dubhe.askway.origin.entities.MagicEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class AskwayModEntities {
    public static final RegistryEntry<EntityType<MagicEntity>> MAGIC = REGISTRATE.entity("magic", MagicEntity::new, MobCategory.MISC)
            .register();
}
