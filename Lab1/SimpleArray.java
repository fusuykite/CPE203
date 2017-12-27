class SimpleArray
{
   public static int [] squareAll(int values[])
   {


 		int [] newValues = new int[values.length];
		int i = 0;
		while (i < values.length) {
			newValues[i] = (int) Math.pow(values[i], 2);
			i ++; }
		return newValues;
			
      /* TO DO: The output array, newValues, should hold as
         its elements the square of the corresponding element
         in the input array.

         Write a loop to compute the square of each element from the
         input array and to place the result into the output array.
      */

   }
}
