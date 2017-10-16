/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: PhysicalAveragePathLength.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	01/05/2013		Versão inicial
 * ****************************************************************************
 */
package br.cns.metrics;

import java.util.List;
import java.util.Map;

import br.cns.Metric;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.transformations.PhysicalFloydWarshall;
import br.cns.util.Dijkstra;

/**
 * 
 * @author Danilo
 * @since 01/05/2013
 */
public class PhysicalAveragePathLength implements Metric<Double> {
	private static final PhysicalAveragePathLength instance = new PhysicalAveragePathLength();

	private PhysicalAveragePathLength() {
	}

	public static PhysicalAveragePathLength getInstance() {
		return instance;
	}

	public double calculate(ComplexNetwork cn) {
		return calculate(cn.getDistances());
	}

	@Override
	public double calculate(Double[][] matrix) {
//		Double[][] shortestPath = PhysicalFloydWarshall.getInstance().transform(matrix);
		Double[][] shortestPath = new Double[matrix.length][matrix.length];

		Map<String, List<Integer>> mapDirectPaths = Dijkstra.calculateAll(matrix);

		for (int i = 0; i < matrix.length; i++) {
			shortestPath[i][i] = 0.0;
			for (int j = i + 1; j < matrix.length; j++) {
				List<Integer> rota = mapDirectPaths.get(i + "-" + j);
				shortestPath[i][j] = 0.0;
				shortestPath[j][i] = 0.0;
				for (int r = 1; r < rota.size(); r++) {
					shortestPath[i][j] += matrix[rota.get(r - 1)][rota.get(r)];
					shortestPath[j][i] = shortestPath[i][j];
				}
			}
		}
		int n = matrix.length;
		int m = 0;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (shortestPath[i][j] != 0) {
					sum += shortestPath[i][j];
					m++;
				}
			}
		}

		return sum / (m * 1.0);
	}

	@Override
	public String name() {
		return TMetric.PHYSICAL_AVERAGE_PATH_LENGTH.toString();
	}

}
