package org.frank.main;

import org.frank.entity.Entity;
import org.frank.object.HealthBar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFInished = false;
    Graphics2D g2d;
    public String currentDialog;
    BufferedImage backgroundImage;
    public int commandNum = 0;
    BufferedImage health1, health2, health3;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        loadBackgroundImage();
        //CREATE HEALTH OBJECTS
        Entity healthBar = new HealthBar(gp);
        health1 = healthBar.down1;
        health2 = healthBar.down2;
        health3 = healthBar.down3;
    }

    private void loadBackgroundImage() {
        try{
            backgroundImage = ImageIO.read(getClass().getResource("/title-screen/title-picture.png"));
        }catch (Exception e){
            throw new RuntimeException("Error loading image Door:"+e);
        }

    }

    public void showMessage(String message){
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2d){

        this.g2d = g2d;

        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);

        //Play State
        if (gp.gameState == gp.playState){
            drawHealthBar();
        }

        //Pause State
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
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

    public void drawPauseScreen(){
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2d.drawString(text,x,y);
    }

    public int getXforCenteredText(String text){
        int x;
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        x = gp.screenWidth/2 - length/2;
        return x;
    }
}
