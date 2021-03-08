package net.nile.magic.client.networking.handlers;

import java.util.UUID;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nile.magic.NileMagic;
import net.nile.magic.entities.Entities;
import net.nile.magic.entities.TornadoEntity;

public class EntitySpawnHandler implements PlayChannelHandler {

    public static final Identifier CHANNEL = new Identifier(NileMagic.modid, "entity_spawn");

    @Environment(EnvType.CLIENT)
    @Override
    public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf,
            PacketSender responseSender) {

        EntityType<?> type = Registry.ENTITY_TYPE.get(buf.readInt());
        int id = buf.readInt();
        UUID uuid = buf.readUuid();

        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();

        // double vx = buf.readDouble();
        // double vy = buf.readDouble();
        // double vz = buf.readDouble();

        int pitch = buf.readInt();
        int yaw = buf.readInt();

        //int data = buf.readInt();

        client.execute(() -> {

            Entity entity = null;

            if (type == Entities.TORNADO) {
                entity = new TornadoEntity(type, client.world);
            }
            
            if (entity != null) {
                entity.updateTrackedPosition(x,y,z);
                entity.refreshPositionAfterTeleport(x,y,z);
                entity.pitch = (float) (pitch * 360) / 256.0F;
                entity.yaw = (float) (yaw * 360) / 256.0F;
                entity.setEntityId(id);
                entity.setUuid(uuid);

                client.world.addEntity(id, entity);
            }
            else{
                NileMagic.logger.error("Received spawn packet with unknown entity.");
            }
        });
    }

}
