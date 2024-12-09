package org.frank.main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFInished = false;
    Graphics2D g2d;
    public String currentDialog;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
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
            //play state studd
        }

        //Pause State
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

        //Dialog State
        if (gp.gameState == gp.dialogueState){
            drawDialogScreen();
        }

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
    }

    private void drawTitleScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 60));
        String text = "Title Screen";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2d.drawString(text,x,y);

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
