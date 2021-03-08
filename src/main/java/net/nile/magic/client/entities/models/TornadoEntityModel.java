package net.nile.magic.client.entities.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.nile.magic.NileMagic;
import net.nile.magic.entities.TornadoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@Environment(EnvType.CLIENT)
public class TornadoEntityModel extends AnimatedGeoModel<TornadoEntity> {

    public static final Identifier TEXTURE = new Identifier(NileMagic.modid, "textures/entity/spell/tornado.png");

    public static final Identifier MODEL = new Identifier(NileMagic.modid, "geo/spell/tornado.geo.json");

    public static final TornadoEntityModel INSTANCE = new TornadoEntityModel();

    @Override
    public Identifier getAnimationFileLocation(TornadoEntity animatable) {
        return null;//GeoModel
    }

    @Override
    public Identifier getModelLocation(TornadoEntity object) {
        return MODEL;
    }

    @Override
    public Identifier getTextureLocation(TornadoEntity object) {
        return TEXTURE;
    }
    
}
