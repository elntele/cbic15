package br.cns.experiments.nodePositions;

public class RandomNodePositions implements NodePositionsGenerator {
	private static final RandomNodePositions instance = new RandomNodePositions();
	
	/**
	 * Construtor da classe.
	 */
	private RandomNodePositions(){
	}
	
	@Override
	public double[][] createNodePositions(int numNodes) {
		double[][] positions = new double[numNodes][2];

		for (int i = 0; i < positions.length; i++) {
			positions[i][0] = Math.random() * (100);
			positions[i][1] = Math.random() * (100);
		}
		return positions;
	}

	/**
	 * Método acessor para obter o valor do atributo instance.
	 * @return O atributo instance
	 */
	public static RandomNodePositions getInstance() {
		return instance;
	}

	@Override
	public double[][] createNodePositions(int numNodes, Integer[][] degree) {
		return createNodePositions(numNodes);
	}

}
