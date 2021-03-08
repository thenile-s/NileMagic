package net.nile.magic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.nile.magic.items.Items;
import net.nile.magic.screenhandlers.SpellTomeScreenHandler;

//TODO creative mode support

public class NileMagic implements ModInitializer {

	public static final Logger logger = LogManager.getLogger();
	public static final String modid = "nilemagic";

	public static final ScreenHandlerType<SpellTomeScreenHandler> SPELL_TOME_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(modid, "spell_tome"), SpellTomeScreenHandler::new);

	public static final ItemGroup SPELL_GROUP = FabricItemGroupBuilder.create(new Identifier(modid, "spells")).icon(()->new ItemStack(Items.SPELL_TOME)).build();

	@Override
	public void onInitialize() {
	}
}
