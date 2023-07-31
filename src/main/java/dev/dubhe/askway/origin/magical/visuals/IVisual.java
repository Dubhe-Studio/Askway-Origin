package dev.dubhe.askway.origin.magical.visuals;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.network.MagicalVisualNetworkImpl;
import dev.dubhe.askway.origin.utils.CustomRegistry;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

public interface IVisual { // 法术视效
    CustomRegistry<IVisual> VISUAL_CUSTOM_REGISTRY = new CustomRegistry<>(AskwayOrigin.of("visual"));
    IVisual STRAIGHT_LINE = VISUAL_CUSTOM_REGISTRY.register("straight_line", new StraightLineVisual());

    /**
     * 展示法术视效
     *
     * @param caster  施法者
     * @param element 元素
     * @param energy  法力值
     * @param target  目标
     * @return 视效网络包
     */
    default MagicalVisualNetworkImpl.MagicalVisualPack display(ICaster caster, AbstractElement element, int energy, ITarget target) {
        return MagicalVisualNetworkImpl.MagicalVisualPack.create(this, caster, element, energy, target);
    }

    /**
     * 展示法术视效
     *
     * @param casterPos 施法者位置
     * @param targetPos 目标位置
     * @param element   元素
     * @param energy    法力值
     */
    @OnlyIn(Dist.CLIENT)
    void display(Vec3 casterPos, Vec3 targetPos, AbstractElement element, int energy);

    /**
     * @return 视效法力值权重
     */
    int getWeights();
}
