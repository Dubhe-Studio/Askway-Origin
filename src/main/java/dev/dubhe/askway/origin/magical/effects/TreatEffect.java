package dev.dubhe.askway.origin.magical.effects;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class TreatEffect implements IEffect {
    @Override
    public void execute(ICaster caster, AbstractElement element, int energy, ITarget target) {
        if (!(target instanceof EntityTarget entityTarget && entityTarget.getEntity() instanceof LivingEntity entity))
            return;
        entity.addEffect(new MobEffectInstance(MobEffects.HEAL));
    }

    @Override
    public int getWeights() {
        return 10;
    }
}
