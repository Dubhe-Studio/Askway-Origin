package dev.dubhe.askway.origin.magical.modes;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.targets.ITargets;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public interface IMode { // 施放方式
    IMode TOUCH = new TouchMode();

    /**
     * 执行法术
     *
     * @param caster  施法者
     * @param targets 目标类型
     * @param direct  直接目标
     * @param magics  法术组
     */
    void execute(@Nonnull ICaster caster, ITargets targets, ITarget direct, MagicGroup @NotNull ... magics);
}
