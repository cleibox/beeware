/**
 * Node
 * Desc: path finding algo
 * @author Cynthia L & Phoebe Y ICS4U
 * @version 1.2 Jun 2022
 */

package ai;

public class Node {
    Node parent;
    public int col;
    public int row;
    
    int gCost;
    int hCost;
    int fCost;

    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int rpw) {
        this.col = col;
        this.row = row;
    }
}
