package App;

import java.io.Serializable;

public class Contrase�a implements Serializable {
	
	String contrase�a;
	String contrase�aEncriptada;
	
	//Contrase�a
	//metodo constructor que crea las contrase�as vacias
	public Contrase�a() {
		this.contrase�a = "";
		this.contrase�aEncriptada = "";
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getContrase�aEncriptada() {
		return contrase�aEncriptada;
	}

	public void setContrase�aEncriptada(String contrase�aEncriptada) {
		this.contrase�aEncriptada = contrase�aEncriptada;
	}

}
