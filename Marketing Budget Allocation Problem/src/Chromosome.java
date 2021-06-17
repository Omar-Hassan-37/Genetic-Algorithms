import java.util.ArrayList;

public class Chromosome {
	
	private ArrayList<Double> chromosome;
	private Double fitness;
	private Double total;
	private boolean infeasable;

	public Chromosome(ArrayList<Double> chromosome, FitnessCalculator fc) {
		super();
		this.chromosome = chromosome;
		this.total = 0.0;
		
		for(Double d : chromosome) {
			this.total += d;
		}
		this.fitness = fc.getFitness(this);
		this.infeasable = this.fitness.equals(-1.0);
	}
	public boolean getInfeasable() {
		return this.infeasable;
	}
	public ArrayList<Double> getChromosome() {
		return chromosome;
	}

	public void setChromosome(ArrayList<Double> chromosome, FitnessCalculator fc) {
		this.chromosome = chromosome;
		this.total = 0.0;
		
		
		for(Double d : chromosome) {
			this.total += d;
		}
		this.fitness = fc.getFitness(this);
		this.infeasable = this.fitness.equals(-1.0);
	}
	
	

	public Double getTotal() {
		return total;
	}

	public double getFitness() {
		
		return fitness;
	}
	
	@Override
	public String toString() {
		return "Chromosome [chromosome=" + chromosome + ", fitness=" + fitness + ", total=" + total + ", infeasable="
				+ infeasable + "]\n";
	}
	
	


	
	
	
	
	
	
	
}
