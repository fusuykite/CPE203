import java.util.List;
import java.util.LinkedList;

class SimpleList
{
   public static List<Integer> squareAll(List<Integer> values)
   {
      List<Integer> newValues = new LinkedList<Integer>();
		for (int x: values){
			int y = (int) Math.pow(x, 2);
			newValues.add(y);
		} 

      return newValues;
   }
}
