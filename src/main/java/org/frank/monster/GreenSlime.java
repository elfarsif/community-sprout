package org.frank.monster;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;
import org.frank.object.Banana;
import org.frank.object.Mushroom;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GreenSlime extends Entity {

    public GreenSlime(GamePanel gp) {
        super(gp);
        this.name = "green-slime";
        this.speed =1;
        this.maxLife = 3;
        type = type_monster;
        this.currentLife = this.maxLife;
        attack = 2;
        defense = 0;
        exp = 1;

        this.solidArea.x =0;
        this.solidArea.y =0;
        this.solidArea.width = gp.tileSize;
        this.solidArea.height = gp.tileSize;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        getImage();
    }

    public void getImage(){
        down1 = setupMonster("/monster/green-slime-1.png");
        down2 = setupMonster("/monster/green-slime-2.png");
        up1 = setupMonster("/monster/green-slime-1.png");
        up2 = setupMonster("/monster/green-slime-2.png");
        left1 = setupMonster("/monster/green-slime-1.png");
        left2 = setupMonster("/monster/green-slime-2.png");
        right1 = setupMonster("/monster/green-slime-1.png");
        right2 = setupMonster("/monster/green-slime-2.png");


    }

    private BufferedImage setupMonster(String filePath){

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :"+e);
        }
        return image;

    }

    @Override
    public void setAction(){
        actionLookCounter++;
        if(actionLookCounter == 120){
            Random random = new Random();
            int i = random.nextInt(20)+1;

            if (i <= 5) {
                direction="up";
            }
            if (i > 5 && i <= 10) {
                direction="down";
            }
            if (i > 10 && i <= 15) {
                direction="left";
            }
            if (i > 15 && i <= 20) {
                direction="right";
            }

            actionLookCounter = 0;
        }

    }

    @Override
    public void damageReaction(){
       moveAwayFromPlayer();
    }

    private void moveAwayFromPlayer(){
        actionLookCounter = 0;
        direction = gp.player.direction;
    }

    @Override
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;

        if (i<50){
            dropItem(new Banana(gp));
        }else {
            dropItem(new Mushroom(gp));
        }

    }
}
