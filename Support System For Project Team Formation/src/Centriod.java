import java.util.ArrayList;
import java.util.HashMap;

public abstract class Centriod {
	private HashMap <String,ArrayList<Set>> ruleSets;

	/**
	 * @param fuzzySets
	 */
	public Centriod(HashMap<String, ArrayList<Set>> ruleSets) {
		super();
		this.ruleSets = ruleSets;
	}
	
	public HashMap<String, ArrayList<Set>> getRuleSets() {
		return ruleSets;
	}

	public void setRuleSets(HashMap<String, ArrayList<Set>> ruleSets) {
		this.ruleSets = ruleSets;
	}

	HashMap<String, Double> getCentriod()
	{
		return null;
	}
	
	double calcCentriod(ArrayList<Integer> points)
	{
		return 0;
		
	}

	double calcCentriod(ArrayList<Integer> points, int[] y_axis) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
