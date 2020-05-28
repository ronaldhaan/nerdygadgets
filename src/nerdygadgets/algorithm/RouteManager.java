package nerdygadgets.algorithm;

import org.json.JSONObject;

import nerdygadgets.shared.Utility;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is the base of the algorithm. This class manages the whole process.
 */
public class RouteManager {

    private NodeCollection nodeCollection = new NodeCollection(); 
    private Node startNode;
    private Node endNode;

    public RouteManager() { }

    /**
     * Initializes a new instance of the RouteManager class.
     * 
     * @param nodes
     */
    public RouteManager(NodeCollection nodes) {
        this.nodeCollection = nodes;
    }

    /**
     * Initializes a new instance of the RouteManager class.
     * 
     * @param coordinates
     */
    public RouteManager(int[][] coordinates) {
        for(int[] coordinate : coordinates) {
            nodeCollection.add(new Node(coordinate[0], coordinate[1]));
        }
    }

    /**
     * Initializes a new instance of the RouteManager class.
     * @param nodes
     * @param startNode
     */
    public RouteManager(NodeCollection nodes, Node startNode) {
        this(nodes, startNode, startNode);
    }
    /**
     * Initializes a new instance of the RouteManager class.
     * @param nodes
     * @param startNode
     */
    public RouteManager(NodeCollection nodes, Node startNode, Node endNode) {
        this.nodeCollection = nodes;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    /**
     * Initializes a new instance of the RouteManager class.
     * @param coordinates
     * @param startCoordinates
     */
    public RouteManager(int[][] coordinates, int[] startCoordinates) {
        for(int[] coordinate : coordinates) {
            nodeCollection.add(new Node(coordinate[0], coordinate[1]));
        }

        this.startNode = new Node(startCoordinates[0], startCoordinates[1]);
    }

    /**
     * Sets the start node based on the coordinates.
     * 
     * @param coordinate
     * @return true, successfull. false otherwise
     */
    public boolean setStartNode(int[] coordinate) {
        return setStartNode(new Node(coordinate[0], coordinate[1]));
    }

    /**
     * Sets the start node 
     * @param node
     * @return true, successfull. false otherwise
     */
    public boolean setStartNode(Node node) {
        if(nodeCollection.getNodes().contains(node)) {
            this.startNode = node;
            
            return true;
        }

        return false;
    }

    /**
     * @return the node collection
     */
    public NodeCollection getNodeCollection() {
        return nodeCollection;
    }   
    
    /**
     * @return the node collection
     */
    public ArrayList<Node> getNodes() {
        return nodeCollection.getNodes();
    }   

    public void addNode(Node node) {
        nodeCollection.add(node);
    }

    /**
     * Generates the shortest path.
     */
    public void generate() {
        System.out.println("start distance: " + nodeCollection.getDistance());

        // Set the start node as the first object in the list.
        Collections.sort(nodeCollection.getNodes(), new NodeComparator(startNode));
        
        //If start node is not equal to the last node in the list.
        if(!startNode.equals(nodeCollection.getNode(nodeCollection.getSize()-1)) 
        // And the startnode is not equal to the endnode.
            && !startNode.equals(endNode)) {
            // Add the startnode as the endnode to the array.
            nodeCollection.add(startNode);
        }

        createRoute(startNode);

        System.out.println(nodeCollection);
    }

    /**
     * Gets the amount of times a node exists within the list.
     * 
     * @param node The node to check
     * @return The amount of times a node exists within the list.
     */
    public int occurenceOf(Node node) {
        int count = 0;
        for (Node n : getNodes()) {
            if (n.equals(node)) {
                count++;
            }
        }

        return count;
    }

    public static Node getNodeByPostalCodeAndPlace(String postalCode, String place) {
        JSONObject coordinates = Postal.getCoordinatesByPostalAndPlace(postalCode, place);
        double doubleX = (double) coordinates.get("x");
        double doubleY = (double) coordinates.get("y");
        int x = (int) doubleX;
        int y = (int) doubleY;

        // Create and add our cities
        return new Node(x, y, place);
    }

    // private methods 

    /**
     * creates the best available route based on the start node.
     * @param startNode
     */
    private void createRoute(Node startNode) {
        createRoute(new ArrayList<Node>(), startNode, 0);
    }

    /**
     * Creates a list with the correct route to follow.
     * 
     * @param sortedList The list that will be returned and filled with the correct route. 
     * @param currentNode The currentNode find the neighbour of.
     * @param index The index of the nodeCollection size.
     * @return The list that will be returned filled with the correct route.
     */
    private void createRoute(ArrayList<Node> sortedList, Node currentNode, int index) {        
        if(index >= nodeCollection.getSize() || currentNode == null) {
            sortedList.add(endNode);
            nodeCollection = new NodeCollection(sortedList);
            return;
        }

        Node neighbour = null;

        boolean visited = sortedList.contains(currentNode);
        if(!visited) {
            int shortestDistance = Integer.MAX_VALUE;    

            // Find the node closest to the current node.
            for(int nodeIndex = 0; nodeIndex < nodeCollection.getSize(); nodeIndex++) {
                Node node = nodeCollection.getNodes().get(nodeIndex);

                if(!currentNode.equals(node) && !sortedList.contains(node)) {
                    int distance = (int)currentNode.distanceTo(node);

                    if(distance < shortestDistance) {
                        shortestDistance = distance;
                        neighbour = node;
                    }
                }                
            }
        }

        if(!visited || currentNode.equals(startNode)) {            
            sortedList.add(currentNode);
        }

        createRoute(sortedList, neighbour, index+1);
    }

    /**
     * Gets the nodes as a url path. 
     * All the node names with a '/' in between
     * and spaces become '+'.
     * @return the nodes list as a url path.
     */
	public String getNodesAsPath() {
		StringBuilder pathBuilder = new StringBuilder();
        for (Node node : getNodes()) {
            System.out.println(node.getName());
            pathBuilder.append(node.getName()).append("/");
        }

        String path = pathBuilder.toString();        
        return path.replace(" ", "+");
    }
    
    public void openRouteInMaps() {
        try {            
            String url = Utility.GOOGLE_MAPS_URL + getNodesAsPath();
            Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome /incognito " + url});
        } catch(Exception e) {
            Utility.handleUnexpectedException(e);
        }
    }
}