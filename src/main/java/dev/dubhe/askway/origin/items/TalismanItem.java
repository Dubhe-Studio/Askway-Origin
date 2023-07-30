package dev.dubhe.askway.origin.items;

import dev.dubhe.askway.origin.init.AskwayModItems;
import dev.dubhe.askway.origin.magical.MagicGroup;
import dev.dubhe.askway.origin.magical.casters.ICaster;
import dev.dubhe.askway.origin.magical.casters.LivingEntityCaster;
import dev.dubhe.askway.origin.magical.goals.IGoal;
import dev.dubhe.askway.origin.magical.modes.IMode;
import dev.dubhe.askway.origin.magical.targets.BlockTarget;
import dev.dubhe.askway.origin.magical.targets.EntityTarget;
import dev.dubhe.askway.origin.magical.targets.ITarget;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
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

public class TalismanItem extends Item {
    public static final String TAG_MAGIC_GROUP = "magic";
    public static final String TAG_MODE = "mode";
    public static final String TAG_GOAL = "goal";

    public TalismanItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        ICaster caster = new LivingEntityCaster(pPlayer);
        TalismanItem.executeMagic(caster, null, stack);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        ICaster caster = new LivingEntityCaster(context.getPlayer());
        ITarget target = new BlockTarget(context.getLevel(), context.getClickedPos());
        TalismanItem.executeMagic(caster, target, context.getItemInHand());
        return InteractionResult.CONSUME;
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity entity, @NotNull InteractionHand hand) {
        ICaster caster = new LivingEntityCaster(player);
        ITarget target = new EntityTarget(entity);
        TalismanItem.executeMagic(caster, target, stack);
        return InteractionResult.CONSUME;
    }

    public static void executeMagic(ICaster caster, ITarget target, ItemStack stack) {
        MagicGroup magic = TalismanItem.getMagicGroup(stack);
        IMode mode = TalismanItem.getMode(stack);
        IGoal goal = TalismanItem.getGoal(stack);
        if (mode != null && goal != null) caster.execute(mode, goal, target, magic);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
    }

    public static MagicGroup getMagicGroup(@NotNull ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (null != tag && tag.contains(TAG_MAGIC_GROUP))
            return MagicGroup.fromNbtTag(stack.getTag().getCompound(TAG_MAGIC_GROUP));
        return null;
    }

    public static IMode getMode(@NotNull ItemStack stack) {
        CompoundTag tag = stack.getTag();
        String id;
        if (null != tag && tag.contains(TAG_MODE)) id = stack.getTag().getString(TAG_MODE);
        else return null;
        return IMode.MODE_CUSTOM_REGISTRY.get(id);
    }

    public static IGoal getGoal(@NotNull ItemStack stack) {
        CompoundTag tag = stack.getTag();
        String id;
        if (null != tag && tag.contains(TAG_GOAL)) id = stack.getTag().getString(TAG_GOAL);
        else return null;
        return IGoal.GOAL_CUSTOM_REGISTRY.get(id);
    }

    public static ItemStack create(MagicGroup group, IMode mode, IGoal goal) {
        ItemStack stack = new ItemStack(AskwayModItems.TALISMAN.get());
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString(TAG_MODE, IMode.MODE_CUSTOM_REGISTRY.getId(mode));
        tag.putString(TAG_GOAL, IGoal.GOAL_CUSTOM_REGISTRY.getId(goal));
        tag.put(TAG_MAGIC_GROUP, group.toNbtTag());
        return stack;
    }
}
