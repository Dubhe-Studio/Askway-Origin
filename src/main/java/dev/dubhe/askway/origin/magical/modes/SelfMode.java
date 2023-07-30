package dev.dubhe.askway.origin.magical.modes;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import org.jetbrains.annotations.NotNull;

public class SelfMode implements IMode {
    @Override
    public void execute(@NotNull ICaster caster, IGoal goal, ITarget direct, MagicGroup @NotNull ... magics) {
        for (MagicGroup magic : magics) {
            magic = magic.addVisuals(goal.getDefaultVisual());
            magic.execute(caster, caster.getTarget());
        }
    }
}
