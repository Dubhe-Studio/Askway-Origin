package dev.dubhe.askway.origin.items;

import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.casters.LivingEntityCaster;
import dev.dubhe.askway.origin.magical.effects.IEffect;
import dev.dubhe.askway.origin.magical.elements.AbstractElement;
import dev.dubhe.askway.origin.magical.modes.IMode;
import dev.dubhe.askway.origin.magical.targets.BlockTarget;
import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class MagicTestItem extends Item {
    public MagicTestItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        int energy = MagicTestItem.getEnergy(context.getItemInHand());
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ICaster caster = new LivingEntityCaster(Objects.requireNonNull(context.getPlayer()));
        ITarget target = new BlockTarget(level, pos);
        MagicGroup fire = new MagicGroup(AbstractElement.FIRE, energy).addEffects(IEffect.BREAK);
        caster.execute(IMode.TOUCH, IGoal.EXACT, target, fire);
        return InteractionResult.CONSUME;
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity entity, @NotNull InteractionHand hand) {
        int energy = MagicTestItem.getEnergy(stack);
        ICaster caster = new LivingEntityCaster(player);
        ITarget target = new EntityTarget(entity);
        MagicGroup fire = new MagicGroup(AbstractElement.FIRE, energy).addEffects(IEffect.BREAK);
        caster.execute(IMode.TOUCH, IGoal.EXACT, target, fire);
        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
        int energy = MagicTestItem.getEnergy(stack);
        components.add(Component.literal("Energy: %s".formatted(energy)).withStyle(ChatFormatting.GREEN).withStyle(Style.EMPTY.withItalic(false)));
    }

    public static int getEnergy(@NotNull ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (null != tag) return tag.getInt("energy");
        return 0;
    }
}
