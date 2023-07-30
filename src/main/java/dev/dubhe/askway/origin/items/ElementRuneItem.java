package dev.dubhe.askway.origin.items;

import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElementRuneItem extends RuneItem<AbstractElement> {
    public ElementRuneItem(Properties pProperties, AbstractElement element) {
        super(pProperties, element);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.empty().append(AbstractElement.ELEMENT_CUSTOM_REGISTRY.getRegistryDisplayName()).append(Component.literal(": ")).append(AbstractElement.ELEMENT_CUSTOM_REGISTRY.getRegistryEntryDisplayName(this.getData())));
    }
}
