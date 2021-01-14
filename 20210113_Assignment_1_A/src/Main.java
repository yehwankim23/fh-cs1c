
// Assignment 1 - The Subset-Sum Problem
// Part A (required) - The Subset Sum Problem For Ints

// Main.java -------------------------------------------------------------------
import java.util.ArrayList;

public class Main
{
   public static void main(String[] args)
   {
      final int NUM_OF_ELEMENTS = 9;
      final int TARGET = 512;

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

         int maxSum = 0;
         int index = -1;

         for (int i = 0; i < collection.size(); i++)
         {
            int sum = collection.get(i).getSum();

            if (sum > maxSum)
            {
               maxSum = sum;
               index = i;
            }
         }

         // show subsets
         if (index == -1)
         {
            System.out.println("   target is too small");
         }
         else
         {
            subset = collection.get(index);

            System.out.println("   sum: " + subset.getSum());
            subset.showSublist();
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
target:
   0

subsets:
   target is too small

 */

/* Output 2
target:
   128

subsets:
   sum: 127
   array[0] = 1
   array[1] = 16
   array[2] = 25
   array[3] = 36
   array[4] = 49

 */

/* Output 3
target:
   256

subsets:
   sum: 256
   array[0] = 1
   array[1] = 9
   array[2] = 16
   array[3] = 36
   array[4] = 49
   array[5] = 64
   array[6] = 81

 */

/* Output 4
target:
   512

subsets:
   target is too large

 */
