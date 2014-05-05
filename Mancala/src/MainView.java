
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView {
    
    public final JPanel board;
    public final double SCALE = 1.5;
    
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
        
        
        JPanel goal1 = new JPanel();
        goal1.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(200 * SCALE)));
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        c.gridheight = 2;
        goal1.setLayout(new GridLayout());
        
        final GoalShape goalCircle1 = new GoalShape((int)(100 * SCALE), (int)(200 * SCALE));
        goalCircle1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                goalCircle1.toggle();
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
        
        goal1.add(goalCircle1);
        board.add(goal1, c);
        
        for (int i = 0; i < 12; i++) {
            JPanel bucket = new JPanel();
            bucket.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(100 * SCALE)));
            c.gridheight = 1;
            c.gridx = i + 1;
            c.gridy = 0;
            bucket.setLayout(new GridLayout());
            
            final GoalShape bucketCircle = new GoalShape((int)(100 * SCALE), (int)(100 * SCALE));
                bucketCircle.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
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
            
            bucket.add(bucketCircle);
            if (i > 5) {
                c.gridx = i - 5;
                c.gridy = 1;
                
            }
            board.add(bucket, c);
        }
        
        JPanel goal2 = new JPanel();
        goal2.setPreferredSize(new Dimension((int)(100 * SCALE), (int)(200 * SCALE)));
      
        c.gridheight = 2;
        c.gridy = 0;
        c.gridx = 7;
        goal2.setLayout(new GridLayout());
        final GoalShape goalCircle2 = new GoalShape((int)(100 * SCALE), (int)(200 * SCALE));
        goalCircle2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                goalCircle2.toggle();
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
        
        goal2.add(goalCircle2);
        
        
        board.add(goal2, c);
    }
    
}
