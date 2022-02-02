package App;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] arg) throws UnknownHostException, IOException, ClassNotFoundException {
		Scanner teclado = new Scanner(System.in);
		String contraseña;
		String host = "localhost";
		int puerto = 1234;
		System.out.println("CLIENTE >> Arranca cliente");
		Socket cliente = new Socket(host, puerto);
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Contraseña c = (Contraseña) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + c.getContraseña());
		System.out.println("Introduce la contraseña:");
		contraseña = teclado.next();
		c.setContraseña(contraseña);
		ObjectOutputStream pMod = new ObjectOutputStream(cliente.getOutputStream());
		pMod.writeObject(c);
		System.out.println("CLIENTE >> Envio al servidor: " + c.getContraseña());
		Contraseña cMod = (Contraseña) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + cMod.getContraseñaEncriptada());
		inObjeto.close();
		pMod.close();
		cliente.close();
	}

}
