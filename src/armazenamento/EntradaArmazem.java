package armazenamento;

import java.util.concurrent.ArrayBlockingQueue;

import compactadores.Bloco;


public class EntradaArmazem extends EntradaArmazemBuffer{
	ArrayBlockingQueue<Bloco> entrada;
	
	public EntradaArmazem(){
		entrada = new ArrayBlockingQueue<Bloco>(100);
	}
	
	@Override
	public Bloco get(){
		try {
			return entrada.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	@Override
	public void set(Bloco bloco) {
		// TODO Auto-generated method stub
		try {
			entrada.put(bloco);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
