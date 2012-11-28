package gep_dc;

public class GPOperatorMinus extends GPBinaryOperator {

	public String toString() {
		return "-";
	}
	
	public double eval(double x, double y) {
		return x-y;
	}

	public double eval() {
		System.out.println("Error: minus must have two arguments");
		return 0;
	}
}