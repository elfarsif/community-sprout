package org.frank.entity;

import org.frank.main.GamePanel;
import org.frank.main.KeyHandler;

import java.awt.*;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=3;
    }

    public void update(){
        if(keyHandler.upPressed){
            y -= speed;
        }
        if(keyHandler.downPressed){
            y += speed;
        }
        if(keyHandler.leftPressed){
            x -= speed;
        }
        if(keyHandler.rightPressed){
            x += speed;
        }
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}
