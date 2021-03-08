package net.nile.magic.client.entities.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.nile.magic.entities.BaseEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.renderer.geo.IGeoRenderer;

@Environment(EnvType.CLIENT)
public class NonLivingEntityRenderer<T extends BaseEntity> extends EntityRenderer<T> implements IGeoRenderer<T> {

    protected NonLivingEntityRenderer(EntityRenderDispatcher dispatcher, AnimatedGeoModel<T> model) {
        super(dispatcher);
        m_model = model;
    }

    private AnimatedGeoModel<T> m_model;

    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices,
            VertexConsumerProvider vertexConsumers, int light) {

        matrices.push();

        applyMatrixTransforms(matrices, entity, tickDelta);

        RenderLayer renderType = getRenderType(entity, tickDelta, matrices, vertexConsumers, null,
                light, getTexture(entity));

        render(m_model.getModel(m_model.getModelLocation(entity)), entity, tickDelta, renderType, matrices, vertexConsumers, null, light,
                OverlayTexture.getUv(0, false), 1, 1, 1, 1);

        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public GeoModelProvider<T> getGeoModelProvider() {
        return m_model;
    }

    @Override
    public Identifier getTextureLocation(T instance) {
        return m_model.getTextureLocation(instance);
    }

    @Override
    public Identifier getTexture(T entity) {
        return m_model.getTextureLocation(entity);
    }

    protected void applyMatrixTransforms(MatrixStack matrixStack, T entity, float tickDelta){

    }

}
