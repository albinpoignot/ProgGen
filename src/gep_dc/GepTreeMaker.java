package gep_dc;

import java.util.ArrayList;

public class GepTreeMaker {

	//public static GepExpressionTree makeTree(ArrayList<GPOperator> exp) {
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
			
			System.out.println("FIFO > 0");
			
			// Si le prochain élément à traiter est un opérateur
			if(exp.get(i).isOperator()) {
				
				GPOperator op = (GPOperator) exp.get(i);
				
				// Si c'est un opérateur binaire
				if(op.isBinaryOperator()) {
					
					// On crée deux enfants
					GepExpressionNode child1 = new GepExpressionNode(exp.get(i+1));
					GepExpressionNode child2 = new GepExpressionNode(exp.get(i+2));
					
					fifo.add(child1);
					fifo.add(child2);
					
					obj = fifo.get(0);
					
					System.out.println("\t Is binary op : " + obj.toString());
					
					// On ajoute les deux enfants à l'arbre
					/*tree.add(obj, new GepExpressionNode(exp.get(i+1)));
					tree.add(obj, new GepExpressionNode(exp.get(i+2)));*/
					tree.add(obj, child1);
					tree.add(obj, child2);
					
					System.out.println("\t Added : " + child1);
					System.out.println("\t Added : " + child2);
					
					//obj = new GepExpressionNode(exp.get(j));
					
					fifo.remove(0);
					
					i+=2;
					++j;
					
				} else {
					
					// TODO Not a binary operator
					
					/*tree.add(obj, new GepExpressionNode(exp.get(i)));
					tree.add(obj, new GepExpressionNode(exp.get(i+1)));
					
					i+=2;
					
					fifo.remove(0);
					fifo.remove(1);*/
				}
			} else {
				
				System.out.println("\t Is a terminal : " + exp.get(i).toString());
				
				// TODO Terminaux ne sont pas ajoutés
				fifo.remove(0);
				//++i;
				++j;
			}
			
			//System.out.println("While : i = " + i + "; exp.get(i) = " + exp.get(i).toString());
			
		}
		
		
		return tree;
	}
	
}
