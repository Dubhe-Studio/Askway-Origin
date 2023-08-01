package dev.dubhe.askway.origin.events.handler;

import dev.dubhe.askway.origin.entities.SpiritEntity;
import dev.dubhe.askway.origin.init.AskwayModClientInit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AskwayModEventHandler {
    @SubscribeEvent
    public void onPlayerDie(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        Level level;
        if ((level = player.level()).isClientSide) return;
        if (Minecraft.getInstance().getConnection() == null) return;
        SpiritEntity entity = SpiritEntity.create(level, Minecraft.getInstance().getConnection().getPlayerInfo(player.getUUID()));
        entity.moveTo(player.position());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onItemColorRegister(RegisterColorHandlersEvent.Item event) {
        AskwayModClientInit.registerItemColor(event);
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if (!(event.getItemStack().getItem() instanceof BlockItem item)) return;
        float time = item.getBlock().defaultDestroyTime();
        event.getToolTip().add(Component.literal("硬度：%s".formatted(time)).withStyle(ChatFormatting.RED).withStyle(Style.EMPTY.withItalic(false)));
    }
}
