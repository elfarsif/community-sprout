package org.frank.object;

import org.frank.entity.Projectile;
import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Fireball extends Projectile {

    GamePanel gp;

    public Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "fireball";
        speed = 9;
        maxLife = 80;
        currentLife = maxLife;
        attack = 1;
        projectileUseCost = 1;
        alive = false;
        getImage();
    }

    private void getImage() {
        up1 = setup("/objects/mushroom.png");
        up2 = setup("/objects/mushroom.png");
        down1 = setup("/objects/mushroom.png");
        down2 = setup("/objects/mushroom.png");
        left1 = setup("/objects/mushroom.png");
        left2 = setup("/objects/mushroom.png");
        right1 = setup("/objects/mushroom.png");
        right2 = setup("/objects/mushroom.png");
    }

    @Override
    public BufferedImage setup(String filePath){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filePath));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image :" + e);
        }
        return image;
    }

}
