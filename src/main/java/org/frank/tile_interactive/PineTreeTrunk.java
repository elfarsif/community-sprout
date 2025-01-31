package org.frank.tile_interactive;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

public class PineTreeTrunk extends InteractiveTile{
    GamePanel gp;

    public PineTreeTrunk(GamePanel gp, int worldX, int worldY) {
        super(gp);
        this.gp = gp;
        down1 = setup("/objects/pine-tree-trunk.png");
        down2 = setup("/objects/pine-tree-trunk.png");
        this.worldX = worldX*gp.tileSize;
        this.worldY = worldY*gp.tileSize;
        currentLife = 3;
        makeDestructible();
    }

    private void makeDestructible() {
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Delay for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            destructible = true;
        }).start();
    }

    public boolean isCorrectTool(Entity entity){
        boolean correctTool = false;
        if(entity.currentWeapon.type== type_axe){
            correctTool = true;
        }
        return correctTool;
    }



}
