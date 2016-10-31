package sanfair.hcsm.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import sanfair.hcsm.items.ItemFishnet;
import sanfair.hcsm.items.ItemLugboxTrap;

public class ModItems {

    public static Item lugboxtrap;
    public static Item fishnet;

    public static void init() {
        lugboxtrap = new ItemLugboxTrap();
        fishnet = new ItemFishnet();
    }

    public static void register() {
        GameRegistry.register(lugboxtrap);
        GameRegistry.register(fishnet);
    }

    public static void registerRenders() {
        registerRender(lugboxtrap);
        registerRender(fishnet);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft()
            .getRenderItem()
            .getItemModelMesher()
            .register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
