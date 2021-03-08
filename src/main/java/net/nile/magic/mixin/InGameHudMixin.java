package net.nile.magic.mixin;

import com.mojang.blaze3d.systems.RenderSystem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.nile.magic.NileMagic;
import net.nile.magic.players.IManaComponent;
import net.nile.magic.players.PlayerComponents;

//Why not a HudRenderCallback?
//To render teh mana bar behind the chat/other vanilla hud
//(we have to render the bar before certain vanilla things)
//Yes ive tried z translation - didnt work!

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {

    private static final Identifier MANA_STARS = new Identifier(NileMagic.modid, "textures/gui/mana_star.png");

    @Inject(method = "render", cancellable = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;getCurrentGameMode()Lnet/minecraft/world/GameMode;"))
    private void nileMagicRenderManaBar(MatrixStack matrixStack, float tickDelta, CallbackInfo cbi) {

        MinecraftClient client = MinecraftClient.getInstance();

        IManaComponent mana = PlayerComponents.MANA.get(client.player);

        float amount = mana.getCurrent();

        if (amount == 0 || client.player.getVehicle() != null || client.player.abilities.creativeMode){
            return;// maybe change this if uwant to stil lrender smth if u have 0 mana
        }

        int rowWidth = 10;

        float starValue = 10;

        int windowWidth = client.getWindow().getScaledWidth();

        int windowHeight = client.getWindow().getScaledHeight();

        int x;

        // n -= 10;

        // seems to be the right place for the top y
        int y = windowHeight - 49;

        client.getTextureManager().bindTexture(MANA_STARS);

        int rowCount = (int)(amount) / rowWidth;

        if (amount % rowWidth != 0) {
            rowCount++;
        }

        RenderSystem.enableAlphaTest();// jic

        // RenderSystem.disableBlend(); (dont)
        // lol check InGameHud render status effect overlay. if an effect is on low
        // duration
        // RenderSystem.color4f(1,1,1,f) if called to make the effect icon fade in and
        // out
        // this affects the mana bar :/
        // haha
        // we need to reset teh color 4f
        // gl be fukd up but mc simply sending stray vibes


        //texture is white
        //set color based on mana level xd
        if(amount == mana.getMax()){
            RenderSystem.color4f(242/255f, 1, 0, 1);
            
        }
        else{
            RenderSystem.color4f(83f/255, 100/255f, 239/255f, 1);

        }

        for (int row = 0; row < rowCount; row++) {
            // if(amount == 0) {
            // break;
            // }//should not need this

            y -= 10;

            // seems to be left edge of hunger bar

            x = windowWidth / 2 + 2;
            for (int i = 0; i < rowWidth; i++) {

                x += 8;

                if (amount >= starValue) {
                    DrawableHelper.drawTexture(matrixStack, x, y, 0, 0, 9, 9, 9, 9);
                    amount -= starValue;
                } else if (amount != 0) {
                    matrixStack.push();

                    // i used this to debug
                    // RenderSystem.color4f(1, 0, 0, 1);

                    float scale =  amount / starValue;

                    float oneOverScale = 1 / scale;

                    // centering translatin addition
                    float cta = 4.5f * (1 - scale);

                    matrixStack.scale(scale, scale, 1);

                    matrixStack.translate((x + cta) * oneOverScale, (y + cta) * oneOverScale + cta, 0);

                    DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, 9, 9, 9, 9);

                    // DrawableHelper.drawTexture(matrixStack, (int)(x * oneOverScale), (int)(y *
                    // oneOverScale), 0, 0, 9, 9, 9, 9);

                    amount = 0;

                    matrixStack.pop();
                } else {
                    break;
                }
            }
        }
    }

}
