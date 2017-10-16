package br.cns.experiments;

public enum MenuActions {
	NEW("Nova rede"), 
	NEW_SIMULATION("Nova simulação"), 
	OPEN("Abrir rede..."),
	IMPORT("Importar rede..."),
	SAVE("Salvar rede..."), 
	PRINT("Imprimir..."), 
	CONFIG("Configurações..."), 
	EXIT("Sair"), 
	EXPORT("Exportar imagem para PNG..."),
	ABOUT("Sobre o Complex Network Simulator"), 
	EXPORT_TABLE_TXT("Exportar dados tabulados para TXT..."), 
	EXPORT_TABLE_XML("Exportar dados tabulados para XML..."), 
	SOM_EXPERIMENT("Analisar modelos usando Mapas Auto-Organizáveis...");

	private String description;

	private MenuActions(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
