package gep_dc;

import java.util.ArrayList;
import java.util.Random;

public class GepMain {

	public static GepData data = new GepData("data/DC_TC_PC_VC_ACEN_MW_DM_NBP.data.csv");
	
	public static GPOperator[] operators = {new GPOperatorAdd(), new GPOperatorDiv(), new GPOperatorExp(), new GPOperatorLog(), 
		new GPOperatorMul(), new GPOperatorNeg(), new GPOperatorSub()};
	
	public static ArrayList<GPTerminalVar> vars; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("GEP_DC: Gene Expression Programming for Dielectric Constant symbolic regression");
		
		/*//creation des contenus de l'arbre
		GPTerminalCste cst1 = new GPTerminalCste(3.13343);
		GPTerminalCste cst2 = new GPTerminalCste(0.233);
		GPTerminalCste cst3 = new GPTerminalCste(432.11);
		GPOperatorAdd add = new GPOperatorAdd();
		GPOperatorMul mul = new GPOperatorMul();
		
		//creation de la racine
		GepExpressionNode root = new GepExpressionNode(add);
		//creation de l'arbre
		GepExpressionTree tree = new GepExpressionTree(root);
		//creation d'un fils gauche
		GepExpressionNode child1 = new GepExpressionNode(add);
		tree.add(root, child1);
		//creation d'un fils droit
		GepExpressionNode child2 = new GepExpressionNode(cst1);
		tree.add(root, child2);
		//creation de 2 fils pour child1
		GepExpressionNode child3 = new GepExpressionNode(cst2);
		GepExpressionNode child4 = new GepExpressionNode(cst3);
		tree.add(child1, child3);
		tree.add(child1, child4);
		
		System.out.println(tree.toString());
		
		System.out.print("Eval = ");
		System.out.println(tree.eval());*/
		
		initVars();
		
		for (GPTerminalVar var : vars) {
			System.out.println(var.toString());
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

		// TODO Equilibrer les proba des vars et des cste 
		
		Random r = new Random();
		int rand=r.nextInt(2);
		
		if(rand == 1) {
			Random r1 = new Random();
			int rand1=r1.nextInt(11);
			
			return new GPTerminalCste(rand1);
		} else {
			Random r2 = new Random();
			int rand2=r2.nextInt(data.getNbVars());
			
			return new GPTerminalVar(data, rand2, data.getVarNames().get(rand2));
		}
		
	}
	
	/**
	 * Génère une kEpression
	 */
	private static GepKExpression randomInit(int size) {
		
		GepKExpression expr = new GepKExpression(size);
		
		// TODO Forcer des opérateurs dans la tête
		
		// TODO Générer les opérateurs contenus dans une liste
		
		for(int i = 0; i < expr.getHead(); i++) {
			Random r = new Random();
			int rand=r.nextInt(2);
			
			if(rand == 1) {
				expr.add(randomOp());
			} else {
				expr.add(randomTerm());
			}
			
		}
		
		// TODO remplir tail
		
		return expr;
		
	}
	
	private static GPOperator randomOp() {

		Random r = new Random();
		int rand=r.nextInt(7);
		
		switch(rand) {
			
			case 0:
				return new GPOperatorAdd();
				
			case 1:
				return new GPOperatorDiv();
				
			case 2:
				return new GPOperatorExp();
				
			case 3:
				return new GPOperatorLog();
				
			case 4:
				return new GPOperatorMul();
				
			case 5:
				return new GPOperatorNeg();
				
			default:
				return new GPOperatorSub();
				
		}
		
	}

}
