package AI;

import java.util.ArrayList;

import static MAINGAME.Panel.maxGameHeight;
import static MAINGAME.Panel.maxGameWidth;
import MAINGAME.Panel;

public class PathFinder {

    Panel panel ;
    Node [] [] node ;

    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode , goalNode , currentNode ;
    boolean goalReached = false;
    int step = 0;
    public PathFinder(Panel panel) {
        this.panel = panel;
        initNode();
    }
    public void initNode ()
    {
        node = new Node [maxGameWidth] [maxGameHeight];

        int col = 0 ;
        int row = 0 ;
        while (col < maxGameWidth && row < maxGameHeight)
        {
            node[col][row] = new Node(col, row);
            col++;
            if (col == maxGameWidth)
            {
                col = 0;
                row++;
            }
        }
    }

    public void resetNode ()
    {
        int col = 0 ;
        int row = 0 ;
        while (col < maxGameWidth && row < maxGameHeight)
        {
            node [col][row].open = false ;
            node [col] [row].checked = false ;
            node [col] [row].solid = false ;

            col++;
            if (col == maxGameWidth)
            {
                col = 0;
                row++;
            }
        }

        ///// RESET ALL
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNode (int startCol , int startRow , int goalCol , int goalRow)
    {
        resetNode();
        startNode = node[startCol] [startRow] ;
        currentNode = startNode ;
        goalNode = node[goalCol] [goalRow] ;
        openList.add(currentNode);


        int col = 0 ;
        int row = 0 ;
        while (col < maxGameWidth && row < maxGameHeight)
        {
            /// SET SOLID NODE //////
            int tileNumber = panel.tileManager.map1 [col][row] ;

            if (tileNumber != 0 && tileNumber >= 11)
            {
                node [col][row].solid = true ;
            }

            getCost(node[col] [row]) ;

            col++;
            if (col == maxGameWidth)
            {
                col = 0;
                row++;
            }
        }

    }

    public void getCost(Node node) {
        /// G COST /////
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost  = xDistance + yDistance ;
        //// H COST ////
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost  = xDistance + yDistance ;

        /// F COST ///
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search ()
    {
        while (!goalReached && step <= 500)
        {
            int col = currentNode.col;
            int row = currentNode.row;

            /// check status
            currentNode.checked = true ;
            openList.remove(currentNode);

            /// open  the RIGHTNode
            if (col + 1 < maxGameWidth)
            {
                openNode(node[col + 1][row] ) ;
            }
            /// open the LEFT NODE
            if (col - 1 >= 0)
            {
                openNode(node[col - 1][row] ) ;
            }
            //// FIND BEST PATH

            int bestNodeIndex = 0 ;
            int bestNodeFCost = 999 ;

            for (int i = 0 ; i < openList.size(); i++)
            {
                ///// CHECK FOR BEST F COST
                if (openList.get(i).fCost < bestNodeFCost ) {
                    bestNodeIndex = i ;
                    bestNodeFCost = openList.get(i).fCost;
                }
                //// IF F COST IS EQUAL TO G COST
                else if (openList.get(i).fCost == bestNodeFCost ) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost)
                    {
                        bestNodeIndex = i ;
                    }
                }
            }
            /// IF not node in openList we exit the loop
            if (openList.size() == 0)
            {
                break;
            }

            //// After the loop , openList [bestNodeIndex] is the next step = current node
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                    trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    private void trackThePath() {
        Node current = goalNode;

        while (current != startNode)
        {
            pathList.add(0,current);
            current = current.parent;
        }
    }

    private void openNode(Node node) {

        if (node.open == false && node.checked == false && node.solid == false )
        {
            node.open = true ;
            node.parent = currentNode ;
            openList.add(node) ;
        }
    }
}
