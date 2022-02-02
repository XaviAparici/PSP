package Ejercicis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		
		String requestParamValue = null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handleGetRequest(httpExchange);
			handleGETResponse(httpExchange, requestParamValue);
		} else if ("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange, requestParamValue);
		}
	}
	
	private String handleGetRequest(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
	}
	
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "<html><body>Hola"+requestParamValue+"</body></html>";
		try {
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String handlePostRequest(HttpExchange httpExchange) {
		InputStream inputStream = httpExchange.getRequestBody();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String postRequest = "";
		String linea = "";
		
		try {
			linea = br.readLine();
			while (linea != null) {
				postRequest = postRequest + linea;
				postRequest = postRequest +"\n";
				linea = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Recibido "+postRequest);
		return postRequest;
	}
	
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
	
}