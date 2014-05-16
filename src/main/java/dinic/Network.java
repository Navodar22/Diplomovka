package dinic;

import java.util.Vector;

public class Network { // Hilfsklasse Datenstruktur Netzwerk

	private Vector edges;// Kanten-Infos

	private int nodes; // Anzahl Knoten

	// create empty network
	public Network() {
		this(0);
	}

	public Network(int numNodes) {
		this.nodes = numNodes;
		this.edges = new Vector();
	}

	// create network form array
	public Network(int numNodes, int[][] edgesArray) {
		this.nodes = numNodes;
		this.edges = new Vector();
		for (int i = 0; i < edgesArray.length; i++) {
			addEdge(edgesArray[i]);
		}
	}

	public void addEdge(int[] e) {
//		System.out.println("fuege Kante hinzu: (" + e[0] + ", " + e[1] + ", "
//				+ e[2] + ", " + e[3] + ")");
		edges.addElement(e);
	}

	public int[] getEdge(int i) {
		return (int[]) edges.elementAt(i);
	}

	public int getNumNodes() {
		return nodes;
	}

	public int getNumEdges() {
		return edges.size();
	}

	public void removeNode(int n) {
//		debugOut("removeNode(" + n + ")");
		if ((n >= 0) && (n < nodes)) {
			int i = 0;
			while (i < edges.size()) {
				if ((getEdge(i)[0] == n) || (getEdge(i)[1] == n)) {
					edges.removeElementAt(i);
				}
				/*
				 * es genuegt, den Knoten zu isolieren else { if (getEdge(i)[0]
				 * > n) { getEdge(i)[0]--; } if (getEdge(i)[1] > n) {
				 * getEdge(i)[1]--; } }
				 */
				i++;
			}
			// nodes--;
		}
	}

	private void debugOut(String s) {
		boolean debug = true;
		if (debug) {
			System.out.println(s);
		}
	}

	public void printEdges() {
		int[] e;
		System.out.println(edges.size() + " Kanten:");
		for (int i = 0; i < edges.size(); i++) {
			e = getEdge(i);
			System.out.println(i + ": (" + e[0] + ", " + e[1] + ") c = " + e[2]
					+ " f = " + e[3]);
		}
	}

}
