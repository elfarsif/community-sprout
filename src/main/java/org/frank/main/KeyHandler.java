package org.frank.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean ePressed;
    public boolean tPressed;

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
            tPressed = !tPressed;
        }
        if(gp.gameState == gp.titleState){
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
                        System.out.println("New Game not implemented");
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
        //PLAY STATE
        if(gp.gameState == gp.playState){
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
            if(code == KeyEvent.VK_E){
                ePressed = true;
            }

            if(code == KeyEvent.VK_P){
                if(gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
                }else if(gp.gameState == gp.pauseState){
                    gp.gameState = gp.playState;
                }
            }
        }else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_SPACE){
                gp.gameState = gp.playState;
            }
        }else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
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

    }
}
