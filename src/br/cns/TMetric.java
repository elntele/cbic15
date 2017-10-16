package br.cns;

import java.util.List;
import java.util.Vector;

public enum TMetric {
	DIAMETER("Di�metro", "diam($G$)"),
	PHYSICAL_DIAMETER("Di�metro f�sico", "Di�metro f�sico", false), 
	PHYSICAL_DENSITY("Densidade por dist�ncia f�sica", "Densidade por dist�ncia f�sica", false), 
	CLUSTERING_COEFFICIENT("Coeficiente de agrupamento", "$CC$"), 
	AVERAGE_DEGREE("Grau m�dio", "$\\overline{d}$"), 
	DENSITY("Densidade", "Densidade"), 
	SPECTRAL_RADIUS("Raio espectral", "Raio espectral"), 
	ALGEBRAIC_CONNECTIVITY("Conectividade alg�brica", "Con. alg�brica"), 
	NATURAL_CONNECTIVITY("Conectividade natural", "Con. natural"), 
	AVERAGE_PATH_LENGTH("Comprimento m�dio de caminho", "$\\overline{c}$"), 
	PHYSICAL_AVERAGE_PATH_LENGTH("Comprimento m�dio de caminho f�sico", "$\\overline{c},km$", false),  
	PHYSICAL_AVERAGE_PATH_LENGTH_SD("Desvio do APL f�sico", "SD APL", false), 
	ENTROPY("Entropia", "$I(G)$"), 
	LINK_EFFICIENCY("Efici�ncia de arestas", "Efici�ncia"), 
	BLOCKING_PROBABILITY("Probabilidade de Bloqueio", "PB"), 
	COST("Custo", "Custo"), 
	EIGENVALUES_SUM("Soma de Autovalores", "Soma de Autovalores"), 
	RESILIENCE("Resili�ncia", "Resili�ncia"), 
//	NODE_REMOVAL_RESILIENCE("Resili�ncia por remo��o de n�", "NRS"), 
//	EDGE_REMOVAL_RESILIENCE("Resili�ncia por remo��o de aresta", "ERS"), 
//	N_RESILIENCE_NODE_REMOVEL_INDEX("Resili�ncia por remo��o de n� 1", "N-RNRI"), 
//	N_RESILIENCE_EDGE_REMOVEL_INDEX("Resili�ncia por remo��o de aresta 1", "N-RERI"),
	SINGLE_FAILURE_IMPACT("Single Failure Impact", "SFI"),
	DOUBLE_FAILURE_IMPACT("Double Failure Impact", "DFI"),
	ZERO_RETURN_COUNT("Zero Return Count", "ZRC"),
	ZERO_RETURN_COUNT_FILTER("Zero Return Count with Filter", "ZRCF"),
	NORMALIZED_DFT_LAPLACIAN_ENTROPY("Norm. DFT Laplacian Entropy", "NTLE"),
	DFT_LAPLACIAN_ENTROPY("DFT Laplacian Entropy", "$\\mathcal{I(F)}$"),
	PHYSICAL_DFT_LAPLACIAN_ENTROPY("Physical DFT Laplacian Entropy", "PTLE", false),
	LAPLACIAN_ENTROPY("Laplacian Entropy", "LE"),
	HUB_DEGREE("Hub degree", "Hub degree"),
	CLOSENESS_ENTROPY("Closeness por hops", "Closeness por hops"),
	BETWENNESS_CENTRALIZATION("Centraliza��o por intermedia��o", "Centraliza��o por intermedia��o"),
	CONCENTRATION_ROUTES("Concentra��o de rotas", "$CR$"),
	CLOSENESS_ENTROPY_PHYSICAL("Closeness por dist�ncia", "Closeness por dist�ncia", false),
	MAXIMUM_CLOSENESS("Closeness m�ximo", "Closeness m�ximo", false),
	ASSORTATIVITY("Assortatividade", "Assortatividade"), 
	CLUSTERING_COEFFICIENT_ENTROPY("Entropia do coeficiente de agrupamento", "Entropia do coef. agrupamento"), 
	NUMBER_OF_COMPONENTS("N�mero de componentes", "N�mero de componentes"),
	NUMBER_OF_NODES("N�mero de n�s", "$n$") ;
	
	private String description;
	
	private String shortDescription;
	
	private boolean adjacencyMatrix;
	
	public static List<TMetric> getDefaults(){
		List<TMetric> metrics = new Vector<>();
		metrics.add(ALGEBRAIC_CONNECTIVITY);
		metrics.add(AVERAGE_DEGREE);
		metrics.add(AVERAGE_PATH_LENGTH);
		metrics.add(CLUSTERING_COEFFICIENT);
		metrics.add(DENSITY);
		metrics.add(DFT_LAPLACIAN_ENTROPY);
		metrics.add(DIAMETER);
		metrics.add(ENTROPY);
		metrics.add(HUB_DEGREE);
		metrics.add(LINK_EFFICIENCY);
		metrics.add(ASSORTATIVITY);
		
		return metrics;
	}
	
	public static List<TMetric> getDefaultsGeo(){
		List<TMetric> metrics = new Vector<>();
		metrics.add(ALGEBRAIC_CONNECTIVITY);
		metrics.add(AVERAGE_DEGREE);
		metrics.add(AVERAGE_PATH_LENGTH);
		metrics.add(CLUSTERING_COEFFICIENT);
		metrics.add(DENSITY);
		metrics.add(DFT_LAPLACIAN_ENTROPY);
		metrics.add(DIAMETER);
		metrics.add(ENTROPY);
		metrics.add(HUB_DEGREE);
		metrics.add(LINK_EFFICIENCY);
		metrics.add(ASSORTATIVITY);
		metrics.add(PHYSICAL_AVERAGE_PATH_LENGTH);
		metrics.add(TMetric.PHYSICAL_DIAMETER);
		
		return metrics;
	}
	
	private TMetric(String description, String shortDescription, boolean adjacencyMatrix){
		this.description = description;
		this.shortDescription = shortDescription;
		this.adjacencyMatrix = adjacencyMatrix;  
	}
	
	private TMetric(String description, String shortDescription){
		this.description = description;
		this.shortDescription = shortDescription;
		this.adjacencyMatrix = true;  
	}
	
	@Override
	public String toString() {
		return description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @return o valor do atributo adjacencyMatrix
	 */
	public boolean isAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	/**
	 * Altera o valor do atributo adjacencyMatrix
	 * @param adjacencyMatrix O valor para setar em adjacencyMatrix
	 */
	public void setAdjacencyMatrix(boolean adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}
}
