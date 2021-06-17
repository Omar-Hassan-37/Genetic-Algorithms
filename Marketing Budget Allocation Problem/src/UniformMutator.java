import java.util.ArrayList;

public class UniformMutator implements IMutator {
	
	InvestmentInfoArray investmentInfo;
	FitnessCalculator fc;
	
	public UniformMutator(InvestmentInfoArray investmentInfo, FitnessCalculator fc)
	{
		super();
		this.investmentInfo = investmentInfo;
		this.fc = fc;
	}
	
	@Override
	public Chromosome mutate(Chromosome c, int t) {
		ArrayList<Double> chromosome = (ArrayList<Double>)c.getChromosome().clone();
		
		for(int i = 0; i < chromosome.size(); i++) {
			double pm = Math.random();
			if(pm < 0.1) {
				int r0 = (int)Math.floor(Math.random() * chromosome.size());
				double delta = 0;
				//double deltaL = investmentInfo.get(i).getLowerBound();
				//deltaL = (deltaL == -1.0)? chromosome.get(i) : chromosome.get(i) - deltaL;
				//double r1 = Math.random();
				
				delta = (r0 > 0.5)? calcDeltaU(investmentInfo.get(i), chromosome.get(i), c.getTotal())
						: calcDeltaL(investmentInfo.get(i), chromosome.get(i), c.getTotal());
				
				double r2 = (int)Math.floor(Math.random() * delta);
				
				if(r0 <= 0.5)
				{
					double newGene = chromosome.get(i) - r2;
					chromosome.set(i, newGene);
					if(chromosome.get(i) < 0.0) {
						System.out.println(chromosome.get(i));
					}
				}
				else
				{
					double newGene = chromosome.get(i) + r2;
					chromosome.set(i, newGene);
					if(chromosome.get(i) < 0.0) {
						System.out.println(chromosome.get(i));
					}
				}
			}
			else {
				continue;
			}
		}
		
		Chromosome temp = new Chromosome(chromosome, fc);
		
		return (temp.getInfeasable())? c : temp;
	}
	/*private double calcDeltaU(InvestmentInfo info, double current, double currentTotal) {
		double upperLimitToAdd = info.getUpperBound();
		
		upperLimitToAdd = (upperLimitToAdd == -1.0)? investmentInfo.getTotalBudget()-currentTotal:
			(investmentInfo.getTotalBudget() - currentTotal)*upperLimitToAdd;
		
		return upperLimitToAdd;
		
	}
	
	private double calcDeltaL(InvestmentInfo info, double current, double currentTotal) {
		double upperLimitToSubtract =  info.getLowerBound();
		upperLimitToSubtract = (upperLimitToSubtract == -1.0)? current : current-upperLimitToSubtract;
		return upperLimitToSubtract;
	}*/
	
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
