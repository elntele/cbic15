/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE e Padtec
 * ****************************************************************************
 * Projeto: Planejador de Redes Ópticas Padtec – Módulo Kernel
 * Arquivo: DistanceLaplacianMatrix.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo		20/01/2013	Versão inicial
 * ****************************************************************************
 */
package br.cns.transformations;

import br.cns.Transformation;

/**
 * @author Danilo
 * 
 * @since 20/01/2013
 */
public class DistanceLaplacianMatrix implements Transformation<Integer> {
	private double[][] distance;
	
	public DistanceLaplacianMatrix(double[][] distance){
		this.distance = distance;
	}
	
	@Override
	public Integer[][] transform(Integer[][] matrix) {
		Integer[][] degree = DegreeMatrix.getInstance().transform(matrix);
		Integer[][] laplacian = new Integer[matrix.length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
//				laplacian[i][j] = degree[i][j] - matrix[i][j] * distance[i][k];
			}
		}

		return laplacian;
	}

}
