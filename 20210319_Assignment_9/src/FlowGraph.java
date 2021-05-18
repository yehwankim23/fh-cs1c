
// FlowGraph.java --------------------------------------------------------------
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import cs_1c.Pair;

class FlowVertex<E>
{
   public static final int KEY_ON_DATA = 0, KEY_ON_DIST = 1;
   public static final double INFINITY = Double.MAX_VALUE;

   public static Stack<Integer> keyStack = new Stack<Integer>();
   public static int keyType = KEY_ON_DATA;

   public HashSet<Pair<FlowVertex<E>, Double>> flowAdjList
         = new HashSet<Pair<FlowVertex<E>, Double>>();
   public HashSet<Pair<FlowVertex<E>, Double>> resAdjList
         = new HashSet<Pair<FlowVertex<E>, Double>>();

   public E data;
   public double dist;
   public FlowVertex<E> nextInPath;

   public FlowVertex(E x)
   {
      data = x;
      dist = INFINITY;
      nextInPath = null;
   }

   public FlowVertex()
   {
      this(null);
   }

   public void addToFlowAdjList(FlowVertex<E> neighbor, double cost)
   {
      flowAdjList.add(new Pair<FlowVertex<E>, Double>(neighbor, cost));
   }

   public void addToFlowAdjList(FlowVertex<E> neighbor, int cost)
   {
      addToFlowAdjList(neighbor, (double) cost);
   }

   public void addToResAdjList(FlowVertex<E> neighbor, double cost)
   {
      resAdjList.add(new Pair<FlowVertex<E>, Double>(neighbor, cost));
   }

   public void addToResAdjList(FlowVertex<E> neighbor, int cost)
   {
      addToResAdjList(neighbor, (double) cost);
   }

   @SuppressWarnings("unchecked")
   public boolean equals(Object rhs)
   {
      FlowVertex<E> other = (FlowVertex<E>) rhs;

      switch (keyType)
      {
      case KEY_ON_DIST:
         return dist == other.dist;
      case KEY_ON_DATA:
         return data.equals(other.data);
      default:
         return data.equals(other.data);
      }
   }

   public int hashCode()
   {
      switch (keyType)
      {
      case KEY_ON_DIST:
         Double d = dist;
         return d.hashCode();
      case KEY_ON_DATA:
         return data.hashCode();
      default:
         return data.hashCode();
      }
   }

   public void showFlowAdjList()
   {
      System.out.print("Adj Flow List for " + data + ": ");

      for (Iterator<Pair<FlowVertex<E>, Double>> iter = flowAdjList.iterator();
            iter.hasNext();)
      {
         Pair<FlowVertex<E>, Double> pair = iter.next();
         System.out.print(pair.first.data + "("
               + String.format("%3.1f", pair.second) + ") ");
      }

      System.out.println();
   }

   public void showResAdjList()
   {
      System.out.print("Adj Res List for " + data + ": ");

      for (Iterator<Pair<FlowVertex<E>, Double>> iter = resAdjList.iterator();
            iter.hasNext();)
      {
         Pair<FlowVertex<E>, Double> pair = iter.next();
         System.out.print(pair.first.data + "("
               + String.format("%3.1f", pair.second) + ") ");
      }

      System.out.println();
   }

   public static boolean setKeyType(int whichType)
   {
      switch (whichType)
      {
      case KEY_ON_DATA:
      case KEY_ON_DIST:
         keyType = whichType;
         return true;
      default:
         return false;
      }
   }

   public static void pushKeyType()
   {
      keyStack.push(keyType);
   }

   public static void popKeyType()
   {
      keyType = keyStack.pop();
   }
}

public class FlowGraph<E>
{
   protected HashSet<FlowVertex<E>> vertexSet;
   protected FlowVertex<E> startVert, endVert;

   public FlowGraph()
   {
      vertexSet = new HashSet<FlowVertex<E>>();
      startVert = endVert = null;
   }

   public boolean setStartVert(E x)
   {
      if (x == null)
      {
         return false;
      }

      startVert = getVertexWithThisData(x);

      if (startVert == null)
      {
         return false;
      }

      return true;
   }

   public boolean setEndVert(E x)
   {
      endVert = getVertexWithThisData(x);

      if (endVert == null)
      {
         return false;
      }

      return true;
   }

   public void addEdge(E source, E dest, double cost)
   {
      FlowVertex<E> src = addToVertexSet(source);
      FlowVertex<E> dst = addToVertexSet(dest);

      src.addToResAdjList(dst, cost);
      dst.addToResAdjList(src, 0.);

      src.addToFlowAdjList(dst, 0.);
   }

   public void addEdge(E source, E dest, int cost)
   {
      addEdge(source, dest, (double) cost);
   }

   public FlowVertex<E> addToVertexSet(E x)
   {
      FlowVertex.pushKeyType();
      FlowVertex.setKeyType(FlowVertex.KEY_ON_DATA);

      FlowVertex<E> retVal = new FlowVertex<E>(x);
      boolean successfulInsertion = vertexSet.add(retVal);

      if (successfulInsertion)
      {
         FlowVertex.popKeyType();
         return retVal;
      }

      for (Iterator<FlowVertex<E>> iter = vertexSet.iterator(); iter.hasNext();)
      {
         FlowVertex<E> vert = iter.next();

         if (vert.equals(retVal))
         {
            return vert;
         }
      }

      return null;
   }

   public void showFlowAdjTable()
   {
      System.out.println("------------------------ ");

      for (Iterator<FlowVertex<E>> iter = vertexSet.iterator(); iter.hasNext();)
      {
         iter.next().showFlowAdjList();
      }

      System.out.println();
   }

   public void showResAdjTable()
   {
      System.out.println("------------------------ ");

      for (Iterator<FlowVertex<E>> iter = vertexSet.iterator(); iter.hasNext();)
      {
         iter.next().showResAdjList();
      }

      System.out.println();
   }

   public void clear()
   {
      vertexSet.clear();
      startVert = endVert = null;
   }

   public double findMaxFlow()
   {
      if (startVert == null || endVert == null)
      {
         return 0;
      }

      while (establishNextFlowPath())
      {
         if (!adjustPathByCost(getLimitingFlowOnResPath()))
         {
            break;
         }
      }

      double totalFlow = 0;

      for (Iterator<Pair<FlowVertex<E>, Double>> iter
            = startVert.flowAdjList.iterator(); iter.hasNext();)
      {
         totalFlow += iter.next().second;
      }

      return totalFlow;
   }

   protected boolean establishNextFlowPath()
   {
      Deque<FlowVertex<E>> partiallyProcessedVerts
            = new LinkedList<FlowVertex<E>>();

      if (startVert == null || endVert == null)
      {
         return false;
      }

      for (Iterator<FlowVertex<E>> iter = vertexSet.iterator(); iter.hasNext();)
      {
         FlowVertex<E> vert = iter.next();
         vert.dist = FlowVertex.INFINITY;
         vert.nextInPath = null;
      }

      startVert.dist = 0;
      partiallyProcessedVerts.addLast(startVert);

      while (!partiallyProcessedVerts.isEmpty())
      {
         FlowVertex<E> v = partiallyProcessedVerts.removeFirst();

         for (Iterator<Pair<FlowVertex<E>, Double>> edgeIter
               = v.resAdjList.iterator(); edgeIter.hasNext();)
         {
            Pair<FlowVertex<E>, Double> edge = edgeIter.next();
            FlowVertex<E> w = edge.first;
            Double costVW = edge.second;

            if (costVW <= 0)
            {
               continue;
            }

            if (v.dist + costVW < w.dist)
            {
               w.dist = v.dist + costVW;
               w.nextInPath = v;

               if (w == endVert)
               {
                  return true;
               }

               partiallyProcessedVerts.addLast(w);
            }
         }
      }

      return false;
   }

   protected double getLimitingFlowOnResPath()
   {
      if (startVert == null || endVert == null)
      {
         return 0;
      }

      double limitingFlow = FlowVertex.INFINITY;

      for (FlowVertex<E> vert = endVert; vert != startVert;
            vert = vert.nextInPath)
      {
         if (vert.nextInPath == null)
         {
            return 0;
         }

         double edgeCost = getCostOfResEdge(vert.nextInPath, vert);

         if (edgeCost < limitingFlow)
         {
            limitingFlow = edgeCost;
         }
      }

      return limitingFlow;
   }

   protected double getCostOfResEdge(FlowVertex<E> src, FlowVertex<E> dst)
   {
      if (src == null || dst == null)
      {
         return 0;
      }

      for (Iterator<Pair<FlowVertex<E>, Double>> iter
            = src.resAdjList.iterator(); iter.hasNext();)
      {
         Pair<FlowVertex<E>, Double> pair = iter.next();

         if (pair.first == dst)
         {
            return pair.second;
         }
      }

      return 0;
   }

   protected boolean adjustPathByCost(double cost)
   {
      for (FlowVertex<E> vert = endVert; vert != startVert;
            vert = vert.nextInPath)
      {
         if (vert.nextInPath == null)
         {
            return false;
         }

         if (!addCostToResEdge(vert.nextInPath, vert, -cost))
         {
            return false;
         }

         if (!addCostToResEdge(vert, vert.nextInPath, cost))
         {
            return false;
         }

         if (!addCostToFlowEdge(vert.nextInPath, vert, cost))
         {
            return false;
         }
      }

      return true;
   }

   protected boolean addCostToResEdge(FlowVertex<E> src, FlowVertex<E> dst,
         double cost)
   {
      if (src == null || dst == null)
      {
         return false;
      }

      for (Iterator<Pair<FlowVertex<E>, Double>> iter
            = src.resAdjList.iterator(); iter.hasNext();)
      {
         Pair<FlowVertex<E>, Double> pair = iter.next();

         if (pair.first == dst)
         {
            pair.second += cost;
            return true;
         }
      }

      return false;
   }

   protected boolean addCostToFlowEdge(FlowVertex<E> src, FlowVertex<E> dst,
         double cost)
   {
      if (src == null || dst == null)
      {
         return false;
      }

      for (Iterator<Pair<FlowVertex<E>, Double>> iter
            = src.flowAdjList.iterator(); iter.hasNext();)
      {
         Pair<FlowVertex<E>, Double> pair = iter.next();

         if (pair.first == dst)
         {
            pair.second += cost;
            return true;
         }
      }

      for (Iterator<Pair<FlowVertex<E>, Double>> iter
            = dst.flowAdjList.iterator(); iter.hasNext();)
      {
         Pair<FlowVertex<E>, Double> pair = iter.next();

         if (pair.first == src)
         {
            pair.second -= cost;
            return true;
         }
      }

      return false;
   }

   protected FlowVertex<E> getVertexWithThisData(E x)
   {
      FlowVertex.pushKeyType();
      FlowVertex.setKeyType(FlowVertex.KEY_ON_DATA);

      FlowVertex<E> searchVert = new FlowVertex<E>(x);

      for (Iterator<FlowVertex<E>> iter = vertexSet.iterator(); iter.hasNext();)
      {
         FlowVertex<E> vert = iter.next();

         if (vert.equals(searchVert))
         {
            FlowVertex.popKeyType();
            return vert;
         }
      }

      FlowVertex.popKeyType();
      return null;
   }
}
