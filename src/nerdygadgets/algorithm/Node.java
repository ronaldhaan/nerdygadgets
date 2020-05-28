/*
 * node.java
 * Models a node
 */

package nerdygadgets.algorithm;

public class Node {
    private int x;
    private int y;
    private String name;

    public Node(int x, int y) {
        this(x, y, "");
    }

    // Constructs a node at chosen x, y location
    public Node(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    // Gets node's x coordinate
    public int getX(){
        return this.x;
    }

    // Gets node's y coordinate
    public int getY(){
        return this.y;
    }

    // Gets node's name
    public String getName() {
        return this.name;
    }

    // Gets the distance to given node
    public double distanceTo(Node node){
        int xDistance = Math.abs(getX() - node.getX());
        int yDistance = Math.abs(getY() - node.getY());

        return getDistance(xDistance, yDistance);
    }

    public double getDistance(double xDistance, double yDistance) {
        return Math.sqrt( Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    @Override
    public String toString() {
        return getName() +"(" +getX()+", "+getY()+")";
    }

    @Override
    public boolean equals(Object obj) {
        Node node = (Node)obj;
        return  node != null 
                && this.getX() == node.getX() 
                && this.getY() == node.getY();
    }
}
