
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
public class GoalShape extends JPanel{
    
    private final int WIDTH;
    private final int HEIGHT;
    
    
    public GoalShape(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        setPreferredSize(new Dimension(w, h));
        this.setLocation(0, 0);
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, WIDTH, HEIGHT);
        g2.setPaint(Color.red);
        g2.fill(circle);

        g2.draw(circle);
    }
}
