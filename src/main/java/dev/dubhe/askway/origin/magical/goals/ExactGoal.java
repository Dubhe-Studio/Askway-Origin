package dev.dubhe.askway.origin.magical.goals;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.visuals.IVisual;

import java.util.List;

public class ExactGoal implements IGoal {
    @Override
    public List<ITarget> getTargets(ICaster caster, ITarget direct) {
        return List.of(direct);
    }

    @Override
    public IVisual getDefaultVisual() {
        return IVisual.STRAIGHT_LINE;
    }
}
