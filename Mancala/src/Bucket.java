import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
/**
 *
 * @author sheff
 */
public class Bucket extends JPanel {
    
    private int bucketWidth;
    private int bucketHeight;
    private boolean selected = false;
    private int numOfMarbles = 0;
    
    
    public Bucket(int w, int h, int marbles) {
        bucketWidth = w;
        bucketHeight = h;
        setPreferredSize(new Dimension(w, h));
        this.setLocation(0, 0);
        numOfMarbles = marbles;
    }
    
    @Override
    public void paint(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        
        final Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, bucketWidth, bucketHeight);
        
        if (selected) {
            g2.setPaint(Color.blue);
        } else {
            g2.setPaint(Color.red);
        }
        
        g2.fill(circle);
        g2.draw(circle);
        
        g2.setPaint(Color.BLACK);
        final Ellipse2D.Double marble = new Ellipse2D.Double(0, 20, 20, 20);
        for (int i = 0; i < numOfMarbles; i++) {
            marble.x = (i * marble.width) + 20;
            g2.fill(marble);
            g2.draw(marble);
        }

        
    }
    
    public void select() {
        selected = !selected;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void resetSelection() {
        selected = false;
    }
}
