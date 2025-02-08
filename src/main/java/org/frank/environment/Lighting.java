package org.frank.environment;

import org.frank.main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
    int lightedCircleSize;

    public Lighting(GamePanel gp, int lightedCircleSize){
        this.gp = gp;
        this.lightedCircleSize = lightedCircleSize;
        this.darknessFilter = createDarknessCircle(gp, lightedCircleSize);
    }

    private BufferedImage createDarknessCircle(GamePanel gp, int lightedCircleSize) {
        //Create a buffered image
        darknessFilter = new BufferedImage(gp.getWidth(), gp.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) darknessFilter.getGraphics();

        //Create a screen area
        Area screenArea = new Area(new Rectangle2D.Double(0,0, gp.screenWidth, gp.screenHeight));

        //get the center of the lighting circle
        int centerX = gp.player.screenX + gp.tileSize/2;
        int centerY = gp.player.screenY + gp.tileSize/2;

        //get the top left corner of the square that the circle is in
        double topLeftXofSquareOfCircle = centerX - lightedCircleSize /2;
        double topLeftYofSquareOfCircle = centerY - lightedCircleSize /2;

        // Creat a light circle shape
        Shape circleShape = new Ellipse2D.Double(topLeftXofSquareOfCircle, topLeftYofSquareOfCircle, lightedCircleSize, lightedCircleSize);

        //Create a lighting area
        Area lightArea = new Area(circleShape);

        //Subtract the light area from the screen area
        screenArea.subtract(lightArea);

        //Create a radial gradient
        RadialGradientPaint radialGradientPaint = createLightingGradient(centerX, centerY, lightedCircleSize);
        //set data on graphics
        g2d.setPaint(radialGradientPaint);
        //draw light circle
        g2d.fill(lightArea);

        g2d.setColor(new Color(0,0,0,0.95f));

        //Draw the screen rectangle without the light circle area
        g2d.fill(screenArea);

        g2d.dispose();

        return darknessFilter;
    }

    private RadialGradientPaint createLightingGradient(int centerX, int centerY, int lightedCircleSize){
        //Create a gradation effect
        Color[] colors = new Color[12];
        //distance between center of the circle and the edge of the circle of the next gradient
        float[] fractions = new float[12];

        colors[0] = new Color(0,0,0,0.1f);
        colors[1] = new Color(0,0,0,0.42f);
        colors[2] = new Color(0,0,0,0.52f);
        colors[3] = new Color(0,0,0,0.61f);
        colors[4] = new Color(0,0,0,0.69f);
        colors[5] = new Color(0,0,0,0.76f);
        colors[6] = new Color(0,0,0,0.82f);
        colors[7] = new Color(0,0,0,0.87f);
        colors[8] = new Color(0,0,0,0.91f);
        colors[9] = new Color(0,0,0,0.94f);
        colors[10] = new Color(0,0,0,0.96f);
        colors[11] = new Color(0,0,0,0.98f);

        fractions[0] = 0.0f;
        fractions[1] = 0.4f;
        fractions[2] = 0.5f;
        fractions[3] = 0.6f;
        fractions[4] = 0.65f;
        fractions[5] = 0.7f;
        fractions[6] = 0.75f;
        fractions[7] = 0.8f;
        fractions[8] = 0.85f;
        fractions[9] = 0.9f;
        fractions[10] = 0.95f;
        fractions[11] = 1.0f;

        return new RadialGradientPaint(centerX, centerY, lightedCircleSize /2, fractions, colors);
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(darknessFilter, 0, 0, null);
    }
}
