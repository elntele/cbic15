/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: Vertex.java
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
public class Node {
	// Static values
	public static int xstep = 40; // Minimum size of display node
	public static int ystep = 30; // Dynamic variables
	protected String name = ""; // Name of node
	protected int degree = 0; // Degree of node
	protected int out_degree = 0; // Out Degree = # directed links out
	protected int in_degree = 0; // In Degree = # directed links in to node
	protected Color color = Color.white; // Red = infected or source; green =
											// sink
	protected int int_color = 0; // Hack to defeat Java serializer limitation
	protected int x = 0; // Location on screen
	protected int y = 0;
	protected double next_state = 0; // Next State used to Sync
	protected double value; // Working value
	protected double cluster = 0; // Cluster coefficient
	protected int level = 0; // Used to find center
	protected int radius = 0; // radius of a node
	protected boolean visited = false; // Used for spanning tree (finding
										// center)
	protected boolean center = false; // True if node is a center
	protected boolean outer = false; // True if node is outer
	protected boolean selected = false; // For mouse down events
	protected int timer = 0;
}
