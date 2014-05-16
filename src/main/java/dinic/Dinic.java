package dinic;

import java.util.Vector;

/**
 * Implementation des Dinic-Algorithmus
 * 
 * @author Sebastian Stober
 * @since Jan. 2003
 */
public class Dinic {

	private Network restNet; // globale Variable fuer Rekursion
	private int layer[];
	private int flow = 0;

	// Konstruktor
	public Dinic() {
	}

	// Hauptroutine
	public Integer getMaxFlow(int numNodes, int[][] myNetDef) {
		flow = 0;
//		System.out.println("starte java.dinic");

		Network net = new Network(numNodes, myNetDef);

//		System.out.println("java.dinic initialisiert");

		int n = 1; // Zaehler der Iterationen

		do {

			// Konstruiere RestNetz

//			System.out.println("konstruiere Restnetz:");
			restNet = getRestNet(net); // O(m)
//			restNet.printEdges();

			// Schichten ermitteln -> fuehre Breitensuche durch

			layer = bfs(restNet); // O(n*m)
//			System.out.println("layer[s]=" + layer[layer.length - 1]);

			if (layer[layer.length - 1] < Integer.MAX_VALUE) { // s ist noch in
																// restNet

				// beschneidem nach Layer-ID - koennte noch besser gemacht
				// werden
				// Schleife beginnt bei 1 und endet bei n-1, da Quelle und Senke
				// ausgeschlossen

				for (int i = 1; i < layer.length - 1; i++) { // O(n)
					if (layer[i] >= layer[layer.length - 1]) {
						restNet.removeNode(i);
					}
				}

				// Fluesse suchen

				findFlow();
//				restNet.printEdges();

				// die Werte stehen jetzt in restNet und muessen in net
				// uebertragen werden

				addFlow(net, restNet); // O(m^2)

			}

//			System.out.println("Netzwerk nach dem " + n + ". Durchlauf:");
//			net.printEdges();

			n++;
		} while (layer[layer.length - 1] < Integer.MAX_VALUE); // O(n)

//		System.out.println("maxFlow = " + flow);
//		System.out.println("java.dinic beendet");
		
		return flow;
	}

	private Network getRestNet(Network N) { // O(m)

		int[] e;
		Network N2 = new Network(N.getNumNodes());

		// laufe ueber alle Kanten => O(m)
		for (int i = 0; i < N.getNumEdges(); i++) {
			e = N.getEdge(i);

			// fuer jede Kante e in N mit f(e)>0 wird Rueckkante e2 mit
			// c(e2)=f(e) eingefuegt
			if (e[3] > 0) { // Fluss > 0
				N2.addEdge(new int[] { e[1], e[0], e[3], 0 });
			}

			// fuer jede unausgelastete Kante wird eine Kante mit Restkapazitaet
			// eingefuegt
			if ((e[2] - e[3]) > 0) { // noch freie Kapazitaet
				N2.addEdge(new int[] { e[0], e[1], e[2] - e[3], 0 });
			}
		}

		return N2;
	} // Ende getRestNet()

	private int[] bfs(Network N) { // O(m*n)

		int[] l = new int[N.getNumNodes()]; // Speichert die Tiefe
		for (int i = 1; i < l.length; i++) {
			l[i] = Integer.MAX_VALUE; // maximale Entfernung fuer alle Knoten
										// ungleich der Quelle
		}
		l[0] = 0; // Quelle hat Tiefe 0
		Vector open = new Vector(); // zu ewpandierende Knoten
		Vector next; // Nachfolger
		open.addElement(new Integer(0)); // fuege quelle zur Liste hinzu
		int[] e; // Kante
		int layer = 1; // Ebenen-Zaehler
		do { // pro Schicht ein Durchlauf => O(n) im schlimmsten Fall
			next = new Vector();

			// laufe ueber alle Kanten => O(m)
			for (int i = 0; i < N.getNumEdges(); i++) {

				e = N.getEdge(i);

				if (l[e[1]] == Integer.MAX_VALUE) { // Knoten noch nicht
													// erreicht
					if (open.contains(new Integer(e[0]))) { // Startknoten soll
															// erweitert werden

						next.addElement(new Integer(e[1])); // als Nachfolger
															// merken
						l[e[1]] = layer; // und die Tiefe speichern

//						System.out.println("layer[" + e[1] + "] = " + layer);
					}
				}
			}
			open = (Vector) next.clone(); // Nachfolger fuer die naechste
											// Iteration uebergeben
			layer++; // Ebenen - Zaehler erhoehen
		} while (!next.contains(new Integer(N.getNumNodes() - 1)) // solange
																	// Senke
																	// noch
																	// nicht
																	// erreicht
				&& (next.size() > 0)); // bis keine Nachfolger mehr gefunden
										// werden
		return l;
	} // Ende bfs

	private int maxFlow; // globale Variablen fuer Rekursion
	private Vector f;

	// Startroutine fuer Rekursion zu Finden der Fluesse
	private void findFlow() {

//		System.out.println("starte findFlow");

		int i;
		boolean found; // weiterer Fluss gefunden
		int e;

		do {
			f = new Vector(); // Fluss initialisieren
			maxFlow = Integer.MAX_VALUE; // hoechster Wert -> min-Operator wird
											// spaeter verwendet
			found = rekFindFlow(0); // beginne Suche an der Quelle
			if (found) {

				for (i = 0; i < f.size(); i++) { // => O(n)
					e = ((Integer) f.elementAt(i)).intValue();
					restNet.getEdge(e)[3] += maxFlow;
				}

//				System.out.println("Fluss gefunden: maxFlow=" + maxFlow);

				flow += maxFlow;
			}
		} while (found); // bis keine Fluesse mehr gefunden werden
	}

	// Rekursion fuer FindFlow
	private boolean rekFindFlow(int v) {

		int i = 0;
		int[] e;
		do {
			e = restNet.getEdge(i++);
			if ((e[0] == v) && (layer[e[0]] < layer[e[1]]) && (e[2] - e[3] > 0)) {

				if ((e[1] == (restNet.getNumNodes() - 1)) // Senke erreicht
						|| (rekFindFlow(e[1])) // oder ueber ueber Weg
												// erreichbar
				) {

					maxFlow = Math.min(e[2] - e[3], maxFlow); // maximalen Fluss
																// bestimmen

					// f.addElement(new Integer(i-1));
					f.insertElementAt(new Integer(i - 1), 0); // Kante merken

//					System.out.print(" " + e[1]);
					return true; // Senke gefunden
				}
				// else weitersuchen
			}
		} while (i < restNet.getNumEdges());
		return false; // gesamtes Netzwerk durchsucht und Senke nicht erreicht
	} // Ende rekFindFlow

	private Network addFlow(Network N, Network N2) {
		// sehr ineffiziente Loesung - Aufwand O(m^2)

		int[] e, e2;
		for (int i = 0; i < N2.getNumEdges(); i++) {
			e = N2.getEdge(i);
			for (int j = 0; j < N.getNumEdges(); j++) {
				e2 = N.getEdge(j);
				if ((e[0] == e2[0]) && (e[1] == e2[1])) { // Vorwaertskante
					N.getEdge(j)[3] += e[3]; // -> Fluss erhoehen
				}
				if ((e[0] == e2[1]) && (e[1] == e2[0])) { // Rueckwaetskante
					N.getEdge(j)[3] -= e[3]; // -> Fluss verringern
				}
			}
		}
		return N;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	
	
}
