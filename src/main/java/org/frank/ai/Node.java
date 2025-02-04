package org.frank.ai;

/**
 * The Node class represents a node in a pathfinding algorithm.
 * It contains information about its position, costs, and state.
 */
public class Node {
    /**
     * The parent node of this node. Used to trace back the path once the goal is reached.
     */
    Node parent;
    /**
     * The column position of the node. Which can represents the column of the Node in the world map
     */
    public int col;
    /**
     * The row position of the node. Which can represents the row of the Node in the world map
     */
    public int row;
    /**
     * The cost to move from the starting node to this node.
     */
    int gCost;
    /**
     * The estimated cost to move from this node to the goal node.
     */
    int hCost;
    /**
     * The total cost of the node. fCost = gCost + hCost
     */
    int fCost;
    /**
     * A boolean flag to indicate if the node is solid or not. Can be used to set obstacles/borders in the world map.
     */
    boolean solid;
    /**
     * A boolean flag to indicate if the node is open or not. Used in the pathfinding algorithm to keep track of the nodes that are being evaluated.
     */
    boolean open;
    /**
     * A boolean flag to indicate if the node has been checked or not. Used in the pathfinding algorithm to keep track of the nodes that have been evaluated.
     */
    boolean checked;

    /**
     * Constructs a Node object with the specified column and row.
     *
     * @param col the column position of the node/col on the map
     * @param row the row position of the node/row on the map
     */
    public Node(int col, int row){
        this.col = col;
        this.row = row;
    }
}
