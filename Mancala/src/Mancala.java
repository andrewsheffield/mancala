
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.DynAnyPackage.InvalidValue;



/**
 *
 * @author sheff
 */
public class Mancala {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainView v = new MainView();
        GameLogicModel model = null;
        try {
             model = new GameLogicModel(3, v);
        } catch (InvalidValue ex) {
            Logger.getLogger(Mancala.class.getName()).log(Level.SEVERE, null, ex);
        }
        v.setData(model);
        
    }
}
