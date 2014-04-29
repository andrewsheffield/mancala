import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is a representation of the board 
 * @author  Giana & Udaiveer  
 *
 */
public class Data 
{
	/* This board is on 1d representation of the board with 
	player B pits ranging from 0-5 and 6th position for 
	mancala Player A from 7-12, 13th for mancala 
	*/
	private ArrayList<Integer> board; 
	private ArrayList<Integer> previousBoard; 
	// size of board 
	private static int SIZE = 14; 
	private static int MANCALA_INDEX_A = 13; 
	private static int MANCALA_INDEX_B = 12; 
	private boolean isPlayerA; 

	/**
	 * The number of stones used to build the initial 
	 * board, the enforcement of the rules is done by 
	 * GameLogicModel 
	 * @param numOfStones
	 */
	public Data(int numOfStones)
	{
		board = new ArrayList<Integer>(SIZE);
		previousBoard = new ArrayList<Integer>(SIZE);
		setStones(numOfStones);
		isPlayerA = true;
	}
	
	/**
	 * Evenly distributes the stones to all of the pits 
	 * except for he mancalas of the players 
	 * @param numOfStones
	 */
	public void setStones(int numOfStones) 
	{
		for(int i = 0; i < SIZE; i++) {
			if(i != MANCALA_INDEX_A || i != MANCALA_INDEX_B) {
				board.set(i, numOfStones);
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
	 */
	
	public int makeMove(int pitNumber) {
		Collections.copy(previousBoard, board);
		int stonesToDistribute = board.get(pitNumber);
		board.set(pitNumber,0);
		while(stonesToDistribute > 0){
			pitNumber++;
			
			if(isPlayerA && pitNumber == MANCALA_INDEX_A){
				board.set(pitNumber,board.get(pitNumber)+1);
				stonesToDistribute--;
			}
			
			if(!isPlayerA && pitNumber == MANCALA_INDEX_B){
				board.set(pitNumber,board.get(pitNumber)+1);
				stonesToDistribute--;
			}
			
			if(pitNumber != MANCALA_INDEX_A 
					|| pitNumber != MANCALA_INDEX_B) {
				board.set(pitNumber,board.get(pitNumber)+1);
				stonesToDistribute--;
			}
			
			if(pitNumber == 13) {
				pitNumber = 0;
			}
			
		}
		return pitNumber;
	}
}
