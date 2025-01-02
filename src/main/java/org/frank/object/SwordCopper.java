package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SwordCopper extends Entity {
    public SwordCopper(GamePanel gp) {
        super(gp);
        name = "copper sword";
        type = type_sword_copper;
        down1 = setup("/tools/sword_copper.png");
        attackValue = 2;
        attackArea.width = gp.tileSize;
        attackArea.height = gp.tileSize;
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
