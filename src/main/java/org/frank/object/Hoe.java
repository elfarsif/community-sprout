package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Hoe extends Entity {
    public Hoe(GamePanel gp) {
        super(gp);
        name = "hoe";
        type = type_hoe;
        down1 = setup("/tools/hoe.png");
        attackValue = 0;
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

