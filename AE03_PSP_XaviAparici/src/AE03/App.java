package AE03;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//cree la mina amb 10000 de stock
		Mina mina = new Mina(10000);
		//cree la variable minero i el fil
		Minero minero;
		Thread t;
		ArrayList<Minero> listaMineros = new ArrayList();
		int sumaRecoleccion = 0;
		int contadorMineros=0;
		
		//cree els miners i els inicie, els anyadixc a un arrayList per a despres traure els recursos recolectats
		for(int i=0;i<=10;i++) {
			minero = new Minero(mina);
			t = new Thread(minero);
			t.start();
			listaMineros.add(minero);
		}
		
		Thread.sleep(1000);
		
		//recorrec el arrayList de miners i mostre cada miner el que ha tret de la mina
		System.out.println();
		for(Minero m : listaMineros) {
			sumaRecoleccion = m.getBolsa() + sumaRecoleccion;
			System.out.println("Minero "+contadorMineros+" ha sacado "+m.getBolsa());
			contadorMineros++;
		}
		
		//mostre la suma total de recursos extrets de la mina
		System.out.println();
		System.out.println("En total han recolectado: "+sumaRecoleccion);

	}

}
