/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: GeoLocation.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	05/11/2014		Versão inicial
 * ****************************************************************************
 */
package br.cns;

/**
 * 
 * @author Danilo Araujo
 * @since 05/11/2014
 */
public class Geolocation {
	private double latitude;

	private double longitude;

	/**
	 * Construtor da classe.
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public Geolocation(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Construtor da classe.
	 */
	public Geolocation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return o valor do atributo latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Altera o valor do atributo latitude
	 * 
	 * @param latitude
	 *            O valor para setar em latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return o valor do atributo longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Altera o valor do atributo longitude
	 * 
	 * @param longitude
	 *            O valor para setar em longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
