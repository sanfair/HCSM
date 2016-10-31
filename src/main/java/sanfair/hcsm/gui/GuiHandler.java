package sanfair.hcsm.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import sanfair.hcsm.gui.fishtrap.FishTrapContainer;
import sanfair.hcsm.gui.fishtrap.FishTrapGui;
import sanfair.hcsm.tileentity.TileEntityFishTrap;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        System.out.println("getServerGuiElement");
        System.out.println("tileEntity : " + tileEntity);
        if ((tileEntity instanceof TileEntityFishTrap)) {
            return new FishTrapContainer(player.inventory, (TileEntityFishTrap)tileEntity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        System.out.println("getClientGuiElement");
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if ((tileEntity instanceof TileEntityFishTrap)) {
            return new FishTrapGui(player.inventory, (TileEntityFishTrap)tileEntity);
         }
        return null;
    }

}
