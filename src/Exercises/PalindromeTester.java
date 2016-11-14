package Exercises;
import javax.swing.JOptionPane;

//This program will request a number from the user and
//notify the user whether or not the number is a palindrome*.
//"A palindrome is a number which reads the same backward or forward.

public class PalindromeTester {

	public static void main(String[] args) {
		//Request our number
		int originalNum = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number."
				+ "\n We will determine whether or not it is a palindrome for you."));
		
		//Output whether it is a palindrome or not
		if(isAPalindrome(originalNum)){
			System.out.println(originalNum+" is a palindrome.");
		}
		else{
			System.out.println(originalNum+" is NOT a palindrome.");
		}		
	}
	
	public static boolean isAPalindrome(int originalNum){
		// we define negative integers as non-palindromes. 
		//so first, we check that
		if(originalNum < 0){
			return false;
		}
		
		//copy the original Number for calculations
		int numForCalculations = originalNum;
		
		//initialize the reverse number
		int reverseNumber = 0;
		
		for(int i = 0; i < String.valueOf(originalNum).length(); i++){
			//get the current rightmost digit of the number for calculations using mod
			int rightDigit = numForCalculations % 10;
			
			//"add" the digit onto our reverseNumber by "shifting left" by multiplying by 10
			reverseNumber = reverseNumber * 10 + rightDigit;
			
			//then cut off the digit from our number for calculations
			//since it is an integer, no decimal point will remain
			numForCalculations = (numForCalculations/10);
		}

		//compare the two numbers
		if(originalNum == reverseNumber){
			return true;
		}
		else{
			return false;
		}
	}
}
