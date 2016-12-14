package Exercises3;

import java.util.Arrays;

public class HighestProductOfThree {

	/*Task:
	Given an arrayOfInts, find the highestProduct you can get from three of the integers.
	The input arrayOfInts will always have at least three integers.
	 */
	
	//Solution:
	//Time: O(n)
	//Space: O(1)
	
	public static int getHighestProduct(int[] integersArray) throws Exception {
		if(integersArray.length < 3){
			throw new Exception("Error: Array must contain at least 3 integers!");
		}
		
		int highestProduct;

		//all values in these arrays will be initialized at 0
		//I decided to store the greatest and lowest values in arrays, 
		//but I could have used separate integers just as easily to save a bit of memory
		int[] positives = new int[3];
		int[] negatives = new int[2];

		//iterate through my integersArray and store the 3 largest positive integers
		//and the 2 smallest negative integers
		for (int index = 0; index < integersArray.length; index++) {
			int currentInteger = integersArray[index];
			if(currentInteger > positives[0]){
				if(currentInteger > positives[1]){
					if(currentInteger > positives[2]){
						int temp = positives[2];
						positives[2] = currentInteger;

						if(temp >= positives[1]){
							positives[0] = positives[1];
							positives[1] = temp;
						}
					}
					else{
						int temp = positives[1];
						positives[1] = currentInteger;

						if(temp > positives[0]){
							positives[0] = temp;
						}
					}
				}
				else{
					positives[0] = currentInteger;
				}
			}
			if(currentInteger < 0){
				if(currentInteger < negatives[1]){
					int temp = negatives[1];
					negatives[1] = currentInteger;
					if(temp < negatives[0]){
						negatives[0] = temp;
					}
				}
				else{
					if(currentInteger < negatives[0]){
						negatives[0] = currentInteger;
					}
				}
			}
		}
			
			//if we don't have two negatives, we will only use positives to achieve our highestProduct:
			if(negatives[0] == 0 || negatives[1] == 0){
				highestProduct = positives[0] * positives[1] * positives[2];
			}
			//otherwise, we have to consider using the negatives
			else{
				//check which of the two options is best
				int highestProductUsingNegatives = negatives[0] * negatives[1] * positives[2];
				int highestProductUsingPositives = positives[0] * positives[1] * positives[2];
				highestProduct = Math.max(highestProductUsingNegatives, highestProductUsingPositives);
			}

		return highestProduct;
	}
	
	public static void main(String[] args) {
		int[] integersArray = new int[]{20, -105, -19, -3, -9, -20, 100, 15, 16};
		
		try {
			System.out.println(getHighestProduct(integersArray));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
