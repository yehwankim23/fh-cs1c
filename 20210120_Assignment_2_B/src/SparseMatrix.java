
// SparseMatrix.java -----------------------------------------------------------
import cs_1c.MyArrayList;
import cs_1c.MyLinkedList;

public class SparseMatrix<E> implements Cloneable
{
   private static final int MIN_SIZE = 1;

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

   public E get(int r, int c)
   {
      if (r < 0 || r >= rowSize || c < 0 || c >= colSize)
      {
         throw new IndexOutOfBoundsException();
      }

      MyLinkedList<MatrixNode> row = rows.get(r);
      int size = row.size();

      for (int i = 0; i < size; i++)
      {
         MatrixNode node = row.get(i);
         int column = node.col;

         if (column == c)
         {
            return node.data;
         }
         else if (column < c)
         {
            break;
         }
      }

      return defaultValue;
   }

   public boolean set(int r, int c, E x)
   {
      if (r < 0 || r >= rowSize || c < 0 || c >= colSize)
      {
         return false;
      }

      MyLinkedList<MatrixNode> row = rows.get(r);
      int size = row.size();
      boolean isDefault = x.equals(defaultValue);

      if (size == 0)
      {
         if (!isDefault)
         {
            row.add(new MatrixNode(c, x));
         }
      }
      else
      {
         MatrixNode node = null;
         int index = 0;
         boolean exists = false;

         while (index < size)
         {
            node = row.get(index);
            int column = node.col;

            if (column == c)
            {
               exists = true;
               break;
            }
            else if (column < c)
            {
               if (index > 0)
               {
                  index--;
               }

               break;
            }

            index++;
         }

         if (exists)
         {
            if (isDefault)
            {
               row.remove(index);
            }
            else
            {
               node.data = x;
            }
         }
         else
         {
            if (!isDefault)
            {
               row.add(index, new MatrixNode(c, x));
            }
         }
      }

      return true;
   }

   public void clear()
   {
      for (int i = 0; i < rowSize; i++)
      {
         rows.get(i).clear();
      }
   }

   public void showSubSquare(int start, int size)
   {
      for (int i = 0; i < size; i++)
      {
         for (int j = 0; j < size; j++)
         {
            System.out.print(this.get(start + i, start + j));

            if (j < size - 1)
            {
               System.out.print("\t");
            }
         }

         System.out.println();
      }
   }

   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
      SparseMatrix<E> newObject = (SparseMatrix<E>) super.clone();

      newObject.rows = (MyArrayList<
            MyLinkedList<SparseMatrix<E>.MatrixNode>>) rows.clone();

      for (int i = 0; i < rowSize; i++)
      {
         newObject.rows.set(i,
               (MyLinkedList<SparseMatrix<E>.MatrixNode>) rows.get(i).clone());
      }

      return (Object) newObject;
   }

   private void allocateEmptyMatrix()
   {
      rows = new MyArrayList<MyLinkedList<MatrixNode>>(rowSize);

      for (int i = 0; i < rowSize; i++)
      {
         rows.add(new MyLinkedList<MatrixNode>());
      }
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
