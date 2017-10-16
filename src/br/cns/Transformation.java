package br.cns;

public interface Transformation<T> {
	public abstract T[][] transform(T[][] matrix);
}
