package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage down1,down2,down3,down4,down5,down6,down7,down8,
            up1,up2,up3,up4,up5,up6, up7,up8
            ,left1,left2,left3,left4,left5,left6, left7,left8
            ,right1,right2,right3,right4,right5,right6, right7,right8;
    public String direction ="down";

    int spriteCounter = 0;
    int spriteNumber =1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Boolean collisionOn = false;
    public int actionLookCounter = 0;
    String[] dialogs = new String[10];
    int dialogIndex=0;
    //For objects
    public BufferedImage image;
    public String name;
    public boolean collision = false;

    //CHARACTER STATUS
    public int maxLife;
    public int currentLife;

    public Entity(GamePanel gp){
        this.gp = gp;
        setDefaultSolidArea();
    }

    private void setDefaultSolidArea() {
        solidArea = new Rectangle();
        solidArea.x = 12*3;
        solidArea.y = 24*3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 8*3;
        solidArea.height = 8*3;

    }

    public void setAction(){}

    public void update(){
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this,false);
        gp.collisionChecker.checkPlayer(this);

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

    public BufferedImage setup(String path){

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = utilityTool.scaleImage(image, gp.tileSize*2, gp.tileSize*2);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :"+e);
        }
        return image;
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //check if tiles is in the screen + 1 tile
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

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

            // times 2 because the image is 4 tiles big
            g2d.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize*2, null);
        }


    }

    public void speak(){
        if(dialogs[dialogIndex] == null){
            dialogIndex = 0;
            gp.gameState = gp.playState;
        }
        gp.ui.currentDialog = dialogs[dialogIndex];
        dialogIndex++;
    }
}
