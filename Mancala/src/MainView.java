import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class MainView {
    
    public GameLogicModel model;
    public final JPanel board;
    public final JPanel window;
    public final double SCALE = 1.5;
    private ArrayList<Bucket> buckets = new ArrayList<>();
    private final JLabel currentPlayer = new JLabel();
    
    public MainView() {
        
        board = new JPanel();
        board.setPreferredSize(new Dimension((int)(800 * SCALE), (int)(250 * SCALE)));
        //setupBoard();
        
        window = new JPanel();
        window.setLayout(new BorderLayout());
       
        window.add(board, BorderLayout.CENTER);
        
        JFrame frame =  new JFrame();
        frame.add(window);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setupBoard() {
        board.removeAll();
        board.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        
        
        for (int i = 0; i < 14; i++) {
            final Bucket bucket;
            
            
            if (i == 0) {
                c.gridheight = 2;
                c.gridx = 0;
                c.gridy = 0;
                bucket = new Bucket((int)(100 * SCALE), (int)(200 * SCALE), model.getMancalaB());
                bucket.setName("6");
            } else if(i == 13) {
                c.gridheight = 2;
                c.gridx = 7;
                c.gridy = 0;
                bucket = new Bucket((int)(100 * SCALE), (int)(200 * SCALE), model.getMancalaA());
                bucket.setName("13");
            } else if (i > 6) {
                c.gridheight = 1;
                c.gridx = i - 6;
                c.gridy = 1 ;
                bucket = new Bucket((int)(100 * SCALE), (int)(100 * SCALE), model.getPlayerBPitsA().get(i-7));
                bucket.setName(i + "");
            } else {
                c.gridheight = 1;
                c.gridx = i;
                c.gridy = 0;
                bucket = new Bucket((int)(100 * SCALE), (int)(100 * SCALE), model.getPlayerPitsB().get(i-1));
                bucket.setName((6-i) + "");
            }
            
            
            bucket.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    /*
                    if (bucket.isSelected()) {
                        resetBucketSelections();
                    } else {
                        resetBucketSelections();
                        bucket.select();
                    }
                    */
                    try {
                        model.makeMove(Integer.parseInt(bucket.getName()));
                    } catch (InvalidValue ex) {
                        JOptionPane.showMessageDialog(board, "Not your turn!  Calm yo Tits!");
                    }
                    board.revalidate();
                    board.repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });

            bucket.add(new JLabel("Testing"));
            
            bucket.setUI(new CircleUI());
            
            board.add(bucket, c);
            buckets.add(bucket);
            
            currentPlayer.removeAll();
            if (model.checkTurnPlayerA()) {
                currentPlayer.setText("Player One's Turn");
            } else {
                currentPlayer.setText("Player Two's Turn");
            }
            
            currentPlayer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            c.gridx = 0;
            c.gridy = 3;
            c.ipady = 10;
            board.add(currentPlayer, c);
        }
    }
    
    private void resetBucketSelections() {
        for (Bucket g : buckets) {
            g.resetSelection();
        }
    }
    
    public void runView() {
        setupBoard();
    }

    void setData(GameLogicModel model) {
        this.model = model;
    }
    
}
