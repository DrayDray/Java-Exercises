package Exercises;

import java.util.Random;

//Returning the longest INCREASING SERIES within a series of random numbers

public class LargestIncreasingSeries {

	private static int[] randomNumbersArray;

	public static void main(String[] args) {
		fillArray();
		
		// printing the array
		for (int index = 0; index < randomNumbersArray.length; index++) {
			System.out.print(randomNumbersArray[index]+", ");
		}
		
		findLargestIncreasingSeriesQ6();
	}

	public static void fillArray(){
		//create an array with size of your choice
		randomNumbersArray = new int[30];
		Random rnd = new Random();

		// filling the array with random numbers
		for (int index = 0; index < randomNumbersArray.length; index++) {			
			int randomNumber = rnd.nextInt(101);
			randomNumbersArray[index] = randomNumber;
		}
	}

	public static void findLargestIncreasingSeriesQ6(){
		//initialize maximum increasing series at 0 and current increasing series at 0
		int maxIncSeries = 0;
		int currentIncSeries = 0;
		int maxIndex = 0;
		if (randomNumbersArray.length > 1){

			//Go through each number in the array (except for the first) and see if it's larger than the previous one
			for (int index = 1; index < randomNumbersArray.length; index++) {
				if (randomNumbersArray[index] > randomNumbersArray[index-1]){
					currentIncSeries++;
					
					//if the current increasing series is larger than the maximum series
					if (currentIncSeries > maxIncSeries){
						//then it becomes the new maximum
						maxIncSeries = currentIncSeries;
						//save the spot where the increasing series begins
						maxIndex = index - maxIncSeries;
					}
				}
				else{
					//"restart" the count
					currentIncSeries = 0;
				}
			}
		}
		//if there is just one number in the array
		else{
			System.out.println("There were no increases, as the array contains just one number");
		}

		System.out.println("\nThe maximum number of increases was " + maxIncSeries + ",\nfirst occuring at index "+ maxIndex);
	}
}
