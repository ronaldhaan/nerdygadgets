package nerdygadgets.algorithm;

import java.util.*;

public class NodeCollection {
    private ArrayList<Node> nodes = new ArrayList<>();
    private int distance = 0;

    /**
     * Initializes a new empty instance of the <code>NodeCollection</code> class.
     */
    public NodeCollection() {}

    /**
     * Initializes a new instance of the <code>NodeCollection</code> class.
     * 
     * @param nodes The node list.
     */
    public NodeCollection(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Adds the node to the list.
     * 
     * @param node , the node that's about to be added.
     */
    public void add(Node node) {
        nodes.add(node);
    }

    /**
     * Gets the size of the list.
     * 
     * @return size of the list.
     */
    public int getSize() {
        return nodes.size();
    }

    /**
     * Gets the list.
     * 
     * @return the list of nodes.
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Gets one node by the index.
     * 
     * @param index , the index of the node.
     * @return the node.
     */
    public Node getNode(int index) {
        return nodes.get(index);
    }

    /**
     * Gets the total distance of the tour
     */ 
    public Integer getDistance(){
        if (distance == 0) {
            int nodeDistance = 0;
            // Loop through our tour's cities
            int nextNodeIndex = 1;
            for (Node currentNode : nodes) {
                Node destinationNode;
                
                if(nextNodeIndex < nodes.size()){
                    destinationNode = nodes.get(nextNodeIndex);
                    nextNodeIndex++;
                }
                else {
                    destinationNode = nodes.get(0);
                }
                // Get the distance between the two cities
                nodeDistance += currentNode.distanceTo(destinationNode);
            }

            distance = nodeDistance;
        }
        return distance;
    }

    @Override
    public String toString() {
        System.out.println("generated distance: " + getDistance());
        StringBuilder builder = new StringBuilder();
        for(Node node : getNodes()) {
            builder.append("|");
            builder.append(node);
        }

        builder.append("|");
        return builder.toString();
    }
}