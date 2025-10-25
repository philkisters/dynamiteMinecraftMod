package me.kisters;

import me.kisters.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class DynamiteModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Register entity renderer for DynamiteEntity
		EntityRendererFactories.register(ModEntities.DYNAMITE_ENTITY_TYPE, FlyingItemEntityRenderer::new);
		EntityRendererFactories.register(ModEntities.STRONG_DYNAMITE_ENTITY_TYPE, FlyingItemEntityRenderer::new);
	}
}