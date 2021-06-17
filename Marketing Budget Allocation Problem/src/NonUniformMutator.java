import java.util.ArrayList;

public class NonUniformMutator implements IMutator {

	
	InvestmentInfoArray investmentInfo;
	FitnessCalculator fc;
	double b = 1;
	int T = 9000000;
	/**
	 * @param investmentInfo
	 */
	public NonUniformMutator(InvestmentInfoArray investmentInfo, FitnessCalculator fc, int T) {
		super();
		this.investmentInfo = investmentInfo;
		this.fc = fc;
		this.T = T;
	}

	@Override
	public Chromosome mutate(Chromosome c, int t) {
		
		ArrayList<Double> chromosome = (ArrayList<Double>)c.getChromosome().clone();
		
		for(int i = 0; i < chromosome.size(); i++) {
			double pm = Math.random();
			if(pm < 0.1)
			{
				double r1 = Math.random();
				
				double y = 0.0;
				
				double delta = 0.0;
				
				if(r1 >= 0.5) {
					y = calcDeltaU(investmentInfo.get(i), chromosome.get(i), c.getTotal());
					delta = calcDelta(y, Math.random(), t);
					
					chromosome.set(i, chromosome.get(i)+delta);
					if(chromosome.get(i) < 0.0) {
						System.out.println(chromosome.get(i));
					}
				}
				else {
					y = calcDeltaL(investmentInfo.get(i), chromosome.get(i), c.getTotal());
					delta = calcDelta(y, Math.random(), t);
					
					chromosome.set(i, chromosome.get(i)-delta);
					if(chromosome.get(i) < 0.0) {
						System.out.println(chromosome.get(i));
					}
				}
			}
			else
			{
				continue;
			}
		}
		
		Chromosome temp = new Chromosome(chromosome, fc);
		
		return (temp.getInfeasable())? c : temp;
	}
	
	private double calcDelta(double y, double r, int t) {
		double delta = y*(1-Math.pow(r, Math.pow(1-t/(double)T,b )));
		
		return delta;
	}

	private double calcDeltaU(InvestmentInfo info, double current, double currentTotal) {
		double upperLimitToAdd = info.getUpperBound();
		
		upperLimitToAdd = current-(investmentInfo.getTotalBudget())*upperLimitToAdd;
		
		return upperLimitToAdd;
		
	}
	
	private double calcDeltaL(InvestmentInfo info, double current, double currentTotal) {
		double upperLimitToSubtract = info.getLowerBound();
		upperLimitToSubtract = current-upperLimitToSubtract;
		return upperLimitToSubtract;
	}
	
	
}
