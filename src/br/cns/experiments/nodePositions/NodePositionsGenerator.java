package br.cns.experiments.nodePositions;

/**
 * Cria posições dos nós.
 * 
 * @author Danilo Araújo
 * 
 * @since 17/08/2012
 */
public interface NodePositionsGenerator {
	/**
	 * Cria posição para os nós.
	 * 
	 * @return Array de posição dos nós.
	 */
	public double[][] createNodePositions(int numNodes);
	
	/**
	 * Cria posição para os nós.
	 * 
	 * @return Array de posição dos nós.
	 */
	public double[][] createNodePositions(int numNodes, Integer[][] degree);
}
