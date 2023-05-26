package dev.dubhe.askway.origin.magical.visuals;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;

public interface IVisual { // 法术视效
    IVisual STRAIGHT_LINE = new StraightLineVisual();

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
