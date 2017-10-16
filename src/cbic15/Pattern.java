/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: Pattern.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	19/06/2015		Versão inicial
 * ****************************************************************************
 */
package cbic15;

import java.util.Arrays;

import br.cns.experiments.ComplexNetwork;

/**
 * 
 * @author Danilo Araujo
 * @since 19/06/2015
 */
public class Pattern {
	private String name;

	private double[] variables;
	
	private ComplexNetwork cn;

	/**
	 * Construtor da classe.
	 */
	public Pattern() {
	}

	/**
	 * Construtor da classe.
	 * @param name
	 * @param variables
	 */
	public Pattern(String name, double[] variables, ComplexNetwork cn) {
		super();
		this.name = name;
		this.variables = variables;
		this.cn = cn;
	}

	public double euclidianDistance(Pattern other) {
		double distance = 0;

		for (int i = 0; i < variables.length; i++) {
			distance += (variables[i] - other.variables[i]) * (variables[i] - other.variables[i]);
		}

		return Math.sqrt(distance);
	}

	/**
	 * @return o valor do atributo name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Altera o valor do atributo name
	 * 
	 * @param name
	 *            O valor para setar em name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return o valor do atributo variables
	 */
	public double[] getVariables() {
		return variables;
	}

	/**
	 * Altera o valor do atributo variables
	 * 
	 * @param variables
	 *            O valor para setar em variables
	 */
	public void setVariables(double[] variables) {
		this.variables = variables;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(variables);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pattern other = (Pattern) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(variables, other.variables))
			return false;
		return true;
	}

	/**
	 * @return o valor do atributo cn
	 */
	public ComplexNetwork getCn() {
		return cn;
	}

	/**
	 * Altera o valor do atributo cn
	 * @param cn O valor para setar em cn
	 */
	public void setCn(ComplexNetwork cn) {
		this.cn = cn;
	}

}
