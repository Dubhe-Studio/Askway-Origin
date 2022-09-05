package dev.dubhe.askway.origin.magical.targets;

import dev.dubhe.askway.origin.magical.casters.ICaster;

import java.util.List;

public interface ITargets { // 目标类型
    ITargets EXACT = new ExactTargets();

    /**
     * 获取目标
     *
     * @param caster 施法者
     * @param direct 直接目标
     * @return 目标列表
     */
    List<ITarget> getTargets(ICaster caster, ITarget direct);
}
