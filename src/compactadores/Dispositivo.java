package compactadores;

import armazenamento.EntradaArmazem;
import esteiras.Esteira;
import sistemaCentral.Recicladora;

public interface Dispositivo {
	public void compacta();
	public void initialize(Recicladora sc, Esteira esteira, EntradaArmazem entrada_armazem);
}
