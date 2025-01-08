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

    public PineTree(GamePanel gp, int worldX, int worldY) {
        super(gp);
        this.gp = gp;
        down1 = setup("/objects/pine-tree.png");
        down2 = setup("/objects/pine-tree.png");
        this.worldX = worldX*gp.tileSize;
        this.worldY = worldY*gp.tileSize;
    }

    public boolean isCorrectTool(Entity entity){
        boolean correctTool = false;
        if(entity.currentWeapon.type== type_sword_copper){
            correctTool = true;
        }
        return correctTool;
    }

    public InteractiveTile getDestroyedTile(){
        InteractiveTile tile = new PineTreeTrunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
