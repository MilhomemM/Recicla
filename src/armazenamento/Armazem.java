package armazenamento;

import java.util.ArrayList;

import compactadores.Bloco;
import sistemaCentral.Recicladora;


public class Armazem extends Thread{
	
	EntradaArmazem entrada;
	Recicladora sc;
	Bloco blocoCompact;
	ArrayList<Bloco> deposito;
	
	public Armazem(EntradaArmazem entrada, Recicladora sc){
		this.entrada = entrada;
		this.sc = sc;
		deposito = new ArrayList<Bloco>();
	}
	
	public void run(){
		while(sc.getFuncionando()){
			try {
				blocoCompact = entrada.get();
				deposito.add(blocoCompact);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
