package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.KeyHandler;
import org.frank.object.Fireball;
import org.frank.object.Mushroom;
import org.frank.object.Shield;
import org.frank.object.Sword;
import org.frank.tile_interactive.InteractiveTile;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public ArrayList<Entity> inventory = new ArrayList<Entity>();
    public final int maxInventorySize = 10;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);

        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 12 * 3;
        solidArea.y = 24 * 3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 8 * 3;
        solidArea.height = 8 * 3;

        attackArea.width = gp.tileSize;
        attackArea.height = gp.tileSize;

        handleClickingOnPlayer(gp);

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
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

    public void setDefaultValues() {
        worldX = gp.tileSize * 35;
        worldY = gp.tileSize * 10;
        speed = 5;
        direction = "down";

        //PLAYER STATUS
        maxLife = 3;
        currentLife = maxLife;
        level = 1;
        strength = 1;
        exp = 0;
        nextLevelExp = 2;
        coin = 0;
        currentWeapon = new Sword(gp);
        currentShield = new Shield(gp);
        projectile = new Fireball(gp);

        attack = getAttackValue();
        defense = getDefenseValue();

    }

    private int getDefenseValue() {
        defense = currentShield.defenseValue;
        return defense;
    }

    private int getAttackValue() {
        attackArea = currentWeapon.attackArea;
        attack = strength*currentWeapon.attackValue;
        return attack;
    }

    public void setItems(){
        inventory.add(new Sword(gp));
        inventory.add(new Mushroom(gp));
        inventory.add(new Mushroom(gp));
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

    public void getPlayerAttackImage() {
        if (currentWeapon.type == type_sword) {
            attackDown1 = setup("/player/attack/down1.png");
            attackDown2 = setup("/player/attack/down2.png");
            attackDown3 = setup("/player/attack/down3.png");
            attackDown4 = setup("/player/attack/down4.png");
            attackUp1 = setup("/player/attack/up1.png");
            attackUp2 = setup("/player/attack/up2.png");
            attackUp3 = setup("/player/attack/up3.png");
            attackUp4 = setup("/player/attack/up4.png");
            attackLeft1 = setup("/player/attack/left1.png");
            attackLeft2 = setup("/player/attack/left2.png");
            attackLeft3 = setup("/player/attack/left3.png");
            attackLeft4 = setup("/player/attack/left4.png");
            attackRight1 = setup("/player/attack/right1.png");
            attackRight2 = setup("/player/attack/right2.png");
            attackRight3 = setup("/player/attack/right3.png");
            attackRight4 = setup("/player/attack/right4.png");
        }

        if (currentWeapon.type == type_sword_copper){
            attackDown1 = setup("/player/attack/copper_sword/down1.png");
            attackDown2 = setup("/player/attack/copper_sword/down2.png");
            attackDown3 = setup("/player/attack/copper_sword/down3.png");
            attackDown4 = setup("/player/attack/copper_sword/down4.png");
            attackUp1 = setup("/player/attack/copper_sword/up1.png");
            attackUp2 = setup("/player/attack/copper_sword/up2.png");
            attackUp3 = setup("/player/attack/copper_sword/up3.png");
            attackUp4 = setup("/player/attack/copper_sword/up4.png");
            attackLeft1 = setup("/player/attack/copper_sword/left1.png");
            attackLeft2 = setup("/player/attack/copper_sword/left2.png");
            attackLeft3 = setup("/player/attack/copper_sword/left3.png");
            attackLeft4 = setup("/player/attack/copper_sword/left4.png");
            attackRight1 = setup("/player/attack/copper_sword/right1.png");
            attackRight2 = setup("/player/attack/copper_sword/right2.png");
            attackRight3 = setup("/player/attack/copper_sword/right3.png");
            attackRight4 = setup("/player/attack/copper_sword/right4.png");
        }

    }

    public void update() {
        boolean isMoving = false;
        if (attacking) {
            attacking();

        } else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed
                || keyHandler.spacePressed) {
            isMoving = true;
        }

        if (isMoving) {
            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }

            if (keyHandler.upPressed && keyHandler.rightPressed) {
                direction = "up-right";
            }
            if (keyHandler.upPressed && keyHandler.leftPressed) {
                direction = "up-left";
            }
            if (keyHandler.downPressed && keyHandler.rightPressed) {
                direction = "down-right";
            }
            if (keyHandler.downPressed && keyHandler.leftPressed) {
                direction = "down-left";
            }

            //check tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            //check object collision
            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            //check npc collisionk
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check monster collision
            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
            contactMonster(monsterIndex);

            //check interactive tile collision
            int iTileIndex = gp.collisionChecker.checkEntity(this, gp.iTiles);

            //check event
            gp.eventHandler.checkEvent();

            if (!collisionOn && !keyHandler.spacePressed) {
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

            gp.keyHandler.spacePressed = false;

            updateSpriteAnimationImage();

        }

        if (gp.keyHandler.shootKeyPressed && projectile.alive == false){
            //SET PROJECTILE POSITION
            projectile.set(worldX, worldY, direction, true, this);

            gp.projectiles.add(projectile);
        }

        if (invincible) {
            invincibleCounter++;
            //60FPS ie 1 second
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void hitInteractiveTile(int i) {
        int counter = 0;
        if (i != 999
                && gp.iTiles[i].destructible
                && gp.iTiles[i].isCorrectTool(this)
                && !gp.iTiles[i].invincible) {

            gp.ui.addMessage("You destroyed ");
            gp.iTiles[i].currentLife--;
            gp.iTiles[i].invincible = true;

            if (gp.iTiles[i].currentLife == 0){
                gp.iTiles[i] = gp.iTiles[i].getDestroyedTile();
            }

        }
    }

    private void attacking() {
        spriteCounter++;
        //ATTACKING ANIMATION
        if (spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 15) {
            spriteNumber = 2;
        }
        if (spriteCounter > 15 && spriteCounter <= 25) {
            spriteNumber = 3;

            //SAVE CURRENT POSITION
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //ADJUST WORLD POSITION FOR ATTACK AREAK
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            // ATTACK AREA BECOMES SOLID AREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
            damageMonster(monsterIndex,attack);

            int iTileIndex = gp.collisionChecker.checkEntity(this, gp.iTiles);
            hitInteractiveTile(iTileIndex);

            //REVERT TO ORIGINAL POSITION
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25 && spriteCounter <= 35) {
            spriteNumber = 4;
        }
        if (spriteCounter > 35) {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damageMonster(int monsterIndex, int attack) {
        if (monsterIndex != 999) {
            if (!gp.monsters[monsterIndex].invincible){

                //CALCULATE DAMAGE TO MONSTER
                int damage = attack - gp.monsters[monsterIndex].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.monsters[monsterIndex].currentLife -= damage;
                gp.ui.addMessage( damage + " damage!");

                //MONSTER REACTION
                gp.monsters[monsterIndex].invincible = true;
                gp.monsters[monsterIndex].damageReaction();

                if(gp.monsters[monsterIndex].currentLife <= 0){
                    gp.monsters[monsterIndex].dying = true;
                    gp.ui.addMessage("+ "+gp.monsters[monsterIndex].exp+" exp");
                    exp+=gp.monsters[monsterIndex].exp;
                    checkLevelUp();
                }
            }
        }

    }

    private void checkLevelUp() {
        if (exp>=nextLevelExp){
            gp.ui.addMessage("LEVEL UP!");
            //change to new player values
            level++;
            nextLevelExp *=2;
            strength++;
            exp = 0;

            //Display dialogue
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialog = "Congratulations! You are now level "+level;

        }
    }

    private void contactMonster(int monsterIndex) {
        if (monsterIndex != 999) {
            if (!invincible && gp.monsters[monsterIndex].dying == false) {
                //CALCULATE MONSTER DAMAGE TO PLAYER
                int damage = gp.monsters[monsterIndex].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                currentLife -= damage;
                invincible = true;
            }
        }
    }

    private void updateSpriteAnimationImage() {

        spriteCounter++;
        int spriteAnimationRate = 9;

        if (spriteCounter > spriteAnimationRate) {
            spriteNumber++;
            if (spriteNumber > 8) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    private void interactNPC(int i) {
        if (gp.keyHandler.spacePressed) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                gp.playSoundEffect(3);
                attacking = true;
            }
        }
        gp.keyHandler.spacePressed = false;
    }

    public void pickUpObject(int objectIndex) {

        if (objectIndex != 999) {

            String text;
            if (inventory.size() != maxInventorySize) {
                inventory.add(gp.objects[objectIndex]);
                text = "You picked up " + gp.objects[objectIndex].name;
            }else {
                text = "inventory is full";
            }
            gp.ui.addMessage(text);
            gp.objects[objectIndex] = null;
        }
    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_sword_copper){
                currentWeapon = selectedItem;
                attack = getAttackValue();
                getPlayerAttackImage();
            }

            if (selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefenseValue();
            }

            if (selectedItem.type == type_consumable){
                /*selectedItem.applyConsumable(this);
                inventory.remove(itemIndex);*/
            }
        }
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "down":
                if (attacking) {
                    if (spriteNumber == 1) {
                        image = attackDown1;
                    }
                    if (spriteNumber == 2) {
                        image = attackDown2;
                    }
                    if (spriteNumber == 3) {
                        image = attackDown3;
                    }
                    if (spriteNumber == 4) {
                        image = attackDown4;
                    }
                } else {
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                    if (spriteNumber == 3) {
                        image = down3;
                    }
                    if (spriteNumber == 4) {
                        image = down4;
                    }
                    if (spriteNumber == 5) {
                        image = down5;
                    }
                    if (spriteNumber == 6) {
                        image = down6;
                    }
                    if (spriteNumber == 7) {
                        image = down7;
                    }
                    if (spriteNumber == 8) {
                        image = down8;
                    }
                }
                break;
            case "up":
                if (attacking) {
                    if (spriteNumber == 1) {
                        image = attackUp1;
                    }
                    if (spriteNumber == 2) {
                        image = attackUp2;
                    }
                    if (spriteNumber == 3) {
                        image = attackUp3;
                    }
                    if (spriteNumber == 4) {
                        image = attackUp4;
                    }
                } else {
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                    if (spriteNumber == 3) {
                        image = up3;
                    }
                    if (spriteNumber == 4) {
                        image = up4;
                    }
                    if (spriteNumber == 5) {
                        image = up5;
                    }
                    if (spriteNumber == 6) {
                        image = up6;
                    }
                    if (spriteNumber == 7) {
                        image = up7;
                    }
                    if (spriteNumber == 8) {
                        image = up8;
                    }
                }
                break;
            case "left", "up-left", "down-left":
                if (attacking) {
                    if (spriteNumber == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNumber == 2) {
                        image = attackLeft2;
                    }
                    if (spriteNumber == 3) {
                        image = attackLeft3;
                    }
                    if (spriteNumber == 4) {
                        image = attackLeft4;
                    }
                } else {
                    if (spriteNumber == 1) {
                        image = left1;
                    }
                    if (spriteNumber == 2) {
                        image = left2;
                    }
                    if (spriteNumber == 3) {
                        image = left3;
                    }
                    if (spriteNumber == 4) {
                        image = left4;
                    }
                    if (spriteNumber == 5) {
                        image = left5;
                    }
                    if (spriteNumber == 6) {
                        image = left6;
                    }
                    if (spriteNumber == 7) {
                        image = left7;
                    }
                    if (spriteNumber == 8) {
                        image = left8;
                    }
                }
                break;
            case "right", "up-right", "down-right":
                if (attacking) {
                    if (spriteNumber == 1) {
                        image = attackRight1;
                    }
                    if (spriteNumber == 2) {
                        image = attackRight2;
                    }
                    if (spriteNumber == 3) {
                        image = attackRight3;
                    }
                    if (spriteNumber == 4) {
                        image = attackRight4;
                    }
                } else {
                    if (spriteNumber == 1) {
                        image = right1;
                    }
                    if (spriteNumber == 2) {
                        image = right2;
                    }
                    if (spriteNumber == 3) {
                        image = right3;
                    }
                    if (spriteNumber == 4) {
                        image = right4;
                    }
                    if (spriteNumber == 5) {
                        image = right5;
                    }
                    if (spriteNumber == 6) {
                        image = right6;
                    }
                    if (spriteNumber == 7) {
                        image = right7;
                    }
                    if (spriteNumber == 8) {
                        image = right8;
                    }
                }
                break;

        }

        if (invincible) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2d.drawImage(image, screenX, screenY, null);

        //prevent UI from being transparent
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

    }

}
