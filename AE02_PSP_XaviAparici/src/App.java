import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
	
	public static void lanzar(String nombre,String posicion, String velocidad){
		String clase = "Calculo";
		try {

			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;
			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(nombre);
			command.add(posicion);
			command.add(velocidad);

			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();
			//Process process = builder.start();
			process.waitFor();
			//System.out.println(process.exitValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		long inici = System.currentTimeMillis();//iniciem el contador del temps total de la aplicació
		
		String fichero = "C:\\Users\\DAM 2\\eclipse-workspace\\AE02_PSP_XaviAparici\\src\\NEOs.txt";//ruta del ficher dels meteoritos
		int cores = Runtime.getRuntime().availableProcessors();//metodo per a saber els cores del ordenador
		
		//a continuació lligc linea a linea el fixer i guarda cada dato en un array per a poder pasaro a parametro
		try {
			FileReader fr = new FileReader(fichero);
			String cadena = "";
			
			BufferedReader br = new BufferedReader(fr);
			
			for(int i=0; i<cores; i++) {
				cadena = br.readLine();
				String[] datos = cadena.split(",");
				lanzar(datos[0], datos[1], datos[2]);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long fi = System.currentTimeMillis();//parem el contador del temps
		double temps = (double) ((fi-inici)/1000);//calculem el total en segons
		System.out.println("Tiempo total de la aplicación: "+temps);
	
	}

}
