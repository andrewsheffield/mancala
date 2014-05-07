
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author sheff
 */
class SquareUI implements MancalaUI {

    @Override
    public RectangularShape getShape() {
        return new Rectangle2D.Double();
    }

    @Override
    public Color getPrimary() {
        return Color.DARK_GRAY;
    }

    @Override
    public Color getSecondary() {
        return Color.PINK;
    }


    
}
