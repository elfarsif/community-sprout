package org.frank.ai;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

import java.util.ArrayList;

public class PathFinder {

    GamePanel gp;
    Node[][] nodes;
    ArrayList<Node> openList = new ArrayList<Node>();
    public ArrayList<Node> pathList = new ArrayList<Node>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes(){
        nodes = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow ){
            nodes[col][row] = new Node(col, row);

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }


    public void resetNodes(){
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row <gp.maxWorldRow){
            //Reset open
            nodes[col][row].open = false;
            nodes[col][row].checked = false;
            nodes[col][row].solid = false;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

        //Reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity){
        resetNodes();

        //set start and goal node
        startNode = nodes[startCol][startRow];
        currentNode = startNode;
        goalNode = nodes[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow){
            //set solid nodes
            //check tiles
            int tileNum = gp.tileManager.mapTileNum[gp.currentMap][col][row];
            if (gp.tileManager.tile[tileNum].collision){
                nodes[col][row].solid = true;
            }

            //check interactive tiles
            for (int i = 0; i<gp.iTiles[1].length;i++){
                if ( gp.iTiles[gp.currentMap][i]!=null && gp.iTiles[gp.currentMap][i].destructible){
                    int iteractiveTileCol = gp.iTiles[gp.currentMap][i].worldX / gp.tileSize;
                    int iteractiveTileRow = gp.iTiles[gp.currentMap][i].worldY / gp.tileSize;
                    nodes[iteractiveTileCol][iteractiveTileRow].solid = true;
                }
            }

            //set cost
            getCost(nodes[col][row]);


            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }


    }

    private void getCost(Node node) {
        //G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        //H cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        //F cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search(){
        //TODO:BUG search doesnt return true

        while(!goalReached && step <500){
            int col = currentNode.col;
            int row = currentNode.row;

            //check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            // open the "up" node
            if (row -1 >=0){
                openNode(nodes[col][row-1]);
            }
            // open the "down" node
            if (row +1 < gp.maxWorldRow){
                openNode(nodes[col][row+1]);
            }
            // open the "left" node
            if (col -1 >=0){
                openNode(nodes[col-1][row]);
            }
            // open the "right" node
            if (col +1 < gp.maxWorldCol){
                openNode(nodes[col+1][row]);
            }

            /// find best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for (int i =0; i<openList.size();i++){
                //check if this nodes F cost is lower
                if (openList.get(i).fCost < bestNodeFCost){
                    bestNodeFCost = openList.get(i).fCost;
                    bestNodeIndex = i;
                }
                // if F cost is same check g cost
                else if (openList.get(i).fCost == bestNodeFCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }

            //if no node in open list end
            if(openList.size() ==0){
                break;
            }
            //after the loop openlist[bestNodeIndex] is the best node
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    private void trackThePath() {
        Node current = goalNode;
        while (current != startNode){
            pathList.add(0,current);
            current = current.parent;
        }
/*
        for (int i = 0; i<pathList.size();i++){
            System.out.println("Path: "+pathList.get(i).col + " "+pathList.get(i).row);
        }*/
    }

    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

}
