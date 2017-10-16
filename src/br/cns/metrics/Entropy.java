package br.cns.metrics;

import br.cns.Metric;
import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.transformations.DegreeMatrix;

public class Entropy implements Metric<Integer> {

	private static final Entropy instance = new Entropy();

	private Entropy() {
	}

	public static Entropy getInstance() {
		return instance;
	}
	
	public double calculate(ComplexNetwork cn) {
		double sum = 0;
		Integer[][] matrix = cn.getAdjacencyMatrix();
		Integer[][] degreeMatrix = cn.getDegreeMatrix();
		if (cn.getDegreeMatrix() == null) {
			degreeMatrix = DegreeMatrix.getInstance().transform(matrix);
			cn.setDegreeMatrix(degreeMatrix);
		}
		
		double[] sequence = new double[matrix.length];

		for (int i = 0; i < degreeMatrix.length; i++) {
			sequence[degreeMatrix[i][i]]++;
		}
		sum = 0;
		for (int i = 0; i < degreeMatrix.length; i++) {
			sequence[i] /= sequence.length;
			if (sequence[i] > 0) {
				sum += sequence[i] * (Math.log10(sequence[i]) / Math.log10(2));
			}
		}
		return -sum;
	}

	@Override
	public double calculate(Integer[][] matrix) {
		double sum = 0;
		Integer[][] degreeMatrix = DegreeMatrix.getInstance().transform(matrix);
		double[] sequence = new double[matrix.length];

		for (int i = 0; i < degreeMatrix.length; i++) {
			sequence[degreeMatrix[i][i]]++;
		}
		sum = 0;
		for (int i = 0; i < degreeMatrix.length; i++) {
			sequence[i] /= sequence.length;
			if (sequence[i] > 0) {
				sum += sequence[i] * (Math.log10(sequence[i]) / Math.log10(2));
			}
		}
		return -sum;
	}

	@Override
	public String name() {
		return TMetric.ENTROPY.toString();
	}

}
