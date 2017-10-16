package br.cns.metrics;

import br.cns.Metric;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;

public class ClusteringCoefficient implements Metric<Integer> {
	private static final ClusteringCoefficient instance = new ClusteringCoefficient();

	private ClusteringCoefficient() {
	}

	public static ClusteringCoefficient getInstance() {
		return instance;
	}
	
	public double calculate(ComplexNetwork cn) {
		return calculate(cn.getAdjacencyMatrix());
	}

	@Override
	public double calculate(Integer[][] matrix) {
		double parcialCoefficient = 0.0;

		for (int i = 0; i < matrix.length; i++) {
			parcialCoefficient += localClusteringCoefficient(matrix, i);
		}

		return parcialCoefficient / matrix.length;
	}

	public double localClusteringCoefficient(Integer[][] matrix, int vertex) {
		double connections = 0.0;
		double neighborhoodConnections = 0.0;

		for (int i = 0; i < matrix.length; i++) {
			if (i == vertex) {
				continue;
			}
			if (matrix[vertex][i] == 1) {
				connections++;
				for (int j = i + 1; j < matrix.length; j++) {
					if (j == vertex) {
						continue;
					}
					if ((matrix[i][j] == 1) && (matrix[vertex][j]) == 1) {
						neighborhoodConnections++;
					}
				}
			}
		}
		if (connections == 0 || neighborhoodConnections == 0){
			return 0;
		}
		return 2.0 * neighborhoodConnections / (connections * (connections - 1));
	}

	@Override
	public String name() {
		return TMetric.CLUSTERING_COEFFICIENT.toString();
	}

}
