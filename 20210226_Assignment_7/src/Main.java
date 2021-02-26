
// Assignment 7 - Analyzing Shellsort's Gaps
// A Flexible ShellSort

// Main.java ---------------------------------------------------------------
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import cs_1c.MySort;

public class Main
{
   public static <E extends Comparable<? super E>> void shellSortX(E[] array,
         int[] gapSequence)
   {
      for (int gapIndex = gapSequence.length - 1; gapIndex >= 0; gapIndex--)
      {
         int gap = gapSequence[gapIndex];

         for (int position = gap; position < array.length; position++)
         {
            E temp = array[position];
            int index = position;

            for (; index >= gap && temp.compareTo(array[index - gap]) < 0;
                  index -= gap)
            {
               array[index] = array[index - gap];
            }

            array[index] = temp;
         }
      }
   }

   public static void main(String[] args)
   {
      final int SEQUENCE_SIZE = 20;
      final int ARRAY_SIZE = 200000;

      System.out.println("array size: " + ARRAY_SIZE + "\n");

      int[] shellSequence = new int[SEQUENCE_SIZE];
      int[] sedgewickSequence = new int[SEQUENCE_SIZE];
      int[] hibbardSequence = new int[SEQUENCE_SIZE];

      for (int i = 0; i < SEQUENCE_SIZE; i++)
      {
         shellSequence[i] = 1 << i;
         sedgewickSequence[i] = i % 2 == 0
               ? (9 * (2 << (i - 1))) - (9 * (2 << (i / 2 - 1))) + 1
               : (2 << (2 * (i / 2 + 2) - 1)) - (3 * (2 << (i / 2 + 1))) + 1;
         hibbardSequence[i] = (2 << i) - 1;
      }

      System.out.println("gap sequences:");
      System.out
            .println("   shell (explicit): " + Arrays.toString(shellSequence));
      System.out.println("   sedgewick: " + Arrays.toString(sedgewickSequence));
      System.out
            .println("   hibbard: " + Arrays.toString(hibbardSequence) + "\n");

      Integer[] array1 = new Integer[ARRAY_SIZE];
      Integer[] array2 = new Integer[ARRAY_SIZE];
      Integer[] array3 = new Integer[ARRAY_SIZE];
      Integer[] array4 = new Integer[ARRAY_SIZE];

      Random random = new Random();

      for (int i = 0; i < ARRAY_SIZE; i++)
      {
         int randomInt = random.nextInt(ARRAY_SIZE) + 1;

         array1[i] = randomInt;
         array2[i] = randomInt;
         array3[i] = randomInt;
         array4[i] = randomInt;
      }

      System.out.println("sort times:");

      DecimalFormat format = new DecimalFormat("0.0000");

      long startTime = System.nanoTime();
      MySort.shellSort1(array1);
      long stopTime = System.nanoTime();
      System.out.println("   shell (implied): "
            + format.format((stopTime - startTime) / 1e6) + " ms");

      startTime = System.nanoTime();
      shellSortX(array2, shellSequence);
      stopTime = System.nanoTime();
      System.out.println("   shell (explicit): "
            + format.format((stopTime - startTime) / 1e6) + " ms");

      startTime = System.nanoTime();
      shellSortX(array3, sedgewickSequence);
      stopTime = System.nanoTime();
      System.out.println("   sedgewick: "
            + format.format((stopTime - startTime) / 1e6) + " ms");

      startTime = System.nanoTime();
      shellSortX(array4, hibbardSequence);
      stopTime = System.nanoTime();
      System.out.println("   hibbard: "
            + format.format((stopTime - startTime) / 1e6) + " ms");
   }
}

// @formatter:off (Eclipse formatter tag)

/* Output 1
array size: 10000

gap sequences:
   shell (explicit): [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8
192, 16384, 32768, 65536, 131072, 262144, 524288]
   sedgewick: [1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289,
 64769, 146305, 260609, 587521, 1045505, 2354689, 4188161]
   hibbard: [1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 
32767, 65535, 131071, 262143, 524287, 1048575]

sort times:
   shell (implied): 5.4104 ms
   shell (explicit): 7.1995 ms
   sedgewick: 7.5686 ms
   hibbard: 4.6367 ms

 */

/* Output 2
array size: 200000

gap sequences:
   shell (explicit): [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8
192, 16384, 32768, 65536, 131072, 262144, 524288]
   sedgewick: [1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289,
 64769, 146305, 260609, 587521, 1045505, 2354689, 4188161]
   hibbard: [1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 
32767, 65535, 131071, 262143, 524287, 1048575]

sort times:
   shell (implied): 130.5824 ms
   shell (explicit): 225.8116 ms
   sedgewick: 89.7332 ms
   hibbard: 106.7140 ms

 */

/* Comments
- Run each gap sequence on six sizes of int arrays ranging from 10,000 to
  200,000. Create a table of the four sequences and six array sizes.
   - ┌────────────┬────────────┬────────────┬────────────┬────────────┐
     │    Size    │ Shell (i)  │ Shell (e)  │ Sedgewick  │  Hibbard   │
     ├────────────┼────────────┼────────────┼────────────┼────────────┤
     │    10000   │    5.4104  │    7.1995  │    7.5686  │    4.6367  │
     ├────────────┼────────────┼────────────┼────────────┼────────────┤
     │    48000   │   27.5360  │   43.4165  │   10.6872  │   13.0061  │
     ├────────────┼────────────┼────────────┼────────────┼────────────┤
     │    86000   │   44.0681  │   83.8158  │   21.6461  │   26.1218  │
     ├────────────┼────────────┼────────────┼────────────┼────────────┤
     │   124000   │   66.9225  │  126.6656  │   35.8446  │   45.6647  │
     ├────────────┼────────────┼────────────┼────────────┼────────────┤
     │   162000   │   96.9022  │  169.6972  │   54.8119  │   75.5608  │
     ├────────────┼────────────┼────────────┼────────────┼────────────┤
     │   200000   │  130.5824  │  225.8116  │   89.7332  │  106.7140  │
     └────────────┴────────────┴────────────┴────────────┴────────────┘
     (i: implied / e: explicit)

- Why does Shell's gap sequence implied by shellSort1() give a different timing
  result that the explicit array described above and passed to shellSortX()?
  Which is faster and why?
   - The implied sequence starts with the array size and divides by 2, while the
     explicit sequence always uses exponents of 2. The implied sequence is
     faster because it can use odd gaps, which reduces overlaps in compared
     array indexes.

 */
