package org.frank.object;

import org.frank.main.GamePanel;

import javax.imageio.ImageIO;

public class PineTree extends SuperObject{
    GamePanel gp;

    public PineTree(GamePanel gp){
        name = "pine-tree";
        try{
            image = ImageIO.read(getClass().getResource("/objects/pine-tree.png"));
            image= utilityTool.scaleImage(image, gp.tileSize*2, gp.tileSize*2);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
