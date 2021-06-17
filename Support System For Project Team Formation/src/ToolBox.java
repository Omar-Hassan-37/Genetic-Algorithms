import java.util.ArrayList;
import java.util.HashMap;

public class ToolBox {
	private HashMap<String, HashMap<String, Double>> fuzzyValues;
	private HashMap <String,ArrayList<Set>> fuzzySets;
	private HashMap<String, ArrayList<Set>> outputSets;
	private HashMap<String, Double> centriodSets;
	private ArrayList <Integer> varValues;
	Fuzzifier fuzzifier;
	Inference inference;
	Rule rules;
	Centriod centriod;
	Defuzzifier defuzzifier;
	/**
	 * @param fuzzySets
	 * @param varValues
	 */
	public ToolBox(HashMap<String, ArrayList<Set>> fuzzySets, ArrayList<Integer> varValues, HashMap<String, ArrayList<Set>> outputSets) {
		super();
		this.fuzzySets = fuzzySets;
		this.varValues = varValues;
		this.outputSets = outputSets;
		this.fuzzyValues = new HashMap<>();
		this.centriodSets = new HashMap<>();
	}
	
	public void fuzzify()
	{
		HashMap <String, ArrayList<Set>> fuzzySet = new HashMap<>();
		ArrayList<String> variableNames = new ArrayList<>(fuzzySets.keySet());
		System.out.println(variableNames);
		
		for(int i = 0; i < varValues.size(); i++)
		{
			//System.out.println(variableNames.get(i));
			ArrayList<Set> temp = fuzzySets.get(variableNames.get(i));
			//System.out.println("set Name:" + temp.get(i).getSetName());
			fuzzySet.put(variableNames.get(i),fuzzySets.get(variableNames.get(i)));
			fuzzifier = new Fuzzifier(fuzzySet, varValues.get(i));
			fuzzifier.fuzzify(variableNames.get(i));
			fuzzyValues.putAll(fuzzifier.getFuzzyValues());
			fuzzySet.clear();
		}
		
		this.inference = new Inference(fuzzyValues);
	}
	
	public void addRule(String rule) {
		inference.addRule(rule);
	}
	public void addSet(Set s) {
		
	}
	
	public String checkPlace(double wae)
	{
		String crispPlace = "";
		ArrayList<Set> sets = new ArrayList<Set>();
		
		for(ArrayList<Set> s : outputSets.values()) {
			sets.addAll(s);
		}
		
		for(Set set : sets)
		{
			ArrayList<Integer> points = new ArrayList<>(set.getPoints());
			for(int i = 0; i < points.size(); i++)
			{
				if(wae < points.get(i))
				{
					return set.getSetName();
				}
			}
		}
		return crispPlace;
	}
	
	void run() {
		//fuzzify();
		System.out.println("1. Fuzzification: ");
		System.out.println(fuzzyValues);
		
		//this.inference = new Inference(fuzzyValues);
		
		
		inference.setFuzzyValues(fuzzyValues);
		HashMap<String, Double> inferedValues = inference.infer();
		System.out.println("2. infered Values: \n" + inferedValues);
		centriod = new triCentroid(outputSets);
		System.out.println("3. centriods: \n" + centriod.getCentriod());
		defuzzifier = new Defuzzifier(centriod, inferedValues);
		System.out.println("4. Defuzzification: \npredicted value: " + defuzzifier.e.calcWAE());
		System.out.println("rsik will be: " + checkPlace(defuzzifier.e.calcWAE()));
	}
	
}
