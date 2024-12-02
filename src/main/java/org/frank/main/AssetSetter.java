package org.frank.main;

import org.frank.object.Door;
import org.frank.object.Mushroom;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        gamePanel.objects[0] = new Mushroom();
        gamePanel.objects[0].worldX = 2 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 4 * gamePanel.tileSize;

        gamePanel.objects[1] = new Mushroom();
        gamePanel.objects[1].worldX = 6 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 4 * gamePanel.tileSize;

        gamePanel.objects[2] = new Door();
        gamePanel.objects[2].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 10 * gamePanel.tileSize;
    }
}
