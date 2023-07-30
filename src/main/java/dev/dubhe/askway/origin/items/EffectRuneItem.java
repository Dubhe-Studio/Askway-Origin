package dev.dubhe.askway.origin.items;

import dev.dubhe.askway.origin.magical.effects.IEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EffectRuneItem extends RuneItem<IEffect> {
    public EffectRuneItem(Properties pProperties, IEffect effect) {
        super(pProperties, effect);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.empty().append(IEffect.EFFECT_CUSTOM_REGISTRY.getRegistryDisplayName()).append(Component.literal(": ")).append(IEffect.EFFECT_CUSTOM_REGISTRY.getRegistryEntryDisplayName(this.getData())));
    }
}
