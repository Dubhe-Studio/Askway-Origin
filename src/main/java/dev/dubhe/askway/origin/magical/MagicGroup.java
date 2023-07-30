package dev.dubhe.askway.origin.magical;

import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.effects.IEffect;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.visuals.IVisual;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class MagicGroup {

    private AbstractElement element; // 元素
    private int energy; // 总能量
    private final List<IEffect> effects; // 法术效果列表
    private final List<IVisual> visuals; // 法术视效列表

    private MagicGroup(AbstractElement element, int energy, List<IEffect> effects, List<IVisual> visuals) {
        this.element = element;
        this.energy = energy;
        this.effects = effects;
        this.visuals = visuals;
    }

    public MagicGroup(AbstractElement element, int energy) {
        this(element, energy, new Vector<>(), new Vector<>());
    }

    /**
     添加法术效果
     @param effects 法术效果
     */
    public MagicGroup addEffects(IEffect... effects) {
        this.effects.addAll(Arrays.asList(effects));
        return this;
    }

    /**
     添加法术视效
     @param visuals 法术视效
     */
    public MagicGroup addVisuals(IVisual... visuals) {
        this.visuals.addAll(Arrays.asList(visuals));
        return this;
    }

    /**
     获取法术组元素
     @return 元素
     */
    public AbstractElement getElement() {
        return this.element;
    }

    /**
     获取法术组充能
     @return 充能
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     获取法术组效果
     @return 效果
     */
    public List<IEffect> getEffects() {
        return this.effects;
    }

    /**
     获取法术组视效
     @return 视效
     */
    public List<IVisual> getVisuals() {
        return this.visuals;
    }

    /**
     分割法术组
     @param count 数量
     @return 新法术组
     */
    public MagicGroup split(int count) {
        return new MagicGroup(element, energy / count, effects, visuals);
    }

    /**
     执行法术
     @param caster 执行者
     @param target 目标
     */
    public void execute(ICaster caster, ITarget target) {
        int weights = 0;
        for (IEffect effect : this.effects) weights += effect.getWeights();
        for (IVisual visual : this.visuals) weights += visual.getWeights();
        int energy = weights == 0 ? 0 : this.energy / weights;
        if (target.getLevel() instanceof ServerLevel)
            for (IEffect effect : this.effects) effect.execute(caster, element, energy * effect.getWeights(), target);
        else for (IVisual visual : this.visuals) visual.display(caster, element, energy * visual.getWeights(), target);
    }

    public CompoundTag toNbtTag() {
        CompoundTag tag = new CompoundTag();
        tag.putString("element", AbstractElement.ELEMENT_CUSTOM_REGISTRY.getId(this.element));
        tag.putInt("energy", this.energy);
        ListTag effectsList = new ListTag();
        for (IEffect effect : effects) {
            effectsList.add(StringTag.valueOf(IEffect.EFFECT_CUSTOM_REGISTRY.getId(effect)));
        }
        tag.put("effects", effectsList);
        ListTag visualsList = new ListTag();
        for (IVisual visual : visuals) {
            visualsList.add(StringTag.valueOf(IVisual.VISUAL_CUSTOM_REGISTRY.getId(visual)));
        }
        tag.put("visuals", visualsList);
        return tag;
    }

    public static MagicGroup fromNbtTag(CompoundTag tag) {
        MagicGroup group = new MagicGroup(AbstractElement.FIRE, 0);
        if (tag.contains("element"))
            group.element = AbstractElement.ELEMENT_CUSTOM_REGISTRY.get(tag.getString("element"));
        if (tag.contains("energy")) group.energy = tag.getInt("energy");
        if (tag.contains("effects")) {
            group.effects.clear();
            ListTag list = tag.getList("effects", Tag.TAG_STRING);
            for (Tag tag1 : list) {
                group.effects.add(IEffect.EFFECT_CUSTOM_REGISTRY.get(tag1.getAsString()));
            }
        }
        if (tag.contains("visuals")) {
            group.visuals.clear();
            ListTag list = tag.getList("visuals", Tag.TAG_STRING);
            for (Tag tag1 : list) {
                group.visuals.add(IVisual.VISUAL_CUSTOM_REGISTRY.get(tag1.getAsString()));
            }
        }
        return group;
    }

}
