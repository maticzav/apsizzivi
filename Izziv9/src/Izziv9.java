import java.util.ArrayList;
import java.util.Scanner;

public class Izziv9 {
	static public void main(String[] args) {
		// Read the input.
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		Network net = new Network(n);

		while (sc.hasNextInt()) {
			int startNodeId = sc.nextInt();
			int endNodeId = sc.nextInt();
			int capacity = sc.nextInt();

			net.addConnection(startNodeId, endNodeId, capacity);
		}

		sc.close();

		// Find the maximum flow.
		net.fordFulkersonMaxFlow();
	}
}

class Network {
	Node[] nodes;

	/**
	 * Create a new network with n nodes (0..n-1).
	 */
	public Network(int n) {
		this.nodes = new Node[n];
		for (int i = 0; i < nodes.length; i++) {
			this.nodes[i] = new Node(i);
		}
	}

	/**
	 * Add a connection to the network, with all the corresponding in and out edges.
	 */
	public void addConnection(int startNodeId, int endNodeId, int capacity) {
		Edge e = new Edge(startNodeId, endNodeId, capacity);

		this.nodes[startNodeId].outEdges.add(e);
		this.nodes[endNodeId].inEdges.add(e);
	}

	public int fordFulkersonMaxFlow() {
		int maxFlow = 0;

		Node tNode = this.nodes[this.nodes.length - 1];

		while (this.findUnsaturatedPath()) {
			int flow = tNode.incFlow;
			maxFlow += flow;

			System.out.print(flow + ": ");

			Node node = tNode;
			while (node != null) {
				System.out.print(node.id);

				if (node.augmEdge == null) {
					// We reached the source.
					break;
				}

				if (node.id == node.augmEdge.startNodeID) {
					// We are on a negative edge.
					node.augmEdge.currentFlow -= flow;
					node = this.nodes[node.augmEdge.endNodeID];

					System.out.print("-  ");
				} else {
					// We are on a positive edge.
					node.augmEdge.currentFlow += flow;
					node = this.nodes[node.augmEdge.startNodeID];

					System.out.print("+  ");
				}
			}

			System.out.println();
		}

		return maxFlow;
	}

	/**
	 * Find an unsaturated path from the source to the sink if it exists, marking
	 * the path as we move through the network.
	 * 
	 * @return true if a path was found, false otherwise.
	 */
	private boolean findUnsaturatedPath() {
		this.resetMarks();

		Node source = this.nodes[0];

		Node temp = source;

		while (temp != null) {

			temp.visited = true;

			// Positive edges.
			for (Edge e : temp.outEdges) {
				if (e.endNodeID == 0) {
					// We don't want to go back to the source.
					continue;
				}

				Node endNode = this.nodes[e.endNodeID];

				// NOTE: We use `incFlow` to figure out whether the node has been marked.
				if (e.currentFlow < e.capacity && endNode.incFlow == -1) {
					// We found an unsaturated and unmarked edge.
					endNode.augmEdge = e;
					endNode.incFlow = infmin(e.capacity - e.currentFlow, temp.incFlow);
				}
			}

			// Negative edges.
			for (Edge e : temp.inEdges) {
				if (e.startNodeID == 0) {
					// We don't want to go back to the source.
					continue;
				}

				Node startNode = this.nodes[e.startNodeID];

				if (e.currentFlow > 0 && startNode.incFlow == -1) {
					// We found an unsaturated and unmarked edge.

					startNode.augmEdge = e;
					startNode.incFlow = infmin(e.currentFlow, temp.incFlow);
				}
			}

			// Check if we reached the target.
			if (this.nodes[this.nodes.length - 1].incFlow != -1) {
				return true;
			}

			temp = this.findMarkedUnvisited();
		}

		// We didn't find a path.
		return false;
	}

	private Node findMarkedUnvisited() {
		// NOTE: We can use the incFlow to determine whether the node has been marked.
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].incFlow >= 0 && !nodes[i].visited) {
				return nodes[i];
			}
		}
		return null;
	}

	/**
	 * Reset all the marks of the algorithm, before the start of a new iteration.
	 */
	public void resetMarks() {
		for (Node node : this.nodes) {
			node.visited = false;
			node.augmEdge = null;
			node.incFlow = -1;
		}
	}

	/// Returns the minimum of both values respecting the infinity denoted as -1.
	private static int infmin(int a, int b) {
		if (a == -1) {
			return b;
		} else if (b == -1) {
			return a;
		} else {
			return Math.min(a, b);
		}
	}
}

class Node {
	int id;

	boolean visited;

	// NOTE: We can use the augmentation edge to determine whether the edge
	// is positive or negative - edges don't change their direction after
	// initialization!
	Edge augmEdge;

	// -1 means a potentially infinite flow
	int incFlow;

	ArrayList<Edge> inEdges;
	ArrayList<Edge> outEdges;

	public Node(int id) {
		this.visited = false;
		this.augmEdge = null;
		this.incFlow = -1;

		this.id = id;

		this.inEdges = new ArrayList<Edge>();
		this.outEdges = new ArrayList<Edge>();
	}
}

class Edge {
	int startNodeID;
	int endNodeID;

	/// Maximum number of units this edge can carry.
	int capacity;

	int currentFlow;

	public Edge(int startNodeId, int endNodeId, int capacity) {
		this.startNodeID = startNodeId;
		this.endNodeID = endNodeId;
		this.capacity = capacity;

		// We start with no flow.
		this.currentFlow = 0;
	}
}