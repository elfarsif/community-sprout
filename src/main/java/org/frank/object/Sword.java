package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Sword extends Entity {
    public Sword(GamePanel gp) {
        super(gp);
        name = "normal sword";
        type = type_sword;
        down1 = setup("/tools/sword.png");
        attackValue = 1;
        attackArea.width = gp.tileSize/2;
        attackArea.height = gp.tileSize/2;
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
