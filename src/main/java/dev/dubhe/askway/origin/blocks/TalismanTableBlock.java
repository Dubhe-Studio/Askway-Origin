package dev.dubhe.askway.origin.blocks;

import dev.dubhe.askway.origin.blocks.entities.TalismanTableBlockEntity;
import dev.dubhe.askway.origin.blocks.state.properties.AskwayModBlockStateProperties;
import dev.dubhe.askway.origin.blocks.state.properties.TalismanTablePart;
import dev.dubhe.askway.origin.menu.TalismanTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static dev.dubhe.askway.origin.AskwayOrigin.REGISTRATE;

public class TalismanTableBlock extends HorizontalDirectionalBlock implements EntityBlock {
    private static final Component CONTAINER_TITLE = REGISTRATE.addRawLang("container.askway_origin.talisman_table", "Talisman Table");
    public static final EnumProperty<TalismanTablePart> PART = AskwayModBlockStateProperties.TALISMAN_TABLE_PART;
    protected static final VoxelShape MIDDLE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape A = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape B = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape C = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape D = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);

    public TalismanTableBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PART, TalismanTablePart.MIDDLE));
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos) {
        return new SimpleMenuProvider((pContainerId, pPlayerInventory, pPlayer) -> new TalismanTableMenu(pContainerId, pPlayerInventory, ContainerLevelAccess.create(pLevel, pPos)), CONTAINER_TITLE);
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, PART);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos pos1 = blockpos.relative(TalismanTableBlock.rotation(direction, Rotation.CLOCKWISE_90));
        BlockPos pos2 = blockpos.relative(TalismanTableBlock.rotation(direction, Rotation.COUNTERCLOCKWISE_90));
        Level level = pContext.getLevel();
        return TalismanTableBlock.canPlaced(level, pos1, pContext) && TalismanTableBlock.canPlaced(level, pos2, pContext) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    public static boolean canPlaced(Level level, BlockPos pos, BlockPlaceContext pContext) {
        return level.getBlockState(pos).canBeReplaced(pContext) && level.getWorldBorder().isWithinBounds(pos);
    }

    @Nullable
    public static Direction getDirection(BlockGetter pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return blockstate.getBlock() instanceof TalismanTableBlock ? blockstate.getValue(FACING) : null;
    }

    @Nullable
    public static TalismanTablePart getPart(BlockGetter pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return blockstate.getBlock() instanceof TalismanTableBlock ? blockstate.getValue(PART) : null;
    }

    @Override
    @SuppressWarnings("all")
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        Direction direction = getDirection(pLevel, pPos);
        TalismanTablePart part = getPart(pLevel, pPos);
        if (direction == null || part == null) return super.getShape(pState, pLevel, pPos, pContext);
        switch (part) {
            case LEFT -> {
                return switch (direction) {
                    case NORTH -> A;
                    case SOUTH -> B;
                    case WEST -> C;
                    default -> D;
                };
            }
            case RIGHT -> {
                return switch (direction) {
                    case NORTH -> B;
                    case SOUTH -> A;
                    case WEST -> D;
                    default -> C;
                };
            }
            default -> {
                return MIDDLE;
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return new TalismanTableBlockEntity(pPos, pState);
    }

    @Override
    public void setPlacedBy(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable LivingEntity pPlacer, @NotNull ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (pLevel.isClientSide) return;
        BlockPos pos1 = pPos.relative(TalismanTableBlock.rotation(pState.getValue(FACING), Rotation.COUNTERCLOCKWISE_90));
        BlockPos pos2 = pPos.relative(TalismanTableBlock.rotation(pState.getValue(FACING), Rotation.CLOCKWISE_90));
        pLevel.setBlock(pos1, pState.setValue(PART, TalismanTablePart.LEFT), 3);
        pLevel.setBlock(pos2, pState.setValue(PART, TalismanTablePart.RIGHT), 3);
        pLevel.blockUpdated(pPos, Blocks.AIR);
        pState.updateNeighbourShapes(pLevel, pPos, 3);
    }

    public static Direction rotation(Direction direction, Rotation rotation) {
        return switch (direction) {
            case NORTH -> switch (rotation) {
                case CLOCKWISE_90 -> Direction.EAST;
                case CLOCKWISE_180 -> Direction.SOUTH;
                case COUNTERCLOCKWISE_90 -> Direction.WEST;
                default -> Direction.NORTH;
            };
            case EAST -> switch (rotation) {
                case CLOCKWISE_90 -> Direction.SOUTH;
                case CLOCKWISE_180 -> Direction.WEST;
                case COUNTERCLOCKWISE_90 -> Direction.NORTH;
                default -> Direction.EAST;
            };
            case SOUTH -> switch (rotation) {
                case CLOCKWISE_90 -> Direction.WEST;
                case CLOCKWISE_180 -> Direction.NORTH;
                case COUNTERCLOCKWISE_90 -> Direction.EAST;
                default -> Direction.SOUTH;
            };
            case WEST -> switch (rotation) {
                case CLOCKWISE_90 -> Direction.NORTH;
                case CLOCKWISE_180 -> Direction.EAST;
                case COUNTERCLOCKWISE_90 -> Direction.SOUTH;
                default -> Direction.WEST;
            };
            default -> direction;
        };
    }
}
