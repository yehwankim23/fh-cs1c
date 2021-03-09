package cs_1c;

public class MySort
{
   protected static int QS_RECURSION_LIMIT = 15;

   protected static <E extends Comparable<? super E>> E median3(E[] a, int left,
         int right)
   {
      int center;
      E tmp;
      center = (left + right) / 2;
      if (a[center].compareTo(a[left]) < 0)
      {
         tmp = a[center];
         a[center] = a[left];
         a[left] = tmp;
      }
      if (a[right].compareTo(a[left]) < 0)
      {
         tmp = a[right];
         a[right] = a[left];
         a[left] = tmp;
      }
      if (a[right].compareTo(a[center]) < 0)
      {
         tmp = a[right];
         a[right] = a[center];
         a[center] = tmp;
      }
      tmp = a[center];
      a[center] = a[right - 1];
      a[right - 1] = tmp;
      return a[right - 1];
   }

   public static boolean setRecursionLimit(int newLim)
   {
      if (newLim < 2 || newLim > 1000)
         return false;
      QS_RECURSION_LIMIT = newLim;
      return true;
   }

   protected static <E extends Comparable<? super E>> void quickSort(E[] a,
         int left, int right)
   {
      E pivot, tmp;
      int i, j;
      if (left + QS_RECURSION_LIMIT <= right)
      {
         pivot = median3(a, left, right);
         for (i = left, j = right - 1;;)
         {
            while (a[++i].compareTo(pivot) < 0)
               ;
            while (pivot.compareTo(a[--j]) < 0)
               ;
            if (i < j)
            {
               tmp = a[i];
               a[i] = a[j];
               a[j] = tmp;
            }
            else
               break;
         }
         tmp = a[i];
         a[i] = a[right - 1];
         a[right - 1] = tmp;
         quickSort(a, left, i - 1);
         quickSort(a, i + 1, right);
      }
      else
         insertionSort(a, left, right);
   }

   protected static <E extends Comparable<? super E>> void insertionSort(E[] a,
         int start, int stop)
   {
      int k, pos;
      E tmp;
      for (pos = start + 1; pos <= stop; pos++)
      {
         tmp = a[pos];
         for (k = pos; k > 0 && tmp.compareTo(a[k - 1]) < 0; k--)
            a[k] = a[k - 1];
         a[k] = tmp;
      }
   }

   public static <E extends Comparable<? super E>> void quickSort(E[] a)
   {
      quickSort(a, 0, a.length - 1);
   }
}
