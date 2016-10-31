package sanfair.hcsm.items;

import sanfair.hcsm.HCSM;
import sanfair.hcsm.lib.HcsmItems;

public class ItemFishnet extends net.minecraft.item.Item {
    public ItemFishnet() {
        setUnlocalizedName(HcsmItems.items.FISHNET.getUnlocolazidedName());
        setRegistryName(HcsmItems.items.FISHNET.getRegistryName());
        setCreativeTab(HCSM.modTab);
    }
    
}
