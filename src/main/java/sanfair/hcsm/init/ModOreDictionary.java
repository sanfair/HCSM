package sanfair.hcsm.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDictionary {

    public static void init() {
        try {
            Class<?> HarvestCraft = Class.forName("com.pam.harvestcraft.HarvestCraft");
            
            
            Item fishbait = com.pam.harvestcraft.item.ItemRegistry.fishtrapbaitItem;
            Item fruitbait = com.pam.harvestcraft.item.ItemRegistry.fruitbaitItem;
            Item grainbait = com.pam.harvestcraft.item.ItemRegistry.grainbaitItem;
            Item veggiebait = com.pam.harvestcraft.item.ItemRegistry.veggiebaitItem;
            
            Item anchovyrawItem = com.pam.harvestcraft.item.ItemRegistry.anchovyrawItem;
            Item bassrawItem = com.pam.harvestcraft.item.ItemRegistry.bassrawItem;
            Item calamarirawItem = com.pam.harvestcraft.item.ItemRegistry.calamarirawItem;
            Item carprawItem = com.pam.harvestcraft.item.ItemRegistry.carprawItem;
            Item catfishrawItem = com.pam.harvestcraft.item.ItemRegistry.catfishrawItem;
            Item charrrawItem = com.pam.harvestcraft.item.ItemRegistry.charrrawItem;
            Item clamrawItem = com.pam.harvestcraft.item.ItemRegistry.clamrawItem;
            Item crabrawItem = com.pam.harvestcraft.item.ItemRegistry.crabrawItem;
            Item crayfishrawItem = com.pam.harvestcraft.item.ItemRegistry.crayfishrawItem;
            Item eelrawItem = com.pam.harvestcraft.item.ItemRegistry.eelrawItem;
            Item frograwItem = com.pam.harvestcraft.item.ItemRegistry.frograwItem;
            Item grouperrawItem = com.pam.harvestcraft.item.ItemRegistry.grouperrawItem;
            Item herringrawItem = com.pam.harvestcraft.item.ItemRegistry.herringrawItem;
            Item jellyfishrawItem = com.pam.harvestcraft.item.ItemRegistry.jellyfishrawItem;
            Item mudfishrawItem = com.pam.harvestcraft.item.ItemRegistry.mudfishrawItem;
            Item octopusrawItem = com.pam.harvestcraft.item.ItemRegistry.octopusrawItem;
            Item perchrawItem = com.pam.harvestcraft.item.ItemRegistry.perchrawItem;
            Item scalloprawItem = com.pam.harvestcraft.item.ItemRegistry.scalloprawItem;
            Item shrimprawItem = com.pam.harvestcraft.item.ItemRegistry.shrimprawItem;
            Item snapperrawItem = com.pam.harvestcraft.item.ItemRegistry.snapperrawItem;
            Item tilapiarawItem = com.pam.harvestcraft.item.ItemRegistry.tilapiarawItem;
            Item troutrawItem = com.pam.harvestcraft.item.ItemRegistry.troutrawItem;
            Item tunarawItem = com.pam.harvestcraft.item.ItemRegistry.tunarawItem;
            Item turtlerawItem = com.pam.harvestcraft.item.ItemRegistry.turtlerawItem;
            Item walleyerawItem = com.pam.harvestcraft.item.ItemRegistry.walleyerawItem;
            
            OreDictionary.registerOre("baitFish", fishbait);
            
            OreDictionary.registerOre("baitAnimal", fruitbait);
            OreDictionary.registerOre("baitAnimal", grainbait);
            OreDictionary.registerOre("baitAnimal", veggiebait);
            
            OreDictionary.registerOre("baitVeggie", veggiebait);
            OreDictionary.registerOre("baitGrain", grainbait);
            OreDictionary.registerOre("baitFruit", fruitbait);
            
            /*
             * DEBUG
            OreDictionary.registerOre("bait", fishbait);
            OreDictionary.registerOre("bait", fruitbait);
            OreDictionary.registerOre("bait", grainbait);
            OreDictionary.registerOre("bait", veggiebait);
            */
            OreDictionary.registerOre("itemFishing", anchovyrawItem);
            OreDictionary.registerOre("itemFishing", bassrawItem);
            OreDictionary.registerOre("itemFishing", calamarirawItem);
            OreDictionary.registerOre("itemFishing", carprawItem);
            OreDictionary.registerOre("itemFishing", catfishrawItem);
            OreDictionary.registerOre("itemFishing", charrrawItem);
            OreDictionary.registerOre("itemFishing", clamrawItem);
            OreDictionary.registerOre("itemFishing", crabrawItem);
            OreDictionary.registerOre("itemFishing", crayfishrawItem);
            OreDictionary.registerOre("itemFishing", eelrawItem);
            OreDictionary.registerOre("itemFishing", frograwItem);
            OreDictionary.registerOre("itemFishing", grouperrawItem);
            OreDictionary.registerOre("itemFishing", herringrawItem);
            OreDictionary.registerOre("itemFishing", jellyfishrawItem);
            OreDictionary.registerOre("itemFishing", mudfishrawItem);
            OreDictionary.registerOre("itemFishing", octopusrawItem);
            OreDictionary.registerOre("itemFishing", perchrawItem);
            OreDictionary.registerOre("itemFishing", scalloprawItem);
            OreDictionary.registerOre("itemFishing", shrimprawItem);
            OreDictionary.registerOre("itemFishing", snapperrawItem);
            OreDictionary.registerOre("itemFishing", tilapiarawItem);
            OreDictionary.registerOre("itemFishing", troutrawItem);
            OreDictionary.registerOre("itemFishing", tunarawItem);
            OreDictionary.registerOre("itemFishing", turtlerawItem);
            OreDictionary.registerOre("itemFishing", walleyerawItem);
            
            
            OreDictionary.registerOre("itemFishing", new ItemStack(Items.FISH, 1, 0));
            OreDictionary.registerOre("itemFishing", new ItemStack(Items.FISH, 1, 1));
            OreDictionary.registerOre("itemFishing", new ItemStack(Items.FISH, 1, 2));
            OreDictionary.registerOre("itemFishing", new ItemStack(Items.FISH, 1, 3));
        } catch (ClassNotFoundException e) {
             System.out.println("Pam's harvest craft not loaded");
        }
    }

}
