package org.frank.main;

import org.frank.entity.Entity;
import org.frank.object.HealthBar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    UtilityTool utilityTool = new UtilityTool();
    Font arial_40;
    public boolean messageOn = false;
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    Graphics2D g2d;
    public String currentDialog;
    BufferedImage backgroundImage;
    BufferedImage inventoryStrip;
    public int commandNum = 0;
    BufferedImage health1, health2, health3;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        loadBackgroundImage();
        loadUIImages();
        //CREATE HEALTH OBJECTS
        Entity healthBar = new HealthBar(gp);
        health1 = healthBar.down1;
        health2 = healthBar.down2;
        health3 = healthBar.down3;
    }

    public void addMessage(String text){
        messages.add(text);
        messageCounter.add(0);
    }

    private void loadBackgroundImage() {
        try{
            backgroundImage = ImageIO.read(getClass().getResource("/title-screen/title-picture.png"));
        }catch (Exception e){
            throw new RuntimeException("Error loading image Door:"+e);
        }

    }

    private void loadUIImages(){
        try{
            inventoryStrip = ImageIO.read(getClass().getResource("/ui/inventory-strip.png"));
            inventoryStrip = utilityTool.scaleImage(inventoryStrip, gp.tileSize*13, gp.tileSize*2);
        }catch (Exception e){
            throw new RuntimeException("Error loading image Door:"+e);
        }
    }

    public void draw(Graphics2D g2d){

        this.g2d = g2d;

        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);

        //Play State
        if (gp.gameState == gp.playState){
            drawHealthBar();
            drawInventory();
            drawMessage();
        }

        //Pause State
        if (gp.gameState == gp.pauseState){
            drawPauseMenu();
        }

        //Dialog State
        if (gp.gameState == gp.dialogueState){
            drawDialogScreen();
            drawHealthBar();
        }

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //OPTIONS STATE
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }


    }

    private void drawOptionsScreen() {
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*10;
        int frameHeight = gp.tileSize*10;

        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState){
            case 0:
                optionsTop(frameX,frameY);
                break;
            case 1:
                optionsControl(frameX,frameY);
                break;
            case 2:
                optionsExitGame(frameX,frameY);
        }

        gp.keyHandler.enterPressed = false;

    }

    private void optionsExitGame(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialog = "Are you sure you want to exit the game?";
        g2d.drawString(currentDialog,textX,textY);

        //YES
        String text = "YES";
        textX = getXforCenteredText(text)- frameX;
        textY += gp.tileSize*2;
        g2d.drawString(text,textX,textY);
        if (commandNum == 0){
            g2d.drawString(">",textX-30,textY);
            if (gp.keyHandler.enterPressed){
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }

        //NO
        text = "NO";
        textX = getXforCenteredText(text)- frameX;
        textY += gp.tileSize;
        g2d.drawString(text,textX,textY);
        if (commandNum == 1){
            g2d.drawString(">",textX-30,textY);
            if (gp.keyHandler.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }

    }

    private void optionsTop(int frameX, int frameY) {
        int textX;
        int textY;

        //TITLE

        String text = "OPTIONS";
        textX = getXforCenteredText(text)- frameX;
        textY = frameY + gp.tileSize;
        g2d.drawString(text,textX,textY);

        //FULL SCREEN ON/OFF
        text = "FULL SCREEN";
        textX = frameX+ gp.tileSize;
        textY += gp.tileSize*2;
        g2d.drawString(text,textX,textY);
        if (commandNum == 0){
            g2d.drawString(">",textX-30,textY);
            if (gp.keyHandler.enterPressed){
                if (!gp.fullScreenOn){
                    gp.fullScreenOn = true;
                }
                else{
                    gp.fullScreenOn = false;
                }

            }
        }

        //MUSIC
        text = "MUSIC";
        textY += gp.tileSize;
        g2d.drawString(text,textX,textY);
        if (commandNum == 1){
            g2d.drawString(">",textX-30,textY);
        }

        //SOUND EFFECT
        text = "SOUND EFFECT";
        textY += gp.tileSize;
        g2d.drawString(text,textX,textY);
        if (commandNum == 2){
            g2d.drawString(">",textX-30,textY);
        }

        //CONTROL
        text = "CONTROL";
        textY += gp.tileSize;
        g2d.drawString(text,textX,textY);
        if (commandNum == 3){
            g2d.drawString(">",textX-30,textY);
            if (gp.keyHandler.enterPressed){
                subState = 1;
                commandNum = 0;
            }
        }

        //Exit
        text = "EXIT";
        textY += gp.tileSize;
        g2d.drawString(text,textX,textY);
        if (commandNum == 4){
            g2d.drawString(">",textX-30,textY);
            if (gp.keyHandler.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }

        //BACK
        text = "BACK";
        textY += gp.tileSize*2;
        g2d.drawString(text,textX,textY);
        if (commandNum == 5){
            g2d.drawString(">",textX-30,textY);
        }

        //FULL SCREEN CHECK BOX
        textX = (int) (frameX + gp.tileSize*6.5);
        textY = (int) (frameY + (int)gp.tileSize*2.5);
        g2d.drawRect(textX,textY,gp.tileSize/3,gp.tileSize/3);
        if (gp.fullScreenOn){
            g2d.fillRect(textX,textY,gp.tileSize/3,gp.tileSize/3);
        }

        //MUSIC SLIDER
        textY += gp.tileSize;
        g2d.drawRect(textX,textY,gp.tileSize*3,gp.tileSize/3);
        int volumeWidth = gp.tileSize*3/5 * gp.music.volumeScale;
        g2d.fillRect(textX,textY,volumeWidth,gp.tileSize/3);

        //SOUND EFFECT SLIDER
        textY += gp.tileSize;
        g2d.drawRect(textX,textY,gp.tileSize*3,gp.tileSize/3);
        volumeWidth = gp.tileSize*3/5 * gp.soundEffect.volumeScale;
        g2d.fillRect(textX,textY,volumeWidth,gp.tileSize/3);

    }

    public void optionsControl(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text = "CONTROL";
        textX = getXforCenteredText(text)- frameX;
        textY = frameY + gp.tileSize;
        g2d.drawString(text,textX,textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2d.drawString("Move",textX,textY);
        textY += gp.tileSize;
        g2d.drawString("Attack",textX,textY);
        textY += gp.tileSize;
        g2d.drawString("shoot",textX,textY);
        textY += gp.tileSize;
        g2d.drawString("Pause",textX,textY);

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2d.drawString("WASD",textX,textY);
        textY += gp.tileSize;
        g2d.drawString("SPACE",textX,textY);
        textY += gp.tileSize;
        g2d.drawString("F",textX,textY);
        textY += gp.tileSize;

        //BACK
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2d.drawString("BACK",textX,textY);
        if (commandNum == 0){
            g2d.drawString(">",textX-30,textY);
            if (gp.keyHandler.enterPressed){
                subState = 0;
                commandNum = 3;
            }
        }




    }

    private void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*5;

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22));

        for(int i = 0; i < messages.size(); i++){
            if(messages.get(i) != null){
                g2d.setColor(Color.WHITE);
                g2d.drawString(messages.get(i),messageX,messageY);

                int counter = messageCounter.get(i)+1;
                messageCounter.set(i,counter);
                messageY +=50;

                if(counter > 120){
                    messages.set(i,null);
                    messageCounter.set(i,0);
                }
            }
        }

    }

    private void drawInventory() {

        int frameX = gp.screenWidth/2-gp.tileSize*6;
        int frameY = gp.screenHeight-gp.tileSize*2;
        g2d.drawImage(inventoryStrip,frameX,frameY,null);

        //SLOT
        final int slotXStart = frameX + gp.tileSize/2;
        final int slotYStart = frameY + gp.tileSize/2;
        int slotX = slotXStart;
        int slotY = slotYStart;

        //DRAW PLAYER INVENTORY
        for(int i = 0; i < gp.player.inventory.size(); i++){
            g2d.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);
            slotX += gp.tileSize + gp.tileSize/2-8;
        }

        //CURSOR
        int cursorX = slotXStart + slotCol*(gp.tileSize+gp.tileSize/2-8);
        int cursorY = slotYStart + slotRow*gp.tileSize;
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //DRAW CURSOR
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()){
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22));
            g2d.setColor(Color.WHITE);
            g2d.drawString(gp.player.inventory.get(itemIndex).name,cursorX,cursorY+gp.tileSize+20);
        }


    }

    private void drawHealthBar() {
        int x = gp.tileSize/2;
        int y = 0;

        if(gp.player.currentLife == 3){
            g2d.drawImage(health3,x,y,gp.tileSize,gp.tileSize*4,null);
        }

        if(gp.player.currentLife == 2){
            g2d.drawImage(health2,x,y,gp.tileSize,gp.tileSize*4,null);
        }

        if(gp.player.currentLife == 1){
            g2d.drawImage(health1,x,y,gp.tileSize,gp.tileSize*4,null);
        }

        if(gp.player.currentLife <= 0){
            g2d.drawImage(health1,x,y,gp.tileSize,gp.tileSize*4,null);
        }
    }

    private void drawTitleScreen() {

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,gp.screenWidth,gp.screenHeight);


        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 60));
        String text = "Community Sprout";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*5;
        //SHADOW
        g2d.setColor(Color.gray);
        g2d.drawString(text,x+5,y+5);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text,x,y);

        //IMAGE
        x = gp.screenWidth/2 - gp.tileSize;
        y += gp.tileSize*2;
        g2d.drawImage(gp.player.down1,x,y,gp.tileSize,gp.tileSize,null);

        x= gp.screenWidth/2-1024/2;
        g2d.drawImage(backgroundImage,x,0,1024,1024,null);

        //MENU
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 30));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y = (int) (gp.screenHeight*0.80);
        g2d.drawString(text,x,y);
        if(commandNum == 0){
            g2d.drawString(">",x-gp.tileSize,y);
        }


        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2d.drawString(text,x,y);
        if(commandNum == 1){
            g2d.drawString(">",x-gp.tileSize,y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2d.drawString(text,x,y);
        if(commandNum == 2){
            g2d.drawString(">",x-gp.tileSize,y);
        }
    }

    private void drawDialogScreen() {
        //Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - gp.tileSize*4;
        int height = gp.tileSize*5;

        drawSubWindow(x,y,width,height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30));
        x += gp.tileSize;
        y += gp.tileSize;
        g2d.drawString(currentDialog,x,y);
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color color = new Color(0,0,0, 200);
        g2d.setColor(color);
        g2d.fillRoundRect(x,y,width,height,20,20);

        color= new Color(255,255,255);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x+3,y+3,width-6,height-6,20,20);
    }

    public void drawPauseMenu(){
        //Frame
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize*2;
        final int frameWidth = gp.tileSize*10;
        final int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //Text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 22));

        int textX = frameX + gp.tileSize/2;
        int textY = frameY + gp.tileSize/2;
        final int lineHeight = gp.tileSize;//font height

        //Names
        g2d.drawString("Level",textX,textY);
        textY += lineHeight;
        g2d.drawString("Attack",textX,textY);
        textY += lineHeight;
        g2d.drawString("XP",textX,textY);
        textY += lineHeight;
        g2d.drawString("Weapon",textX,textY);
        textY += lineHeight;
        g2d.drawString("Defense",textX,textY);

        //VALUES
        int tailX = frameX + frameWidth - 30;
        textY = frameY + gp.tileSize/2;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value,tailX);
        g2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value,tailX);
        g2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value,tailX);
        g2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = gp.player.currentWeapon.name;
        textX = getXforAlignToRightText(value,tailX);
        g2d.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value,tailX);
        g2d.drawString(value,textX,textY);
        textY += lineHeight;

    }

    public int getItemIndexOnSlot(){
        int index = slotCol;
        return index;
    }

    public int getXforCenteredText(String text){
        int x;
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        x = gp.screenWidth/2 - length/2;
        return x;
    }

    public int getXforAlignToRightText(String text, int tailX){
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = tailX - length;
        return x;
    }

}
