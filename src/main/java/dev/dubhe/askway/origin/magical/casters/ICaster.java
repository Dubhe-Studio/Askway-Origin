package dev.dubhe.askway.origin.magical.casters;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.modes.IMode;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public interface ICaster { // 施法者

    /**
     * 施放法术
     *
     * @param mode    施放方式
     * @param goal 目标类型
     * @param direct  直接目标
     * @param magics  法术组
     */
    default void execute(@Nonnull IMode mode, IGoal goal, ITarget direct, MagicGroup @NotNull ... magics) {
        mode.execute(this, goal, direct, magics);
    }

    /**
     * @return 施法者坐标
     */
    Vec3 getPos();

    /**
     * @return 施法者伤害源
     */
    DamageSource getDamageSource();

    /**
     * @return 施法者剩余法力
     */
    default int getResidualEnergy() {
        return 0;
    }

    /**
     * 扣除施法者指定单位的法力
     *
     * @param energy 扣除的法力
     */
    default void reduceEnergy(int energy) {

    }
}
