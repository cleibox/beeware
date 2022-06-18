/**
 * PathFinder
 * Desc: path finding algo
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package ai;

import java.util.ArrayList;

// IMPORTS (classes)
import main.GamePanel;
import sprites.Sprites;

public class PathFinder {
    GamePanel game;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    
    Node startNode, goalNode, currentNode;
    boolean goalReached = false; // target
    
    int step = 0; // to get to the target

    public PathFinder(GamePanel game){
        this.game = game;
        instantiateNodes();
    }

    public void instantiateNodes(){ // create a node for every tile on the map
        node = new Node[game.maxMapCol][game.maxMapRow];
        
        int col = 0;
        int row = 0;

        while(col < game.maxMapCol && row < game.maxMapRow){
            node[col][row] = new Node(col, row);
        }
        col++;
        if (col == game.maxMapCol){
            col = 0;
            row++;
        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;

        while(col < game.maxMapCol && row < game.maxMapRow){
            // reset open, checked and solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if (col == game.maxMapCol) {
                col = 0;
                row++;
            }
        }
        // reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Sprites sprites){
        resetNodes();

        // set start and goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < game.maxMapCol && row < game.maxMapRow) {
            // set solid node

            // check tiles
            int tileNum = game.tileManager.mapTileNum[col][row]; // MHM
            if (game.tileManager.tile[tileNum].collision == true){
                node[col][row].solid = true;
            }

            // set cost
            getCost(node[col][row]);
            col++;
            if(col == game.maxMapCol){
                col = 0;
                row++;
            }  
        }
    }

    public void getCost(Node node){
        // G cost
        int xDistance = Math.abs(node.col - startNode.col);   
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H cost
        xDistance = Math.abs(node.col - startNode.col);   
        yDistance = Math.abs(node.row - startNode.row);
        node.hCost = xDistance + yDistance;

        // F cost
        node.fCost = node.gCost + node.hCost;
    }
    /* 
    public boolean search(){
        while (goalReached == false && step < 500){
            int col = currentNode.col;
            int row = currentNode.row;

            // check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            // open the up node
            if (row - 1 >= 0){
                openNode(node[col][row-1]);
            }
            // open the left node
            if (col - 1 >= 0){
                openNode(node[col - 1][row]);
            }
            // open the down node
            if (row + 1 < game.maxMapRow){
                openNode(node[col][row+1]);
            }
            // open the right node
            if (col - 1 < game.maxMapCol){
                openNode(node[col+1][row]);
            }

            // find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i =0; i < openList.size(); i ++){
                // check if this node's f cost is better
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // if F cost is equal, check the G cost
                else if (openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }

            // if there is no node in the openList, end the loop
            if (openList.size() == 0){
                break;
            }

            // After the loop, openList[bestNodeIndex] is the next step (= currentNode)
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }

        return goalReached;
    }*/

    public void openNode (Node node){
        if (node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node current = goalNode;

        while (current != startNode){
            pathList.add(0, current);
            current = current.parent;

        }
    }


}
