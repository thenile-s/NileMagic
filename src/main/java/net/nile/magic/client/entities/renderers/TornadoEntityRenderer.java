package net.nile.magic.client.entities.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Quaternion;
import net.nile.magic.entities.TornadoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@Environment(EnvType.CLIENT)
public class TornadoEntityRenderer extends NonLivingEntityRenderer<TornadoEntity> {

    protected TornadoEntityRenderer(EntityRenderDispatcher dispatcher, AnimatedGeoModel<TornadoEntity> model) {
        super(dispatcher, model);
    }

    @Override
    protected void applyMatrixTransforms(MatrixStack matrixStack, TornadoEntity entity, float tickDelta) {

        matrixStack.multiply(new Quaternion(0, entity.getYaw(tickDelta), 0, true));

    }
}
