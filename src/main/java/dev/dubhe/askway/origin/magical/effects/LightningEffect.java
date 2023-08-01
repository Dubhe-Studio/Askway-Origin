package dev.dubhe.askway.origin.magical.effects;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;

public class LightningEffect implements IEffect {
    @Override
    public void execute(ICaster caster, AbstractElement element, int energy, ITarget target) {
        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(target.getLevel());
        if (bolt == null) return;
        bolt.moveTo(target.getPos());
        target.getLevel().addFreshEntity(bolt);
    }

    @Override
    public int getWeights() {
        return 10;
    }
}
