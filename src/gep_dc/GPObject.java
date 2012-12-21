package gep_dc;

public interface GPObject  {
	public boolean isOperator();
	public boolean isTerminal();
	public boolean isVariable();
	public String toString();
	public double eval();
}
