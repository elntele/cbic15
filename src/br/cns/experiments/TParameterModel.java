package br.cns.experiments;

import static br.cns.models.TModel.BARABASI;
import static br.cns.models.TModel.BARABASI_DENSITY;
import static br.cns.models.TModel.CUSTOM;
import static br.cns.models.TModel.CUSTOM_PROBABILITY;
import static br.cns.models.TModel.ERDOS_RENYI_N_M;
import static br.cns.models.TModel.ERDOS_RENYI_N_P;
import static br.cns.models.TModel.GILBERT;
import static br.cns.models.TModel.K_REGULAR;
import static br.cns.models.TModel.NEWMAN_WATTS;
import static br.cns.models.TModel.TOROID;
import static br.cns.models.TModel.WATTS_STROGATZ;
import static br.cns.models.TModel.WATTS_STROGATZ_DENSITY;
import br.cns.models.TModel;

public enum TParameterModel {
	DENSITY_ERDOS("Densidade", ERDOS_RENYI_N_M), 
	PROBABILITY_ERDOS("Probabilidade", ERDOS_RENYI_N_P), 
	PROBABILITY_GILBERT("Probabilidade", GILBERT), 
	K("Par�metro k", K_REGULAR), 
	WS_PROBABILITY("Probabilidade", WATTS_STROGATZ),
	WSD_DENSITY("Densidade", WATTS_STROGATZ_DENSITY),
	WSD_PROBABILITY("Probabilidade", WATTS_STROGATZ_DENSITY),
	WS_DENSITY("Densidade", WATTS_STROGATZ),
	K_WS("Par�metro k", WATTS_STROGATZ),
	NW_PROBABILITY("Probabilidade", NEWMAN_WATTS), 
	NUM_NODES_BARABASI("N�mero de n�s", BARABASI), 
	M_LINKS_BARABASI("N�mero de links para novos n�s", BARABASI),
	DENSITY_BARABASI("Densidade da rede Barab�si", BARABASI_DENSITY),
	NUM_NODES_CUSTOM("N�mero de n�s", CUSTOM), 
	NUM_NODES_CUSTOM_PROBABILITY("N�mero de n�s", CUSTOM_PROBABILITY),
	TOROID_DENSITY("Densidade", TOROID);

	private String description;

	private TModel model;

	public static TParameterModel getType(String model, String description) {
		for (TParameterModel parameterModel : values()) {
			if (parameterModel.model.toString().equals(model) && parameterModel.description.equals(description)) {
				return parameterModel;
			}
		}
		return null;
	}

	private TParameterModel(String description, TModel model) {
		this.description = description;
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public TModel getModel() {
		return model;
	}
}
