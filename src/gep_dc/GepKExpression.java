package gep_dc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GepKExpression {
	
	private ArrayList<GPObject> kExpression;
	private int head;
	private int tail;
	private int size;
	
	public void add(GPObject element) {
		kExpression.add(element);
	}
	
	public GepKExpression(int size) {
		kExpression = new ArrayList<GPObject>();
		//this.size = size;
		//on choisit n=2, cas des operateurs binaires
		this.head = (size-1)/2;
		this.tail= (int) ((size-this.head)*1.50);
		this.size = head + tail;
	}
	
	public GPObject get(int index) {
		return kExpression.get(index);
	}
	
	//Permet de supprimer les objets de la kExpression à partir de l'indice i
	public void rm(int index){
		for(int i = index; i < kExpression.size(); i++)
		kExpression.remove(i);
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
			GepExpressionNode current = reserve.remove(0);
			
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
	
	public int getHead() {
		return this.head;
	}
	
	public int getTail() {
		return this.tail;
	}
	
	public int getSize(){
		return this.size;
	}
	
	//Renvoi l'objet i de l'expression
	public GPObject getObject(int i){
		return kExpression.get(i);
	}
}
