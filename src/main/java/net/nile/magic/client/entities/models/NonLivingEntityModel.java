package net.nile.magic.client.entities.models;

import net.minecraft.util.Identifier;
import net.nile.magic.entities.BaseEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NonLivingEntityModel<T extends BaseEntity> extends AnimatedGeoModel<T> {

    public NonLivingEntityModel(Identifier model, Identifier texture){
        m_modelLocation = model;
        m_textureLocation = texture;
    }

    private final Identifier m_modelLocation;

    private final Identifier m_textureLocation;

    @Override
    public Identifier getAnimationFileLocation(T animatable) {
        return null;
    }

    @Override
    public Identifier getModelLocation(T object) {
        return m_modelLocation;
    }

    @Override
    public Identifier getTextureLocation(T object) {
        return m_textureLocation;
    }
    
}
