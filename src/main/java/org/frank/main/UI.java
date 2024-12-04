package org.frank.main;

import org.frank.object.Mushroom;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gP;
    Font arial_40;
    BufferedImage mushroomImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFInished = false;

    public UI(GamePanel gP){
        this.gP = gP;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        Mushroom mushroom = new Mushroom();
        mushroomImage = mushroom.image;
    }

    public void showMessage(String message){
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2d){
        if(gameFInished){
            String text;
            int textLength;
            int x,y;

            text = "Game Finished";
            textLength = g2d.getFontMetrics().getStringBounds(text, g2d).getBounds().width;

            x = gP.getWidth()/2 - textLength/2;
            y = gP.getHeight()/2 - gP.tileSize*2;
            g2d.drawString(text, x, y);

        }else {
            g2d.setFont(arial_40);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(mushroomImage, gP.tileSize / 2, gP.tileSize / 2, gP.tileSize, gP.tileSize, null);
            g2d.drawString("x " + gP.player.hasMushroom, 80, 70);

            //MESSAGE
            if (messageOn) {
                g2d.setFont(g2d.getFont().deriveFont(20f));
                g2d.drawString(message, 80, 150);
                messageCounter++;

                if (messageCounter > 100) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }
}
