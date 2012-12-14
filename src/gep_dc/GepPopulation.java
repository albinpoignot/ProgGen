package gep_dc;

import java.util.ArrayList;
import java.util.HashMap;

public class GepPopulation {

	private ArrayList<GepKExpression> population;
	private HashMap<GepKExpression, Double> fitness;
	
	public GepPopulation() {
		population = new ArrayList<GepKExpression>();
		fitness = new HashMap<GepKExpression, Double>();
	}
	
	public GepPopulation(ArrayList<GepKExpression> expr) {
		population = expr;
		fitness = new HashMap<GepKExpression, Double>();
	}
	
	public void evaluate() {
		// Parcours de la population
		for (GepKExpression elem : population) {
			GepExpressionTree tree = elem.getExpressionTree();
			
			double nbSubstances = (double)GepMain.data.getNbSubstances();
			
			// Parcours des substances
			double avgMinusDC = 0;
			for(int i = 0; i<nbSubstances; i++) {
				avgMinusDC += Math.abs(tree.eval(i) - GepMain.data.getDC(i));
			}
			
			// Calcul de la fitness et affichage
			fitness.put(elem, avgMinusDC / nbSubstances );
		}
	}
	
	public void display() {
		System.out.println("=== Display ===");
		System.out.println("Nb element evaluated : " + fitness.size());
		
		if(GepMain.DEBUG_SHORT) {
			for (GepKExpression elem : fitness.keySet()) {
				System.out.println("\tExpression : " + elem.toString());
				System.out.println("\t\tFitness : " + fitness.get(elem));
			}
		} else {
			for (GepKExpression elem : fitness.keySet()) {
				System.out.println("\tFitness : " + fitness.get(elem));
			}
		}
	}
	
}
