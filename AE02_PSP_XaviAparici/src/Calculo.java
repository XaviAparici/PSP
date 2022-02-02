import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Calculo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long inici = System.currentTimeMillis();//iniciem el contador del temps per a que començe a contar
		
		double posicionNEO = Double.parseDouble(args[1]);
		double velocidadNEO = Double.parseDouble(args[2]);
		double posicionTierra = 1;
		double velocidadTierra = 100;

		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
			posicionNEO = posicionNEO + velocidadNEO * i;
			posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
				Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
		
		DecimalFormat df = new DecimalFormat("0.00");//definixc el format a dos decimals
		
		String resultat = df.format(resultado);//pase el resultat a dos decimals
		System.out.println(resultat);
		
		//comprobe si es major que 10 i si es aixi pose la alerta i sino no
		if(resultado>10) {
			System.err.println("Alerta Mundial");
		}
		else {
			System.out.println("Tranquilidad");
		}
		
		//a continuació cree cada archiu amb el nom de cada meteorit i escriu el percentaje de cadascún
			try {
				File fichero = new File(args[0] + ".txt");
				
				if (!fichero.exists()) {
					fichero.createNewFile();
				}
				
				FileWriter fr = new FileWriter(fichero);
				BufferedWriter br = new BufferedWriter(fr);
				br.write(resultat+"%");
				br.close();
				
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			long fi = System.currentTimeMillis();//parem el contador de temps
			
			double temps = (double) ((fi-inici)/1000);//fem el calcul total en segons
			System.out.println("Tiempo por meteorito: "+temps);
		
	}

}
