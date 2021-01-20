package cs_1c;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyLinkedList<E> implements Cloneable, Iterable<E>, Collection<E>,
      Queue<E>, Deque<E>, List<E>
{
   private class Node
   {
      Node prev, next;
      E data;

      Node(E x, Node prv, Node nxt)
      {
         prev = prv;
         next = nxt;
         data = x;
      }

      void insertBefore(E x)
      {
         Node pNew = new Node(x, prev, this);
         prev.next = pNew;
         prev = pNew;
      }

      E remove()
      {
         prev.next = next;
         next.prev = prev;
         return this.data;
      }
   }

   private class Pair
   {
      Node node;
      int index;

      Pair(Node node, int index)
      {
         this.node = node;
         this.index = index;
      }
   }

   private int mSize;
   private Node mHead, mTail;

   private static final int NOT_FOUND = -1;

   private int modCount = 0;

   public MyLinkedList()
   {
      clear();
   }

   public MyLinkedList(Collection<? extends E> rhs)
   {
      clear();
      addAll(rhs);
   }

   public int size()
   {
      return mSize;
   }

   public boolean isEmpty()
   {
      return mSize == 0;
   }

   public void clear()
   {
      mSize = 0;
      mHead = new Node(null, null, null);
      mTail = new Node(null, null, null);
      mHead.next = mTail;
      mTail.prev = mHead;
      modCount++;
   }

   private Node getNode(int index)
   {
      Node p;
      int k;
      if (index < mSize / 2)
         for (k = 0, p = mHead.next; k < index; p = p.next, k++)
            ;
      else
         for (k = mSize, p = mTail; k > index; p = p.prev, k--)
            ;
      return p;
   }

   private Pair getFirstOccurrence(Object o)
   {
      Node p;
      int k;
      if (o != null)
      {
         for (k = 0, p = mHead.next; k < mSize; p = p.next, k++)
            if (o.equals(p.data))
               return new Pair(p, k);
      }
      else
      {
         for (k = 0, p = mHead.next; k < mSize; p = p.next, k++)
            if (p.data == null)
               return new Pair(p, k);
      }
      return new Pair(null, NOT_FOUND);
   }

   private Pair getLastOccurrence(Object o)
   {
      Node p;
      int k;
      if (o != null)
      {
         for (k = mSize - 1, p = mTail.prev; k > 0; p = p.prev, k--)
            if (o.equals(p.data))
               return new Pair(p, k);
      }
      else
      {
         for (k = mSize - 1, p = mTail.prev; k > 0; p = p.prev, k--)
            if (p.data == null)
               return new Pair(p, k);
      }
      return new Pair(null, NOT_FOUND);
   }

   public E get(int index)
   {
      if (index < 0 || index >= mSize)
         throw new IndexOutOfBoundsException();
      return getNode(index).data;
   }

   public E getFirst()
   {
      if (mSize == 0)
         throw new NoSuchElementException();
      return mHead.next.data;
   }

   public E getLast()
   {
      if (mSize == 0)
         throw new NoSuchElementException();
      return mTail.prev.data;
   }

   public E peek()
   {
      if (mSize == 0)
         return null;
      return mHead.next.data;
   }

   public E peekFirst()
   {
      if (mSize == 0)
         return null;
      return mHead.next.data;
   }

   public E peekLast()
   {
      if (mSize == 0)
         return null;
      return mTail.prev.data;
   }

   public E poll()
   {
      if (mSize == 0)
         return null;
      E retVal = mHead.next.data;
      mHead.next.remove();
      modCount++;
      mSize--;
      return retVal;
   }

   public E pollFirst()
   {
      if (mSize == 0)
         return null;
      E retVal = mHead.next.data;
      mHead.next.remove();
      modCount++;
      mSize--;
      return retVal;
   }

   public E pollLast()
   {
      if (mSize == 0)
         return null;
      E retVal = mTail.prev.data;
      mTail.prev.remove();
      modCount++;
      mSize--;
      return retVal;
   }

   public boolean contains(Object o)
   {
      return (indexOf(o) != NOT_FOUND);
   }

   public E set(int index, E x)
   {
      E retVal;
      Node p;
      if (index < 0 || index >= mSize)
         throw new IndexOutOfBoundsException();
      p = getNode(index);
      retVal = p.data;
      p.data = x;
      return retVal;
   }

   public boolean add(E x)
   {
      mTail.insertBefore(x);
      mSize++;
      modCount++;
      return true;
   }

   public void add(int index, E x)
   {
      if (index < 0 || index > mSize)
         throw new IndexOutOfBoundsException();
      getNode(index).insertBefore(x);
      modCount++;
      mSize++;
   }

   public void addFirst(E x)
   {
      mHead.next.insertBefore(x);
      mSize++;
      modCount++;
   }

   public void addLast(E x)
   {
      mTail.insertBefore(x);
      mSize++;
      modCount++;
   }

   public boolean offer(E x)
   {
      mTail.insertBefore(x);
      mSize++;
      modCount++;
      return true;
   }

   public boolean offerFirst(E x)
   {
      mHead.next.insertBefore(x);
      mSize++;
      modCount++;
      return true;
   }

   public boolean offerLast(E x)
   {
      mTail.insertBefore(x);
      mSize++;
      modCount++;
      return true;
   }

   public E pop()
   {
      if (mSize == 0)
         throw new NoSuchElementException();
      mSize--;
      modCount++;
      return mHead.next.remove();
   }

   public void push(E x)
   {
      mHead.next.insertBefore(x);
      mSize++;
      modCount++;
   }

   public E remove()
   {
      if (mSize == 0)
         throw new NoSuchElementException();
      mSize--;
      modCount++;
      return mHead.next.remove();
   }

   public E removeFirst()
   {
      if (mSize == 0)
         throw new NoSuchElementException();
      mSize--;
      modCount++;
      return mHead.next.remove();
   }

   public E removeLast()
   {
      if (mSize == 0)
         throw new NoSuchElementException();
      mSize--;
      modCount++;
      return mTail.prev.remove();
   }

   public E remove(int index)
   {
      Node removedNode;
      if (index < 0 || index >= mSize)
         throw new IndexOutOfBoundsException();
      removedNode = getNode(index);
      mSize--;
      modCount++;
      return removedNode.remove();
   }

   public boolean remove(Object o)
   {
      Pair result = getFirstOccurrence(o);
      if (result.index == NOT_FOUND)
         return false;
      result.node.remove();
      mSize--;
      modCount++;
      return true;
   }

   public boolean removeFirstOccurrence(Object o)
   {
      return remove(o);
   }

   public boolean removeLastOccurrence(Object o)
   {
      Pair result = getLastOccurrence(o);
      if (result.index == NOT_FOUND)
         return false;
      result.node.remove();
      mSize--;
      modCount++;
      return true;
   }

   protected void removeRange(int fromK, int toK)
   {
      Node p;
      int k;
      if (fromK < 0 || fromK >= mSize || toK > size() || toK < fromK)
         throw new IndexOutOfBoundsException();
      for (k = fromK, p = getNode(fromK); k < toK; k++, p = p.next)
         p.remove();
      mSize -= (toK - fromK);
      modCount++;
   }

   public boolean addAll(Collection<? extends E> rhs)
   {
      if (rhs.size() == 0)
         return false;
      for (E x : rhs)
         add(x);
      return true;
   }

   public boolean addAll(int index, Collection<? extends E> rhs)
   {
      if (rhs.size() == 0)
         return false;
      int k = 0;
      for (E x : rhs)
         add(k++, x);
      return true;
   }

   public boolean containsAll(Collection<?> c)
   {
      for (Object o : c)
         if (!contains(o))
            return false;
      return true;
   }

   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
      MyLinkedList<E> newObject = (MyLinkedList<E>) super.clone();
      newObject.clear();
      newObject.addAll(this);
      return newObject;
   }

   public int indexOf(Object o)
   {
      return getFirstOccurrence(o).index;
   }

   public int lastIndexOf(Object o)
   {
      return getLastOccurrence(o).index;
   }

   @SuppressWarnings("unchecked")
   public boolean equals(Object o)
   {
      Node p1, p2;
      MyLinkedList<E> that;
      E thisData, thatData;
      if (!(o instanceof MyLinkedList<?>))
         return false;
      that = (MyLinkedList<E>) o;
      if (that.size() != mSize)
         return false;
      for (p1 = mHead.next, p2 = that.mHead.next; p1 != mTail;
            p1 = p1.next, p2 = p2.next)
      {
         thisData = p1.data;
         thatData = p2.data;
         if (thisData == null || thatData == null)
         {
            if (thisData != null || thatData != null)
               return false;
         }
         else
         {
            if (!thisData.equals(thatData))
               return false;
         }
      }
      return true;
   }

   public Object[] toArray()
   {
      int k;
      Node p;
      Object[] retArray = new Object[mSize];
      for (k = 0, p = mHead.next; k < mSize; k++, p = p.next)
         retArray[k] = p.data;
      return retArray;
   }

   public <T> T[] toArray(T[] userArray)
   {
      int k;
      Node p;
      Object[] retArray;
      if (mSize > userArray.length)
         retArray = new Object[mSize];
      else
         retArray = userArray;
      for (k = 0, p = mHead.next; k < mSize; k++, p = p.next)
         retArray[k] = p.data;
      if (mSize < userArray.length)
         retArray[mSize] = null;
      return (T[]) userArray;
   }

   public E element()
   {
      if (mSize == 0)
         throw new NoSuchElementException();
      return mHead.next.data;
   }

   public boolean retainAll(Collection<?> c)
   {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection<?> c)
   {
      throw new UnsupportedOperationException();
   }

   public List<E> subList(int fromIndex, int toIndex)
   {
      throw new UnsupportedOperationException();
   }

   public Iterator<E> iterator()
   {
      return new MyIterator();
   }

   public ListIterator<E> listIterator()
   {
      return new MyListIterator();
   }

   public ListIterator<E> listIterator(int index)
   {
      if (index < 0 || index >= mSize)
         throw new ArrayIndexOutOfBoundsException();
      return new MyListIterator(index);
   }

   public Iterator<E> descendingIterator()
   {
      return new MyDescendingIterator();
   }

   private class MyIterator implements Iterator<E>
   {
      protected static final int NOT_VALID = -1;
      protected static final int NEXT = 10;
      protected static final int PREVIOUS = 11;

      protected Node mCurrentNode;
      protected int mCurrentIndex;

      protected int mIterModCount = modCount;

      protected Node mLastNodeReturned = null;
      protected int mLastIteration = NOT_VALID;

      public boolean hasNext()
      {
         return mCurrentIndex < mSize;
      }

      public E next()
      {
         if (mIterModCount != modCount)
            throw new ConcurrentModificationException();
         if (!hasNext())
            throw new NoSuchElementException();
         mLastNodeReturned = mCurrentNode;
         mCurrentNode = mCurrentNode.next;
         mCurrentIndex++;
         mLastIteration = NEXT;
         return mLastNodeReturned.data;
      }

      public void remove()
      {
         if (mIterModCount != modCount)
            throw new ConcurrentModificationException();
         if (mLastNodeReturned == null)
            throw new IllegalStateException();
         mLastNodeReturned.remove();
         if (mLastIteration == NEXT)
            mCurrentIndex--;
         mSize--;
         mLastNodeReturned = null;
      }

      MyIterator()
      {
         mCurrentNode = mHead.next;
         mCurrentIndex = 0;
      }
   }

   private class MyListIterator extends MyIterator implements ListIterator<E>
   {
      MyListIterator()
      {
         super();
      }

      MyListIterator(int index)
      {
         super();
         if (index < 0 || index >= mSize)
            return;
         mCurrentNode = getNode(index);
         mCurrentIndex = index;
      }

      public boolean hasPrevious()
      {
         return mCurrentIndex > 0;
      }

      public E previous()
      {
         if (mIterModCount != modCount)
            throw new ConcurrentModificationException();
         if (!hasPrevious())
            throw new NoSuchElementException();
         mCurrentNode = mCurrentNode.prev;
         mLastNodeReturned = mCurrentNode;
         mCurrentIndex--;
         mLastIteration = PREVIOUS;
         return mLastNodeReturned.data;
      }

      public int nextIndex()
      {
         return mCurrentIndex;
      }

      public int previousIndex()
      {
         return mCurrentIndex - 1;
      }

      public void set(E x)
      {
         if (mIterModCount != modCount)
            throw new ConcurrentModificationException();
         if (mLastNodeReturned == null)
            throw new IllegalStateException();
         mLastNodeReturned.data = x;
      }

      public void add(E x)
      {
         if (mIterModCount != modCount)
            throw new ConcurrentModificationException();
         mCurrentNode.insertBefore(x);
         mCurrentIndex++;
         mSize++;
         mLastNodeReturned = null;
      }
   }

   private class MyDescendingIterator extends MyListIterator
   {
      public E next()
      {
         return previous();
      }

      MyDescendingIterator()
      {
         mCurrentNode = mTail;
         mCurrentIndex = mSize;
      }
   }
}
