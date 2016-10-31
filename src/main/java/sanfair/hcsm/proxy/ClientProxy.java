package sanfair.hcsm.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import sanfair.hcsm.HCSM;
import sanfair.hcsm.gui.GuiHandler;
import sanfair.hcsm.init.ModBlocks;
import sanfair.hcsm.init.ModItems;

public class ClientProxy extends CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    public void init(FMLInitializationEvent e) {
        super.init(e);
        ModItems.registerRenders();
        ModBlocks.registerRenders();
        
    }

    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

}
