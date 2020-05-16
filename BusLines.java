import java.io.BufferedReader; //import require libraries
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Stack;

/**
 * class to model the possible paths based on the graph
 * 
 * @author jakenemiroff
 *
 */
public class BusLines {

	/**
	 * instance variable of the graph
	 */
	Graph graph;

	/**
	 * instance variables width, length, and buschanges read from the input file
	 */
	int width, length, busChanges;

	/**
	 * starting and ending node to be stored for building the path later
	 */
	GraphNode start, end;

	/**
	 * the stack of GraphNode objects which will be used to store the path
	 */
	Stack<GraphNode> stack = new Stack<GraphNode>();

	/**
	 * constructor of the class will read from a file
	 * 
	 * @param inputFile
	 * @throws MapException
	 * @throws IOException
	 */
	public BusLines(String inputFile) throws MapException, IOException {

		BufferedReader in = new BufferedReader(new FileReader(inputFile));

		String line = in.readLine();

		int c = 0;

		StringTokenizer token = new StringTokenizer(line, " ");

		token.nextToken();

		width = Integer.parseInt(token.nextToken());

		length = Integer.parseInt(token.nextToken());

		busChanges = Integer.parseInt(token.nextToken()); // read width length and busChanges from file, make integer
															// values

		graph = new Graph(length * width); // initialize the graph with size length * width

		String line1 = "";
		String line2 = "";

		in.mark(1); // mark the first line

		int stringLength = in.readLine().length(); // get the length of the strings, needed for checking each character
													// for bus lines

		in.reset(); // reset, go back to the mark (the first line) so as not to skip anything

		while (line1 != null || line2 != null) {

			line1 = in.readLine();

			line2 = in.readLine(); // read the two lines until they are null

			for (int i = 0; i < stringLength; i++) { // loop through each line,

				if (i % 2 == 0 && line1 != null) { // if i is even, check if the node is the starting or ending node

					if (line1.charAt(i) == 'S') {

						try {

							start = graph.getNode(c);

						} catch (GraphException e) {

							System.out.println(e.getMessage());
						}
					}

					if (line1.charAt(i) == 'D') {

						try {

							end = graph.getNode(c);

						} catch (GraphException e) {

							System.out.println(e.getMessage());
						}
					}

					if (line2 != null && line2.charAt(i) != ' ') { // add an edge between node c and node c + width

						try {

							GraphNode u = graph.getNode(c);

							GraphNode v = graph.getNode(c + width);

							graph.insertEdge(u, v, line2.charAt(i));

						} catch (GraphException e) {

							System.out.println(e.getMessage());
						}
					}

					c++; // increment counter
				}

				if (i % 2 != 0 && line1 != null) { // if i is odd, and not null

					if (line1.charAt(i) != ' ' && line1.charAt(i) != '.') { // check for an edge

						try {

							GraphNode u = graph.getNode(c - 1);

							GraphNode v = graph.getNode(c);

							graph.insertEdge(u, v, line1.charAt(i)); // insert an edge if the nodes are found in the
																		// graph

						} catch (GraphException e) {

							System.out.println(e.getMessage());
						}
					}
				}
			}
		}

		in.close(); // close file
	}

	/**
	 * method to return the graph
	 * 
	 * @return
	 * @throws MapException
	 */
	public Graph getGraph() throws MapException {

		if (graph != null) { // if the graph is not null, return it, otherwise throw a MapException

			return graph;
		}

		throw new MapException("map could not be created");

	}

	/*
	 * Returns a Java Iterator containing the nodes along the path from the starting
	 * point to the destination, if such a path exists. If the path does not exist,
	 * this method returns the value null.
	 */
	public Iterator<GraphNode> trip() throws MapException {

		if (graph == null) { // if there is no map throw an exception

			throw new MapException("Graph could not be created when bus lines was initialized!");
		}

		LinkedList<GraphNode> list = new LinkedList<GraphNode>(); // initialize a list

		if (buildPath(start, end, busChanges, ' ', list)) { // call helper method

			return list.iterator(); // return iterator of the linked list
		}

		return null;
	}

	/**
	 * Helper method used to build the path that the bus can follow. 
	 * Takes into account number of available bus changes.
	 * 
	 * @param current
	 * @param end
	 * @param changesLeft
	 * @param currentLine
	 * @param list
	 * @return
	 */
	private boolean buildPath(GraphNode current, GraphNode end, int changesLeft, char currentLine,LinkedList<GraphNode> list) {

		current.setMark(true); // mark the current node

		if (current.getName() == end.getName()) { // if the current node is the end destination

			list.add(current); // add it to the list then return true

			return true;
		}

		try {

			Iterator<GraphEdge> iter = graph.incidentEdges(current); // get the incident edges

			while (iter.hasNext()) { // loop through all the edge (or find the path first)

				boolean change = false; // set boolean change to false

				GraphEdge edge = iter.next(); // get the incident edge

				GraphNode node = edge.secondEndpoint(); // get the node at the other end of the edge

				char newBusLine = edge.getBusLine(); // get the busLine of the edge

				// if the current line is not a white space and the new line is not the same as
				// the current line, set boolean change to true
				if (currentLine != ' ' && currentLine != newBusLine) {

					change = true;
				}

				if (!node.getMark()) { // if node is not marked

					int newChangesLeft = changesLeft; // set new changes to current changes

					if (change) { // if change is true

						newChangesLeft--; // decrement the new changes by 1
					}

					if (newChangesLeft >= 0) { // if the new changes is still greater than -1 path find

						// if pathFinder returns true
						if (buildPath(node, end, newChangesLeft, newBusLine, list)) {

							list.add(current); // add the current node to the list and return true

							return true;
						}
					}
				}
			}
		}

		catch (GraphException e) {

			System.out.println(e.getMessage());
		}

		current.setMark(false); // set current node to false and return false

		return false;
	}
}
