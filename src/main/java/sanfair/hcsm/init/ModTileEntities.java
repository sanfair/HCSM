package sanfair.hcsm.init;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sanfair.hcsm.tileentity.TileEntityFishTrap;
import sanfair.hcsm.lib.Reference;

public class ModTileEntities {

    public static void register() {
        register(TileEntityFishTrap.class);
    }

    private static void register(Class<? extends TileEntity> te) {
        register(te, te.getSimpleName(), Reference.MOD_ID);
    }

    private static void register(Class<? extends TileEntity> te, String name) {
        register(te, name, Reference.MOD_ID);
    }

    private static void register(Class<? extends TileEntity> te, String name, String BaseName) {
        GameRegistry.registerTileEntity(te, BaseName+ "_" + name);
    }

}
