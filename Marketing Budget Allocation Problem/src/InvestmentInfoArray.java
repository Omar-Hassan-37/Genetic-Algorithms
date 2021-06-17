import java.util.ArrayList;

public class InvestmentInfoArray {
	private ArrayList<InvestmentInfo> investmentInfo;
	private double totalBudget;
	/**
	 * @param investmentInfo
	 * @param remainingBudget
	 */
	public InvestmentInfoArray(ArrayList<InvestmentInfo> investmentInfo, double totalBudget) {
		super();
		this.investmentInfo = investmentInfo;
		this.totalBudget = totalBudget;
	}
	public ArrayList<InvestmentInfo> getInvestmentInfo() {
		return investmentInfo;
	}
	public void setInvestmentInfo(ArrayList<InvestmentInfo> investmentInfo) {
		this.investmentInfo = investmentInfo;
	}
	
	public double getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}
	public InvestmentInfo get(int index) {
		return investmentInfo.get(index);
	}
	public boolean add(InvestmentInfo e) {
		return investmentInfo.add(e);
	}
	public int size() {
		return investmentInfo.size();
	}
	
	public double sumLowerBound() {
		double sum = 0.0;
		
		for(InvestmentInfo i : investmentInfo) {
			
			sum += i.getLowerBound();
		}
		return sum;
	}
	
	
}
