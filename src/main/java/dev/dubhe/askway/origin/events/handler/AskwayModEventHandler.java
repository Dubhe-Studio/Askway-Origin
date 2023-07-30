package dev.dubhe.askway.origin.events.handler;

import dev.dubhe.askway.origin.entities.SpiritEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
}
