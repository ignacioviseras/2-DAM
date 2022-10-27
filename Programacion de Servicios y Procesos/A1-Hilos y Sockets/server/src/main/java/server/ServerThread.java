package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Book;

public class ServerThread extends Thread {

	private static int id;

	private Thread thread;
	private Socket socket;
	private String name;
	private long born;
	private Book book;
	
	private List<String> data;

	public ServerThread(Socket socket, String name) {

		id++;

		this.socket = socket;
		this.name = name + "-" + id;
		this.born = System.currentTimeMillis();
		
		thread = new Thread(this, "Cliente-" + id);
		thread.start();
	}

	@Override
	public void run() {
		System.out.println("Estableciendo comunicación con " + thread.getName());
		try {

			//InputStream input = socket.getInputStream();
			PrintStream output = new PrintStream(socket.getOutputStream());
			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			BufferedReader bf = new BufferedReader(input);

			
			//El servidor se quedaría aquí parado hasta que el cliente nos mande
			//informacion, es decir, cuando haga un salida.println(INFORMACION);				
			setData(bf.readLine());
			
			System.out.println("Client "+id+" - " + data);
			
			while (!data.get(0).equalsIgnoreCase("0")) {
					
				if (data.get(0).equalsIgnoreCase("1")) {
					// consultar por isbn
					
					System.out.println(data.get(1));
					
					output.println("funcionaaaaaaaaaaaaa"); // envia la info al cliente
				}
				
				if (data.get(0).equalsIgnoreCase("2")) {
					// consultar por titulo
					
					output.println(book.toString());
					
				}
				if (data.get(0).equalsIgnoreCase("3")) {
					// consultar por autor
					
					output.println(book.toString());
				}
				
				if (data.get(0).equalsIgnoreCase("4")) {
					// añadir libro
					
					
					output.println(book.toString());
				}
				
				
				setData(bf.readLine());
				
			}
			
			output.write("Hasta pronto, gracias por establecer conexión".getBytes());
			System.out.println(thread.getName() + " ha cerrado la comunicación. ");
					
			
			input.close();
			output.close();
			socket.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 
	}

	public List<String> getData() {
		return data;
	}

	public void setData(String string) {
		this.data = Arrays.asList(string.split("-"));
	}
	
	
}
