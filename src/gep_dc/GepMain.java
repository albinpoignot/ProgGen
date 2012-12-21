package gep_dc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GepMain {

	/**
	 * Données à utiliser
	 */
	public static GepData data = new GepData("data/DC_TC_PC_VC_ACEN_MW_DM_NBP.data.csv");
	
	/**
	 * Liste des opérateurs disponibles (instanciés)
	 */
	public static GPOperator[] operators = {new GPOperatorAdd(), new GPOperatorDiv(), new GPOperatorExp(), new GPOperatorLog(), 
		new GPOperatorMul(), new GPOperatorNeg(), new GPOperatorSub()};
	
	/**
	 * Liste des constante disponibles (instanciées)
	 */
	public static GPTerminalCste[] cste = {new GPTerminalCste(0.0), new GPTerminalCste(1.0), new GPTerminalCste(2.0), 
		new GPTerminalCste(3.0), new GPTerminalCste(4.0), new GPTerminalCste(5.0), new GPTerminalCste(6.0), new GPTerminalCste(7.0), 
		new GPTerminalCste(8.0), new GPTerminalCste(9.0), new GPTerminalCste(10.0)};
	
	/**
	 * Liste des variables disponibles (instanciées)
	 */
	public static ArrayList<GPTerminalVar> vars;
	
	/**
	 * Constante de Debug
	 */
	public static boolean DEBUG = false;
	
	/**
	 * Constante de Debug 2
	 */
	public static boolean DEBUG_SHORT = false;
	
	/**
	 * Taille des expressions
	 */
	public static int SIZE_OF_EXPRESSIONS = 30;
	
	/**
	 * Nombre d'expressions générées au départ
	 */
	public static int NUMBER_OF_MEMBERS = 10000;
	
	/**
	 * Nombre de population croisée qui seront calculées
	 */
	public static int NUMBER_OF_CROSS = 30;
	
	/**
	 * Randomer général utilisé dans tout le projet
	 */
	public static Random randomer = new Random();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("GEP_DC: Gene Expression Programming for Dielectric Constant symbolic regression");
		
		/*
		 * Step 1 : Initialisation des variables 
		 */
		Date start = new Date();
		
		System.out.println("1. Initialization of variables");
		initVars();
		Date now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		/*
		 * Step 2 : Initialisation des expressions aléatoires 
		 */
		System.out.println("2. Initialization of random expressions");
		start = new Date();
		ArrayList<GepKExpression> expr = new ArrayList<GepKExpression>();
		for(int i=0; i<NUMBER_OF_MEMBERS; i++) {
			expr.add(randomInit(SIZE_OF_EXPRESSIONS));
		}
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		/*
		 * Step 3 : Initialisation de la population et du Chart
		 */
		System.out.println("3. Initialization of the population");
		start = new Date();
			GepPopulation pop = new GepPopulation(expr);
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
				
		XYSeries series = new XYSeries("Fitness");
		XYDataset xyDataset = new XYSeriesCollection(series);
		Chart chart = new Chart("Gep Programming", "Fitnesses chart", xyDataset);
		chart.pack();
		chart.setVisible(true);
		
		Double x = 0.0;
		
		/*
		 * Step 4 : Première évaluation de la population
		 */
		System.out.println("4. Evaluation of the initial population");
		start = new Date();
			pop.evaluate();
		now = new Date();
		series.add(x++, pop.getFitnessStorage().getFitnessMinimum());
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("5. Displaying of the best initial fitness");
		start = new Date();
			pop.displayBest();
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("6. Launching...");
		
		/*
		 * Step 5 : Crossing et morphing
		 */
		for(int i=0; i<NUMBER_OF_CROSS; i++) {
			System.out.println("==========================================================");
			System.out.println("\t6.1 Crossing the expressions");
			start = new Date();
			GepPopulationUtils popUtils = new GepPopulationUtils(pop);
			popUtils.cross(3000);
			
			pop = popUtils.getCrossedPopulation();
			now = new Date();
			System.out.println("\t("+ ((double)(now.getTime() - start.getTime())/1000) +")");
			
			System.out.println("\t6.2 Evaluate the new crossed population");
			start = new Date();
				pop.evaluate();
			now = new Date();
			System.out.println("\t("+ ((double)(now.getTime() - start.getTime())/1000) +")");
			
			System.out.println("\t6.3 Displaying of the best part of the new crossed population");
			start = new Date();
				pop.displayBest();
			now = new Date();
			System.out.println("\t("+ ((double)(now.getTime() - start.getTime())/1000) +")");
			
			series.add(x++, pop.getFitnessStorage().getFitnessMinimum());
			
		}
		
	}
	
	/**
	 * Initialise le tableau des variables
	 */
	private static void initVars() {
		
		vars = new ArrayList<GPTerminalVar>();
		
		// Parcours des variables puis création et ajout dans le tableau
		for(int i=0; i<data.getNbVars(); i++) {
			vars.add(new GPTerminalVar(data, i, data.getVarNames().get(i)));
		}
		
	}
	
	/**
	 * Renvoie aléatoirement un terminal : Soit une constante soit une variable
	 * @return un GPTerminal
	 */
	private static GPTerminal randomTerm() { 
		
		int nbConst = 10;
		int nbVars = vars.size();
		int nbElem = nbConst + nbVars;
		
		
		int rand=randomer.nextInt(nbElem);
		
		if(rand < nbElem / 2) {
			Random r1 = new Random();
			int rand1=r1.nextInt(nbConst);
			
			return new GPTerminalCste(rand1);
		} else {
			Random r2 = new Random();
			int rand2=r2.nextInt(nbVars);
			
			return vars.get(rand2);
		}
		
	}
	
	/**
	 * Génère une kExpression
	 */
	private static GepKExpression randomInit(int size) {
		
		GepKExpression expr = new GepKExpression(size);
					
		for(int i = 0; i < expr.getHead(); i++) {
			expr.add(randomOp());
		}
		
		for(int i = 0; i < expr.getTail(); i++) {
			expr.add(randomTerm());			
		}
		
		return expr;
		
	}
	
	/**
	 * Renvoi un opérateur
	 * @return renvoi un opérateur, aléatoire
	 */
	private static GPOperator randomOp() {

		int rand=randomer.nextInt(operators.length);
		
		return operators[rand];
		
	}
	
}
