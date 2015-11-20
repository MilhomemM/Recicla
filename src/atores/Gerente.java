package atores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import armazenamento.Armazem;
import armazenamento.EntradaArmazem;
import compactadores.DispositivosFactory;
import esteiras.Esteira;
import misc.Material;
import sistemaCentral.Recicladora;

public class Gerente {
	Recicladora recicladora;

	public Gerente(Recicladora recicladora) {
		this.recicladora = recicladora;
	}

	public void ligaDispositivos() {
		System.out.println("Gerente: Ligando os dispositivos...");

		recicladora.compPL = DispositivosFactory.getDispositivo("plastico");
		recicladora.compPapel = DispositivosFactory.getDispositivo("papel");
		recicladora.compVidro = DispositivosFactory.getDispositivo("vidro");
		recicladora.compMetal = DispositivosFactory.getDispositivo("metal");

		recicladora.compPL.initialize(recicladora, recicladora.estPL, recicladora.entradaArmazem);
		recicladora.compPapel.initialize(recicladora, recicladora.estPapel, recicladora.entradaArmazem);
		recicladora.compVidro.initialize(recicladora, recicladora.estVidro, recicladora.entradaArmazem);
		recicladora.compMetal.initialize(recicladora, recicladora.estMetal, recicladora.entradaArmazem);

		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.execute((Runnable) recicladora.compPL);
		executor.execute((Runnable) recicladora.compPapel);
		executor.execute((Runnable) recicladora.compVidro);
		executor.execute((Runnable) recicladora.compMetal);

		executor.shutdown();

		System.out.println("Gerente: ...dispositivos ligados");
	}

	public void ligaEsteiras() {
		System.out.println("Gerente: Ligando esteiras...");

		recicladora.estPL = new Esteira("plastico");
		recicladora.estPapel = new Esteira("papel");
		recicladora.estVidro = new Esteira("vidro");
		recicladora.estMetal = new Esteira("metal");

		System.out.println("Gerente: ...esteiras ligadas");
	}

	public void gerenciamentoFila() {
		System.out.println("Gerente: Gerenciando fila.. Mandando os recicladores para as esteiras");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		for (int i = 0; i < this.recicladora.recicladores.size(); i++) {
			executor.execute(recicladora.recicladores.get(i));
		}

		executor.shutdown();

		while (!executor.isTerminated()) {
		}

		System.out.println("Gerente: Todos os recicladores já foram mandados para as esteiras");

		pararEsteiras();
		
		System.out.println("Gerente: Esteiras desligadas...");
		
		recicladora.setFuncionando(false);
		
		
	}

	public void pararEsteiras() {
		boolean parar = false;
		while (!parar) {
			if (recicladora.estPL.ehVazia())
				if (recicladora.estPapel.ehVazia())
					if (recicladora.estVidro.ehVazia())
						if (recicladora.estMetal.ehVazia())
							parar = true;
		}
		
		recicladora.estPL.set(new Material("desligar"));
		recicladora.estPapel.set(new Material("desligar"));
		recicladora.estVidro.set(new Material("desligar"));
		recicladora.estMetal.set(new Material("desligar"));
	}

	public void ligaArmazem() {
		System.out.println("Gerente\t\tLigando armazem...");
		recicladora.entradaArmazem = new EntradaArmazem();
		recicladora.armazem = new Armazem(recicladora.entradaArmazem, recicladora);
	}
}
