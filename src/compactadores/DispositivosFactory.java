package compactadores;

public class DispositivosFactory {
	/**
	 * Este método permite que a fabrica retorne um compactor específico*/
	public static Dispositivo getDispositivo(String tipo){
		if(tipo.equals("metal"))
			return new Compactador_Metal(3);
		else if(tipo.equals("papel"))
			return new Compactador_Papel(10);
		else if(tipo.equals("plastico"))
			return new Compactador_Plastico(10);
		else if(tipo.equals("vidro"))
			return new Compactador_Vidro(8);
		else
			return null;
	}
}

