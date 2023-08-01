package dev.dubhe.askway.origin.events.handler;

import dev.dubhe.askway.origin.entities.SpiritEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AskwayModEventHandler {
    @SubscribeEvent
    public void onPlayerDie(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        Level level = player.level();
        SpiritEntity entity = SpiritEntity.create(level, player.getUUID());
        entity.moveTo(player.position());
        level.addFreshEntity(entity);
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if (!(event.getItemStack().getItem() instanceof BlockItem item)) return;
        float time = item.getBlock().defaultDestroyTime();
        event.getToolTip().add(Component.literal("硬度：%s".formatted(time)).withStyle(ChatFormatting.RED).withStyle(Style.EMPTY.withItalic(false)));
    }

    @SubscribeEvent
    public void onLivingEntityAttacked(EntityEvent event) {

    }
}
