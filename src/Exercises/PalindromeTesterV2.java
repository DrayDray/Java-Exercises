package Exercises;
import javax.swing.JOptionPane;

//This is different, quicker version of the PalindromeTester
//I will go through our number, comparing the last digit to the first digit,

public class PalindromeTesterV2 {

	public static void main(String[] args) {
		//Request our number
		int originalNum = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number."
				+ "\n We will determine whether or not it is a palindrome for you."));

		//Output result
		if(isAPalindrome(originalNum)){
			System.out.println(originalNum+" is a palindrome.");
		}
		else{
			System.out.println(originalNum+" is NOT a palindrome.");
		}		
	}

	public static boolean isAPalindrome(int originalNum){
		// we define negative integers as non-palindromes. 
		if(originalNum < 0){
			return false;
		}

		//copy the original Number for calculations
		int numForCalculations = originalNum;

		//get the length of the original number
		int lengthOfOriginal = String.valueOf(originalNum).length();

		//we will need this divider value to extract the leftmost digit of our number each iteration
		int divider = (int) Math.pow(10, (lengthOfOriginal-1));

		for (int i = 0; i < lengthOfOriginal; i++) {

			//compare the leftmostdigit to the rightmostdigit
			int leftDigit = originalNum/divider%10;
			int rightDigit = numForCalculations % 10;

			//compare the digits
			if(leftDigit != rightDigit){
				return false;
			}

			//truncate right digit of the copy
			numForCalculations = (numForCalculations/10);

			//if the length of the copy is half (for even numbers
			//or half+1 (for odd numbers) of the original numbers' length
			//then we have reached the center successfully (without returning false)
			int lengthOfCopy = String.valueOf(numForCalculations).length();
			if(lengthOfCopy == (lengthOfOriginal/2) || lengthOfCopy == ((lengthOfOriginal/2)+1)){
				return true;				
			}

			//reduce the divider
			divider=divider/10;
		}
		//to appease eclipse we add a false return statement;
		return false;
	}

}
