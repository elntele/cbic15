/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: LinkClosenessSequence.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	28/12/2013		Versão inicial
 * ****************************************************************************
 */
package br.cns.transformations;

import java.util.List;
import java.util.Map;

import br.cns.Transformation;
import br.cns.util.Dijkstra;

/**
 * 
 * @author Danilo
 * @since 28/12/2013
 */
public class LinkClosenessSequence implements Transformation<Double> {
	private static final LinkClosenessSequence instance = new LinkClosenessSequence();

	private LinkClosenessSequence() {
	}

	public static LinkClosenessSequence getInstance() {
		return instance;
	}

	@Override
	public Double[][] transform(Double[][] matrix) {
		int n = matrix.length;

		Map<String, List<Integer>> mapDirectPaths = Dijkstra.calculateAll(matrix);
		List<Integer> path = null;

		Double[][] linkCloseness = new Double[n][n];
		
		for (int i = 0; i < n; i++) {
			linkCloseness[i][i] = 0.0;
			for (int j = i + 1; j < n; j++) {
				linkCloseness[i][j] = 0.0;
				linkCloseness[j][i] = 0.0;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				path = mapDirectPaths.get(i + "-" + j);
				if (path != null && path.size() >= 2) {
					for (int k = 0; k < path.size() - 1; k++) {
						linkCloseness[path.get(k)][path.get(k + 1)] += 1;
						linkCloseness[path.get(k + 1)][path.get(k)] += 1;
					}
				}
			}
		}
		return linkCloseness;
	}

}
