import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class triCentroid extends Centriod{
	
	public triCentroid(HashMap<String, ArrayList<Set>> ruleSets) {
		super(ruleSets);
		
	}
	@Override
	HashMap<String, Double> getCentriod()
	{
		HashMap<String, Double> centriodSet = new HashMap<String, Double>();
		
		ArrayList<Set> points = new ArrayList<Set>();
		
		for(ArrayList<Set> s : getRuleSets().values()) {
			points.addAll(s);
		}
		
		for(Set set : points)
		{
			double centriod = calcCentriod(set.getPoints());
			centriodSet.put(set.getSetName(), centriod);
		}
		
		return centriodSet;
	}
	
	@Override
	double calcCentriod(ArrayList<Integer> points)
	{
		double centriod = 0;
		
		for(int i : points)
		{
			centriod += i;
		}
		
		return (centriod / 3);
	}

}
