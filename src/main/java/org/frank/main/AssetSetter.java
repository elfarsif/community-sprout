package org.frank.main;

import org.frank.entity.Oscael;
import org.frank.object.PineTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.objects[0] = new PineTree(gp);
        gp.objects[0].collision = true;
        gp.objects[0].worldX = 27 * gp.tileSize;
        gp.objects[0].worldY = 4 * gp.tileSize;

        gp.objects[1] = new PineTree(gp);
        gp.objects[1].worldX = 29 * gp.tileSize;
        gp.objects[1].worldY = 6 * gp.tileSize;




    }

    public void setNPC(){
        gp.npc[0] = new Oscael(gp);
        gp.npc[0].worldX = 30 * gp.tileSize;
        gp.npc[0].worldY = 10* gp.tileSize;
    }
}
