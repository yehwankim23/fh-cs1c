
// Sublist.java ----------------------------------------------------------------
import java.util.ArrayList;

public class Sublist implements Cloneable
{
   private ArrayList<Integer> originalObjects;
   private ArrayList<Integer> indices;
   private int sum;

   public Sublist(ArrayList<Integer> orig)
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
      Sublist newObject = (Sublist) super.clone();
      newObject.indices = (ArrayList<Integer>) indices.clone();

      return newObject;
   }

   public Sublist addItem(int indexOfItemToAdd)
   {
      Sublist subset = null;

      try
      {
         subset = (Sublist) this.clone();
      }
      catch (CloneNotSupportedException e)
      {
         e.printStackTrace();
      }

      int element = originalObjects.get(indexOfItemToAdd);
      subset.indices.add(element);
      subset.sum += element;

      return subset;
   }

   public void showSublist()
   {
      System.out.print("[");

      for (int i = 0; i < indices.size(); i++)
      {
         System.out.print(indices.get(i));

         if (i < indices.size() - 1)
         {
            System.out.print(", ");
         }
      }

      System.out.println("]");
   }
}
