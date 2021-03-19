package net.nile.magic.blocks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nile.magic.NileMagic;
import net.nile.magic.blocks.entities.TomeTableBlockEntity;

public class Blocks implements ModInitializer {

    public static final Block TOME_TABLE = new TomeTableBlock(FabricBlockSettings.of(Material.WOOD));

    public static BlockEntityType<TomeTableBlockEntity> TOME_TABLE_ENTITY;

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier(NileMagic.modid, "tome_table"), TOME_TABLE);
        TOME_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(NileMagic.modid, "tome_table"), BlockEntityType.Builder.create(TomeTableBlockEntity::new, TOME_TABLE).build(null));    }
    
}
