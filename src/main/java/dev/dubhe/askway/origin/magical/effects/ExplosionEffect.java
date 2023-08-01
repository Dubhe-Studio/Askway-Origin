package dev.dubhe.askway.origin.magical.effects;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExplosionEffect implements IEffect {
    @Override
    public void execute(ICaster caster, AbstractElement element, int energy, ITarget target) {
        target.getLevel().explode(caster.getOwner(), caster.getDamageSource(), new ExplosionDamageCalculator(), target.getPos(), energy / 5.0F, element == AbstractElement.FIRE, Level.ExplosionInteraction.BLOCK);
    }

    @Override
    public int getWeights() {
        return 10;
    }
}
