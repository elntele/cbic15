package br.cns.persistence;

import java.io.IOException;

import br.cns.experiments.ResultSet;

public interface IResultSet {
	/**
	 * Salva o objeto passado em um arquivo xml
	 * 
	 * @param xml
	 *            Path do arquivo xml a ser salvo
	 * @param object
	 *            Objeto referente à rede complexa
	 * @throws IOException
	 *             Caso ocorra algum erro no processo de serialização
	 */
	public void save(String xml, ResultSet object) throws IOException;

	/**
	 * Carrega um objeto da classe especificada.
	 * 
	 * @param xml
	 *            Path do arquivo xml a ser salvo
	 * @return Objeto correspondente ao XML.
	 * @throws IOException
	 *             Caso ocorra algum erro no processo de deserialização
	 */
	public ResultSet load(String xml) throws IOException;
}
