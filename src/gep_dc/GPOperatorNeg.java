package gep_dc;

public class GPOperatorNeg extends GPUnaryOperator implements GPOperator {

	public String toString() {
		return "-u";
	}
	
	public double eval(double x) {
		return x * -1.0;
	}
	
	public double eval() {
		System.out.println("Error: neg must have one argument");
		return 0;
	}
	
}
