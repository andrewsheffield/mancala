
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author sheff
 */
public class CircleUI implements MancalaUI {

    @Override
    public RectangularShape getShape() {
        return new Ellipse2D.Double();
    }

    @Override
    public Color getPrimary() {
        return Color.RED;
    }

    @Override
    public Color getSecondary() {
        return Color.BLUE;
    }
    
}
