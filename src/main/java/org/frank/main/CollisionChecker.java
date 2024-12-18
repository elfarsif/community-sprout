package org.frank.main;

import org.frank.entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tile1, tile2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tile2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){

        int index = 999;
        for (int i = 0; i < gp.objects.length; i++) {
            if (gp.objects[i]!=null){
                //Get entity solid area position
                entity.solidArea.x = entity.solidArea.x + entity.worldX;
                entity.solidArea.y = entity.solidArea.y + entity.worldY;

                //get object solid area position
                gp.objects[i].solidArea.x = gp.objects[i].worldX + gp.objects[i].solidArea.x;
                gp.objects[i].solidArea.y = gp.objects[i].worldY + gp.objects[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if (gp.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if (gp.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if (gp.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if (gp.objects[i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gp.objects[i].solidArea.x = gp.objects[i].solidAreaDefaultX;
                gp.objects[i].solidArea.y = gp.objects[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    public int checkEntity(Entity entity,Entity[] target){
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i]!=null){
                //Get entity solid area position
                entity.solidArea.x = entity.solidArea.x + entity.worldX;
                entity.solidArea.y = entity.solidArea.y + entity.worldY;

                //get object solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(target[i].solidArea)){
                    if (target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;

        //Get entity solid area position
        entity.solidArea.x = entity.solidArea.x + entity.worldX;
        entity.solidArea.y = entity.solidArea.y + entity.worldY;

        //get object solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)){
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
