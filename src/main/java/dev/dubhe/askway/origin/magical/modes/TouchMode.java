package dev.dubhe.askway.origin.magical.modes;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.targets.ITargets;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TouchMode implements IMode {
    @Override
    public void execute(@NotNull ICaster caster, @NotNull ITargets targets, ITarget direct, MagicGroup @NotNull ... magics) {
        List<ITarget> targetList = targets.getTargets(caster, direct);
        for (MagicGroup magic : magics) {
            magic = magic.split(targetList.size());
            for (ITarget target : targetList) {
                magic.execute(caster, target);
            }
        }
    }
}
