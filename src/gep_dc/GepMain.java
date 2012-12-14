package gep_dc;

import java.util.ArrayList;
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("GEP_DC: Gene Expression Programming for Dielectric Constant symbolic regression");
		
		initVars();
		
		ArrayList<GepKExpression> expr = new ArrayList<GepKExpression>();
		for(int i=0; i<1000; i++) {
			expr.add(randomInit(30));
		}
		
		GepPopulation pop = new GepPopulation(expr);
		pop.evaluate();
		pop.display();
		
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
		
		Random r = new Random();
		int rand=r.nextInt(nbElem);
		
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
		
		int tail = expr.getSize() -(expr.getHead());
		
					
		for(int i = 0; i < expr.getHead(); i++) {
			expr.add(randomOp());
		}
		
		for(int i = 0; i < tail; i++) {
			expr.add(randomTerm());			
		}
		
		return expr;
		
	}
	
	private static GPOperator randomOp() {

		Random r = new Random();
		int rand=r.nextInt(operators.length);
		
		return operators[rand];
		
	}
	
	

}
