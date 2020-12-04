 
public class Karatsuba {
	
	public static void main(String args[]) {
		
		Calc calculator = new Calc();
		
		String number1 = args[0];
		String number2 = args[1];

		System.out.println(calculator.karatsuba(number1, number2));
	}
	
}