package dev.dubhe.askway.origin.mixins;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BlockItem.class)
public class BlockItemMixin {
    @Inject(method = "appendHoverText", at = @At("RETURN"))
    private void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag, CallbackInfo ci) {
        if (stack.getItem() instanceof BlockItem item) {
            float time = item.getBlock().defaultDestroyTime();
            components.add(Component.literal("硬度：%s".formatted(time)).withStyle(ChatFormatting.RED).withStyle(Style.EMPTY.withItalic(false)));
        }
    }
}
