package compactTFTest;


public class PreFlowPush {
	private static final int INFINITE = 10000000;

	private int nodes;
	private int[][] flow;
	private int[][] capacities;

	public PreFlowPush(int nodes) {
		this.nodes = nodes;

		flow = new int[nodes][];

		capacities = new int[nodes][];

		for (int i = 0; i < nodes; i++) {
			flow[i] = new int[nodes];
			capacities[i] = new int[nodes];
		}
	}

	public void setCap(int x, int y, int n) {
		capacities[x][y] = n;
	}

	private void fillCapacitiesByMatrix( int[][] matrix ){
		
		for(int i = 0; i < matrix.length; i++)
		{
		  int[] aMatrix = matrix[i];
		  int   aLength = aMatrix.length;
		  capacities[i] = new int[aLength];
		  System.arraycopy(aMatrix, 0, capacities[i], 0, aLength);
		}
	}
	
	public Integer getMaxFlow( int[][] matrix ) {
		
		fillCapacitiesByMatrix(matrix);

//		System.out.println(capacities.length);
		
//		System.out.print("Capacity:\n");
//		printMatrix(capacities);

		Integer maxFlow = pushRelabel(capacities, flow, 0, capacities.length);
		
//		System.out.print("Max Flow:\n" + maxFlow
//				+ "\n");
//
//		System.out.print("Flows:\n");
//		printMatrix(flow);
		
		return maxFlow;
	}

	public int min(int a, int b) {
		if (a < b) {
			return a;
		}

		return b;
	}

	public void push(int[][] c, int[][] f, int[] excess, int u, int v) {
		int send = min(excess[u], c[u][v] - f[u][v]);

		f[u][v] += send;
		f[v][u] -= send;
		excess[u] -= send;
		excess[v] += send;
	}

	public void relabel(int[][] c, int[][] f, int[] height, int u) {
		int v;
		int min_height = INFINITE;

		for (v = 0; v < nodes; v++) {
			if (c[u][v] - f[u][v] > 0) {
				min_height = min(min_height, height[v]);
				height[u] = min_height + 1;
			}
		}
	}

	void discharge(int[][] c, int[][] f, int[] excess, int[] height,
			int[] seen, int u) {
		while (excess[u] > 0) {
			if (seen[u] < nodes) {
				int v = seen[u];

				if ((c[u][v] - f[u][v] > 0) && (height[u] > height[v])) {
					push(c, f, excess, u, v);
				} else {
					seen[u] += 1;
				}
			} else {
				relabel(c, f, height, u);
				seen[u] = 0;
			}
		}
	}

	public void moveToFront(int i, int[] a) {
		int temp = a[i];
		int n;

		for (n = i; n > 0; n--) {
			a[n] = a[n - 1];
		}

		a[0] = temp;
	}

	int pushRelabel(int[][] c, int[][] f, int source, int sink) {
		int[] excess;
		int[] height;
		int[] list;
		int[] seen;
		int i;
		int p;

		excess = new int[nodes];
		height = new int[nodes];
		seen = new int[nodes];

		list = new int[nodes];

		for (i = 0, p = 0; i < nodes; i++) {
			if ((i != source) && (i != sink)) {
				list[p] = i;
				p++;
			}
		}

		height[source] = nodes;
		excess[source] = INFINITE;

		for (i = 0; i < nodes; i++) {
			push(c, f, excess, source, i);
		}

		p = 0;

		while (p < nodes - 2) {
			int u = list[p];
			int old_height = height[u];

			discharge(c, f, excess, height, seen, u);

			if (height[u] > old_height) {
				moveToFront(p, list);
				p = 0;
			} else {
				p += 1;
			}
		}

		int maxflow = 0;

		for (i = 0; i < nodes; i++) {
			maxflow += f[source][i];
		}

		return maxflow;
	}

	public void printMatrix(int[][] m) {
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				System.out.print(" " + m[i][j]);
			}

			System.out.println();
		}
	}
}
