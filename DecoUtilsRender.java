package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class DecoUtilsRender 
{
	public static void drawCrossedSquares(Block block, double x, double y, double z, RenderBlocks render, double width, double height, int metadata, Icon icon)
	{
		Tessellator tess = Tessellator.instance;
		GL11.glColor3f(1, 1, 1);

		int brightness = block.getMixedBrightnessForBlock(render.blockAccess, (int)x, (int)y, (int)z);
		tess.setBrightness(brightness);
		tess.setColorOpaque_F(1, 1, 1);

		double minU = icon.getMinU();
		double maxU = icon.getMaxU();
		double minV = icon.getMinV();
		double maxV = icon.getMaxV();

		double minX = x + 0.5D - width;
		double maxX = x + 0.5D + width;
		double minZ = z + 0.5D - width;
		double maxZ = z + 0.5D + width;

		tess.addVertexWithUV(minX, y + height, minZ, minU, minV);
		tess.addVertexWithUV(minX, y + 0.0D, minZ, minU, maxV);
		tess.addVertexWithUV(maxX, y + 0.0D, maxZ, maxU, maxV);
		tess.addVertexWithUV(maxX, y + height, maxZ, maxU, minV);

		tess.addVertexWithUV(maxX, y + height, maxZ, minU, minV);
		tess.addVertexWithUV(maxX, y + 0.0D, maxZ, minU, maxV);
		tess.addVertexWithUV(minX, y + 0.0D, minZ, maxU, maxV);
		tess.addVertexWithUV(minX, y + height, minZ, maxU, minV);

		tess.addVertexWithUV(minX, y + height, maxZ, minU, minV);
		tess.addVertexWithUV(minX, y + 0.0D, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, y + 0.0D, minZ, maxU, maxV);
		tess.addVertexWithUV(maxX, y + height, minZ, maxU, minV);

		tess.addVertexWithUV(maxX, y + height, minZ, minU, minV);
		tess.addVertexWithUV(maxX, y + 0.0D, minZ, minU, maxV);
		tess.addVertexWithUV(minX, y + 0.0D, maxZ, maxU, maxV);
		tess.addVertexWithUV(minX, y + height, maxZ, maxU, minV);
		
		tess.setNormal((int)x, (int)y, (int)z);
	}
	
	public static void setRenderBoundsRotatedAboutJToFacing(RenderBlocks render, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int direction)
	{
		float rotatedMinX;
		float rotatedMinZ;
		float rotatedMaxX;
		float rotatedMaxZ;
		
		switch (direction)
		{
			case 4 :
			{
				rotatedMinX = 1.0F - maxX;
				rotatedMinZ = 1.0F - maxZ;
				rotatedMaxX = 1.0F - minX;
				rotatedMaxZ = 1.0F - minZ;
				
				break;
			}
			case 3 :
			{
				rotatedMinX = minZ;
				rotatedMinZ = minX;
				rotatedMaxX = maxZ;
				rotatedMaxZ = maxX;
				
				break;
			}
			case 2 :
			{
				rotatedMinX = 1.0F - maxZ;
				rotatedMinZ = 1.0F - maxX;
				rotatedMaxX = 1.0F - minZ;
				rotatedMaxZ = 1.0F - minX;
				
				break;
			}
			default :
			{
				rotatedMinX = minX;
				rotatedMinZ = minZ;
				rotatedMaxX = maxX;
				rotatedMaxZ = maxZ;
				
				break;
			}
		}

		render.setRenderBounds((double) rotatedMinX, (double) minY, (double) rotatedMinZ, (double) rotatedMaxX, (double) maxY, (double) rotatedMaxZ);
	}
}
