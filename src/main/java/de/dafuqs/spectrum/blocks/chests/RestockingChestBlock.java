package de.dafuqs.spectrum.blocks.chests;

import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.screen.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class RestockingChestBlock extends SpectrumChestBlock {
	
	protected static final VoxelShape CHEST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
	
	public RestockingChestBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	@Nullable
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new RestockingChestBlockEntity(pos, state);
	}
	
	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, SpectrumBlockEntities.RESTOCKING_CHEST, RestockingChestBlockEntity::tick);
	}
	
	@Override
	public void openScreen(World world, BlockPos pos, PlayerEntity player) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof RestockingChestBlockEntity) {
			if (!isChestBlocked(world, pos)) {
				player.openHandledScreen((NamedScreenHandlerFactory) blockEntity);
			}
		}
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return CHEST_SHAPE;
	}
	
}
