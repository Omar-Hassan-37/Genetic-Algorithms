import java.util.ArrayList;
import java.util.HashMap;

public class Defuzzifier {
	Centriod c;
	WAE e;
	HashMap<String, Double> msValues;
	
	
	
	/**
	 * @param c
	 */
	public Defuzzifier(Centriod c, HashMap<String, Double> msValues) {
		super();
		this.c = c;
		this.msValues = msValues;
		e = new WAE(c.getCentriod(), msValues);
	}

}
