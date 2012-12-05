import gep_dc.GPOperatorAdd;
import gep_dc.GPOperatorMul;
import gep_dc.GPOperatorNeg;
import gep_dc.GPOperatorSub;
import gep_dc.GPTerminalCste;
import gep_dc.GepExpressionTree;
import gep_dc.GepKExpression;
import gep_dc.GepTreeMaker;



public class Main {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		
		//ArrayList<GPObject> expression = new ArrayList<GPObject>();
		
		// Operators
		GPOperatorAdd addOp = new GPOperatorAdd();
		GPOperatorSub subOp = new GPOperatorSub();
		GPOperatorMul mulOp = new GPOperatorMul();
		
		GPOperatorNeg neg = new GPOperatorNeg();
		
		// Constantes
		GPTerminalCste two = new GPTerminalCste(2f);
		GPTerminalCste three = new GPTerminalCste(3f);
		GPTerminalCste five = new GPTerminalCste(5f);
		GPTerminalCste six = new GPTerminalCste(6f);
		
		// Variables
		GPTerminalCste x1 = new GPTerminalCste(10f);
		GPTerminalCste x2 = new GPTerminalCste(20f);
		GPTerminalCste x3 = new GPTerminalCste(30f);
		
		GepKExpression expr = new GepKExpression(15);
		
		// Expression
		expr.add(addOp);
		expr.add(mulOp);
		expr.add(subOp);
		expr.add(neg);
		expr.add(x1);
		expr.add(mulOp);
		expr.add(five);
		expr.add(three);
		expr.add(x2);
		expr.add(six);

		
		GepExpressionTree tree = expr.getExpressionTree();
		System.out.println(tree.eval());
		System.out.println(tree.toString());
		System.out.println(expr.toString());
		
	}*/

}
