import java.util.HashMap;

/**
 * File Name: GraphDump.java
 * 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphDump{
	private Graph g ;
	private String name;
	//You can have any number of private variables
	
	GraphDump(Graph g, String n) {
		this.g = g ;
		this.name = n ;
		dump() ;
	}
	
	/******************************************************************
  									WRITE YOUR CODE BELOW
	******************************************************************/
	private void dump() {
		System.out.println("------------ " + name + " ------------");
		System.out.println("GraphType: " + g.type);
		System.out.println("Num Vertices = " + g.numV());
		System.out.println("Num Edges = " + g.numE());
		for (Node n : g.nodes){
			System.out.print("\n" + g.io.getRealName(n.num) + "  Fanouts: ");
			if(n.fanout.size() == 0)
				System.out.print("None");
			else{
				for(Edge e: n.fanout.values())
					System.out.print(g.io.getRealName(e.other) + ", ");}


			System.out.print("\n" + g.io.getRealName(n.num) + "  Fanins: ");
			if(n.fanin.size() == 0)
				System.out.print("None");
			else{
				for(Edge E : n.fanin.values())
					System.out.print(g.io.getRealName(E.other) + ", ");}
			System.out.println("");
		}
	}
	

}