package dev.dubhe.askway.origin.magical.effects;

import dev.dubhe.askway.origin.AskwayOrigin;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.utils.CustomRegistry;

public interface IEffect { // 法术效果
    CustomRegistry<IEffect> EFFECT_CUSTOM_REGISTRY = new CustomRegistry<>(AskwayOrigin.of("magical_effect"));
    IEffect BREAK = EFFECT_CUSTOM_REGISTRY.register("break", new BreakEffect());
    IEffect TELEPORT = EFFECT_CUSTOM_REGISTRY.register("teleport", new TeleportEffect());
    IEffect EXPLOSION = EFFECT_CUSTOM_REGISTRY.register("explosion", new ExplosionEffect());
    IEffect LIGHTING = EFFECT_CUSTOM_REGISTRY.register("lightning", new LightningEffect());
    IEffect TREAT = EFFECT_CUSTOM_REGISTRY.register("treat", new TreatEffect());
    IEffect ASTRAL_VISION = EFFECT_CUSTOM_REGISTRY.register("astral_vision", new AstralVisionEffect());

    /**
     * 展现法术效果
     *
     * @param caster  施法者
     * @param element 元素类型
     * @param energy  法力值
     * @param target  施法目标
     */
    void execute(ICaster caster, AbstractElement element, int energy, ITarget target);

    /**
     * @return 法术权重
     */
    int getWeights();
}
