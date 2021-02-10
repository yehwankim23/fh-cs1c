
// SplayTree.java --------------------------------------------------------------
import cs_1c.BinarySearchTreeNode;
import cs_1c.BinarySearchTree;

public class SplayTree<E extends Comparable<? super E>>
      extends BinarySearchTree<E>
{
   @Override
   public boolean insert(E x)
   {
      if (mRoot == null)
      {
         mRoot = new BinarySearchTreeNode<E>(x, null, null);
         mSize++;

         return true;
      }

      mRoot = splay(mRoot, x);

      int compareResult = x.compareTo(mRoot.data);

      if (compareResult < 0)
      {
         mRoot = new BinarySearchTreeNode<E>(x, mRoot.lftChild, mRoot);
         mSize++;

         return true;
      }
      else if (compareResult > 0)
      {
         mRoot = new BinarySearchTreeNode<E>(x, mRoot, mRoot.rtChild);
         mSize++;

         return true;
      }

      return false;
   }

   @Override
   public boolean remove(E x)
   {
      if (mRoot == null)
      {
         return false;
      }

      mRoot = splay(mRoot, x);

      if (x.compareTo(mRoot.data) != 0)
      {
         return false;
      }

      BinarySearchTreeNode<E> newRoot;

      if (mRoot.lftChild == null)
      {
         newRoot = mRoot.rtChild;
      }
      else
      {
         newRoot = splay(mRoot.lftChild, x);
         mRoot.lftChild.rtChild = mRoot.rtChild;
      }

      mRoot = newRoot;
      mSize--;

      return true;
   }

   @Override
   protected BinarySearchTreeNode<E> find(BinarySearchTreeNode<E> root, E x)
   {
      if (root == null)
      {
         return null;
      }

      mRoot = splay(root, x);

      if (x.compareTo(mRoot.data) != 0)
      {
         return null;
      }

      return mRoot;
   }

   public E showRoot()
   {
      return mRoot == null ? null : mRoot.data;
   }

   protected BinarySearchTreeNode<E> splay(BinarySearchTreeNode<E> root, E x)
   {
      BinarySearchTreeNode<E> rightTree = null;
      BinarySearchTreeNode<E> leftTree = null;
      BinarySearchTreeNode<E> rightTreeMin = null;
      BinarySearchTreeNode<E> leftTreeMax = null;

      while (root != null)
      {
         int compareResult = x.compareTo(root.data);

         if (compareResult < 0)
         {
            if (root.lftChild == null)
            {
               break;
            }
            else if (x.compareTo(root.lftChild.data) < 0)
            {
               root = rotateWithLeftChild(root);

               if (root.lftChild == null)
               {
                  break;
               }
            }

            if (rightTree == null)
            {
               rightTreeMin = rightTree = root;
            }
            else
            {
               rightTreeMin = rightTreeMin.lftChild = root;
            }

            root = root.lftChild;
         }
         else if (compareResult > 0)
         {
            if (root.rtChild == null)
            {
               break;
            }
            else if (x.compareTo(root.rtChild.data) > 0)
            {
               root = rotateWithRightChild(root);

               if (root.rtChild == null)
               {
                  break;
               }
            }

            if (leftTree == null)
            {
               leftTreeMax = leftTree = root;
            }
            else
            {
               leftTreeMax = leftTreeMax.rtChild = root;
            }

            root = root.rtChild;
         }
         else
         {
            break;
         }
      }

      if (leftTree != null)
      {
         leftTreeMax.rtChild = root.lftChild;
         root.lftChild = leftTree;
      }

      if (rightTree != null)
      {
         rightTreeMin.lftChild = root.rtChild;
         root.rtChild = rightTree;
      }

      return root;
   }

   protected BinarySearchTreeNode<E>
         rotateWithLeftChild(BinarySearchTreeNode<E> k2)
   {
      BinarySearchTreeNode<E> k1 = k2.lftChild;
      k2.lftChild = k1.rtChild;
      k1.rtChild = k2;

      return k1;
   }

   protected BinarySearchTreeNode<E>
         rotateWithRightChild(BinarySearchTreeNode<E> k2)
   {
      BinarySearchTreeNode<E> k1 = k2.rtChild;
      k2.rtChild = k1.lftChild;
      k1.lftChild = k2;

      return k1;
   }
}
