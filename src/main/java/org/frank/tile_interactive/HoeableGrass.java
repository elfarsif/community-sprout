package org.frank.tile_interactive;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class HoeableGrass extends InteractiveTile {
    GamePanel gp;

    public HoeableGrass(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "hoeable-grass";
        down1 = setup("/tiles/grass-plain.png");
        down2 = setup("/tiles/grass-plain.png");
        destructible = true;
        currentLife = 1;
        walkOverable = true;




    }

    public boolean isCorrectTool(Entity entity){
        boolean correctTool = false;
        if(entity.currentWeapon.type== type_hoe){
            correctTool = true;
        }
        return correctTool;
    }

    public InteractiveTile getDestroyedTile(){
        InteractiveTile soil = new Soil(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return soil;
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
