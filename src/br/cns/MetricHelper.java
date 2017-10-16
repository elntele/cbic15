package br.cns;

import br.cns.exceptions.NoSuchAlgorithmException;
import br.cns.metrics.PhysicalDensity;


public class MetricHelper {
	private static final MetricHelper instance = new MetricHelper();
	
	public static MetricHelper getInstance(){
		return instance;
	}
	
	public double calculate(TMetric metric, Integer[][] matrix){
		Metric<Integer> metricObject = MetricFactory.getInstance().find(metric.toString());
		if (metricObject == null){
			return 0; 
//			throw new NoSuchAlgorithmException("Algoritmo não implementado " + metric.toString());
		} 
		return metricObject.calculate(matrix);
	}
	
	public double calculateDouble(TMetric metric, Double[][] matrix){
		Metric<Double> metricObject = MetricFactory.getInstance().findDouble(metric.toString());
		if (metricObject == null){
			throw new NoSuchAlgorithmException("Algoritmo não implementado " + metric.toString());
		} 
		return metricObject.calculate(matrix);
	}
	
	public double calculateDoubleComplete(TMetric metric, Double[][] completeMatrix, Double[][] matrix){
		Metric<Double> metricObject = MetricFactory.getInstance().findDouble(metric.toString());
		if (metricObject == null){
			throw new NoSuchAlgorithmException("Algoritmo não implementado " + metric.toString());
		} 
		if (metricObject instanceof PhysicalDensity) {
			return PhysicalDensity.getInstance(completeMatrix).calculate(matrix);
		}
		return metricObject.calculate(matrix);
	}

}
