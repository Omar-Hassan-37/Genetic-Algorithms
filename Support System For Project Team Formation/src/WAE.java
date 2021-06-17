import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.naming.ldap.SortControl;

public class WAE {
	private HashMap<String, Double> centriods;
	private HashMap<String, Double> msValue;
	/**
	 * @param centriods
	 * @param msValue
	 */
	public WAE(HashMap<String, Double> centriods, HashMap<String, Double> msValue) {
		super();
		this.centriods = centriods;
		this.msValue = msValue;
	}
	public HashMap<String, Double> getCentriods() {
		return centriods;
	}
	public void setCentriods(HashMap<String, Double> centriods) {
		this.centriods = centriods;
	}
	public HashMap<String, Double> getMsValue() {
		return msValue;
	}
	public void setMsValue(HashMap<String, Double> msValue) {
		this.msValue = msValue;
	}
	
	public double calcWAE()
	{
		
		ArrayList<Double> centroidsArray = new ArrayList<Double>();
		for(Double d : centriods.values()) {
			centroidsArray.add(d);
		}
		//Collections.sort(centroidsArray);
		//System.out.println(centroidsArray);
		//System.out.println(msValue);
		double prod_sum = 0;
		double crisp_value = 0;
		double msValues_sum = 0;
		for(String s : centriods.keySet())
		{
			prod_sum += msValue.get(s) * centriods.get(s);
			msValues_sum += msValue.get(s);
		}
		
		/*for(int i = 0; i < centriods.size(); i++)
		{
			prod_sum += msValue.get(i) * centroidsArray.get(i);
			msValues_sum += msValue.get(i);
		}*/
		
		crisp_value = prod_sum / msValues_sum;
		
		return crisp_value;
	}
	private void sort(ArrayList<Double> centroidsArray) {
		// TODO Auto-generated method stub
		
	}
}
