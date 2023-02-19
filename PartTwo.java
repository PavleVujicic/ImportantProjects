/* ------------------------------------------------------ f22
 * 
 * course: 1406A
 * assignment: Assignment 1
 * question: What is a formula X consecutive positive integers that sum to a given number
 * for all X in a specified range?
 * 
 * name:  Pavle Vujicic
 * id: 101159343
 * 
 * comments: Formula for s+1 consecutive number
 * number = (a+1)+(a+2)+ ...(a+s)
 * for s+1 sequence
 * a=((1.0 * number - (s * (s + 1)) / 2) / (s + 1)
 * 
 -----------------------------------------------------------*/

public class PartTwo{


   /** Finds ALL runs of X consecutive positive integers that sum to a given number for all X in a specified range.
    * The range bounds are inclusive. 
    *
    * Examples: 
    *     runs(9,2,3) -> [ [4,5], [2,3,4] ]   // two runs found
    *     runs(9,1,1) -> [ [9] ]              // one run found
    *     runs(9,4,8) -> [ ]                  // no runs found!
    *     runs(125,1,8) -> [ [125], [62,63], [23,24,25,26,27]]   // 3 runs!
    *
    * @param number must be a positive integer
    * @param start is lower bound for the range (of consecutive numbers in the sum). It is a positive integer.
    * @param end is the upper bound for the range (of consecutive numbers in the sum). It is a positive 
    *        integer that is not smaller than start.
    * @return an array of arrays. Each inner array is a sequence of consecutive positive 
    *         integers that sum to the input number. The ordering inside the arrays is that 
    *         is the natural ordering (increasing order by value), and the ordering of the inside arrays
    *         is by increasing length.
    */
   public static int[][] runs(int number, int start, int end){
      		// count size of result array
		int count = countConsecutiveSequence(number, start, end);
		
		int[][] result = new int[count][];
		int i = 0;
		
		for (int s = start - 1; s * (s + 1) < 2 * number; s++) {
			if (s <= end - 1) {
				double current = (double) ((1.0 * number - (s * (s + 1)) / 2) / (s + 1));
				if (current - (int) current == 0.0) {
					int[] temp = new int[s + 1];
					for (int k = 0; k < s + 1; k++) {
						temp[k] = (int) current + k;
					}
					result[i] = temp;
					i++;
				}
			}
		}
		return result;
   }

   /** finds the locations of all local maximum values in a list of numbers.
    * 
    * A (local) maximum value is greater than both values adjacent to it. You can assume
    * The numbers at either end of the list (start or end) are maximal if the single number adjacent
    * to it is smaller. 
    *
    * Examples:
    *   int[] data1 = {1,7,1,24,15};
    *   peaks(data1) -> [1,3] since 7 and 24 are local maximal values
    *   int[] data2 = {20,1,-3,2,0,25,1,24,15,25,4,-2,-25};
    *   peaks(data2) -> [0,3,5,7,9]
    *   int[] data3 = {-123455};
    *   peaks(data3) -> [0]
    *
    *  @param data is an array of integers with at least one number in it
    *  @return an array storing the locations (index values) of all local maxima in the input array. 
    *          The size of this array will match the number of local maxima.
    */
   public static int[] peaks(int[] data){
      	// count size of result
		int count = countLocalMax(data);
		// System.out.println(count);
		int[] result = new int[count];
		int j = 0;
		for (int i = 0; i < data.length; i++) {

			if (i == 0) {
				if (i + 1 < data.length) {
					if (data[i] > data[i + 1] ) {
						result[j] = i;
						j++;
					}
				}else {
					result[j] = i;
				}
			} else if (i + 1 < data.length) {
				if (data[i] > data[i + 1] && data[i] > data[i - 1]) {
					result[j] = i;
					j++;
				}
			} else {
				if (data[i] > data[i - 1]) {
					result[j] = i;
					
				}
			}

		}
		return result;
   }    
   
  /** find a local minimum position in a 2-d grid of numbers using a steepest descent 
    * approach finds the location.
    *  
    * Starting with the provided startingRow and startingColumn, repeatedly take steps 
    * inside the grid in the direction that gives the greatest decrease in value. 
    * Consider the four (4) grid locations surrounding the current position (up, down, left, right)
    * and keep going until you can no longer make any progress. Do not take any "diagonal" steps.
    *
    * If there is a tie to move, break the tie as follows: UP before RIGHT before DOWN before LEFT. 
    *
    * The method returns the row and column of the local minimum found in an array [row,col]
    *
    * Examples:
    *   int[][] data1 = { {6,4,3,0},
    *                     {3,2,1,3},
    *                     {3,4,2,4},
    *                     {3,3,0,2}}; // four rows and four columns
    *   findMin(data1,0,0) -> [1,2]  (this makes it way to the number 1)
    *   findMin(data1,2,1) -> [1,2]  (this makes it way to the number 1)
    *   findMin(data1,3,2) -> [3,2]  (it does not move at all)
    *
    *   int[][] data2 = { {42} }; // one row and one column
    *   findMin(data2,0,0) -> [0,0] (there is only location so it must be the min!)
    *
    *   int[][] data3 = { {1,2,3,4,5,6,7,8,9,10} };  // one row, ten columns
    *   findMin(data3,0,3) -> [0,0] 
    *
    *   int[][] data4 = { {1}, {-2}, {3}, {4}, {5}, {6}, {70}, {800} }; // eight rows, one column
    *   findMin(data4,6,0) -> [1,0] (there is only location so it must be the min!)
    *
    *  @param data is a 2-d array of integers with at least 1 row and 1 column.
    *  @param startingRow is the row to start with 
    *  @param startingColumn is the column to start with
    *  @return an array [row,col] of size two that stores the row and column of the 
    *          local minimum found (using the rules described above)
    */
    public static int[] findMin(int[][] data, int startingRow, int startingColumn){
      
		int[] deepMin = new int[] { startingRow, startingColumn };;
		int i;
		int j;
		// loop until i and j are changed
		do {
			i=deepMin[0];
			j=deepMin[1];
		    // find local minimum deep for i and j 
			deepMin=findlocalMinimumDeep(data, i, j);

	     	
		}while (i!=deepMin[0] || j!=deepMin[1] );
		return deepMin;
	}
	
	
    // count consecutive sequences, needs for result array size
	private static int countConsecutiveSequence(int number, int rangeStart, int rangeEnd) {

		int count = 0;

		for (int s = rangeStart - 1; s * (s + 1) < 2 * number; s++) {
			if (s <= rangeEnd - 1) {
				double start = (double) ((1.0 * number - (s * (s + 1)) / 2) / (s + 1));
				if (start - (int) start == 0.0)
					count++;
			}
		}
		return count;
	}
	
	
    // count local max , needs for result array size
	private static int countLocalMax(int[] data) {
		int count = 0;

		// System.out.println(data.length);
		for (int i = 0; i < data.length; i++) {
			// System.out.println(i);
			if (i == 0) {
				if (i + 1 < data.length) {
					if (data[i] > data[i + 1] ) {
						count++;
					}
				}else {
					count++;
				}
			} else if (i + 1 < data.length) {
				if (data[i] > data[i + 1] && data[i] > data[i - 1]) {
					count++;
				}
			} else {
				if (data[i] > data[i - 1]) {
					count++;
				}
			}
		}
		// System.out.println(count);
		return count;
	}
	
	
	// find min - compare in order up right, down, left
	// if min is not changed return input rowIndex and colIndex
	
	
	private static int[]  findlocalMinimumDeep(int[][] array, int rowIndex, int colIndex) {
        int topRowIndex = rowIndex-1; 
        int bottomRowIndex = rowIndex+1; 
        int	leftColIndex = colIndex-1; 
        int	rightColIndex = colIndex+1;
        
        int []result = new int[] { rowIndex, colIndex };
        
        int rowSize = array.length;
        int colSize =array[rowIndex].length;
        
        if( -1 < topRowIndex && topRowIndex < rowSize){
            if(( array[rowIndex][colIndex] > array[topRowIndex][colIndex])){
            	result[0]=topRowIndex;
            	result[1]=colIndex;
            }
        }
        
        if( -1 < rightColIndex && rightColIndex < colSize){
            if(( array[rowIndex][colIndex] > array[rowIndex][rightColIndex])){ 
            	if(array[result[0]][result[1]] > array[rowIndex][rightColIndex]) {
            		result[0]=rowIndex;
                	result[1]=rightColIndex;
            	}
            }
        }
        
        if( -1 < bottomRowIndex && bottomRowIndex < rowSize){
            if(( array[rowIndex][colIndex] > array[bottomRowIndex][colIndex])){ 
            	if(array[result[0]][result[1]] > array[bottomRowIndex][colIndex]) {
            		result[0]=bottomRowIndex;
                	result[1]=colIndex;
            	}
            }
        }
        
        if( -1 < leftColIndex && leftColIndex < colSize){
            if(( array[rowIndex][colIndex] > array[rowIndex][leftColIndex])){ 
            	if(array[result[0]][result[1]] > array[rowIndex][leftColIndex]) {
            		result[0]=rowIndex;
                	result[1]=leftColIndex;
            	}
            }
        }
        
      
        
        return result;
       
   }    

   /* I might be a program! */
   public static void main(String[] args){
      	// use this for testing your methods if you wish...
		//int N = 125;
		//System.out.println(countConsecutiveSequence(N, 1, 8));
		//N = 9;
		//System.out.println(countConsecutiveSequence(N, 4, 8));
		//N = 9;
		//System.out.println(countConsecutiveSequence(N, 2, 3));
		// * Examples: runs(9,2,3) -> [ [4,5], [2,3,4] ] // two runs found runs(9,1,1) ->
		//		 * [ [9] ] // one run found runs(9,4,8) -> [ ] // no runs found! runs(125,1,8)
		//		 * -> [ [125], [62,63], [23,24,25,26,27]] // 3 runs!
		System.out.println("TEST FOR RUNS ==================================================");
		 System.out.println("\ntesting  runs(9, 2, 3)");
		 //runs(9,2,3) -> [ [4,5], [2,3,4] ] 
		int[][] input1 = runs(9, 2, 3);
		String expected="{[4, 5],[2, 3, 4]}";
		String result="{";
		for (int i = 0; i < input1.length; i ++) {
			if (i > 0) {
				result=result+",";
			}
			result=result+java.util.Arrays.toString(input1[i]);
			
		}
		result=result+"}";
		System.out.print("runs(9, 2, 3) ) expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		 System.out.println("\ntesting  runs(9,1,1)");
		 //runs(9,1,1 -> [ [9] ] 
		input1 = runs(9,1,1);
		expected="{[9]}";
		result="{";
		for (int i = 0; i < input1.length; i ++) {
			if (i > 0) {
				result=result+",";
			}
			result=result+java.util.Arrays.toString(input1[i]);
			
		}
		result=result+"}";
		System.out.print("runs(9,1,1) ) expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		 System.out.println("\ntesting  runs(9,4,8)");
		 //runs(9,4,8 -> [  ] 
		input1 = runs(9,4,8);
		expected="{}";
		result="{";
		for (int i = 0; i < input1.length; i ++) {
			if (i > 0) {
				result=result+",";
			}
			result=result+java.util.Arrays.toString(input1[i]);
			
		}
		result=result+"}";
		System.out.print("runs((9,4,8) expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		
		 System.out.println("\ntesting  runs(125,1,8)");
		 //runs(125,1,8) -> [ [125], [62,63], [23,24,25,26,27] ] 
		input1 = runs(125,1,8);
		expected="{[125],[62, 63],[23, 24, 25, 26, 27]}";
		result="{";
		for (int i = 0; i < input1.length; i ++) {
			if (i > 0) {
				result=result+",";
			}
			result=result+java.util.Arrays.toString(input1[i]);
			
		}
		result=result+"}";
		System.out.print("runs(125,1,8) expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		
		System.out.println("TEST FOR PEAKS ==================================================");
		//--------------------------------------------------
		// * Examples: int[] data1 = {1,7,1,24,15}; peaks(data1) -> [1,3] since 7 and 24
		// * are local maximal values int[] data2 = {20,1,-3,2,0,25,1,24,15,25,4,-2,-25};
		//* peaks(data2) -> [0,3,5,7,9] int[] data3 = {-123455}; peaks(data3) -> [0]
		System.out.println("\ntesting   int[] data1 = {1,7,1,24,15}; peaks(data1)");
		int[] input2 = { 1, 7, 1, 24, 15 };
		int[] input3 =  peaks(input2);
		expected="[1, 3]";
		result=java.util.Arrays.toString(input3);
	
		System.out.print("int[] data1 = {1,7,1,24,15}; peaks(data1) expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		System.out.println("\ntesting   int[] data2 = {20,1,-3,2,0,25,1,24,15,25,4,-2,-25};; peaks(data2)");
		int[] input4 = { 20, 1, -3, 2, 0, 25, 1, 24, 15, 25, 4, -2, -25 };
		input3 =  peaks(input4);
		expected="[0, 3, 5, 7, 9]";
		result=java.util.Arrays.toString(input3);
	
		System.out.print("int[] data2 = {20,1,-3,2,0,25,1,24,15,25,4,-2,-25}; peaks(data2) expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		System.out.println("\ntesting   int[] data3 = {-123455}; peaks(data3)");
		int[] input5 = { -123455 };
		input3 =  peaks(input5);
		expected="[0]";
		result=java.util.Arrays.toString(input3);
	
		System.out.print("int[] data3 = {-123455}; peaks(data3) expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------

		
		
		System.out.println("TEST FOR FINDMIN==================================================");
		
		// *
		// * Examples: int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}}; //
		// * four rows and four columns findMin(data1,0,0) -> [1,2] (this makes it way to
		// * the number 1) findMin(data1,2,1) -> [1,2] (this makes it way to the number 1)
		// * findMin(data1,3,2) -> [3,2] (it does not move at all)
		// *
		 //* int[][] data2 = { {42} }; // one row and one column findMin(data2,0,0) ->
		// * [0,0] (there is only location so it must be the min!)
		// *
		// * int[][] data3 = { {1,2,3,4,5,6,7,8,9,10} }; // one row, ten columns
		// * findMin(data3,0,3) -> [0,0]
		 //*
		// * int[][] data4 = { {1}, {-2}, {3}, {4}, {5}, {6}, {70}, {800} }; // eight
		// * rows, one column findMin(data4,6,0) -> [1,0] (there is only location so it
		// * must be the min!)
		System.out.println("\ntesting   int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}}; findMin(data1,0,0);");
		int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}};
		input3 =  findMin(data1,0,0);
		expected="[1, 2]";
		result=java.util.Arrays.toString(input3);
	
		System.out.print(" int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}}; findMin(data1,0,0); expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		System.out.println("\ntesting   int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}}; findMin(data1,2,1);");
	
		input3 =  findMin(data1,2,1);
		expected="[1, 2]";
		result=java.util.Arrays.toString(input3);
	
		System.out.print(" int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}}; findMin(data1,2,1); expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		System.out.println("\ntesting   int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}}; findMin(data1,3,2);");
		
		input3 =  findMin(data1,3,2);
		expected="[3, 2]";
		result=java.util.Arrays.toString(input3);
	
		System.out.print(" int[][] data1 = { {6,4,3,0}, {3,2,1,3}, {3,4,2,4}, {3,3,0,2}}; findMin(data1,3,2); expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
        System.out.println("\ntesting   int[][] data2 = { {42} };  findMin(data2,0,0);");
    	int[][] data2 = { {42} }; 
		input3 =  findMin(data2,0,0);
		expected="[0, 0]";
		result=java.util.Arrays.toString(input3);
	
		System.out.print(" int[][] data2 = { {42} };  findMin(data2,0,0); expecting " + expected + ", got "+result + " : ");
		
		 if( result.equals(expected)){
	         System.out.println(" [passed]");
	      }else{
	         System.out.println(" [failed]");
	      }
		
		System.out.println();
		//--------------------------------------------------
		
		  System.out.println("\ntesting   int[][] data3 = { {1,2,3,4,5,6,7,8,9,10} };   findMin(data3,0,3);");
		  int[][] data3 = { {1,2,3,4,5,6,7,8,9,10} }; 
			input3 =  findMin(data3,0,3);
			expected="[0, 0]";
			result=java.util.Arrays.toString(input3);
		
			System.out.print(" int[][] data3 = { {1,2,3,4,5,6,7,8,9,10} };   findMin(data3,0,3); expecting " + expected + ", got "+result + " : ");
			
			 if( result.equals(expected)){
		         System.out.println(" [passed]");
		      }else{
		         System.out.println(" [failed]");
		      }
			
			System.out.println();
			//--------------------------------------------------
			
			System.out.println("\ntesting   int[][] data4 = { {1}, {-2}, {3}, {4}, {5}, {6}, {70}, {800} };   findMin(data4,6,0);");
			int[][] data4 = { {1}, {-2}, {3}, {4}, {5}, {6}, {70}, {800} }; 
				input3 =  findMin(data4,6,0);
				expected="[1, 0]";
				result=java.util.Arrays.toString(input3);
			
				System.out.print(" int[][] data4 = { {1}, {-2}, {3}, {4}, {5}, {6}, {70}, {800} };   findMin(data4,6,0); expecting " + expected + ", got "+result + " : ");
				
				 if( result.equals(expected)){
			         System.out.println(" [passed]");
			      }else{
			         System.out.println(" [failed]");
			      }
				
				System.out.println();
				//--------------------------------------------------     
   }

}