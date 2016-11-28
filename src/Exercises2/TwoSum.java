package Exercises2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1]
 */
public class TwoSum {

	public static void main(String[] args) {
		int[] nums = new int[]{2, 7, 11, 15};
		int target = 9;
		int[] solution = twoSum1(nums, target);

		//print the values of the array
		System.out.println(Arrays.toString(solution));

	}

	/*Approach one - Straight-Forward Approach
	Time complexity : O(n^2). For each element, we try to find its complement by looping through the rest of array which takes O(n) time.
	Space complexity : O(1). We did not use any additional storage space.
	 */
	public static int[] twoSum1(int[] nums, int target) {

		for(int index1 = 0; index1 < nums.length; index1++){
			for(int index2 = (index1 + 1); index2 < nums.length; index2++){
				if(nums[index1] + nums[index2] == target){
					//we have found our solution
					int[] solution = new int[]{index1, index2};
					return solution;   
				}
			}
		}
		//solution was not found
		return null;
	}

	/*Approach two - Hash Map Usage
	Time complexity : O(n). We go through the list containing n elements exactly twice.
	Once to fill in the hashmap and once to find our compliment within the hashmap (each lookup time in the hash map is O(1)).
	Space Complexity: O(n) We created another hashtable of n elements.
	 */
	public static int[] twoSum2(int[] nums, int target){
		//put the values of the array into a HashMap
		Map<Integer, Integer> myMap = new HashMap<>();
		for(int index = 0; index < nums.length; index++){
			myMap.put(nums[index], index);
		}
		//go through all of the values once and check if their compliment exists
		//ensuring the compliment is not itself
		for(int index = 0; index < nums.length; index++){
			int complement = target - nums[index];
			//.containsKey is equivalent to myMap.get(complement) != null
			if(myMap.containsKey(complement) && myMap.get(complement) != index){
				return new int[]{index, myMap.get(complement)};
			}
		}
		//solution was not found
		return null;
	}

	/*Approach three - Hash Map Usage with Same-time Lookup
	Time complexity : O(n) We go through the list containing n elements just once, inserting and checking in the same step.
	Space Complexity: O(n) We created another hashtable of n elements.
	 */
	public static int[] twoSum3(int[] nums, int target){
		Map<Integer, Integer> myMap = new HashMap<>();

		for(int index = 0; index < nums.length; index++){
			int complement = target - nums[index];
			//go through all of the values once and check if their compliment exists
			//this time we do not need to ensure the compliment is not itself since we insert it to the map at the end
			//.containsKey is equivalent to myMap.get(complement) != null
			if(myMap.containsKey(complement)){
				return new int[]{index, myMap.get(complement)};
			}
			//put the values of the array into a HashMap
			myMap.put(nums[index], index);
		}
		//solution was not found
		return null;
	}
	
}
