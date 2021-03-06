
// Assignment 2 - Sparse Matrices
// Part A (required) - Sparse Matrices

// Main.java -------------------------------------------------------------------
public class Main
{
   public static void main(String[] args)
   {
      final int SIZE = 100000;

      SparseMatrix<Double> matrix1 = new SparseMatrix<Double>(SIZE, SIZE, 0.0);

      for (int row = 1; row < 11; row++)
      {
         for (int column = 1; column < 11; column++)
         {
            matrix1.set(row, column, (row - 1) * 10.0 + column);
         }
      }

      System.out.println("set(): left-to-right and top-to-bottom\n");
      matrix1.showSubSquare(0, 12);

      matrix1.clear();

      System.out.println("clear()\n");
      matrix1.showSubSquare(0, 12);

      for (int row = 10; row > 0; row--)
      {
         for (int column = 10; column > 0; column--)
         {
            matrix1.set(row, column, (row - 1) * 10.0 + column);
         }
      }

      System.out.println("set(): right-to-left and bottom-to-top\n");
      matrix1.showSubSquare(0, 12);

      for (int row = 1; row < 11; row++)
      {
         for (int column = 1; column < 11; column++)
         {
            matrix1.set(row, column, 0.0);
         }
      }

      System.out.println("set(): all default values\n");
      matrix1.showSubSquare(0, 12);
   }
}

// @formatter:off (Eclipse formatter tag)

/* Output
set(): left-to-right and top-to-bottom

0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   1.0   2.0   3.0   4.0   5.0   6.0   7.0   8.0   9.0   10.0  0.0
0.0   11.0  12.0  13.0  14.0  15.0  16.0  17.0  18.0  19.0  20.0  0.0
0.0   21.0  22.0  23.0  24.0  25.0  26.0  27.0  28.0  29.0  30.0  0.0
0.0   31.0  32.0  33.0  34.0  35.0  36.0  37.0  38.0  39.0  40.0  0.0
0.0   41.0  42.0  43.0  44.0  45.0  46.0  47.0  48.0  49.0  50.0  0.0
0.0   51.0  52.0  53.0  54.0  55.0  56.0  57.0  58.0  59.0  60.0  0.0
0.0   61.0  62.0  63.0  64.0  65.0  66.0  67.0  68.0  69.0  70.0  0.0
0.0   71.0  72.0  73.0  74.0  75.0  76.0  77.0  78.0  79.0  80.0  0.0
0.0   81.0  82.0  83.0  84.0  85.0  86.0  87.0  88.0  89.0  90.0  0.0
0.0   91.0  92.0  93.0  94.0  95.0  96.0  97.0  98.0  99.0  100.0 0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

clear()

0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

set(): right-to-left and bottom-to-top

0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   1.0   2.0   3.0   4.0   5.0   6.0   7.0   8.0   9.0   10.0  0.0
0.0   11.0  12.0  13.0  14.0  15.0  16.0  17.0  18.0  19.0  20.0  0.0
0.0   21.0  22.0  23.0  24.0  25.0  26.0  27.0  28.0  29.0  30.0  0.0
0.0   31.0  32.0  33.0  34.0  35.0  36.0  37.0  38.0  39.0  40.0  0.0
0.0   41.0  42.0  43.0  44.0  45.0  46.0  47.0  48.0  49.0  50.0  0.0
0.0   51.0  52.0  53.0  54.0  55.0  56.0  57.0  58.0  59.0  60.0  0.0
0.0   61.0  62.0  63.0  64.0  65.0  66.0  67.0  68.0  69.0  70.0  0.0
0.0   71.0  72.0  73.0  74.0  75.0  76.0  77.0  78.0  79.0  80.0  0.0
0.0   81.0  82.0  83.0  84.0  85.0  86.0  87.0  88.0  89.0  90.0  0.0
0.0   91.0  92.0  93.0  94.0  95.0  96.0  97.0  98.0  99.0  100.0 0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0

set(): all default values

0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0
0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0   0.0


 */
