import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is a representation of the board 
 * @author  Giana & Udaiveer  
 *
 */
public class Data 
{
	/* This board is on 1D representation of the board with 
	player B pits ranging from 0-5 and 6th position for 
	mancala Player A from 7-12, 13th for mancala A 
	*/
	public ArrayList<Integer> board; 
	// size of board 
	public int SIZE = 14; 
	public int MANCALA_INDEX_A = 13; 
	public int MANCALA_INDEX_B = 6;
    public int numberOfStones;
	public boolean isPlayerA; 

	/**
	 * The number of stones used to build the initial 
	 * board, the enforcement of the rules is done by 
	 * GameLogicModel which only allows 3 or 4 stones
	 * Player A always goes first. 
	 * @param numOfStones the number of stones to be added
	 * @author Gianna 
	 */
	public Data(int numOfStones)
	{
		board = new ArrayList<Integer>(SIZE);
		setStones(numOfStones);
		isPlayerA = true;
                numberOfStones = numOfStones;
	}
	
	/**
	 * empty constructor 
	 * @author udaiveer
	 */
	public Data() {
		board = new ArrayList<Integer>(14);
	}

	/**
	 * returns a deep copy of the state of the 
	 * board. 
	 * @return deep copy
	 * @author udaiveer
	 */
	public Data DeepCopy() 
	{
		Data tmp = new Data(numberOfStones); 
		tmp.isPlayerA = this.isPlayerA;
		Collections.copy(tmp.board, board);
		return tmp;
	}
	
	/**
	 * Evenly distributes the stones to all of the pits 
	 * except for the mancalas of the players which have 
	 * 0 stones  
	 * @param numOfStones
	 * @author udaiveer
	 */
	public void setStones(int numOfStones) 
	{
		for(int i = 0; i < SIZE; i++) {
			if(i == MANCALA_INDEX_A || i == MANCALA_INDEX_B) {
				board.add(0);
			} else {
				board.add(numOfStones);
			}
		}
	}
	
	/**
	 * The makeMove function is called by the GameLogicModel class
	 * The GameLogicModel checks if all the pits are empty before
	 * and after the makeMove function to see if game is won.
	 * GameLogicModel also checks if the player will steal the
	 * mancalas or if player will get extra turn. 
	 * @param pitNumber
	 * @return the last index the player left
	 * @author udaiveer
	 */
	
	public int move(int pitNumber) {
		int stonesToDistribute = board.get(pitNumber);
		if(stonesToDistribute > 0) {
		board.set(pitNumber,0);
		pitNumber++;
		while(stonesToDistribute != 0){
			if(isPlayerA && pitNumber == MANCALA_INDEX_A){
				System.out.println("set mancala A to " + (board.get(pitNumber)+1));
				board.set(pitNumber,board.get(pitNumber)+1);
				stonesToDistribute--;
			}else if(!isPlayerA && pitNumber == MANCALA_INDEX_B){
				board.set(pitNumber,board.get(pitNumber)+1);
				stonesToDistribute--;
			} 
			else {
				board.set(pitNumber,board.get(pitNumber)+1);
				stonesToDistribute--;
			}	
			
			if(pitNumber == 13) {
				if(stonesToDistribute == 0 && isPlayerA) {
					return 13;
				}
				pitNumber = -1;
			}
			pitNumber++;
		}
		
		return pitNumber-1;
		} else
			return -1;
	}

	/**
	 * prints the board used for testing purposes only
	 * @author udaiveer
	 */
  public void printBoard()
    {
    	System.out.println("|Mancala  B | " + board.get(6));
    	for(int i = 5 ; i >= 0; i--)
    	{
	    	System.out.print("| " + board.get(i) + " |");    	    	
    	}
    	System.out.print("\n--------------------------------\n");
    	for(int i = 7 ; i < 13; i++)
    	{
	    	System.out.print("| " + board.get(i) + " |");    	    	
    	}
    	System.out.println("\n|Mancala  A | " + board.get(13) );
    	System.out.println("is player A " + isPlayerA + "\n");
    }
  
  /**
   * 
   * @param index
   * @return
   * @author udaiveer
   */
  public int getStoneInPit(int index) 
  {
	  return board.get(index);
  }
  
  /**
   * 
   * @author udaiveer
   * @param index
   * @author udaiveer
   */
  public void removeAllStonesFromPit(int index) 
  {
	  board.set(index, 0);
  }
  
  /**
   * @author udaiveer
   * @return
   */
  public ArrayList<Integer> getPitsB()
  {
	  ArrayList<Integer> b = new ArrayList<Integer>(); 
	  for(int i = 5 ; i >= 0; i--) {
	    	b.add(board.get(i));    	    	
  	}
	  return b;
  }
  
  /**
   * @author udaiveer
   * @return
   */
  public ArrayList<Integer> getPitsA() 
  {
	  ArrayList<Integer> a = new ArrayList<Integer>(); 
	  for(int i = 7 ; i < 13; i++) {
	    	a.add(board.get(i));    	    	
  	}
	  return a;
  }
  
  /**
   * If player A won returns 1
   * if player B won returns -1
   * if tie return 0
   * if in progress returns 6969 
   * @author udaiveer
   * @return int representing state of game 
   * @author udaiveer
   */
  public int checkWinState() 
  {
	  boolean prevIsZero = true;
	  for(int i = 0 ; i < 6; i++){
	    if(board.get(i) == 0) {
	    	prevIsZero = true;
	    }
	    else {
	    	prevIsZero = false;
	    	break;
	    }
  	  }
	  // player B won
	  if(prevIsZero) {
		  return -1;
	  }
	  
	  prevIsZero = true;
	  for(int i = 7 ; i < 13; i++) {
	    if(board.get(i) == 0) {
	    	prevIsZero = true;
	    }
	    else {
	    	prevIsZero = false;
	    	break;
	    }
  	  }
	  // player A just won
	  if(prevIsZero) {
		  return 1;
	  }
	  // no one has won 
	  return 0;
  }
  
  /**
   * Adds stones to mancala 
   * @author udaiveer
   * @param stones
   */
  public void addStonesToMancala(int stones)
  {
	  if(isPlayerA)
		  board.set(MANCALA_INDEX_A, board.get(MANCALA_INDEX_A)+ stones);
	  else
		  board.set(MANCALA_INDEX_B, board.get(MANCALA_INDEX_B)+ stones);
  }
}
