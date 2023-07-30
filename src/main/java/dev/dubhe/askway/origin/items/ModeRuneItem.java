package dev.dubhe.askway.origin.items;

import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.modes.IMode;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModeRuneItem extends RuneItem<IMode> {
    public ModeRuneItem(Properties pProperties, IMode mode) {
        super(pProperties, mode);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.empty().append(IMode.MODE_CUSTOM_REGISTRY.getRegistryDisplayName()).append(Component.literal(": ")).append(IMode.MODE_CUSTOM_REGISTRY.getRegistryEntryDisplayName(this.getData())));
    }
}
