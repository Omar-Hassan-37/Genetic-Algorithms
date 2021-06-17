import java.util.ArrayList;
import java.util.HashMap;

public class Fuzzifier {
		
	private HashMap <String,ArrayList<Set>> fuzzySets; 
	private HashMap<String, HashMap<String, Double>> fuzzyValues;
	private int varValue;
	
	Fuzzifier(HashMap <String,ArrayList<Set>> fuzzySets , int varValue)
	{
		this.fuzzySets = fuzzySets;
		this.varValue = varValue;
		this.fuzzyValues = new HashMap<String, HashMap<String,Double>>();
	}
	
	
	
	public HashMap<String, HashMap<String, Double>> getFuzzyValues() {
		return fuzzyValues;
	}



	public void setFuzzyValues(HashMap<String, HashMap<String, Double>> fuzzyValues) {
		this.fuzzyValues = fuzzyValues;
	}



	void fuzzify(String variableName) 
	{
		HashMap<String, Double> memberShip = new HashMap<String, Double>();
		ArrayList<Set> temp = new  ArrayList<Set>(); 
		temp = fuzzySets.get(variableName);
		for(Set set : temp)
		{
			String setName = set.getSetName();
			Double msValue = getMemberShipValue(set.getPoints(), set.getY_axis(), varValue);
			memberShip.put(setName, msValue);
		}
		fuzzyValues.put(variableName, memberShip);
	}
	
	float getSlope(float x0, float x1, float y0, float y1)
	{
		float slope = (y1 - y0) / (x1 - x0);
		return slope;
	}
	
	float getIntercept(int y, float m, int x)
	{
		float c = y - (m*x);
		return c;
	}
	
	Double getMemberShipValue(ArrayList<Integer> setPoints, int [] y_axis, int varValue)
	{
		Double msValue = (double) 0;
		boolean flag = false;
		int x0 = 0, x1 = 0, y0 = 0, y1 = 0;
		for(int i = 0; i < setPoints.size(); i++)
		{
			if((varValue < setPoints.get(i)))
			{
				if(i == 0)
				{
					return (double) 0;
				}
				x0 = setPoints.get(i - 1);
				x1 = setPoints.get(i);
				y0 = y_axis[i-1];
				y1 = y_axis[i];
				flag = true;
				break;
			}
		}
		if(flag == false) {return (double) 0;}
		float slope = getSlope(x0, x1, y0, y1);
		float intercept = getIntercept(y0, slope, x0);
		msValue = (double) ((slope * varValue) + intercept);
		
		return msValue;
	}
	
}
