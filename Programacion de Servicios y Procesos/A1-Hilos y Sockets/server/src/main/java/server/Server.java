package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import beans.Book;

public class Server {

	
	private static final int PORT=2000;
	
	public static void main(String[] args) {
		
		ArrayList<Book> books = new ArrayList();
		books.add(new Book(12345, "Como desarrollar una mente prodijiosa", "Ramón Campayo", 16));
		books.add(new Book(12345, "Como desarrollar una mente prodijiosa", "Ramón Campayo", 16));
		books.add(new Book(12345, "Como desarrollar una mente prodijiosa", "Ramón Campayo", 16));
		books.add(new Book(12345, "Como desarrollar una mente prodijiosa", "Ramón Campayo", 16));
	
	
		

		
		System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");
		System.out.println("----------------------------------");
		try {
			
			ServerSocket server = new ServerSocket();
			// InetSocketAddress direction = new InetSocketAddress("ip public",PORT);
			InetSocketAddress direction = new InetSocketAddress(PORT); //localhost

			server.bind(direction);

			System.out.println("Servidor listo para aceptar solicitudes");
			System.out.println("Dirección IP: " + direction.getAddress());
			
			while (true) {
				Socket socket = server.accept();
				System.out.println("Comunicación entrante");
				new ServerThread(socket, "thread-"+direction.getHostName());
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}