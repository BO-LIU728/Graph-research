import java.io.FileWriter;
import java.io.IOException;

/**
 * File Name: GraphDot.java
 * 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphDot{
	private Graph g ;
	private String fname;
	//You can have any number of private variables
	
	GraphDot(Graph g, String n) {
		this.g = g ;
		this.fname = n ;
		dump() ;
	}
	
	/******************************************************************
									WRITE YOUR CODE BELOW
	 ******************************************************************/
	private void dump() {
		System.out.println("See dot file at " + fname) ;
		try {
			FileWriter filewriter = new FileWriter(fname);
			filewriter.write("## Bo Liu ##\n");
			filewriter.write("## dot -Tpdf" + fname + " -o " + fname + ".pdf\n");
			filewriter.write("digraph g { \n");
			if (g.type == GraphType.Type.UNDIRECTED || g.type == GraphType.Type.WEIGHTED_UNDIRECTED)
				filewriter.write("edge [dir=none, color=red]\n");
			else
				filewriter.write("edge [color=red]\n");
			for(Node n : g.nodes){
				for(Edge e : n.fanout.values()){
					double w = e.cost;
					if((g.type == GraphType.Type.WEIGHTED_UNDIRECTED) || (g.type == GraphType.Type.WEIGHTED_DIRECTED)) {
						if((n.num < e.other)||(g.type == GraphType.Type.WEIGHTED_DIRECTED))
							filewriter.write(" " + g.io.getRealName(n.num) + "->" + g.io.getRealName(e.other) + "[label = " + w + "]");
					}
					else{
						if((n.num < e.other)||(g.type == GraphType.Type.DIRECTED))
							filewriter.write(" " + g.io.getRealName(n.num) + "->" + g.io.getRealName(e.other));
					}

				}
			}
			filewriter.write("}\n");
			filewriter.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}