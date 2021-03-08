package net.nile.magic.players;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.util.Identifier;
import net.nile.magic.NileMagic;

public final class PlayerComponents implements EntityComponentInitializer {

public static final ComponentKey<IManaComponent> MANA = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(NileMagic.modid, "mana"), IManaComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        
        registry.registerForPlayers(MANA, player -> new PlayerManaComponent(player), RespawnCopyStrategy.ALWAYS_COPY);

    }

}