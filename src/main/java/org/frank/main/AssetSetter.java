package org.frank.main;

import org.frank.entity.Oscael;
import org.frank.monster.GreenSlime;
import org.frank.object.SwordCopper;
import org.frank.tile_interactive.PineTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.objects[0] = new SwordCopper(gp);
        gp.objects[0].worldX = 30 * gp.tileSize;
        gp.objects[0].worldY = 12 * gp.tileSize;
    }

    public void setNPC(){
        gp.npc[0] = new Oscael(gp);
        gp.npc[0].worldX = 30 * gp.tileSize;
        gp.npc[0].worldY = 10* gp.tileSize;
    }

    public void setMonster(){
        gp.monsters[0] = new GreenSlime(gp);
        gp.monsters[0].worldX = 30 * gp.tileSize;
        gp.monsters[0].worldY = 20 * gp.tileSize;

        gp.monsters[1] = new GreenSlime(gp);
        gp.monsters[1].worldX = 32 * gp.tileSize;
        gp.monsters[1].worldY = 22 * gp.tileSize;
    }

    public void setInteractiveTiles() {
        gp.iTiles[0] = new PineTree(gp);
        gp.iTiles[0].collision = true;
        gp.iTiles[0].worldX = 27 * gp.tileSize;
        gp.iTiles[0].worldY = 10 * gp.tileSize;

        gp.iTiles[1] = new PineTree(gp);
        gp.iTiles[1].collision = true;
        gp.iTiles[1].worldX = 27 * gp.tileSize;
        gp.iTiles[1].worldY = 4 * gp.tileSize;

        gp.iTiles[2] = new PineTree(gp);
        gp.iTiles[2].worldX = 29 * gp.tileSize;
        gp.iTiles[2].worldY = 6 * gp.tileSize;
    }
}
