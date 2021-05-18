package cs_1c;

public class Pair<E, F>
{
   public E first;
   public F second;

   public Pair(E x, F y)
   {
      first = x;
      second = y;
   }

   @SuppressWarnings("unchecked")
   public boolean equals(Object rhs)
   {
      Pair<E, F> other;
      other = (Pair<E, F>) rhs;
      return first.equals(other.first);
   }

   public int hashCode()
   {
      return first.hashCode();
   }
}
