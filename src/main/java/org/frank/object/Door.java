package org.frank.object;

import javax.imageio.ImageIO;

public class Door extends SuperObject{
    public Door(){
        name = "door";
        try{
            image = ImageIO.read(getClass().getResource("/objects/door.png"));
        }catch (Exception e){
            throw new RuntimeException("Error loading image Door:"+e);
        }
    }
}
