package gep_dc;

public class GepMain {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("GEP_DC: Gene Expression Programming for Dielectric Constant symbolic regression");
		
		//creation des contenus de l'arbre
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
		System.out.println(tree.eval());
	}*/

}
