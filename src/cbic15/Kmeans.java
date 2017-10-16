/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: Kmeans.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	19/06/2015		Versão inicial
 * ****************************************************************************
 */
package cbic15;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Danilo Araujo
 * @since 19/06/2015
 */
public class Kmeans {
	private int k;

	private List<Pattern> patterns;

	private Pattern[] centroids;// declarando mas sem iniciar um array do tipo
								// Pattern
	private List <Integer> lisItaration = new ArrayList<>();
	

	/**
	 * Construtor da classe.
	 */
	public Kmeans(int k, List<Pattern> patterns) {
		this.k = k;
		this.patterns = patterns;
	}

	public Pattern[] getNearestPatternsFromCentroid() {
		Pattern[] nearestPatterns = new Pattern[centroids.length];

		int i = 0;
		for (Pattern c : centroids) {
			double minDistance = Double.MAX_VALUE;
			for (Pattern p : patterns) {
				double d = p.euclidianDistance(c);
				if (d < minDistance) {
					nearestPatterns[i] = p;
					minDistance = d;
				}
			}
			i++;
		}

		return nearestPatterns;
	}

	public List<Pattern>[] execute() {
		return execute(10000);
	}

	public double getSilhouetteIndex(List<Pattern>[] clusters) {
		double gs = 0;
		int nonEmptyClusters = 0;
		for (int i = 0; i < clusters.length; i++) {
			if (clusters[i].isEmpty()) {
				continue;
			}
			nonEmptyClusters++;
			double cs = 0;
			for (int x = 0; x < clusters[i].size(); x++) {
				double ax = 0;
				for (int o = 0; o < clusters[i].size(); o++) {
					if (x != o) {
						ax += clusters[i].get(x).euclidianDistance(clusters[i].get(o));
					}
				}
				if (clusters[i].size() > 1) {
					ax /= clusters[i].size() - 1;// (danilo) para clusetes com
													// size =1, havera uma div
													// por 0, pois 1-1=0...
					double bxmin = Double.MAX_VALUE;
					for (int j = 0; j < clusters.length; j++) {
						if (j != i && !clusters[j].isEmpty()) {
							double bx = 0;
							for (int o = 0; o < clusters[j].size(); o++) {
								bx += clusters[i].get(x).euclidianDistance(clusters[j].get(o));
							}
							bx /= clusters[j].size();
							if (bx < bxmin) {
								bxmin = bx;
							}
						}
					}
					cs += (bxmin - ax) / Math.max(ax, bxmin);
				}
			}

			cs /= clusters[i].size();
			gs += cs;
		}

		return gs / nonEmptyClusters;
	}

	// esse parece ser o método que preenche o atributo array centroids
	// e retorna um array de clusters
	public List<Pattern>[] execute(int maxIter) {
		List<Pattern>[] clustters = new List[k];// o grupo de clustes será um
												// array de lista do tipo
												// Patterns de tamanho de K
												// (numero de clusters)
		for (int c = 0; c < k; c++) {
			clustters[c] = new Vector<Pattern>(); // agora cada list do array é
													// instaciada como um
													// vettor(veja a teoria,
													// artigo das ongs)
		}

		centroids = new Pattern[k]; // inciando o array centrids com o tipo
									// Pattern e tamanho K.
		Pattern[] previousCentroids = new Pattern[k]; // novo array tipo Pattern
		List<Integer> listDifferentWarrant = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			boolean wasDrawn = false;
			int differentWarrant;

			do {
				differentWarrant = (int) (Math.random() * (patterns.size() - 1));
				for (Integer w : listDifferentWarrant) {
					if (differentWarrant == w) {
						wasDrawn = true;
						break;
					} else {
						wasDrawn = false;
					}
				}
			} while (wasDrawn);
			listDifferentWarrant.add(differentWarrant);
			// centroids[i] = patterns.get((int) (Math.random() *
			// (patterns.size() - 1)));
			centroids[i] = patterns.get(differentWarrant);// ini. centroid
															// randomicamente de
															// acordo com o
		} // tamanho da lista pattern dada pelo construtor ou seja
			// serão k centroids com valeres de 1 a 185
		int iterationCopy=0;

		for (int iteration = 0; iteration < maxIter; iteration++) {
			System.out.println("Iteração " + iteration);
			for (int c = 0; c < k; c++) {
				clustters[c].clear(); // dá clear nos clusters, acho que é caso
										// não seja a primeira vez, ps não
			} // é o centroid é o cluster, cada cluster tem um centroid
			/**
			 * Esse foretch tem um for mais interno que percorre o array de
			 * centroids e medi a distancia euclidiana entre cada um dos
			 * centroids para cada uma das cidades da lista de cidades, nomeada
			 * patterns, em busca da menor distância. Olhando centroid a
			 * centroid, quando ele acha essa menor distância, ele guarda o
			 * indice C do centroid na variavel bestClass, ao termino ele coloca
			 * no array de listas, nomeado clusters, a cidade ou Pattern (
			 * variavel pattern do foritch mais esterno) na posição C do array
			 * cluster (C é o indice que aponta o centroid no array de
			 * centroids). Com isso ele monta o cluster no array de listas
			 * cluster, ou seja, cada indice do array vai ser o centroid e como
			 * em cada posição tem uma lista (em real um Vector), os membros
			 * dessa lista serão as cidades do cluster.
			 */
			for (Pattern pattern : patterns) {
				double minDistance = Double.MAX_VALUE;// distancia minima recebe
														// o valor max pra ser
														// substituido por um
														// ponto do cluster
				int bestClass = -1;
				for (int c = 0; c < k; c++) {// aqui mede a distancia euclidiana
												// entre o centroid e os outros
												// pontos do cluster
					double dc = pattern.euclidianDistance(centroids[c]);

					if (dc < minDistance) {
						minDistance = dc;
						bestClass = c;
					}
				}
				clustters[bestClass].add(pattern); // adiciona ao cluster o
													// pattern de menor
													// distancia
			}

			for (int c = 0; c < k; c++) {
				previousCentroids[c] = centroids[c]; // copia o array de
														// centrids atual para o
														// array previoCetroids
			}
			// calcular novos centróides
			for (int c = 0; c < k; c++) {
				// instancia um novo partern pra ser um centroid, note que a
				// partir daqui o centroid
				// não tem mais nome, só as coordenadas que staão no array
				// Variables
				Pattern centroid = new Pattern("", new double[previousCentroids[0].getVariables().length], null);
				// percorre cada cidade dentro da lista que esta na posição C do
				// Array cluster
				for (Pattern pattern : clustters[c]) {
					// copia para o pattern centroid o array variables de cada
					// pattern que estava
					// dentro do cluster somando, ou seja, o valor da
					// variables[0] de centroid
					// vai ser a somatória das variables[0] de cada pattern
					// dentro do cluster.
					for (int v = 0; v < pattern.getVariables().length; v++) {
						centroid.getVariables()[v] += pattern.getVariables()[v];
					}
				}
				// divide a somatoria anterior pelo numero de cidades dentro de
				// cada cluster
				// isso fara que a coordenada do novo centrido seja a média
				// aritimetrica
				// das coordenadas de cada cidade do cluster
				for (int v = 0; v < centroid.getVariables().length; v++) {// jorge
					centroid.getVariables()[v] /= clustters[c].size();// c é o K
				}
				// adiciona ao array (atributo da classe) o centroid calculado.
				centroids[c] = centroid;
			}
			// busta dentro dos array centroids (atributo), pelo menos um caso
			// eum que o centroid
			// que seja diferente ao da lista previos centroid
			boolean stop = true;
			for (int c = 0; c < k; c++) {
				if (!previousCentroids[c].equals(centroids[c])) {
					stop = false;
					break;
				}
			}
			// para porque atingiu o max interation ou pq não encontrou pelo
			// menos um centroid centroid do array
			// (atributo) centroids, que seja diferente da lista de centroids
			// anterior. ou seja, pelo menos um
			// tem que ser diferente se não para o loop principal antes de
			// atingir a maxInteration
			if (stop) {
				break;
			}
			iterationCopy=iteration;
		}

//		int i = 0;
//		for (Pattern p : centroids) {
//			System.out.println("Detalhes do centróide " + i);
//			for (double v : p.getVariables()) {
//				System.out.printf("%.6f ", v);
//			}
//			System.out.println();
//			i++;
//		}
		this.lisItaration.add(iterationCopy);
		return clustters;
	}
	
	/**
	 * 
	 * Lista de interações praticadas
	 */
	

	public List<Integer> getLisItaration() {
		return lisItaration;
	}

	/**
	 * @return o valor do atributo centroids
	 */
	public Pattern[] getCentroids() {
		return centroids;
	}

	/**
	 * Altera o valor do atributo centroids
	 * 
	 * @param centroids
	 *            O valor para setar em centroids
	 */
	public void setCentroids(Pattern[] centroids) {
		this.centroids = centroids;
	}
}
