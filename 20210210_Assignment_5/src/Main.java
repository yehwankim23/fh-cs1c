
// Assignment 5 - Top-Down Splay Trees Using Inheritance

// Main.java -------------------------------------------------------------------
public class Main
{
   // @formatter:off (Eclipse formatter tag)
   /* Proof of correctness
   public static void main(String[] args)
   {
      SplayTree<Integer> tree = new SplayTree<Integer>();
      PrintObject<Integer> printer = new PrintObject<Integer>();

      tree.traverse(printer);

      System.out.println("Initial size: " + tree.size());
      for (int k = 1; k <= 32; k++)
      {
         tree.insert(k);
      }
      System.out.println("New size: " + tree.size());

      System.out.println("\nTraversal");
      tree.traverse(printer);
      System.out.println();

      for (int k = -1; k < 13; k++)
      {
         int k2 = 10 - k;

         if (!tree.contains(k2))
         {
            System.out.println(" oops on contains " + k2);
         }
         System.out.println("contains " + k2 + " --> root: " + tree.showRoot()
               + " height: " + tree.showHeight());

         try
         {
            tree.find(k);
         }
         catch (Exception e)
         {
            System.out.println(" oops on find " + k);
         }
         System.out.println("find " + k + " --> root: " + tree.showRoot()
               + " height: " + tree.showHeight());
      }
   }
    */
   // @formatter:on (Eclipse formatter tag)

   public static void main(String[] args)
   {
      SplayTree<Integer> tree = new SplayTree<Integer>();
      PrintObject<Integer> printer = new PrintObject<Integer>();

      System.out.print("tree: ");
      if (tree.empty())
      {
         System.out.println("empty\n");
      }
      else
      {
         tree.traverse(printer);
         System.out.println("\n");
      }

      System.out.println(
            "insert 3: " + tree.insert(3) + " / root: " + tree.showRoot());
      System.out.println(
            "insert 1: " + tree.insert(1) + " / root: " + tree.showRoot());
      System.out.println(
            "insert 4: " + tree.insert(4) + " / root: " + tree.showRoot());
      System.out.println(
            "insert 1: " + tree.insert(1) + " / root: " + tree.showRoot());
      System.out.println(
            "insert 5: " + tree.insert(5) + " / root: " + tree.showRoot());
      System.out.println(
            "insert 9: " + tree.insert(9) + " / root: " + tree.showRoot());
      System.out.println(
            "insert 2: " + tree.insert(2) + " / root: " + tree.showRoot());

      System.out.print("\ntree: ");
      if (tree.empty())
      {
         System.out.println("empty\n");
      }
      else
      {
         tree.traverse(printer);
         System.out.println("\n");
      }

      for (int i = 1; i < 10; i++)
      {
         System.out.println("contains " + i + ": " + tree.contains(i)
               + " / root: " + tree.showRoot());
      }
      System.out.println();

      for (int i = 1; i < 10; i++)
      {
         System.out.print("find " + i + ": ");

         try
         {
            System.out.print(tree.find(i) == null ? "false" : "true");
         }
         catch (Exception e)
         {
            System.out.print(e.getClass().getSimpleName());
         }

         System.out.println(" / root: " + tree.showRoot());
      }
   }
}

// @formatter:off (Eclipse formatter tag)

/* Proof of correctness
Initial size: 0
New size: 32

Traversal
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
 31 32 
contains 11 --> root: 11 height: 11
 oops on find -1
find -1 --> root: 1 height: 13
contains 10 --> root: 10 height: 12
 oops on find 0
find 0 --> root: 1 height: 13
contains 9 --> root: 9 height: 13
find 1 --> root: 1 height: 14
contains 8 --> root: 8 height: 14
find 2 --> root: 2 height: 15
contains 7 --> root: 7 height: 15
find 3 --> root: 3 height: 16
contains 6 --> root: 6 height: 16
find 4 --> root: 4 height: 17
contains 5 --> root: 5 height: 17
find 5 --> root: 5 height: 17
contains 4 --> root: 4 height: 18
find 6 --> root: 6 height: 16
contains 3 --> root: 3 height: 18
find 7 --> root: 7 height: 15
contains 2 --> root: 2 height: 17
find 8 --> root: 8 height: 14
contains 1 --> root: 1 height: 16
find 9 --> root: 9 height: 13
 oops on contains 0
contains 0 --> root: 1 height: 15
find 10 --> root: 10 height: 12
 oops on contains -1
contains -1 --> root: 1 height: 14
find 11 --> root: 11 height: 11
 oops on contains -2
contains -2 --> root: 1 height: 13
find 12 --> root: 12 height: 7

 */

/* Output
tree: empty

insert 3: true / root: 3
insert 1: true / root: 1
insert 4: true / root: 4
insert 1: false / root: 1
insert 5: true / root: 5
insert 9: true / root: 9
insert 2: true / root: 2

tree: 1 3 4 5 9 2 3 4 5 9 

contains 1: true / root: 1
contains 2: true / root: 2
contains 3: true / root: 3
contains 4: true / root: 4
contains 5: true / root: 5
contains 6: false / root: 9
contains 7: false / root: 5
contains 8: false / root: 9
contains 9: true / root: 9

find 1: true / root: 1
find 2: true / root: 2
find 3: true / root: 3
find 4: true / root: 4
find 5: true / root: 5
find 6: NoSuchElementException / root: 9
find 7: NoSuchElementException / root: 5
find 8: NoSuchElementException / root: 9
find 9: true / root: 9

 */
