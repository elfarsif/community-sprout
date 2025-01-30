package org.frank.entity;

import org.frank.main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Random;

public class Oscael extends Entity {

    public Oscael(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getPlayerImage();
        setDialogs();
    }

    public void setDialogs(){
        dialogs[0] = "Hello there!";
        dialogs[1] = "I am Oscael.";
        dialogs[2] = "I am the guardian of this forest.";
        dialogs[3] = "I am here to help you.";
    }

    public void getPlayerImage() {
        up1 = this.setup("/npc/character.png");
        up2 = this.setup("/npc/character.png");
        down1 = this.setup("/npc/character.png");
        down2 = this.setup("/npc/character.png");
        left1 = this.setup("/npc/character.png");
        left2 = this.setup("/npc/character.png");
        right1 = this.setup("/npc/character.png");
        right2 = this.setup("/npc/character.png");
    }

    @Override
    public void setAction(){
        if (onPath){
            int goalCol = 29;
            int goalRow = 30;

            this.searchPath(goalCol, goalRow);



        }else {

            actionLookCounter++;
            if (actionLookCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(20) + 1;

                if (i <= 5) {
                    direction = "up";
                }
                if (i > 5 && i <= 10) {
                    direction = "down";
                }
                if (i > 10 && i <= 15) {
                    direction = "left";
                }
                if (i > 15 && i <= 20) {
                    direction = "right";
                }

                actionLookCounter = 0;
            }

        }
    }

    @Override
    public void speak(){
        super.speak();

        onPath = true;
        gp.ui.addMessage(String.valueOf(onPath));
    }

}
