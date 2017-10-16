/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: PhysicalDiameter.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	01/05/2013		Versão inicial
 * ****************************************************************************
 */
package br.cns.metrics;

import br.cns.Metric;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.transformations.PhysicalFloydWarshall;

/**
 * 
 * @author Danilo
 * @since 01/05/2013
 */
public class PhysicalDiameter implements Metric<Double> {
	private static final PhysicalDiameter instance = new PhysicalDiameter();

	private PhysicalDiameter() {
	}

	public static PhysicalDiameter getInstance() {
		return instance;
	}
	
	public double calculate(ComplexNetwork cn) {
		return calculate(cn.getDistances());
	}
	
	@Override
	public double calculate(Double[][] matrix) {
		Double[][] shortestPath = PhysicalFloydWarshall.getInstance().transform(matrix);
		int n = matrix.length;
		double maior = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (shortestPath[i][j] > maior) {
					maior = shortestPath[i][j];
				}
			}
		}
		return maior;
	}

	@Override
	public String name() {
		return TMetric.PHYSICAL_DIAMETER.toString();
	}

}
