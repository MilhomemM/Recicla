package atores;

import java.util.Vector;

import esteiras.Esteira;
import misc.Material;

public class Reciclador implements Runnable {
	int id;
	Vector<Material> carrinho;
	Esteira estMetal;
	Esteira estVidro;
	Esteira estPapel;
	Esteira estPL;

	public Reciclador(int id, Esteira estMetal, Esteira estVidro, Esteira estPapel, Esteira estPL) {
		super();
		this.id = id;
		this.carrinho = new Vector<Material>();
		this.estMetal = estMetal;
		this.estVidro = estVidro;
		this.estPapel = estPapel;
		this.estPL = estPL;
	}

	@Override
	public void run() {
		System.out.println("Reciclador " + id + " separando materiais nas esteiras");
		for (int i = 0; i < carrinho.size(); i++) {
			switch (carrinho.get(i).toString()) {
			case "plastico":
				estPL.set(carrinho.get(i));
				break;
			case "papel":
				estPapel.set(carrinho.get(i));
				break;
			case "vidro":
				estVidro.set(carrinho.get(i));
				break;
			case "metal":
				estMetal.set(carrinho.get(i));
				break;
			}
		}
	}

	public void adicionaCarrinho(Material item) {
		carrinho.add(item);
	}

	public void printCarrinho() {
		System.out.print("<");
		for (int i = 0; i < this.carrinho.size(); i++) {
			System.out.print(this.carrinho.get(i));
			if (i < this.carrinho.size() - 1)
				System.out.print(", ");
			else
				System.out.println(">");
		}
	}

	public int getId() {
		return id;
	}

	public Vector<Material> getCarrinho() {
		return carrinho;
	}

}
