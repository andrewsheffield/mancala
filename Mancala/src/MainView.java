/**
 * author: Andrew J Sheffield
 * This view paints a Mancala board.  The data model updates the board when ever a change is made
 * and the board then displays the new data.  Buttons or the board can be pressed to update content
 * in the model.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.omg.CORBA.DynAnyPackage.InvalidValue;

public class MainView {
    
    private GameLogicModel model;
    private final JPanel board;
    private final double SCALE = 1.7;
    private final JLabel currentPlayer = new JLabel();
    private final JButton undo;
    private final ButtonGroup bg1;
    private final JRadioButton three;
    private final JRadioButton four;
    private MancalaUI userInt = new CircleUI();
    
    public MainView() {
        JFrame frame =  new JFrame();
        frame.setLayout(new BorderLayout());
        
        board = new JPanel();
        board.setPreferredSize(new Dimension((int)(800 * SCALE), (int)(250 * SCALE)));
        frame.add(board, BorderLayout.CENTER);
        
        currentPlayer.setText("Player 1");
        currentPlayer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        frame.add(currentPlayer, BorderLayout.PAGE_END);
        
        JPanel buttons = new JPanel();
        
        //Controller
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
        
        //Controller
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (three.isSelected()) {
                    model.newGame(3);
                } else {
                    model.newGame(4);
                }
                undo.setEnabled(false);
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
        
        //Controller
        JButton circle = new JButton("Circle UI");
        circle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                userInt = new CircleUI();
                setupBoard();
            }
        });
        buttons.add(circle);
        JButton square = new JButton("Square UI");
        square.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                userInt = new SquareUI();
                setupBoard();
            }
        });
        buttons.add(square);
        
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
            
            //Controller
            bucket.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (model.checkWinState() == 6969) {
                        try {
                            model.makeMove(Integer.parseInt(bucket.getName()));
                        } catch (InvalidValue ex) {
                            String temp = currentPlayer.getText();
                            currentPlayer.setText(temp.split(" ")[0] + " INVALID MOVE");
                        }
                        undo.setEnabled(true);
                    }
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
            
            bucket.setUI(userInt);
            
            board.add(bucket, c);
            
        }
        
        if (model.checkWinState() == 1) {
            currentPlayer .setText("Player 1 Wins");
            JOptionPane.showMessageDialog(board, "Player 1 Wins!!!!");
        } else if (model.checkWinState() == -1) {
            currentPlayer .setText("Player 2 wins");
            JOptionPane.showMessageDialog(board, "Player 2 Wins!!!!");
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
