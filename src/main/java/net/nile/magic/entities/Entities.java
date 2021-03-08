package net.nile.magic.entities;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nile.magic.NileMagic;

public class Entities implements ModInitializer {

    public static final EntityType<Entity> TORNADO = Registry.register(Registry.ENTITY_TYPE,
     new Identifier(NileMagic.modid, "tornado"), FabricEntityTypeBuilder.create(
         SpawnGroup.MISC, TornadoEntity::new).dimensions(EntityDimensions.fixed(.8f, 1.125f)).build());
         
    @Override
	public void onInitialize() {
	}
    
}
