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
	 * Realiza a opera��o de transforma��o o n�mero de vezes especificado na
	 * vari�vel time.
	 * 
	 * @param matrix
	 *            Matriz de adjac�ncias inicial.
	 * @param time
	 *            N�mero de vezes que a regra de forma��o � executada.
	 * @return Matriz obtida ap�s o final do processo.
	 */
	public Integer[][] grow(Integer[][] matrix, int time) {
		Integer[][] result = matrix;
		for (int i = 0; i < time; i++) {
			result = transform(result);
		}
		return result;
	}
}
