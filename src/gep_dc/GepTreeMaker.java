package gep_dc;

import java.util.ArrayList;

public class GepTreeMaker {

	public static GepExpressionTree makeTree(ArrayList<GPOperator> exp) {
		
		// Lecture de la racine
		GepExpressionNode root = new GepExpressionNode(exp.get(0));
		
		// Initialisation de l'arbre
		GepExpressionTree tree = new GepExpressionTree(root);
		
		ArrayList<GepExpressionNode> fifo = new ArrayList<GepExpressionNode>();
		fifo.add(root);
		
		int i = 0;
		GepExpressionNode obj = root;
		
		while(fifo.size() != 0) {
			
			if(exp.get(i).isBinaryOperator()) {
				GepExpressionNode child1 = new GepExpressionNode(exp.get(i+1));
				GepExpressionNode child2 = new GepExpressionNode(exp.get(i+1));
				//tree.add(root, child1);
				
				fifo.add(child1);
				fifo.add(child2);
				tree.add(obj, new GepExpressionNode(exp.get(i+1)));
				tree.add(obj, new GepExpressionNode(exp.get(i+2)));
				
				// TODO
				// SUITE DE LA BOUCLE A FAIRE
				obj = child1; 
				
				i+=2;
				
				fifo.remove(0);
				
			} else {
				
				tree.add(obj, new GepExpressionNode(exp.get(i+1)));
				tree.add(obj, new GepExpressionNode(exp.get(i+2)));
				
				i+=2;
				
				fifo.remove(0);
				fifo.remove(1);
			}
			
		}
		
		
		return tree;
	}
	
}
