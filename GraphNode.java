/**
 * class used to describe a node being stored in the graph
 * 
 * @author jakenemiroff
 *
 */
public class GraphNode {
	
	/**
	 * instance variable depicting the name of the node
	 */
	private int name;
	
	/**
	 * instance variable depicting the mark of the node
	 */
	private boolean mark;
	
	/**
	 * constructor for the class, creates a node with a given name
	 * @param name
	 */
	public GraphNode(int name) {
		
		this.name = name;
		
	}

	/**
	 * method to set the mark of the node, can either be true (visited) or false (not visited)
	 * @param mark
	 */
	public void setMark(boolean mark) {
		
		this.mark = mark;
	}
	
	/**
	 * method to return the mark of a particular node
	 * @return
	 */
	public boolean getMark() {
		
		return mark;
	}
	
	/**
	 * method to return the name of a particular node
	 * @return
	 */
	public int getName() {
		
		return name;
	}	
	
}
