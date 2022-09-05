package dev.dubhe.askway.origin.magical.goals;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.targets.ITarget;

import java.util.List;

public class ExactGoal implements IGoal {
    @Override
    public List<ITarget> getTargets(ICaster caster, ITarget direct) {
        return List.of(direct);
    }
}
