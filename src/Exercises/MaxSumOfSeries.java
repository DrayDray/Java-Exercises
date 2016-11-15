package Exercises;

import java.util.Random;

//Challenge: Write an algorithm that receives a list of whole numbers (both positive and negative)
//The algorithm will return the maximum sum of any series of numbers within the list.
//Do this with O(n) !

//I completed the task in with O(2n), which "reduces" to O(n).

public class MaxSumOfSeries {

	private static int[] wholeNumbers;

	public static void main(String[] args) {
		//initialize array, with however many values you would like
		wholeNumbers = new int[20];
		
		fillArray();
		printArray();
		int maxSum = findMaxSeries();
		System.out.println("The maximum series sum was " + maxSum);
	}
	
	public static int findMaxSeries(){
		//There is a mathematical trick here:
		//Only when the ongoing sum becomes negative, do you want to restart the series
		//You also never want to start with a series with a negative value
		
		//Initialize values
		int maxSum = wholeNumbers[0];
		int currentSum = wholeNumbers[0];
		
		//never start a series sum with a negative number
		//if the first value is negative, set current sum to zero
		if(currentSum < 0){
			currentSum = 0;
		}
		int positionOfSeriesStart = 0;
		
		//Iterate through the array, starting with index of 1
		for (int index = 1; index < wholeNumbers.length; index++) {
			
			//if adding this number keeps me at a positive value
			if(currentSum + wholeNumbers[index] > 0){
				//then add it to our current series
				currentSum = currentSum + wholeNumbers[index];
				
				//if the currentSum is greater than the maxSum
				if(currentSum > maxSum){
					//update the maxSum
					maxSum = currentSum;
				}
			}
			
			//adding this number makes me 0 or below
			else{
				//restart our series
				positionOfSeriesStart = index;
				
				//never start a series with a negative number
				//if it is negative, set current sum to zero
				if(wholeNumbers[index]<0){
					currentSum = 0;
				}
				else{
					currentSum = wholeNumbers[index];
				}
			}
		}
		
		//if the array was filled with negative numbers, our method will not have checked it properly
		//so we just check by iterating once through the array
		if(maxSum < 0){
			maxSum = wholeNumbers[0];
			for (int i = 1; i < wholeNumbers.length; i++) {
				if(wholeNumbers[i] > maxSum){
					maxSum = wholeNumbers[i];
				}
			}
		}
		return maxSum;
	}
	
	public static void fillArray(){
		//Fill the array with random numbers

		//a single Random object is reused here
		Random randomGenerator = new Random();

		for (int i = 0; i < wholeNumbers.length; i++) {
			int randomInt = randomGenerator.nextInt(101);

			//int randomNum = randomGenerator.nextInt((max - min) + 1) + min;
			int randomNum = (randomGenerator.nextInt(201) - 100);

			wholeNumbers[i] = randomNum;
		}
	}

	public static void printArray(){
		for (int i = 0; i < wholeNumbers.length; i++) {
			System.out.print(wholeNumbers[i] + ", ");
		}
		System.out.println();
	}
}

