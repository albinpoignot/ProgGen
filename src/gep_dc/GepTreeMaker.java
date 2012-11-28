package gep_dc;

import java.util.ArrayList;

public class GepTreeMaker {

	/**
	 * Generate the tree from the expression given in parameters
	 * @param exp the expression you want to convert into a tree
	 * @return the tree
	 */
	public static GepExpressionTree makeTree(ArrayList<GPObject> exp) {
		
		// Lecture de la racine
		GepExpressionNode root = new GepExpressionNode(exp.get(0));
		
		// Initialisation de l'arbre
		GepExpressionTree tree = new GepExpressionTree(root);
		
		ArrayList<GepExpressionNode> fifo = new ArrayList<GepExpressionNode>();
		fifo.add(root);
		
		// Parcours depuis le début
		int i = 0;
		int j = 0;
		
		// Obj = objet courant
		GepExpressionNode obj = root;
		
		// Tant qu'il y en a quelque chose dans FIFO
		while(fifo.size() != 0) {
			
			// Si le prochain élément à traiter est un opérateur
			if(exp.get(j).isOperator()) {
				
				GPOperator op = (GPOperator) exp.get(j);
				
				// Si c'est un opérateur binaire
				if(op.isBinaryOperator()) {
					
					// On crée deux enfants
					GepExpressionNode child1 = new GepExpressionNode(exp.get(i+1));
					GepExpressionNode child2 = new GepExpressionNode(exp.get(i+2));
					
					fifo.add(child1);
					fifo.add(child2);
					
					obj = fifo.get(0);
					
					// On ajoute les deux enfants à l'arbre
					tree.add(obj, child1);
					tree.add(obj, child2);
					
					fifo.remove(0);
					
					i+=2;
					++j;
					
				} else {
					
					// TODO Not a binary operator

				}
			} else {
				fifo.remove(0);
				++j;
			}
			
		}
		
		
		return tree;
	}
	
}
