
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView {
    
    public final JPanel board;
    public final double SCALE = 1.0;
    
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

    private void setupBoard(JPanel board) {
        board.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel goal1 = new JPanel();
        goal1.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(200 * SCALE)));
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        c.gridheight = 2;
        goal1.setLayout(new GridLayout());
        goal1.add(new GoalShape(100, 200));
        board.add(goal1, c);
        
        for (int i = 0; i < 12; i++) {
            JPanel bucket = new JPanel();
            bucket.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(100 * SCALE)));
            bucket.setBackground(Color.blue);
            c.gridheight = 1;
            c.gridx = i + 1;
            c.gridy = 0;
            if (i > 5) {
                c.gridx = i - 5;
                c.gridy = 1;
                bucket.setBackground(Color.yellow);
            }
            board.add(bucket, c);
        }
        
        JPanel goal2 = new JPanel();
        goal2.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(200 * SCALE)));
      
        c.gridheight = 2;
        c.gridy = 0;
        c.gridx = 7;
        goal2.setLayout(new GridLayout());
        goal2.add(new GoalShape(100, 200));
        board.add(goal2, c);
    }
    
}
