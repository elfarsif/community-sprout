package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.object.Mushroom;

import java.util.Random;

import javax.imageio.ImageIO;
import java.util.Random;

public class StoreClerk extends Entity {

    public StoreClerk(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getPlayerImage();
        setDialogs();
        setItems();
    }

    public void setDialogs(){
        dialogs[0] = "Hello there! Welcome to the store";
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

    public void setItems(){
        this.inventory.add(new Mushroom(gp));
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
    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;

    }

}
