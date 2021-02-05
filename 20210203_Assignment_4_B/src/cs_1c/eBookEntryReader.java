package cs_1c;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class eBookEntryReader
{
   ArrayList<eBookEntry> books = new ArrayList<eBookEntry>();
   private int numBooks;
   private boolean fileOpenError;
   private String bookFile;

   public eBookEntryReader(String fileName)
   {
      eBookEntry book;
      BufferedReader inFile;
      String line, entryString;
      numBooks = 0;
      fileOpenError = false;
      bookFile = "NO FILE NAME PROVIDED";
      if (fileName.length() == 0)
      {
         fileOpenError = true;
         return;
      }
      bookFile = fileName;
      try
      {
         inFile = new BufferedReader(new FileReader(fileName));
         while (inFile.ready())
         {
            line = inFile.readLine();
            if (isDataLine(line))
            {
               entryString = readOneEntry(inFile, line);
               if (entryString == null)
               {
                  fileOpenError = true;
                  break;
               }
               if (!entryString.contains(">en<"))
                  continue;
               book = readOneBook(entryString);
               books.add(book);
               numBooks++;
            }
         }
         inFile.close();
      }
      catch (FileNotFoundException e)
      {
         fileOpenError = true;
      }
      catch (IOException e)
      {
         fileOpenError = true;
      }
   }

   public eBookEntry getBook(int k)
   {
      if (k < 0 || k >= numBooks)
         return new eBookEntry();
      return books.get(k);
   }

   public String getFileName()
   {
      return bookFile;
   }

   public boolean readError()
   {
      return fileOpenError;
   }

   public int getNumBooks()
   {
      return numBooks;
   }

   private String readOneEntry(BufferedReader infile, String line)
   {
      String fullEntry = line;
      String nextLine = "";
      try
      {
         while (infile.ready()
               && fullEntry.length() < eBookEntry.MAX_ENTRY_LENGTH - 20)
         {
            nextLine = infile.readLine();
            fullEntry += nextLine;
            if (nextLine.equals("</pgterms:etext>"))
               break;
         }
      }
      catch (IOException e)
      {
         return null;
      }
      if (!nextLine.equals("</pgterms:etext>"))
         fullEntry += "</pgterms:etext>";
      return fullEntry;
   }

   private eBookEntry readOneBook(String entryString)
   {
      eBookEntry book = new eBookEntry();
      book.setEtextNum(readIDFromLine(entryString));
      book.setCreator(readStringFromEntry(entryString, "<dc:creator"));
      book.setTitle(readStringFromEntry(entryString, "<dc:title"));
      book.setSubject(readStringFromEntry(entryString, "<dc:subject"));
      return book;
   }

   private boolean isDataLine(String line)
   {
      if (line.length() < 15)
         return false;
      if (line.substring(0, 14).equals("<pgterms:etext"))
         return true;
      return false;
   }

   int readIDFromLine(String line)
   {
      int startNumPos;
      int numLength;
      startNumPos = line.indexOf("ID=\"etext") + 9;
      numLength = line.substring(startNumPos).indexOf("\"");
      if (startNumPos < 0 || startNumPos > eBookEntry.MAX_STRING
            || numLength < 0 || numLength > 20)
         return 0;
      else
         return Integer
               .valueOf(line.substring(startNumPos, startNumPos + numLength));
   }

   String readStringFromEntry(String entryString, String delimiter)
   {
      int start, stop, k;
      String stringAfterDelimiter;
      if (delimiter.length() < 1 || entryString.length() < delimiter.length())
         return "(no data found)";
      start = entryString.indexOf(delimiter);
      if (start < 0)
         return "(no data found)";
      stringAfterDelimiter = entryString.substring(start + delimiter.length());
      for (k = 0; k < stringAfterDelimiter.length() - 1; k++)
      {
         if (stringAfterDelimiter.charAt(k) == '>'
               && ((stringAfterDelimiter.charAt(k + 1) >= 'a'
                     && stringAfterDelimiter.charAt(k + 1) <= 'z')
                     || (stringAfterDelimiter.charAt(k + 1) >= 'A'
                           && stringAfterDelimiter.charAt(k + 1) <= 'Z')))
            break;
      }
      if (k == stringAfterDelimiter.length() - 1)
         return "(no data found)";
      start = k + 1;
      stringAfterDelimiter = stringAfterDelimiter.substring(start);
      stop = stringAfterDelimiter.indexOf("<");
      return stringAfterDelimiter.substring(0, stop);
   }
}
