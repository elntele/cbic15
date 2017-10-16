package br.cns.persistence;

import br.cns.experiments.ComplexNetwork;

public interface IGmlDao {
	public void save(ComplexNetwork network, String path);
	
	public ComplexNetwork load(String path);
}
