package dev.dubhe.askway.origin.mixins;

import dev.dubhe.askway.origin.player.AskwayPlayerData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    private final AskwayPlayerData data = new AskwayPlayerData();

    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    private void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        CompoundTag dataTag = tag.getCompound("AskwayPlayerData");
        dataTag.putDouble("MaxHP", data.maxHP);
        dataTag.putDouble("MaxHunger", data.maxHunger);
        dataTag.putDouble("DEF", data.def);
        dataTag.putDouble("ElementDEF", data.elementDef);
        dataTag.putDouble("ATKSpeed", data.atkSpeed);
        dataTag.putDouble("ATKDMG", data.atkDmg);
        dataTag.putDouble("ElementDMG", data.elementDmg);
        dataTag.putDouble("ElementRES", data.elementRes);
        dataTag.putDouble("CRITDMG", data.critDmg);
        dataTag.putDouble("CRITRate", data.critRate);
        dataTag.putDouble("Speed", data.speed);
        dataTag.putDouble("Jump", data.jump);
        tag.put("AskwayPlayerData", dataTag);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    private void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        CompoundTag dataTag = tag.getCompound("AskwayPlayerData");
        data.maxHP = dataTag.getDouble("MaxHP");
        data.maxHunger = dataTag.getDouble("MaxHunger");
        data.def = dataTag.getDouble("DEF");
        data.elementDef = dataTag.getDouble("ElementDEF");
        data.atkSpeed = dataTag.getDouble("ATKSpeed");
        data.atkDmg = dataTag.getDouble("ATKDMG");
        data.elementDmg = dataTag.getDouble("ElementDMG");
        data.elementRes = dataTag.getDouble("ElementRES");
        data.critDmg = dataTag.getDouble("CRITDMG");
        data.critRate = dataTag.getDouble("CRITRate");
        data.speed = dataTag.getDouble("Speed");
        data.jump = dataTag.getDouble("Jump");
    }
}
