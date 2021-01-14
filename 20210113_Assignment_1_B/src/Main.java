
// Assignment 1 - The Subset-Sum Problem
// Part B (required) - The Subset Sum Problem For iTunesEntries

// Main.java -------------------------------------------------------------------
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import cs_1c.iTunesEntry;
import cs_1c.iTunesEntryReader;

public class Main
{
   public static void main(String[] args)
   {
      final int TARGET = 43200;

      ArrayList<iTunesEntry> dataSet = new ArrayList<iTunesEntry>();

      // read data
      iTunesEntryReader tunesInput = new iTunesEntryReader("itunes_file.txt");

      if (tunesInput.readError())
      {
         System.out.println(
               "couldn't open " + tunesInput.getFileName() + " for input.");
         return;
      }

      int arraySize = tunesInput.getNumTunes();
      int masterSum = 0;
      int masterMin = Integer.MAX_VALUE;

      // load data
      for (int i = 0; i < arraySize; i++)
      {
         iTunesEntry data = tunesInput.getTune(i);
         dataSet.add(data);
         int time = data.getTime();
         masterSum += time;

         if (time < masterMin)
         {
            masterMin = time;
         }
      }

      ArrayList<Sublist> choices = new ArrayList<Sublist>();
      Sublist sublist = new Sublist(dataSet);

      // add empty sublist to choices
      choices.add(sublist);

      System.out.println("target time:\n   " + TARGET + "\n");
      System.out.println("sublist:");

      long startTime = System.nanoTime();

      if (TARGET > masterSum)
      {
         System.out.println("   target is too large");
      }
      else if (TARGET < masterMin)
      {
         System.out.println("   target is too small");
      }
      else
      {
         boolean found = false;
         int maxSum = 0;
         int index = -1;

         // algorithm
         for (int i = 0; i < arraySize; i++)
         {
            int choicesSize = choices.size();

            for (int j = 0; j < choicesSize; j++)
            {
               sublist = choices.get(j);
               int sum = sublist.getSum() + dataSet.get(i).getTime();

               // add new sublist to choices
               if (sum <= TARGET)
               {
                  choices.add(sublist.addItem(i));

                  if (sum > maxSum)
                  {
                     maxSum = sum;
                     index = choices.size() - 1;
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

         // show subsets
         sublist = choices.get(index);

         System.out.println("   sum: " + sublist.getSum());
         sublist.showSublist();
      }

      long stopTime = System.nanoTime();

      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      // report algorithm time
      System.out.println("\nalgorithm elapsed time:\n   "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds");
   }
}

// @formatter:off (Eclipse formatter tag)

/* Output 1
target time:
   60

sublist:
   target is too small

algorithm elapsed time:
   0 seconds

 */

/* Output 2
target time:
   1800

sublist:
   sum: 1800
   array[0] = Carrie Underwood | Cowboy Casanova |  3:56
   array[3] = Foo Fighters | All My Life |  4:23
   array[4] = Foo Fighters | Monkey Wrench |  3:50
   array[6] = Eric Clapton | Bad Love |  5:08
   array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
   array[8] = Howlin' Wolf | Well That's All Right |  2:55
   array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
   array[10] = Reverend Gary Davis | Twelve Sticks |  3:14

algorithm elapsed time:
   0.002 seconds

 */

/* Output 3
target time:
   3600

sublist:
   sum: 3600
   array[0] = Carrie Underwood | Cowboy Casanova |  3:56
   array[1] = Carrie Underwood | Quitter |  3:40
   array[2] = Rihanna | Russian Roulette |  3:48
   array[4] = Foo Fighters | Monkey Wrench |  3:50
   array[5] = Eric Clapton | Pretending |  4:43
   array[6] = Eric Clapton | Bad Love |  5:08
   array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
   array[8] = Howlin' Wolf | Well That's All Right |  2:55
   array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
   array[11] = Roy Buchanan | Hot Cha |  3:28
   array[12] = Roy Buchanan | Green Onions |  7:23
   array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
   array[14] = Janiva Magness | You Were Never Mine |  4:36
   array[15] = John Lee Hooker | Hobo Blues |  3:07
   array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02

algorithm elapsed time:
   0.0256 seconds

 */

/* Output 4
target time:
   43200

sublist:
   target is too large

algorithm elapsed time:
   0 seconds

 */
