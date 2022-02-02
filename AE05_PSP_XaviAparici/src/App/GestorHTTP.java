package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler{

	private int temperaturaActual;
	private int temperaturaTermostato;
	
	//GestorHTTP
	//Sin parametros inicializa las temperaturas a 15 como dice el enunciado
	public GestorHTTP() {
		temperaturaActual = 15;
		temperaturaTermostato = 15;
	}
	
	//GestorHTTP
	public GestorHTTP(int temperaturaActual, int temperaturaTermostato) {
		this.temperaturaActual = temperaturaActual;
		this.temperaturaTermostato = temperaturaTermostato;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String requestParamValue = null;
		if ("GET".equals(exchange.getRequestMethod())) {
			requestParamValue = handleGetRequest(exchange);
			handleGETResponse(exchange, requestParamValue);
		} else if ("POST".equals(exchange.getRequestMethod())) {
			try {
				requestParamValue = handlePostRequest(exchange);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handlePOSTResponse(exchange, requestParamValue);
		}
	}
	
	//handleGetRequest
	//Arrepleguem la part de la url que volem(que es a partir del caracter ?) de la petició GET
	//Entrada: HttpExchange httpExchange
	//Eixida: String
	private String handleGetRequest(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().toString().split("\\?")[1];
	}
	
	//handleGETResponse
	//Generem el html amb les temperatures
	//Entrada: HttpExchange httpExchange, String requestParamValue
	//Eixida: void
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "<html><body>Temperatura actual: "+temperaturaActual+ " Temperatura termostato: "+temperaturaTermostato+"</body></html>";
		try {
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//handlePostRequest
	//Recibim el que ens pasen per el postman amb la petició POST
	//Entrada: HttpExchange httpExchange
	//Eixida: String
	private String handlePostRequest(HttpExchange httpExchange) throws InterruptedException {
		InputStream inputStream = httpExchange.getRequestBody();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String postRequest = "";
		String linea = "";
		String aux;
		
		try {
			linea = br.readLine();
			while (linea != null) {
				postRequest = postRequest + linea;
				//postRequest = postRequest +"\n";
				linea = br.readLine();
			}
			aux = postRequest.split("=")[1];
			regularTemperatura(aux);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return postRequest;
	}
	
	//handlePOSTResponse
	//Entrada: HttpExchange httpExchange, String requestParamValue
	//Eixida: void
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "Respuesta a la petición POST";
		try {
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//regularTemperatura
	//Dependiendo de la temperatura se va sumando o restando hasta llegar a la temperatura del termostato
	//Entrada: String temperatura
	//Eixida: void
	public void regularTemperatura(String temperatura) throws InterruptedException {
		temperaturaTermostato = Integer.parseInt(temperatura);
		if(temperaturaActual!=temperaturaTermostato) {
			if(temperaturaActual>temperaturaTermostato) {
				while(temperaturaActual!=temperaturaTermostato) {
					temperaturaActual--;
					Thread.sleep(5000);
				}
			}
			else {
				while(temperaturaActual!=temperaturaTermostato) {
					temperaturaActual++;
					Thread.sleep(5000);
				}
			}
		}
	}
	
	public int getTemperaturaActual() {
		return temperaturaActual;
	}

	public void setTemperaturaActual(int temperaturaActual) {
		this.temperaturaActual = temperaturaActual;
	}

	public int getTemperaturaTermostato() {
		return temperaturaTermostato;
	}

	public void setTemperaturaTermostato(int temperaturaTermostato) {
		this.temperaturaTermostato = temperaturaTermostato;
	}
	
}
