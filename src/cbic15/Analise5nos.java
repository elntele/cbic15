/*
 * ****************************************************************************
 * Copyright (c) 2013
 * Todos os direitos reservados, com base nas leis brasileiras de copyright
 * Este software é confidencial e de propriedade intelectual da UFPE
 * ****************************************************************************
 * Projeto: BONS - Brazilian Optical Network Simulator
 * Arquivo: Analise5nos.java
 * ****************************************************************************
 * Histórico de revisões
 * Nome				Data		Descrição
 * ****************************************************************************
 * Danilo Araújo	21/06/2015		Versão inicial
 * ****************************************************************************
 */
package cbic15;

import java.util.List;
import java.util.Vector;

import br.cns.TMetric;
import br.cns.experiments.ComplexNetwork;
import br.cns.metrics.DFTLaplacianEntropy;
import br.cns.metrics.Entropy;
import br.cns.models.TModel;

/**
 * 
 * @author Danilo Araujo
 * @since 21/06/2015
 */
public class Analise5nos {

	/**
	 * Construtor da classe.
	 */
	public Analise5nos() {
	}

	public static void main(String[] args) {
		List<TMetric> metrics = new Vector<TMetric>();
		metrics.add(TMetric.DENSITY);
		metrics.add(TMetric.ASSORTATIVITY);
		metrics.add(TMetric.ENTROPY);
		metrics.add(TMetric.NORMALIZED_DFT_LAPLACIAN_ENTROPY);
		Analise5nos a = new Analise5nos();
		List<Integer[][]> ams = new Vector<Integer[][]>();

		ams.add(a.getNetwork01());
		ams.add(a.getNetwork02());
		ams.add(a.getNetwork03());
		ams.add(a.getNetwork04());
		ams.add(a.getNetwork05());
		ams.add(a.getNetwork06());
		ams.add(a.getNetwork07());
		ams.add(a.getNetwork08());
		ams.add(a.getNetwork09());
		ams.add(a.getNetwork10());
		ams.add(a.getNetwork11());
		ams.add(a.getNetwork12());
		ams.add(a.getNetwork13());
		ams.add(a.getNetwork14());
		ams.add(a.getNetwork15());
		ams.add(a.getNetwork16());
		ams.add(a.getNetwork17());
		ams.add(a.getNetwork18());
		ams.add(a.getNetwork19());
		ams.add(a.getNetwork20());
		ams.add(a.getNetwork21());

		for (int i = 0; i < ams.size(); i++) {
			ComplexNetwork cn = new ComplexNetwork(i, ams.get(i), new double[5][5], TModel.CUSTOM, metrics);
			System.out.printf("%d: $q$ = %.4f, $r$ = %.4f, $I(G)$ = %.4f; $\\mathcal{I(F)}$ = %.4f \n", (i+1), 
					cn.getMetricValues().get(TMetric.DENSITY), 
					cn.getMetricValues().get(TMetric.ASSORTATIVITY),
					Entropy.getInstance().calculate(cn), DFTLaplacianEntropy.getInstance().calculate(cn));
		}
	}

	public Integer[][] getNetwork01() {
		return new Integer[][] { 
				{ 0, 1, 0, 0, 0 }, 
				{ 1, 0, 1, 1, 1 }, 
				{ 0, 1, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0 },
				{ 0, 1, 0, 0, 0 }, };
	}

	public Integer[][] getNetwork02() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 1, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0 },
				{ 0, 1, 0, 0, 0 }, };
	}

	public Integer[][] getNetwork03() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 0, 1, 0 }, 
				{ 1, 0, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 1 },
				{ 0, 0, 0, 1, 0 }, };
	}

	public Integer[][] getNetwork04() {
		return new Integer[][] { 
				{ 0, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 1, 0 }, 
				{ 1, 0, 0, 0, 0 }, 
				{ 1, 1, 0, 0, 0 },
				{ 1, 0, 0, 0, 0 }, };
	}

	public Integer[][] getNetwork05() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 0, 1 }, 
				{ 0, 1, 0, 0, 0 },
				{ 0, 0, 1, 0, 0 }, };
	}
	
	public Integer[][] getNetwork06() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 1 },
				{ 0, 0, 0, 1, 0 }, };
	}
	
	public Integer[][] getNetwork07() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 0, 1, 0 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 0, 1, 1, 0, 0 },
				{ 0, 0, 1, 0, 0 }, };
	}
	
	public Integer[][] getNetwork08() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 0, 1, 0 }, 
				{ 1, 0, 0, 0, 1 }, 
				{ 0, 1, 0, 0, 1 },
				{ 0, 0, 1, 1, 0 }, };
	}
	
	public Integer[][] getNetwork09() {
		return new Integer[][] { 
				{ 0, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 1, 0 }, 
				{ 1, 0, 0, 0, 1 }, 
				{ 1, 1, 0, 0, 0 },
				{ 1, 0, 1, 0, 0 }, };
	}
	
	public Integer[][] getNetwork10() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 0, 1, 1, 0, 0 },
				{ 0, 1, 1, 0, 0 }, };
	}
	
	public Integer[][] getNetwork11() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 0, 1 }, 
				{ 0, 1, 0, 0, 1 },
				{ 0, 0, 1, 1, 0 }, };
	}
	
	public Integer[][] getNetwork12() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 1, 0 }, 
				{ 0, 1, 1, 0, 1 },
				{ 0, 0, 0, 1, 0 }, };
	}
	
	public Integer[][] getNetwork13() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 1, 0 }, 
				{ 0, 1, 1, 0, 1 },
				{ 0, 0, 0, 1, 0 }, };
	}
	
	public Integer[][] getNetwork14() {
		return new Integer[][] { 
				{ 0, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 1, 0 }, 
				{ 1, 0, 0, 0, 1 }, 
				{ 1, 1, 0, 0, 1 },
				{ 1, 0, 1, 1, 0 }, };
	}
	
	public Integer[][] getNetwork15() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 1 }, 
				{ 1, 1, 0, 1, 1 }, 
				{ 0, 1, 1, 0, 0 },
				{ 0, 1, 1, 0, 0 }, };
	}
	
	public Integer[][] getNetwork16() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 0, 1, 1, 0, 1 },
				{ 0, 1, 1, 1, 0 }, };
	}
	
	public Integer[][] getNetwork17() {
		return new Integer[][] { 
				{ 0, 1, 1, 1, 0 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 1, 1 }, 
				{ 1, 1, 1, 0, 0 },
				{ 0, 0, 1, 0, 0 }, };
	}
	
	public Integer[][] getNetwork18() {
		return new Integer[][] { 
				{ 0, 1, 1, 1, 1 }, 
				{ 1, 0, 1, 1, 0 }, 
				{ 1, 1, 0, 0, 1 }, 
				{ 1, 1, 0, 0, 1 },
				{ 1, 0, 1, 1, 0 }, };
	}
	
	public Integer[][] getNetwork19() {
		return new Integer[][] { 
				{ 0, 1, 1, 0, 0 }, 
				{ 1, 0, 1, 1, 1 }, 
				{ 1, 1, 0, 1, 1 }, 
				{ 0, 1, 1, 0, 1 },
				{ 0, 1, 1, 1, 0 }, };
	}
	
	public Integer[][] getNetwork20() {
		return new Integer[][] { 
				{ 0, 1, 1, 1, 1 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 1, 0, 0, 1, 1 }, 
				{ 1, 1, 1, 0, 1 },
				{ 1, 1, 1, 1, 0 }, };
	}
	
	public Integer[][] getNetwork21() {
		return new Integer[][] { 
				{ 0, 1, 1, 1, 1 }, 
				{ 1, 0, 1, 1, 1 }, 
				{ 1, 1, 0, 1, 1 }, 
				{ 1, 1, 1, 0, 1 },
				{ 1, 1, 1, 1, 0 }, };
	}
}
