
// LazyBinarySearchTree.java ---------------------------------------------------
import java.util.NoSuchElementException;

import cs_1c.Traverser;

public class LazyBinarySearchTree<E extends Comparable<? super E>>
      implements Cloneable
{
   protected int mSize, mSizeHard;
   protected LazyBinarySearchTreeNode<E> mRoot;

   public LazyBinarySearchTree()
   {
      clear();
   }

   public void clear()
   {
      mSize = 0;
      mSizeHard = 0;
      mRoot = null;
   }

   public boolean empty()
   {
      return mSize == 0;
   }

   public int size()
   {
      return mSize;
   }

   public int sizeHard()
   {
      return mSizeHard;
   }

   public E findMin()
   {
      if (mRoot == null || mSize == 0)
      {
         throw new NoSuchElementException();
      }

      return findMin(mRoot).data;
   }

   public E findMax()
   {
      if (mRoot == null || mSize == 0)
      {
         throw new NoSuchElementException();
      }

      return findMax(mRoot).data;
   }

   public E find(E x)
   {
      LazyBinarySearchTreeNode<E> resultNode = find(mRoot, x);

      if (resultNode == null)
      {
         throw new NoSuchElementException();
      }

      return resultNode.data;
   }

   public boolean contains(E x)
   {
      return find(mRoot, x) != null;
   }

   public boolean insert(E x)
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, x);

      return mSize != oldSize;
   }

   public boolean remove(E x)
   {
      int oldSize = mSize;
      remove(mRoot, x);

      return mSize != oldSize;
   }

   public <F extends Traverser<? super E>> void traverse(F function)
   {
      traverse(function, mRoot);
   }

   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
      LazyBinarySearchTree<E> newObject
            = (LazyBinarySearchTree<E>) super.clone();
      newObject.clear();
      newObject.mSize = mSize;
      newObject.mSizeHard = mSizeHard;
      newObject.mRoot = cloneSubtree(mRoot);

      return newObject;
   }

   public boolean collectGarbage()
   {
      int oldSize = mSizeHard;
      mRoot = collectGarbage(mRoot);

      return mSizeHard != oldSize;
   }

   protected LazyBinarySearchTreeNode<E>
         findMin(LazyBinarySearchTreeNode<E> root)
   {
      if (root == null)
      {
         return null;
      }
      else if (root.leftChild == null)
      {
         return root.deleted ? null : root;
      }

      LazyBinarySearchTreeNode<E> leftChild = findMin(root.leftChild);

      return leftChild == null ? root.deleted ? null : root : leftChild;
   }

   protected LazyBinarySearchTreeNode<E>
         findMax(LazyBinarySearchTreeNode<E> root)
   {
      if (root == null)
      {
         return null;
      }
      else if (root.rightChild == null)
      {
         return root.deleted ? null : root;
      }

      LazyBinarySearchTreeNode<E> rightChild = findMax(root.rightChild);

      return rightChild == null ? root.deleted ? null : root : rightChild;
   }

   protected LazyBinarySearchTreeNode<E> find(LazyBinarySearchTreeNode<E> root,
         E x)
   {
      if (root == null)
      {
         return null;
      }

      int compareResult = x.compareTo(root.data);

      if (compareResult < 0)
      {
         return find(root.leftChild, x);
      }
      else if (compareResult > 0)
      {
         return find(root.rightChild, x);
      }

      return root.deleted ? null : root;
   }

   protected LazyBinarySearchTreeNode<E>
         insert(LazyBinarySearchTreeNode<E> root, E x)
   {
      if (root == null)
      {
         mSize++;
         mSizeHard++;

         return new LazyBinarySearchTreeNode<E>(x, null, null);
      }

      int compareResult = x.compareTo(root.data);

      if (compareResult < 0)
      {
         root.leftChild = insert(root.leftChild, x);
      }
      else if (compareResult > 0)
      {
         root.rightChild = insert(root.rightChild, x);
      }
      else if (root.deleted)
      {
         root.deleted = false;
         mSize++;
      }

      return root;
   }

   protected void remove(LazyBinarySearchTreeNode<E> root, E x)
   {
      if (root == null)
      {
         return;
      }

      int compareResult = x.compareTo(root.data);

      if (compareResult < 0)
      {
         remove(root.leftChild, x);
      }
      else if (compareResult > 0)
      {
         remove(root.rightChild, x);
      }
      else if (!root.deleted)
      {
         root.deleted = true;
         mSize--;
      }
   }

   protected <F extends Traverser<? super E>> void traverse(F function,
         LazyBinarySearchTreeNode<E> treeNode)
   {
      if (treeNode == null)
      {
         return;
      }

      traverse(function, treeNode.leftChild);

      if (!treeNode.deleted)
      {
         function.visit(treeNode.data);
      }

      traverse(function, treeNode.rightChild);
   }

   protected LazyBinarySearchTreeNode<E>
         cloneSubtree(LazyBinarySearchTreeNode<E> root)
   {
      if (root == null)
      {
         return null;
      }

      return new LazyBinarySearchTreeNode<E>(root.data,
            cloneSubtree(root.leftChild), cloneSubtree(root.rightChild));
   }

   protected LazyBinarySearchTreeNode<E>
         collectGarbage(LazyBinarySearchTreeNode<E> root)
   {
      if (root == null)
      {
         return null;
      }

      root.leftChild = collectGarbage(root.leftChild);
      root.rightChild = collectGarbage(root.rightChild);

      if (root.deleted)
      {
         root = removeHard(root);
      }

      return root;
   }

   protected LazyBinarySearchTreeNode<E>
         removeHard(LazyBinarySearchTreeNode<E> root)
   {
      if (root == null)
      {
         return null;
      }

      if (root.leftChild != null && root.rightChild != null)
      {
         LazyBinarySearchTreeNode<E> rightChildMin = findMin(root.rightChild);
         root.deleted = false;
         root.data = rightChildMin.data;
         rightChildMin.deleted = true;
         root.rightChild = removeHard(rightChildMin);
      }
      else
      {
         root = root.leftChild != null ? root.leftChild : root.rightChild;
         mSizeHard--;
      }

      return root;
   }

   protected class LazyBinarySearchTreeNode<T extends Comparable<? super T>>
   {
      public T data;
      public LazyBinarySearchTreeNode<T> leftChild, rightChild;
      public LazyBinarySearchTreeNode<T> myRoot;
      public boolean deleted;

      public LazyBinarySearchTreeNode(T d, LazyBinarySearchTreeNode<T> lft,
            LazyBinarySearchTreeNode<T> rt)
      {
         data = d;
         leftChild = lft;
         rightChild = rt;
         deleted = false;
      }

      public LazyBinarySearchTreeNode()
      {
         this(null, null, null);
      }
   }
}
