package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

public class Shield extends Entity {
    public Shield(GamePanel gp) {
        super(gp);
        name = "normal shield";
        defenseValue = 0;
    }
}
