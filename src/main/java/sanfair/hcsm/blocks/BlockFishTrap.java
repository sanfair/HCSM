package sanfair.hcsm.blocks;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import sanfair.hcsm.HCSM;
import sanfair.hcsm.init.ModBlocks;
import sanfair.hcsm.lib.HcsmBlocks;
import sanfair.hcsm.tileentity.TileEntityFishTrap;

public class BlockFishTrap extends BlockSmartTrap {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyInteger WATER = PropertyInteger.create("water", 0, 3);

    public BlockFishTrap() {
        super(Material.WOOD);
        setSoundType(SoundType.CLOTH);
        setUnlocalizedName(HcsmBlocks.blocks.FISHTRAP.getUnlocolazidedName());
        setRegistryName(HcsmBlocks.blocks.FISHTRAP.getRegistryName());
        setCreativeTab(HCSM.modTab);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.smatrfishtrap);
    }

    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, 0)); //Meta 0
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFishTrap();
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            worldIn.setBlockState(pos, state.withProperty(WATER, getWaterProperty(worldIn,pos)), 2);
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.openGui(HCSM.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ()); 
        }
        return true;
    }

    public boolean isWaterNear(World worldIn, BlockPos pos) {
        IBlockState state;
        state = worldIn.getBlockState(pos.add(1, 0, 0));
         if (state.getMaterial() == Material.WATER) {
             if (((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                 return true;
                 }
         }
        state = worldIn.getBlockState(pos.add(0, 0, 1));
        if (state.getMaterial() == Material.WATER) {
            if (((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                return true;
            }
        }
        state = worldIn.getBlockState(pos.add(-1, 0, 0));
        if (state.getMaterial() == Material.WATER) {
            if (((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                return true;
            }
        }
        state = worldIn.getBlockState(pos.add(0, 0, -1));
        if (state.getMaterial() == Material.WATER) {
            if (((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isWaterAbove(World worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos.add(0, 1, 0)).getMaterial() == Material.WATER) {
            return true;
        }
        return false;
    }
    
    public int getWaterProperty(World worldIn, BlockPos pos) {        
        if (isWaterAbove(worldIn, pos)) {
            return 2;
        }
        if (isWaterNear(worldIn, pos)) {
            return 1;
        }
        return 0;
    }

    //super method ??
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return getDefaultState().withProperty(WATER, getWaterProperty(worldIn,pos));
    }

    //super method ??
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(WATER, getWaterProperty(worldIn,pos)), 2);
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        Random random = worldIn.rand;
        TileEntityFishTrap te;
        if ((te = (TileEntityFishTrap)worldIn.getTileEntity(pos)) != null) {
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

    //super method ??
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(WATER, meta);
    }

    //super method ??
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(WATER);
    }

    //super method ??
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {WATER});
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
        if(isWaterAbove(worldIn, pos)) {
            if(this.getMetaFromState(state) != 2) {
                setState(worldIn, pos, 2);
            }
        } else if (isWaterNear(worldIn, pos)) {
            if ( this.getMetaFromState(state) != 1) {
                setState(worldIn, pos, 1);
            }
        } else {  
            if(this.getMetaFromState(state) != 0) {
                setState(worldIn, pos, 0);
            }
        }
    }

    public static void setState(World worldIn, BlockPos pos, int meta) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        worldIn.setBlockState(pos, ModBlocks.smatrfishtrap.getDefaultState().withProperty(WATER, meta), 2);
        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

}
