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
	public static boolean waitting;

	private Thread thread;
	private Socket socket;
	private String name;
	private long born;

	private BookDaoImpl book;

	public PrintStream output; // para recibir datos del cliente
	public ScreenStream input; // para enviar datos al cliente
	public ArrayList<String> data;

	public ServerThread(Socket socket, String name) {

		id++;

		this.socket = socket;
		this.name = name + "-" + id;
		this.born = System.currentTimeMillis();

		this.book = new BookDaoImpl();

		try {
			this.input = new ScreenStream(socket.getInputStream());
			this.output = new PrintStream(socket.getOutputStream());

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		this.thread = new Thread(this, "Cliente-" + id);
		this.thread.start();
		System.out.println(thread.getName() + " communication openned. ");
	}

	@Override
	public void run() {
		System.out.println("Estableciendo comunicación con " + thread.getName());
		try {
			do {

				// El servidor se quedaría aquí parado hasta que el cliente nos mande
				// informacion, es decir, cuando haga un salida.println(INFORMACION);
				String str = input.read();

				// Generamos la arraylist con la entrada de informacion desde el cliente
				String[] strSplit = str.split("-");
				data = new ArrayList<String>(Arrays.asList(strSplit));

				if (data.get(0).equalsIgnoreCase("1")) {
					// consultar por isbn
					try {
						output.println("" + book.findByIsbn(Integer.parseInt(data.get(1))));

					} catch (NumberFormatException e) {
						output.println("number format error");
					}
				}

				if (data.get(0).equalsIgnoreCase("2")) {

					try {
						output.println(book.findByTitle(data.get(1)));

					} catch (Exception e) {
						output.println("Exception ->" + e.getMessage());
					}
				}

				if (data.get(0).equalsIgnoreCase("3")) {
					// consultar por autor

					try {
						output.println(book.findByAuthor(data.get(1)));

					} catch (Exception e) {
						output.println("Exception ->" + e.getMessage());
					}
				}

				if (data.get(0).equalsIgnoreCase("4")) {
					// añadir libro
					addOneBookOnTime();

				}

			} while (!data.get(0).equalsIgnoreCase("5"));

			output.write("Hasta pronto, gracias por establecer conexión".getBytes());
			socket.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println(thread.getName() + " communication closed .");
		}
	}

	/**
	 * Agrega un libro a la vez para evitar problemas.
	 */
	public synchronized void addOneBookOnTime() {
		try {

			int a = book.addBook(
					new Book(Integer.parseInt(data.get(1)), data.get(2), data.get(3), Integer.parseInt(data.get(1))));
			
			if (a==0) 
				output.println("Book created. Thank you so much, nextime gonna to cry in stackoverflow");
			
		} catch (Exception e) {
			output.println("Exception -> " + e.getMessage());

		}
	}
}
