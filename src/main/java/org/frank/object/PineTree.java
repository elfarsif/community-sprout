package org.frank.object;

import javax.imageio.ImageIO;

public class PineTree extends SuperObject{
    public PineTree(){
        name = "pine-tree";
        try{
            image = ImageIO.read(getClass().getResource("/objects/pine-tree.png"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
