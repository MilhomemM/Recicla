package compactadores;

public class Bloco {
	String tipo;
	int qtd;

	public Bloco(String tipo, int qtd) {
		this.tipo = tipo;
		this.qtd = qtd;
	}

	public String toString() {
		return tipo+" "+qtd;
	}
}
