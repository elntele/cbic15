package br.cns.experiments.nodePositions;

/**
 * Cria posi��es dos n�s.
 * 
 * @author Danilo Ara�jo
 * 
 * @since 17/08/2012
 */
public interface NodePositionsGenerator {
	/**
	 * Cria posi��o para os n�s.
	 * 
	 * @return Array de posi��o dos n�s.
	 */
	public double[][] createNodePositions(int numNodes);
	
	/**
	 * Cria posi��o para os n�s.
	 * 
	 * @return Array de posi��o dos n�s.
	 */
	public double[][] createNodePositions(int numNodes, Integer[][] degree);
}
