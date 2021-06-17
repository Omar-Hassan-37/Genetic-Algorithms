import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.math.*;
public class Population {
	
	private ArrayList<Chromosome> population;
	private FitnessCalculator fc;
	private Integer population_size;
	private IMutator mutator;
	private int nGeneration;
	private InvestmentInfoArray investmentInfo;
	private UniformMutator um;
	private int k;
	
	
	
	/**
	 * @param population
	 * @param fc
	 * @param population_size
	 * @param mutator
	 * @param investmentInfo
	 */
	public Population( FitnessCalculator fc, Integer population_size, IMutator mutator,
			InvestmentInfoArray investmentInfo, int k) {
		this.fc = fc;
		this.population_size = population_size;
		this.mutator = mutator;
		this.investmentInfo = investmentInfo;
		this.initializeGeneration0();
		this.k = k;
	}


	public void initializeGeneration0() {
		this.population = new ArrayList<Chromosome>();
		//double sumLowerBound = investmentInfo.sumLowerBound();
		for(int i = 0; i < population_size; i++) {
			
			population.add(generateChromosome());
		}
		
		int x = 0;
	}
	
	public Chromosome generateChromosome() {
		
		double sumLowerBound = investmentInfo.sumLowerBound();
		
		ArrayList<Double> currentChromosome = new ArrayList<Double>();
		double remainingBudget = investmentInfo.getTotalBudget();
		for(int j = 0; j < investmentInfo.size(); j++) {
			
			sumLowerBound -= investmentInfo.get(j).getLowerBound();
			
			Double lowerBound = investmentInfo.get(j).getLowerBound();
			
			Double upperBound = investmentInfo.get(j).getUpperBound();
			
			upperBound = upperBound*investmentInfo.getTotalBudget();
			
			Double gene = lowerBound + Math.random()*(upperBound-lowerBound);
			
			gene = (remainingBudget - gene >= sumLowerBound)? gene: remainingBudget-sumLowerBound;
			
			if(gene < 0.0) {
				System.out.println(gene);
			}
			currentChromosome.add(gene);
			
			if(lowerBound > upperBound) {
				System.out.println("");
			}
			remainingBudget -= gene;
			
			nGeneration = 0;
		}
		
		return new Chromosome(currentChromosome, fc);
	}
	
	public void newGeneration() {
		ArrayList<Chromosome> elite = getElite();
		ArrayList<Chromosome> newGeneration = getMatingPool(elite);
		
		newGeneration = crossover(newGeneration, elite);
		
		newGeneration = mutation(newGeneration);
		
		
		
		this.population = newGeneration;
		
		
		
		nGeneration++;
	}
	
	private ArrayList<Chromosome> mutation(ArrayList<Chromosome> newGeneration) {
		ArrayList<Chromosome> result = new ArrayList<Chromosome>();
		
		for(Chromosome c : newGeneration) {
			result.add(mutator.mutate(c, nGeneration));
		}
		return result;
	}


	private ArrayList<Chromosome> crossover(ArrayList<Chromosome> arr, ArrayList<Chromosome> elite){
		ArrayList<Chromosome> result = new ArrayList<Chromosome>();
		
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		
		for(int i = 0; i < arr.size(); i++) {
			candidates.add(i);
		}
		
		for(int i = 0; i < arr.size()-k; i+=2) {
			
			int i1 = candidates.remove(randIndex(candidates.size()));
			int i2 = candidates.get(randIndex(candidates.size()));
			
			Chromosome c1 = arr.get(i1);
			Chromosome c2 = arr.get(i2);
			
			Chromosome[] offsprings = twoPointCrossover(c1, c2);
			
			result.add(offsprings[0]);
			result.add(offsprings[1]);
			
		}
		
		for(Chromosome c : elite) {
			result.add(c);
		}
		
		return result;
		
		
	}
	
	private Chromosome[] twoPointCrossover(Chromosome c1, Chromosome c2) {
		double r0 = Math.random();
		
		if(r0 < 0.4) {
			return new Chromosome[] {c1,c2};
		}else {
			ArrayList<Double> newC1 = new ArrayList<Double>(), newC2 = new ArrayList<Double>();
			int size = c1.getChromosome().size();
			int point1 = (int)Math.floor(Math.random()*(size/2));
			int point2 = (int)Math.floor(Math.random()*size/2)+ size/2;
			
			for(int i = 0; i < point1; i++) {
				newC1.add(c1.getChromosome().get(i));
				newC2.add(c2.getChromosome().get(i));
				
			}
			
			for(int i = point1; i < point2; i++) {
				newC1.add(c2.getChromosome().get(i));
				newC2.add(c1.getChromosome().get(i));
				
			}
			for(int i = point2; i < c1.getChromosome().size(); i++) {
				
				newC1.add(c1.getChromosome().get(i));
				newC2.add(c2.getChromosome().get(i));
			}
			
			Chromosome offspring1 = new Chromosome(newC1, fc);
			Chromosome offspring2 = new Chromosome(newC2, fc);
			
			return new Chromosome[] {(offspring1.getInfeasable())?c1:offspring1, (offspring2.getInfeasable())?c2:offspring2};
			
		}
		
	}
	
	
	private ArrayList<Chromosome> handleInfeasable(ArrayList<Chromosome> newGeneration){
		
		newGeneration.removeIf(new Predicate<Chromosome>() {

			@Override
			public boolean test(Chromosome t) {

				return t.getInfeasable();
			}
			
		});
		
		int diff = population_size - newGeneration.size();
		
		for(int i = 0; i < diff; i++) {
			newGeneration.add(generateChromosome());
		}
		
		return newGeneration;
		
		
		
		
	}
	
	
	
	
	
	
	public Chromosome getBestSolution() {
		Chromosome best = population.get(0);
		
		for(Chromosome c : population) {
			if (c.getFitness() > best.getFitness()) {
				best = c;
			}
		}
		return best;
	}
	
	private ArrayList<Chromosome> getMatingPool(ArrayList<Chromosome> elite) {
		
		
		ArrayList<Chromosome> matingPool = new ArrayList<Chromosome>();
		for(int i = 0; i < population.size(); i++) {
			matingPool.add( tournament(population.get(randIndex(population.size())), population.get(randIndex(population.size()))) );
		}
		
		for(Chromosome c : elite) {
			matingPool.add(c);
		}
		
		return matingPool;
		
	}
	
	private ArrayList<Chromosome> getElite(){
		ArrayList<Chromosome> elite = new ArrayList<Chromosome>();
		for(int i = 0; i < k; i++) {
			Chromosome cur = this.population.get(i);
			for(int j = 0; j < this.population.size(); j++) {
				if(cur.getFitness() < population.get(j).getFitness() && 
						!elite.contains(population.get(j))) {
					cur = population.get(j);
				}
				
				
			}
			elite.add(cur);
			population.remove(cur);
			
		}
		return elite;
		
	}
	
	private int randIndex(int upperBound) {
		return (int)Math.floor(Math.random()*upperBound);
	}
	
	private Chromosome tournament(Chromosome c1, Chromosome c2) {
		return (c1.getFitness() > c2.getFitness())? c1 : c2;
	}


	public ArrayList<Chromosome> getPopulation() {
		return population;
	}
	
	public void setPopulation(ArrayList<Chromosome> population) {
		this.population = population;
	}
	
	public FitnessCalculator getFc() {
		return fc;
	}
	
	public void setFc(FitnessCalculator fc) {
		this.fc = fc;
	}
	
	public IMutator getMutator() {
		return mutator;
	}
	
	public void setMutator(IMutator mutator) {
		this.mutator = mutator;
	}
	
	public InvestmentInfoArray getInvestmentInfo()
	{
		return investmentInfo;
	}
	
}
