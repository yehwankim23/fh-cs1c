
// Assignment 3 - Timing Matrix Multiplication
// Part B - A Sparse Matrix Implementation for Large Matrices

// Main.java -------------------------------------------------------------------
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

      SparseMatrixWithMult mat1
            = new SparseMatrixWithMult(MAT_SIZE, MAT_SIZE, 0.0);
      mat1.set(0, 0, 1.0);
      mat1.set(0, 1, 2.0);
      mat1.set(0, 2, 3.0);
      mat1.set(0, 3, 4.0);
      mat1.set(0, 4, 5.0);
      mat1.set(1, 0, -1.0);
      mat1.set(1, 1, -2.0);
      mat1.set(1, 2, -3.0);
      mat1.set(1, 3, -4.0);
      mat1.set(1, 4, -5.0);
      mat1.set(2, 0, 1.0);
      mat1.set(2, 1, 3.0);
      mat1.set(2, 2, 1.0);
      mat1.set(2, 3, 3.0);
      mat1.set(2, 4, 1.0);
      mat1.set(3, 1, 1.0);
      mat1.set(3, 3, 1.0);
      mat1.set(4, 0, -1.0);
      mat1.set(4, 1, -1.0);
      mat1.set(4, 2, -1.0);
      mat1.set(4, 3, -1.0);
      mat1.set(4, 4, -1.0);

      SparseMatrixWithMult mat2
            = new SparseMatrixWithMult(MAT_SIZE, MAT_SIZE, 0.0);
      mat2.set(0, 0, 2.0);
      mat2.set(0, 1, 1.0);
      mat2.set(0, 2, 5.0);
      mat2.set(0, 4, 2.0);
      mat2.set(1, 0, 1.0);
      mat2.set(1, 1, 4.0);
      mat2.set(1, 2, 3.0);
      mat2.set(1, 3, 2.0);
      mat2.set(1, 4, 7.0);
      mat2.set(2, 0, 4.0);
      mat2.set(2, 1, 4.0);
      mat2.set(2, 2, 4.0);
      mat2.set(2, 3, 4.0);
      mat2.set(2, 4, 4.0);
      mat2.set(3, 0, 7.0);
      mat2.set(3, 1, 1.0);
      mat2.set(3, 2, -1.0);
      mat2.set(3, 3, -1.0);
      mat2.set(3, 4, -1.0);
      mat2.set(4, 2, 8.0);
      mat2.set(4, 3, -1.0);
      mat2.set(4, 4, -6.0);

      SparseMatrixWithMult matAns
            = new SparseMatrixWithMult(MAT_SIZE, MAT_SIZE, 0.0);

      mat1.showSubSquare(0, MAT_SIZE);
      mat2.showSubSquare(0, MAT_SIZE);

      long startTime = System.nanoTime();

      matAns.matMult(mat1, mat2);

      long stopTime = System.nanoTime();

      matAns.showSubSquare(0, MAT_SIZE);

      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      System.out.println("Size = " + MAT_SIZE + " / Elapsed time: "
            + tidy.format((stopTime - startTime) / 1e9) + " seconds");
   }
    */
   // @formatter:on (Eclipse formatter tag)

   public static void main(String[] args)
   {
      final int MAT_SIZE = 500;
      final double PERCENT = 10;

      // allocate matrices
      SparseMatrixWithMult mat
            = new SparseMatrixWithMult(MAT_SIZE, MAT_SIZE, 0.0);
      SparseMatrixWithMult matAns
            = new SparseMatrixWithMult(MAT_SIZE, MAT_SIZE, 0.0);

      // generate PERCENT% sparse matrix
      Random random = new Random();

      for (int i = 0; i < MAT_SIZE / 100. * PERCENT * MAT_SIZE; i++)
      {
         mat.set(random.nextInt(MAT_SIZE), random.nextInt(MAT_SIZE),
               random.nextDouble());
      }

      mat.showSubSquare(MAT_SIZE - 10, 10);

      long startTime = System.nanoTime();

      matAns.matMult(mat, mat);

      long stopTime = System.nanoTime();

      matAns.showSubSquare(MAT_SIZE - 10, 10);

      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      System.out.println("Size = " + MAT_SIZE + " / Percent = " + PERCENT
            + " % / Elapsed time: " + tidy.format((stopTime - startTime) / 1e9)
            + " seconds");
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

Size = 5 / Elapsed time: 0.0002 seconds

 */

/* Output 1
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.88
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.3   0.0
0.0   0.11  0.0   0.0   0.0   0.12  0.0   0.0   0.0   0.0
0.0   0.0   0.48  0.0   0.0   0.51  0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.06  0.0   0.0   0.0   0.06  0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

Size = 800 / Percent = 1.0 % / Elapsed time: 0.1297 seconds

 */

/* Output 2
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.36
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.07  0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.08  0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.6   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

0.48  0.0   0.0   0.0   0.0   0.0   0.0   0.22  0.0   0.0
0.21  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.65  0.0
0.0   0.17  0.0   0.0   0.26  0.0   0.0   0.0   0.0   0.35
0.47  0.0   0.0   0.18  0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.6   0.0   0.0   0.11  0.0   0.0   0.0
0.3   0.11  0.0   0.0   0.14  0.0   0.0   0.0   0.0   1.16
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.46  0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.14  0.0

Size = 1600 / Percent = 1.0 % / Elapsed time: 1.2439 seconds

 */

/* Output 3
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.59  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

0.0   0.0   0.05  0.0   0.0   0.39  0.0   0.0   0.0   0.0
0.06  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.12  0.0   0.0   0.28  0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.23  0.0   0.0   0.0
0.0   0.02  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.14  0.0   0.17  0.0   0.26  0.34  0.0   0.0   0.0   0.0
0.0   0.0   0.07  0.0   0.0   0.0   0.0   0.0   0.65  0.0
0.22  0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.14  0.0   0.0   0.0   0.3   0.0   0.0   0.0
0.51  0.28  0.0   0.0   0.17  0.0   0.0   0.0   0.0   0.01

Size = 3200 / Percent = 1.0 % / Elapsed time: 23.8422 seconds

 */

/* Comments
- Are the times longer or shorter than for dynamic matrices?
   - For 10%, times were significantly longer (see table below)

- What was the smallest M that gave you a non-zero time?
   - Size = 7 / Percent = 10.0 % / Elapsed time: 0 seconds
   - Size = 8 / Percent = 10.0 % / Elapsed time: 0.0001 seconds

- What happened when you doubled M, tripled it, quadrupled it, etc? Give
  several M values and their times in a table.
   - ┌──────────┬──────────┬──────────┐
     │   Size   │ Dynamic  │  Sparse  │
     ├──────────┼──────────┼──────────┤
     │    100   │  0.0037  │   0.0123 │
     ├──────────┼──────────┼──────────┤
     │    200   │  0.0135  │   0.0590 │
     ├──────────┼──────────┼──────────┤
     │    300   │  0.0386  │   0.2532 │
     ├──────────┼──────────┼──────────┤
     │    400   │  0.0926  │   0.9225 │
     ├──────────┼──────────┼──────────┤
     │    500   │  0.1753  │   2.2821 │
     ├──────────┼──────────┼──────────┤
     │    600   │  0.2965  │   4.7786 │
     ├──────────┼──────────┼──────────┤
     │    700   │  0.4758  │   9.0199 │
     ├──────────┼──────────┼──────────┤
     │    800   │  0.7172  │  15.3063 │
     ├──────────┼──────────┼──────────┤
     │    900   │  1.3105  │  24.3323 │
     ├──────────┼──────────┼──────────┤
     │   1000   │  3.3466  │  36.8877 │
     └──────────┴──────────┴──────────┘
     (10% sparse matrix multiplication)

- What was the largest M you could use here, and was the reason the same or
  different than for dynamic arrays?
   - Size = 1200 / Percent = 10.0 % / Elapsed time: 77.3394 seconds
   - Size = 2000 / Percent = 10.0 % / Elapsed time: 680.6111 seconds
   - Size = 32000 / OutOfMemoryError: Java heap space while generating matrix

- Try different sparseness values: 1% 5% 10% 0.2%, and report how the growth
  rates behave in each case.
   - ┌────────┬────────┬────────┬────────┬────────┐
     │  Size  │  0.2%  │   1%   │   5%   │  10%   │
     ├────────┼────────┼────────┼────────┼────────┤
     │   100  │ 0.0008 │ 0.0016 │ 0.0070 │ 0.0125 │
     ├────────┼────────┼────────┼────────┼────────┤
     │   200  │ 0.0019 │ 0.0070 │ 0.0211 │ 0.0593 │
     ├────────┼────────┼────────┼────────┼────────┤
     │   300  │ 0.0046 │ 0.0136 │ 0.0853 │ 0.2522 │
     ├────────┼────────┼────────┼────────┼────────┤
     │   400  │ 0.0077 │ 0.0275 │ 0.1961 │ 0.9323 │
     ├────────┼────────┼────────┼────────┼────────┤
     │   500  │ 0.0111 │ 0.0509 │ 0.4775 │ 2.2939 │
     └────────┴────────┴────────┴────────┴────────┘

 */
