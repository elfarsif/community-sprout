package org.frank.main;

import org.frank.entity.Oscael;
import org.frank.entity.StoreClerk;
import org.frank.monster.GreenSlime;
import org.frank.object.*;
import org.frank.tile_interactive.HoeableGrass;
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

        gp.objects[mapNum][1] = new Door(gp);
        gp.objects[mapNum][1].worldX = 35 * gp.tileSize;
        gp.objects[mapNum][1].worldY = 15 * gp.tileSize;

        gp.objects[mapNum][2] = new SingleBed(gp);
        gp.objects[mapNum][2].worldX = 35 * gp.tileSize;
        gp.objects[mapNum][2].worldY = 18 * gp.tileSize;

        gp.objects[mapNum][3] = new Axe(gp);
        gp.objects[mapNum][3].worldX = 32 * gp.tileSize;
        gp.objects[mapNum][3].worldY = 12 * gp.tileSize;

        gp.objects[mapNum][4] = new Hoe(gp);
        gp.objects[mapNum][4].worldX = 39 * gp.tileSize;
        gp.objects[mapNum][4].worldY = 8 * gp.tileSize;

        gp.objects[mapNum][5] = new Mushroom(gp);
        gp.objects[mapNum][5].worldX = 39 * gp.tileSize;
        gp.objects[mapNum][5].worldY = 10 * gp.tileSize;

        gp.objects[mapNum][6] = new Mushroom(gp);
        gp.objects[mapNum][6].worldX = 40 * gp.tileSize;
        gp.objects[mapNum][6].worldY = 10 * gp.tileSize;


    }

    public void setNPC(){
        int mapNum =0;
        gp.npc[mapNum][0] = new Oscael(gp);
        gp.npc[mapNum][0].worldX = 32 * gp.tileSize;
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

        gp.iTiles[mapNum][3] = new HoeableGrass(gp);
        gp.iTiles[mapNum][3].worldX = 44 * gp.tileSize;
        gp.iTiles[mapNum][3].worldY = 10 * gp.tileSize;

        gp.iTiles[mapNum][4] = new HoeableGrass(gp);
        gp.iTiles[mapNum][4].worldX = 45 * gp.tileSize;
        gp.iTiles[mapNum][4].worldY = 10 * gp.tileSize;

    }
}
