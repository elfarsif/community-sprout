package org.frank.tile_interactive;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

public class PineTree extends InteractiveTile {
    GamePanel gp;

    public PineTree(GamePanel gp) {
        super(gp);
        this.gp = gp;
        down1 = setup("/objects/pine-tree.png");
        down2 = setup("/objects/pine-tree.png");
        destructible = true;
        currentLife = 3;
    }


    public boolean isCorrectTool(Entity entity){
        boolean correctTool = false;
        if(entity.currentWeapon.type== type_axe){
            correctTool = true;
        }
        return correctTool;
    }

    public InteractiveTile getDestroyedTile(){
        InteractiveTile tile = new PineTreeTrunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
