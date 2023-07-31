package dev.dubhe.askway.origin.init;

import net.minecraft.world.level.block.state.properties.WoodType;

public class AskwayModWoodTypes {
    public static final WoodType WILLOW = WoodType.register(new WoodType("willow", AskwayModBlockSetTypes.WILLOW));
    public static final WoodType PEACH = WoodType.register(new WoodType("peach", AskwayModBlockSetTypes.PEACH));
    public static final WoodType LIGHTNING_PEACH = WoodType.register(new WoodType("lightning_peach", AskwayModBlockSetTypes.LIGHTNING_PEACH));
}
