package nerdygadgets.algorithm;

import java.util.Comparator;

/**
 * Sorts the node list and sets the StartNode to the first place.
 */
public class NodeComparator implements Comparator<Node> {
    private Node startNode;

    public NodeComparator(Node startNode) {
        this.startNode = startNode;
    }

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.equals(startNode)) // update to make it stable
            return -1;
        if (o2.equals(startNode)) 
            return 0;
        return 1;
    }
}