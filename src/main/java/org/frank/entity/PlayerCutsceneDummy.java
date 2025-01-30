package org.frank.entity;

import org.frank.main.GamePanel;

public class PlayerCutsceneDummy extends Entity{
    public static  final String npcName = "PlayerCutsceneDummy";

    public PlayerCutsceneDummy(GamePanel gp) {
        super(gp);
        name = npcName;
        getPlayerImage();
    }

    public void getPlayerImage() {
        down1 = setup("/player/walking/down1.png");
        down2 = setup("/player/walking/down2.png");
        down3 = setup("/player/walking/down3.png");
        down4 = setup("/player/walking/down4.png");
        down5 = setup("/player/walking/down5.png");
        down6 = setup("/player/walking/down6.png");
        down7 = setup("/player/walking/down7.png");
        down8 = setup("/player/walking/down8.png");
        up1 = setup("/player/walking/up1.png");
        up2 = setup("/player/walking/up2.png");
        up3 = setup("/player/walking/up3.png");
        up4 = setup("/player/walking/up4.png");
        up5 = setup("/player/walking/up5.png");
        up6 = setup("/player/walking/up6.png");
        up7 = setup("/player/walking/up7.png");
        up8 = setup("/player/walking/up8.png");
        left1 = setup("/player/walking/left1.png");
        left2 = setup("/player/walking/left2.png");
        left3 = setup("/player/walking/left3.png");
        left4 = setup("/player/walking/left4.png");
        left5 = setup("/player/walking/left5.png");
        left6 = setup("/player/walking/left6.png");
        left7 = setup("/player/walking/left7.png");
        left8 = setup("/player/walking/left8.png");
        right1 = setup("/player/walking/right1.png");
        right2 = setup("/player/walking/right2.png");
        right3 = setup("/player/walking/right3.png");
        right4 = setup("/player/walking/right4.png");
        right5 = setup("/player/walking/right5.png");
        right6 = setup("/player/walking/right6.png");
        right7 = setup("/player/walking/right7.png");
        right8 = setup("/player/walking/right8.png");
    }
}
