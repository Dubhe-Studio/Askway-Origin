package dev.dubhe.askway.origin.magical.modes;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TouchMode implements IMode {
    @Override
    public void execute(@NotNull ICaster caster, @NotNull IGoal goal, ITarget direct, MagicGroup @NotNull ... magics) {
        List<ITarget> targetList = goal.getTargets(caster, direct);
        for (MagicGroup magic : magics) {
            magic = magic.addVisuals(goal.getDefaultVisual()).split(targetList.size());
            for (ITarget target : targetList) {
                magic.execute(caster, target);
            }
        }
    }
}
