
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView {
    
    public final JPanel board;
    public final double SCALE = 1.5;
    private ArrayList<GoalShape> buckets = new ArrayList<>();
    
    public MainView(/*datatype model*/) {
        
        board = new JPanel();
        board.setPreferredSize(new Dimension((int)(800 * SCALE), (int)(200 * SCALE)));
        setupBoard(board);
        
        JFrame frame =  new JFrame();
        frame.add(board);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setupBoard(final JPanel board) {
        
        board.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        for (int i = 0; i < 14; i++) {
            
            JPanel bucket = new JPanel();
            bucket.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(100 * SCALE)));
            final GoalShape bucketCircle;
            
            
            if (i == 0) {
                c.gridheight = 2;
                c.gridx = 0;
                c.gridy = 0;
                bucket.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(200 * SCALE)));
                bucketCircle = new GoalShape((int)(100 * SCALE), (int)(200 * SCALE));
            } else if(i == 13) {
                c.gridheight = 2;
                c.gridy = 0;
                c.gridx = 7;
                bucket.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(200 * SCALE)));
                bucket.setBackground(Color.green);
                bucketCircle = new GoalShape((int)(100 * SCALE), (int)(200 * SCALE));
            } else if (i > 6) {
                c.gridheight = 1;
                c.gridx = i - 6;
                c.gridy = 1 ;
                bucketCircle = new GoalShape((int)(100 * SCALE), (int)(100 * SCALE));
            } else {
                c.gridheight = 1;
                c.gridx = i;
                c.gridy = 0;
                bucketCircle = new GoalShape((int)(100 * SCALE), (int)(100 * SCALE));
            }
            
            
            bucketCircle.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    resetBucketToggle();
                    bucketCircle.toggle();
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
            
            bucket.setLayout(new GridLayout());
            bucket.add(bucketCircle);
            board.add(bucket, c);
            buckets.add(bucketCircle);
        }
    }
    
    private void resetBucketToggle() {
        for (GoalShape g : buckets) {
            g.resetToggle();
        }
    }
    
}
