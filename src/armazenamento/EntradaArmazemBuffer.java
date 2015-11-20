package armazenamento;

import compactadores.Bloco;

public abstract class EntradaArmazemBuffer {
	public abstract Bloco get();
	public abstract void set(Bloco bloco);
}
