package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Banana extends Entity {
    GamePanel gp;

    public Banana(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "banana";
        value = 3;
        down1 = setup("/objects/banana.png");
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

    @Override
    public void applyConsumable(Entity entity){
        gp.ui.addMessage("You ate the banana. You feel better.");
    }
}
