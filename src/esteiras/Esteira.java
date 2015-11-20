package esteiras;

import java.util.concurrent.ArrayBlockingQueue;

import misc.Material;

public class Esteira implements EsteiraInterface{
	/*esta classe representa uma esteira que recebe itens dos recicladores e manda
	 * os dispositivos associados. Vamos representar cada esteira como um buffer visto 
	 * em aula, utilize aqui a estrutura que você acha mais adequada para sincronização entre
	 * os recicladores e os dispositivos compactadores*/
	String tipoMaterial;
	ArrayBlockingQueue<Material> buffer;
	
	public Esteira(String tipoMaterial){
		this.tipoMaterial = tipoMaterial; 
		buffer = new ArrayBlockingQueue<>(5);		 
	}
	
	public boolean ehVazia()
	{
		return buffer.isEmpty();
	}
	
	@Override
	public Material get() {
		// TODO Auto-generated method stub
		Material mat = null;
		try {
			mat = buffer.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mat;
	}

	@Override
	public void set(Material item) {
		// TODO Auto-generated method stub
		try {
			buffer.put(item);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
