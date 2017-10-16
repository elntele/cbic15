package br.cns.util;

public class RandomUtils {
	private static RandomUtils instance = new RandomUtils();
	
	private RandomUtils(){
	}
	
	/**
	 * Retorna o valor de uma variável aleatória de uma distribuição uniforme no intervalo (min, max). 
	 * @param min Valor mínimo.
	 * @param max Valor máximo.
	 * @return Valor da variável aleatória.
	 */
	public int nextInt(int min, int max){
		return (int)(Math.round(Math.random() * (max - min) + min));
	}
	
	/**
	 * Retorna o valor de uma variável aleatória de uma distribuição uniforme no intervalo (0, max). 
	 * @param max Valor máximo.
	 * @return Valor da variável aleatória.
	 */
	public int nextInt(int max){
		return nextInt(0, max);
	}

	public static RandomUtils getInstance() {
		return instance;
	}
	
}
