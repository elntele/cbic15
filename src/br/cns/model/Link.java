/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: Edge.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	07/10/2013		Versão inicial
 * ****************************************************************************
 */
package br.cns.model;

import java.awt.Color;

/**
 * 
 * @author Danilo
 * @since 07/10/2013
 */
public class Link {
	protected String name = ""; // Name of link
	protected int tail; // From Node #
	protected int head; // To Node #
	protected double value = 0; // Value of link
	protected Color c; // Link Color
	protected boolean selected = false;
}
