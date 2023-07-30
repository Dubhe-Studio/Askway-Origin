package dev.dubhe.askway.origin.init;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.dubhe.askway.origin.blocks.entities.TalismanTableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

@SuppressWarnings("unused")
public class AskwayModBlockEntities {
    public static final BlockEntityEntry<BlockEntity> TALISMAN_TABLE = REGISTRATE
            .blockEntity("talisman_table",(type, pos, state) -> new TalismanTableBlockEntity(pos, state))
            .register();

    public static void register() {
    }
}
