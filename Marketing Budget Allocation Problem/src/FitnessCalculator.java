import java.util.ArrayList;
import java.util.Arrays;

public class FitnessCalculator {
	
	InvestmentInfoArray investmentInfo;
	
	

	/**
	 * @param investmentInfo
	 */
	public FitnessCalculator(InvestmentInfoArray investmentInfo) {
		super();
		this.investmentInfo = investmentInfo;
	}



	public Double getFitness(Chromosome chromosome) {
		ArrayList<Double> c = chromosome.getChromosome();
		double fitness = 0.0;
		for(int i = 0; i < c.size(); i++) {
			fitness += c.get(i)*investmentInfo.get(i).getROI();
		}
		
		return (infeasable(chromosome))? -1.0 : fitness;
	}
	
	private boolean infeasable(Chromosome chromosome) {
		
		
		if(chromosome.getTotal() > investmentInfo.getTotalBudget())
			return true;
		
		for(int i = 0; i < chromosome.getChromosome().size(); i++) {
			double gene = chromosome.getChromosome().get(i);
			
			if(gene < investmentInfo.get(i).getLowerBound() || 
			gene > investmentInfo.get(i).getUpperBound()*investmentInfo.getTotalBudget()) {
				
				return true;
			}
		}
		
		return false;
	}

	
	
}
