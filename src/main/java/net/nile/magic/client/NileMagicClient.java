package net.nile.magic.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.nile.magic.NileMagic;
import net.nile.magic.blocks.Blocks;
import net.nile.magic.client.blocks.entities.renderers.TomeTableBlockEntityRenderer;
import net.nile.magic.client.screens.SpellTomeScreen;
import net.nile.magic.items.Items;

@Environment(EnvType.CLIENT)
public class NileMagicClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        //screens
        ScreenRegistry.register(NileMagic.SPELL_TOME_HANDLER, SpellTomeScreen::new);
        
        //model predicates
        FabricModelPredicateProviderRegistry.register(Items.SPELL_TOME, new Identifier("casting"),
         (itemStack, clientWorld, entity)->{
            return 0;
        });

        BlockEntityRendererRegistry.INSTANCE.register(Blocks.TOME_TABLE_ENTITY, TomeTableBlockEntityRenderer::new);
    }
    
}
