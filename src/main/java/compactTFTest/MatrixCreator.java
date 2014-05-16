package compactTFTest;

public class MatrixCreator {

	private int[][] matrixNet;

	
	public void createMatrixToDinic(CompactNet net){
		int size = net.getArcs().size();
		
		matrixNet = new int[size][];
		
		
		for (int i = 0; i < size; i++) {
			matrixNet[i] = new int[4];
		}
		
		int index = 0;
		for (CompactArc arc : net.getArcs()) {
			matrixNet[index][0] = arc.getSource().getId();
			matrixNet[index][1] = arc.getDestination().getId();
			matrixNet[index][2] = arc.getCapacity();
			matrixNet[index][3] = 0;
			index++;
		}
		
//		printMatrix(matrixNet);
	}
	
	public void createMatrixByCompactNet(CompactNet net) {

		
		int nodes = net.getVertexes().size();
		
		matrixNet = new int[nodes][];

		for (int i = 0; i < nodes; i++) {
			matrixNet[i] = new int[nodes];
		}
		
		for (CompactArc arc : net.getArcs()) {

			matrixNet[arc.getSource().getId()][arc.getDestination().getId()] = arc
					.getCapacity();

		}
//		printMatrix(matrixNet);
	}

	public MatrixCreator() {


		
	}

	public void printMatrix(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(" " + m[i][j]);
			}
			System.out.println();
		}
	}
	

	public int[][] getMatrixNet() {
		return matrixNet;
	}

	public void setMatrixNet(int[][] matrixNet) {
		this.matrixNet = matrixNet;
	}

	
	

}
