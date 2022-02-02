package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Peticion implements Runnable {

	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;
	
	public Peticion(Socket socket) {
		this.socket = socket;
	}
	
	//encriptar
	//le pasamos la contraseña y la pasa al siguiente caracter ASCII
	//Entrada: String contraseña
	//Salida: String
	public static String encriptar(String contraseña) {
		String encriptada="";
		for(int i=0;i<contraseña.length();i++) {
			int j = contraseña.charAt(i);
			encriptada = encriptada + String.valueOf((char)(j+1));
		}
		return encriptada;
	}
	
	@Override
	public void run() {
		try {
			Contraseña c = new Contraseña();
			ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
			outObjeto.writeObject(c);
			ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
			try {
				Contraseña cMod = (Contraseña) inObjeto.readObject();
				System.err.println("SERVIDOR >> Recibo del cliente: " + cMod.getContraseña());
				cMod.setContraseñaEncriptada((encriptar(cMod.getContraseña())));
				outObjeto.writeObject(cMod);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			outObjeto.close();
			inObjeto.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR >>> Error");
		}
	}

}
