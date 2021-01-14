
// Assignment 1 - The Subset-Sum Problem
// Part A (required) - The Subset Sum Problem For Ints

// Main.java -------------------------------------------------------------------
import java.util.ArrayList;

public class Main
{
   public static void main(String[] args)
   {
      final int NUM_OF_ELEMENTS = 9;
      final int TARGET = 300;

      ArrayList<Integer> sublist = new ArrayList<Integer>();
      int sublistSum = 0;

      // add elements to sublist
      for (int i = 1; i <= NUM_OF_ELEMENTS; i++)
      {
         int element = i * i;
         sublist.add(element);
         sublistSum += element;
      }

      ArrayList<Sublist> collection = new ArrayList<Sublist>();
      Sublist subset = new Sublist(sublist);

      // add empty subset to collection
      collection.add(subset);

      System.out.println("sublist:\n   " + sublist + "\n");
      System.out.println("target:\n   " + TARGET + "\n");
      System.out.println("subsets:");

      if (TARGET <= sublistSum)
      {
         // algorithm
         for (int i = 0; i < NUM_OF_ELEMENTS; i++)
         {
            int collectionSize = collection.size();

            for (int j = 0; j < collectionSize; j++)
            {
               subset = collection.get(j);
               int sum = subset.getSum() + sublist.get(i);

               // add new subset to collection
               if (sum <= TARGET)
               {
                  collection.add(subset.addItem(i));
               }
               else
               {
                  break;
               }
            }
         }

         // show subsets
         for (int i = 0; i < collection.size(); i++)
         {
            if (collection.get(i).getSum() == TARGET)
            {
               System.out.print("   ");
               collection.get(i).showSublist();
            }
         }
      }
      else
      {
         System.out.println("   target is too large");
      }
   }
}

// @formatter:off (Eclipse formatter tag)

/* Output 1
sublist:
   [1, 4, 9, 16, 25, 36, 49, 64, 81]

target:
   23

subsets:

 */

/* Output 2
sublist:
   [1, 4, 9, 16, 25, 36, 49, 64, 81]

target:
   100

subsets:
   [1, 9, 16, 25, 49]

 */

/* Output 3
sublist:
   [1, 4, 9, 16, 25, 36, 49, 64, 81]

target:
   200

subsets:
   [1, 9, 16, 25, 36, 49, 64]
   [9, 25, 36, 49, 81]

 */

/* Output 4
sublist:
   [1, 4, 9, 16, 25, 36, 49, 64, 81]

target:
   300

subsets:
   target is too large

 */
