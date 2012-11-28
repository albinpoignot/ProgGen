package gep_dc;

public class GPOperatorLog extends GPUnaryOperator implements GPOperator {

	public String toString() {
		return "log";
	}
	
	public double eval(double x) {
		if(x > 0) {
			return Math.log(x);
		} else {
			System.out.println("Error: log -> x < 0");
			return 0;
		}
	}
	
	public double eval() {
		System.out.println("Error: log must have one argument");
		return 0;
	}
	
}
