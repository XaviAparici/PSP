package AE03;

public class Minero implements Runnable {
	
	private int bolsa;
	private int tiempoExtraccion;
	private Mina mina;
	
	public int getBolsa() {
		return bolsa;
	}

	public void setBolsa(int bolsa) {
		this.bolsa = bolsa;
	}

	public int getTiempoExtraccion() {
		return tiempoExtraccion;
	}

	public void setTiempoExtraccion(int tiempoExtraccion) {
		this.tiempoExtraccion = tiempoExtraccion;
	}

	public Mina getMina() {
		return mina;
	}

	public void setMina(Mina mina) {
		this.mina = mina;
	}
	
	public Minero(Mina mina) {
		this.bolsa = 0;
		this.tiempoExtraccion = 1000;
		this.mina = mina;
	}
	
	@Override
	public void run() {
		extraerRecurso();
	}

	//en synchronized (this) tots els fils treballen pero el total en alguna ejeccució es major a 10000
	//en synchronized (mina) el fil 1 trau els 10000 pero el total sempre es 10000
	//extraerRecurso
	//mentre en la mina hi han recursos entra i suma 1 a la bolsa del miner (fil) i resta 1 al stock de la mina
	//Entrada: res
	//Eixida: void
	public void extraerRecurso () {
		synchronized (this) {
			while(mina.getStock()>0) {
				bolsa = bolsa + 1;
				mina.setStock(mina.getStock()-1);
				System.out.println("El minero "+Thread.currentThread().getName()+" lleva "+bolsa+" recursos y saca otro más");
			}
		}
	}

	
}
