package com.rainbowdestiny.konchu.block;

import java.util.Random;

import com.rainbowdestiny.konchu.main.init.KonchuBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShrineLanternBlock extends HorizontalBlock implements ILitState, IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	public static final BooleanProperty SOUL_LIT = BooleanProperty.create("soul_lit");
	protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);

	public ShrineLanternBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)).setValue(SOUL_LIT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override 
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand handIn, BlockRayTraceResult result) {		
		if (!world.isClientSide) {
			if(!state.getValue(WATERLOGGED)) {
				ItemStack stack = entity.getItemInHand(handIn);
				if(stack != null && stack.getItem() == Items.TORCH) {
					if(!(state.getValue(LIT)) && !(state.getValue(SOUL_LIT))) {
					}					this.getLit(state, pos, world, entity);
					if(!entity.isCreative()) {
						stack.shrink(1);
					}
				}
				if(stack != null && stack.getItem() == Items.SOUL_TORCH) {
					if(!(state.getValue(LIT)) && !(state.getValue(SOUL_LIT))) {
					}
					this.getSoulLit(state, pos, world, entity);
					if(!entity.isCreative()) {
						stack.shrink(1);
					}
				}
				if(stack != null && stack.getItem() == Items.FLINT_AND_STEEL || stack.getItem() == Items.FIRE_CHARGE) {
					if (entity.level.dimension() == World.NETHER) {
						if(!(state.getValue(LIT)) && !(state.getValue(SOUL_LIT))) {
						}
						this.getSoulLit(state, pos, world, entity);
					} else {
						if(!(state.getValue(LIT)) && !(state.getValue(SOUL_LIT))) {
						}
						this.getLit(state, pos, world, entity);
					}
				}
				if(stack.getItem() == Items.AIR) {
					if(state.getValue(LIT) || state.getValue(SOUL_LIT)) {
					}
					this.getUnlit(state, pos, world, entity);
				}
			}
		} 
		return ActionResultType.SUCCESS;
	}

	public void getLit(BlockState state, BlockPos pos, World world, PlayerEntity entity) {
		if(!state.getValue(LIT) && !state.getValue(SOUL_LIT)) {
			world.setBlockAndUpdate(pos, state.setValue(LIT, true));
			world.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundCategory.BLOCKS, 0.3F, 1.0F);
			//entity.displayClientMessage(new StringTextComponent(TextFormatting.GREEN + "Toro has been lit!"), true);
		} else {
			//entity.displayClientMessage(new StringTextComponent(TextFormatting.RED + "Toro cannot be lit twice..."), true);
			world.playSound(null, pos, SoundEvents.BLAZE_BURN, SoundCategory.BLOCKS, 0.3F, 1.0F);
		}
	}

	public void getSoulLit(BlockState state, BlockPos pos, World world, PlayerEntity entity) {
		if(!state.getValue(LIT) && !state.getValue(SOUL_LIT)) {
			world.setBlockAndUpdate(pos, state.setValue(SOUL_LIT, true));
			world.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundCategory.BLOCKS, 0.3F, 1.0F);
		} else {
			world.playSound(null, pos, SoundEvents.SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}

	public void getUnlit(BlockState state, BlockPos pos, World world, PlayerEntity entity) {
		if(state.getValue(LIT) || state.getValue(SOUL_LIT)) {
			world.setBlockAndUpdate(pos, state.setValue(LIT, false).setValue(SOUL_LIT, false));
			world.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 0.3F, 1.0F);
		}
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
		state.add(FACING, LIT, SOUL_LIT, WATERLOGGED);
	}

	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	public BlockState getLitState(World world, BlockState state) {
		return KonchuBlocks.COBBLESTONE_SHRINE_LANTERN.get().defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED));
	}

	public BlockState getSoulLitState(World world, BlockState state) {
		return KonchuBlocks.COBBLESTONE_SHRINE_LANTERN.get().defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(WATERLOGGED, state.getValue(WATERLOGGED));
	}

	public void randomTick(ILitState litState, BlockState state, ServerWorld world, BlockPos pos, Random rand, PlayerEntity entity) {
		if(!world.isClientSide) {
			if(litState.getLitState(world, state) != null) {
				this.getUnlit(state, pos, world, entity);
			}
			if(state.getValue(WATERLOGGED)) {
				this.getUnlit(state, pos, world, entity);
			}
		}
	}
		
	public void onProjectileHit(World world, BlockState state, BlockRayTraceResult result, ProjectileEntity projectileEntity) {
		if (!world.isClientSide && projectileEntity.isOnFire()) {
			Entity entity = projectileEntity.getOwner();
			boolean flag = entity == null || entity instanceof PlayerEntity || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, entity);
			if (flag && !state.getValue(LIT) && !state.getValue(WATERLOGGED)) {
				BlockPos pos = result.getBlockPos();
				this.getLit(state, pos, world, (PlayerEntity) entity);
			}
		}
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getValue(LIT) || state.getValue(SOUL_LIT)) {
			return 12;
		} else {
			return 0;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if (state.getValue(LIT) || state.getValue(SOUL_LIT)) {
			double d0 = (double)pos.getX();
			double d1 = (double)pos.getY();
			double d2 = (double)pos.getZ();
			if (rand.nextDouble() < 0.3D) {
				world.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.6F, 1.0F, false);
			}
		}
	}
}	


