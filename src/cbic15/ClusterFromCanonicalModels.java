/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: ClusterFromCanonicalModels.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	09/07/2015		Versão inicial
 * ****************************************************************************
 */
package cbic15;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import br.cns.Geolocation;
import br.cns.GravityModel;
import br.cns.MetricHelper;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.experiments.nodePositions.CircularNetwork;
import br.cns.metrics.AlgebraicConnectivity;
import br.cns.metrics.Density;
import br.cns.model.GmlData;
import br.cns.models.BarabasiDensity;
import br.cns.models.GeokRegularTraffic;
import br.cns.models.Geometric;
import br.cns.models.SWDistance;
import br.cns.models.TModel;
import br.cns.models.WattsStrogatzDensity;
import br.cns.models.Waxman;
import br.cns.persistence.GmlDao;
import br.cns.transformations.DegreeMatrix;

/**
 * 
 * @author Danilo Araujo
 * @since 09/07/2015
 */
public class ClusterFromCanonicalModels {
	private static final String BARRA_BARRA = "\\\\";

	private static final String BARRA = "\\";

	private static List<TMetric> defaultMetrics = new Vector<TMetric>();
	
	private static final String strGmlOrigem = "Evolink.gml";

	/**
	 * Construtor da classe.
	 */
	public ClusterFromCanonicalModels() {
	}

	public static void createWs(List<TMetric> metrics, double[][] minMax, List<Pattern> patterns, int index,
			int numNodes, double rp, double d, List<Integer[]> ordemGrauOriginal, Comparator<Integer[]> comparatorGrau,
			GmlData originalNetwork, String basePath) {
		int kp = (int)Math.round(0.5 * d * (numNodes - 1));
		if (kp == 0) {
			kp = 1;
		}
		WattsStrogatzDensity wsd = new WattsStrogatzDensity(rp, kp, d, false);
		Integer[][] am = new Integer[numNodes][numNodes];
		am = wsd.transform(am);
		ComplexNetwork cn = new ComplexNetwork(index, am, CircularNetwork.getInstance().createNodePositions(numNodes),
				TModel.CUSTOM);

		double ac = AlgebraicConnectivity.getInstance().calculate(cn);
		while (ac < 0.0001) {
			am = wsd.transform(am);
			cn = new ComplexNetwork(index, am, CircularNetwork.getInstance().createNodePositions(numNodes),
					TModel.CUSTOM);
			ac = AlgebraicConnectivity.getInstance().calculate(cn);
		}

		Pattern p = new Pattern();
		double[] variables = new double[metrics.size()];

		for (int m = 0; m < metrics.size(); m++) {
			if (metrics.get(m).isAdjacencyMatrix()) {
				variables[m] = MetricHelper.getInstance().calculate(metrics.get(m), cn.getAdjacencyMatrix());
			} else {
				variables[m] = MetricHelper.getInstance().calculateDouble(metrics.get(m), cn.getDistances());
			}
			if (variables[m] < minMax[m][0]) {
				minMax[m][0] = variables[m];
			}
			if (variables[m] > minMax[m][1]) {
				minMax[m][1] = variables[m];
			}
		}
		p.setVariables(variables);
		String nameNet = "WS " + index;
		p.setName(nameNet);
		p.setCn(cn);

		List<Integer[]> ordemGrau = new Vector<Integer[]>();
		Integer[][] degreeMatrix = DegreeMatrix.getInstance().transform(cn.getAdjacencyMatrix());
		for (int i = 0; i < numNodes; i++) {
			ordemGrau.add(new Integer[] { i, degreeMatrix[i][i] });
		}
		Collections.sort(ordemGrau, comparatorGrau);

		GmlDao dao = new GmlDao();
		GmlData gmlOut = originalNetwork.clone();
		gmlOut.getEdges().clear();
		for (int i = 0; i < numNodes; i++) {
			for (int j = i + 1; j < numNodes; j++) {
				if (cn.getAdjacencyMatrix()[i][j] != 0) {
					int newIndexI = 0;
					int newIndexJ = 0;
					for (int k = 0; k < numNodes; k++) {
						if (ordemGrau.get(k)[0] == i) {
							newIndexI = ordemGrauOriginal.get(k)[0];
							break;
						}
					}
					for (int k = 0; k < numNodes; k++) {
						if (ordemGrau.get(k)[0] == j) {
							newIndexJ = ordemGrauOriginal.get(k)[0];
							break;
						}
					}

					gmlOut.addEdge(newIndexI, newIndexJ, newIndexI + "-" + newIndexJ, null);
				}
			}
		}
		dao.save(gmlOut, basePath + "/" + strGmlOrigem + "_generated/" + nameNet + ".gml");

		patterns.add(p);
	}

	public static void createBA(List<TMetric> metrics, double[][] minMax, List<Pattern> patterns, int index,
			int numNodes, double rp, double d, List<Integer[]> ordemGrauOriginal, Comparator<Integer[]> comparatorGrau,
			GmlData originalNetwork, String basePath) {
		BarabasiDensity ba = new BarabasiDensity(d, rp);
		Integer[][] am = new Integer[numNodes][numNodes];
		am = ba.grow(null, numNodes);
		ComplexNetwork cn = new ComplexNetwork(index, am, CircularNetwork.getInstance().createNodePositions(numNodes),
				TModel.CUSTOM);

		double ac = AlgebraicConnectivity.getInstance().calculate(cn);
		while (ac < 0.0001) {
			am = ba.grow(null, numNodes);
			cn = new ComplexNetwork(index, am, CircularNetwork.getInstance().createNodePositions(numNodes),
					TModel.CUSTOM);
			ac = AlgebraicConnectivity.getInstance().calculate(cn);
		}

		Pattern p = new Pattern();
		double[] variables = new double[metrics.size()];

		for (int m = 0; m < metrics.size(); m++) {
			if (metrics.get(m).isAdjacencyMatrix()) {
				variables[m] = MetricHelper.getInstance().calculate(metrics.get(m), cn.getAdjacencyMatrix());
			} else {
				variables[m] = MetricHelper.getInstance().calculateDouble(metrics.get(m), cn.getDistances());
			}
			if (variables[m] < minMax[m][0]) {
				minMax[m][0] = variables[m];
			}
			if (variables[m] > minMax[m][1]) {
				minMax[m][1] = variables[m];
			}
		}
		p.setVariables(variables);
		String nameNet = "BA " + index;
		p.setName(nameNet);
		p.setCn(cn);

		List<Integer[]> ordemGrau = new Vector<Integer[]>();
		Integer[][] degreeMatrix = DegreeMatrix.getInstance().transform(cn.getAdjacencyMatrix());
		for (int i = 0; i < numNodes; i++) {
			ordemGrau.add(new Integer[] { i, degreeMatrix[i][i] });
		}
		Collections.sort(ordemGrau, comparatorGrau);

		GmlDao dao = new GmlDao();
		GmlData gmlOut = originalNetwork.clone();
		gmlOut.getEdges().clear();
		for (int i = 0; i < numNodes; i++) {
			for (int j = i + 1; j < numNodes; j++) {
				if (cn.getAdjacencyMatrix()[i][j] != 0) {
					int newIndexI = 0;
					int newIndexJ = 0;
					for (int k = 0; k < numNodes; k++) {
						if (ordemGrau.get(k)[0] == i) {
							newIndexI = ordemGrauOriginal.get(k)[0];
							break;
						}
					}
					for (int k = 0; k < numNodes; k++) {
						if (ordemGrau.get(k)[0] == j) {
							newIndexJ = ordemGrauOriginal.get(k)[0];
							break;
						}
					}

					gmlOut.addEdge(newIndexI, newIndexJ, newIndexI + "-" + newIndexJ, null);
				}
			}
		}
		dao.save(gmlOut, basePath + "/" + strGmlOrigem + "_generated/" + nameNet + ".gml");
		patterns.add(p);
	}

	public static void main(String[] args) {
		defaultMetrics.add(TMetric.NUMBER_OF_NODES);
		defaultMetrics.add(TMetric.DENSITY);
		defaultMetrics.add(TMetric.ENTROPY);
		defaultMetrics.add(TMetric.ALGEBRAIC_CONNECTIVITY);
		defaultMetrics.add(TMetric.ASSORTATIVITY);
		defaultMetrics.add(TMetric.HUB_DEGREE);

		List<TMetric> metrics = new Vector<TMetric>();
		// metrics.add(TMetric.NUMBER_OF_NODES);
		metrics.add(TMetric.DENSITY);
		 metrics.add(TMetric.ENTROPY);
		 metrics.add(TMetric.ASSORTATIVITY);
		 metrics.add(TMetric.HUB_DEGREE);
		// metrics.add(TMetric.AVERAGE_PATH_LENGTH);
		metrics.add(TMetric.DFT_LAPLACIAN_ENTROPY);
		int k = 4;
		String basePath = "results/gmlCompl/";
		File dirBasePath = new File(basePath);
		double[][] minMax = new double[metrics.size() + 1][2];
		List<Pattern> patterns = new Vector<Pattern>();
		int numNets = 0;

		GmlDao dao = new GmlDao();
		String strNet = strGmlOrigem;
		GmlData data = dao.loadGmlData(basePath + strNet);
		String dirNet = basePath + "/" + strNet + "_generated";
		if (!new File(dirNet).exists()) {
			new File(dirNet).mkdirs();
		}
		int numNodes = data.getNodes().size();
		GravityModel gm = new GravityModel(data);
		Double[][] traffic = gm.getTrafficMatrix();

		Geolocation[] locations = new Geolocation[numNodes];
		for (int i = 0; i < locations.length; i++) {
			locations[i] = new Geolocation(data.getNodes().get(i).getLatitude(), data.getNodes().get(i).getLongitude());
		}

		ComplexNetwork cn = data.createComplexNetworkDistance();

		List<Integer[]> ordemGrau = new Vector<Integer[]>();
		Comparator<Integer[]> comparatorGrau = new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] v1, Integer[] v2) {
				if (v1[1] < v2[1]) {
					return -1;
				} else if (v1[1] > v2[1]) {
					return 1;
				} else {
					if (v1[0] > v2[0]) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		};
		Integer[][] degreeMatrix = DegreeMatrix.getInstance().transform(cn.getAdjacencyMatrix());
		for (int i = 0; i < numNodes; i++) {
			ordemGrau.add(new Integer[] { i, degreeMatrix[i][i] });
		}
		Collections.sort(ordemGrau, comparatorGrau);

		// double firstDensity y= 0.03;
		// double lastDensity = 0.12;
		double firstDensity = 2.0 / (numNodes - 1);
		double lastDensity = 5.0 / (numNodes - 1);
		System.out.println("Gerando redes com densidade entre " + firstDensity + " e " + lastDensity);
		for (double d = firstDensity; d <= lastDensity; d += 0.005) {
			System.out.println("Processando d = " + d);

			for (int i = 0; i < 25; i++) {
				numNets++;
				createWs(metrics, minMax, patterns, numNets, numNodes, 0.10, d, ordemGrau, comparatorGrau, data,
						basePath);
			}

			// barabasi
			for (int i = 0; i < 25; i++) {
				numNets++;
				createBA(metrics, minMax, patterns, numNets, numNodes, 1.0, d, ordemGrau, comparatorGrau, data,
						basePath);
			}
		}
		System.out.println("Total de redes = " + numNets);
		for (Pattern p : patterns) {
			System.out.println("Normalizando " + p.getName());
			for (int m = 0; m < metrics.size(); m++) {
				p.getVariables()[m] = (p.getVariables()[m] - minMax[m][0]) / (minMax[m][1] - minMax[m][0]);
			}
		}
		Kmeans kmeans = new Kmeans(k, patterns);
		int nruns = 100;
		double[] ce = new double[nruns];
		double[] si = new double[nruns];
		System.out.println("Resultados para k = " + k);
		double numNonEmptyClusters = 0;
		for (int r = 0; r < nruns; r++) {
			ce[r] = 0;
			numNonEmptyClusters = 0;
			List<Pattern>[] clusters = kmeans.execute(100);
			Pattern[] centroids = kmeans.getNearestPatternsFromCentroid();
			for (int c = 0; c < clusters.length; c++) {
				if (clusters[c].isEmpty()) {
					continue;
				}
				numNonEmptyClusters++;
//				System.out.println(clusters[c].size() + " redes no cluster " + c + ":");
				int countWS = 0;
				int countBA = 0;
				for (Pattern p : clusters[c]) {
//					System.out.println(p.getName());
					if (p.getName().contains("BA")) {
						countBA++;
					} else {
						countWS++;
					}
				}
				if (centroids[c].getName().contains("WS")) {
					ce[r] += (countWS * 1.0) / (countBA + countWS);
				} else {
					ce[r] += (countBA * 1.0) / (countBA + countWS);
				}
				si[r] = kmeans.getSilhouetteIndex(clusters);
//				System.out.println("\n");
//				System.out.println("O centróide é " + centroids[c].getName());
//				int i = 0;
//				System.out.println("Variáveis: ");
//				for (double v : centroids[c].getVariables()) {
//					System.out.printf("%.4f ", v * (minMax[i][1] - minMax[i][0]) + minMax[i][0]);
//					i++;
//				}
//				System.out.println();
			}
			// generateLatexTables(centroids, metrics);
		}
		for (int i = 0; i < nruns; i++) {
			System.out.printf("%.4f %.4f \n", ce[i]/numNonEmptyClusters, si[i]);
		}

	}

	/**
	 * 
	 */
	public static void generateLatexTables(Pattern[] selectedNetworks, List<TMetric> metrics) {
		String basePath = "results/gmlCompl/";
		File dirBasePath = new File(basePath);

		System.out.println("\\begin{table*}[!htbp]");
		System.out.println("\\centering");
		System.out.println("\\caption{title}");
		System.out.print("\\begin{tabular}{|l|c|c|c|");
		for (int i = 0; i < metrics.size(); i++) {
			System.out.print("c|");
		}
		double[][] minMax = new double[metrics.size()][2];
		Map<String, Double> mapMetricValues = new HashMap<String, Double>();
		System.out.println("}");
		System.out.println("\\hline");
		System.out.print("\\textbf{Rede} & \\textbf{$n$} & \\textbf{Modelo} & \\textbf{$e$}");
		int j = 0;
		for (TMetric metric : metrics) {
			System.out.printf(" & \\textbf{%s} ", metric.getShortDescription());
			minMax[j][0] = Double.MAX_VALUE;
			minMax[j][1] = Double.MIN_VALUE;
			j++;
		}
		System.out.print(BARRA_BARRA + BARRA + "hline");
		System.out.println(BARRA + "hline");
		int numNets = 0;
		for (String strArq : dirBasePath.list()) {
			if (strArq.endsWith(".gml")) {
				boolean achou = false;
				for (Pattern p : selectedNetworks) {
					if (p.getName().equals(strArq)) {
						achou = true;
						break;
					}
				}
				if (!achou) {
					continue;
				}
				numNets++;
				GmlDao dao = new GmlDao();
				GmlData data = dao.loadGmlData(basePath + strArq);
				int numNodes = data.getNodes().size();
				GravityModel gm = new GravityModel(data);
				Double[][] traffic = gm.getTrafficMatrix();
				// normalizar matriz de tráfego
				double maior = 0;
				for (int r = 0; r < traffic.length; r++) {
					for (int c = r + 1; c < traffic.length; c++) {
						if (traffic[r][c] > maior) {
							maior = traffic[r][c];
						}
					}
				}
				for (int r = 0; r < traffic.length; r++) {
					traffic[r][r] = 0.0;
					for (int c = r + 1; c < traffic.length; c++) {
						traffic[r][c] /= maior;
						traffic[c][r] = traffic[r][c];
					}
				}

				Geolocation[] locations = new Geolocation[numNodes];
				for (int i = 0; i < locations.length; i++) {
					locations[i] = new Geolocation(data.getNodes().get(i).getLatitude(), data.getNodes().get(i)
							.getLongitude());
				}

				ComplexNetwork cn = data.createComplexNetworkDistance();
				double densityOriginal = Density.getInstance().calculate(cn);
				System.out.print("\\multirow{5}{*}{" + data.getInformations().get("Network") + "} & \\multirow{5}{*}{"
						+ numNodes + "} & Original & " + data.getEdges().size());
				int i = 0;
				for (TMetric metric : metrics) {
					double metricValue = 0;
					if (metric.isAdjacencyMatrix()) {
						metricValue = MetricHelper.getInstance().calculate(metric, cn.getAdjacencyMatrix());
					} else {
						metricValue = MetricHelper.getInstance().calculateDouble(metric, cn.getDistances());
					}
					mapMetricValues.put(strArq + ".original." + metric.toString(), metricValue);
					if (metricValue < minMax[i][0]) {
						minMax[i][0] = metricValue;
					}
					if (metricValue > minMax[i][1]) {
						minMax[i][1] = metricValue;
					}
					i++;
					System.out.printf(" & %.2f ", metricValue);
				}
				System.out.println(BARRA_BARRA + BARRA + "cline{3-" + (metrics.size() + 4) + "}");

				GeokRegularTraffic gabriel = new GeokRegularTraffic(locations, traffic, densityOriginal);
				Integer[][] gabrielMatrix = gabriel.transform(new Integer[numNodes][numNodes]);

				showModelRow(metrics, gabrielMatrix, gm.getDistances(), "Gabriel", strArq, mapMetricValues, minMax);
				System.out.println(BARRA_BARRA + BARRA + "cline{3-" + (metrics.size() + 4) + "}");

				Integer[][] geometricMatrix = getGeometricMatrix(numNodes, gm, locations);

				showModelRow(metrics, geometricMatrix, gm.getDistances(), "Geométrico", strArq, mapMetricValues, minMax);
				System.out.println(BARRA_BARRA + BARRA + "cline{3-" + (metrics.size() + 4) + "}");

				SWDistance wst = new SWDistance(traffic, getK(densityOriginal, numNodes), densityOriginal);

				Integer[][] wstMatrix = wst.transform(new Integer[numNodes][numNodes]);

				showModelRow(metrics, wstMatrix, gm.getDistances(), "WS-T", strArq, mapMetricValues, minMax);
				System.out.println(BARRA_BARRA + BARRA + "cline{3-" + (metrics.size() + 4) + "}");

				Integer[][] wxmMatrix = getWaxmanGraph(numNodes, densityOriginal, locations);

				showModelRow(metrics, wxmMatrix, gm.getDistances(), "Waxman", strArq, mapMetricValues, minMax);

				System.out.println(BARRA_BARRA + BARRA + "hline");
			}
		}
		System.out.println("\\end{tabular}");
		System.out.println("\\end{table*}");

		double[][] emq = new double[metrics.size() + 1][4];
		System.out.println();
		for (String strArq : dirBasePath.list()) {
			if (strArq.endsWith(".gml")) {
				boolean achou = false;
				for (Pattern p : selectedNetworks) {
					if (p.getName().equals(strArq)) {
						achou = true;
						break;
					}
				}
				if (!achou) {
					continue;
				}
				int i = 0;
				for (TMetric metric : metrics) {
					double valueOriginal = mapMetricValues.get(strArq + ".original." + metric.toString());
					valueOriginal = (valueOriginal - minMax[i][0]) / (minMax[i][1] - minMax[i][0]);

					double valueGabriel = mapMetricValues.get(strArq + ".Gabriel." + metric.toString());
					valueGabriel = (valueGabriel - minMax[i][0]) / (minMax[i][1] - minMax[i][0]);

					double valueGeometric = mapMetricValues.get(strArq + ".Geométrico." + metric.toString());
					valueGeometric = (valueGeometric - minMax[i][0]) / (minMax[i][1] - minMax[i][0]);

					double valueWsT = mapMetricValues.get(strArq + ".WS-T." + metric.toString());
					valueWsT = (valueWsT - minMax[i][0]) / (minMax[i][1] - minMax[i][0]);

					double valueWaxman = mapMetricValues.get(strArq + ".Waxman." + metric.toString());
					valueWaxman = (valueWaxman - minMax[i][0]) / (minMax[i][1] - minMax[i][0]);

					emq[i][0] += (valueOriginal - valueGabriel) * (valueOriginal - valueGabriel);
					emq[i][1] += (valueOriginal - valueGeometric) * (valueOriginal - valueGeometric);
					emq[i][2] += (valueOriginal - valueWsT) * (valueOriginal - valueWsT);
					emq[i][3] += (valueOriginal - valueWaxman) * (valueOriginal - valueWaxman);
					i++;
				}
			}
		}

		System.out.println("\\begin{table}[!htbp]");
		System.out.println("\\centering");
		System.out.println("\\caption{title}");
		System.out.print("\\begin{tabular}{|l|c|c|c|c|}");
		System.out.println("\\hline");
		System.out
				.print("\\textbf{Métrica} & \\textbf{Gabriel} & \\textbf{Geométrico} & \\textbf{WS-T} & \\textbf{Waxman}");
		System.out.print(BARRA_BARRA + BARRA + "hline");
		System.out.println(BARRA + "hline");
		int i = 0;
		for (TMetric metric : metrics) {
			System.out.printf("%s & %.4f & %.4f & %.4f & %.4f ", metric.getShortDescription(), emq[i][0] / numNets,
					emq[i][1] / numNets, emq[i][2] / numNets, emq[i][3] / numNets);
			System.out.print(BARRA_BARRA + BARRA + "hline\n");
			i++;
		}
		for (i = 0; i < metrics.size(); i++) {
			emq[emq.length - 1][0] += emq[i][0];
			emq[emq.length - 1][1] += emq[i][1];
			emq[emq.length - 1][2] += emq[i][2];
			emq[emq.length - 1][3] += emq[i][3];
		}
		System.out.printf("%s & %.4f & %.4f & %.4f & %.4f ", "$SG$", emq[emq.length - 1][0]
				/ (numNets * metrics.size()), emq[emq.length - 1][1] / (numNets * metrics.size()),
				emq[emq.length - 1][2] / (numNets * metrics.size()), emq[emq.length - 1][3]
						/ (numNets * metrics.size()));
		System.out.print(BARRA_BARRA + BARRA + "hline\n");
		System.out.println("\\end{tabular}");
		System.out.println("\\end{table}");
	}

	/**
	 * @param numNodes
	 * @param densityOriginal
	 * @param locations
	 * @return
	 */
	public static Integer[][] getWaxmanGraph(int numNodes, double densityOriginal, Geolocation[] locations) {
		double alpha = 1;
		double beta = densityOriginal;

		Waxman wxm = new Waxman(locations, alpha, beta);
		Integer[][] wxmMatrix = wxm.transform(new Integer[numNodes][numNodes]);
		int attempts = 0;
		while (attempts < 10
				&& (AlgebraicConnectivity.getInstance().calculate(wxmMatrix) <= 0.0001 || Math.abs(Density
						.getInstance().calculate(wxmMatrix) - densityOriginal) > 0.02)) {
			// System.out.println("Tentativa " + attempts + " em Waxman...");
			wxm = new Waxman(locations, 0.8, 2 * densityOriginal);
			wxmMatrix = wxm.transform(new Integer[numNodes][numNodes]);
			beta = Math.random() * 0.2 + densityOriginal;
			alpha = Math.random() * 0.4 + 0.6;
			attempts++;
		}
		return wxmMatrix;
	}

	/**
	 * @param numNodes
	 * @param gm
	 * @param locations
	 * @return
	 */
	public static Integer[][] getGeometricMatrix(int numNodes, GravityModel gm, Geolocation[] locations) {
		double min = gm.getGeoMinDistance();
		AlgebraicConnectivity ac = AlgebraicConnectivity.getInstance();
		Geometric geometric = new Geometric(locations, min);
		Integer[][] geometricMatrix = geometric.transform(new Integer[numNodes][numNodes]);
		while (ac.calculate(geometricMatrix) <= 0.00001) {
			min += 0.5;
			geometric = new Geometric(locations, min);
			geometricMatrix = geometric.transform(new Integer[numNodes][numNodes]);
		}
		return geometricMatrix;
	}

	public static int getK(double density, int numNodes) {
		if (density > 8.0 / (numNodes - 1)) {
			return 4;
		} else if (density > 6.0 / (numNodes - 1)) {
			return 3;
		} else if (density > 4.0 / (numNodes - 1)) {
			return 2;
		}
		return 1;
	}

	/**
	 * @param metrics
	 * @param gabrielMatrix
	 * @param model
	 */
	public static void showModelRow(List<TMetric> metrics, Integer[][] am, Double[][] distanceMatrix, String model,
			String strArq, Map<String, Double> mapMetricValues, double[][] minMax) {
		System.out.print(" & & " + model + " & " + getNumLinks(am));
		Double[][] distance = new Double[am.length][am.length];
		for (int i = 0; i < am.length; i++) {
			distance[i][i] = 0.0;
			for (int j = i + 1; j < am.length; j++) {
				distance[i][j] = 0.0;
				if (am[i][j] != 0) {
					distance[i][j] = distanceMatrix[i][j];
				}
				distance[j][i] = distance[i][j];
			}
		}
		int i = 0;
		for (TMetric metric : metrics) {
			double metricValue = 0;
			if (metric.isAdjacencyMatrix()) {
				metricValue = MetricHelper.getInstance().calculate(metric, am);
			} else {
				metricValue = MetricHelper.getInstance().calculateDouble(metric, distance);
			}
			mapMetricValues.put(strArq + "." + model + "." + metric.toString(), metricValue);
			System.out.printf(" & %.2f ", metricValue);
			if (metricValue < minMax[i][0]) {
				minMax[i][0] = metricValue;
			}
			if (metricValue > minMax[i][1]) {
				minMax[i][1] = metricValue;
			}
			i++;
		}
	}

	private static int getNumLinks(Integer[][] am) {
		int links = 0;
		for (int i = 0; i < am.length; i++) {
			for (int j = i + 1; j < am.length; j++) {
				links += am[i][j];
			}
		}
		return links;

	}
}
