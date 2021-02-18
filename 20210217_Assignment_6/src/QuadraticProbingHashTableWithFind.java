
// QuadraticProbingHashTableWithFind.java --------------------------------------
import java.util.NoSuchElementException;

import cs_1c.QuadraticProbingHashTable;
import cs_1c.HashEntry;

public class QuadraticProbingHashTableWithFind<KeyType,
      E extends Comparable<KeyType>> extends QuadraticProbingHashTable<E>
{
   public E find(KeyType key)
   {
      HashEntry<E> entry = mArray[findPosKey(key)];

      if (entry.state != ACTIVE)
      {
         throw new NoSuchElementException();
      }

      return entry.data;
   }

   protected int myHashKey(KeyType key)
   {
      int hashVal = key.hashCode() % mTableSize;

      if (hashVal < 0)
      {
         hashVal += mTableSize;
      }

      return hashVal;
   }

   protected int findPosKey(KeyType key)
   {
      int index = myHashKey(key);

      for (int kthOddNum = 1; mArray[index].state != EMPTY
            && mArray[index].data.compareTo(key) != 0; kthOddNum += 2)
      {
         index += kthOddNum;

         if (index >= mTableSize)
         {
            index -= mTableSize;
         }
      }

      return index;
   }
}
