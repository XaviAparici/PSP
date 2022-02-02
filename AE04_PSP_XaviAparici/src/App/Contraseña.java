package App;

import java.io.Serializable;

public class Contraseña implements Serializable {
	
	String contraseña;
	String contraseñaEncriptada;
	
	//Contraseña
	//metodo constructor que crea las contraseñas vacias
	public Contraseña() {
		this.contraseña = "";
		this.contraseñaEncriptada = "";
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getContraseñaEncriptada() {
		return contraseñaEncriptada;
	}

	public void setContraseñaEncriptada(String contraseñaEncriptada) {
		this.contraseñaEncriptada = contraseñaEncriptada;
	}

}
