 
public class Calc {

	public String karatsuba(String number1, String number2) {
		// n1 x n2 = (10^n * (a*c)) + (10 ^ n/2 * ((a*d)) + (b*c) + (b*d)

		if(number1.length() < number2.length()) {
			number1 = treatZeros(number1,number2);
		}
		
		if(number2.length() < number1.length()) {
			number2 = treatZeros(number2,number1);
		}
		
		if(number1.length() <= 4 && number2.length() <= 4) {
			return String.valueOf(Integer.parseInt(number1) * Integer.parseInt(number2));
		}
		
		number1 = pad(number1);
		number2 = pad(number2);
		
		Integer n = number1.length();
		
	    String a = number1.substring(0,n/2);
	    String b = number1.substring(n/2,n);
	    String c = number2.substring(0,n/2);
	    String d = number2.substring(n/2,n);
	    
	    String ab = sum(a,b);
	    String cd = sum(c,d);
	    
	    String karat_ac = karatsuba(a,c);
	    String karat_bd = karatsuba(b,d);
	    String karat_abcd = karatsuba(ab, cd);
	    

	    String sum_ac_bd = sum(karat_ac, karat_bd);
	    String sub_k = subtract(karat_abcd, sum_ac_bd);
	    String shift_result = shift(karat_ac, n);
	    String result = sum(sum(shift_result, shift(sub_k, n/2)), karat_bd);
	    
	    result = result.replaceFirst("^0+(?!$)", "");
	   
		return result;
	}
	
	public String subtract(String number1, String number2) 
	{ 
		
	   if(checkSize(number1, number2)) {
		   String aux = number2;
		   number2 = number1;
		   number1 = aux;
	   }
	   
		if(number1.length() < number2.length()) {
			number1 = treatZeros(number1,number2);
		}
		
		if(number2.length() < number1.length()) {
			number2 = treatZeros(number2,number1);
		}
		
		String result = "";
		Integer carry_next = 0;
		
		Integer size = number1.length();
		for(int i = size - 1; i >= 0; i--) {
			
			Integer d1 = Integer.parseInt(String.valueOf(number1.charAt(i)));
			Integer d2 = Integer.parseInt(String.valueOf(number2.charAt(i)));
			
			
			Integer sub_d1_d2 = d1 - d2 - carry_next;
			
			//Integer left_value = Integer.parseInt(String.valueOf(number1.charAt(i - 1)));
		
			if(sub_d1_d2 < 0) {
				d1 = d1 + 10;
				sub_d1_d2 = d1 - d2 - carry_next;
				carry_next = 1;
			} else {
				carry_next = 0;
			}
			
			result = sub_d1_d2.toString() + result;
			
		}
	   
	   result = result.replaceFirst("^0+(?!$)", "");
		
	   return result;
	}

	public String sum(String number1, String number2) {
		Integer carry = 0;

		if(number1.length() < number2.length()) {
			number1 = treatZeros(number1,number2);
		}
		
		if(number2.length() < number1.length()) {
			number2 = treatZeros(number2,number1);
		}
		
		String result = "";
		
		Integer size = number1.length();
		for(int i = size - 1; i >= 0; i--) {

			Integer d1 = Integer.parseInt(String.valueOf(number1.charAt(i)));
			Integer d2 = Integer.parseInt(String.valueOf(number2.charAt(i)));
			
			Integer sum_d1_d2 = d1 + d2 + carry;
			Integer aux_sum_d1_d2 = d1 + d2 + carry;
			Integer sum_d1_d2_no_carry = d1 + d2;
			
			if(sum_d1_d2 >= 10) {
				carry = 1;
				sum_d1_d2 = sum_d1_d2 - 10;
			}
			
			if((sum_d1_d2_no_carry < 10 && carry != 1) 
			|| (sum_d1_d2_no_carry < 10 && aux_sum_d1_d2 < 10)) {
				carry = 0;
			}

			if(i == 0 && aux_sum_d1_d2 >= 10) {
				result = aux_sum_d1_d2.toString() + result;
			}else {
				
			result = sum_d1_d2.toString() + result;
			
			}
		}
		
		return result;
	}
	
	private String treatZeros(String number1, String number2) {
		
		Integer totalZeros = 0;
		
		if(number1.length() < number2.length()) {
			totalZeros = number2.length() - number1.length();
		}
		
		for(int i = 0; i < totalZeros; i++) {
			number1 = "0" + number1;
		}
		
		return number1;
	}
	
	private boolean checkSize(String number1, String number2) {
		Integer biggestNumberSize = 0;
		
		if(number1.length() < number2.length()) {
			number1 = treatZeros(number1,number2);
		}
		
		if(number2.length() < number1.length()) {
			number2 = treatZeros(number2,number1);
		}
		
		biggestNumberSize = number1.length();
	  
	    for (int i = 0; i < biggestNumberSize; i++) 
	    { 
	        if (number1.charAt(i) < number2.charAt(i)) {
	            return true; 
	        }
	        else if (number1.charAt(i) > number2.charAt(i)) {
	            return false; 
	        }
	    } 
	    return false; 
	}
	
	public String shift(String x, Integer n) {
		String res = new String(x);
		
		for (int i=1; i<=n; i++) {
			res = res + "0";
		};
		
		
		return res;
    }
	
	public String pad(String n1) {
		
		if(n1.length() % 2 == 1) {
			n1 = "0" + n1;
		}
		
		return n1;
	}
}
