package dev.dubhe.askway.origin;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import dev.dubhe.askway.origin.init.AskwayModBlocks;
import dev.dubhe.askway.origin.init.AskwayModCreativeModeTabs;
import dev.dubhe.askway.origin.init.AskwayModEntities;
import dev.dubhe.askway.origin.init.AskwayModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(AskwayOrigin.MODID)
public class AskwayOrigin {
    public static final String MODID = "askway_origin";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrate REGISTRATE = Registrate.create(MODID);

    public AskwayOrigin() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        AskwayModCreativeModeTabs.TAB_REGISTER.register(modEventBus);

        AskwayModBlocks.register();
        AskwayModItems.register();
        AskwayModEntities.register();

    }

    public static @NotNull ResourceLocation of(String str) {
        return new ResourceLocation(MODID, str);
    }

}
