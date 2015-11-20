package compactadores;

import armazenamento.EntradaArmazem;
import esteiras.Esteira;
import misc.Material;
import sistemaCentral.Recicladora;

/**
 * Dispositivo compactador de Plastico
 */
public class Compactador_Plastico implements Dispositivo, Runnable {

	Esteira esteira;
	EntradaArmazem entrada_armazem;
	Recicladora sc;
	int qtdParaBloco;
	int sobras;
	int qtdBlocos;

	Compactador_Plastico(int qtdParaBloco) {
		this.qtdParaBloco = qtdParaBloco;
		sobras = 0;
		qtdBlocos = 0;
	}

	@Override
	public void initialize(Recicladora sc, Esteira esteira, EntradaArmazem entrada_armazem) {
		this.sc = sc;
		this.esteira = esteira;
		this.entrada_armazem = entrada_armazem;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Dispositivo Compactador de Plastico LIGADO...");
		Material mat;
		while (sc.getFuncionando()) {
			mat = esteira.get();
			if (!mat.toString().equalsIgnoreCase("desligar")) {
				sobras++;
				// System.out.println(mat + " " + i);
				if (sobras == qtdParaBloco) {
					compacta();
					sobras = 0;
				}
			}
		}
		System.out.println("Dispositivo Compactador de Plastico DESLIGADO");
		exibirLog();
	}
	public void exibirLog() {
		System.out.println("--------------------------------------------------");
		System.out.println("                     PLASTICO                     ");
		System.out.println("--------------------------------------------------");
		System.out.println("Materiais mandados para a esteira: " + ((qtdBlocos * qtdParaBloco) + sobras));
		System.out.println("Blocos compactados: " + qtdBlocos);
		System.out.println("Sobras: " + sobras);
		System.out.println("..................................................");
		System.out.println();
	}
	@Override
	public void compacta() {
		// TODO Auto-generated method stub
		Bloco bloco = new Bloco("plastico", qtdParaBloco);
		entrada_armazem.set(bloco);
		System.out.println("Bloco compactado: plastico...");
		qtdBlocos++;
	}

	
	

	
}
