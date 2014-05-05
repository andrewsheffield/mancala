import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MainView {
    
    public final JPanel board;
    public final JPanel window;
    public final double SCALE = 1.5;
    private ArrayList<Bucket> buckets = new ArrayList<>();
    private final JLabel currentPlayer = new JLabel();
    
    public MainView(/*datatype model*/) {
        
        board = new JPanel();
        board.setPreferredSize(new Dimension((int)(800 * SCALE), (int)(250 * SCALE)));
        setupBoard();
        
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

    private void setupBoard() {
        
        board.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        
        
        for (int i = 0; i < 14; i++) {
            final Bucket bucket;
            
            
            if (i == 0) {
                c.gridheight = 2;
                c.gridx = 0;
                c.gridy = 0;
                bucket = new Bucket((int)(100 * SCALE), (int)(200 * SCALE), 4);
                bucket.setName("M1");
            } else if(i == 13) {
                c.gridheight = 2;
                c.gridx = 7;
                c.gridy = 0;
                bucket = new Bucket((int)(100 * SCALE), (int)(200 * SCALE), 5);
                bucket.setName("M2");
            } else if (i > 6) {
                c.gridheight = 1;
                c.gridx = i - 6;
                c.gridy = 1 ;
                bucket = new Bucket((int)(100 * SCALE), (int)(100 * SCALE), 3);
                bucket.setName("B" + (i - 6));
            } else {
                c.gridheight = 1;
                c.gridx = i;
                c.gridy = 0;
                bucket = new Bucket((int)(100 * SCALE), (int)(100 * SCALE), 2);
                bucket.setName("A" + i);
            }
            
            
            bucket.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    
                    if (bucket.isSelected()) {
                        resetBucketSelections();
                    } else {
                        resetBucketSelections();
                        bucket.select();
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
            
            currentPlayer.setText("Player One's Turn");
            currentPlayer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            c.gridx = 0;
            c.gridy = 3;
            c.ipady = 10;
            board.add(currentPlayer, c);
        }
        
        for (Bucket b : buckets) {
            System.out.println(b.getName());
        }
    }
    
    private void resetBucketSelections() {
        for (Bucket g : buckets) {
            g.resetSelection();
        }
    }
    
}
