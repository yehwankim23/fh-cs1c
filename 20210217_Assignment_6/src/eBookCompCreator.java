
// eBookCompCreator.java -------------------------------------------------------
import cs_1c.eBookEntry;

public class eBookCompCreator implements Comparable<String>
{
   eBookEntry data;

   public eBookCompCreator(eBookEntry book)
   {
      data = book;
   }

   public String toString()
   {
      return data.toString();
   }

   public int compareTo(String key)
   {
      return data.getCreator().compareTo(key);
   }

   public boolean equals(eBookCompCreator rhs)
   {
      return data.equals(rhs.data);
   }

   public int hashCode()
   {
      return data.getCreator().hashCode();
   }
}
