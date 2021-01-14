
// Sublist.java ----------------------------------------------------------------
import java.util.ArrayList;

public class Sublist<E> implements Cloneable
{
   private ArrayList<E> originalObjects;
   private ArrayList<Integer> indices;
   private int sum;

   public Sublist(ArrayList<E> orig)
   {
      originalObjects = orig;
      indices = new ArrayList<Integer>();
      sum = 0;
   }

   public int getSum()
   {
      return sum;
   }

   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
      Sublist<E> newObject = (Sublist<E>) super.clone();
      newObject.indices = (ArrayList<Integer>) indices.clone();

      return newObject;
   }

   @SuppressWarnings("unchecked")
   public Sublist<E> addItem(int indexOfItemToAdd, int valueOfItemToAdd)
   {
      Sublist<E> subset = null;

      try
      {
         subset = (Sublist<E>) this.clone();
      }
      catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }

      subset.indices.add(indexOfItemToAdd);
      subset.sum += valueOfItemToAdd;

      return subset;
   }

   public void showSublist()
   {
      for (int i = 0; i < indices.size(); i++)
      {
         int index = indices.get(i);

         System.out.println(
               "   array[" + index + "] = " + originalObjects.get(index));
      }
   }
}
