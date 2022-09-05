package dev.dubhe.askway.origin.magical.targets;

import dev.dubhe.askway.origin.magical.casters.ICaster;

import java.util.List;

public class ExactTargets implements ITargets {
    @Override
    public List<ITarget> getTargets(ICaster caster, ITarget direct) {
        return List.of(direct);
    }
}
