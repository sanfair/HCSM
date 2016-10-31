package sanfair.hcsm.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sanfair.hcsm.HCSM;
import sanfair.hcsm.lib.HcsmBlocks;

public class BlockAnimalTrap extends BlockSmartTrap {

    public BlockAnimalTrap() {
        super(Material.WOOD);
        setUnlocalizedName(HcsmBlocks.blocks.ANIMALTRAP.getUnlocolazidedName());
        setRegistryName(HcsmBlocks.blocks.ANIMALTRAP.getRegistryName());
        setCreativeTab(HCSM.modTab);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        // TODO Auto-generated method stub
        return null;
    }

}
