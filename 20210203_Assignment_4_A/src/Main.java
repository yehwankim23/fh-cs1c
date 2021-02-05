
// Assignment 4 - Lazy Deletion in Binary Search Trees
// Part A (required) - Lazy Deletion With Ints

// Main.java -------------------------------------------------------------------
public class Main
{
   // @formatter:off (Eclipse formatter tag)
   /* Proof of correctness
   @SuppressWarnings("unchecked")
   public static void main(String[] args) throws CloneNotSupportedException
   {
      LazyBinarySearchTree<Integer> searchTree
            = new LazyBinarySearchTree<Integer>();
      PrintObject<Integer> intPrinter = new PrintObject<Integer>();

      searchTree.traverse(intPrinter);

      System.out.println("\ninitial size: " + searchTree.size());
      searchTree.insert(50);
      searchTree.insert(20);
      searchTree.insert(30);
      searchTree.insert(70);
      searchTree.insert(10);
      searchTree.insert(60);

      System.out.println("After populating -- traversal and sizes:");
      searchTree.traverse(intPrinter);
      System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out
            .println("Collecting garbage on new tree - should be no garbage.");
      searchTree.collectGarbage();
      System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      LazyBinarySearchTree<Integer> searchTree2
            = (LazyBinarySearchTree<Integer>) searchTree.clone();

      System.out.println("\n\nAttempting 1 removal:");
      if (searchTree.remove(20))
         System.out.println("removed 20");
      System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out.println("Collecting Garbage - should clean 1 item.");
      searchTree.collectGarbage();
      System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out.println("Collecting Garbage again - no change expected.");
      searchTree.collectGarbage();
      System.out.println("tree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out.println("Adding 'hard' 22 - should see new sizes.");
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out.println("\nAfter soft removal.");
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out.println("Repeating soft removal. Should see no change.");
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out.println("Soft insertion. Hard size should not change.");
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      System.out.println("\n\nAttempting 100 removals:");
      for (int k = 100; k > 0; k--)
      {
         if (searchTree.remove(k))
         {
            System.out.println("removed " + k);
         }
      }
      searchTree.collectGarbage();

      System.out.println("\nsearch_tree now:");
      searchTree.traverse(intPrinter);
      System.out.println("\ntree 1 size: " + searchTree.size() + "  Hard size: "
            + searchTree.sizeHard());

      searchTree2.insert(500);
      searchTree2.insert(200);
      searchTree2.insert(300);
      searchTree2.insert(700);
      searchTree2.insert(100);
      searchTree2.insert(600);
      System.out.println("\nsearchTree2:\n");
      searchTree2.traverse(intPrinter);
      System.out.println("\ntree 2 size: " + searchTree2.size()
            + "  Hard size: " + searchTree2.sizeHard());
   }
    */
   // @formatter:on (Eclipse formatter tag)

   @SuppressWarnings("unchecked")
   public static void main(String[] args)
   {
      LazyBinarySearchTree<Integer> tree1 = new LazyBinarySearchTree<Integer>();
      PrintObject<Integer> printer = new PrintObject<Integer>();

      System.out.print("find min: ");
      try
      {
         System.out.println(tree1.findMin());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.print("find max: ");
      try
      {
         System.out.println(tree1.findMax());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.println("insert 4: " + tree1.insert(4));
      System.out.println("insert 2: " + tree1.insert(2));
      System.out.println("insert 6: " + tree1.insert(6));
      System.out.println("insert 1: " + tree1.insert(1));
      System.out.println("insert 3: " + tree1.insert(3));
      System.out.println("insert 5: " + tree1.insert(5));
      System.out.println("insert 7: " + tree1.insert(7));
      System.out.println("insert 1: " + tree1.insert(1));
      System.out.println("insert 4: " + tree1.insert(4));
      System.out.println("insert 7: " + tree1.insert(7));

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.print("find min: ");
      try
      {
         System.out.println(tree1.findMin());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.print("find max: ");
      try
      {
         System.out.println(tree1.findMax());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.println("remove 1: " + tree1.remove(1));
      System.out.println("remove 4: " + tree1.remove(4));
      System.out.println("remove 7: " + tree1.remove(7));
      System.out.println("remove 1: " + tree1.remove(1));
      System.out.println("remove 4: " + tree1.remove(4));
      System.out.println("remove 7: " + tree1.remove(7));

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.print("find min: ");
      try
      {
         System.out.println(tree1.findMin());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.print("find max: ");
      try
      {
         System.out.println(tree1.findMax());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.println("insert 1: " + tree1.insert(1));
      System.out.println("insert 4: " + tree1.insert(4));
      System.out.println("remove 6: " + tree1.remove(6));

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.println("collect garbage: " + tree1.collectGarbage());

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.println("collect garbage: " + tree1.collectGarbage());

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      LazyBinarySearchTree<Integer> tree2 = null;

      System.out.print("clone: ");
      try
      {
         tree2 = (LazyBinarySearchTree<Integer>) tree1.clone();
         System.out.println("no exception");
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println(
            "size: " + tree1.size() + " / hard size: " + tree1.sizeHard());
      System.out.print("tree2: ");
      if (tree2.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree2.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree2.size() + " / hard size: "
            + tree2.sizeHard() + "\n");

      System.out.println("remove 1: " + tree1.remove(1));
      System.out.println("remove 2: " + tree1.remove(2));
      System.out.println("remove 3: " + tree1.remove(3));
      System.out.println("remove 4: " + tree1.remove(4));
      System.out.println("remove 5: " + tree1.remove(5));

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println(
            "size: " + tree1.size() + " / hard size: " + tree1.sizeHard());
      System.out.print("tree2: ");
      if (tree2.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree2.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree2.size() + " / hard size: "
            + tree2.sizeHard() + "\n");

      System.out.print("find min: ");
      try
      {
         System.out.println(tree1.findMin());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.print("find max: ");
      try
      {
         System.out.println(tree1.findMax());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println(
            "size: " + tree1.size() + " / hard size: " + tree1.sizeHard());
      System.out.print("tree2: ");
      if (tree2.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree2.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree2.size() + " / hard size: "
            + tree2.sizeHard() + "\n");

      System.out.println("clear");
      tree1.clear();

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
         System.out.println();
      }
      System.out.println(
            "size: " + tree1.size() + " / hard size: " + tree1.sizeHard());
      System.out.print("tree2: ");
      if (tree2.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree2.traverse(printer);
         System.out.println();
      }
      System.out.println("size: " + tree2.size() + " / hard size: "
            + tree2.sizeHard() + "\n");

      System.out.println("contains 1: " + tree2.contains(1));
      System.out.print("find 1: ");
      try
      {
         System.out.println(tree2.find(1));
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.println("contains 7: " + tree2.contains(7));
      System.out.print("find 7: ");
      try
      {
         System.out.println(tree2.find(7));
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.print("tree2: ");
      if (tree2.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree2.traverse(printer);
         System.out.println();
      }
      System.out.println(
            "size: " + tree2.size() + " / hard size: " + tree2.sizeHard());
   }
}

// @formatter:off (Eclipse formatter tag)

/* Proof of correctness

initial size: 0
After populating -- traversal and sizes:
10 20 30 50 60 70 
tree 1 size: 6  Hard size: 6
Collecting garbage on new tree - should be no garbage.
tree 1 size: 6  Hard size: 6


Attempting 1 removal:
removed 20
tree 1 size: 5  Hard size: 6
Collecting Garbage - should clean 1 item.
tree 1 size: 5  Hard size: 5
Collecting Garbage again - no change expected.
tree 1 size: 5  Hard size: 5
Adding 'hard' 22 - should see new sizes.
10 22 30 50 60 70 
tree 1 size: 6  Hard size: 6

After soft removal.
10 30 50 60 70 
tree 1 size: 5  Hard size: 6
Repeating soft removal. Should see no change.
10 30 50 60 70 
tree 1 size: 5  Hard size: 6
Soft insertion. Hard size should not change.
10 22 30 50 60 70 
tree 1 size: 6  Hard size: 6


Attempting 100 removals:
removed 70
removed 60
removed 50
removed 30
removed 22
removed 10

search_tree now:

tree 1 size: 0  Hard size: 0

searchTree2:

10 20 30 50 60 70 100 200 300 500 600 700 
tree 2 size: 12  Hard size: 12

 */

/* Output
find min: NoSuchElementException
find max: NoSuchElementException
tree1: empty
size: 0 / hard size: 0

insert 4: true
insert 2: true
insert 6: true
insert 1: true
insert 3: true
insert 5: true
insert 7: true
insert 1: false
insert 4: false
insert 7: false
tree1: 1 2 3 4 5 6 7 
size: 7 / hard size: 7

find min: 1
find max: 7
tree1: 1 2 3 4 5 6 7 
size: 7 / hard size: 7

remove 1: true
remove 4: true
remove 7: true
remove 1: false
remove 4: false
remove 7: false
tree1: 2 3 5 6 
size: 4 / hard size: 7

find min: 2
find max: 6
tree1: 2 3 5 6 
size: 4 / hard size: 7

insert 1: true
insert 4: true
remove 6: true
tree1: 1 2 3 4 5 
size: 5 / hard size: 7

collect garbage: true
tree1: 1 2 3 4 5 
size: 5 / hard size: 5

collect garbage: false
tree1: 1 2 3 4 5 
size: 5 / hard size: 5

clone: no exception
tree1: 1 2 3 4 5 
size: 5 / hard size: 5
tree2: 1 2 3 4 5 
size: 5 / hard size: 5

remove 1: true
remove 2: true
remove 3: true
remove 4: true
remove 5: true
tree1: empty
size: 0 / hard size: 5
tree2: 1 2 3 4 5 
size: 5 / hard size: 5

find min: NoSuchElementException
find max: NoSuchElementException
tree1: empty
size: 0 / hard size: 5
tree2: 1 2 3 4 5 
size: 5 / hard size: 5

clear
tree1: empty
size: 0 / hard size: 0
tree2: 1 2 3 4 5 
size: 5 / hard size: 5

contains 1: true
find 1: 1
contains 7: false
find 7: NoSuchElementException
tree2: 1 2 3 4 5 
size: 5 / hard size: 5

 */
