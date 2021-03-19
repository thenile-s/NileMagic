package net.nile.magic.client.entities.renderers;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.nile.magic.entities.FireBlastEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FireBlastEntityRenderer extends NonLivingEntityRenderer<FireBlastEntity> {

    protected FireBlastEntityRenderer(EntityRenderDispatcher dispatcher, AnimatedGeoModel<FireBlastEntity> model) {
        super(dispatcher, model);
        
    }
    
}
