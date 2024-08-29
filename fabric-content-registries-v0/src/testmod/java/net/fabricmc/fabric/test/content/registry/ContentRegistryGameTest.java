/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.test.content.registry;

import java.util.function.Consumer;

import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;

public class ContentRegistryGameTest {
	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
	public void testCompostingChanceRegistry(TestContext context) {
		BlockPos pos = new BlockPos(0, 1, 0);
		context.setBlockState(pos, Blocks.COMPOSTER);
		ItemStack obsidian = new ItemStack(Items.OBSIDIAN, 64);
		PlayerEntity player = context.createMockPlayer(GameMode.SURVIVAL);
		player.setStackInHand(Hand.MAIN_HAND, obsidian);
		// If on level 0, composting always increases composter level
		context.useBlock(pos, player);
		context.expectBlockProperty(pos, ComposterBlock.LEVEL, 1);
		context.assertEquals(obsidian.getCount(), 63, "obsidian stack count");
		context.complete();
	}

	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
	public void testFlattenableBlockRegistry(TestContext context) {
		BlockPos pos = new BlockPos(0, 1, 0);
		context.setBlockState(pos, Blocks.RED_WOOL);
		ItemStack shovel = new ItemStack(Items.NETHERITE_SHOVEL);
		PlayerEntity player = context.createMockPlayer(GameMode.SURVIVAL);
		player.setStackInHand(Hand.MAIN_HAND, shovel);
		context.useBlock(pos, player);
		context.expectBlock(Blocks.YELLOW_WOOL, pos);
		context.assertEquals(shovel.getDamage(), 1, "shovel damage");
		context.complete();
	}

	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
	public void testStrippableBlockRegistry(TestContext context) {
		BlockPos pos = new BlockPos(0, 1, 0);
		context.setBlockState(pos, Blocks.QUARTZ_PILLAR);
		ItemStack axe = new ItemStack(Items.NETHERITE_AXE);
		PlayerEntity player = context.createMockPlayer(GameMode.SURVIVAL);
		player.setStackInHand(Hand.MAIN_HAND, axe);
		context.useBlock(pos, player);
		context.expectBlock(Blocks.HAY_BLOCK, pos);
		context.assertEquals(axe.getDamage(), 1, "axe damage");
		context.complete();
	}

	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
	public void testTillableBlockRegistry(TestContext context) {
		BlockPos pos = new BlockPos(0, 1, 0);
		context.setBlockState(pos, Blocks.GREEN_WOOL);
		ItemStack hoe = new ItemStack(Items.NETHERITE_HOE);
		PlayerEntity player = context.createMockPlayer(GameMode.SURVIVAL);
		player.setStackInHand(Hand.MAIN_HAND, hoe);
		context.useBlock(pos, player);
		context.expectBlock(Blocks.LIME_WOOL, pos);
		context.assertEquals(hoe.getDamage(), 1, "hoe damage");
		context.complete();
	}

	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
	public void testOxidizableBlocksRegistry(TestContext context) {
		// Test de-oxidation. (the registry does not make the blocks oxidize.)
		PlayerEntity player = context.createMockPlayer(GameMode.SURVIVAL);
		BlockPos pos = new BlockPos(0, 1, 0);
		context.setBlockState(pos, Blocks.DIAMOND_ORE);
		ItemStack axe = new ItemStack(Items.NETHERITE_AXE);
		player.setStackInHand(Hand.MAIN_HAND, axe);
		context.useBlock(pos, player);
		context.expectBlock(Blocks.GOLD_ORE, pos);
		context.assertEquals(axe.getDamage(), 1, "axe damage");
		context.useBlock(pos, player);
		context.expectBlock(Blocks.IRON_ORE, pos);
		context.useBlock(pos, player);
		context.expectBlock(Blocks.COPPER_ORE, pos);
		context.complete();
	}

	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE)
	public void testWaxableBlocksRegistry(TestContext context) {
		PlayerEntity player = context.createMockPlayer(GameMode.SURVIVAL);
		BlockPos pos = new BlockPos(0, 1, 0);
		context.setBlockState(pos, Blocks.DIAMOND_ORE);
		ItemStack honeycomb = new ItemStack(Items.HONEYCOMB, 64);
		player.setStackInHand(Hand.MAIN_HAND, honeycomb);
		context.useBlock(pos, player);
		context.expectBlock(Blocks.DEEPSLATE_DIAMOND_ORE, pos);
		context.assertEquals(honeycomb.getCount(), 63, "honeycomb count");
		ItemStack axe = new ItemStack(Items.NETHERITE_AXE);
		player.setStackInHand(Hand.MAIN_HAND, axe);
		context.useBlock(pos, player);
		context.expectBlock(Blocks.DIAMOND_ORE, pos);
		context.assertEquals(axe.getDamage(), 1, "axe damage");
		context.complete();
	}

	private void brew(TestContext context, ItemStack input, ItemStack bottle, Consumer<BrewingStandBlockEntity> callback) {
		BlockPos pos = new BlockPos(0, 1, 0);
		context.setBlockState(pos, Blocks.BREWING_STAND);

		if (!(context.getBlockEntity(pos) instanceof BrewingStandBlockEntity brewingStand)) {
			throw new AssertionError("Brewing stand was not placed");
		}

		brewingStand.setStack(0, bottle);
		brewingStand.setStack(3, input);
		brewingStand.setStack(4, new ItemStack(Items.BLAZE_POWDER, 64));
		context.waitAndRun(401, () -> callback.accept(brewingStand));
	}

	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE, tickLimit = 410)
	public void testBrewingFlower(TestContext context) {
		brew(context, new ItemStack(Items.DANDELION), PotionContentsComponent.createStack(Items.POTION, Potions.AWKWARD), brewingStand -> {
			ItemStack bottle = brewingStand.getStack(0);
			PotionContentsComponent potion = bottle.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT);
			context.assertEquals(potion.potion().orElseThrow(), Potions.HEALING, "brewed potion");
			context.complete();
		});
	}

	@GameTest(templateName = FabricGameTest.EMPTY_STRUCTURE, tickLimit = 410)
	public void testBrewingDirt(TestContext context) {
		brew(context, new ItemStack(Items.DIRT), PotionContentsComponent.createStack(Items.POTION, Potions.AWKWARD), brewingStand -> {
			ItemStack bottle = brewingStand.getStack(0);
			context.assertTrue(bottle.getItem() instanceof ContentRegistryTest.DirtyPotionItem, "potion became dirty");
			context.complete();
		});
	}
}