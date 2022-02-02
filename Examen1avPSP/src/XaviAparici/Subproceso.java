package XaviAparici;

import java.util.ArrayList;
import java.util.Random;

public class Subproceso implements Runnable {
	
	static ArrayList<String> colores = new ArrayList();
	static String color;
	static ArrayList<String> coloresFavoritos = new ArrayList();
	
	@Override
	synchronized public void run() {
		coloresFavoritos.add(color);
		System.out.println(color);
	}
	
	public static int randomde0a4() {
		double numeroRandom = Math.random();
		numeroRandom = numeroRandom * 5 + 1;
		numeroRandom = numeroRandom - 1;
		int nRandom = (int) numeroRandom;
		return nRandom;
	}
	
	public Subproceso(String color) {
		this.color = color;
	}

	public static void main(String[] args) throws InterruptedException {
		Random random = new Random();
		
		colores.add("amarillo");
		colores.add("verde");
		colores.add("rojo");
		colores.add("azul");
		colores.add("naranja");
		
		int contPersonas = 0;
		int personas = 100;
		
		/*if(args!=null) {
			int n = Integer.parseInt(args[0]);
			personas = n;
		}*/
		
		Thread hiloPersona = null;
		
		for(int i=0 ; i<personas ; i++) {
			String colorAleatorio = colores.get(randomde0a4());
			Subproceso persona = new Subproceso(colorAleatorio);
			hiloPersona = new Thread(persona);
			hiloPersona.start();
			contPersonas++;
		}
		
		Thread.sleep(100);
		System.out.println("Número de personas: "+contPersonas);
		System.out.println("Tamaño lista creada: "+coloresFavoritos.size());
	}


}
