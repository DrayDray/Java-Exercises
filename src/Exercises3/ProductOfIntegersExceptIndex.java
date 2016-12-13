package Exercises3;

import java.util.Arrays;

/*TASK:
 * You have an array of integers, and for each index you want to find the product of every integer except the integer at that index.
Write a function getProductsOfAllIntsExceptAtIndex() that takes an array of integers and returns an array of the products.

Do not use division in your solution!

For example, given:

  [1, 7, 3, 4]

your function would return:

  [84, 12, 28, 21]

by calculating:

  [7*3*4, 1*3*4, 1*7*4, 1*7*3]
 */

public class ProductOfIntegersExceptIndex {

	//4. BEST APPROACH
	//which I reached by improving to my previous trials
	//Time Complexity: 2O(n) ==> O(n)              (Two passes through our integersArray.)
	//Space Complexity: O(n)                       (The array we build is the same length as the integersArray received.)
	public static int[] getProductsOfAllIntsExceptAtIndex4(int[] integersArray) {
		int arraySize = integersArray.length;
		int[] fromLeftProductsAndFinal = new int[arraySize];

		//currentProduct will now represent the product of all elements to the LEFT of the index (exclusive)
		int productSoFar = 1;
		//Creating an array where each element represents the product of all elements to its left (EXCLUSIVE of itself)
		for (int i = 0; i < fromLeftProductsAndFinal.length; i++) {
			fromLeftProductsAndFinal[i] = productSoFar;
			productSoFar = productSoFar * integersArray[i];
		}

		//currentProduct will now represent the product of all elements to the RIGHT of the index (exclusive)
		productSoFar = 1;
		//saving the product of all elements to an index's right (EXCLUSIVE of itself)
		//And then multiplying by the products to the left, to achieve the final array
		//(rather than saving the product in an array and only then doing the multiplication in another step)
		for (int i = (fromLeftProductsAndFinal.length - 1); i >= 0; i--) {
			fromLeftProductsAndFinal[i] = productSoFar * fromLeftProductsAndFinal[i];
			productSoFar = productSoFar * integersArray[i];
		}

		return fromLeftProductsAndFinal;
	}

	//3. Similar to solution #2, but improved and more clear
	//Time Complexity Achieved: 3O(n) ==> O(n)
	//Space Complexity: 3O(n) ==> O(n)
	public static int[] getProductsOfAllIntsExceptAtIndex3(int[] integersArray) {
		int arraySize = integersArray.length;
		int[] fromLeftProducts = new int[arraySize];
		int[] fromRightProducts = new int[arraySize];

		int currentProduct = 1;
		//Creating an array where each element represents the product of all elements to its left (EXCLUSIVE of itself)
		for (int i = 0; i < fromLeftProducts.length; i++) {
			fromLeftProducts[i] = currentProduct;
			currentProduct = currentProduct * integersArray[i];
		}

		currentProduct = 1;
		//Creating an array where each element represents the product of all elements to its right (EXCLUSIVE of itself)
		for (int i = (fromRightProducts.length - 1); i >= 0; i--) {
			fromRightProducts[i] = currentProduct;
			currentProduct = currentProduct * integersArray[i];
		}

		//For each element, multiply the products to its left and to its right to get result.
		int[] productsArray = new int[arraySize];			
		for (int i = 0; i < productsArray.length; i++) {
			productsArray[i] = fromLeftProducts[i] * fromRightProducts[i];
		}
		return productsArray;
	}

	//2. Good approach
	//Time Complexity Achieved: 3O(n) ==> O(n)
	//Space Complexity: 3O(n) ==> O(n)
	public static int[] getProductsOfAllIntsExceptAtIndex2(int[] integersArray) {
		int arraySize = integersArray.length;
		if(arraySize == 0){
			return new int[0];
		}
		else if(arraySize == 1){
			return new int[]{1};
		}
		int[] fromLeftProducts = new int[arraySize];
		int[] fromRightProducts = new int[arraySize];

		int currentProduct = 1;
		//Creating an array where each element represents the product of all elements to its left (inclusive of itself)
		for (int i = 0; i < fromLeftProducts.length; i++) {
			currentProduct = currentProduct * integersArray[i];
			fromLeftProducts[i] = currentProduct;
		}

		currentProduct = 1;
		//Creating an array where each element represents the product of all elements to its right (inclusive of itself)
		for (int i = (fromRightProducts.length - 1); i >= 0; i--) {
			currentProduct = currentProduct * integersArray[i];
			fromRightProducts[i] = currentProduct;
		}

		//For each element, multiply the products to its left and to its right to get result.
		int[] productsArray = new int[arraySize];
		//Take care of the outer elements, which require multiplication from one side only
		productsArray[0] = fromRightProducts[1];
		productsArray[productsArray.length-1] = fromLeftProducts[productsArray.length-2];

		//Run through the inner elements, which require multiplication from both sides
		for (int i = 1; i < productsArray.length - 1; i++) {
			productsArray[i] = fromLeftProducts[i-1] * fromRightProducts[i+1];
		}
		return productsArray;
	}

	//1. Brute Force Solution (NOT optimal):
	//Time Complexity: O(n^2)
	//Space Complexity: O(0)
	public static int[] getProductsOfAllIntsExceptAtIndex1(int[] integersArray) {
		int arraySize = integersArray.length;
		if(arraySize == 0){
			return new int[0];
		}
		else if(arraySize == 1){
			return new int[]{1};
		}
		int[] productsArray = new int[arraySize];

		int product = 1;
		for(int outerIndex = 0; outerIndex < productsArray.length; outerIndex++){
			for(int innerIndex = 0; innerIndex < productsArray.length; innerIndex++){
				if(innerIndex != outerIndex){
					product = product * integersArray[innerIndex];
				}
			}
			productsArray[outerIndex] = product;
			//re-initialize product.
			product = 1;
		}        
		return productsArray;
	}

	public static void main(String[] args) {
		int[] integersArray = new int[]{};
		System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex1(integersArray)));
		System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex2(integersArray)));
		System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex3(integersArray)));
		System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex4(integersArray)));
	}
}


