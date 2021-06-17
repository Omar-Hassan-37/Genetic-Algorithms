import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		ArrayList <Integer> points1 = new ArrayList <Integer>(Arrays.asList(0,0,10,30));
		ArrayList <Integer> points2 = new ArrayList <Integer>(Arrays.asList(10,30,40,60));
		ArrayList <Integer> points3 = new ArrayList <Integer>(Arrays.asList(40,60,70,90));
		ArrayList <Integer> points4 = new ArrayList <Integer>(Arrays.asList(70,90,100,100));
		
		Set set1 = new QuadSet("project_funding", "verylow", points1);
		Set set2 = new QuadSet("project_funding", "low", points2);
		Set set3 = new QuadSet("project_funding", "medium", points3);
		Set set4 = new QuadSet("project_funding", "high", points4);
		
		ArrayList <Integer> point5 = new ArrayList <Integer>(Arrays.asList(0,15,30));
		ArrayList <Integer> point6 = new ArrayList <Integer>(Arrays.asList(15,30,45));
		ArrayList <Integer> point7 = new ArrayList <Integer>(Arrays.asList(30,60,60));
		
		Set set5 = new TriSet("team_experience_level", "beginner", point5);
		Set set6 = new TriSet("team_experience_level", "intermediate", point6);
		Set set7 = new TriSet("team_experience_level", "expert", point7);
		
		ArrayList<Set> sets1 = new ArrayList <Set>(Arrays.asList(set1, set2, set3, set4));
		ArrayList<Set> sets2 = new ArrayList <Set>(Arrays.asList(set5, set6, set7));
		
		HashMap <String, ArrayList<Set>> fuzzySets = new HashMap<String, ArrayList<Set>>();
		fuzzySets.put("project_funding", sets1);
		fuzzySets.put("team_experience_level", sets2);
		
	
		ArrayList <Integer> points5 = new ArrayList <Integer>(Arrays.asList(0,25,50));
		ArrayList <Integer> points6 = new ArrayList <Integer>(Arrays.asList(25,50,75));
		ArrayList <Integer> points7 = new ArrayList <Integer>(Arrays.asList(50,100,100));
		//ArrayList <Integer> points8 = new ArrayList <Integer>(Arrays.asList(30,45,60));
		//ArrayList <Integer> points9 = new ArrayList <Integer>(Arrays.asList(45,60,60));
		
		Set set8 = new TriSet("risk", "high", points5);
		Set set9 = new TriSet("risk", "normal", points6);
		Set set10 = new TriSet("risk", "low", points7);
		//Set set11 = new QuadSet("washTime", "large", points7);
		//Set set12 = new QuadSet("washTime", "verylarge", points8);
		
		ArrayList<Set> sets3 = new ArrayList <Set>(Arrays.asList(set8, set9, set10));
		
		HashMap <String, ArrayList<Set>> centriodSet = new HashMap<String, ArrayList<Set>>();
		centriodSet.put("risk", sets3);
		
		Centriod centriod = new triCentroid(centriodSet);

		
		ArrayList <Double> pointsc = new ArrayList <Double>(Arrays.asList(5.0,15.0,30.0,45.0,55.0));
		ArrayList <Double> pointsms = new ArrayList <Double>(Arrays.asList(0.0,0.75,0.25,0.0,0.0));
		
		System.out.println("Enter Project Funding: ");
		Integer funding = in.nextInt();
		System.out.println("Enter Experience: ");
		Integer exp = in.nextInt();
		
		ArrayList <Integer> varValues = new ArrayList <Integer>(Arrays.asList(exp,funding));
		
		ToolBox tb = new ToolBox(fuzzySets, varValues, centriodSet);
		tb.fuzzify();
		String rule = "project_funding high or team_experience_level expert then risk low";
		tb.addRule(rule);
		String rule2 = "project_funding medium and team_experience_level intermediate or team_experience_level beginner then risk normal";
		tb.addRule(rule2);
		String rule3 = "project_funding verylow then risk high";
		tb.addRule(rule3);
		String rule4 = "project_funding low and team_experience_level beginner then risk high";
		tb.addRule(rule4);
		/*tb.addRule(rule4);
		String rule5 = "dirt large and fabric not soft then washTime verylarge";
		tb.addRule(rule5);
		String rule6 = "dirt large and fabric soft then washTime standard";
		tb.addRule(rule6);*/
		tb.run();
	}
}
