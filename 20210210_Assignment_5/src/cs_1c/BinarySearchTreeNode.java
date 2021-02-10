package cs_1c;

public class BinarySearchTreeNode<E extends Comparable<? super E>>
{
   public BinarySearchTreeNode<E> lftChild, rtChild;
   public E data;
   public BinarySearchTreeNode<E> myRoot;

   public BinarySearchTreeNode(E d, BinarySearchTreeNode<E> lft,
         BinarySearchTreeNode<E> rt)
   {
      lftChild = lft;
      rtChild = rt;
      data = d;
   }

   public BinarySearchTreeNode()
   {
      this(null, null, null);
   }
}
