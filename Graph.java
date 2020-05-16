import java.util.LinkedList;	//import necessary libraries
import java.util.Iterator;

/**
 * class representing a graph
 * 
 * @author jakenemiroff
 *
 */
public class Graph implements GraphADT {

	/**
	 * instance variable, an array storing GraphNode objects
	 */
	private GraphNode nodeArray[];

	/**
	 * instance variable, an array storing linked lists containing GraphEdge objects
	 */
	private LinkedList<GraphEdge> adjList[];

	/**
	 * instance variable to keep track of the size of the graph
	 */
	int size;

	/**
	 * constructor for the class
	 * set the size, and build the nodeArray and adjList
	 * 
	 * @param n
	 */
	public Graph(int n) {

		this.size = n;

		nodeArray = new GraphNode[n];
		adjList = new LinkedList[n];

		for (int i = 0; i < n; i++) {

			nodeArray[i] = new GraphNode(i);

			adjList[i] = new LinkedList<GraphEdge>();

		}

	}

	/**
	 * method used to insert an edge into the adjacency list
	 */
	public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException {

		if (u.getName() >= size) {		//must make sure the node u, and the node v are in the index of nodes, otherwise throw an exception

			throw new GraphException("node u was not found in the graph");
		}

		if (v.getName() >= size) {

			throw new GraphException("node v was not found in the graph");
		}

		Iterator<GraphEdge> iter = adjList[u.getName()].iterator();	//build an iterator of all elements joined by node u

		while (iter.hasNext()) {	//loop through iterator and check if the second endpoint is equal to v, if it is throw exception

			GraphEdge edge = iter.next();

			if (edge.secondEndpoint() == v) {

				throw new GraphException("edge already joins nodes u and v");
			}
		}

		GraphEdge edgeU = new GraphEdge(u, v, busLine);	
		
		GraphEdge edgeV = new GraphEdge(v, u, busLine);	

		adjList[u.getName()].add(edgeU);	//add edge to index at u

		adjList[v.getName()].add(edgeV);	//add edge to index at v

	}

	/**
	 * method to return the node with the given name
	 */
	public GraphNode getNode(int name) throws GraphException {

		if (name >= size) {		//if the name is greater than the size of the graph, it cannot be in the graph, throw an exception

			throw new GraphException("node with given name was not found in the graph");
		}

		return nodeArray[name];		//otherwise, return the node
	}

	/**
	 * method to return an iterator of all the edges incident on node u
	 */
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {

		if (u.getName() >= size) {		//the node u must be smaller than the graph size

			throw new GraphException("node not found in graph");
		}

		Iterator<GraphEdge> iter = adjList[u.getName()].iterator();	//create the iterator

		if (iter == null) {

			return null;
		}

		return iter;
	}

	/**
	 * 
	 */
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {

		if (u.getName() >= size) {

			throw new GraphException("node u was not found in the graph");
		}

		if (v.getName() >= size) {

			throw new GraphException("node v was not found in the graph");		//nodes u and v must be in the graph
		}

		Iterator<GraphEdge> iter = adjList[u.getName()].iterator();

		while (iter.hasNext()) {	//iterate through the edges

			GraphEdge edge = iter.next();

			if (edge.secondEndpoint() == v) {

				return edge;

			}

		}

		throw new GraphException("nodes u and v not joined by edge");

	}

	/**
	 * method to check whether two given nodes are adjacent
	 */
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {

		if (u.getName() < size && v.getName() < size) {		//both nodes must be in the graph

			try {
				getEdge(u, v);	//if there is an edge between them, they are adjacent, otherwise they are not

				return true;
			} catch (GraphException e) {

				return false;

			}
		}

		throw new GraphException("nodes u and v were not found in the graph");
	}
}
