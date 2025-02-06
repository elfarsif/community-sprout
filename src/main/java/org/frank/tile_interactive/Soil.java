package org.frank.tile_interactive;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Soil extends InteractiveTile {
    GamePanel gp;


    public Soil(GamePanel gp, int worldX, int worldY) {
        super(gp);
        this.gp = gp;
        down1 = setup("/tiles/farming/soil.png");
        down2 = setup("/tiles/farming/soil.png");
        this.worldX = worldX*gp.tileSize;
        this.worldY = worldY*gp.tileSize;
        currentLife = 1;
        walkOverable = true;
        makeDestructible();
    }


    public boolean isCorrectTool(Entity entity){
        boolean correctTool = false;
        if(entity.currentWeapon.type== type_watering_can){
            correctTool = true;
        }
        return correctTool;
    }

    public InteractiveTile getDestroyedTile(){
        InteractiveTile wateredSoil = new WateredSoil(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return wateredSoil;
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
