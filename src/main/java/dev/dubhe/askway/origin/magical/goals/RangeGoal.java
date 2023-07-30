package dev.dubhe.askway.origin.magical.goals;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.targets.BlockTarget;
import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.visuals.IVisual;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RangeGoal implements IGoal {
    @Override
    public List<ITarget> getTargets(ICaster caster, ITarget direct) {
        List<ITarget> targetList = new ArrayList<>();
        if (direct instanceof EntityTarget target) {
            List<Entity> entities = target.getLevel().getEntities(new EntityTypeTest<>() {
                public Entity tryCast(@NotNull Entity entity) {
                    return entity;
                }

                public @NotNull Class<? extends Entity> getBaseClass() {
                    return Entity.class;
                }
            }, new AABB(-10, -10, -10, 10, 10, 10), Entity::isAlive);
            for (Entity entity : entities) {
                if (targetList.size() >= 10) break;
                targetList.add(new EntityTarget(entity));
            }
        } else if (direct instanceof BlockTarget target) {
            BlockPos pos = target.getBlockPos().offset(-1, -1, -1);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        BlockPos pos1 = pos.offset(i, j, k);
                        if (target.getLevel().getBlockState(pos1).isAir()) continue;
                        targetList.add(new BlockTarget(target.getLevel(), pos1));
                    }
                }
            }

        }
        return targetList;
    }

    @Override
    public IVisual getDefaultVisual() {
        return IVisual.STRAIGHT_LINE;
    }
}
