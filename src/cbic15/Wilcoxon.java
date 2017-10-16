/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: Wilcoxon.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	17/07/2015		Versão inicial
 * ****************************************************************************
 */
package cbic15;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest;

/**
 * 
 * @author Danilo Araujo
 * @since 17/07/2015
 */
public class Wilcoxon {

	/**
	 * Construtor da classe.
	 */
	public Wilcoxon() {
		
	}
	
	public static void main(String[] args) {
		WilcoxonSignedRankTest w = new WilcoxonSignedRankTest();
		double[] d1 = new double[] {7, 19, 8, 12, 14, 14, 17, 20, 8, 18, 14, 14, 19, 16, 19, 15, 19, 12, 12, 9, 9, 20, 15, 9, 22, 10, 17, 13, 13, 8, 17, 11, 15, 10, 8, 16, 12, 14, 9, 11, 18, 9, 14, 11, 16, 15, 8, 9, 16, 21, 12, 20, 15, 16, 19, 14, 12, 16, 19, 12, 20, 8, 8, 11, 13, 18, 13, 13, 16, 10, 21, 15, 12, 13, 14, 15, 17, 12, 14, 12, 13, 15, 17, 10, 8, 9, 8, 12, 9, 8, 15, 13, 10, 14, 15, 14, 15, 17, 16, 10};
		double[] d2 = new double[] {6, 6, 6, 7, 6, 7, 8, 6, 4, 6, 6, 6, 7, 5, 7, 5, 6, 5, 5, 4, 6, 6, 5, 4, 6, 7, 7, 8, 5, 5, 6, 5, 6, 7, 5, 6, 5, 6, 6, 6, 7, 6, 6, 6, 6, 5, 5, 5, 6, 6, 6, 5, 4, 12, 7, 6, 9, 7, 4, 4, 6, 6, 6, 6, 8, 6, 7, 4, 6, 6, 6, 7, 8, 5, 5, 4, 5, 5, 6, 7, 7, 6, 5, 7, 4, 5, 7, 7, 6, 7, 6, 6, 5, 5, 6, 5, 8, 6, 6, 6};
		double[] d3 = new double[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 5, 5, 5, 5, 4, 4, 3, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 4, 5, 5, 5, 4, 5, 5, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 5, 5, 5, 5, 5, 5, 5, 7, 7, 5, 5, 5, 5, 5, 3, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5};
		double wd = w.wilcoxonSignedRank(d2, d3);
		System.out.println(wd);
		int N = d1.length;

		final double ES = (double) (N * (N + 1)) / 4.0;
		final double VarS = ES * ((double) (2 * N + 1) / 6.0);
		final double z = (wd - ES - 0.5) / Math.sqrt(VarS);
		System.out.println(z);
		final NormalDistribution standardNormal = new NormalDistribution(0, 1);
		System.out.println(standardNormal.cumulativeProbability(z));
	}

}
