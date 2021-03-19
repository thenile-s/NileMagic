package net.nile.magic.client.entities.renderers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.nile.magic.client.entities.models.NonLivingEntityModels;
import net.nile.magic.entities.Entities;

@Environment(EnvType.CLIENT)
public class EntityRenderers implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        
        EntityRendererRegistry.INSTANCE.register(Entities.TORNADO, (dispatcher, context) -> new TornadoEntityRenderer(dispatcher, NonLivingEntityModels.TORNADO));

        EntityRendererRegistry.INSTANCE.register(Entities.FIRE_BLAST, (dispatcher, context) -> new FireBlastEntityRenderer(dispatcher, NonLivingEntityModels.FIRE_BLAST));

    }
    
}
