/**
 * class used to describe a edge between two nodes being stored in the graph 
 * 
 * @author jakenemiroff
 *
 */
public class GraphEdge {
	
	/**
	 * instance variable describing the first endppint of the edge
	 */
	private GraphNode u;
	
	/**
	 * instance variable describing the second endpoint of the edge
	 */
	private GraphNode v;
	
	/**
	 * instance variable to keep track of the bus line
	 */
	private char busLine;

	/**
	 * constructor for the class, create an edge between the two given nodes with the given bus line 
	 * 
	 * @param u
	 * @param v
	 * @param busLine
	 */
	public GraphEdge(GraphNode u, GraphNode v, char busLine) {
		
		this.u = u;
		
		this.v = v;
		
		this.busLine = busLine;
		
		
	}
	
	/**
	 * method to return the first endpoint of a given edge
	 * 
	 * @return
	 */
	public GraphNode firstEndpoint() {
		
		return u;		
		
	}
	
	/**
	 * method to return the second endpoint of a given edge
	 * 
	 * @return
	 */
	public GraphNode secondEndpoint() {
		
		return v;
		
	}
	
	/**
	 * method to return the bus line of a given edge
	 * 
	 * @return
	 */
	public char getBusLine() {
		
		return busLine;		
		
	}
}
