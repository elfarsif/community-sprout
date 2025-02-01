package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Door extends Entity {

    GamePanel gp;

    public Door(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "door";
        down1 = this.setup("/objects/door.png");
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
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :" + e);
        }
        return image;
    }



}
