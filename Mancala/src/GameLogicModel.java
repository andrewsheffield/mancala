import java.util.ArrayList;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

/**
 * Game logic class hold an instance of the board Data class
 * The Data class is a representation of the board, The GameLogicModel
 * class enforces all of the rules. 
 * @author Giana & Udaiveer  
 *
 */
public class GameLogicModel 
{
	public int undoCounterA; 
	public int undoCounterB; 
	public Data data; 
	public Data prevoiusData;
	private MainView view;
	
	/**
	 * Builds the board using the Data class and checks for 
	 * the max/min number of stones to be distributed 
	 * @param numOfStones
	 * @throws InvalidValue
	 * @author Gianna 
	 */
	public GameLogicModel(int numOfStones, MainView view) throws InvalidValue 
	{
		undoCounterA = 0;
		undoCounterB = 0; 
		if(numOfStones < 3 || numOfStones > 4)
			throw new InvalidValue("Only 3 or 4 stones are allowed");
		data = new Data(numOfStones);
		prevoiusData = data;
                this.view = view;
	}
	
	/**
	 * Starts a new game 
	 * @author Gianna 
	 */
	public void newGame()
	{
		undoCounterA = 0;
		undoCounterB = 0;
		data = new Data(data.numberOfStones);
		prevoiusData = data;
		data.isPlayerA = true;
	}
	
	/**
	 * This is the main function that is used to play the game it checks 
	 * all of the rules and will throw an exception if wrong pits are 
	 * selected on wrong turns
	 * @param pitIndex
	 * @throws InvalidValue
	 * @author udaiveer & Gianna 
	 */
	public void makeMove(int pitIndex) throws InvalidValue 
	{
		prevoiusData = data.DeepCopy();
		if(pitIndex == data.MANCALA_INDEX_A || 
				pitIndex == data.MANCALA_INDEX_B) {
			throw new InvalidValue("mancala are not clickable");
		}
		
		if(checkIfValidMove(pitIndex)) {
			throw new InvalidValue("player A turn is " + data.isPlayerA + " you cant pick that pit");
		}
		
		int endingIndex = data.move(pitIndex);
		
		System.out.println("endingIndex is " + endingIndex);
		// steal stones from pit 
		checkIfPitWasEmpty(endingIndex); 
		
		// give player A another turn
		if(data.isPlayerA && endingIndex == data.MANCALA_INDEX_A)
		{
			data.isPlayerA = true;
		} else if(!data.isPlayerA && endingIndex == data.MANCALA_INDEX_B)
		{
			data.isPlayerA = false;
		} else {
			data.isPlayerA = !data.isPlayerA;
		}
                view.setupBoard();

	}
	
	/**
	 * Will revert back the the previous known turn each player 
	 * is allows 3 undo clicks after that no matter how many 
	 * times the undo is pressed nothing will occur. 
	 * @author Gianna
	 */
	public void undo()
	{
		if(data.isPlayerA && undoCounterA < 3) {
			undoCounterA++;
			data = prevoiusData;
		}
		
		if(!data.isPlayerA && undoCounterB < 3) {
			undoCounterB++;
			data = prevoiusData;
		}
	}
	
	/**
	 * If player A won returns 1
	 * if player B won returns -1
	 * if tie return 0
	 * if in progress returns 6969 
	 * @return 1,0,-1, 6969 check win state of player A, none, B, in progress
	 * @author udaiveer 
	 */
	public int checkWinState()
	{
		if(data.checkWinState() != 0)
		{
			int mA = getMancalaA(); 
			int mB = getMancalaB(); 
			if(mA > mB)
				return 1;
			else if(mB > mA)
				return -1;
			else 
				return 0;
		}
		return 6969;
	}
	
	/**
	 * returns an array list in the order of display 
	 * for player A: A6,A5,A4,A3,A2,A1 
	 * @return arrayList on integer representing pits of A
	 * @author Gianna 
	 */
	public ArrayList<Integer> getPlayerPitsA() 
	{
		return data.getPitsA();
	}
	
	/**
	 * returns an array list in the order of display 
	 * for player B: B6,B5,B4,B3,B2,B1 
	 * @return arrayList on integer representing pits of B 
	 * @author Gianna 
	 */
	public ArrayList<Integer> getPlayerPitsB() 
	{
		return data.getPitsB();
	}
	
	/**
	 * @return number of stones in player A mancala 
	 * @author Gianna
	 */
	public int getMancalaA()
	{
		return data.getStoneInPit(data.MANCALA_INDEX_A);
	}
	
	/**
	 * @return number of stones in player B mancala 
	 * @author Gianna
	 */
	public int getMancalaB()
	{
		return data.getStoneInPit(data.MANCALA_INDEX_B);
	}
	
	/**
	 * Returns true if is player A turn
	 * @return boolean 
	 * @author Gianna
	 */
	public boolean checkTurnPlayerA() {
		return data.isPlayerA; 
	}
	
	/**
	 * Helper Function of make move 
	 * @param pitIndex
	 * @return boolean
	 * @author Gianna
	 */
	private boolean checkIfValidMove(int pitIndex)
	{
		if(data.isPlayerA && pitIndex >=0 && pitIndex <=6 )
			return true;
		if(!data.isPlayerA && pitIndex >=7 && pitIndex <=12 )
			return true;
		
		return false;
	}
	
	/**
	 * This function checks if a pit was empty and steels 
	 * @param endingIndex
	 * @author Gianna
	 */
	private void checkIfPitWasEmpty(int endingIndex) 
	{
		if(data.isPlayerA && endingIndex >=7 && endingIndex <=12 
				&& data.getStoneInPit(endingIndex) == 1) {
			// steal stones from pit B add to mancala A
			int adjIndex = getAdjacentIndex(endingIndex);
			int stoneForMancal = data.getStoneInPit(adjIndex);
			data.removeAllStonesFromPit(adjIndex);
			data.addStonesToMancala(stoneForMancal);
		}
		
		if(!data.isPlayerA && endingIndex >=0 && endingIndex <=5
				&& data.getStoneInPit(endingIndex) == 1) {
			// steal stones from pit A add to mancala B
			int adjIndex = getAdjacentIndex(endingIndex);
			System.out.println("adj index " + adjIndex + " " + endingIndex);
			int stoneForMancal = data.getStoneInPit(adjIndex);
			data.removeAllStonesFromPit(adjIndex);
			data.addStonesToMancala(stoneForMancal);
				}
	}
	
	/**
	 * adjusts the index to find the corresponding index to 
	 * steal from. 
	 * @param endingIndex
	 * @return corresponding index
	 * @author udaiveer
	 */
	private int getAdjacentIndex(int endingIndex) 
	{
			return 12 - endingIndex ;
	}
        
	
}
