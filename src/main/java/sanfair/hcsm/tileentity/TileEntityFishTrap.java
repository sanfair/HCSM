package sanfair.hcsm.tileentity;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityFishTrap extends TileEntity implements ISidedInventory, ITickable {
    
    public int produceTime;
    
    public TileEntityFishTrap() {
        super();
        produceTime = 0;
    }
    
    private ItemStack[] inventory = new ItemStack[19];
    /*
     * custom fields
     * 
     * public int runTime = 0;
     * public int currentBeeRunTime = 0;
     * public int produceTime = 0;
     */

    @Override
    public String getName() {
        return "FishTrap";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList tagList = compound.getTagList("Items", 10);
        this.inventory = new ItemStack[getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
            byte slot = stackTag.getByte("Slot");
            if (slot >= 0 && slot < this.inventory.length) {
                this.inventory[slot] = ItemStack.loadItemStackFromNBT(stackTag);
            }
        }
        this.produceTime = (int) compound.getShort("produceTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("produceTime", (short)this.produceTime);
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(stackTag);
                tagList.appendTag(stackTag);
            }
        }
        compound.setTag("Items", tagList);
        return compound;
    }

    @Override
    public void update() {
        
        
        boolean needsUpdate = false;
        boolean canRun = canRun();
        ItemStack bait = this.inventory[0];
        
        if (!this.worldObj.isRemote) {
            if (canRun) {
                ++this.produceTime;
                int runTime = getRunTime();
                runTime = 10;
                if (runTime > 0 && this.produceTime >= Math.floor(runTime)) {
                    this.produceTime = 0;
                    run();
                    needsUpdate = true;
                }
            }
        }
        if (needsUpdate) {
            markDirty();
            this.worldObj.notifyBlockUpdate(this.pos, this.worldObj.getBlockState(this.pos), this.worldObj.getBlockState(this.pos), 3);
        }
    }

    private void run() {
        ItemStack catchedFish = getFish();
        if(catchedFish == null) {
            return;
        }
        for(int i = 1; i <= 18; ++i) {
            if (this.inventory[i] == null) {
                decrInputStack();
                this.inventory[i] = catchedFish.copy();
                break;
            }
        }
        
    }

    private ItemStack getFish() {
        Random rnd = new Random();
        List<ItemStack> fishes = OreDictionary.getOres("itemFishing");
        if (this.inventory[0] != null) {
            int rndnum = rnd.nextInt(fishes.size());            
            return fishes.get(rndnum) == null? null : fishes.get(rndnum).copy();
        }
        return null;
        
    }

    private void decrInputStack() {
       ItemStackHelper.getAndSplit(this.inventory, 0, 1);
        
    }

    private int getRunTime() {
        int speed = getSpeed();
        
        if (speed == -1) {
            return -1;
        }
        int water = countBlocks("water");
        int i = 0;
        for (i = 0; i < water; ++i) {
            speed = (int)(speed * 0.95D);
        }
        return speed;
    }

    private int getSpeed() {
        ItemStack bait = inventory[0];
        
        if (bait == null) {
            return -1;
        }
        try {
            Class<?> HarvestCraft = Class.forName("com.pam.harvestcraft.HarvestCraft");
          
            
            if (bait.getItem() == com.pam.harvestcraft.item.ItemRegistry.fishtrapbaitItem) {
                return 3200;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Pam's harvest craft not loaded");
        }
        
        return -1;
    }

    private boolean canRun() {
        ItemStack bait = inventory[0];
        if (bait != null && isCorrectBait(bait) && countBlocks("water") > 5) {
            return true;
        }
        return false;
    }

    private int countBlocks(String name) {
        // TODO Auto-generated method stub
        byte i = 0;
        byte count = 0;
        World world = this.worldObj;
        BlockPos pos = this.pos;
        IBlockState block;
        if(Objects.equals(new String("water"), name)) {
            for(i = -2; i <= 2; ++i) {
                if (i == 0) {
                    continue;
                }
                block = world.getBlockState(pos.add(i, 0, 0));
                if (block.getMaterial() == Material.WATER) {
                    if (((Integer)block.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                            ++count;
                    }
                }
            }
            for(i = -2; i <= 2; ++i) {
                if (i == 0) {
                    continue;
                }
                block = world.getBlockState(pos.add(0, 0, i));
                if (block.getMaterial() == Material.WATER) {
                    if (((Integer)block.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                            ++count;
                    }
                }
            }
            return count;
        }
        if(Objects.equals(new String("trap"), name)) {
            //??
        }
        return 0;
    }

    private boolean isCorrectBait(ItemStack bait) {
        // TODO Auto-generated method stub
        List<ItemStack> baits = OreDictionary.getOres("baitFish", true);
        for(ItemStack allowedBait : baits) {
            if(Objects.equals(bait.getUnlocalizedName(), allowedBait.getUnlocalizedName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
            return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
         return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
          return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index != 0) {
            return false;
        } else {
            //TODO valid baits
            if (isCorrectBait(stack.copy())) {
                return true;
            }
            
            return false;
        }
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.inventory.length; ++i) {
            this.inventory[i] = null;
        }
        markDirty();
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        int[] slots  = new int[inventory.length];
        for (int i = 0; i < inventory.length; i++) {
            slots[i] = i;
        }
        return slots;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {        
        return index == 0 ? false : true;
    }

}
