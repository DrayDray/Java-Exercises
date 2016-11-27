package Exercises;
import java.util.Random;

import javax.swing.JOptionPane;

//Bulls And Cows Game:
//On a sheet of paper, the players each write a 4-digit secret number.
//The digits must be all different. 
//Then, in turn, the players try to guess their opponent's number who gives the number of matches. 
//If the matching digits are in their right positions, they are "bulls", if in different positions, they are "cows".

public class BullsAndCowsGame {

	private final static int HARD_NUMBER_OF_GUESSES = 7;
	private final static int MEDIUM_NUMBER_OF_GUESSES = 10;
	private final static int EASY_NUMBER_OF_GUESSES = 13;

	public static void main(String[] args) {
		int difficultyLevel = getValidDifficultyLevel();
		int amountOfGuesses = getAmountOfGuesses(difficultyLevel);
		int computerNum = generateValidComputerNumber();
		
		//We print the number to the console for our testing purposes only
		System.out.println(computerNum);
		runGame(computerNum, amountOfGuesses);
	}
	
	private static int getValidDifficultyLevel(){
		int difficultyLevel = getUserDifficultyLevelChoice();

		while(difficultyLevel<1 || difficultyLevel>3){
			JOptionPane.showMessageDialog(null, "Invalid number, please choose from 1-3");
			difficultyLevel = getUserDifficultyLevelChoice();
		}

		return difficultyLevel;
	}
	
	private static int getUserDifficultyLevelChoice(){
		String menu = "Please enter difficulty level 1-3\n"+
				"1.Easy - 13 guesses\n"+
				"2.Medium - 10 guesses\n"+
				"3.Hard - 7 guesses";
		String strUserChoice = JOptionPane.showInputDialog(menu);

		int userChoice = Integer.parseInt(strUserChoice);
		return userChoice;
	}

	private static void runGame(int computerNum, int amountOfGuesses) {		
		while(amountOfGuesses>0){
			int userGuess = getValidUserGuess();

			if (userGuess == computerNum){
				System.out.println("That's correct! You are a winner!");
				return;
			}

			// If the matching digits are in their right positions, they are "bulls"
			int amountOfBulls = calculateAmountOfBulls(computerNum, userGuess);
			
			//If the matching digits are in different positions, they are "cows"
			//This gives us the full number of identical digits, so we must reduce it by the amount of bulls(exact hits) that we have already calculated.
			int amountOfPgia = calculateAmountOfIdenticalDigits(computerNum, userGuess) - amountOfBulls;

			System.out.println("Guess :" + userGuess + "\n" + 
					"Pgiot :" + amountOfPgia + "\n" + 
					"Bulls :" + amountOfBulls + "\n");
			amountOfGuesses--;
		}

		JOptionPane.showMessageDialog(null, "Failed to guess the number, which was... " + computerNum);	

	}

	// This function returns an accumulated amount of bulls and pgias, hence - 
	// in order to calculate the exact amount of pgia, we'll deduct the amount of bulls
	// from the value this function returns
	private static int calculateAmountOfIdenticalDigits(int computerNum, int userGuess) {
		boolean[] digitsArray = new boolean[10];

		for (int index = 0; index < 4; index++) {
			//each present digit is turned into "true"
			int digit = computerNum % 10;
			digitsArray[digit] = true;
			computerNum = computerNum / 10;
		}

		int amountOfPgia = 0;
		for (int index = 0; index < digitsArray.length; index++) {
			int digit = userGuess % 10;
			if (digitsArray[digit] == true){
				amountOfPgia++;
			}
			userGuess = userGuess / 10;
		}

		return amountOfPgia;
	}

	private static int calculateAmountOfBulls(int computerNum, int userGuess) {
		int amountOfBulls = 0;

		while (computerNum!=0){
			//compare each computer digit to the same digit of the guess
			int digit1 = computerNum % 10;
			int digit2 = userGuess % 10;

			if (digit1 == digit2){
				amountOfBulls++;
			}

			computerNum = computerNum / 10;
			userGuess = userGuess / 10;
		}

		return amountOfBulls;
	}

	private static int getValidUserGuess() {
		String strUserGuess = JOptionPane.showInputDialog("Please enter your guess");
		int userGuess = Integer.parseInt(strUserGuess);

		while(isValidUserGuess(userGuess) == false){
			strUserGuess = JOptionPane.showInputDialog("Invalid number, please reenter");
			userGuess = Integer.parseInt(strUserGuess);
		}
		return userGuess;
	}

	private static boolean isValidUserGuess(int userGuess){
		if (userGuess < 1023){
			return false;
		}		

		if (userGuess > 9876){
			return false;
		}

		boolean response = isAllDigitsDifferent(userGuess);
		return response;
	}

	private static int generateValidComputerNumber() {
		Random rnd = new Random();
		//9876 (upper limit) - 1023 (lower limit) = 8853 (max range for random generator, to which we add one)
		int computerGuess = 1023 + rnd.nextInt(8854);

		//generate a new guess until a valid one is generated
		while(isAllDigitsDifferent(computerGuess) == false){
			computerGuess = 1023 + rnd.nextInt(8854);
		}

		return computerGuess;
	}

	private static boolean isAllDigitsDifferent(int computerGuess) {
		boolean[] digitsArray = new boolean[10];

		while(computerGuess!=0){
			int rightMostDigit = computerGuess % 10;

			//If we find that the digitsArray already has a true value
			//it means we've already encountered this digit (the digit appears twice)
			//so the number is invalid --> we return false
			if (digitsArray[rightMostDigit] == true){
				return false;
			}

			// Every cell represents a digit (ex: the cell whose index is 5 represents whether or not
			// the digit 5 has appeared or not).
			// After we've isolated the right most digit, we mark that it has appeared, by
			// turning the relevant cell to true
			digitsArray[rightMostDigit] = true;
			computerGuess = computerGuess / 10;	
		}
		
		return true;
	}

	private static int getAmountOfGuesses(int difficultyLevel) {
		if (difficultyLevel == 1){
			return EASY_NUMBER_OF_GUESSES;
		}

		if (difficultyLevel == 2){
			return MEDIUM_NUMBER_OF_GUESSES;
		}

		return HARD_NUMBER_OF_GUESSES;
	}

}


