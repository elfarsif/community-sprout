package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SingleBed extends Entity {


    GamePanel gp;

    public SingleBed(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "bed";
        down1 = this.setup("/objects/single-bed.png");
        collision = true;

    }

    @Override
    public void interact(){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "The door is locked. you need an axe";
    }

    @Override
    public BufferedImage setup(String filePath){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
            image = utilityTool.scaleImage(image, gp.tileSize*2, gp.tileSize*3);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :" + e);
        }
        return image;
    }
}
