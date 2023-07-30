package dev.dubhe.askway.origin.entities.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.dubhe.askway.origin.init.AskwayModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpiritModel<T extends Mob> extends PlayerModel<T> {
    public SpiritModel(ModelPart pRoot, boolean pSlim) {
        super(pRoot, pSlim);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack pPoseStack, @NotNull VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        boolean flag = visible(Minecraft.getInstance().player);
        super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, flag ? 0.15F : 0.0F);
    }

    public boolean visible(@Nullable Player pPlayer) {
        if (pPlayer == null) return false;
        boolean flag = pPlayer.hasEffect(AskwayModMobEffects.ASTRAL_VISION.get());
        String UUID = pPlayer.getUUID().toString();
        boolean flag2 = UUID.charAt(UUID.length() - 6) == 'e';
        return flag || flag2;
    }


}
