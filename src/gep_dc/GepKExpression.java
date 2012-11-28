package gep_dc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GepKExpression {
	
	private ArrayList<GPObject> kExpression;
	private int head;
	private int tail;
	private int size;
	
	public void randomInit() {
		
		for(int i = 0; i < head; i++) {
			Random r = new Random();
			int rand=r.nextInt(2);
			
			if(rand == 1) {
				kExpression.add(randomOp());
			} else {
				kExpression.add(randomTerm());
			}
			
		}
		
	}
	
	private GPOperator randomOp() {

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
	
	private GPTerminal randomTerm() {

		Random r = new Random();
		int rand=r.nextInt(2);
		
		if(rand == 1) {
			// TODO provide value
			return new GPTerminalCste(value);
		} else {
			// TODO provide data, column and name
			return new GPTerminalVar(data, column, name);
		}
		
	}
	
	public void add(GPObject element) {
		kExpression.add(element);
	}
	
	public GepKExpression(int size) {
		kExpression = new ArrayList<GPObject>();
		this.size = size;
		//on choisit n=2, cas des operateurs binaires
		this.head = (size-1)/2;
		this.tail= size-this.head;	
	}
	
	public GepExpressionTree getExpressionTree() {
		//la reserve est une liste FIFO qui contient les noeud de l'arbre à remplir
		ArrayList<GepExpressionNode> reserve =new ArrayList<GepExpressionNode>();
		
		//ajouter la racine dans la reserve
		GepExpressionNode root = new GepExpressionNode();
		reserve.add(root);
		
		//boucle tant que la reserve n'est pas vide
		Iterator<GPObject> itr = kExpression.iterator();
		
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
	}
	
	public String toString() {
		String s = "";
		Iterator<GPObject> itr = kExpression.iterator();
		while (itr.hasNext()) {
			GPObject element = (GPObject) itr.next();
			s += "["+element.toString()+"]";
		}
		return s;	
	}
}
