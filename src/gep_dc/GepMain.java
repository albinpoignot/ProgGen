package gep_dc;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class GepMain {

	public static GepData data = new GepData("data/DC_TC_PC_VC_ACEN_MW_DM_NBP.data.csv");
	
	public static GPOperator[] operators = {new GPOperatorAdd(), new GPOperatorDiv(), new GPOperatorExp(), new GPOperatorLog(), 
		new GPOperatorMul(), new GPOperatorNeg(), new GPOperatorSub()};
	
	public static GPTerminalCste[] cste = {new GPTerminalCste(0.0), new GPTerminalCste(1.0), new GPTerminalCste(2.0), 
		new GPTerminalCste(3.0), new GPTerminalCste(4.0), new GPTerminalCste(5.0), new GPTerminalCste(6.0), new GPTerminalCste(7.0), 
		new GPTerminalCste(8.0), new GPTerminalCste(9.0), new GPTerminalCste(10.0)};
	
	public static ArrayList<GPTerminalVar> vars;
	
	public static boolean DEBUG = false;
	public static boolean DEBUG_SHORT = false;
	public static int SIZE_OF_EXPRESSIONS = 30;
	
	public static Random randomer = new Random();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("GEP_DC: Gene Expression Programming for Dielectric Constant symbolic regression");
		
		Date start = new Date();
		
		System.out.println("1. Initialization of variables");
		initVars();
		Date now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("2. Initialization of random expressions");
		start = new Date();
		ArrayList<GepKExpression> expr = new ArrayList<GepKExpression>();
		for(int i=0; i<10000; i++) {
			expr.add(randomInit(SIZE_OF_EXPRESSIONS));
		}
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		
		System.out.println("3. Initialization of the population");
		start = new Date();
			GepPopulation pop = new GepPopulation(expr);
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("4. Evaluation of the initial population");
		start = new Date();
			pop.evaluate();
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("5. Displaying of the best fitness");
		start = new Date();
			pop.displayBest();
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("6. Crossing the expressions");
		start = new Date();
		GepPopulationUtils popUtils = new GepPopulationUtils(pop);
		popUtils.cross(3000);
		
		pop = popUtils.getCrossedPopulation();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("7. Evaluate the new crossed population");
		start = new Date();
			pop.evaluate();
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
		System.out.println("8. Displaying of the best part of the new crossed population");
		start = new Date();
			pop.displayBest();
		now = new Date();
		System.out.println("("+ ((double)(now.getTime() - start.getTime())/1000) +")");
		
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
	
	private static GPOperator randomOp() {

		int rand=randomer.nextInt(operators.length);
		
		return operators[rand];
		
	}
	
	//Cette fonction va couper l'expression 1 aléatoirement et remplir ce qui a été coupé avec l'expression 2
	//à partir de l'index où on a coupé l'expression 1.
	private static GepKExpression GepCrossExpression(GepKExpression expr1, GepKExpression expr2){
			
		int myRand1 = randomer.nextInt(expr1.getSize());
		expr1.rm(myRand1);
					
		for(int i = myRand1; i < (expr2.getSize()-myRand1); i++){
			expr1.add(expr2.getObject(i));
		}
			
			return expr1;
			
		}
	
}
