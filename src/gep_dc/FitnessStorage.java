package gep_dc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Cette classe permet de gérer les fitness des différentes expressions.
 * Elle gère automatiquement le fait de ne garder que les meilleurs résulats.
 * Pour cela, lors de l'instanciation, il est nécessaire d'indiquer la taille maximum
 * de la liste.
 * 
 * Conceptuellement, le FitnessStorage doit être considéré comme une unique liste.
 * Techniquement, la classe intègre deux ArrayList, l'une contenant les expressions
 * et l'autre les fitness. La classe assure elle-même la concordance des index des
 * deux listes.
 *
 */
public class FitnessStorage {

	/**
	 * Expression avec la fitness minimum
	 */
	private GepKExpression expMinimum;
	
	/**
	 * Liste des exceptions
	 */
	private ArrayList<GepKExpression> expressions;
	
	/**
	 * Fitness minimum
	 */
	private Double fitMinimum;
	
	/**
	 * Liste des fitness
	 */
	private ArrayList<Double> fitnesses;
	
	/**
	 * Fitness maximum actuellement
	 */
	private Double maximum;
	
	/**
	 * Taille maximum des listes
	 */
	private int maxSize;
	
	/**
	 * Constructeur
	 * @param size Taille maximum des listes
	 */
	public FitnessStorage(int size) {
		expressions = new ArrayList<GepKExpression>();
		fitnesses = new ArrayList<Double>();
		maximum = 0.0;
		maxSize = size;
		fitMinimum = Double.MAX_VALUE;
	}
	
	/**
	 * Display function
	 */
	public void display() {
		System.out.println("=== FitnessStore ===");
		System.out.println("Nb final element : " + expressions.size());
		
		if(GepMain.DEBUG_SHORT) {
			int i = 0;
			for (GepKExpression elem : expressions) {
				System.out.println("\tExpression : " + elem.toString());
				System.out.println("\t\tFitness : " + fitnesses.get(i));
				++i;
			}
		} else {
			for (Double fitness : fitnesses) {
				System.out.println("\tFitness : " + fitness);
			}
		}
	}
	
	/**
	 * Retourne l'expression avec la fitness minimum
	 * @return
	 */
	public GepKExpression getExpMinimum() {
		return expMinimum;
	}
	
	public ArrayList<GepKExpression> getExpressions() {
		return expressions;
	}
	
	public ArrayList<Double> getFitnesses() {
		return fitnesses;
	}
	
	/**
	 * Retourne la fitness minimum
	 * @return
	 */
	public Double getFitnessMinimum() {
		return fitMinimum;
	}
	
	/**
	 * Retourne la fitness maximum
	 * @return la fitness maximale
	 */
	public Double getMaximum() {
		return maximum;
	}
	
	/**
	 * Insert a couple only if needed : 
	 * 		the current size should be < 20% of the population
	 * 		the value should be < current maximum value
	 * @return true if inserted, false if not
	 */
	public boolean insert(GepKExpression expression, Double value) {
		
		// Si la taille maximum n'est pas encore atteinte,
		// on ajoute
		if(expressions.size() < maxSize) {
			expressions.add(expression);
			fitnesses.add(value);
			
			// Mise à jour du maximum si nécessaire
			if(value > maximum) {
				maximum = value;
			}
			
			if(value < fitMinimum) {
				fitMinimum = value;
				expMinimum = expression;
			}
			return true;
		} else {
			// Sinon, on vérifie que la valeur est inférieure
			// au maximum, et on l'ajoute si oui tout en retirant
			// la valeur maximum
			if(value < maximum) {
				
				// Index of the max value
				int maxIndex = fitnesses.indexOf(maximum);
				
				// Remove the max value in the two arrays
				expressions.remove(maxIndex);
				fitnesses.remove(maxIndex);
				
				// Add the new value in the two arrays
				expressions.add(expression);
				fitnesses.add(value);
				
				// Mise à jour du maximum
				maximum = Collections.max(fitnesses);
				
				if(value < fitMinimum) {
					fitMinimum = value;
					expMinimum = expression;
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Retourne la taille des listes
	 * @return taille des listes
	 */
	public int size() {
		return expressions.size();
	}
	
}
