package dev.dubhe.askway.origin.magical.visuals;

import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StraightLineVisual implements IVisual {
    @Override
    @OnlyIn(Dist.CLIENT)
    public void display(Vec3 casterPos, Vec3 targetPos, AbstractElement element, int energy) {
        Vec3 step = targetPos.subtract(casterPos).multiply(1.0 / energy, 1.0 / energy, 1.0 / energy);
        for (int i = 0; i < energy; i++) {
            if (Minecraft.getInstance().level == null) continue;
            Minecraft.getInstance().level.addParticle(new DustParticleOptions(element.getColor().getVec3f(), element.getColor().getAlphaF()), casterPos.x, casterPos.y, casterPos.z, 0, 0, 0);
            casterPos = casterPos.add(step);
        }
    }

    @Override
    public int getWeights() {
        return 1;
    }
}
