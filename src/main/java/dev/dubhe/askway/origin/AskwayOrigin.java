package dev.dubhe.askway.origin;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.dubhe.askway.origin.init.AskwayModBlocks;
import dev.dubhe.askway.origin.init.AskwayModCreativeModeTabs;
import dev.dubhe.askway.origin.init.AskwayModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Map;

@Mod(AskwayOrigin.MODID)
public class AskwayOrigin {
    public static final String MODID = "askway_origin";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(MODID));

    public AskwayOrigin() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::register);
        modEventBus.addListener(this::registerCreativeModeTab);

        AskwayModCreativeModeTabs.register();
        AskwayModBlocks.register();

    }

    public void register(@NotNull RegisterEvent event) {
        for (Map.Entry<String, Item> entry : AskwayModItems.ITEM_MAP.entrySet()) {
            event.register(Registries.ITEM, AskwayOrigin.of(entry.getKey()), entry::getValue);
        }
    }

    public void registerCreativeModeTab(@NotNull RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, AskwayOrigin.of("origin"), () -> AskwayModItems.ORIGIN);
    }

    public static @NotNull ResourceLocation of(String str) {
        return new ResourceLocation(MODID, str);
    }

    public static Registrate getRegistrate() {
        return REGISTRATE.get();
    }
}
