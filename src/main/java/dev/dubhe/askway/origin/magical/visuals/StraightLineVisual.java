package dev.dubhe.askway.origin.magical.visuals;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.phys.Vec3;

public class StraightLineVisual implements IVisual {
    @Override
    public void display(ICaster caster, AbstractElement element, int energy, ITarget target) {
        Vec3 pos = caster.getPos();
        Vec3 pos2 = target.getPos();
        Vec3 step = pos2.subtract(pos).multiply(1.0 / energy, 1.0 / energy, 1.0 / energy);
        for (int i = 0; i < energy; i++) {
            pos = pos.add(step);
            System.out.println(pos);
            System.out.println(pos2);
            target.getLevel().addParticle(new DustParticleOptions(element.getColor().getVec3f(), element.getColor().getAlphaF()),pos.x, pos.y, pos.z,0,0,0);
        }
    }

    @Override
    public int getWeights() {
        return 1;
    }
}
