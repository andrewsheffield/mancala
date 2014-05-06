import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class MainView {
    
    private GameLogicModel model;
    private final JPanel board;
    private final double SCALE = 1.5;
    private final JLabel currentPlayer = new JLabel();
    private final JButton undo;
    private final ButtonGroup bg1;
    private final JRadioButton three;
    private final JRadioButton four;
    
    public MainView() {
        JFrame frame =  new JFrame();
        frame.setLayout(new BorderLayout());
        
        board = new JPanel();
        board.setPreferredSize(new Dimension((int)(800 * SCALE), (int)(250 * SCALE)));
        frame.add(board, BorderLayout.CENTER);
        
        currentPlayer.setText("Player 1");
        frame.add(currentPlayer, BorderLayout.PAGE_END);
        
        JPanel buttons = new JPanel();
        
        undo = new JButton("Undo");
        undo.setEnabled(false);
        undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.undo();
                undo.setEnabled(false);
            }
        });
        buttons.add(undo);
        
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (three.isSelected()) {
                    model.newGame(3);
                } else {
                    model.newGame(4);
                }
            }
        });
        buttons.add(newGame);
        
        bg1 = new ButtonGroup();
        three = new JRadioButton("3");
        three.setSelected(true);
        four = new JRadioButton("4");
        bg1.add(three);
        bg1.add(four);
        buttons.add(three);
        buttons.add(four);
        
        frame.add(buttons, BorderLayout.PAGE_START);
        
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setupBoard() {
        board.removeAll();
        board.revalidate();
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
                bucket = new Bucket((int)(100 * SCALE), (int)(100 * SCALE), model.getPlayerPitsA().get(i-7));
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
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    try {
                        model.makeMove(Integer.parseInt(bucket.getName()));
                    } catch (InvalidValue ex) {
                        JOptionPane.showMessageDialog(board, "Not your turn!  Calm yo Tits!");
                    }
                    undo.setEnabled(true);
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
            
            bucket.setUI(new CircleUI());
            
            board.add(bucket, c);
            
        }
        
        if (model.checkWinState() == 1) {
            currentPlayer .setText("Player 1 wins");
        } else if (model.checkWinState() == -1) {
            currentPlayer .setText("Player 2 wins");
        } else if (model.checkTurnPlayerA()) {
            currentPlayer.setText("Player 1");
        } else {
            currentPlayer.setText("Player 2");
        }
        
        board.revalidate();
        board.repaint();
    }

    void setData(GameLogicModel model) {
        this.model = model;
        setupBoard();
    }
    
}
