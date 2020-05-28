package nerdygadgets.algorithm;

//import com.teamdev.jxbrowser.chromium.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SaPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private NodeCollection nodeCollection;
    
    public SaPanel(ArrayList<Node> nodeList) {
        // add(new JLabel("Total distance: " + best.getDistance()));
        this.nodeCollection = new NodeCollection(nodeList);
    }

    public SaPanel(NodeCollection collection) {
        this.nodeCollection = collection;
    }

    @Override
    protected void paintComponent(Graphics g) {
        ArrayList<Node> nodes = nodeCollection.getNodes();
        for(int i = 0; i < nodes.size(); i++) {
            Node Node1 = (Node)nodes.get(i);           
            g.setColor(Color.RED);
            g.fillOval(Node1.getX() - 5, Node1.getY() -5, 10, 10);

            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(i+1), Node1.getX() - 5, Node1.getY() -5);

            try {
                Node Node2 = nodes.get(i+1);  
                g.setColor(Color.BLUE);
                g.drawLine(Node1.getX(), Node1.getY(), Node2.getX(), Node2.getY());
            } catch(Exception e) {
                break;
            }
        }
    }
}