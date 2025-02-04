package org.frank.ai;

import org.frank.entity.Entity;
import org.frank.main.GamePanel;

import java.util.ArrayList;
/**
 * The PathFinder class is responsible for finding a path from a start tile in the world to a goal tile in the world using A* pathfinding algorithm.
 */
public class PathFinder {
    GamePanel gp;
    /**
     * A grid of nodes representing the world map.
     */
    Node[][] nodes;
    /**
     * A list of nodes that are open and need to be evaluated.
     */
    ArrayList<Node> openList = new ArrayList<Node>();
    /**
     * A list of nodes that represent the path from the start node to the goal node.
     */
    public ArrayList<Node> pathList = new ArrayList<Node>();
    /**
     * The start, goal and current node in the world map.
     */
    Node startNode, goalNode, currentNode;
    /**
     * A boolean flag to indicate if the goal node has been reached.
     */
    boolean goalReached = false;
    /**
     * A counter to keep track of the number of steps taken in the pathfinding algorithm. Used to limit the number of steps taken and avoid looping indefinitely. Increase in size if the goal is far or else the path will not be found.
     */
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
    }
    /**
     * Instantiates the nodes array with the maximum number of columns and rows in the world map, so that each tile in the world map has a corresponding node.
     */
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
    /**
     * Resets the nodes in the world map to their initial state. So that the pathfinding algorithm can be run again.
     */
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
    /**
     * Sets the start and goal nodes and initializes the nodes with node cost information,for the pathfinding algorithm. Checks the tiles and the interactive tiles for obstacles.
     *
     * @param startCol the column of the start node
     * @param startRow the row of the start node
     * @param goalCol  the column of the goal node
     * @param goalRow  the row of the goal node
     */
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        resetNodes();

        //set start and goal node
        startNode = nodes[startCol][startRow];
        currentNode = startNode;
        goalNode = nodes[goalCol][goalRow];
        //add first node to open list
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

    /**
     * Searches for a path from the start node to the goal node.
     *
     * @return true if the goal is reached, false otherwise
     */
    public boolean search(){
        //step counter is limited by fps, so if the goal is far is requires more steps to iterate through
        while(!goalReached && step <900){
           //store current node position
            int col = currentNode.col;
            int row = currentNode.row;

            //mark  the current node as checked, so that it is not checked again
            currentNode.checked = true;
            openList.remove(currentNode);

            openSouroundingNodes(col, row);

            /// find the index of node with the lowest cost
            int bestNodeIndex = getBestNodeIndex();

            //if no node in open list end
            if(openList.size() ==0){
                break;
            }

            //add node to open list to be evaluated.
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }
    /**
     * Returns the index of the node with the lowest F cost in the open list.
     *
     * @return the index of the node with the lowest F cost in the open list
     */
    private int getBestNodeIndex() {
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
        return bestNodeIndex;
    }
    /**
     * Opens the surrounding nodes of the current node, so that they can be evaluated in the pathfinding algorithm.
     *
     * @param col the column of the current node
     * @param row the row of the current node
     */
    private void openSouroundingNodes(int col, int row) {
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

    }
    /**
     * Traces back the path from the goal node to the start node and stores the path in the pathList variable.
     */
    private void trackThePath() {
        Node current = goalNode;
        while (current != startNode){
            pathList.add(0,current);
            current = current.parent;
        }
    }
    /**
     * Opens the node if it is not solid, checked, or open. So that it can be evaluated in the pathfinding algorithm.
     *
     * @param node the node to be opened
     */
    private void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
    /**
     * Calculates the cost of the node based on the distance from the start node and the goal node.
     *
     * @param node the node for which the cost needs to be calculated
     */
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

}
