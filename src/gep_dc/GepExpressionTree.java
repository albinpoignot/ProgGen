package gep_dc;

public class GepExpressionTree {
	GepExpressionNode root_node;
	
	public GepExpressionTree(GepExpressionNode root) {
		root_node = root;
	}
	
	/*public double eval() {
		return root_node.eval();
	}*/
	
	public double eval(int substance) {
		return root_node.eval(substance);
	}
	
	public void add(GepExpressionNode parent, GepExpressionNode child) {
		parent.add(child);
		child.setParent(parent);
	}
	
	public String toString() {
		String s = new String();
		
		s = root_node.toString();
		return s;
	}
	
}
