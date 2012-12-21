package gep_dc;

public class GPOperatorExp extends GPUnaryOperator implements GPOperator {

	public String toString() {
		return "exp";
	}
	
	public double eval(double x) {
		return Math.exp(x);
	}
	
	public double eval() {
		System.out.println("Error: exp must have one argument");
		return 0;
	}
	
	@Override
	public boolean isVariable() {
		return false;
	}
	
}
