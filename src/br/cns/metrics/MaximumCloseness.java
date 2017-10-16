/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: MaximumCloseness.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	27/12/2013		Versão inicial
 * ****************************************************************************
 */
package br.cns.metrics;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import br.cns.Metric;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.util.Dijkstra;

/**
 * 
 * @author Danilo
 * @since 27/12/2013
 */
public class MaximumCloseness implements Metric<Double> {
	private static final MaximumCloseness instance = new MaximumCloseness();

	private MaximumCloseness() {
	}

	public static MaximumCloseness getInstance() {
		return instance;
	}

	public double calculate(ComplexNetwork cn) {
		return calculate(cn.getDistances());
	}

	@Override
	public double calculate(Double[][] matrix) {
		int n = matrix.length;

		Map<String, List<Integer>> mapDirectPaths = Dijkstra.calculateAll(matrix);
		List<Integer> path = null;

		double[] individualCloseness = new double[n];

		Arrays.fill(individualCloseness, 0);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				path = mapDirectPaths.get(i + "-" + j);
				if (path != null && path.size() > 2) {
					for (int k = 1; k < path.size() - 1; k++) {
						individualCloseness[path.get(k)] += 1;
					}
				}
			}
		}
		double maior = 0;
		for (double closeness : individualCloseness) {
			if (closeness > maior) {
				maior = closeness;
			}
		}
		return maior;
	}

	@Override
	public String name() {
		return TMetric.MAXIMUM_CLOSENESS.toString();
	}

}
