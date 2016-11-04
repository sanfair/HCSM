package sanfair.hcsm.gui.fishtrap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import sanfair.hcsm.tileentity.TileEntityFishTrap;

public class FishTrapContainer extends Container {

    private final TileEntityFishTrap fishtrap;
    private final int sizeInventory;

    /*
     * custom fields
     * 
     * private int lastProduceTime = 0;
     * private int lastRunTime = 0;
     * private int lastBeeRunTime = 0;
     * 
     */

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return fishtrap.isUseableByPlayer(playerIn);
    }

    public FishTrapContainer(InventoryPlayer inv, TileEntityFishTrap TileEntityFishTrap) {
        this.fishtrap = TileEntityFishTrap;
        sizeInventory = fishtrap.getSizeInventory();
        //trap input slot
        addSlotToContainer(new Slot(TileEntityFishTrap, 0, 26, 35));
        //trap output slots
        int x, y;
        for (y = 0; y < 3; ++y) {
            for (x = 0; x < 6; ++x) {
                addSlotToContainer(new Slot(TileEntityFishTrap, 1 + x + 6 * y, 62 + 18 * x, 17 + 18 * y));
            }
        }
        //player hotbar slots
        for (x = 0; x < 9; ++x) {
            addSlotToContainer(new Slot(inv,  x, 8 + x * 18, 142));
        }
        //player inventory slots
        for (y = 0; y < 3;  ++y) {
            for (x = 0; x < 9; ++x) {
                addSlotToContainer(new Slot(inv,x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
       //TODO something
    }
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();
            if (fromSlot >= 19) {
                // From Player Inventory to TE Inventory
                //checking for oreDictionary fishBait
                if(fishtrap.isItemValidForSlot(0, current)) {
                    if (!this.mergeItemStack(current, 0, 1, true)) {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                // From TE Inventory to Player Inventory
                if (!mergeItemStack(current, sizeInventory, sizeInventory+36, false)) {
                    return null;
                }
            }
            if (current.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
            if (current.stackSize == previous.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }

}
