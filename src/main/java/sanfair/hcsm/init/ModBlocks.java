package sanfair.hcsm.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sanfair.hcsm.blocks.BlockAnimalTrap;
import sanfair.hcsm.blocks.BlockFishTrap;
import sanfair.hcsm.lib.Reference;

public class ModBlocks {

    public static Block smatranimaltrap;
    public static Block smatrfishtrap;

    public static void init() {
        //smatranimaltrap = new BlockAnimalTrap();
        smatrfishtrap = new BlockFishTrap();
    }

    public static void register() {
        //registerBlock(smatranimaltrap);
        registerBlock(smatrfishtrap);
    }

    private static void registerBlock(Block block){
        GameRegistry.register(block);
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
    }

    public static void registerRenders() {
        //registerRender(smatranimaltrap);
        registerRender(smatrfishtrap);
    }

    public static void registerRender(Block block) {
        Minecraft
            .getMinecraft()
            .getRenderItem()
            .getItemModelMesher()
            .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

}
