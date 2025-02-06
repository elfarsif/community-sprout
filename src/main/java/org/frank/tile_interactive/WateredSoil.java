package org.frank.tile_interactive;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class WateredSoil extends InteractiveTile {
    GamePanel gp;
    public WateredSoil(GamePanel gp, int worldX, int worldY) {
        super(gp);
        this.gp = gp;
        down1 = setup("/tiles/farming/watered-soil.png");
        down2 = setup("/tiles/farming/watered-soil.png");
        this.worldX = worldX*gp.tileSize;
        this.worldY = worldY*gp.tileSize;
        currentLife = 1;
        walkOverable = true;
        destructible = false;
    }


    public boolean isCorrectTool(Entity entity){
        boolean correctTool = false;
        if(entity.currentWeapon.type== type_watering_can){
            correctTool = true;
        }
        return correctTool;
    }

    public InteractiveTile getDestroyedTile(){
          return null;
    }


    @Override
    public BufferedImage setup(String filePath){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :" + e);
        }
        return image;
    }
}
