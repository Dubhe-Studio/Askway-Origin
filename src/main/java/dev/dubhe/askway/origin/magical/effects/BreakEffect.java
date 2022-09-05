package dev.dubhe.askway.origin.magical.effects;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.BlockTarget;
import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;

public class BreakEffect implements IEffect {
    @Override
    public void execute(ICaster caster, AbstractElement element, int energy, ITarget target) {
        if (target instanceof EntityTarget entity) {
            entity.getEntity().hurt(caster.getDamageSource(), energy);
        } else if (target instanceof BlockTarget block) {
            float destroySpeed = block.getState().getDestroySpeed(block.level(), block.blockPos());
            AskwayOrigin.LOGGER.info("destroySpeed:%s,energy:%s".formatted(destroySpeed, energy));
            if (destroySpeed > 0 && destroySpeed <= energy) {
                block.level().destroyBlock(block.blockPos(), true);
            }
        }
    }

    @Override
    public int getWeights() {
        return 10;
    }
}
