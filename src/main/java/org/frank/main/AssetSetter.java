package org.frank.main;

import org.frank.entity.Oscael;
import org.frank.object.Door;
import org.frank.object.Mushroom;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.objects[0] = new Mushroom();
        gp.objects[0].worldX = 2 * gp.tileSize;
        gp.objects[0].worldY = 4 * gp.tileSize;


    }

    public void setNPC(){
        gp.npc[0] = new Oscael(gp);
        gp.npc[0].worldX = 3 * gp.tileSize;
        gp.npc[0].worldY = 3 * gp.tileSize;
    }
}
