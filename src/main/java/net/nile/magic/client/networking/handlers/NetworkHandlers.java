package net.nile.magic.client.networking.handlers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class NetworkHandlers implements ClientModInitializer{

    public static final EntitySpawnHandler EntitySpawn = new EntitySpawnHandler();

    @Override
    public void onInitializeClient() {

        ClientPlayNetworking.registerGlobalReceiver(EntitySpawnHandler.CHANNEL, EntitySpawn);
        
    }
    
}
