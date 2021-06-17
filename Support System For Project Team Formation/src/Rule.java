import java.util.*;
public class Rule {
	private Stack<String> ops;
	private Stack<Double> nums;
	private HashMap<String, HashMap<String, Double>> fuzzyValues;
	private String rule;
	private String output;
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @param fuzzyValues
	 * @param rule
	 */
	public Rule( HashMap<String, HashMap<String, Double>> fuzzyValues,
			String rule) {
		super();
		this.ops = new Stack<String>();
		this.nums = new Stack<Double>();
		this.fuzzyValues = fuzzyValues;
		this.rule = rule;
		
		String[] ruleAndOutput = rule.split("then");
		
		this.rule = ruleAndOutput[0];
		this.output = ruleAndOutput[1];
	}

	public double evaluate() {
		
		String[] tokens = rule.split(" ");
		
		for(int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			
			
			if( token.equals("(")) {
				ops.push("(");
			}
			else if(fuzzyValues.containsKey(token)) {
				String set = tokens[++i];
				if(set.equals("not")) {
					ops.push("not");
					set = tokens[++i];
				}
				nums.push( fuzzyValues.get(token).get(set) );
				
			}
			else if(token.equals(")")) {
				
				String op = ops.pop();
				Double a = nums.pop();
				Double b = nums.pop();
				
				switch(op) {
				case "and":
					nums.push(Math.min(a, b));
					break;
				case "or":
					nums.push(Math.max(a, b));
					break;
				}
				
				
			}
			else if(token.equals("or")) {
				if(!ops.isEmpty()) {
					if(ops.peek().equals("not")) {
						ops.pop();
						Double a = nums.pop();
						nums.push((double)1-a);
					}
					if(ops.peek().equals("and")) {
						String op = ops.pop();
						Double a = nums.pop();
						Double b = nums.pop();
						nums.push(Math.min(a, b));
					}
					
				}
				ops.push(token);
				
			}
			else if(token.equals("and")) {
				if(!ops.isEmpty() && ops.peek().equals("not")) {
					ops.pop();
					Double a = nums.pop();
					nums.push((double)1-a);
				}
				ops.push("and");
			}
			
		}
		
		while(!ops.isEmpty() && nums.size() >1) {
			String op = ops.pop();
			if(op.equals("not")) {
				Double a = nums.pop();
				nums.push((double)1-a);
			}
			else if(op.equals("and")) {
				Double a = nums.pop();
				Double b = nums.pop();
				nums.push(Math.min(a, b));
			}
			else if (op.equals("or")) {
				Double a = nums.pop();
				Double b = nums.pop();
				nums.push(Math.max(a, b));
			}
			
			
		}
		
		return nums.pop();
	}
	
	
	
}
