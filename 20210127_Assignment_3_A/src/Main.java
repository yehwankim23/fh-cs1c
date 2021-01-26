
// Assignment 3 - Timing Matrix Multiplication
// Part A (required) - A Simple Array Implementation for Large Matrices

// Main.java -------------------------------------------------------------------
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Main
{
   // @formatter:off (Eclipse formatter tag)
   /* Proof of correctness
   public static void main(String[] args)
   {
      final int MAT_SIZE = 5;

      double[][] mat1 =
      {
            { 1, 2, 3, 4, 5 },
            { -1, -2, -3, -4, -5 },
            { 1, 3, 1, 3, 1 },
            { 0, 1, 0, 1, 0 },
            { -1, -1, -1, -1, -1 } };
      double[][] mat2 =
      {
            { 2, 1, 5, 0, 2 },
            { 1, 4, 3, 2, 7 },
            { 4, 4, 4, 4, 4 },
            { 7, 1, -1, -1, -1 },
            { 0, 0, 8, -1, -6 } };
      double[][] matAns = new double[MAT_SIZE][MAT_SIZE];

      matShow(mat1, 0, 5);
      matShow(mat2, 0, 5);

      long startTime = System.nanoTime();

      matMult(mat1, mat2, matAns);

      long stopTime = System.nanoTime();

      matShow(matAns, 0, 5);

      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      System.out.println("Size = " + MAT_SIZE + " / Elapsed time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds");
   }
    */
   // @formatter:on (Eclipse formatter tag)

   public static void main(String[] args)
   {
      final int MAT_SIZE = 1000;

      // allocate matrices
      double[][] mat = new double[MAT_SIZE][MAT_SIZE];
      double[][] matAns = new double[MAT_SIZE][MAT_SIZE];

      // generate 10% sparse matrix
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
      DecimalFormat format = new DecimalFormat("0.0#");

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

/* Proof of correctness
1.0   2.0   3.0   4.0   5.0
-1.0  -2.0  -3.0  -4.0  -5.0
1.0   3.0   1.0   3.0   1.0
0.0   1.0   0.0   1.0   0.0
-1.0  -1.0  -1.0  -1.0  -1.0

2.0   1.0   5.0   0.0   2.0
1.0   4.0   3.0   2.0   7.0
4.0   4.0   4.0   4.0   4.0
7.0   1.0   -1.0  -1.0  -1.0
0.0   0.0   8.0   -1.0  -6.0

44.0  25.0  59.0  7.0   -6.0
-44.0 -25.0 -59.0 -7.0  6.0
30.0  20.0  23.0  6.0   18.0
8.0   5.0   2.0   1.0   6.0
-14.0 -10.0 -19.0 -4.0  -6.0

Size = 5 / Elapsed time: 0 seconds

 */

/* Output 1
0.0   0.0   0.0   0.0   0.0   0.0   0.1   0.0   0.0   0.0
0.82  0.0   0.0   0.0   0.12  0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.67  0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.98  0.0   0.0
0.0   0.0   0.0   0.33  0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.87  0.0   0.72  0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.81  0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

2.13  0.61  2.09  3.3   2.1   2.57  1.06  2.06  1.98  0.81
1.76  0.71  2.72  1.76  1.37  2.25  1.8   1.38  1.51  1.56
3.29  2.38  2.28  2.04  0.78  1.79  3.07  1.56  1.42  1.53
1.06  1.75  2.54  2.29  1.82  4.52  3.81  2.14  3.42  2.38
2.32  2.06  3.96  1.24  1.99  1.31  1.43  3.76  2.45  2.75
2.07  2.96  3.09  1.03  2.15  4.52  0.61  1.31  2.46  1.53
2.37  3.14  2.76  2.09  3.33  1.49  1.69  2.6   3.15  3.35
1.54  1.47  2.46  2.48  0.92  2.06  1.67  3.53  1.44  0.98
2.47  2.58  0.63  3.74  1.56  1.81  1.52  1.73  1.15  3.18
1.41  1.54  1.78  3.47  1.57  3.02  3.16  1.99  1.62  1.44

Size = 1000 / Elapsed time: 3.3466 seconds

 */

/* Output 2
0.0   0.36  0.0   0.0   0.0   0.0   0.0   0.0   0.03  0.0
0.57  0.0   0.0   0.0   0.17  0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.96  0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.04  0.17  0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.18
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.68  0.32  0.0

5.28  6.65  4.55  4.09  4.81  6.91  5.01  5.69  4.53  5.43
7.13  6.1   5.06  4.38  6.19  5.93  5.47  2.49  4.16  4.65
4.01  4.52  4.71  3.59  3.42  2.88  6.41  3.79  2.84  5.85
7.33  4.49  4.54  3.91  5.49  6.62  5.0   6.95  5.5   5.82
5.49  4.53  3.77  4.2   4.82  3.94  6.47  5.86  6.44  7.95
4.27  3.94  6.45  2.66  4.64  5.83  3.31  3.85  4.01  6.1
5.84  5.91  6.36  2.8   2.55  3.87  4.45  5.43  2.67  6.76
5.18  3.19  3.1   4.01  3.94  5.74  3.44  4.17  5.14  2.55
3.77  6.85  3.44  5.01  6.16  3.07  3.63  6.65  3.51  4.71
4.19  5.23  7.87  4.06  4.69  4.74  3.15  5.8   5.01  7.47

Size = 2000 / Elapsed time: 64.8316 seconds

 */

/* Output 3
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.28  0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.6
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.36  0.0   0.0   0.0   0.0   0.0   0.0   0.0

11.89 5.64  9.58  9.0   12.01 8.71  6.61  12.84 9.55  7.4
10.86 7.54  9.2   8.22  11.68 14.73 7.23  10.78 12.37 13.11
9.86  5.06  9.32  10.17 10.23 10.53 7.6   11.48 8.73  7.99
11.96 8.28  9.08  8.85  11.87 7.49  9.28  8.63  10.34 8.84
7.33  6.43  11.63 9.65  9.72  6.82  10.36 8.82  6.83  5.48
9.79  8.0   7.88  7.95  9.57  10.31 9.08  7.97  8.37  10.35
8.66  7.33  10.1  8.45  6.98  9.52  10.3  8.72  6.94  8.63
9.87  6.85  11.88 10.28 7.93  11.31 4.57  10.49 5.52  9.8
9.11  7.65  12.52 7.18  8.78  10.55 8.95  10.31 5.9   7.28
11.15 6.81  8.53  10.34 8.2   10.65 6.33  9.02  11.56 9.54

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
