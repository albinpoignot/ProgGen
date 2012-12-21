package gep_dc;

import java.util.ArrayList;

public class GepPopulation {

	private ArrayList<GepKExpression> population;
	//private HashMap<GepKExpression, Double> fitness;
	
	private FitnessStorage finalFitnesses;
	
	/**
	 * Constructor.
	 * @param expr An ArrayList containing all the GepKExprresion that we will use
	 */
	public GepPopulation(ArrayList<GepKExpression> expr) {
		population = expr;
		//fitness = new HashMap<GepKExpression, Double>();
	}
	
	/**
	 * Evaluate all the expression given to the constructor and store the
	 * 20% of the better result in the finalFitnesses field.
	 */
	public void evaluate() {
		
		// Calcule de la taille des 20%
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
			
			// Stockage quoi qu'il arrive, pour debug
			//fitness.put(elem, fit);
			
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
		/*System.out.println("Nb element evaluated : " + fitness.size());
		if(GepMain.DEBUG_SHORT) {
			for (GepKExpression elem : fitness.keySet()) {
				System.out.println("\tExpression : " + elem.toString());
				System.out.println("\t\tFitness : " + fitness.get(elem));
			}
		} else {
			for (GepKExpression elem : fitness.keySet()) {
				System.out.println("\tFitness : " + fitness.get(elem));
			}
		}*/
		
		finalFitnesses.display();
	}
	
	/**
	 * Remove all expression in HashMap which have fitness NaN or Infinity
	 */
	/*public void clear() {
		
		Set<GepKExpression> keySet = fitness.keySet();
		
		Iterator<GepKExpression> itr = keySet.iterator(); 
		while(itr.hasNext()) {

		    GepKExpression element = (GepKExpression) itr.next(); 
		    if(fitness.get(element).isNaN() || fitness.get(element).isInfinite()) {
		    	itr.remove();
		    	population.remove(element);
		    }

		}
	}*/
	
}
