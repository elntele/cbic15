package br.cns;

import br.cns.experiments.ComplexNetwork;

public interface Metric<T> {
	public abstract double calculate(T[][] matrix);
	
	public abstract double calculate(ComplexNetwork network);
	
	public abstract String name();
}
