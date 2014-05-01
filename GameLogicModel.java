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
	private int undoCounterA; 
	private int undoCounterB; 

	public static void main(String[] args) throws InvalidValue 
	{
		GameLogicModel tmp = new GameLogicModel(3);
		tmp.data.printBoard();
		tmp.makeMove(7); // player A picks A1 
		tmp.data.printBoard();
		tmp.undo(); // reverts back to player A and board to previous state 
		tmp.data.printBoard();
		tmp.makeMove(12); // player A picks A6 
		tmp.data.printBoard();

	}
	
	public Data data; 
	public Data prevoiusData;
	
	/**
	 * Builds the board using the Data class and checks for 
	 * the max/min number of stones to be distributed 
	 * @param numOfStones
	 * @throws InvalidValue
	 */
	public GameLogicModel(int numOfStones) throws InvalidValue 
	{
		undoCounterA = 0;
		undoCounterB = 0; 
		if(numOfStones < 3 || numOfStones > 4)
			throw new InvalidValue("Only 3 or 4 stones are allowed");
		data = new Data(numOfStones);
		prevoiusData = data;
	}
	
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
		
		// steal stones from pit 
		checkIfPitWasEmpty(endingIndex); 
		
		data.isPlayerA = !data.isPlayerA;

		// give player A another turn
		if(data.isPlayerA && endingIndex == data.MANCALA_INDEX_A)
		{
			data.isPlayerA = true;
		}
		
		// give player B another turn
		if(!data.isPlayerA && endingIndex == data.MANCALA_INDEX_B)
		{
			data.isPlayerA = false;
		}
		
	}
	
	
	private boolean checkIfValidMove(int pitIndex)
	{
		if(data.isPlayerA && pitIndex >=0 && pitIndex <=6 )
			return true;
		if(!data.isPlayerA && pitIndex >=7 && pitIndex <=12 )
			return true;
		
		return false;
	}
	
	/**
	 * This function checks if a pit was empty and 
	 * @param endingIndex
	 */
	private void checkIfPitWasEmpty(int endingIndex) 
	{
		if(data.isPlayerA && endingIndex >=0 && endingIndex <=6 
				&& data.getStoneInPit(endingIndex) == 1) {
			// steal stones from pit B add to mancala A
			int adjIndex = getAdjacentIndex(endingIndex);
			int stoneForMancal = data.getStoneInPit(adjIndex);
			data.removeAllStonesFromPit(adjIndex);
			data.addStonesToMancala(stoneForMancal);
		}
		
		if(!data.isPlayerA && endingIndex >=7 && endingIndex <=12
				&& data.getStoneInPit(endingIndex) == 1) {
			// steal stones from pit A add to mancala B
			int adjIndex = getAdjacentIndex(endingIndex);
			int stoneForMancal = data.getStoneInPit(adjIndex);
			data.removeAllStonesFromPit(adjIndex);
			data.addStonesToMancala(stoneForMancal);
				}
	}
	
	private int getAdjacentIndex(int endingIndex) 
	{
		if(endingIndex >=0 && endingIndex <=6)
			return endingIndex +7;
		else
			return endingIndex -7;
	}
	
	/**
	 * Returns true if is player A turn
	 * @return
	 */
	public boolean checkTurnPlayerA() {
		return data.isPlayerA; 
	}
	
	public void undo()
	{
		if(data.isPlayerA && undoCounterA < 4) {
			undoCounterA++;
			data = prevoiusData;
		}
		
		if(!data.isPlayerA && undoCounterB < 4) {
			undoCounterB++;
			data = prevoiusData;
		}
	}
	
	/**
	 * If player A won returns 1
	 * if player B won returns -1
	 * else it returns a 0 
	 * @return 1,0,-1 check win state of player A, none, B 
	 */
	public int checkWinState()
	{
		return data.checkWinState();
	}
}
