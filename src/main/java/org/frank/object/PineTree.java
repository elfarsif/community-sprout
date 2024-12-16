package org.frank.object;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

import javax.imageio.ImageIO;

public class PineTree extends Entity {

    public PineTree(GamePanel gp){
        super(gp);
        name = "pine-tree";
        down1 = setup("/objects/pine-tree.png");
    }
}
