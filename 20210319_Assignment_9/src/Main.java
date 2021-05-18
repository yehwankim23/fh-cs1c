
// Assignment 9 - The Maximum Flow Problem
// A Flow Graph Algorithm

// Main.java -------------------------------------------------------------------
public class Main
{
   // Proof of correctness
   public static void main(String[] args)
   {
      FlowGraph<String> myG = new FlowGraph<String>();

      myG.addEdge("s", "h", 5);
      myG.addEdge("h", "i", 5);
      myG.addEdge("i", "b", 5);
      myG.addEdge("a", "b", 5);
      myG.addEdge("s", "a", 5);
      myG.addEdge("s", "c", 5);
      myG.addEdge("s", "d", 5);
      myG.addEdge("a", "e", 5);
      myG.addEdge("e", "j", 5);
      myG.addEdge("j", "t", 5);
      myG.addEdge("d", "g", 5);
      myG.addEdge("g", "t", 5);
      myG.addEdge("b", "t", 5);
      myG.addEdge("c", "t", 5);
      myG.addEdge("d", "c", 5);

      myG.showResAdjTable();
      myG.showFlowAdjTable();

      myG.setStartVert("s");
      myG.setEndVert("t");

      double finalFlow = myG.findMaxFlow();
      System.out.println("Final flow: " + finalFlow + "\n");

      myG.showResAdjTable();
      myG.showFlowAdjTable();
   }
}

// @formatter:off (Eclipse formatter tag)

/* Proof of correctness
------------------------ 
Adj Res List for a: b(5.0) s(0.0) e(5.0) 
Adj Res List for b: a(0.0) t(5.0) i(0.0) 
Adj Res List for s: a(5.0) c(5.0) d(5.0) h(5.0) 
Adj Res List for c: s(0.0) t(5.0) d(0.0) 
Adj Res List for d: s(0.0) c(5.0) g(5.0) 
Adj Res List for t: b(0.0) c(0.0) g(0.0) j(0.0) 
Adj Res List for e: a(0.0) j(5.0) 
Adj Res List for g: d(0.0) t(5.0) 
Adj Res List for h: s(0.0) i(5.0) 
Adj Res List for i: b(5.0) h(0.0) 
Adj Res List for j: t(5.0) e(0.0) 

------------------------ 
Adj Flow List for a: b(0.0) e(0.0) 
Adj Flow List for b: t(0.0) 
Adj Flow List for s: a(0.0) c(0.0) d(0.0) h(0.0) 
Adj Flow List for c: t(0.0) 
Adj Flow List for d: c(0.0) g(0.0) 
Adj Flow List for t: 
Adj Flow List for e: j(0.0) 
Adj Flow List for g: t(0.0) 
Adj Flow List for h: i(0.0) 
Adj Flow List for i: b(0.0) 
Adj Flow List for j: t(0.0) 

Final flow: 20.0

------------------------ 
Adj Res List for a: b(5.0) s(5.0) e(0.0) 
Adj Res List for b: a(0.0) t(0.0) i(5.0) 
Adj Res List for s: a(0.0) c(0.0) d(0.0) h(0.0) 
Adj Res List for c: s(5.0) t(0.0) d(0.0) 
Adj Res List for d: s(5.0) c(5.0) g(0.0) 
Adj Res List for t: b(5.0) c(5.0) g(5.0) j(5.0) 
Adj Res List for e: a(5.0) j(0.0) 
Adj Res List for g: d(5.0) t(0.0) 
Adj Res List for h: s(5.0) i(0.0) 
Adj Res List for i: b(0.0) h(5.0) 
Adj Res List for j: t(0.0) e(5.0) 

------------------------ 
Adj Flow List for a: b(0.0) e(5.0) 
Adj Flow List for b: t(5.0) 
Adj Flow List for s: a(5.0) c(5.0) d(5.0) h(5.0) 
Adj Flow List for c: t(5.0) 
Adj Flow List for d: c(0.0) g(5.0) 
Adj Flow List for t: 
Adj Flow List for e: j(5.0) 
Adj Flow List for g: t(5.0) 
Adj Flow List for h: i(5.0) 
Adj Flow List for i: b(5.0) 
Adj Flow List for j: t(5.0) 


 */
