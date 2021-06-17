import java.util.ArrayList;
import java.util.HashMap;

public class QuadCentriod extends Centriod{

	public QuadCentriod(HashMap<String, ArrayList<Set>> ruleSets) {
		super(ruleSets);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	HashMap<String, Double> getCentriod()
	{
		HashMap<String, Double> centriodSet = new HashMap<String, Double>();
		ArrayList<Set> points = getRuleSets().get(getRuleSets().values());
		
		for(Set set : points)
		{
			double centriod = calcCentriod(set.getPoints(), set.getY_axis());
			centriodSet.put(set.getSetName(), centriod);
		}
		
		return centriodSet;
	}
	
	@Override
	double calcCentriod(ArrayList<Integer> x_axis, int[] y_axis)
	{
		double centriodSum = 0;
		double centriod = 0;
		double signedArea = calcSignedArea(x_axis, y_axis);
		for(int i = 0; i < (x_axis.size() - 1); i++) 
		{
			centriodSum += (x_axis.get(i) + x_axis.get(i+1)) * ((x_axis.get(i) * y_axis[i + 1]) - (x_axis.get(i + 1) * y_axis[i]));
		}
		centriod = centriodSum / (6 * signedArea);
		
		return centriod;
	}
	
	double calcSignedArea(ArrayList<Integer> x_axis, int[] y_axis)
	{
		double signedArea = 0;
		for(int i = 0; i < (x_axis.size() - 1); i++)
		{
			signedArea += (x_axis.get(i) * y_axis[i + 1]) - (x_axis.get(i + 1) * y_axis[i]);
		}
		return (signedArea / 2);
	}

}
