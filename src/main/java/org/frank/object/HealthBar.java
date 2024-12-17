package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

public class HealthBar extends Entity {

    public HealthBar(GamePanel gp){
        super(gp);
        name = "health-bar";
        down1 = setup("/objects/health-bar/health-bar-1.png");
        down2 = setup("/objects/health-bar/health-bar-2.png");
        down3 = setup("/objects/health-bar/health-bar-3.png");
    }
}
