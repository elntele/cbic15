/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: Geometric.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	05/11/2014		Versão inicial
 * ****************************************************************************
 */
package br.cns.models;

import br.cns.Geolocation;

/**
 * 
 * @author Danilo Araujo
 * @since 05/11/2014
 */
public class Geometric extends GeoGenerativeProcedure {
	private double dTh;
	
	/**
	 * Construtor da classe.
	 * @param geoCoords
	 */
	public Geometric(Geolocation[] geoCoords, double dTh) {
		super(geoCoords);
		this.dTh = dTh;
	}
	
	/* (non-Javadoc)
	 * @see br.cns.models.GeoGenerativeProcedure#transform(java.lang.Integer[][])
	 */
	@Override
	public Integer[][] transform(Integer[][] matrix) {
		Integer[][] newMatrix = new Integer[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			newMatrix[i][i] = 0;
			for (int j = i + 1; j < matrix.length; j++) {
				if (distances[i][j] < dTh) {
					newMatrix[i][j] = 1;
					newMatrix[j][i] = 1;
				} else {
					newMatrix[i][j] = 0;
					newMatrix[j][i] = 0;
				}
			}
		}
		return newMatrix;
	}

	/**
	 * @return o valor do atributo dTh
	 */
	public double getdTh() {
		return dTh;
	}

	/**
	 * Altera o valor do atributo dTh
	 * @param dTh O valor para setar em dTh
	 */
	public void setdTh(double dTh) {
		this.dTh = dTh;
	}
}
