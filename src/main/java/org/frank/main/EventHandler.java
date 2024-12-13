package org.frank.main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventReactDefaultX;
    int eventReactDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = gp.tileSize/2;
        eventRect.y = gp.tileSize/2;
        eventRect.width = 1;
        eventRect.height = 1;
        eventReactDefaultX = eventRect.x;
        eventReactDefaultY = eventRect.y;
    }

    public void checkEvent(){
        if(hit(29,6,"up")){
            handleEvent();
        }

    }

    private void handleEvent() {
        System.out.println("handle event");
        gp.gameState = 3;
        gp.ui.currentDialog = "You found a treasure chest!";
        gp.ui.draw(gp.g2d);
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;
        gp.g2d.drawRect(eventRect.x, eventRect.y, eventRect.width, eventRect.height);

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventReactDefaultX;
        eventRect.y = eventReactDefaultY;

        return hit;
    }






}
