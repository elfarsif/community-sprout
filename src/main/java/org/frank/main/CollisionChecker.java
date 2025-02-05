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
                tile1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tile2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tile2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tile2 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tile2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tile1].collision ||
                        gp.tileManager.tile[tile2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){

        int index = 999;
        for (int i = 0; i < gp.objects[1].length; i++) {
            if (gp.objects[gp.currentMap][i]!=null){
                //Get entity solid area position
                entity.solidArea.x = entity.solidArea.x + entity.worldX;
                entity.solidArea.y = entity.solidArea.y + entity.worldY;

                //get object solid area position
                gp.objects[gp.currentMap][i].solidArea.x = gp.objects[gp.currentMap][i].worldX + gp.objects[gp.currentMap][i].solidArea.x;
                gp.objects[gp.currentMap][i].solidArea.y = gp.objects[gp.currentMap][i].worldY + gp.objects[gp.currentMap][i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[gp.currentMap][i].solidArea)){
                            if (gp.objects[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[gp.currentMap][i].solidArea)){
                            if (gp.objects[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[gp.currentMap][i].solidArea)){
                            if (gp.objects[gp.currentMap][i].collision){
                                entity.collisionOn = true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[gp.currentMap][i].solidArea)){
                            if (gp.objects[gp.currentMap][i].collision){
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

                gp.objects[gp.currentMap][i].solidArea.x = gp.objects[gp.currentMap][i].solidAreaDefaultX;
                gp.objects[gp.currentMap][i].solidArea.y = gp.objects[gp.currentMap][i].solidAreaDefaultY;

            }
        }
        return index;
    }

    /**
     * Check collision between entity and a list of target entities, like nps, IteractiveTiles, objects etc
     * @param entity the entity that is colliding
     * @param target the list of target entities that the entity is wants to check collision with
     * @return the index of the target entity that the entity is colliding with, 999 if no collision
     */
    public int checkEntity(Entity entity,Entity[][] target){
        int index = 999;
        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i]!=null){
                //Get entity solid area position
                entity.solidArea.x = entity.solidArea.x + entity.worldX;
                entity.solidArea.y = entity.solidArea.y + entity.worldY;

                //get object solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

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

                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){
                    if (target[gp.currentMap][i] != entity){
                        updateCollisionStatusForWalkingOverInteractiveTiles(entity, target, i);
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;

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
    /**
     * Changes the collisionOn status of the entity to false if the entity is colliding with is walkOverable, used for interactive tiles
     *
     * @param entity the entity hitting the interactive tile
     * @param target the list of target interactive tile entities being hit
     * @param i the index of the specific target entity
     */
    private void updateCollisionStatusForWalkingOverInteractiveTiles(Entity entity, Entity[][] target, int i) {
        if (target[gp.currentMap][i].walkOverable){
            entity.collisionOn = false;
        }else{
            entity.collisionOn = true;
        }
    }
}
