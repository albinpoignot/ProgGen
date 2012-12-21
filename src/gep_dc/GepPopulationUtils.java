package gep_dc;

import java.util.ArrayList;

public class GepPopulationUtils {
	
	private GepPopulation populationGeneree;
	private GepPopulation populationParents;
	
	public GepPopulationUtils(GepPopulation population) {
		populationParents = population;
	}
	
	/**
	 * 
	 * @param size Taille de la population générée voulue
	 */
	public void cross(int size) {
		
		ArrayList<GepKExpression> list = populationParents.getFitnessStorage().getExpressions();
		
		for(int i=0; i < size; i++) {
			
			// Récupération des index aléatoires
			int indexPar1 = GepMain.randomer.nextInt(populationParents.getSize());
			int indexPar2 = GepMain.randomer.nextInt(populationParents.getSize());
			int coupure = GepMain.randomer.nextInt(GepMain.SIZE_OF_EXPRESSIONS);
			
			// Récupération des parents
			GepKExpression parent1 = populationParents.getExpression(indexPar1);
			GepKExpression parent2 = populationParents.getExpression(indexPar2);
			
			GepKExpression enfant = new GepKExpression(GepMain.SIZE_OF_EXPRESSIONS);
			
			// Stockage partie du parent 1
			for(int j = 0; j <= coupure; j++) {
				double rand = GepMain.randomer.nextDouble();
				if(rand > 0.3) {
					enfant.add(parent1.get(j));
				} else {
					if(parent1.get(j).isOperator()) {
						int rand2 = GepMain.randomer.nextInt(GepMain.operators.length);
						enfant.add(GepMain.operators[rand2]);
					} else if(parent1.get(j).isVariable()){
						int rand2 = GepMain.randomer.nextInt(GepMain.vars.size());
						enfant.add(GepMain.vars.get(rand2));
					} else {
						int rand2 = GepMain.randomer.nextInt(GepMain.cste.length);
						enfant.add(GepMain.cste[rand2]);
					}
				}
			}
			
			// Stockage partie du parent 2
			for(int j = coupure+1; j < parent2.getSize(); j++) {
				double rand = GepMain.randomer.nextDouble();
				if(rand > 0.3) {
					enfant.add(parent2.get(j));
				} else {
					if(parent2.get(j).isOperator()) {
						int rand2 = GepMain.randomer.nextInt(GepMain.operators.length);
						enfant.add(GepMain.operators[rand2]);
					} else if(parent2.get(j).isVariable()){
						int rand2 = GepMain.randomer.nextInt(GepMain.vars.size());
						enfant.add(GepMain.vars.get(rand2));
					} else {
						int rand2 = GepMain.randomer.nextInt(GepMain.cste.length);
						enfant.add(GepMain.cste[rand2]);
					}
				}
			}
			
			list.add(enfant);
			
		}
		
		populationGeneree = new GepPopulation(list);
	}
	
	public GepPopulation getCrossedPopulation() {
		return populationGeneree;
	}

}
