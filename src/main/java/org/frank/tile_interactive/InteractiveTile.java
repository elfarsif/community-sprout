package org.frank.tile_interactive;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

public class InteractiveTile extends Entity {
    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp) {
        super(gp);
        this.gp = gp;

    }
    public void makeDestructible() {
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Delay for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            destructible = true;
        }).start();
    }


    public boolean isCorrectTool(Entity entity){
        boolean correctTool = false;
        return correctTool;
    }

    public void playSoundEffect(){}

    public InteractiveTile getDestroyedTile(){
        InteractiveTile tile = null;
        return null;
    }

    @Override
    public void update(){
         if (invincible) {
            invincibleCounter++;
            //60FPS ie 1 second
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
