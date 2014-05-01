import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
public class VertJLabel extends JComponent {
    
    private String label;
    
    public VertJLabel(String s) {
        this.label = s;
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        g2.rotate(Math.toRadians(270));
        g2.drawString(label, 0, 100);
    }
}
