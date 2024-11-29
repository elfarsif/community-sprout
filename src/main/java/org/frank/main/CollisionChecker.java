package org.frank.main;

import org.frank.entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tile1, tile2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tile1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tile1].collision ||
                        gamePanel.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tile1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tile1].collision ||
                        gamePanel.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tile1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tile1].collision ||
                        gamePanel.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tile1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tile1].collision ||
                        gamePanel.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
