import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * File Name: GraphBuilder.java
 * 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2021
 */

class GraphBuilder{
	private Graph g ;
	//You can have any number of private variables
	
	GraphBuilder(Graph g, String f) {
		this.g = g ;
		buildGraph(f) ;
	}

	private void buildGraph(String f) {
		System.out.println("Building Graph from file " + f);
		int numlines = 0 ;
		GraphType.Type t = g.type ;
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			StringBuilder sb = new StringBuilder(); 
			String lastline = null ;
			int notreadlines = 0 ;
			while (true) {
				String line1 = br.readLine();
				if (line1 == null) {
					//System.out.println("lastline = " + lastline) ;
					//System.out.println("notreadlines = " + notreadlines) ;
					break ;
				}else {
					// 956468   9 0.00202841
					//Eat both leading and trailing spaces using trim
					String line = line1.trim();
					//There may be more than one space
					//956468   9 0.00202841
					lastline = line;
					String delims = "[ ]+";
					String[] s = line.split(delims);
					int l = s.length;
					if (l == 3) {
						++numlines;
						g.u.myassert((t == GraphType.Type.WEIGHTED_UNDIRECTED) || (t == GraphType.Type.WEIGHTED_DIRECTED));
					} else if (l == 2) {
						++numlines;
						g.u.myassert((t == GraphType.Type.UNDIRECTED) || (t == GraphType.Type.DIRECTED));
					} else {
						//System.out.println("l = " + l + " line = " + line) ;
						++notreadlines;
					}

					/******************************************************************
					 WRITE YOUR CODE BELOW
					 ******************************************************************/
					if (l == 2 || l == 3) {
						double we = 0;
						int num1 = g.io.insertOrFind(s[0], false);
						int num2 = g.io.insertOrFind(s[1], false);
						if (num1 != num2) {
							Node n1 = g.buildNodeIfNotExistAndAppend(num1);
							Node n2 = g.buildNodeIfNotExistAndAppend(num2);
							if (l == 3)
								we = Double.parseDouble(s[2]);
							//create the edges of fanin and fanout
							g.createEdge(n1, n2, we, true);
							g.createEdge(n2, n1, we, false);
							if ((t == GraphType.Type.UNDIRECTED) || (t == GraphType.Type.WEIGHTED_UNDIRECTED)) {
								//double the edge
								g.createEdge(n2, n1, we, true);
								g.createEdge(n1, n2, we, false);
							}
						}
					}

				}
			}	
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}