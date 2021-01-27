
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

         for (int i = 0; i < arraySize; i++)
         {
            sublist = sublist.addItem(i);
         }

         System.out.println("   sum: " + sublist.getSum());
         sublist.showSublist();
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
   sum: 22110
   array[0] = Carrie Underwood | Cowboy Casanova |  3:56
   array[1] = Carrie Underwood | Quitter |  3:40
   array[2] = Rihanna | Russian Roulette |  3:48
   array[3] = Foo Fighters | All My Life |  4:23
   array[4] = Foo Fighters | Monkey Wrench |  3:50
   array[5] = Eric Clapton | Pretending |  4:43
   array[6] = Eric Clapton | Bad Love |  5:08
   array[7] = Howlin' Wolf | Everybody's In The Mood |  2:58
   array[8] = Howlin' Wolf | Well That's All Right |  2:55
   array[9] = Reverend Gary Davis | Samson and Delilah |  3:36
   array[10] = Reverend Gary Davis | Twelve Sticks |  3:14
   array[11] = Roy Buchanan | Hot Cha |  3:28
   array[12] = Roy Buchanan | Green Onions |  7:23
   array[13] = Janiva Magness | I'm Just a Prisoner |  3:50
   array[14] = Janiva Magness | You Were Never Mine |  4:36
   array[15] = John Lee Hooker | Hobo Blues |  3:07
   array[16] = John Lee Hooker | I Can't Quit You Baby |  3:02
   array[17] = Snoop Dogg | That's The Homie |  5:43
   array[18] = Snoop Dogg | Gangsta Luv |  4:17
   array[19] = The Rubyz | Ladies and Gentleman |  3:21
   array[20] = The Rubyz | Watch the Girl |  3:12
   array[21] = Veggie Tales | Donuts for Benny |  3:04
   array[22] = Veggie Tales | Our Big Break |  1:09
   array[23] = Berliner Philharmoniker | Brahms: Symphony No. 1 in C Minor Op. 6
8 |  13:59
   array[24] = Berliner Philharmoniker | Brahms: Symphony No. 4 in E Minor Op. 9
8 |  13:20
   array[25] = Yo-yo Ma | Bach: Suite for Cello No. 1 in G Major Prelude |  2:21
   array[26] = Yo-yo Ma | Simple Gifts |  2:34
   array[27] = Ry Cooter | Alimony |  2:55
   array[28] = Ry Cooter | France Chance |  2:48
   array[29] = Aaron Watson | The Road |  3:24
   array[30] = Terra Incognita | Clone |  4:58
   array[31] = Terra Incogni | Lizard Skin |  4:30
   array[32] = Blue Record | Bullhead's Psalm |  1:19
   array[33] = Blue Record | Ogeechee Hymnal |  2:35
   array[34] = Mastadon | Oblivion |  5:48
   array[35] = Mastadon | The Bit |  4:55
   array[36] = Sean Kingston | Fire Burning |  3:59
   array[37] = Sean Kingston | My Girlfriend |  3:24
   array[38] = T-Pain | Take Your Shirt Off |  3:48
   array[39] = Lil Jon | Give It All U Got |  3:38
   array[40] = Jay-Z | What We Talkin' About |  4:03
   array[41] = Jay-Z | Empire State of Mind |  4:36
   array[42] = Snoop Dog | Think About It |  3:37
   array[43] = Snoop Dog | Lil' Crips |  3:15
   array[44] = Jeff Golub | Shuffleboard |  3:30
   array[45] = Jeff Golub | Goin' On |  5:56
   array[46] = Jeff Golub | Fish Fare |  4:59
   array[47] = Caraivana | Noites Cariocas |  4:12
   array[48] = Caraivana | Tico-Tico No Fuba |  2:27
   array[49] = John Patitucci | Monk/Trane |  7:14
   array[50] = John Patitucci | Sonny Side |  7:25
   array[51] = Nina Simone | Pirate Jenny |  6:42
   array[52] = Nina Simone | The Other Woman |  3:06
   array[53] = Nina Simone | Feeling Good |  2:57
   array[54] = John Coltrane | A Love Supreme Part 1 |  7:42
   array[55] = John Coltrane | In a Sentimental Mood |  4:16
   array[56] = AOL Dejando Huellas | Dime Si te Vas Con El |  3:24
   array[57] = AOL Dejando Huella | Te Amo Tanto |  3:12
   array[58] = McCoy Tyner | Blues On the Corner |  6:07
   array[59] = McCoy Tyner | Afro Blue |  12:22
   array[60] = Kanye West | Stronger |  5:11
   array[61] = Kanye West | Good Life |  3:27
   array[62] = Steely Dan | Black Cow |  5:10
   array[63] = Steely Dan | Kid Charlemagne |  4:38
   array[64] = Steely Dan | Haitian Divorce |  5:51
   array[65] = Herbie Hancock | Nefertiti |  7:31
   array[66] = Herbie Hancock | Rockit |  5:25
   array[67] = Herbie Hancock | Chameleon |  15:41
   array[68] = Return to Forever | Medieval Overture |  5:13
   array[69] = Suzanne Vega | Luka |  3:51
   array[70] = Suzanne Vega | Small Blue Thing |  3:55
   array[71] = Bonnie Raitt | Something to Talk About |  3:47
   array[72] = Bonnie Raitt | I Can't Make You Love Me |  5:31
   array[73] = Natalie Cole | This Will Be |  2:51
   array[74] = Natalie Cole | Unforgettable |  3:31
   array[75] = Jet | Timothy |  4:20
   array[76] = Jet | Rip It Up |  3:20
   array[77] = Was (Not Was) | Where Did Your Heart Go? |  5:47
   array[78] = Was (Not Was) | I Blew Up The United States |  3:50

algorithm elapsed time:
   0.0016 seconds

 */
