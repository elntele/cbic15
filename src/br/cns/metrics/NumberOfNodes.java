/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: NumberOfNodes.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	21/06/2015		Versão inicial
 * ****************************************************************************
 */
package br.cns.metrics;

import br.cns.Metric;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;

/**
 * 
 * @author Danilo Araujo
 * @since 21/06/2015
 */
public class NumberOfNodes  implements Metric<Integer> {
	/**
	 * 
	 */
	private static final NumberOfNodes instance = new NumberOfNodes();

	private NumberOfNodes() {
	}

	public static NumberOfNodes getInstance() {
		return instance;
	}

	public double calculate(ComplexNetwork cn) {
		return cn.getAdjacencyMatrix().length;
	}

	public double calculate(Integer[][] matrix) {
		return matrix.length;
	}

	@Override
	public String name() {
		return TMetric.NUMBER_OF_NODES.toString();
	}
}
