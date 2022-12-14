package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import beans.Book;

public class Server {
	
	private static final int PORT=2000;
	private static final String IP_SERVER="localhost";
	public static void main(String[] args) {
		
		ArrayList<Book> books = new ArrayList<Book>();
		
		books.add(new Book(12345, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));
		books.add(new Book(23456, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));
		books.add(new Book(34567, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));
		books.add(new Book(45678, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));
		
		//voy
		
		System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");
		System.out.println("----------------------------------");
		
		try (ServerSocket serverSocket = new ServerSocket()) {

			InetSocketAddress direction = new InetSocketAddress(IP_SERVER,PORT); 
			serverSocket.bind(direction);
			
			System.out.println("INET: " + direction.getHostName());
			System.out.println("PORT: " + direction.getPort());
			System.out.println("IP: " + direction.getAddress());
			System.out.println("----------------------------------");
			System.out.println("Server socket ready to listen petitions...");
			
			while (true) {	
				Socket socket = serverSocket.accept();
				System.out.println("["+ direction.getAddress() +"]");
				new ServerThread(socket, "thread-"+direction.getHostName());
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}