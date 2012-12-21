package gep_dc;

import java.util.ArrayList;

public class GepPopulation {

	private ArrayList<GepKExpression> population;
	
	private FitnessStorage finalFitnesses;
	
	/**
	 * Constructor.
	 * @param expr An ArrayList containing all the GepKExprresion that we will use
	 */
	public GepPopulation(ArrayList<GepKExpression> expr) {
		population = expr;
	}
	
	/**
	 * Evaluate all the expression given to the constructor and store the
	 * 20% of the better result in the finalFitnesses field.
	 */
	public void evaluate() {
		
		// Calcul de la taille des 20%
		int twentyPercent = (int)(population.size() * 0.20);
		
		// Init the final storage
		finalFitnesses = new FitnessStorage(twentyPercent);
		
		// Parcours de la population
		for (GepKExpression elem : population) {
			
			// Création de l'arbre
			GepExpressionTree tree = elem.getExpressionTree();
			
			double nbSubstances = (double)GepMain.data.getNbSubstances();
			
			// Parcours des substances et calcule d'une partie de la fitness
			double avgMinusDC = 0;
			for(int i = 0; i<nbSubstances; i++) {
				avgMinusDC += Math.abs(tree.eval(i) - GepMain.data.getDC(i));
			}
			
			// Calcul final de la fitness
			Double fit = avgMinusDC / nbSubstances;
			
			// Ajout dans le stockage final si on est pas dans le cas de
			// NaN ou de Infinity
			if(!Double.isNaN(fit) && !Double.isInfinite(fit))
				finalFitnesses.insert(elem, fit);
			
		}
	}
	
	
	/**
	 * Display function
	 */
	public void display() {
		System.out.println("=== Display everything ===");
		finalFitnesses.display();
	}
	
	/**
	 * Display the best element
	 */
	public void displayBest() {
		System.out.println("\t\t=== Display best element ===");
		System.out.println("\t\tExpression : " + finalFitnesses.getExpMinimum().toString());
		System.out.println("\t\tFitness : " + finalFitnesses.getFitnessMinimum());
	}
	
	public FitnessStorage getFitnessStorage() {
		return finalFitnesses;
	}
	
	public int getSize() {
		return finalFitnesses.size();
	}
	
	public GepKExpression getExpression(int index) {
		return finalFitnesses.getExpressions().get(index);
	}
	
}
