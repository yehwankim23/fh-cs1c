
// SparseMatrix.java -----------------------------------------------------------
import java.text.DecimalFormat;
import java.util.ListIterator;

import cs_1c.MyArrayList;
import cs_1c.MyLinkedList;

public class SparseMatrix<E> implements Cloneable
{
   public static final int MIN_SIZE = 1;

   protected int rowSize, colSize;
   protected E defaultValue;
   protected MyArrayList<MyLinkedList<MatrixNode>> rows;

   public SparseMatrix(int numRows, int numCols, E defaultVal)
   {
      if (numRows < MIN_SIZE || numCols < MIN_SIZE || defaultVal == null)
      {
         throw new IllegalArgumentException();
      }

      rowSize = numRows;
      colSize = numCols;
      defaultValue = defaultVal;

      allocateEmptyMatrix();
   }

   public E get(int row, int col)
   {
      if (!isValid(row, col))
      {
         throw new IndexOutOfBoundsException();
      }

      for (ListIterator<MatrixNode> iter = rows.get(row).listIterator();
            iter.hasNext();)
      {
         if (iter.next().col == col)
         {
            return iter.previous().data;
         }
      }

      return defaultValue;
   }

   public boolean set(int row, int col, E x)
   {
      if (!isValid(row, col))
      {
         return false;
      }

      for (ListIterator<MatrixNode> iter = rows.get(row).listIterator();
            iter.hasNext();)
      {
         if (iter.next().col == col)
         {
            if (x.equals(defaultValue))
            {
               iter.remove();
            }
            else
            {
               iter.previous().data = x;
            }

            return true;
         }
      }

      if (!x.equals(defaultValue))
      {
         rows.get(row).add(new MatrixNode(col, x));
      }

      return true;
   }

   protected void allocateEmptyMatrix()
   {
      rows = new MyArrayList<MyLinkedList<MatrixNode>>();

      for (int row = 0; row < rowSize; row++)
      {
         rows.add(new MyLinkedList<MatrixNode>());
      }
   }

   protected boolean isValid(int row, int col)
   {
      if (row < 0 || row >= rowSize || col < 0 || col >= colSize)
      {
         return false;
      }

      return true;
   }

   public void clear()
   {
      for (int row = 0; row < rowSize; row++)
      {
         rows.get(row).clear();
      }
   }

   public void showSubSquare(int start, int size)
   {
      if (start < 0 || size < 0 || start + size > rowSize
            || start + size > colSize)
      {
         return;
      }

      DecimalFormat format = new DecimalFormat("0.0#");

      for (int row = start; row < start + size; row++)
      {
         for (int col = start; col < start + size; col++)
         {
            System.out.print(format.format(get(row, col)));

            if (col < start + size - 1)
            {
               System.out.print("\t");
            }
         }

         System.out.println();
      }

      System.out.println();
   }

   protected class MatrixNode implements Cloneable
   {
      public int col;
      public E data;

      MatrixNode()
      {
         col = 0;
         data = null;
      }

      MatrixNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }

      @SuppressWarnings("unchecked")
      public Object clone() throws CloneNotSupportedException
      {
         MatrixNode newObject = (MatrixNode) super.clone();

         return (Object) newObject;
      }
   }
}
