package dev.dubhe.askway.origin.magical.visuals;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.utils.CustomRegistry;

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
     */
    void display(ICaster caster, AbstractElement element, int energy, ITarget target);

    /**
     * @return 视效法力值权重
     */
    int getWeights();
}
