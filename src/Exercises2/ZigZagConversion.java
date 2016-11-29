package Exercises2;

/*THE TASK:
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * 
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

String convert(String text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

 */

public class ZigZagConversion {
	public static void main(String[] args) {
		String s = "ILOVEICECREAM";
		System.out.println(convert(s, 4));
	}

	public static String convert(String s, int numRows) {
		if(numRows == 1){
			return s;
		}
		else{
			//each element in the array will represent a row  
			String[] rowsArray = new String[numRows];
			
			//fill the array with empty strings
			for (int index = 0; index < rowsArray.length; index++) {
				rowsArray[index] = "";
			}

			int lengthOfString = s.length();

			//loop through all of the letters and add them to the relevant "row" representation in our array
			int currentRow = 0;
			
			//this boolean will the direction of flow through our rows
			boolean isUpwards = true;
			for (int index = 0; index < lengthOfString; index++) {
				char currentChar = s.charAt(index);
				rowsArray[currentRow] = rowsArray[currentRow] + currentChar;

				//iterate through the rows
				if(currentRow < (numRows-1) && isUpwards){
					currentRow++;
				}
				//we reached the last row, start going back down
				else if (currentRow == (numRows-1)){
					isUpwards = false;
					currentRow--;
				}
				//going down but have not reached the bottom row
				else if (!isUpwards && currentRow !=0){
					currentRow--;
				}
				//going down & reached the bottom row
				else if(currentRow == 0){
					isUpwards = true;
					currentRow++;
				}
			}
			
			//go through the array and recreate the String
			String zigZagString = "";
			for (int index = 0; index < rowsArray.length; index++) {
				zigZagString+= rowsArray[index];
			}
			return zigZagString;
		}
	}
}
