
// Sublist.java ----------------------------------------------------------------
import java.util.ArrayList;

import cs_1c.iTunesEntry;

public class Sublist implements Cloneable
{
   private ArrayList<iTunesEntry> originalObjects;
   private ArrayList<Integer> indices;
   private int sum;

   public Sublist(ArrayList<iTunesEntry> orig)
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

      subset.indices.add(indexOfItemToAdd);
      subset.sum += originalObjects.get(indexOfItemToAdd).getTime();

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
