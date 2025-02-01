package org.frank.entity;

import org.frank.main.GamePanel;

import java.util.Random;

public class Oscael extends Entity {

    public Oscael(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        dialogueSetNumber = -1;

        getPlayerImage();
        setDialogs();
    }

    public void setDialogs(){
        dialogues[0][0] = "Hello there! Set 1";
        dialogues[0][1] = "I am Oscael.";
        dialogues[0][2] = "I am the guardian of this forest.";
        dialogues[0][3] = "I am here to help you.";

        dialogues[1][0] = "I am here to help you. Set 2";
        dialogues[1][1] = "I am the guardian of this forest.";
        dialogues[1][2] = "I am Oscael.";

        dialogues[2][0] = "I am Oscael. third dialogue set";
        dialogues[2][1] = "I am here to help you.";
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
        super.facePlayer();
        super.startDialogue(this, dialogueSetNumber);
        dialogueSetNumber++;
        onPath = true;
    }

}
