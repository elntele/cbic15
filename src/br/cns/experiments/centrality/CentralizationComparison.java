/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: CentralizationComparison.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	17/05/2015		Versão inicial
 * ****************************************************************************
 */
package br.cns.experiments.centrality;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.models.BarabasiDensity;
import br.cns.models.ErdosRenyiP;
import br.cns.models.TModel;
import br.cns.models.WattsStrogatzDensity;

/**
 * 
 * @author Danilo Araujo
 * @since 17/05/2015
 */
public class CentralizationComparison {

	/**
	 * Construtor da classe.
	 */
	public CentralizationComparison() {
	}

	public static void main(String[] args) {
		int numNets = 30;
		int n = 50;
		double density = 3.0 / (n - 1);
		ErdosRenyiP er = null;
		BarabasiDensity ba = null;
		WattsStrogatzDensity ws = null;
		ComplexNetwork cn = null;
		List<TMetric> metrics = new Vector<TMetric>();
		metrics.add(TMetric.DENSITY);
		metrics.add(TMetric.CONCENTRATION_ROUTES);
		metrics.add(TMetric.BETWENNESS_CENTRALIZATION);
		StringBuffer sb = new StringBuffer();

		for (int degree = 2; degree < 6; degree++) {
			density = 3.0 / (n - 1);
			System.out.println("Resultados para d = " + degree);
			double mean = 0;
			double meanCR = 0;
			for (int i = 0; i < numNets; i++) {
				er = new ErdosRenyiP(density);
				// ba = new BarabasiDensity(density);
				// ws = new WattsStrogatzDensity(0.10, (int) Math.round(density * (n
				// - 1)), density, true);
				cn = new ComplexNetwork(0, er.transform(new Integer[n][n]), new double[n][n], TModel.ERDOS_RENYI_N_P,
						metrics);
				// cn = new ComplexNetwork(0, ba.grow(null, n), new double[n][n],
				// TModel.ERDOS_RENYI_N_P,
				// metrics);
				String am = "";
				for (int j = 0; j < n; j++) {
					for (int k = j + 1; k < n; k++) {
						am += cn.getAdjacencyMatrix()[j][k] + " ";
					}
				}
				sb.append(String.format("%.4f;%.4f;%.4f;%s", cn.getMetricValues().get(TMetric.DENSITY), cn
						.getMetricValues().get(TMetric.CONCENTRATION_ROUTES),
						cn.getMetricValues().get(TMetric.BETWENNESS_CENTRALIZATION), am)).append("\n");
				mean += cn.getMetricValues().get(TMetric.BETWENNESS_CENTRALIZATION);
				meanCR += cn.getMetricValues().get(TMetric.CONCENTRATION_ROUTES);
			}
//			System.out.println(sb.toString());
			System.out.printf("Média F = %.4f \n", mean /= numNets);
			System.out.printf("Média CR = %.4f \n", meanCR /= numNets);
		}
		
//		try {
//			FileWriter fw = new FileWriter("erdos-50-0.06.txt");
//			fw.write(sb.toString());
//			fw.close();
//		} catch (IOException e) {
//		}
	}
}
