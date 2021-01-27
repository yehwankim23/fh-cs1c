
// SparseMatrixWithMult.java ---------------------------------------------------
import java.util.ListIterator;

public class SparseMatrixWithMult extends SparseMatrix<Double>
{
   public SparseMatrixWithMult(int numRows, int numCols, Double defaultVal)
   {
      super(numRows, numCols, defaultVal);
   }

   public boolean matMult(SparseMatrixWithMult matA, SparseMatrixWithMult matB)
   {
      if ((matA.colSize != matB.rowSize) || matA.rowSize < 1 || matA.colSize < 1
            || matB.rowSize < 1 || matB.colSize < 1)
      {
         return false;
      }

      clear();
      rowSize = matA.rowSize;
      colSize = matB.colSize;

      for (int row = 0; row < rowSize; row++)
      {
         for (ListIterator<MatrixNode> iter = matA.rows.get(row).listIterator();
               iter.hasNext();)
         {
            MatrixNode matANode = iter.next();

            for (int column = 0; column < colSize; column++)
            {
               double matBData = matB.get(matANode.col, column);

               if (matBData != matB.defaultValue)
               {
                  set(row, column,
                        get(row, column) + (matANode.data * matBData));
               }
            }
         }
      }

      return true;
   }
}
