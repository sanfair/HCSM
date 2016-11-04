package sanfair.hcsm.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import sanfair.hcsm.init.ModBlocks;
import sanfair.hcsm.init.ModCrafting;
import sanfair.hcsm.init.ModItems;
import sanfair.hcsm.init.ModOreDictionary;
import sanfair.hcsm.init.ModTileEntities;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModItems.init();
        ModItems.register();
        ModBlocks.init();
        ModBlocks.register();
        ModTileEntities.register();
        
        ModOreDictionary.init();
    }

    public void init(FMLInitializationEvent e) {
        ModCrafting.register();
    }

    public void postInit(FMLPostInitializationEvent e) {}

}
