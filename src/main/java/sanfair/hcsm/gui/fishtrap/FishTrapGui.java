package sanfair.hcsm.gui.fishtrap;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import sanfair.hcsm.tileentity.TileEntityFishTrap;

//@SideOnly(Reference.CLIENT_PROXY_CLASS) ---  WTF??
public class FishTrapGui extends GuiContainer {

    private static final ResourceLocation gui = new ResourceLocation("hcsm:textures/gui/fishtrap.png");
    private final InventoryPlayer inventoryPlayer;
    private final IInventory tileFishTrap;

    public FishTrapGui(InventoryPlayer inventoryPlayer, TileEntityFishTrap tileFishTrap) {
        super(new FishTrapContainer(inventoryPlayer, tileFishTrap));
        this.inventoryPlayer = inventoryPlayer;
        this.tileFishTrap = tileFishTrap;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString("Water Trap", 8, 8, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 4, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        org.lwjgl.opengl.GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(gui);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }

}
