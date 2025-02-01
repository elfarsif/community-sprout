package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Mushroom extends Entity {
    GamePanel gp;
    int healthValue = 1;

    public Mushroom(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "mushroom";
        type = type_consumable;
        down1 = setup("/objects/mushroom.png");
        description = "Give +10 health and +5 energy";
    }

    @Override
    public void applyConsumable(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You ate the mushroom. You feel better."+healthValue+" health";
        entity.currentLife += healthValue;
        if (gp.player.currentLife > gp.player.maxLife){
            gp.player.currentLife = gp.player.maxLife;
        }
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
