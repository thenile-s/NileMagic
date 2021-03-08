package net.nile.magic.items;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nile.magic.NileMagic;
import net.nile.magic.items.mana.ManaResetItem;
import net.nile.magic.items.mana.MaxManaItem;
import net.nile.magic.items.spell.SpellScrollItem;
import net.nile.magic.items.spell.SpellTomeItem;
import net.nile.magic.statuseffects.StatusEffects;

//food items apparently don't apply instant effects :/ so we have to fix/bypass this?

public class Items implements ModInitializer {

        //food/consumables

    public static final Item MANA_COOKIE = new MaxManaItem(
            new Item.Settings().group(ItemGroup.FOOD).maxCount(64)
                    .food(new FoodComponent.Builder().alwaysEdible().hunger(1).saturationModifier(3)
                            .statusEffect(new StatusEffectInstance(StatusEffects.MANA_REGENERATION, 63, 1), 1).build()),
            false, 10, 20);

    public static final Item MANA_FLESH = new MaxManaItem(new Item.Settings().group(ItemGroup.FOOD).maxCount(64)
            .food(new FoodComponent.Builder().alwaysEdible().hunger(4).saturationModifier(6)
                    .statusEffect(new StatusEffectInstance(StatusEffects.MANA_DEGENERATION, 94, 2), 1).build()),
            true, 30, 0);

    public static final Item MANA_PIE = new MaxManaItem(new Item.Settings()
            .food(new FoodComponent.Builder().alwaysEdible().hunger(16).saturationModifier(7)
                    .statusEffect(new StatusEffectInstance(StatusEffects.MANA_REGENERATION, 600, 1), 1).build())
            .group(ItemGroup.FOOD).maxCount(64), false, 10, 50);

    public static final Item MANA_RESET = new ManaResetItem(new Item.Settings().maxCount(1).group(ItemGroup.MISC));

    // TODO mana potions and more max mana items and fixed mana effects

        //Tomes

    public static final Item SPELL_TOME = new SpellTomeItem(new Item.Settings().maxCount(1).group(NileMagic.SPELL_GROUP));

    //Chapters

    //Scrolls

    public static final Item SPELL_SCROLL = new SpellScrollItem(new Item.Settings().maxCount(1).group(NileMagic.SPELL_GROUP).maxDamage(3));

    @Override
    public void onInitialize() {
            //cons
        Registry.register(Registry.ITEM, new Identifier(NileMagic.modid, "mana_cookie"), MANA_COOKIE);

        Registry.register(Registry.ITEM, new Identifier(NileMagic.modid, "mana_flesh"), MANA_FLESH);

        Registry.register(Registry.ITEM, new Identifier(NileMagic.modid, "mana_reset"), MANA_RESET);

        Registry.register(Registry.ITEM, new Identifier(NileMagic.modid, "mana_pie"), MANA_PIE);

        //tome

        Registry.register(Registry.ITEM, new Identifier(NileMagic.modid, "spell_tome"), SPELL_TOME);

        //chap

        //scrolls

        Registry.register(Registry.ITEM, new Identifier(NileMagic.modid, "spell_scroll"), SPELL_SCROLL);

    }
}
