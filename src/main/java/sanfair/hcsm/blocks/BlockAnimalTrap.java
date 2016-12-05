package sanfair.hcsm.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import sanfair.hcsm.HCSM;
import sanfair.hcsm.init.ModBlocks;
import sanfair.hcsm.lib.HcsmBlocks;
import sanfair.hcsm.tileentity.TileEntityAnimalTrap;

public class BlockAnimalTrap extends BlockSmartTrap {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockAnimalTrap() {
        super(Material.WOOD);
        setUnlocalizedName(HcsmBlocks.blocks.ANIMALTRAP.getUnlocolazidedName());
        setRegistryName(HcsmBlocks.blocks.ANIMALTRAP.getRegistryName());
        setCreativeTab(HCSM.modTab);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.smatranimaltrap);
    }

    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, 0)); //Meta 0
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityAnimalTrap();
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {}
    
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(HCSM.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ()); 
        }
        return true;
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {}

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        Random random = worldIn.rand;
        TileEntityAnimalTrap te;
        if ((te = (TileEntityAnimalTrap)worldIn.getTileEntity(pos)) != null) {
            for (int i = 0; i < te.getSizeInventory(); i++) {
                ItemStack itemStack = te.getStackInSlot(i);
                if (itemStack != null) {
                    float offsetX = random.nextFloat() * 0.8F + 0.1F;
                    float offsetY = random.nextFloat() * 0.8F + 0.1F;
                    float offsetZ = random.nextFloat() * 0.8F + 0.1F;
                    while (itemStack.stackSize > 0) {
                        int j = Math.min(random.nextInt(21) + 10, itemStack.stackSize);
                        itemStack.stackSize -= j;
                        EntityItem entityItem = new EntityItem(worldIn, pos.getX() + (double)offsetX, pos.getY() + (double)offsetY, pos.getZ() + (double)offsetZ, new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
                        if (itemStack.getTagCompound() != null) {
                            entityItem.getEntityItem().readFromNBT((NBTTagCompound)itemStack.getTagCompound().copy());
                        }
                        float speedRate = 0.05F;
                        entityItem.motionX = (random.nextGaussian() * speedRate);
                        entityItem.motionY = (random.nextGaussian() * speedRate + 0.20000000298023224D);
                        entityItem.motionZ = (random.nextGaussian() * speedRate);
                        worldIn.spawnEntityInWorld(entityItem);
                    }
                    worldIn.updateComparatorOutputLevel(pos, worldIn.getBlockState(pos).getBlock());
                    super.randomTick(worldIn, pos, state, random);
                    super.breakBlock(worldIn, pos, state);
                }
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return super.getMetaFromState(state);
    }

}
