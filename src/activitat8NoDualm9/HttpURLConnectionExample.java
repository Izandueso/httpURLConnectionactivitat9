package activitat8NoDualm9;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		Scanner teclado = new Scanner(System.in);
		
		HttpURLConnectionExample http = new HttpURLConnectionExample();
		
		System.out.println("Quieres utilizar el metodo GET o PUT?");
		System.out.println("Escribe 1 para utilizar GET");
		System.out.println("Escribe 2 para utilizar PUT");
		int getput = teclado.nextInt();
		
		if(getput == 1){
			System.out.println("Testing 1 - Send Http GET request");
			http.sendGet();
		}else if (getput == 2){
			System.out.println("\nTesting 2 - Send Http POST request");
			http.sendPost();
		}else{
			System.out.println("Numero introducido no valido");
		}

	}

	// HTTP GET request
	private void sendGet() throws Exception {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introdueix URL: http://www.insbaixcamp.org/");
		String url = teclado.nextLine();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	// HTTP POST request
	private void sendPost() throws Exception {
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Introdueix URL: http://www.insbaixcamp.org/");
		String url = teclado.nextLine();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "ca-es");
		
		//Query string
		String urlParameters = "categoryid=7";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}

}

