package org.frank.main;

import org.frank.entity.PlayerCutsceneDummy;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2d;
    public int sceneNum;
    public int scenePhase;

    //Scene Number
    public final int NA =0;
    public final int oscaelIntro =1;
    public final int gameStart =2;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2d){
        this.g2d = g2d;

        switch(sceneNum){
            case oscaelIntro:
                oscaelIntroCutscene();
                break;
            case gameStart:
                gameStartCutscene();
                break;
        }
    }

    public void oscaelIntroCutscene(){
        if(scenePhase == 0){
            //allows to move camera effect so you move player without seeing player
            gp.player.drawing = false;
            //add dummy player in vacant slot
            for(int i = 0; i < gp.npc[gp.currentMap].length; i++){
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerCutsceneDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            scenePhase++;
        }
        if (scenePhase == 1){
            //move camera down
            gp.player.worldY+=2;
            //stop camera at certain point
            if(gp.player.worldY >= 20 * gp.tileSize){
                scenePhase++;
            }
        }
        if (scenePhase == 2){
            // move to slime monster
        }


    }


    public void gameStartCutscene(){
        System.out.println("gameStartCutscene");
    }

}
