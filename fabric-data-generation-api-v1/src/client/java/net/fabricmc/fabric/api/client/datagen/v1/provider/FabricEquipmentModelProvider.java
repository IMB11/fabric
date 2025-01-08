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

package net.fabricmc.fabric.api.client.datagen.v1.provider;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.data.DataOutput;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;

/**
 * Extend this class and implement {@link FabricEquipmentModelProvider#generate}.
 *
 * <p>Register an instance of the class with {@link FabricDataGenerator.Pack#addProvider} in a {@link net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint}.
 */
public abstract class FabricEquipmentModelProvider extends FabricCodecDataProvider<EquipmentModel> {
	protected FabricEquipmentModelProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(dataOutput, registriesFuture, DataOutput.OutputType.RESOURCE_PACK, "equipment", EquipmentModel.CODEC);
	}

	/**
	 * Create a new equipment model builder.
	 *
	 * @param assetKey The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @return The equipment model builder.
	 */
	public static FabricEquipmentModelBuilder create(RegistryKey<EquipmentAsset> assetKey) {
		return new FabricEquipmentModelBuilder(assetKey);
	}

	/**
	 * Create an equipment model with humanoid and humanoid_leggings layers.
	 *
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @return The equipment model with humanoid and humanoid_leggings layers.
	 */
	public FabricEquipmentModelBuilder humanoid(RegistryKey<EquipmentAsset> key) {
		return this.humanoid(key, false);
	}

	/**
	 * Create an equipment model with humanoid and humanoid_leggings layers.
	 *
	 * <p>The default dye color will be leather.
	 *
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with humanoid and humanoid_leggings layers.
	 */
	public FabricEquipmentModelBuilder humanoid(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return this.humanoid(key, dyeable ? new EquipmentModel.Dyeable(Optional.of(-6265536)) : null);
	}

	/**
	 * Create an equipment model with humanoid and humanoid_leggings layers.
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param defaultDyeColor The default dye color to use for the equipment model. This will be used for all layers that support dyeing. If this parameter is <code>null</code>, dye support will not be added.
	 * @return
	 */
	public FabricEquipmentModelBuilder humanoid(RegistryKey<EquipmentAsset> key, @Nullable EquipmentModel.Dyeable defaultDyeColor) {
		return this.multi(key, defaultDyeColor, EquipmentModel.LayerType.HUMANOID, EquipmentModel.LayerType.HUMANOID_LEGGINGS);
	}

	/**
	 * Create an equipment model with a "horse" layer.
	 *
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @return The equipment model with the "horse" layer.
	 */
	public FabricEquipmentModelBuilder horse(RegistryKey<EquipmentAsset> key) {
		return this.horse(key, false);
	}

	/**
	 * Create an equipment model with a "horse" layer.
	 *
	 * <p>The default dye color will be leather.
	 *
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "horse" layer.
	 */
	public FabricEquipmentModelBuilder horse(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return this.horse(key, dyeable ? new EquipmentModel.Dyeable(Optional.of(-6265536)) : null);
	}

	/**
	 * Create an equipment model with a "horse" layer.
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param defaultDyeColor The default dye color to use for the equipment model. This will be used for all layers that support dyeing. If this parameter is <code>null</code>, dye support will not be added.
	 * @return
	 */
	public FabricEquipmentModelBuilder horse(RegistryKey<EquipmentAsset> key, @Nullable EquipmentModel.Dyeable defaultDyeColor) {
		return multi(key, defaultDyeColor, EquipmentModel.LayerType.HORSE_BODY);
	}

	/**
	 * Create an equipment model with a "llama" layer.
	 *
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @return The equipment model with the "llama" layer.
	 */
	public FabricEquipmentModelBuilder llama(RegistryKey<EquipmentAsset> key) {
		return this.llama(key, false);
	}

	/**
	 * Create an equipment model with a "llama" layer.
	 *
	 * <p>The default dye color will be leather.
	 *
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "llama" layer.
	 */
	public FabricEquipmentModelBuilder llama(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return this.llama(key, dyeable ? new EquipmentModel.Dyeable(Optional.of(-6265536)) : null);
	}

	/**
	 * Create an equipment model with a "llama" layer.
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param defaultDyeColor The default dye color to use for the equipment model. This will be used for all layers that support dyeing. If this parameter is <code>null</code>, dye support will not be added.
	 * @return The equipment model with the "llama" layer.
	 */
	public FabricEquipmentModelBuilder llama(RegistryKey<EquipmentAsset> key, @Nullable EquipmentModel.Dyeable defaultDyeColor) {
		return multi(key, defaultDyeColor, EquipmentModel.LayerType.LLAMA_BODY);
	}

	/**
	 * Create an equipment model with a "wings" layer.
	 *
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @return The equipment model with the "wings" layer.
	 */
	public FabricEquipmentModelBuilder wings(RegistryKey<EquipmentAsset> key) {
		return this.wings(key, false);
	}

	/**
	 * Create an equipment model with a "wings" layer.
	 *
	 * <p>The default dye color will be leather.
	 *
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "wings" layer.
	 */
	public FabricEquipmentModelBuilder wings(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return this.wings(key, dyeable ? new EquipmentModel.Dyeable(Optional.of(-6265536)) : null);
	}

	/**
	 * Create an equipment model with a "wings" layer.
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param defaultDyeColor The default dye color to use for the equipment model. This will be used for all layers that support dyeing. If this parameter is <code>null</code>, dye support will not be added.
	 * @return The equipment model with the "wings" layer.
	 */
	public FabricEquipmentModelBuilder wings(RegistryKey<EquipmentAsset> key, @Nullable EquipmentModel.Dyeable defaultDyeColor) {
		return multi(key, defaultDyeColor, EquipmentModel.LayerType.WINGS);
	}

	/**
	 * Create an equipment model with a "wolf" layer.
	 *
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @return The equipment model with the "wolf" layer.
	 */
	public FabricEquipmentModelBuilder wolf(RegistryKey<EquipmentAsset> key) {
		return this.wolf(key, false);
	}

	/**
	 * Create an equipment model with a "wolf" layer.
	 *
	 * <p>The default dye color will be leather.
	 *
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "wolf" layer.
	 */
	public FabricEquipmentModelBuilder wolf(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return this.wolf(key, dyeable ? new EquipmentModel.Dyeable(Optional.of(-6265536)) : null);
	}

	public FabricEquipmentModelBuilder wolf(RegistryKey<EquipmentAsset> key, @Nullable EquipmentModel.Dyeable defaultDyeColor) {
		return multi(key, defaultDyeColor, EquipmentModel.LayerType.WOLF_BODY);
	}

	/**
	 * Create an equipment model with multiple layers.
	 *
	 * @param key    The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param layers The layers to add to the equipment model.
	 * @return The equipment model with the specified layers.
	 */
	public FabricEquipmentModelBuilder multi(RegistryKey<EquipmentAsset> key, EquipmentModel.LayerType... layers) {
		return this.multi(key, false, layers);
	}

	/**
	 * Create an equipment model with multiple layers.
	 *
	 * <p>The default dye color will be leather.
	 *
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the textures support dyeing.
	 * @param layers  The layers to add to the equipment model.
	 * @return The equipment model with the specified layers.
	 */
	public FabricEquipmentModelBuilder multi(RegistryKey<EquipmentAsset> key, boolean dyeable, EquipmentModel.LayerType... layers) {
		return this.multi(key, dyeable ? new EquipmentModel.Dyeable(Optional.of(-6265536)) : null, layers);
	}

	/**
	 * Create an equipment model with multiple layers.
	 * @param key The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param defaultDyeColor The default dye color to use for the equipment model. This will be used for all layers that support dyeing. If this parameter is <code>null</code>, dye support will not be added.
	 * @param layers The layers to add to the equipment model.
	 * @return The equipment model with the specified layers.
	 */
	public FabricEquipmentModelBuilder multi(RegistryKey<EquipmentAsset> key, @Nullable EquipmentModel.Dyeable defaultDyeColor, EquipmentModel.LayerType... layers) {
		FabricEquipmentModelBuilder builder = create(key);

		for (EquipmentModel.LayerType layer : layers) {
			builder.addLayers(layer, new EquipmentModel.Layer(key.getValue(), Optional.ofNullable(defaultDyeColor), false));
		}

		return builder;
	}

	@Override
	public String getName() {
		return "Equipment Model Provider";
	}

	/**
	 * Implement this method to generate your equipment models.
	 *
	 * <p>You can use the various utility methods to quickly create equipment models, or use {@link #create(RegistryKey)} to create a blank equipment model builder.
	 *
	 * @param modelConsumer The consumer to use to add your equipment models.
	 * @param lookup        The {@link RegistryWrapper.WrapperLookup} to use to look up registries.
	 */
	public abstract void generate(Consumer<FabricEquipmentModelBuilder> modelConsumer, RegistryWrapper.WrapperLookup lookup);

	@Override
	protected void configure(BiConsumer<Identifier, EquipmentModel> provider, RegistryWrapper.WrapperLookup lookup) {
		ArrayList<FabricEquipmentModelBuilder> models = new ArrayList<>();
		this.generate(models::add, lookup);

		for (FabricEquipmentModelBuilder model : models) {
			provider.accept(model.getEquipmentAssetKey().getValue(), model.build());
		}
	}

	/**
	 * An extended {@link FabricEquipmentModelBuilder} that allows you to get the {@link RegistryKey} used to create the equipment model.
	 */
	public static class FabricEquipmentModelBuilder extends EquipmentModel.Builder {
		private final RegistryKey<EquipmentAsset> assetKey;

		/**
		 * Create a new equipment model builder.
		 *
		 * @param assetKey The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
		 */
		public FabricEquipmentModelBuilder(RegistryKey<EquipmentAsset> assetKey) {
			super();
			this.assetKey = assetKey;
		}

		/**
		 * Get the {@link RegistryKey} used to create the equipment model.
		 *
		 * @return The {@link RegistryKey} used to create the equipment model.
		 */
		public RegistryKey<EquipmentAsset> getEquipmentAssetKey() {
			return assetKey;
		}

		/**
		 * Add humanoid layers (humanoid, humanoid_leggings) to the equipment model.
		 *
		 * @param textureId The texture id to use for the humanoid layers.
		 * @return The equipment model builder.
		 */
		@Override
		public FabricEquipmentModelBuilder addHumanoidLayers(Identifier textureId) {
			return (FabricEquipmentModelBuilder) super.addHumanoidLayers(textureId);
		}

		/**
		 * Add humanoid layers (humanoid, humanoid_leggings) to the equipment model.
		 *
		 * @param textureId The texture id to use for the humanoid layers.
		 * @param dyeable   Whether the texture supports dyeing.
		 * @return The equipment model builder.
		 */
		@Override
		public FabricEquipmentModelBuilder addHumanoidLayers(Identifier textureId, boolean dyeable) {
			return (FabricEquipmentModelBuilder) super.addHumanoidLayers(textureId, dyeable);
		}

		/**
		 * Add only the "humanoid" to the equipment model.
		 *
		 * @param textureId The texture id to use for the humanoid layer.
		 * @param dyeable   Whether the texture supports dyeing.
		 * @return The equipment model builder.
		 */
		@Override
		public FabricEquipmentModelBuilder addMainHumanoidLayer(Identifier textureId, boolean dyeable) {
			return (FabricEquipmentModelBuilder) super.addMainHumanoidLayer(textureId, dyeable);
		}

		/**
		 * Add a layer to the equipment model.
		 *
		 * @param layerType The type of layer to add.
		 * @param layers    The layers to add.
		 * @return The equipment model builder.
		 */
		@Override
		public FabricEquipmentModelBuilder addLayers(EquipmentModel.LayerType layerType, EquipmentModel.Layer... layers) {
			return (FabricEquipmentModelBuilder) super.addLayers(layerType, layers);
		}
	}
}
