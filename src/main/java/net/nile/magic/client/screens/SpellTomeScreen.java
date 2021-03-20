package net.nile.magic.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nile.magic.NileMagic;
import net.nile.magic.screenhandlers.SpellTomeScreenHandler;

@Environment(EnvType.CLIENT)
public class SpellTomeScreen extends HandledScreen<SpellTomeScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(NileMagic.modid, "textures/gui/spell_tome.png");

    public SpellTomeScreen(SpellTomeScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        //this.playerInventoryTitleY -= 18;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        client.getTextureManager().bindTexture(TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

    }

/*
    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        super.drawForeground(matrices, mouseX, mouseY);
        //textRenderer.draw(matrices, "", 0, 0, 4210752);
    }
*/

    /*@Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
    }*/
    
}
