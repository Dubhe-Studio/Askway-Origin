package dev.dubhe.askway.origin.magical.goals;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.visuals.IVisual;

import javax.annotation.Nullable;
import java.util.List;

public interface IGoal { // 目标类型
    IGoal EXACT = new ExactGoal();

    /**
     * 获取目标
     *
     * @param caster 施法者
     * @param direct 直接目标
     * @return 目标列表
     */
    List<ITarget> getTargets(ICaster caster, ITarget direct);

    /**
     * @return 默认视效
     */
    @Nullable
    default IVisual getDefaultVisual() {
        return null;
    }
}
