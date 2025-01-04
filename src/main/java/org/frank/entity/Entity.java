package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Represents a general entity in the game, including the player, NPCs, and monsters, objects, tools.
 * Handles common attributes and behaviors such as movement, collision, drawing on the screen, and interactions with other entities.
 */

public abstract class Entity {
    public GamePanel gp;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8,
            up1, up2, up3, up4, up5, up6, up7, up8, left1, left2, left3, left4, left5, left6, left7, left8, right1, right2, right3, right4, right5, right6, right7, right8;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4,
            attackDown1, attackDown2, attackDown3, attackDown4,
            attackLeft1, attackLeft2, attackLeft3, attackLeft4,
            attackRight1, attackRight2, attackRight3, attackRight4;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    String[] dialogs = new String[10];
    public boolean collision = false;
    public String description="";

    //STATE
    public int worldX, worldY;
    public String direction = "down";
    int spriteNumber = 1;
    int dialogIndex = 0;
    public Boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;

    //COUNTERS
    public int invincibleCounter = 0;
    int spriteCounter = 0;
    int dyingCounter = 0;
    public int actionLookCounter = 0;
    int hpBarCounter = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public int speed;
    public int maxLife;
    public int currentLife;
    public int maxMana;
    public int currentMana;
    public int strength;
    public int level;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public int projectileUseCost;

    //TYPE
    public int type;//0 player, 1 npc, 2 monster
    public final int type_monster = 2;
    public final int type_sword=3;
    public final int type_sword_copper=4;
    public final int type_shield=5;
    public final int type_consumable=6;



    public Entity(GamePanel gp) {
        this.gp = gp;
        setDefaultSolidArea();
    }

    /**
     * Configures the default solid area for collision detection.
     */
    private void setDefaultSolidArea() {
        solidArea = new Rectangle();
        solidArea.x = 12 * 3;
        solidArea.y = 24 * 3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 8 * 3;
        solidArea.height = 8 * 3;

    }

    public void setAction() {
    }

    public void damageReaction(){

    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.monsters);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer) {
            if (!gp.player.invincible) {
                int damage = attack - gp.player.defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.player.currentLife -= damage;
                gp.player.invincible = true;
            }
        }

        if (!collisionOn) {
            switch (direction) {
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
        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

        if (invincible) {
            invincibleCounter++;
            //60FPS ie 1 second
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public BufferedImage setup(String path) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = utilityTool.scaleImage(image, gp.tileSize * 2, gp.tileSize * 2);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :" + e);
        }
        return image;
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //check if tiles is in the screen + 1 tile
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "down":
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                    break;
                case "up":
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                    break;
                case "left":
                    if (spriteNumber == 1) {
                        image = left1;
                    }
                    if (spriteNumber == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNumber == 1) {
                        image = right1;
                    }
                    if (spriteNumber == 2) {
                        image = right2;
                    }
                    break;
            }

            //MONSTER HEALTH BAR
            if (type == 2 && hpBarOn) {

                double oneScale = (double) gp.tileSize / (double) maxLife;
                double hpBarValue = oneScale * currentLife;
                //outline
                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(screenX-1, screenY - 11, gp.tileSize+2, 7);
                //health
                g2d.setColor(new Color(255, 0, 30));
                g2d.fillRect(screenX, screenY - 10, (int)hpBarValue, 5);

                hpBarCounter++;

                if (hpBarCounter>600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlphaForDyingAnimationa(g2d,0.5f);
            }

            if (dying){
                dyingAnimation(g2d);
            }

            g2d.drawImage(image, screenX, screenY, null);

            changeAlphaForDyingAnimationa(g2d,1f);
        }

    }

    private void dyingAnimation(Graphics2D g2d) {
        dyingCounter++;

        int dyingFrames = 5;

        if (dyingCounter<=dyingFrames){
            changeAlphaForDyingAnimationa(g2d,0f);
        }
        if (dyingCounter>dyingFrames && dyingCounter<=dyingFrames*2){
            changeAlphaForDyingAnimationa(g2d,1f);
        }
        if (dyingCounter>dyingFrames*2 && dyingCounter<=dyingFrames*3){
            changeAlphaForDyingAnimationa(g2d,0f);
        }
        if (dyingCounter>dyingFrames*3 && dyingCounter<=dyingFrames*4){
            changeAlphaForDyingAnimationa(g2d,1f);
        }
        if (dyingCounter>dyingFrames*4 && dyingCounter<=dyingFrames*5){
            changeAlphaForDyingAnimationa(g2d,0f);
        }
        if (dyingCounter>dyingFrames*5 && dyingCounter<=dyingFrames*6){
            changeAlphaForDyingAnimationa(g2d,1f);
        }
        if (dyingCounter>dyingFrames*6 && dyingCounter<=dyingFrames*7){
            changeAlphaForDyingAnimationa(g2d,0f);
        }
        if (dyingCounter>dyingFrames*7){
            alive = false;
        }


    }

    private void changeAlphaForDyingAnimationa(Graphics2D g2d,float alpha){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public void applyConsumable(Entity entity){}


    public void speak() {
        if (dialogs[dialogIndex] == null) {
            dialogIndex = 0;
            gp.gameState = gp.playState;
        }
        gp.ui.currentDialog = dialogs[dialogIndex];
        dialogIndex++;
    }
}
