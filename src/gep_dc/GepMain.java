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
		
		for(int i=0; i<15; i++) {
			GepKExpression expr = randomInit(14);
			GepExpressionTree tree = expr.getExpressionTree();
			System.out.println(tree.eval());
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
