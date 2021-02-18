
// eBookCompTextNum.java -------------------------------------------------------
import cs_1c.eBookEntry;

public class eBookCompTextNum implements Comparable<Integer>
{
   eBookEntry data;

   public eBookCompTextNum(eBookEntry book)
   {
      data = book;
   }

   public String toString()
   {
      return data.toString();
   }

   public int compareTo(Integer key)
   {
      return data.getETextNum() - key;
   }

   public boolean equals(eBookCompTextNum rhs)
   {
      return data.equals(rhs.data);
   }

   public int hashCode()
   {
      return data.getETextNum();
   }
}
