package org.frank.environment;

import org.frank.main.GamePanel;

import java.awt.*;

/**
 * Manages the environment effects of the game, such as lighting, weather, rain, fog, etc.
 */
public class EnvironmentManager {
    GamePanel gp;
    Lighting lighting;

    public EnvironmentManager(GamePanel gp){
        this.gp = gp;
    }

    public void setup(){
        lighting = new Lighting(gp,600);
    }

    public  void draw(Graphics2D g2d){
        lighting.draw(g2d);
    }
}
