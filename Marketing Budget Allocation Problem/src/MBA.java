import java.util.ArrayList;

public class MBA {
	
	private Population population;
	private FitnessCalculator fc;
	private InvestmentInfoArray investmentInfo;
	private Chromosome bestSolution;
	private final int maxGenerations = 9000;
	
	
	/**
	 * @param population
	 * @param fc
	 * @param investmentInfo
	 * @param bestSolution
	 * @param totalBudget
	 */
	public MBA( InvestmentInfoArray investmentInfo) {
		super();
		this.fc = new FitnessCalculator(investmentInfo);
		this.investmentInfo = investmentInfo;
		this.bestSolution = null;
		this.population = new Population(fc, 50, new NonUniformMutator(investmentInfo, fc, maxGenerations), investmentInfo, 5);
		//this.population = new Population(fc, 50, new UniformMutator(investmentInfo, fc), investmentInfo, 5);
	}


	public Chromosome GA() {
		for(int i = 0; i < maxGenerations; i++) {
			population.newGeneration();
			bestSolution = population.getBestSolution();
		}
		return bestSolution;
	}
}
