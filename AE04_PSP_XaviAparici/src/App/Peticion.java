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
	//le pasamos la contrase�a y la pasa al siguiente caracter ASCII
	//Entrada: String contrase�a
	//Salida: String
	public static String encriptar(String contrase�a) {
		String encriptada="";
		for(int i=0;i<contrase�a.length();i++) {
			int j = contrase�a.charAt(i);
			encriptada = encriptada + String.valueOf((char)(j+1));
		}
		return encriptada;
	}
	
	@Override
	public void run() {
		try {
			Contrase�a c = new Contrase�a();
			ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
			outObjeto.writeObject(c);
			ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
			try {
				Contrase�a cMod = (Contrase�a) inObjeto.readObject();
				System.err.println("SERVIDOR >> Recibo del cliente: " + cMod.getContrase�a());
				cMod.setContrase�aEncriptada((encriptar(cMod.getContrase�a())));
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
