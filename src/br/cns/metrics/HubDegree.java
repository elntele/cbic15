/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: HubDegree.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	05/09/2013		Versão inicial
 * ****************************************************************************
 */
package br.cns.metrics;

import br.cns.Metric;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.transformations.DegreeMatrix;

/**
 * 
 * @author Danilo
 * @since 05/09/2013
 */
public class HubDegree implements Metric<Integer> {

	private static final HubDegree instance = new HubDegree();

	private HubDegree() {
	}

	public static HubDegree getInstance() {
		return instance;
	}
	
	public double calculate(ComplexNetwork cn) {
		Integer[][] degree = cn.getDegreeMatrix();
		if (cn.getDegreeMatrix() == null) {
			degree = DegreeMatrix.getInstance().transform(cn.getAdjacencyMatrix());
			cn.setDegreeMatrix(degree);
		}
		double maior = 0;

		for (int i = 0; i < degree.length; i++) {
			if (degree[i][i] > maior) {
				maior = degree[i][i];
			}
		}

		return maior;
	}

	@Override
	public double calculate(Integer[][] matrix) {
		Integer[][] degree = DegreeMatrix.getInstance().transform(matrix);
		double maior = 0;

		for (int i = 0; i < degree.length; i++) {
			if (degree[i][i] > maior) {
				maior = degree[i][i];
			}
		}

		return maior;
	}

	@Override
	public String name() {
		return TMetric.HUB_DEGREE.toString();
	}

}
