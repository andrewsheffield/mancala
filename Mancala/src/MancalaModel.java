/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
//need to make move function into several smaller functions.
//version 1
*/
import java.util.Arrays;

import javax.activity.InvalidActivityException;

/**
*
* @author giannafusaro
*/
public class MancalaModel {
    
    //board data structure
    private int [][] board;
    private boolean isPlayerA;
    private int[][] boardBeforeMove;
    private static int SIZE = 7;
    private int undoCounterA = 0;
    private int undoCounterB = 0;

    public static void main(String[] args) throws InvalidActivityException 
    {
    	MancalaModel model = new MancalaModel(3); 
    	model.printBoard();
  
    	System.out.println(model.isPlayerA);
    	model.movePlayer(1, 0);
    	model.printBoard();
    	/*
    	System.out.println(model.isPlayerA);
    	model.movePlayer(0, 0);
    	model.printBoard();
    	System.out.println(model.isPlayerA);
    	model.undoMove();
    	model.printBoard();
    	System.out.println(model.isPlayerA);
    	model.movePlayer(1, 0);
    	model.printBoard();
    	*/
    }
    
    /**
     * This function prints the representation of the current state of the 
     * board
     * @udaiveer 
     */
    public void printBoard()
    {
    	System.out.println("|Mancala  B | " + board[0][6]);
    	for(int i = 0 ; i < 2; i++)
    	{
    		for(int j = 0; j < SIZE-1; j++) {
    			if(i == 1 && j == 0)
        	    	System.out.print("\n--------------------------------\n");
    	    	System.out.print("| " + board[i][j] + " |");
    	    	
    		}
    	}
    	System.out.println("\n|Mancala  A | " + board[1][6] );
    	System.out.println();
    }
    
    
    public MancalaModel(int initialStones)
    {
        board=new int[2][7];
        for(int[]row:board)
        {
           Arrays.fill(row, initialStones);

        }
        board[0][6]=0;
        board[1][6]=0;
        isPlayerA=true;
    }
    
    public void movePlayer(int i, int j) throws InvalidActivityException
    {
    	movePit(i,j);
        isPlayerA = !isPlayerA;
    }
    
    //movePit
    public void movePit(int i, int j) throws InvalidActivityException
    {
        if(!validateFirstMove(i))
        {
    		return;
        }
        //save state of board before move
        boardBeforeMove = deepCopy(board);
        //stones in board[i][j] pit
        int stonesToDistribute = board[i][j];
        System.out.println("board[i][j] starting out is: " + board[i][j]);
        
        //not a valid move: player cannot click pit that has zero stones
        if(stonesToDistribute==0)
        {
            return;
        }
        
        //not a valid move: cannot try and move mancala pits
        if(j==6)
        {
            return;
        }
      
        //valid move#1: if it is player A's turn
        if(isPlayerA)
        {
            board[i][j]=0;
            while(stonesToDistribute > 0)
            {
                j++;
                //System.out.println("this is j at every iteration in while loop: "+j);
                //player A is at their own mancala, so place in mancala
                //and resume distributing stones
                if(j==6 && i==1)
                {
                    //System.out.println("condition j==6 && i==1 when playerA's turn is here is met");
                    board[i][j]++;
                    i=0;
                    j=-1;
                    stonesToDistribute--;
                }
                //player A is at Player B's mancala, so skip player B's mancala
                //and resume distributing stones
                else if(j==6&&i==0)
                {
                    //System.out.println("condition j==6&&i==0 when playerA's turn is met");
                    i=1;
                    j=0;
                    // stonesToDistribute--;
                }
                //not at a mancala, distribute stones
                else
                {
                    //System.out.println("i is: "+ i+ "and j is: "+ j +" when it is just distributing stones");
                    board[i][j]++;
                    stonesToDistribute--;
                }
            }
            /////////////////////////////
            //now stonesToDistribute =0//
            /////////////////////////////
            
            //if mancala was last index, return function
            //because now player chooses another pit to click
            if(j==6)
            {
                //System.out.println("the last pit was the mancala pit");
                return;
            }
            
            //pit is not empty before last stone is placed into pit
            //so move that pit again, player keeps playing
            else if(board[i][j]>1){
                //System.out.println("pit is not empty player should keep playing");
                //System.out.println("this is i: "+i+"this is j: "+j);
                movePit(i,j);
            }
            
            //pit was empty when player placed last stone into pit
            else if(board[i][j]==1){
                
                //case#1: if playerB
            }
            
        }
        //valid move#2: if it is player B's turn and the row =0
        else if(!isPlayerA)
        {
            board[i][j]=0;
            while(stonesToDistribute>0)
            {
                j++;
                //player B is at their own mancala, so place in mancala
                //and resume distributing stones
                if(j==6&&i==0)
                {
                    board[i][j]++;
                    i=1;
                    j=0;
                    stonesToDistribute--;
                }
                //player B is at Player A's mancala, so skip player A's mancala
                //and resume distributing stones
                else if(j==6&&i==0)
                {
                    i=0;
                    j=0;
                }
                //not at a mancala, distribute stones
                else
                {
                    board[i][j]++;
                    stonesToDistribute--;
                }
            }
            /////////////////////////////
            //now stonesToDistribute =0//
            /////////////////////////////
            
            //if mancala was last index, return function
            //because now player chooses another pit to click
            if(j==6)
            {
                return;
            }
            
            //pit is not empty before last stone is placed into pit
            //so move that pit again, player keeps playing
            else if(board[i][j]>1){
                movePit(i,j);
            }
            
            //pit was empty when player placed last stone into pit
            else if(board[i][j]==1){
                
                //case#1: if playerB
            }
        }
    }
    
    /**
     * This function undos the player move and then it restores the 
     * state of the board
     * @throws InvalidActivityException 
     */
    public void undoMove() throws InvalidActivityException 
    {
    	isPlayerA = !isPlayerA;

    	if(undoCounterA >= 3)
    	{
    		throw new InvalidActivityException("Player A has 3 undos"); 
    	}
    	
    	if(undoCounterB >= 3) 
    	{
    		throw new InvalidActivityException("Player B has 3 undos"); 
    	}
    	
    	if(isPlayerA) {
    		undoCounterA++;
    	}
    	else {
    		undoCounterB++;
    	}
    	board = boardBeforeMove;
    }
    
    public boolean validateFirstMove(int i)
    {
        if(isPlayerA && i==1||!isPlayerA && i==0)
        {
            return true;
        }
        return false;
        
    }
    
    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
    
    public int getStonesAtIndex(int i, int j)
    {
        return board[i][j];
    }
    
}