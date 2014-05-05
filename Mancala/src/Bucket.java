import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
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
    RectangularShape shape = new Ellipse2D.Double();
    Color primary = Color.RED;
    Color secondary = Color.BLUE;
    
    
    public Bucket(int w, int h, int marbles) {
        bucketWidth = w;
        bucketHeight = h;
        setPreferredSize(new Dimension(w, h));
        this.setLocation(0, 0);
        numOfMarbles = marbles;
    }
    
    public void setUI(MancalaUI m) {
        shape = m.getShape();
        primary = m.getPrimary();
        secondary = m.getSecondary();
    }
    
    @Override
    public void paint(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        
        shape.setFrame(0, 0, bucketWidth, bucketHeight);
        
        if (selected) {
            g2.setPaint(secondary);
        } else {
            g2.setPaint(primary);
        }
        
        g2.fill(shape);
        g2.draw(shape);
        
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
