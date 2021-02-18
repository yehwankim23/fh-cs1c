
// Assignment 6 - Quadratic Probing with a find()

// Main.java -------------------------------------------------------------------
import java.util.NoSuchElementException;

import cs_1c.eBookEntryReader;

public class Main
{
   // @formatter:off (Eclipse formatter tag)
   /* Proof of correctness (Integer)
   public static void main(String[] args)
   {
      final int NUM_RANDOM_INDICES = 25;

      eBookEntryReader bookInput = new eBookEntryReader("catalog-short4.txt");

      if (bookInput.readError())
      {
         System.out.println(
               "couldn't open " + bookInput.getFileName() + " for input.");
         return;
      }

      System.out.println(bookInput.getFileName());
      System.out.println(bookInput.getNumBooks());

      int[] randomIndices = new int[NUM_RANDOM_INDICES];
      int arraySize = bookInput.getNumBooks();

      System.out.print("Random indices generated: ");

      for (int k = 0; k < NUM_RANDOM_INDICES; k++)
      {
         randomIndices[k] = (int) (Math.random() * arraySize);
         System.out.print(randomIndices[k] + " ");
      }
      System.out.println("\n");

      QuadraticProbingHashTableWithFind<Integer, eBookCompTextNum> hashTable
      = new QuadraticProbingHashTableWithFind<Integer, eBookCompTextNum>();

      for (int k = 0; k < arraySize; k++)
      {
         hashTable.insert(new eBookCompTextNum(bookInput.getBook(k)));
      }

      System.out.println("Some random books from the eBookEntryReader ");

      for (int k = 0; k < NUM_RANDOM_INDICES; k++)
      {
         System.out.print(bookInput.getBook(randomIndices[k]));
      }

      System.out.println("The same random books from the hash table ");

      for (int k = 0; k < NUM_RANDOM_INDICES; k++)
      {
         System.out.println("Book #" + randomIndices[k] + " ");

         try
         {
            eBookCompTextNum bookResult = hashTable
                  .find(bookInput.getBook(randomIndices[k]).getETextNum());

            System.out.println("YES: " + bookResult);
         }
         catch (NoSuchElementException e)
         {
            System.out.println("no.");
         }

         System.out.println();
      }

      try
      {
         eBookCompTextNum bookResult = hashTable.find(-3);

         System.out
         .println("YES: " + bookResult.data.getCreator().substring(0, 8)
               + " " + bookResult.data.getTitle().substring(0, 10));
      }
      catch (NoSuchElementException e)
      {
         System.out.println("no.");
      }

      try
      {
         eBookCompTextNum bookResult = hashTable.find(9000);

         System.out
         .println("YES: " + bookResult.data.getCreator().substring(0, 8)
               + " " + bookResult.data.getTitle().substring(0, 10));
      }
      catch (NoSuchElementException e)
      {
         System.out.println("no.");
      }

      try
      {
         eBookCompTextNum bookResult = hashTable.find(5000);

         System.out
         .println("YES: " + bookResult.data.getCreator().substring(0, 8)
               + " " + bookResult.data.getTitle().substring(0, 10));
      }
      catch (NoSuchElementException e)
      {
         System.out.println("no.");
      }
   }
    */
   // @formatter:on (Eclipse formatter tag)

   // Proof of correctness (String)
   public static void main(String[] args)
   {
      final int NUM_RANDOM_INDICES = 25;

      eBookEntryReader bookInput = new eBookEntryReader("catalog-short4.txt");

      if (bookInput.readError())
      {
         System.out.println(
               "couldn't open " + bookInput.getFileName() + " for input.");
         return;
      }

      System.out.println(bookInput.getFileName());
      System.out.println(bookInput.getNumBooks());

      int[] randomIndices = new int[NUM_RANDOM_INDICES];
      int arraySize = bookInput.getNumBooks();

      System.out.print("Random indices generated: ");

      for (int k = 0; k < NUM_RANDOM_INDICES; k++)
      {
         randomIndices[k] = (int) (Math.random() * arraySize);
         System.out.print(randomIndices[k] + " ");
      }
      System.out.println("\n");

      QuadraticProbingHashTableWithFind<String, eBookCompCreator> hashTable
            = new QuadraticProbingHashTableWithFind<String, eBookCompCreator>();

      for (int k = 0; k < arraySize; k++)
      {
         hashTable.insert(new eBookCompCreator(bookInput.getBook(k)));
      }

      System.out.println("Some random books from the eBookEntryReader ");

      for (int k = 0; k < NUM_RANDOM_INDICES; k++)
      {
         System.out.print(bookInput.getBook(randomIndices[k]));
      }

      System.out.println("The same random books from the hash table ");

      for (int k = 0; k < NUM_RANDOM_INDICES; k++)
      {
         System.out.println("Book #" + randomIndices[k] + " ");

         try
         {
            eBookCompCreator bookResult = hashTable
                  .find(bookInput.getBook(randomIndices[k]).getCreator());

            System.out.println("YES: " + bookResult);
         }
         catch (NoSuchElementException e)
         {
            System.out.println("no.");
         }

         System.out.println();
      }

      try
      {
         eBookCompCreator bookResult = hashTable.find("Jack Kerouac");

         System.out
               .println("YES: " + bookResult.data.getCreator().substring(0, 8)
                     + " " + bookResult.data.getTitle().substring(0, 10));
      }
      catch (NoSuchElementException e)
      {
         System.out.println("no.");
      }

      try
      {
         eBookCompCreator bookResult = hashTable.find(" no one");

         System.out
               .println("YES: " + bookResult.data.getCreator().substring(0, 8)
                     + " " + bookResult.data.getTitle().substring(0, 10));
      }
      catch (NoSuchElementException e)
      {
         System.out.println("no.");
      }

      try
      {
         eBookCompCreator bookResult
               = hashTable.find("Leacock, Stephen, 2010 - 2009");

         System.out
               .println("YES: " + bookResult.data.getCreator().substring(0, 8)
                     + " " + bookResult.data.getTitle().substring(0, 10));
      }
      catch (NoSuchElementException e)
      {
         System.out.println("no.");
      }
   }
}

// @formatter:off (Eclipse formatter tag)

/* Proof of correctness (TextNum)
catalog-short4.txt
4863
Random indices generated: 605 4763 2650 1783 2207 3645 712 2050 3743 2885 2429 2
931 236 3928 1850 1562 1369 3151 2042 4170 1468 3681 1234 1676 423 

Some random books from the eBookEntryReader 
   # 28675  ---------------
   "Red Men and White"
   by Wister, Owen, 1860-1938
   re: Western stories

   # 24909  ---------------
   "The Golden Magnet"
   by Fenn, George Manville, 1831-1909
   re: Treasure troves -- Juvenile fiction

   # 1142  ---------------
   "Typhoon"
   by Conrad, Joseph, 1857-1924
   re: Sea stories

   # 29157  ---------------
   "The Coinages of the Channel Islands"
   by Lowsley, B.
   re: Coins -- Channel Islands

   # 19573  ---------------
   "Alice's Adventures in Wonderland"
   by Carroll, Lewis, 1832-1898
   re: Fantasy

   # 26622  ---------------
   "How to Read the Crystalor, Crystal and Seer"
   by Sepharial, 1864-1929
   re: Crystal gazing

   # 2635  ---------------
   "Clarence"
   by Harte, Bret, 1836-1902
   re: American fiction -- 19th century

   # 14573  ---------------
   "The Truce of God"
   by Rinehart, Mary Roberts, 1876-1958
   re: Christmas stories

   # 26470  ---------------
   "The Red House Mystery"
   by Milne, A. A. (Alan Alexander), 1882-1956
   re: Detective and mystery stories

   # 29401  ---------------
   "The Solar Magnet"
   by Meek, S. P. (Sterner St. Paul), 1894-1972
   re: Science fiction

   # 3284  ---------------
   "The Discovery of the Source of the Nile"
   by Speke, John Hanning, 1827-1864
   re: Nile River

   # 27861  ---------------
   "Kathay: A Cruise in the China Seas"
   by Macaulay, W. Hastings
   re: China -- Description and travel

   # 28214  ---------------
   "Notes and Queries, Number 236, May 6, 1854A Medium of Inter-communication fo
r Literary Men, Artists,Antiquaries, Genealogists, etc"
   by Various
   re: Questions and answers -- Periodicals

   # 26215  ---------------
   "The Little Colonel's Christmas Vacation"
   by Johnston, Annie F. (Annie Fellows), 1863-1931
   re: Boarding schools -- Juvenile fiction

   # 29458  ---------------
   "Cost of Living"
   by Sheckley, Robert, 1928-2005
   re: Science fiction

   # 28000  ---------------
   "Inaugural Presidential Address"
   by Obama, Barack, 1961-
   re: Presidents -- United States -- Inaugural addresses

   # 28283  ---------------
   "Mary Queen of ScotsMakers of History"
   by Abbott, Jacob, 1803-1879
   re: Mary, Queen of Scots, 1542-1587

   # 27476  ---------------
   "The Sign of the Spider"
   by Mitford, Bertram, 1855-1914
   re: Adventure stories

   # 29949  ---------------
   "Washington's Masonic CorrespondenceAs Found among the Washington Papers in t
he Library of Congress"
   by Washington, George, 1732-1799
   re: Washington, George, 1732-1799 -- Freemasonry

   # 25879  ---------------
   "An Historical Account of the Settlements of Scotch Highlanders in America"
   by MacLean, J. P. (John Patterson), 1848-1939
   re: Scots -- United States

   # 28909  ---------------
   "The Philosophy of Style"
   by Spencer, Herbert, 1820-1903
   re: Style, Literary

   # 26575  ---------------
   "Selected Lullabies"
   by Field, Eugene, 1850-1895
   re: Children's poetry

   # 29051  ---------------
   "Account of a Voyage of Discoveryto the West Coast of Corea, and the Great Lo
o-Choo Island"
   by Hall, Basil, 1788-1844
   re: Ryukyu Islands -- Description and travel

   # 28810  ---------------
   "The Devil's Pool"
   by Sand, George, 1804-1876
   re: Pastoral fiction, French

   # 29931  ---------------
   "The Big Tomorrow"
   by Lohrman, Paul
   re: Science fiction

The same random books from the hash table 
Book #605 
YES:    # 28675  ---------------
   "Red Men and White"
   by Wister, Owen, 1860-1938
   re: Western stories



Book #4763 
YES:    # 24909  ---------------
   "The Golden Magnet"
   by Fenn, George Manville, 1831-1909
   re: Treasure troves -- Juvenile fiction



Book #2650 
YES:    # 1142  ---------------
   "Typhoon"
   by Conrad, Joseph, 1857-1924
   re: Sea stories



Book #1783 
YES:    # 29157  ---------------
   "The Coinages of the Channel Islands"
   by Lowsley, B.
   re: Coins -- Channel Islands



Book #2207 
YES:    # 19573  ---------------
   "Alice's Adventures in Wonderland"
   by Carroll, Lewis, 1832-1898
   re: Fantasy



Book #3645 
YES:    # 26622  ---------------
   "How to Read the Crystalor, Crystal and Seer"
   by Sepharial, 1864-1929
   re: Crystal gazing



Book #712 
YES:    # 2635  ---------------
   "Clarence"
   by Harte, Bret, 1836-1902
   re: American fiction -- 19th century



Book #2050 
YES:    # 14573  ---------------
   "The Truce of God"
   by Rinehart, Mary Roberts, 1876-1958
   re: Christmas stories



Book #3743 
YES:    # 26470  ---------------
   "The Red House Mystery"
   by Milne, A. A. (Alan Alexander), 1882-1956
   re: Detective and mystery stories



Book #2885 
YES:    # 29401  ---------------
   "The Solar Magnet"
   by Meek, S. P. (Sterner St. Paul), 1894-1972
   re: Science fiction



Book #2429 
YES:    # 3284  ---------------
   "The Discovery of the Source of the Nile"
   by Speke, John Hanning, 1827-1864
   re: Nile River



Book #2931 
YES:    # 27861  ---------------
   "Kathay: A Cruise in the China Seas"
   by Macaulay, W. Hastings
   re: China -- Description and travel



Book #236 
YES:    # 28214  ---------------
   "Notes and Queries, Number 236, May 6, 1854A Medium of Inter-communication fo
r Literary Men, Artists,Antiquaries, Genealogists, etc"
   by Various
   re: Questions and answers -- Periodicals



Book #3928 
YES:    # 26215  ---------------
   "The Little Colonel's Christmas Vacation"
   by Johnston, Annie F. (Annie Fellows), 1863-1931
   re: Boarding schools -- Juvenile fiction



Book #1850 
YES:    # 29458  ---------------
   "Cost of Living"
   by Sheckley, Robert, 1928-2005
   re: Science fiction



Book #1562 
YES:    # 28000  ---------------
   "Inaugural Presidential Address"
   by Obama, Barack, 1961-
   re: Presidents -- United States -- Inaugural addresses



Book #1369 
YES:    # 28283  ---------------
   "Mary Queen of ScotsMakers of History"
   by Abbott, Jacob, 1803-1879
   re: Mary, Queen of Scots, 1542-1587



Book #3151 
YES:    # 27476  ---------------
   "The Sign of the Spider"
   by Mitford, Bertram, 1855-1914
   re: Adventure stories



Book #2042 
YES:    # 29949  ---------------
   "Washington's Masonic CorrespondenceAs Found among the Washington Papers in t
he Library of Congress"
   by Washington, George, 1732-1799
   re: Washington, George, 1732-1799 -- Freemasonry



Book #4170 
YES:    # 25879  ---------------
   "An Historical Account of the Settlements of Scotch Highlanders in America"
   by MacLean, J. P. (John Patterson), 1848-1939
   re: Scots -- United States



Book #1468 
YES:    # 28909  ---------------
   "The Philosophy of Style"
   by Spencer, Herbert, 1820-1903
   re: Style, Literary



Book #3681 
YES:    # 26575  ---------------
   "Selected Lullabies"
   by Field, Eugene, 1850-1895
   re: Children's poetry



Book #1234 
YES:    # 29051  ---------------
   "Account of a Voyage of Discoveryto the West Coast of Corea, and the Great Lo
o-Choo Island"
   by Hall, Basil, 1788-1844
   re: Ryukyu Islands -- Description and travel



Book #1676 
YES:    # 28810  ---------------
   "The Devil's Pool"
   by Sand, George, 1804-1876
   re: Pastoral fiction, French



Book #423 
YES:    # 29931  ---------------
   "The Big Tomorrow"
   by Lohrman, Paul
   re: Science fiction



no.
no.
no.

 */

/* Proof of correctness (Creator)
catalog-short4.txt
4863
Random indices generated: 3703 1439 2459 2147 3819 3233 3667 3918 2340 4130 824 
2561 547 3359 3832 1245 439 3491 4384 3896 2207 3875 3367 2259 4180 

Some random books from the eBookEntryReader 
   # 26538  ---------------
   "Madge Morton's Victory"
   by Chalmers, Amy D. V.
   re: PZ

   # 28272  ---------------
   "Biographia Scoticana (Scots Worthies)A Brief Historical Account of the Lives
, Characters, and Memorable Transactions of the Most Eminent Scots Worthies"
   by Howie, John, 1735-1793
   re: Church of Scotland -- Biography

   # 7401  ---------------
   "A Crystal Age"
   by Hudson, W. H. (William Henry), 1841-1922
   re: PS

   # 12260  ---------------
   "Jonas on a Farm in Winter"
   by Abbott, Jacob, 1803-1879
   re: (no data found)

   # 26339  ---------------
   "CupologyHow to Be Entertaining"
   by Clara
   re: Amusements

   # 27333  ---------------
   "Pan and Æolus: Poems"
   by Musgrove, Charles Hamilton
   re: Poetry

   # 26591  ---------------
   "Wonderwings and other Fairy Stories"
   by Howes, Edith
   re: Fairy tales

   # 26225  ---------------
   "Fifteen Thousand Useful Phrases"
   by Kleiser, Grenville, 1868-1953
   re: English language -- Terms and phrases

   # 7282  ---------------
   "The Captiva and the Mostellaria"
   by Plautus, Titus Maccius, 254 BC-184 BC
   re: PA

   # 25939  ---------------
   "A Bibliography of the writings in Prose and Verse of George Henry Borrow"
   by Wise, Thomas James, 1859-1937
   re: Borrow, George Henry, 1803-1881 -- Bibliography

   # 29852  ---------------
   "The Ivory Snuff Box"
   by Kummer, Frederic Arnold, 1873-1943
   re: Detective and mystery stories

   # 7872  ---------------
   "Dubliners"
   by Joyce, James, 1882-1941
   re: (no data found)

   # 28987  ---------------
   "Sunlight Patch"
   by Harris, Credo Fitch, 1874-1956
   re: PS

   # 27098  ---------------
   "Technology and Books for All"
   by Lebert, Marie
   re: (no data found)

   # 26322  ---------------
   "Peterkin"
   by Molesworth, Mrs., 1839-1921
   re: PZ

   # 29372  ---------------
   "A Discourse on the Study of the Law of Nature and Nations"
   by Mackintosh, James, Sir, 1765-1832
   re: International law

   # 29718  ---------------
   "The Automobile Storage BatteryIts Care And Repair"
   by Witte, Otto A.
   re: Storage batteries

   # 26878  ---------------
   "The Repairing &amp; Restoration of Violins'The Strad' Library, No. XII."
   by Petherick, Horace, 1839-1919
   re: Violin -- Construction

   # 25581  ---------------
   "Rinkitink in Oz"
   by Baum, L. Frank (Lyman Frank), 1856-1919
   re: Oz (Imaginary place) -- Fiction

   # 26252  ---------------
   "The Prince and the Pauper"
   by Twain, Mark, 1835-1910
   re: Historical fiction

   # 19573  ---------------
   "Alice's Adventures in Wonderland"
   by Carroll, Lewis, 1832-1898
   re: Fantasy

   # 26274  ---------------
   "The Swiss Family Robinson"
   by Wyss, Johann David, 1743-1818
   re: Survival after airplane accidents, shipwrecks, etc. -- Fiction

   # 27086  ---------------
   "The Dodge Clubor, Italy in MDCCCLIX"
   by De Mille, James, 1833-1880
   re: Italy -- Fiction

   # 12231  ---------------
   "Punch, or the London Charivari, Volume 156, May 21, 1919"
   by Various
   re: English wit and humor -- Periodicals

   # 25860  ---------------
   "Punch, or the London Charivari, Vol. 147, July 29, 1914"
   by Various
   re: English wit and humor -- Periodicals

The same random books from the hash table 
Book #3703 
YES:    # 20737  ---------------
   "Madge Morton's Secret"
   by Chalmers, Amy D. V.
   re: (no data found)



Book #1439 
YES:    # 28272  ---------------
   "Biographia Scoticana (Scots Worthies)A Brief Historical Account of the Lives
, Characters, and Memorable Transactions of the Most Eminent Scots Worthies"
   by Howie, John, 1735-1793
   re: Church of Scotland -- Biography



Book #2459 
YES:    # 7401  ---------------
   "A Crystal Age"
   by Hudson, W. H. (William Henry), 1841-1922
   re: PS



Book #2147 
YES:    # 28667  ---------------
   "Genghis Khan, Makers of History Series"
   by Abbott, Jacob, 1803-1879
   re: Genghis Khan, 1162-1227



Book #3819 
YES:    # 26339  ---------------
   "CupologyHow to Be Entertaining"
   by Clara
   re: Amusements



Book #3233 
YES:    # 27333  ---------------
   "Pan and Æolus: Poems"
   by Musgrove, Charles Hamilton
   re: Poetry



Book #3667 
YES:    # 26591  ---------------
   "Wonderwings and other Fairy Stories"
   by Howes, Edith
   re: Fairy tales



Book #3918 
YES:    # 26225  ---------------
   "Fifteen Thousand Useful Phrases"
   by Kleiser, Grenville, 1868-1953
   re: English language -- Terms and phrases



Book #2340 
YES:    # 7282  ---------------
   "The Captiva and the Mostellaria"
   by Plautus, Titus Maccius, 254 BC-184 BC
   re: PA



Book #4130 
YES:    # 25939  ---------------
   "A Bibliography of the writings in Prose and Verse of George Henry Borrow"
   by Wise, Thomas James, 1859-1937
   re: Borrow, George Henry, 1803-1881 -- Bibliography



Book #824 
YES:    # 29852  ---------------
   "The Ivory Snuff Box"
   by Kummer, Frederic Arnold, 1873-1943
   re: Detective and mystery stories



Book #2561 
YES:    # 7872  ---------------
   "Dubliners"
   by Joyce, James, 1882-1941
   re: (no data found)



Book #547 
YES:    # 28987  ---------------
   "Sunlight Patch"
   by Harris, Credo Fitch, 1874-1956
   re: PS



Book #3359 
YES:    # 29801  ---------------
   "A Short History of EBooks"
   by Lebert, Marie
   re: Electronic books



Book #3832 
YES:    # 28306  ---------------
   "The Christmas Fairyand Other Stories"
   by Molesworth, Mrs., 1839-1921
   re: Children's stories



Book #1245 
YES:    # 29372  ---------------
   "A Discourse on the Study of the Law of Nature and Nations"
   by Mackintosh, James, Sir, 1765-1832
   re: International law



Book #439 
YES:    # 29718  ---------------
   "The Automobile Storage BatteryIts Care And Repair"
   by Witte, Otto A.
   re: Storage batteries



Book #3491 
YES:    # 26878  ---------------
   "The Repairing &amp; Restoration of Violins'The Strad' Library, No. XII."
   by Petherick, Horace, 1839-1919
   re: Violin -- Construction



Book #4384 
YES:    # 27951  ---------------
   "Policeman Bluejay"
   by Baum, L. Frank (Lyman Frank), 1856-1919
   re: Fantasy



Book #3896 
YES:    # 26213  ---------------
   "Fenimore Cooper's Literary Offenses"
   by Twain, Mark, 1835-1910
   re: American literature -- History and criticism -- Theory, etc.



Book #2207 
YES:    # 28885  ---------------
   "Alice's Adventures in WonderlandIllustrated by Arthur Rackham. With a Proem 
by Austin Dobson"
   by Carroll, Lewis, 1832-1898
   re: Fantasy



Book #3875 
YES:    # 26274  ---------------
   "The Swiss Family Robinson"
   by Wyss, Johann David, 1743-1818
   re: Survival after airplane accidents, shipwrecks, etc. -- Fiction



Book #3367 
YES:    # 28435  ---------------
   "The CryptogramA Novel"
   by De Mille, James, 1833-1880
   re: Canada -- Fiction



Book #2259 
YES:    # 29767  ---------------
   "The Continental Monthly, Vol. 4, No. 2, August, 1863Devoted to Literature an
d National Policy"
   by Various
   re: Literature, Modern -- 19th century -- Periodicals



Book #4180 
YES:    # 29767  ---------------
   "The Continental Monthly, Vol. 4, No. 2, August, 1863Devoted to Literature an
d National Policy"
   by Various
   re: Literature, Modern -- 19th century -- Periodicals



no.
no.
no.

 */
