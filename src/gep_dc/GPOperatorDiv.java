package gep_dc;

public class GPOperatorDiv extends GPBinaryOperator implements GPOperator {

	public String toString() {
		return "/";
	}
	
	public double eval(double x, double y) {
		if(y == 0) {
			return x/y;
		} else {
			if(GepMain.DEBUG) {
				System.out.println("Error: div -> y = 0");
			}
			return 0;
		}
	}
	
	public double eval() {
		System.out.println("Error: div must have two arguments");
		return 0;
	}
}