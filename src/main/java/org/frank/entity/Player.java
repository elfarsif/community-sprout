package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/walking/down1.png"));
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
                y -= speed;
            }
            if(keyHandler.downPressed){
                direction = "down";
                y += speed;
            }
            if(keyHandler.leftPressed){
                direction = "left";
                x -= speed;
            }
            if(keyHandler.rightPressed){
                direction = "right";
                x += speed;
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

        g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

    }
}
