package gep_dc;

public class GPOperatorDiv extends GPBinaryOperator implements GPOperator {

	public String toString() {
		return "/";
	}
	
	public double eval(double x, double y) {
		if(y != 0.0f) {
			return x/y;
		} else {
			if(GepMain.DEBUG) {
				System.out.println("Error: div -> y = 0");
			}
			//return 0.0f;
			return Double.NaN;
		}
	}
	
	public double eval() {
		System.out.println("Error: div must have two arguments");
		return 0;
	}
	
	@Override
	public boolean isVariable() {
		return false;
	}
}