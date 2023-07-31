package dev.dubhe.askway.origin.magical.targets;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public interface ITarget { // 目标

    /**
     * 执行法术
     *
     * @param caster 施法者
     * @param magics 法术组
     */
    default void execute(ICaster caster, MagicGroup @NotNull ... magics) {
        for (MagicGroup magic : magics) {
            magic.execute(caster, this);
        }
    }

    /**
     * @return 目标坐标
     */
    Vec3 getPos();

    /**
     * @return 目标所在世界
     */
    Level getLevel();

    /**
     * @return 目标所在区块
     */
    LevelChunk getChunk();
}
