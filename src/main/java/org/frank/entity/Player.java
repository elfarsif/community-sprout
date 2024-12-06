package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);

        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 15;
        speed=4;
        direction = "down";
    }

    public void getPlayerImage() {
        down1 = setup("/player/walking/down1.png");
        down2 = setup("/player/walking/down2.png");
        up1 = setup("/player/standing/character.png");
        up2 = setup("/player/standing/character.png");
        left1 = setup("/player/standing/character.png");
        left2 = setup("/player/standing/character.png");
        right1 = setup("/player/standing/character.png");
        right2 = setup("/player/standing/character.png");

    }

    public void update(){
        boolean isMoving = false;
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){
            isMoving = true;
        }

        if(isMoving){
            if(keyHandler.upPressed){
                direction = "up";
            }
            if(keyHandler.downPressed){
                direction = "down";
            }
            if(keyHandler.leftPressed){
                direction = "left";
            }
            if(keyHandler.rightPressed){
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            //check object collision
            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            //check npc collisionk
            int npcIndex = gp.collisionChecker.checkEntity(this,gp.npc);
            interactNPC(npcIndex);

            if(!collisionOn){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }


            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    private void interactNPC(int i) {
        if(i != 999){
            System.out.println("Interacting with NPC");
        }
    }

    public void pickUpObject(int objectIndex){
        if(objectIndex != 999){

        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        switch (direction){
            case "down":
                if(spriteNumber == 1){
                    image = down1;
                }
                if(spriteNumber == 2){
                    image = down2;
                }
                break;
            case "up":
                if(spriteNumber == 1){
                    image = up1;
                }
                if(spriteNumber == 2){
                    image = up2;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left1;
                }
                if(spriteNumber == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image = right1;
                }
                if(spriteNumber == 2 ){
                    image = right2;
                }
                break;
        }

        g2d.drawImage(image, screenX, screenY, (int)(gp.tileSize*2),(int) (gp.tileSize*2), null);

    }
}
