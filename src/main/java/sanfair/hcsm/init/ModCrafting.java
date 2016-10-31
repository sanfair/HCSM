package sanfair.hcsm.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModCrafting {

    public static void register(){
        GameRegistry
            .addRecipe(new ShapedOreRecipe(
                new ItemStack(ModItems.lugboxtrap), "PPP","PPS","  S", 'P', "plankWood", 'S', "stickWood"));
        GameRegistry
            .addRecipe(new ShapedOreRecipe(
                    new ItemStack(ModItems.lugboxtrap), " PP","PPS","P S", 'P', "plankWood", 'S', "stickWood"));
        GameRegistry
            .addRecipe(new ShapedOreRecipe(
                    new ItemStack(ModItems.fishnet), "SsS","sss","SsS", 's', "string", 'S', "stickWood"));
    }

}
