import java.util.ArrayList;
import java.util.HashMap;

public class Inference {
	private ArrayList<Rule> rules;
	private HashMap<String, HashMap<String, Double>> fuzzyValues;
	
	
	public Inference(HashMap<String, HashMap<String, Double>> fuzzyValues) {
		this.rules = new ArrayList<Rule>();
		this.fuzzyValues = fuzzyValues;
	}
	
	public void addRule(String r) {
		rules.add(new Rule(fuzzyValues, r));
	}
	HashMap<String, Double> infer(){
		HashMap<String, Double> result = new HashMap<String, Double>();
		ArrayList<Double> a = new ArrayList<Double>();
		System.out.println(rules.size());
		for(Rule r : rules) {
			String sName = r.getOutput().split(" ")[2];
			Double value = r.evaluate();
			a.add(value);
			if(result.containsKey(sName)) {
				if(value > result.get(sName)) {
					
					result.put(sName, value);
				}
			}else {
				result.put(sName, value);
			}
			
			
			
		}
		System.out.println("All Values: " + a);
		return result;
	}
	public HashMap<String, HashMap<String, Double>> getFuzzyValues() {
		return fuzzyValues;
	}
	public void setFuzzyValues(HashMap<String, HashMap<String, Double>> fuzzyValues) {
		this.fuzzyValues = fuzzyValues;
	}
	
	
}
