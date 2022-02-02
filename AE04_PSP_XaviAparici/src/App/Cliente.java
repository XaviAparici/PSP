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
		String contrase�a;
		String host = "localhost";
		int puerto = 1234;
		System.out.println("CLIENTE >> Arranca cliente");
		Socket cliente = new Socket(host, puerto);
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Contrase�a c = (Contrase�a) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + c.getContrase�a());
		System.out.println("Introduce la contrase�a:");
		contrase�a = teclado.next();
		c.setContrase�a(contrase�a);
		ObjectOutputStream pMod = new ObjectOutputStream(cliente.getOutputStream());
		pMod.writeObject(c);
		System.out.println("CLIENTE >> Envio al servidor: " + c.getContrase�a());
		Contrase�a cMod = (Contrase�a) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + cMod.getContrase�aEncriptada());
		inObjeto.close();
		pMod.close();
		cliente.close();
	}

}
