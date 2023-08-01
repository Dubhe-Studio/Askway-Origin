package dev.dubhe.askway.origin;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import dev.dubhe.askway.origin.events.handler.AskwayModEventHandler;
import dev.dubhe.askway.origin.init.*;
import dev.dubhe.askway.origin.network.MagicalVisualNetworkImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
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

        MinecraftForge.EVENT_BUS.register(new AskwayModEventHandler());

        AskwayModBlocks.register();
        AskwayModEntities.register();
        AskwayModBlockEntities.register();
        AskwayModMenus.register();
        AskwayModMobEffects.register();
        AskwayModItems.register();
        MagicalVisualNetworkImpl.register();
    }

    public static @NotNull ResourceLocation of(String str) {
        return new ResourceLocation(MODID, str);
    }

}
