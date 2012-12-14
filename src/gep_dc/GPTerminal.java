package gep_dc;

public abstract class GPTerminal extends java.lang.Object implements GPObject {
	
	public abstract double eval();
	public abstract boolean isVariable();
	
	public boolean isOperator() {
		return false;
	}

	public abstract boolean isTerminal();
}
