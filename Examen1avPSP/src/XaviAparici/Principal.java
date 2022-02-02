package XaviAparici;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public void lanzarSubproceso(Integer n1){
		String clase = "XaviAparici.Subproceso";
		try {

			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			System.out.println(classpath);
			String className = clase;
			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(n1.toString());

			System.out.println("Comando que se pasa a ProcessBuilder: " + command);
			System.out.println("Comando a ejecutar en cmd.exe: " + command.toString().replace(",",""));

			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();
			process.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int numeroPersonas;
		
		System.out.println("Introduce el número de personas: ");
		numeroPersonas = teclado.nextInt();
		
		Principal llamada = new Principal();
		llamada.lanzarSubproceso(numeroPersonas);
		System.out.println("Llamada a Subproceso realizada con éxito");

	}

}
