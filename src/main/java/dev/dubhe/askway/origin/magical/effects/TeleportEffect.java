package dev.dubhe.askway.origin.magical.effects;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.phys.Vec3;

public class TeleportEffect implements IEffect {
    @Override
    public void execute(ICaster caster, AbstractElement element, int energy, ITarget target) {
        Vec3 pos = caster.getPos();
        caster.setPos(target.getPos());
        target.setPos(pos);
    }

    @Override
    public int getWeights() {
        return 10;
    }
}
