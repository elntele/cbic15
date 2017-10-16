package br.cns.persistence;

import java.io.IOException;

import br.cns.experiments.ComplexNetwork;

public interface IComplexNetworkDao {
	/**
	 * Salva o objeto passado em um arquivo xml
	 * 
	 * @param xml
	 *            Path do arquivo xml a ser salvo
	 * @param object
	 *            Objeto referente � rede complexa
	 * @throws IOException
	 *             Caso ocorra algum erro no processo de serializa��o
	 */
	public void save(String xml, ComplexNetwork object) throws IOException;

	/**
	 * Carrega um objeto da classe especificada.
	 * 
	 * @param xml
	 *            Path do arquivo xml a ser salvo
	 * @return Objeto correspondente ao XML.
	 * @throws IOException
	 *             Caso ocorra algum erro no processo de deserializa��o
	 */
	public ComplexNetwork load(String xml) throws IOException;
}
