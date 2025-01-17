package org.frank.tile;

import org.frank.main.GamePanel;
import org.frank.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public int[][] map;
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[50];
        mapTileNum = new int[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldMap.txt",0);
        loadMap("/maps/worldMap2.txt",1);

    }

    private void getTileImage() {

        setup(0, "/tiles/grass-plain.png", false);
        setup(1, "/tiles/dirt.png", true);
        setup(2, "/tiles/water-plain.png", true);
        setup(3, "/tiles/top-border.png", true);
        setup(4, "/tiles/left-top-border.png", true);
        setup(5, "/tiles/left-border.png", true);
        setup(6, "/tiles/border-convex-bottom-left.png", true);
        setup(7, "/tiles/right-border.png", true);
        setup(8, "/tiles/border-convex-bottom-right.png", true);
        setup(9, "/tiles/border-convex-bottom-left-water.png", true);
        setup(10, "/tiles/border-convex-bottom-right-water.png", true);
        setup(11, "/tiles/border-right-water-1.png", true);
        setup(12, "/tiles/border-left-water-1.png", true);
        setup(13, "/tiles/border-convex-bottom-left-water-1.png", true);
        setup(14, "/tiles/border-concave-right-bottom-water-1.png", true);
        setup(15, "/tiles/border-bottom-water-1.png", true);
        setup(16, "/tiles/border-top-left-water-intersection.png", true);
        setup(17, "/tiles/border-concave-bottom-left-water-1.png", true);
        setup(18, "/tiles/border-convex-top-left-water-1.png", true);
        setup(19, "/tiles/border-top-water-1.png", true);
        setup(20, "/tiles/border-convex-top-right-water-1.png", true);
        setup(21, "/tiles/border-concave-bottom-left-water-intersection.png", true);
        setup(22, "/tiles/border-concave-top-right.png", true);
        setup(23, "/tiles/border-right.png", true);
        setup(24, "/tiles/border-concave-bottom-right.png", true);
        setup(25, "/tiles/border-bottom.png", true);
        setup(26, "/tiles/border-concave-left-bottom.png", true);
        setup(27, "/tiles/house/house-0-0.png", true);
        setup(28, "/tiles/house/house-0-1.png", true);
        setup(29, "/tiles/house/house-0-2.png", true);
        setup(30, "/tiles/house/house-0-3.png", true);
        setup(31, "/tiles/house/house-1-0.png", true);
        setup(32, "/tiles/house/house-1-1.png", true);
        setup(33, "/tiles/house/house-1-2.png", true);
        setup(34, "/tiles/house/house-1-3.png", true);
        setup(35, "/tiles/house/house-2-0.png", true);
        setup(36, "/tiles/house/house-2-1.png", true);
        setup(37, "/tiles/house/house-2-2.png", true);
        setup(38, "/tiles/house/house-2-3.png", true);
        setup(39, "/tiles/house/house-3-0.png", true);
        setup(40, "/tiles/house/house-3-1.png", true);
        setup(41, "/tiles/house/house-3-2.png", true);
        setup(42, "/tiles/house/house-3-3.png", true);
        setup(43, "/tiles/house/house-4-0.png", true);
        setup(44, "/tiles/house/house-4-1.png", true);
        setup(45, "/tiles/house/house-4-2.png", true);
        setup(46, "/tiles/house/house-4-3.png", true);
    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();

                while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow && line != null) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }

                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }

            }
            br.close();

        } catch (Exception e) {
            throw new RuntimeException("Map Loading error " + e);
        }
    }

    public void draw(Graphics2D g2d) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[gamePanel.currentMap][worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            //check if tiles is in the screen + 1 tile
            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2d.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
