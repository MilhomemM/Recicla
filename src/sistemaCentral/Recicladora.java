package sistemaCentral;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import armazenamento.Armazem;
import armazenamento.EntradaArmazem;
import atores.Gerente;
import atores.Reciclador;
import compactadores.Dispositivo;
import esteiras.Esteira;
import misc.Material;

public class Recicladora {
	// possui acesso a toda classe Recicladora
	public Gerente gerente;
	// dispositivos compactadores e esteiras
	public Dispositivo compMetal, compPapel, compVidro, compPL;
	public Esteira estMetal = null, estPapel = null, estVidro = null, estPL = null;
	// Armazem
	public Armazem armazem;
	public EntradaArmazem entradaArmazem;

	// sobras do dia
	public Vector<Material> sobras;

	// Recicladores
	public Vector<Reciclador> recicladores;
	// indica se a empresa esta aberta ou não
	boolean funcionando = false;

	/**
	 * Construtor da empresa Recicladora que representa uma empresa de
	 * reciclagem de materiais. Inicialmente existe uma fila de recicladores que
	 * todos os dias chegam até a empresa com o objetivo de depositar seus
	 * itens para o processo de reciclagem. A empresa possui um gerente que é
	 * responsável por inicializar e gerenciar todos os subsistemas.
	 */
	Recicladora() {
		gerente = new Gerente(this);
	}

	/**
	 * @author anderson Método responsavel pela inicialização do sistema
	 *         central da empresa, o responsável por ligar todos os subsistemas
	 *         é o gerente.
	 */
	public void iniciaSistema(String file) {

		gerente.ligaArmazem();

		gerente.ligaEsteiras();

		funcionando = true;

		gerente.ligaDispositivos();

		sobras = new Vector<Material>();

		abrePortas(file);

	}

	/**
	 * Através dessa interface o gerente permite que os recicladores que estão
	 * aguardando possam iniciar a separação dos materiais nas esteiras de
	 * cada dispositivo compactador
	 */
	public void abrePortas(String file) {
		System.out.println("Recicladora\tAbrindo portas...");
		filaDeEntrada(file);
		for (int i = 0; i < recicladores.size(); i++) {
			System.out.println("Reciclador " + recicladores.get(i).getId());
			System.out.print("Carrinho: ");
			recicladores.get(i).printCarrinho();
			System.out.println();
		}
		System.out.println("\n\n");
		gerente.gerenciamentoFila(); // S� Deus na causa
	}

	/**
	 * Essa interface permite organizar os recicladores em uma fila
	 */
	public void filaDeEntrada(String file) {
		System.out.println("Cadastrando recicladores");
		recicladores = new Vector<Reciclador>();
		Scanner scanner;
		int rec = 1;
		try {
			scanner = new Scanner(new FileReader(file));
			while (scanner.hasNextLine()) {
				Reciclador reciclador = new Reciclador(rec, this.estMetal, this.estVidro, this.estPapel, this.estPL);

				String line = scanner.nextLine();
				String materiais[] = line.split(";");
				for (int i = 0; i < materiais.length; i++) {

					Material tipo = new Material(materiais[i]);

					reciclador.adicionaCarrinho(tipo);

				}
				recicladores.add(reciclador);
				rec++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Foram precadastrados " + recicladores.size() + " recicladores\n");

	}

	public boolean getFuncionando() {
		return funcionando;
	}
	
	public void setFuncionando(boolean funcionando){
		this.funcionando = funcionando;
	}

	public static void main(String[] args) {
		Recicladora recicladora = new Recicladora();
		/* Inicializando o sistema da empresa */
		System.out.println("----------------------------------");
		System.out.println("Inicializando o sistema da empresa");
		System.out.println("----------------------------------");
		recicladora.iniciaSistema(args[0]);

	}
}
