
// Assignment 3 - Timing Matrix Multiplication
// Part A (required) - A Simple Array Implementation for Large Matrices

// Main.java -------------------------------------------------------------------
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Main
{
   public static void main(String[] args)
   {
      final int MAT_SIZE = 4000;

      // allocate matrices
      double[][] mat = new double[MAT_SIZE][MAT_SIZE];
      double[][] matAns = new double[MAT_SIZE][MAT_SIZE];

      // generate semi-sparse matrix
      Random random = new Random();

      for (int i = 0; i < MAT_SIZE / 10. * MAT_SIZE; i++)
      {
         mat[random.nextInt(MAT_SIZE)][random.nextInt(MAT_SIZE)]
               = random.nextDouble();
      }

      matShow(mat, MAT_SIZE - 10, 10);

      long startTime = System.nanoTime();

      matMult(mat, mat, matAns);

      long stopTime = System.nanoTime();

      matShow(matAns, MAT_SIZE - 10, 10);

      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      System.out.println("Size = " + MAT_SIZE + " / Elapsed time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds");
   }

   public static void matMult(double[][] matA, double[][] matB, double[][] matC)
   {
      if (matA[0].length == matB.length)
      {
         for (int row = 0; row < matA.length; row++)
         {
            for (int column = 0; column < matB[0].length; column++)
            {
               double sum = 0;

               for (int i = 0; i < matA[0].length; i++)
               {
                  sum += matA[row][i] * matB[i][column];
               }

               matC[row][column] = sum;
            }
         }
      }
   }

   public static void matShow(double[][] matA, int start, int size)
   {
      DecimalFormat format = new DecimalFormat("0.00");

      for (int i = 0; i < size; i++)
      {
         for (int j = 0; j < size; j++)
         {
            System.out.print(format.format(matA[start + i][start + j]));

            if (j < size - 1)
            {
               System.out.print("\t");
            }
         }

         System.out.println();
      }

      System.out.println();
   }
}

// @formatter:off (Eclipse formatter tag)

/* Output 1
0.00  0.00  0.13  0.00  0.00  0.99  0.00  0.00  0.00  0.00
0.00  0.56  0.00  0.00  0.48  0.00  0.00  0.00  0.00  0.00
0.00  0.92  0.84  0.05  0.70  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.28  0.78  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.20  0.72
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.85  0.23  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.76  0.00  0.00  0.00  0.00  0.00  0.00

3.67  4.58  1.79  1.45  4.16  2.71  2.67  1.95  1.48  2.95
1.27  3.64  1.35  1.59  1.21  2.43  1.53  1.96  0.46  1.38
2.59  4.09  3.43  3.95  4.03  2.47  3.86  4.67  2.34  0.87
1.68  2.91  1.16  2.08  2.51  2.25  2.56  1.13  2.33  2.38
1.59  2.14  0.90  1.52  4.22  1.68  0.85  3.96  0.86  1.67
2.10  2.50  0.87  3.26  1.41  2.25  1.47  3.05  2.21  1.65
1.58  3.68  2.73  2.55  3.20  3.09  1.10  3.04  0.92  1.31
3.64  3.62  2.35  2.29  3.42  2.31  2.14  2.78  2.83  1.47
2.75  1.62  2.24  2.06  4.28  1.41  2.79  3.20  0.39  4.41
1.38  2.57  2.44  2.05  1.19  1.20  0.75  1.21  2.54  2.04

Size = 1000 / Elapsed time: 3.3466 seconds

 */

/* Output 2
0.00  0.00  0.57  0.00  0.00  0.00  0.00  0.00  0.19  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.35  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.11  0.00
0.00  0.34  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.81  0.00  0.00  0.00  0.00  0.00  0.58
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.31  0.00  0.00  0.00
0.00  0.47  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.62  0.66  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.03
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.28  0.00

3.94  3.45  4.64  2.30  4.89  4.46  3.16  2.71  5.37  4.88
2.77  4.61  2.67  5.54  2.96  5.68  5.38  6.68  5.57  5.28
3.90  4.18  3.20  4.64  3.08  4.64  3.20  2.45  4.44  7.37
4.00  3.46  6.20  3.69  6.08  3.34  3.37  6.54  6.25  4.32
5.89  6.58  2.84  4.35  3.12  6.29  3.37  3.21  3.59  2.98
4.17  2.36  4.89  3.13  5.31  2.07  4.20  4.83  6.49  4.14
5.37  3.58  5.14  5.02  6.97  4.12  4.03  3.81  4.56  6.39
4.13  4.55  2.86  4.38  6.48  4.13  4.71  6.88  5.43  2.55
3.37  4.64  3.26  2.53  6.69  5.06  8.00  7.77  3.78  2.57
3.75  5.25  4.22  5.68  3.89  3.15  4.66  5.22  5.40  5.91

Size = 2000 / Elapsed time: 64.8316 seconds

 */

/* Output 3
0.00  0.13  0.00  0.00  0.00  0.00  0.25  0.00  0.00  0.83
0.00  0.33  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.50  0.00  0.00  0.00  0.00  0.21  0.03  0.00  0.00
0.00  0.16  0.00  0.00  0.10  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.28  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00
0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00  0.00

5.65  11.28 7.41  7.67  6.48  10.08 9.93  9.09  11.28 8.51
12.43 11.54 10.14 6.33  10.58 6.54  10.70 6.70  9.79  9.45
9.71  8.75  6.90  8.08  8.75  9.60  6.16  8.30  11.54 7.87
7.35  9.51  7.28  8.78  9.51  8.02  10.00 6.93  8.65  8.14
9.93  7.91  10.56 7.29  8.17  10.03 9.96  10.28 8.67  9.28
11.96 11.27 10.79 7.63  7.48  10.86 6.19  7.33  11.94 10.72
6.48  10.97 12.45 7.60  9.63  10.46 9.35  10.77 5.47  9.38
7.23  8.37  8.18  8.30  7.83  6.64  5.10  10.43 10.41 5.88
9.61  5.75  7.88  8.78  8.91  8.86  5.62  9.21  8.75  4.66
11.63 5.46  9.05  9.28  8.50  6.45  9.37  6.70  6.44  8.10

Size = 4000 / Elapsed time: 600.5682 seconds

 */

/* Comments
- Big-O estimation : O(N^3)
   - The method needs to add N products for N rows and N columns

- Theta estimation : Θ(N^3)
   - This would be the same since the method needs to visit every element

- What was the smallest M that gave you a non-zero time?
   - Size = 13 / Elapsed time: 0 seconds
   - Size = 14 / Elapsed time: 0.0001 seconds

- What happened when you doubled M, tripled it, quadrupled it, etc? Give several
  M values and their times in a table.
   - ┌────────┬────────┐
     │  Size  │  Time  │
     ├────────┼────────┤
     │   100  │ 0.0037 │
     ├────────┼────────┤
     │   200  │ 0.0135 │
     ├────────┼────────┤
     │   300  │ 0.0386 │
     ├────────┼────────┤
     │   400  │ 0.0926 │
     ├────────┼────────┤
     │   500  │ 0.1753 │
     ├────────┼────────┤
     │   600  │ 0.2965 │
     ├────────┼────────┤
     │   700  │ 0.4758 │
     ├────────┼────────┤
     │   800  │ 0.7172 │
     ├────────┼────────┤
     │   900  │ 1.3105 │
     ├────────┼────────┤
     │  1000  │ 3.3466 │
     └────────┴────────┘

- How large an M can you use before the program refuses to run (exception or
  run-time error due to memory overload) or it takes so long you can't wait for
  the run?
   - Size = 2000 / Elapsed time: 64.8316 seconds
   - Size = 4000 / Elapsed time: 600.5682 seconds
   - Size = 16000 / OutOfMemoryError: Java heap space

- How did the data agree or disagree with your original time complexity
  estimate?
   - When I plotted the data, it matched the y=x^3 function very well

 */
