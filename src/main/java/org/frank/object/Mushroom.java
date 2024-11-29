package org.frank.object;

import javax.imageio.ImageIO;

public class Mushroom extends SuperObject{
    public Mushroom(){
        name = "mushroom";
        try{
            image = ImageIO.read(getClass().getResource("/objects/mushroom.png"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
