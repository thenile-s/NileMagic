package net.nile.magic.client.blocks.entities.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.client.render.entity.model.BookModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.MathHelper;
import net.nile.magic.blocks.entities.TomeTableBlockEntity;

@Environment(EnvType.CLIENT)
public class TomeTableBlockEntityRenderer extends BlockEntityRenderer<TomeTableBlockEntity> {

    public TomeTableBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

   private final BookModel book = new BookModel();

    @Override
    public void render(TomeTableBlockEntity entity, float tickDelta, MatrixStack matrices,
            VertexConsumerProvider vertexConsumers, int light, int overlay) {
                
                matrices.push();
      matrices.translate(0.5D, 0.75D, 0.5D);
      float g = (float)entity.ticks + tickDelta;
      matrices.translate(0.0D, (double)(0.1F + MathHelper.sin(g * 0.1F) * 0.01F), 0.0D);

      float h;
      for(h = entity.field_11964 - entity.field_11963; h >= 3.1415927F; h -= 6.2831855F) {
      }

      while(h < -3.1415927F) {
         h += 6.2831855F;
      }

      float k = entity.field_11963 + h * tickDelta;
      matrices.multiply(Vector3f.POSITIVE_Y.getRadialQuaternion(-k));
      matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(80.0F));
      float l = MathHelper.lerp(tickDelta, entity.pageAngle, entity.nextPageAngle);
      float m = MathHelper.fractionalPart(l + 0.25F) * 1.6F - 0.3F;
      float n = MathHelper.fractionalPart(l + 0.75F) * 1.6F - 0.3F;
      float o = MathHelper.lerp(tickDelta, entity.pageTurningSpeed, entity.nextPageTurningSpeed);
      this.book.setPageAngles(g, MathHelper.clamp(m, 0.0F, 1.0F), MathHelper.clamp(n, 0.0F, 1.0F), o);
      VertexConsumer vertexConsumer = EnchantingTableBlockEntityRenderer.BOOK_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
      this.book.method_24184(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
      matrices.pop();
        
    }
    
}
