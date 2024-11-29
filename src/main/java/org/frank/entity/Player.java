package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gamePanel.tileSize;
        solidArea.height = gamePanel.tileSize;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 5;
        worldY = gamePanel.tileSize * 5;
        speed=4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/standing/character.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/walking/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/walking/down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/walking/down4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/walking/down5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/walking/down6.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/walking/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/walking/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/walking/up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/walking/up4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/walking/up5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/player/walking/up6.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/walking/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/walking/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/walking/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/walking/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/walking/left5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/walking/left6.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/walking/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/walking/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/walking/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/walking/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/walking/right5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/walking/right6.png"));
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :"+e);
        }
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
            gamePanel.collisionChecker.checkTile(this);

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

    public void draw(Graphics2D g2d){
/*        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);*/
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

        g2d.drawImage(image, screenX, screenY, (int)(gamePanel.tileSize*2),(int) (gamePanel.tileSize*2), null);

    }
}
