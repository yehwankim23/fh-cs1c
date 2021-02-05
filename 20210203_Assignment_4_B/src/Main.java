
// Assignment 4 - Lazy Deletion in Binary Search Trees
// Part B - Lazy Deletion with EBooks

// Main.java -------------------------------------------------------------------
import cs_1c.eBookEntry;
import cs_1c.eBookEntryReader;

public class Main
{
   @SuppressWarnings("unchecked")
   public static void main(String[] args)
   {
      eBookEntryReader reader = new eBookEntryReader("catalog-short4.txt");
      if (reader.readError())
      {
         System.out.println("couldn't open " + reader.getFileName());
         return;
      }

      final int SIZE = 5;
      System.out.println("SIZE: " + SIZE + "\n");

      eBookEntry[] books = new eBookEntry[SIZE];
      System.out.println("books:");
      for (int i = 0; i < SIZE; i++)
      {
         eBookEntry book = reader.getBook(i);
         books[i] = book;
         System.out.print(book);
      }

      LazyBinarySearchTree<eBookEntry> tree1 = new LazyBinarySearchTree<eBookEntry>();
      PrintObject<eBookEntry> printer = new PrintObject<eBookEntry>();

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         tree1.traverse(printer);
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

      System.out.println("\ninsert books[2]: " + tree1.insert(books[2]));
      System.out.println("insert books[3]: " + tree1.insert(books[3]));
      System.out.println("insert books[1]: " + tree1.insert(books[1]));
      System.out.println("insert books[4]: " + tree1.insert(books[4]));
      System.out.println("insert books[0]: " + tree1.insert(books[0]));
      System.out.println("insert books[3]: " + tree1.insert(books[3]));
      System.out.println("insert books[0]: " + tree1.insert(books[0]));

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         System.out.println();
         tree1.traverse(printer);
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.print("find min: ");
      try
      {
         System.out.print("\n" + tree1.findMin());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.print("find max: ");
      try
      {
         System.out.print("\n" + tree1.findMax());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.println("remove books[3]: " + tree1.remove(books[3]));
      System.out.println("remove books[0]: " + tree1.remove(books[0]));
      System.out.println("remove books[3]: " + tree1.remove(books[3]));
      System.out.println("remove books[0]: " + tree1.remove(books[0]));

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         System.out.println();
         tree1.traverse(printer);
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      System.out.print("find min: ");
      try
      {
         System.out.print("\n" + tree1.findMin());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.print("find max: ");
      try
      {
         System.out.print("\n" + tree1.findMax());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.println("insert books[3]: " + tree1.insert(books[3]));
      System.out.println("remove books[1]: " + tree1.remove(books[1]));

      System.out.print("tree1: ");
      if (tree1.empty())
      {
         System.out.println("empty");
      }
      else
      {
         System.out.println();
         tree1.traverse(printer);
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
         System.out.println();
         tree1.traverse(printer);
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
         System.out.println();
         tree1.traverse(printer);
      }
      System.out.println("size: " + tree1.size() + " / hard size: "
            + tree1.sizeHard() + "\n");

      LazyBinarySearchTree<eBookEntry> tree2 = null;

      System.out.print("clone: ");
      try
      {
         tree2 = (LazyBinarySearchTree<eBookEntry>) tree1.clone();
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
         System.out.println();
         tree1.traverse(printer);
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
         System.out.println();
         tree2.traverse(printer);
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
         System.out.println();
         tree1.traverse(printer);
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
         System.out.println();
         tree2.traverse(printer);
      }
      System.out.println("size: " + tree2.size() + " / hard size: "
            + tree2.sizeHard() + "\n");

      System.out.print("find min: ");
      try
      {
         System.out.print("\n" + tree1.findMin());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.print("find max: ");
      try
      {
         System.out.print("\n" + tree1.findMax());
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }

      System.out.println("\ncontains books[3]: " + tree2.contains(books[3]));
      System.out.print("find books[3]: ");
      try
      {
         System.out.print("\n" + tree2.find(books[3]));
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
      System.out.println("contains books[0]: " + tree2.contains(books[0]));
      System.out.print("find books[0]: ");
      try
      {
         System.out.print("\n" + tree2.find(books[0]));
      }
      catch (Exception e)
      {
         System.out.println(e.getClass().getSimpleName());
      }
   }
}

//@formatter:off (Eclipse formatter tag)

/* Output
SIZE: 5

books:
   # 30170  ---------------
   "Lonesome Hearts"
   by Winterbotham, R. R. (Russell Robert), 1904-1971
   re: Science fiction

   # 30169  ---------------
   "The Story of the White Mouse"
   by Unknown
   re: Conduct of life -- Juvenile fiction

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

tree1: empty
size: 0 / hard size: 0

find min: NoSuchElementException
find max: NoSuchElementException

insert books[2]: true
insert books[3]: true
insert books[1]: true
insert books[4]: true
insert books[0]: true
insert books[3]: false
insert books[0]: false
tree1: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

   # 30169  ---------------
   "The Story of the White Mouse"
   by Unknown
   re: Conduct of life -- Juvenile fiction

   # 30170  ---------------
   "Lonesome Hearts"
   by Winterbotham, R. R. (Russell Robert), 1904-1971
   re: Science fiction

size: 5 / hard size: 5

find min: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

find max: 
   # 30170  ---------------
   "Lonesome Hearts"
   by Winterbotham, R. R. (Russell Robert), 1904-1971
   re: Science fiction

remove books[3]: true
remove books[0]: true
remove books[3]: false
remove books[0]: false
tree1: 
   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

   # 30169  ---------------
   "The Story of the White Mouse"
   by Unknown
   re: Conduct of life -- Juvenile fiction

size: 3 / hard size: 5

find min: 
   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

find max: 
   # 30169  ---------------
   "The Story of the White Mouse"
   by Unknown
   re: Conduct of life -- Juvenile fiction

insert books[3]: true
remove books[1]: true
tree1: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

size: 3 / hard size: 5

collect garbage: true
tree1: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

size: 3 / hard size: 3

collect garbage: false
tree1: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

size: 3 / hard size: 3

clone: no exception
tree1: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

size: 3 / hard size: 3
tree2: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

size: 3 / hard size: 3

clear
tree1: empty
size: 0 / hard size: 0
tree2: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

   # 28546  ---------------
   "A History of England Principally in the Seventeenth Century, Volume I (of 6)
"
   by Ranke, Leopold von, 1795-1886
   re: Great Britain -- History -- Stuarts, 1603-1714

   # 28805  ---------------
   "Dorothy's House Party"
   by Raymond, Evelyn, 1843-1910
   re: Orphans -- Juvenile fiction

size: 3 / hard size: 3

find min: NoSuchElementException
find max: NoSuchElementException

contains books[3]: true
find books[3]: 
   # 28711  ---------------
   "Operas Every Child Should KnowDescriptions of the Text and Music of Some of 
the Most Famous Masterpieces"
   by Bacon, Mary Schell Hoke, 1870-1934
   re: Operas -- Stories, plots, etc.

contains books[0]: false
find books[0]: NoSuchElementException

 */
