package org.frank.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean spacePressed;
    public boolean showDebug;
    public boolean shootKeyPressed;
    public boolean enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        //DEBUG TIME
        if(code == KeyEvent.VK_T){
            showDebug = !showDebug;
        }
        //REFRESH MAP
        //this does work with intellij because it does handle change in ressources it just reloads the old ressource while running
        if(code == KeyEvent.VK_R){
            gp.tileManager.loadMap("/maps/worldMap.txt",0);
            System.out.println("Map Refreshed");
        }
        //TITLE STATE
        if(gp.gameState == gp.titleState){
           titleState(code);
        }
        //PLAY STATE
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //OPTIONS STATE
        else if(gp.gameState == gp.optionsState){
            optionsState(code);
        }

    }

    private void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0:
                maxCommandNum = 5;
                break;
            case 2:
                maxCommandNum = 1;
                break;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSoundEffect(1);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSoundEffect(1);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_A) {
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    //TO CHANGE VOLUME WHILE MUSIC IS PLAYING
                    gp.music.checkVolume();
                    gp.playSoundEffect(1);
                }
                if(gp.ui.commandNum == 2 && gp.soundEffect.volumeScale > 0){
                    gp.soundEffect.volumeScale--;
                    gp.playSoundEffect(1);
                }
            }
        }

        if (code == KeyEvent.VK_D) {
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSoundEffect(1);
                }
                if(gp.ui.commandNum == 2 && gp.soundEffect.volumeScale < 5){
                    gp.soundEffect.volumeScale++;
                    gp.playSoundEffect(1);
                }
            }
        }

    }

    public void titleState(int code){
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }

        }
        if(code == KeyEvent.VK_ENTER){
            switch (gp.ui.commandNum){
                case 0:
                    gp.gameState = gp.cutsceneState;
                    gp.cutsceneManager.sceneNum = gp.cutsceneManager.gameStart;

                    break;
                case 1:
                    gp.gameState = gp.playState;
//                        gp.playMusic(0);
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_J){
            if(gp.ui.slotCol!=0){
                gp.ui.slotCol--;
                gp.playSoundEffect(2);
                gp.player.selectItem();
            }

        }
        if (code == KeyEvent.VK_K){
            if(gp.ui.slotCol!=8){
                gp.ui.slotCol++;
                gp.playSoundEffect(2);
                gp.player.selectItem();
            }
        }
        if (code == KeyEvent.VK_F){
            shootKeyPressed = true;
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_SPACE){
            System.out.println("Space Pressed");
            spacePressed = true;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shootKeyPressed = false;
        }

    }
}
