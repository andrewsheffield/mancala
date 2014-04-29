
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
public class GoalShape extends JPanel {
    
    private int bucketWidth;
    private int bucketHeight;
    boolean toggle = true;
    
    
    public GoalShape(int w, int h) {
        bucketWidth = w;
        bucketHeight = h;
        setPreferredSize(new Dimension(w, h));
        this.setLocation(0, 0);
    }
    
    @Override
    public void paint(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        
        final Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, bucketWidth, bucketHeight);
        
        if (toggle) {
            g2.setPaint(Color.blue);
        } else {
            g2.setPaint(Color.red);
        }
        
        g2.fill(circle);
        g2.draw(circle);
    }
    
    public void toggle() {
        toggle = !toggle;
    }

}
