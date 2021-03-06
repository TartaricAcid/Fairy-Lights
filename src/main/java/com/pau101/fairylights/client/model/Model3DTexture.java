package com.pau101.fairylights.client.model;

import com.pau101.fairylights.client.renderer.FastenerRenderer;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;

public final class Model3DTexture extends ModelBox {
	public int width;

	public int height;

	public int textureOffsetX;

	public int textureOffsetY;

	public Model3DTexture(ModelRenderer model, int textureOffsetX, int textureOffsetY, float posX, float posY, float posZ, int width, int height) {
		super(model, 0, 0, posX, posY, posZ, width, height, 1, 0);
		this.width = width;
		this.height = height;
		this.textureOffsetX = textureOffsetX;
		this.textureOffsetY = textureOffsetY;
	}

	public Model3DTexture(ModelRenderer model, int textureOffsetX, int textureOffsetY, int width, int height) {
		this(model, textureOffsetX, textureOffsetY, 0, 0, 0, width, height);
	}

	@Override
	public void render(VertexBuffer buf, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(posX1 * scale, posY1 * scale, posZ1 * scale);
		FastenerRenderer.render3DTexture(width, height, textureOffsetX, textureOffsetY);
		GlStateManager.popMatrix();
	}
}
