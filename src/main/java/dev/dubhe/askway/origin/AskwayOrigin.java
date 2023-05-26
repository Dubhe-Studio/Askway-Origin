package dev.dubhe.askway.origin;

import com.mojang.logging.LogUtils;
import dev.dubhe.askway.origin.items.MyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.CreativeModeTabEvent;
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

    public AskwayOrigin() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::register);
        modEventBus.addListener(this::registerCreativeModeTab);
    }

    public void register(@NotNull RegisterEvent event) {
        for (Map.Entry<String, Item> entry : MyItems.ITEM_MAP.entrySet()) {
            event.register(Registries.ITEM, AskwayOrigin.of(entry.getKey()), entry::getValue);
        }
    }

    public void registerCreativeModeTab(@NotNull CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(
                AskwayOrigin.of("origin"),
                MyItems.ORIGIN
        );
    }

    public static @NotNull ResourceLocation of(String str) {
        return new ResourceLocation(MODID, str);
    }
}
