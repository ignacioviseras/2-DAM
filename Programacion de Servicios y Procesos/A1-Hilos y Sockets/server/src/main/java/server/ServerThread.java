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
import daos.BookDaoImpl;

public class ServerThread extends Thread {
	
	private static int id;

	private Thread thread;
	private Socket socket;
	private String name;
	private long born;
	private BookDaoImpl book;

	public ServerThread(Socket socket, String name) {

		id++;
		
		this.socket = socket;
		this.name = name + "-" + id;
		this.born = System.currentTimeMillis();

		this.thread = new Thread(this, "Cliente-" + id);
		this.thread.start();
		
		this.book = new BookDaoImpl();
	}

	@Override
	public synchronized void run() {
		System.out.println("Estableciendo comunicaci�n con " + thread.getName());
		try {

			PrintStream output = new PrintStream(socket.getOutputStream()); // para enviar datos al cliente
			ScreenStream input = new ScreenStream(socket.getInputStream()); // para recibir datos del cliente
			
				
			ArrayList<String> data;
			do {

				// El servidor se quedar�a aqu� parado hasta que el cliente nos mande
				// informacion, es decir, cuando haga un salida.println(INFORMACION);
				String str = input.read();
				
				// Generamos la arraylist con la entrada de informacion desde el cliente
				String[] strSplit = str.split("-"); 
				data = new ArrayList<String>(Arrays.asList(strSplit));

				
				if (data.get(0).equalsIgnoreCase("1")) {
					// consultar por isbn
					try {
						output.println(""+book.findByIsbn(Integer.parseInt(data.get(1))));
						
					} catch (NumberFormatException e) {
						output.println("number format error");
					}
				}

				if (data.get(0).equalsIgnoreCase("2")) {
				
					try {
						output.println(book.findByTittle(data.get(1)));
						
					} catch (Exception e) {
						output.println("Exception ->"+e.getMessage());
					}
				}

				if (data.get(0).equalsIgnoreCase("3")) {
					// consultar por autor
					try {
						output.println(book.findByAuthor(data.get(1)));
						
					} catch (Exception e) {
						output.println("Exception ->"+e.getMessage());
					}
				}

				if (data.get(0).equalsIgnoreCase("4")) {
					// a�adir libro
					
					try {
						wait(232232); // pone los demas hilos en espera
						
						output.println(book.addBook(
								new Book(
										Integer.parseInt(data.get(1)),
										data.get(2),
										data.get(3),
										Integer.parseInt(data.get(1))
										)
								));
						
						
					} catch (Exception e) {
						output.println("Exception ->"+e.getMessage());
					}
					notify(); // activa los demas hilos que estaban wait()
				}
				
				
				// devovler resultado final de la consulta
				//output.println(book.toString()); 
				
			} while (!data.get(0).equalsIgnoreCase("5"));
			
			
			output.write("Hasta pronto, gracias por establecer conexi�n".getBytes());
			socket.close();
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println(thread.getName() + " ha cerrado la comunicaci�n. ");
		}
	}
}
