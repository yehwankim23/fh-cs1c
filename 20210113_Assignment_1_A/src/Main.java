
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
      int sublistMin = Integer.MAX_VALUE;

      // add elements to sublist
      for (int i = 1; i <= NUM_OF_ELEMENTS; i++)
      {
         int element = i * i;
         sublist.add(element);
         sublistSum += element;

         if (element < sublistMin)
         {
            sublistMin = element;
         }
      }

      ArrayList<Sublist> collection = new ArrayList<Sublist>();
      Sublist subset = new Sublist(sublist);

      // add empty subset to collection
      collection.add(subset);

      System.out.println("target:\n   " + TARGET + "\n");
      System.out.println("subsets:");

      if (TARGET > sublistSum)
      {
         System.out.println("   target is too large");

         for (int i = 0; i < NUM_OF_ELEMENTS; i++)
         {
            subset = subset.addItem(i);
         }

         System.out.println("   sum: " + subset.getSum());
         subset.showSublist();
      }
      else if (TARGET < sublistMin)
      {
         System.out.println("   target is too small");
      }
      else
      {
         boolean found = false;
         int maxSum = 0;
         int index = -1;

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

                  if (sum > maxSum)
                  {
                     maxSum = sum;
                     index = collection.size() - 1;
                  }

                  if (sum == TARGET)
                  {
                     found = true;
                     break;
                  }
               }
            }

            if (found)
            {
               break;
            }
         }

         // show subset
         subset = collection.get(index);

         System.out.println("   sum: " + subset.getSum());
         subset.showSublist();
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
   array[3] = 16
   array[4] = 25
   array[5] = 36
   array[6] = 49

 */

/* Output 3
target:
   256

subsets:
   sum: 256
   array[0] = 1
   array[2] = 9
   array[3] = 16
   array[5] = 36
   array[6] = 49
   array[7] = 64
   array[8] = 81

 */

/* Output 4
target:
   512

subsets:
   target is too large
   sum: 285
   array[0] = 1
   array[1] = 4
   array[2] = 9
   array[3] = 16
   array[4] = 25
   array[5] = 36
   array[6] = 49
   array[7] = 64
   array[8] = 81

 */
