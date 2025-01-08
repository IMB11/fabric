package net.fabricmc.fabric.api.client.datagen.v1.provider;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with humanoid and humanoid_leggings layers.
	 */
	public FabricEquipmentModelBuilder humanoid(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return create(key).addHumanoidLayers(key.getValue(), dyeable);
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
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "horse" layer.
	 */
	public FabricEquipmentModelBuilder horse(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return create(key).addLayers(EquipmentModel.LayerType.HORSE_BODY, EquipmentModel.Layer.create(key.getValue(), dyeable));
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
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "llama" layer.
	 */
	public FabricEquipmentModelBuilder llama(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return create(key).addLayers(EquipmentModel.LayerType.LLAMA_BODY, EquipmentModel.Layer.create(key.getValue(), dyeable));
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
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "wings" layer.
	 */
	public FabricEquipmentModelBuilder wings(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return create(key).addLayers(EquipmentModel.LayerType.WINGS, EquipmentModel.Layer.create(key.getValue(), dyeable));
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
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the texture supports dyeing.
	 * @return The equipment model with the "wolf" layer.
	 */
	public FabricEquipmentModelBuilder wolf(RegistryKey<EquipmentAsset> key, boolean dyeable) {
		return create(key).addLayers(EquipmentModel.LayerType.WOLF_BODY, EquipmentModel.Layer.create(key.getValue(), dyeable));
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
	 * @param key     The {@link RegistryKey} used to create the equipment model. This should be the same key used to instantiate your {@link net.minecraft.item.equipment.ArmorMaterial}.
	 * @param dyeable Whether the textures support dyeing.
	 * @param layers  The layers to add to the equipment model.
	 * @return The equipment model with the specified layers.
	 */
	public FabricEquipmentModelBuilder multi(RegistryKey<EquipmentAsset> key, boolean dyeable, EquipmentModel.LayerType... layers) {
		FabricEquipmentModelBuilder builder = create(key);
		for (EquipmentModel.LayerType layer : layers) {
			builder.addLayers(layer, EquipmentModel.Layer.create(key.getValue(), false));
		}
		return builder;
	}

	@Override
	public String getName() {
		return "Equipment Model Provider";
	}

	/**
	 * Implement this method to generate your equipment models.
	 * <p>
	 * You can use the various utility methods to quickly create equipment models, or use {@link #create(RegistryKey)} to create a blank equipment model builder.
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
