package net.nile.magic.client.entities.models;

import net.minecraft.util.Identifier;
import net.nile.magic.NileMagic;
import net.nile.magic.entities.FireBlastEntity;
import net.nile.magic.entities.TornadoEntity;

public class NonLivingEntityModels {
    public static final NonLivingEntityModel<TornadoEntity> TORNADO = new NonLivingEntityModel<TornadoEntity>(new Identifier(NileMagic.modid, "geo/spell/tornado.geo.json"), new Identifier(NileMagic.modid, "textures/entity/spell/tornado.png"));
    //TODO fireball model
    public static final NonLivingEntityModel<FireBlastEntity> FIRE_BLAST = new NonLivingEntityModel<FireBlastEntity>(new Identifier(NileMagic.modid, "geo/spell/tornado.geo.json"), new Identifier(NileMagic.modid, "textures/entity/spell/fire_blast.png"));

}
