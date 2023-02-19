/* ------------------------------------------------------ f22
 * 
 * course: 1406A
 * assignment: Assignment 1 
 * question: What is formula for sum of three consecutive numbers?
 * 
 * name:  Pavle Vujicic
 * id: 101159343
 * 
 * comments: 
 * Sum of three consecutive numbers:
 * Sum = a+a+1+a+2 = 3a+3
 * 
 -----------------------------------------------------------*/


public class PartOne{

   /** Checks if a number is the sum of three consecutive positive integers
    * 
    * Examples: 
    *     checkForRunOfThree(9) -> true since 2+3+4=9 and 2,3,4>0
    *     checkForRunOfThree(8) -> false      
    *     checkForRunOfThree(192) -> true since 63+64+65=192
    *     checkForRunOfThree(3) -> false since 0+1+2=3, but 0 is NOT a positive number            
    *
    * @param number must be a positive integer
    * @return true if the input number is the sum of three consecutive positive integers, otherwise false
    */
   public static boolean checkForRunOfThree(int number){
      boolean result= false;
      if(runOfThreeStartingValue(number)!= -1){
         result= true;
      }
      return result;
   }

   /** Finds the FIRST of the three consecutive positive integers that sum to a given number or
    *  indicates that the number is not the sum of three consecutive positive integers. 
    *
    * Examples: 
    *     RunOfThreeStartingValue(9) -> 2 since 2+3+4=9 and 2,3,4>0
    *     RunOfThreeStartingValue(8) -> -1 since checkForRunOfThree(8) is false
    *     RunOfThreeStartingValue(192) -> 63 since 63+64+65=192
    *     RunOfThreeStartingValue(3) -> -1 since checkForRunOfThree(3) is false
    *
    * @param number must be a positive integer
    * @return the first of three consecutive positive integers that sum to the input number or -1 if the 
    *         number is not the sum of three consecutive positive integers
    */
   public static int runOfThreeStartingValue(int number){
      int result= -1;
      int sum= 0;
      for(int i=1; i< number; i++){
         sum= 3*i+3;
         if(sum== number){
            result=i;
            break;
         }
      }
      return result;
   }

   /** finds the first location of the maximal value in a list of numbers.
    * 
    * Examples:
    *   int[] data1 = {1,3,1,24,15};
    *   peakLocation(data1) -> 3 since 24 is the largest value
    *   int[] data2 = {20,1,-3,2,0,25,1,24,15,25,4,-2,25};
    *   peakLocation(data2) -> 5 since it is the FIRST location of the maximal value 25
    *   int[] data3 = {-123455};
    *   peakLocation(data3) -> 0 since there is only value in the array (so it is maximal)
    *  @param data is an array of integers with at least one number in it
    *  @return the index value of the first instance of the maximal value in the input data
    */
   public static int peakLocation(int[] data){
      int result= 0;
      int max= data[0];
      for(int i=1; i< data.length; i++){
         if(max< data[i]){
            max= data[i];
            result= i;
         }
      }
      return result;
   }    
   

   /* I am a program! */
   public static void main(String[] args){
      System.out.println("\nSome simple testing");
      System.out.println("-------------------");
      
      // checkForRunOfThree
      System.out.println("\ntesting checkForRunOfThree()");
      int[] input1 = {9,8,192,3};
      boolean[] expected1 = {true, false, true, false};
      for(int i=0; i<input1.length; i+=1){
         System.out.print("checkForRunOfThree(" + input1[i] + ") expecting " + expected1[i] + ", got ");
         boolean actual = checkForRunOfThree( input1[i]);
         System.out.print(actual);
         if( actual == expected1[i]){
            System.out.println(" [passed]");
         }else{
            System.out.println(" [failed]");
         }
      }

   // runOfThreeStartingValue
   System.out.println("\ntesting runOfThreeStartingValue()");
   int[] input2 = {9,8,192,3};
   int[] expected2 = {2, -1, 63, -1};
   for(int i=0; i<input2.length; i+=1){
      System.out.print("runOfThreeStartingValue(" + input2[i] + ") expecting " + expected2[i] + ", got ");
      int actual = runOfThreeStartingValue( input2[i]);
      System.out.print(actual);
      if( actual == expected2[i]){
         System.out.println(" [passed]");
      }else{
         System.out.println(" [failed]");
      }
   }

   
   // peakLocation()
   System.out.println("\ntesting peakLocation()");
   int[][] input3 = { {1,3,1,24,15}, {20,1,-3,2,0,25,1,24,15,25,4,-2,25}, {-123455}};
   int[] expected3 = {3,5,0};
   for(int i=0; i<input3.length; i+=1){
      System.out.print("peakLocation(" + java.util.Arrays.toString(input3[i]) + ") expecting " + expected3[i] + ", got ");
      int actual = peakLocation( input3[i]);
      System.out.print(actual);
      if( actual == expected3[i]){
         System.out.println(" [passed]");
      }else{
         System.out.println(" [failed]");
      }
   }
   
   }

}