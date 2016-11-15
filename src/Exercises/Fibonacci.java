package Exercises;

import javax.swing.JOptionPane;

//Task: Receive an index from the user,
//and output the Fibonacci* number at the index received.

//* In the Fibonacci sequence, every number 
//after the first two is the sum of the two preceding ones
//As such: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144...

public class Fibonacci {
	
	public static void main(String[] args) {
	
		int chosenIndex = Integer.parseInt(JOptionPane.showInputDialog("Please choose the index:"));
		
		int resultAtIndex = findResultAtIndex(chosenIndex);
		System.out.println("The result at the index of " + chosenIndex + " is " + resultAtIndex + ".");
	}
	
	public static int findResultAtIndex(int chosenIndex){
		//at index 0 or 1 we simply return 1
		if(chosenIndex == 0 || chosenIndex == 1){
			return 1;
		}
		
		//initialize current total and the new total
		int oldTotal = 1;
		int currentTotal = 1;
		
		for (int i = 2; i <= chosenIndex; i++) {
			//calculate the new values
			//currentTotal will always be the value at the chosen Index
			//and oldTotal will be the value to its left
			currentTotal = currentTotal + oldTotal;
			oldTotal = currentTotal - oldTotal;
		}
		
		return currentTotal;
	}
	
	
	
	

}
