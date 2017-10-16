package br.cns.models;

import br.cns.Transformation;

/**
 * Representa um modelo para o crescimento de redes complexas.
 * 
 * @author Danilo
 *
 */
public abstract class GenerativeProcedure implements Transformation<Integer> {
	public abstract Integer[][] transform(Integer[][] matrix);
	
	public abstract String name();

	/**
	 * Realiza a operação de transformação o número de vezes especificado na
	 * variável time.
	 * 
	 * @param matrix
	 *            Matriz de adjacências inicial.
	 * @param time
	 *            Número de vezes que a regra de formação é executada.
	 * @return Matriz obtida após o final do processo.
	 */
	public Integer[][] grow(Integer[][] matrix, int time) {
		Integer[][] result = matrix;
		for (int i = 0; i < time; i++) {
			result = transform(result);
		}
		return result;
	}
}
