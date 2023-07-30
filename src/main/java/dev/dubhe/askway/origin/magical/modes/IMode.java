package dev.dubhe.askway.origin.magical.modes;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.utils.CustomRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public interface IMode { // 施放方式
    CustomRegistry<IMode> MODE_CUSTOM_REGISTRY = new CustomRegistry<>(AskwayOrigin.of("mode"));
    IMode TOUCH = MODE_CUSTOM_REGISTRY.register("touch", new TouchMode());
    IMode SELF = MODE_CUSTOM_REGISTRY.register("self", new SelfMode());
    IMode THROW = MODE_CUSTOM_REGISTRY.register("throw", new ThrowMode());
    IMode SHOOT = MODE_CUSTOM_REGISTRY.register("shoot", new ShootMode());

    /**
     * 执行法术
     *
     * @param caster 施法者
     * @param goal   目标类型
     * @param direct 直接目标
     * @param magics 法术组
     */
    void execute(@Nonnull ICaster caster, IGoal goal, ITarget direct, MagicGroup @NotNull ... magics);
}
