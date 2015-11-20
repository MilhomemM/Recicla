package compactadores;

import armazenamento.EntradaArmazem;
import esteiras.Esteira;
import misc.Material;
import sistemaCentral.Recicladora;

/**
 * Dispositivo compactador de Plastico*/
public class Compactador_Papel implements Dispositivo, Runnable{

	Esteira esteira;
	EntradaArmazem entrada_armazem;
	Recicladora sc;
	int qtdParaBloco;
	int sobras;
	int qtdBlocos;
	
	Compactador_Papel(int qtdParaBloco){
		this.qtdParaBloco = qtdParaBloco;
		sobras = 0;
		qtdBlocos = 0;
	}
	
	@Override
	public void initialize(Recicladora sc, Esteira esteira, EntradaArmazem entrada_armazem){
		this.sc = sc;
		this.entrada_armazem = entrada_armazem;
		this.esteira = esteira;
	}

	@Override
	public void run() {
		System.out.println("Dispositivo Compactador de Papel LIGADO...");
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
		System.out.println("Dispositivo Compactador de Papel DESLIGADO");
		exibirLog();
	}
	
	public void exibirLog() {
		System.out.println("--------------------------------------------------");
		System.out.println("                     PAPEL                     ");
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
		Bloco bloco = new Bloco("papel", qtdParaBloco);
		entrada_armazem.set(bloco);
		System.out.println("Bloco compactado: papel...");
		qtdBlocos++;
	}


	
}
