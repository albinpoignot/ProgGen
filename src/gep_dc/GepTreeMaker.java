package gep_dc;

import java.util.ArrayList;
import java.util.Iterator;

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
					
					// On crée deux enfants
					GepExpressionNode child1 = new GepExpressionNode(exp.get(i+1));
					
					fifo.add(child1);
					
					obj = fifo.get(0);
					
					// On ajoute les deux enfants à l'arbre
					tree.add(obj, child1);
					
					fifo.remove(0);
					
					i+=1;
					++j;
					
				}
			} else {
				fifo.remove(0);
				++j;
			}
			
		}
		
		
		return tree;
	}
	
	/*public static GepExpressionTree getExpressionTree(GepKExpression exp) {
		//la reserve est une liste FIFO qui contient les noeud de l'arbre à remplir
		ArrayList<GepExpressionNode> reserve =new ArrayList<GepExpressionNode>();
		
		//ajouter la racine dans la reserve
		GepExpressionNode root = new GepExpressionNode();
		reserve.add(root);
		
		//boucle tant que la reserve n'est pas vide
		Iterator<GPObject> itr = exp.iterator();
		
		while ( !(reserve.isEmpty()) ) {
		
			//recupérer le premier élément de la reserve
			GepExpressionNode current= reserve.remove(0);
			
			//affecter l'objet de l'expression
			GPObject element = itr.next();
			current.setUserObject(element);
			
			// si l'élément ajouté est un opérateur, créer les fils et les ajouter à la réserve.
			if ( element.isOperator() ) {
				GepExpressionNode  child1 = new GepExpressionNode();
				current.add(child1);
				reserve.add(child1);
				
				//si c'est un opérateur binaire, il faut ajouter un autre fils
				if ( ((GPOperator)element).isBinaryOperator()) {
					GepExpressionNode  child2 = new GepExpressionNode();
					current.add(child2);
					reserve.add(child2);
				}
			}
		}
		
		//terminé, on crée l'arbre et on retourne
		return new GepExpressionTree(root);
	}*/
	
}
