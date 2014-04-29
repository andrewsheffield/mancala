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
	private Data data; 
	
	/**
	 * Builds the board using the Data class and checks for 
	 * the max/min number of stones to be distributed 
	 * @param numOfStones
	 * @throws InvalidValue
	 */
	public GameLogicModel(int numOfStones) throws InvalidValue 
	{
		if(numOfStones != 3 || numOfStones != 4)
			throw new InvalidValue("Only 3 or 4 stones are allowed");
		data = new Data(numOfStones); 
		
	}
	
	
}
