import java.util.LinkedList;
import java.util.Scanner;

public class Izziv12 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Graph g = new Graph(n);

		while (scanner.hasNext()) {
			int startNodeId = scanner.nextInt();
			int endNodeId = scanner.nextInt();
			int cost = scanner.nextInt();

			g.addEdge(startNodeId, endNodeId, cost);
		}

		scanner.close();

		boolean hasNegativeCycle = g.bellmanFord(0);
	}
}

class Graph {
	private Node[] nodes;
	private LinkedList<Edge> edges;

	public Graph(int n) {
		this.nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			this.nodes[i] = new Node(i);
		}

		this.edges = new LinkedList<Edge>();
	}

	public void addEdge(int startNodeId, int endNodeId, int cost) {
		Edge e = new Edge(startNodeId, endNodeId, cost);

		this.nodes[startNodeId].outEdges.add(e);
		this.nodes[endNodeId].inEdges.add(e);

		this.edges.add(e);
	}

	/// Calcualtes the shortest distances from a given root node to the other nodes.
	public boolean bellmanFord(int startNodeId) {
		this.reset();

		this.nodes[startNodeId].distance = 0;

		for (int h = 0; h < this.nodes.length; h++) {
			for (Edge e : this.edges) {
				Node startNode = this.nodes[e.startNodeId];
				Node endNode = this.nodes[e.endNodeId];

				// Skip edges that are not reachable yet (Inf + n = Inf).
				if (startNode.distance == -1) {
					continue;
				}

				// Relax the edge if the distance can be improved.
				if (endNode.distance == -1 || startNode.distance + e.cost < endNode.distance) {
					endNode.distance = startNode.distance + e.cost;
				}
			}

			System.out.printf("h%d: ", h);
			for (Node n : this.nodes) {
				if (n.distance != -1) {
					System.out.printf("%d ", n.distance);
				} else {
					System.out.printf("Inf ");
				}
			}
			System.out.println();
		}

		for (Edge e : this.edges) {
			Node startNode = this.nodes[e.startNodeId];
			Node endNode = this.nodes[e.endNodeId];

			if (endNode.distance > startNode.distance + e.cost) {
				return false;
			}
		}

		return true;
	}

	private void reset() {
		for (Node n : this.nodes) {
			n.distance = -1;
		}
	}
}

class Node {
	int id;
	LinkedList<Edge> inEdges;
	LinkedList<Edge> outEdges;

	/// Distance from the root node to this node.
	/// NOTE: -1 means infinity.
	int distance;

	public Node(int id) {
		this.id = id;
		this.inEdges = new LinkedList<Edge>();
		this.outEdges = new LinkedList<Edge>();

		this.distance = -1;
	}
}

class Edge {
	int startNodeId;
	int endNodeId;
	int cost;

	public Edge(int startNodeId, int endNodeId, int cost) {
		this.startNodeId = startNodeId;
		this.endNodeId = endNodeId;
		this.cost = cost;
	}
}