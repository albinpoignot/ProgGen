import gep_dc.GPObject;
import gep_dc.GPOperatorAdd;
import gep_dc.GPOperatorMinus;
import gep_dc.GPOperatorMul;
import gep_dc.GPTerminalCste;
import gep_dc.GepExpressionTree;
import gep_dc.GepTreeMaker;

import java.util.ArrayList;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<GPObject> expression = new ArrayList<GPObject>();
		
		// Operators
		GPOperatorAdd addOp = new GPOperatorAdd();
		GPOperatorMinus minusOp = new GPOperatorMinus();
		GPOperatorMul mulOp = new GPOperatorMul();
		
		// Constantes
		GPTerminalCste two = new GPTerminalCste(2f);
		GPTerminalCste three = new GPTerminalCste(3f);
		GPTerminalCste five = new GPTerminalCste(5f);
		GPTerminalCste six = new GPTerminalCste(6f);
		
		// Variables
		GPTerminalCste x1 = new GPTerminalCste(10f);
		GPTerminalCste x2 = new GPTerminalCste(20f);
		GPTerminalCste x3 = new GPTerminalCste(30f);
		
		// Expression
		expression.add(addOp);
		expression.add(mulOp);
		expression.add(minusOp);
		expression.add(three);
		expression.add(x1);
		expression.add(mulOp);
		expression.add(five);
		expression.add(x2);
		expression.add(six);
		/*expression.add(x3);
		expression.add(x1);
		expression.add(three);
		expression.add(two);*/
		
		GepExpressionTree tree = GepTreeMaker.makeTree(expression);
		System.out.println(tree.eval());
		//System.out.println(tree.toString());
		
	}

}
