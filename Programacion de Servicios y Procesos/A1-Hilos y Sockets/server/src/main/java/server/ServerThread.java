package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private static int id;

	private Thread thread;
	private Socket socket;
	private String name;
	private long born;

	public ServerThread(Socket socket, String name) {

		id++;

		this.socket = socket;
		this.name = name;
		this.born = System.currentTimeMillis();

		thread = new Thread(this, "Cliente-" + id);
		thread.start();
	}

	@Override
	public void run() {
		System.out.println("Estableciendo comunicación con " + thread.getName());
		try {

			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			String data = "";

			while (!data.trim().equals("FIN")) {
				
				byte[] mensaje = new byte[100];
				input.read(mensaje);
				data = new String(mensaje);
				
				
				if (data.trim().equals("FIN")) {
					output.write("Hasta pronto, gracias por establecer conexión".getBytes());
					System.out.println(thread.getName() + " ha cerrado la comunicación. ");
					
				} else {
					System.out.println(thread.getName() + " dice: " + data);
					output.write(("Tu mensaje tiene " + data.trim().length() + " caracteres").getBytes());
				}
			}
			
			input.close();
			output.close();
			socket.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 

	}

}
