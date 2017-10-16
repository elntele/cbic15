/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: GenerativeAlgortithm.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	07/10/2013		Versão inicial
 * ****************************************************************************
 */
package br.cns.model;

/**
 * 
 * @author Danilo
 * @since 07/10/2013
 */
public interface GenerativeAlgortithm {
	public Network generate(int n, double ... parameters);
}
