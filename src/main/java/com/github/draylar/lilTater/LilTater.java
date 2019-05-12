package com.github.draylar.lilTater;

import com.github.draylar.lilTater.blocks.CompressedTaterTotBlock;
import com.github.draylar.lilTater.blocks.IrritatedLilTaterBlock;
import com.github.draylar.lilTater.blocks.LilTaterBlock;
import com.github.draylar.lilTater.blocks.LilTaterTotBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class LilTater implements ModInitializer
{
	// lil tater the OG
	public static final Block LIL_TATER_BLOCK = new LilTaterBlock(FabricBlockSettings.of(Material.TNT).hardness(0.3f).build());
	public static final BlockItem LIL_TATER_ITEM = new BlockItem(LIL_TATER_BLOCK, new Item.Settings().itemGroup(ItemGroup.MISC));

	// lil tater tot
	public static final LilTaterTotBlock TATER_TOT_BLOCK = new LilTaterTotBlock(FabricBlockSettings.of(Material.TNT).hardness(0.5f).build());
	public static final Item TATER_TOT_ITEM = new Item(new Item.Settings().food(new FoodItemSetting.Builder().hunger(6).build()).itemGroup(ItemGroup.MISC))
	{
		@Override
		public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1)
		{
			// place & consume if player is sneaking
			if(itemUsageContext_1.getPlayer().isSneaking())
			{
				// set block & remove item
				itemUsageContext_1.getWorld().setBlockState(itemUsageContext_1.getBlockPos().up(), TATER_TOT_BLOCK.getDefaultState().with(LilTaterTotBlock.FACING, itemUsageContext_1.getPlayerHorizontalFacing().getOpposite()));

				ItemStack stack = itemUsageContext_1.getPlayer().inventory.main.get(itemUsageContext_1.getPlayer().inventory.selectedSlot);
				stack.setAmount(stack.getAmount() - 1);

				itemUsageContext_1.getPlayer().inventory.setInvStack(itemUsageContext_1.getPlayer().inventory.selectedSlot, stack);
			}

			return super.useOnBlock(itemUsageContext_1);
		}
	};

	// compressed tater tot
	public static final CompressedTaterTotBlock COMPRESSED_TOT_BLOCK = new CompressedTaterTotBlock(FabricBlockSettings.of(Material.TNT).hardness(1f).build());
	public static final Item COMPRESSED_TOT_ITEM = new Item(new Item.Settings().food(new FoodItemSetting.Builder().hunger(90).build()).itemGroup(ItemGroup.MISC))
	{
		@Override
		public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1)
		{
			// place & consume if player is sneaking
			if(itemUsageContext_1.getPlayer().isSneaking())
			{
				// set block & remove item
				itemUsageContext_1.getWorld().setBlockState(itemUsageContext_1.getBlockPos().up(), COMPRESSED_TOT_BLOCK.getDefaultState().with(CompressedTaterTotBlock.FACING, itemUsageContext_1.getPlayerHorizontalFacing().getOpposite()));

				ItemStack stack = itemUsageContext_1.getPlayer().inventory.main.get(itemUsageContext_1.getPlayer().inventory.selectedSlot);
				stack.setAmount(stack.getAmount() - 1);

				itemUsageContext_1.getPlayer().inventory.setInvStack(itemUsageContext_1.getPlayer().inventory.selectedSlot, stack);
			}

			return super.useOnBlock(itemUsageContext_1);
		}
	};

	// irritated lil tater
	public static final IrritatedLilTaterBlock IRRITATED_TATER_BLOCK = new IrritatedLilTaterBlock(FabricBlockSettings.of(Material.TNT).hardness(1f).build());
	public static final Item IRRITATED_TATER_ITEM = new Item(new Item.Settings().food(new FoodItemSetting.Builder().hunger(4).build()).itemGroup(ItemGroup.MISC))
	{
		@Override
		public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1)
		{
			// place & consume if player is sneaking
			if(itemUsageContext_1.getPlayer().isSneaking())
			{
				// set block & remove item
				itemUsageContext_1.getWorld().setBlockState(itemUsageContext_1.getBlockPos().up(), IRRITATED_TATER_BLOCK.getDefaultState().with(IrritatedLilTaterBlock.FACING, itemUsageContext_1.getPlayerHorizontalFacing().getOpposite()));

				ItemStack stack = itemUsageContext_1.getPlayer().inventory.main.get(itemUsageContext_1.getPlayer().inventory.selectedSlot);
				stack.setAmount(stack.getAmount() - 1);

				itemUsageContext_1.getPlayer().inventory.setInvStack(itemUsageContext_1.getPlayer().inventory.selectedSlot, stack);
			}

			return super.useOnBlock(itemUsageContext_1);
		}

		@Override
		public ItemStack onItemFinishedUsing(ItemStack itemStack_1, World world_1, LivingEntity livingEntity_1)
		{
			livingEntity_1.addPotionEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5 * 20, 1));
			return super.onItemFinishedUsing(itemStack_1, world_1, livingEntity_1);
		}
	};

	@Override
	public void onInitialize()
	{
		Registry.register(Registry.BLOCK, "lil-tater:lil_tater", LIL_TATER_BLOCK);
		Registry.register(Registry.ITEM, "lil-tater:lil_tater", LIL_TATER_ITEM);

		Registry.register(Registry.ITEM, "lil-tater:lil_tater_tot", TATER_TOT_ITEM);
		Registry.register(Registry.BLOCK, "lil-tater:lil_tater_tot", TATER_TOT_BLOCK);

		Registry.register(Registry.ITEM, "lil-tater:compressed_tater_tot", COMPRESSED_TOT_ITEM);
		Registry.register(Registry.BLOCK, "lil-tater:compressed_tater_tot", COMPRESSED_TOT_BLOCK);

		Registry.register(Registry.ITEM, "lil-tater:irritated_tater", IRRITATED_TATER_ITEM);
		Registry.register(Registry.BLOCK, "lil-tater:irritated_tater", IRRITATED_TATER_BLOCK);
	}
}
