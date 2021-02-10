package cs_1c;

import java.util.NoSuchElementException;

public class BinarySearchTree<E extends Comparable<? super E>>
      implements Cloneable
{
   protected int mSize;
   protected BinarySearchTreeNode<E> mRoot;

   public BinarySearchTree()
   {
      clear();
   }

   public boolean empty()
   {
      return (mSize == 0);
   }

   public int size()
   {
      return mSize;
   }

   public void clear()
   {
      mSize = 0;
      mRoot = null;
   }

   public int showHeight()
   {
      return findHeight(mRoot, -1);
   }

   public E findMin()
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMin(mRoot).data;
   }

   public E findMax()
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMax(mRoot).data;
   }

   public E find(E x)
   {
      BinarySearchTreeNode<E> resultNode;
      resultNode = find(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();
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
      return (mSize != oldSize);
   }

   public boolean remove(E x)
   {
      int oldSize = mSize;
      mRoot = remove(mRoot, x);
      return (mSize != oldSize);
   }

   public <F extends Traverser<? super E>> void traverse(F func)
   {
      traverse(func, mRoot);
   }

   @SuppressWarnings("unchecked")
   public Object clone() throws CloneNotSupportedException
   {
      BinarySearchTree<E> newObject = (BinarySearchTree<E>) super.clone();
      newObject.clear();
      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      return newObject;
   }

   protected BinarySearchTreeNode<E> findMin(BinarySearchTreeNode<E> root)
   {
      if (root == null)
         return null;
      if (root.lftChild == null)
         return root;
      return findMin(root.lftChild);
   }

   protected BinarySearchTreeNode<E> findMax(BinarySearchTreeNode<E> root)
   {
      if (root == null)
         return null;
      if (root.rtChild == null)
         return root;
      return findMax(root.rtChild);
   }

   protected BinarySearchTreeNode<E> insert(BinarySearchTreeNode<E> root, E x)
   {
      int compareResult;
      if (root == null)
      {
         mSize++;
         return new BinarySearchTreeNode<E>(x, null, null);
      }
      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         root.lftChild = insert(root.lftChild, x);
      else if (compareResult > 0)
         root.rtChild = insert(root.rtChild, x);
      return root;
   }

   protected BinarySearchTreeNode<E> remove(BinarySearchTreeNode<E> root, E x)
   {
      int compareResult;
      if (root == null)
         return null;
      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         root.lftChild = remove(root.lftChild, x);
      else if (compareResult > 0)
         root.rtChild = remove(root.rtChild, x);
      else if (root.lftChild != null && root.rtChild != null)
      {
         root.data = findMin(root.rtChild).data;
         root.rtChild = remove(root.rtChild, root.data);
      }
      else
      {
         root = (root.lftChild != null) ? root.lftChild : root.rtChild;
         mSize--;
      }
      return root;
   }

   protected <F extends Traverser<? super E>> void traverse(F func,
         BinarySearchTreeNode<E> treeNode)
   {
      if (treeNode == null)
         return;
      traverse(func, treeNode.lftChild);
      func.visit(treeNode.data);
      traverse(func, treeNode.rtChild);
   }

   protected BinarySearchTreeNode<E> find(BinarySearchTreeNode<E> root, E x)
   {
      int compareResult;
      if (root == null)
         return null;
      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         return find(root.lftChild, x);
      if (compareResult > 0)
         return find(root.rtChild, x);
      return root;
   }

   protected BinarySearchTreeNode<E> cloneSubtree(BinarySearchTreeNode<E> root)
   {
      BinarySearchTreeNode<E> newNode;
      if (root == null)
         return null;
      newNode = new BinarySearchTreeNode<E>(root.data,
            cloneSubtree(root.lftChild), cloneSubtree(root.rtChild));
      return newNode;
   }

   protected int findHeight(BinarySearchTreeNode<E> treeNode, int height)
   {
      int leftHeight, rightHeight;
      if (treeNode == null)
         return height;
      height++;
      leftHeight = findHeight(treeNode.lftChild, height);
      rightHeight = findHeight(treeNode.rtChild, height);
      return (leftHeight > rightHeight) ? leftHeight : rightHeight;
   }
}
