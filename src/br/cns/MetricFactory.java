package br.cns;

import java.util.HashMap;
import java.util.Map;

import br.cns.metrics.AlgebraicConnectivity;
import br.cns.metrics.Assortativity;
import br.cns.metrics.AverageDegree;
import br.cns.metrics.AveragePathLength;
import br.cns.metrics.BetwennessCentralization;
import br.cns.metrics.ClosenessEntropy;
import br.cns.metrics.ClosenessEntropyPhysical;
import br.cns.metrics.ClusteringCoefficient;
import br.cns.metrics.ConcentrationRoutes;
import br.cns.metrics.DFTLaplacianEntropy;
import br.cns.metrics.Density;
import br.cns.metrics.Diameter;
import br.cns.metrics.DoubleFailureImpact;
import br.cns.metrics.EigenValuesSum;
import br.cns.metrics.Entropy;
import br.cns.metrics.HubDegree;
import br.cns.metrics.LaplacianEntropy;
import br.cns.metrics.LinkEfficiency;
import br.cns.metrics.MaximumCloseness;
import br.cns.metrics.NaturalConnectivity;
import br.cns.metrics.NormalizedDftLaplacianEntropy;
import br.cns.metrics.NumberOfComponents;
import br.cns.metrics.NumberOfNodes;
import br.cns.metrics.OpticalNetworkCost;
import br.cns.metrics.PhysicalAveragePathLength;
import br.cns.metrics.PhysicalAveragePathLengthSD;
import br.cns.metrics.PhysicalDFTLaplacianEntropy;
import br.cns.metrics.PhysicalDensity;
import br.cns.metrics.PhysicalDiameter;
import br.cns.metrics.Resilience;
import br.cns.metrics.SimtonBlockingProbability;
import br.cns.metrics.SingleFailureImpact;
import br.cns.metrics.SpectralRadius;
import br.cns.metrics.ZeroReturnCount;
import br.cns.metrics.ZeroReturnCountFilter;

public class MetricFactory {
	private static Map<String, Metric<Integer>> map = new HashMap<String, Metric<Integer>>();

	private static Map<String, Metric<Double>> mapDoubleMetrics = new HashMap<String, Metric<Double>>();

	private static final MetricFactory instance = new MetricFactory();

	public static MetricFactory getInstance() {
		return instance;
	}

	private MetricFactory() {
		if (map.isEmpty()) {
			map.put(Diameter.getInstance().name(), Diameter.getInstance());
			map.put(NumberOfNodes.getInstance().name(), NumberOfNodes.getInstance());
			map.put(AlgebraicConnectivity.getInstance().name(), AlgebraicConnectivity.getInstance());
			map.put(NumberOfComponents.getInstance().name(), NumberOfComponents.getInstance());
			map.put(NaturalConnectivity.getInstance().name(), NaturalConnectivity.getInstance());
			map.put(AverageDegree.getInstance().name(), AverageDegree.getInstance());
			map.put(Density.getInstance().name(), Density.getInstance());
			map.put(ClusteringCoefficient.getInstance().name(), ClusteringCoefficient.getInstance());
			map.put(AveragePathLength.getInstance().name(), AveragePathLength.getInstance());
			map.put(Entropy.getInstance().name(), Entropy.getInstance());
			map.put(LinkEfficiency.getInstance().name(), LinkEfficiency.getInstance());
			map.put(OpticalNetworkCost.getInstance().name(), OpticalNetworkCost.getInstance());
			map.put(SimtonBlockingProbability.getInstance().name(), SimtonBlockingProbability.getInstance());
			map.put(EigenValuesSum.getInstance().name(), EigenValuesSum.getInstance());
			;
			map.put(Resilience.getInstance().name(), Resilience.getInstance());
			map.put(ZeroReturnCount.getInstance().name(), ZeroReturnCount.getInstance());
			map.put(SingleFailureImpact.getInstance().name(), SingleFailureImpact.getInstance());
			map.put(DoubleFailureImpact.getInstance().name(), DoubleFailureImpact.getInstance());
			map.put(ZeroReturnCountFilter.getInstance().name(), ZeroReturnCountFilter.getInstance());
			map.put(HubDegree.getInstance().name(), HubDegree.getInstance());
			map.put(NormalizedDftLaplacianEntropy.getInstance().name(), NormalizedDftLaplacianEntropy.getInstance());
			map.put(DFTLaplacianEntropy.getInstance().name(), DFTLaplacianEntropy.getInstance());
			map.put(LaplacianEntropy.getInstance().name(), LaplacianEntropy.getInstance());
			map.put(SpectralRadius.getInstance().name(), SpectralRadius.getInstance());
			map.put(ClosenessEntropy.getInstance().name(), ClosenessEntropy.getInstance());
			map.put(BetwennessCentralization.getInstance().name(), BetwennessCentralization.getInstance());
			map.put(ConcentrationRoutes.getInstance().name(), ConcentrationRoutes.getInstance());
			map.put(Assortativity.getInstance().name(), Assortativity.getInstance());

			mapDoubleMetrics.put(PhysicalDiameter.getInstance().name(), PhysicalDiameter.getInstance());
			mapDoubleMetrics.put(PhysicalAveragePathLength.getInstance().name(),
					PhysicalAveragePathLength.getInstance());
			mapDoubleMetrics.put(PhysicalDFTLaplacianEntropy.getInstance().name(),
					PhysicalDFTLaplacianEntropy.getInstance());
			mapDoubleMetrics.put(ClosenessEntropyPhysical.getInstance().name(), ClosenessEntropyPhysical.getInstance());
			mapDoubleMetrics.put(MaximumCloseness.getInstance().name(), MaximumCloseness.getInstance());
			mapDoubleMetrics.put(TMetric.PHYSICAL_DENSITY.toString(), PhysicalDensity.getInstance(null));
			mapDoubleMetrics.put(TMetric.PHYSICAL_AVERAGE_PATH_LENGTH_SD.toString(), PhysicalAveragePathLengthSD.getInstance());
		}
	}

	public Metric<Integer> find(String metricName) {
		return map.get(metricName);
	}

	public Metric<Double> findDouble(String metricName) {
		return mapDoubleMetrics.get(metricName);
	}
}
