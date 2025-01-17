package org.frank.main;

import org.frank.entity.Oscael;
import org.frank.entity.StoreClerk;
import org.frank.monster.GreenSlime;
import org.frank.object.SwordCopper;
import org.frank.tile_interactive.PineTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        int mapNum =0;
        gp.objects[mapNum][0] = new SwordCopper(gp);
        gp.objects[mapNum][0].worldX = 30 * gp.tileSize;
        gp.objects[mapNum][0].worldY = 12 * gp.tileSize;

    }

    public void setNPC(){
        int mapNum =0;
        gp.npc[mapNum][0] = new Oscael(gp);
        gp.npc[mapNum][0].worldX = 30 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 10* gp.tileSize;

        mapNum = 1;
        gp.npc[mapNum][0] = new StoreClerk(gp);
        gp.npc[mapNum][0].worldX = 30 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 10* gp.tileSize;
    }

    public void setMonster(){
        int mapNum =0;
        gp.monsters[mapNum][0] = new GreenSlime(gp);
        gp.monsters[mapNum][0].worldX = 30 * gp.tileSize;
        gp.monsters[mapNum][0].worldY = 20 * gp.tileSize;

        gp.monsters[mapNum][1] = new GreenSlime(gp);
        gp.monsters[mapNum][1].worldX = 32 * gp.tileSize;
        gp.monsters[mapNum][1].worldY = 22 * gp.tileSize;
    }

    public void setInteractiveTiles() {
        int mapNum =0;
        gp.iTiles[mapNum][0] = new PineTree(gp);
        gp.iTiles[mapNum][0].collision = true;
        gp.iTiles[mapNum][0].worldX = 27 * gp.tileSize;
        gp.iTiles[mapNum][0].worldY = 10 * gp.tileSize;

        gp.iTiles[mapNum][1] = new PineTree(gp);
        gp.iTiles[mapNum][1].collision = true;
        gp.iTiles[mapNum][1].worldX = 27 * gp.tileSize;
        gp.iTiles[mapNum][1].worldY = 4 * gp.tileSize;

        gp.iTiles[mapNum][2] = new PineTree(gp);
        gp.iTiles[mapNum][2].worldX = 29 * gp.tileSize;
        gp.iTiles[mapNum][2].worldY = 6 * gp.tileSize;
    }
}
