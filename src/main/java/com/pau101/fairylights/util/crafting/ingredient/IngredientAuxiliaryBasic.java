package com.pau101.fairylights.util.crafting.ingredient;

import java.util.Collections;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.pau101.fairylights.util.crafting.GenericRecipe.MatchResultAuxiliary;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public abstract class IngredientAuxiliaryBasic<A> implements IngredientAuxiliary<A> {
	protected final ItemStack ingredient;

	protected final boolean isRequired;

	protected final int limit;

	public IngredientAuxiliaryBasic(Item item, boolean isRequired, int limit) {
		this(new ItemStack(Objects.requireNonNull(item, "item")), isRequired, limit);
	}

	public IngredientAuxiliaryBasic(Item item, int metadata, boolean isRequired, int limit) {
		this(new ItemStack(Objects.requireNonNull(item, "item"), 1, metadata), isRequired, limit);
	}

	public IngredientAuxiliaryBasic(Block block, boolean isRequired, int limit) {
		this(new ItemStack(Objects.requireNonNull(block, "block"), 1, OreDictionary.WILDCARD_VALUE), isRequired, limit);
	}

	public IngredientAuxiliaryBasic(ItemStack stack, boolean isRequired, int limit) {
		Preconditions.checkArgument(limit > 0, "limit must be greater than zero");
		this.ingredient = Objects.requireNonNull(stack, "stack");
		this.isRequired = isRequired;
		this.limit = limit;
	}

	@Override
	public final MatchResultAuxiliary matches(ItemStack input, ItemStack output) {
		return new MatchResultAuxiliary(this, input, OreDictionary.itemMatches(ingredient, input, false), Collections.EMPTY_LIST);
	}

	@Override
	public ImmutableList<ItemStack> getInputs() {
		return getMatchingSubtypes(ingredient);
	}

	@Override
	public boolean isRequired() {
		return isRequired;
	}

	@Override
	public int getLimit() {
		return limit;
	}
}
