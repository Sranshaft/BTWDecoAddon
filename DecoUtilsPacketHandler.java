package net.minecraft.src;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;

public class DecoUtilsPacketHandler 
{
	public static void ServerOpenCustomInterface(EntityPlayerMP player, Container container, int containerID)
	{
		try
		{
			int windowID = player.IncrementAndGetWindowID();
			
			ByteArrayOutputStream var4 = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(var4);
			output.writeInt(windowID);
			output.writeInt(containerID);
			
			Packet250CustomPayload payload = new Packet250CustomPayload("DC|OI", var4.toByteArray());
			player.playerNetServerHandler.sendPacketToPlayer(payload);
			player.openContainer = container;
			player.openContainer.windowId = windowID;
			player.openContainer.addCraftingToCrafters(player);
		}
		catch (Exception var7)
		{
			var7.printStackTrace();
		}
	}
	
	public static boolean packetReceived(Minecraft mc, Packet250CustomPayload payload)
	{
		try
		{
			WorldClient client = mc.theWorld;
	        DataInputStream input = new DataInputStream(new ByteArrayInputStream(payload.data));
			
			if (payload.channel.equals("DC|OI"))
			{
				int windowID = input.readInt();
                int containerID = input.readInt();
				EntityClientPlayerMP player = mc.thePlayer;
                GuiContainer guiWindow = getAssociatedGUI(player, containerID);

                if (guiWindow != null)
                {
                    mc.displayGuiScreen(guiWindow);
                    player.openContainer.windowId = windowID;
                    return true;
                }
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public static GuiContainer getAssociatedGUI(EntityClientPlayerMP player, int containerID)
    {
		if (containerID == DecoModuleMechanical.decoSubModuleCompostBin.decoContainerCompostBinID)
        {
            DecoTileEntityCompostBin entity = new DecoTileEntityCompostBin();
            return new DecoClientGuiCompostBin(player.inventory, entity);
        }
        else if (containerID == DecoModuleDecoration.decoSubModuleCrates.decoContainerCrateID)
        {
        	DecoTileEntityCrate entity = new DecoTileEntityCrate();
        	return new DecoClientGuiCrate(player.inventory, entity);
        }
        else if (containerID == DecoModuleDecoration.decoSubModuleSack.decoContainerSackID)
        {
        	DecoTileEntitySack entity = new DecoTileEntitySack();
        	return new DecoClientGuiSack(player.inventory, entity);
        }
        else
        	return null;
    }
}
