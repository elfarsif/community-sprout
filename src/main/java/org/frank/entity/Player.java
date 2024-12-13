package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.KeyHandler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        solidArea.x = 12*3;
        solidArea.y = 24*3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 8*3;
        solidArea.height = 8*3;
        handleClickingOnPlayer(gp);

        setDefaultValues();
        getPlayerImage();
    }

    private void handleClickingOnPlayer(GamePanel gp) {
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (mouseX >= screenX && mouseX <= screenX + gp.tileSize * 2 &&
                        mouseY >= screenY && mouseY <= screenY + gp.tileSize * 2) {
                    down1 = up1;
                }
            }

        });

    }


    public void setDefaultValues(){
        worldX = gp.tileSize * 35;
        worldY = gp.tileSize * 10;
        speed=5;
        direction = "down";
    }

    public void getPlayerImage() {
        down1 = setup("/player/walking/down1.png");
        down2 = setup("/player/walking/down2.png");
        down3 = setup("/player/walking/down3.png");
        down4 = setup("/player/walking/down4.png");
        down5 = setup("/player/walking/down5.png");
        down6 = setup("/player/walking/down6.png");
        down7 = setup("/player/walking/down7.png");
        down8 = setup("/player/walking/down8.png");
        up1 = setup("/player/walking/up1.png");
        up2 = setup("/player/walking/up2.png");
        up3 = setup("/player/walking/up3.png");
        up4 = setup("/player/walking/up4.png");
        up5 = setup("/player/walking/up5.png");
        up6 = setup("/player/walking/up6.png");
        up7 = setup("/player/walking/up7.png");
        up8 = setup("/player/walking/up8.png");
        left1 = setup("/player/walking/left1.png");
        left2 = setup("/player/walking/left2.png");
        left3 = setup("/player/walking/left3.png");
        left4 = setup("/player/walking/left4.png");
        left5 = setup("/player/walking/left5.png");
        left6 = setup("/player/walking/left6.png");
        left7 = setup("/player/walking/left7.png");
        left8 = setup("/player/walking/left8.png");
        right1 = setup("/player/walking/right1.png");
        right2 = setup("/player/walking/right2.png");
        right3 = setup("/player/walking/right3.png");
        right4 = setup("/player/walking/right4.png");
        right5 = setup("/player/walking/right5.png");
        right6 = setup("/player/walking/right6.png");
        right7 = setup("/player/walking/right7.png");
        right8 = setup("/player/walking/right8.png");
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

            if(keyHandler.upPressed && keyHandler.rightPressed){
                direction = "up-right";
            }
            if(keyHandler.upPressed && keyHandler.leftPressed){
                direction = "up-left";
            }
            if(keyHandler.downPressed && keyHandler.rightPressed){
                direction = "down-right";
            }
            if(keyHandler.downPressed && keyHandler.leftPressed){
                direction = "down-left";
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
                    case "up-right":
                        worldY -= speed;
                        worldX += speed;
                        break;
                    case "up-left":
                        worldY -= speed;
                        worldX -= speed;
                        break;
                    case "down-right":
                        worldY += speed;
                        worldX += speed;
                        break;
                    case "down-left":
                        worldY += speed;
                        worldX -= speed;
                        break;
                }
            }

            updateSpriteAnimationImage();

        }

    }

    private void updateSpriteAnimationImage() {

        spriteCounter++;
        int spriteAnimationRate = 9;

        if(spriteCounter>spriteAnimationRate){
            spriteNumber++;
            if(spriteNumber > 8){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    private void interactNPC(int i) {
        if(i != 999){
            if(gp.keyHandler.ePressed){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyHandler.ePressed = false;
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
                if(spriteNumber == 3){
                    image = down3;
                }
                if(spriteNumber == 4){
                    image = down4;
                }
                if(spriteNumber == 5){
                    image = down5;
                }
                if(spriteNumber == 6){
                    image = down6;
                }
                if(spriteNumber == 7){
                    image = down7;
                }
                if(spriteNumber == 8){
                    image = down8;
                }
                break;
            case "up":
                if(spriteNumber == 1){
                    image = up1;
                }
                if(spriteNumber == 2){
                    image = up2;
                }
                if(spriteNumber == 3){
                    image = up3;
                }
                if(spriteNumber == 4){
                    image = up4;
                }
                if(spriteNumber == 5){
                    image = up5;
                }
                if(spriteNumber == 6){
                    image = up6;
                }
                if(spriteNumber == 7){
                    image = up7;
                }
                if(spriteNumber == 8){
                    image = up8;
                }
                break;
            case "left", "up-left", "down-left":
                if(spriteNumber == 1){
                    image = left1;
                }
                if(spriteNumber == 2){
                    image = left2;
                }
                if(spriteNumber == 3){
                    image = left3;
                }
                if(spriteNumber == 4){
                    image = left4;
                }
                if(spriteNumber == 5){
                    image = left5;
                }
                if(spriteNumber == 6) {
                    image = left6;
                }
                if(spriteNumber == 7){
                    image = left7;
                }
                if(spriteNumber == 8){
                    image = left8;
                }
                break;
            case "right", "up-right", "down-right":
                if(spriteNumber == 1){
                    image = right1;
                }
                if(spriteNumber == 2 ){
                    image = right2;
                }
                if(spriteNumber == 3){
                    image = right3;
                }
                if(spriteNumber == 4){
                    image = right4;
                }
                if(spriteNumber == 5){
                    image = right5;
                }
                if(spriteNumber == 6){
                    image = right6;
                }
                if(spriteNumber == 7){
                    image = right7;
                }
                if(spriteNumber == 8){
                    image = right8;
                }
                break;

        }

        g2d.drawImage(image, screenX, screenY, null);

    }


}
