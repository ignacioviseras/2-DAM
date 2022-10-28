package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


//esto es el socket de la parte cliente
public class ClientMain {

	//indicamos los puertos e ip que usaremos
	public static final int PORT = 2000;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) {
		System.out.println("*_		CLIENT APP		_*");
		System.out.println("--------------------------");

		//Socket -> es la clase que nos va a permitir comunicarnos con el servidor

		//InputStreamReader entrada -> Entrada de datos.
		//Es el canal de entrada del cliente, es decir
		//Canal por el cual el servidor nos va a mandar la información.

		//PrintStream salida -> Salida de datos.
		//Es el canal de salida del cliente, es decir.
		//Canal por el cual vamos a enviar información al servidor.

		//servDirection Es un objeto que almacena localhost y el puerto
		InetSocketAddress servDirection = new InetSocketAddress(IP_SERVER, PORT);


		//para asegurarnos de que todos lo que creemos sea cerrado cuando
		//termine la ejecucion usamos try-catch
		//----------------
		//otra opcion seria utilizar el metodo close() dentro del bloque finally (esto es mas complejo)
		try (Scanner sc = new Scanner(System.in)){
			Socket socketToServ = new Socket();

			//---------Aqui se establece la conexion----------
			System.out.println("Client-> Waiting to conect to the server");
			socketToServ.connect(servDirection);//Esto realiza la conexion
			System.out.println("Client-> Connection established " + IP_SERVER + " port used-> " + PORT +"\n");

			//--------Creamos el objeto que usaremos para leer la salida del servidor-----------
			InputStreamReader input = new InputStreamReader(socketToServ.getInputStream());

			//El metodo InputStreamReader() lee datos caracter a caracter -> ejemplo h o l a
			//Como queremos las "palabras" enteras usamos BufferedReader -> ejemplo hola
			BufferedReader buffer = new BufferedReader(input);

			//-----------Mandar info al servidor---------------
			//Vamos a crear un objeto q nos permita mandar la info al servidor
			PrintStream out = new PrintStream(socketToServ.getOutputStream());

			String InfoActions = "";
			boolean continues = true;
			do {
				System.out.println("########################");
				System.out.println("Insert a option:");
				System.out.println(" 1. Search by ISBN");
				System.out.println(" 2. Search by Tittle");
				System.out.println(" 3. Search by Author");
				System.out.println(" 4. Add book");
				System.out.println(" 5. Exit");

				//--------OJO---------
				//Cuando enviamos un dato mediante un socket lo tenemos que enviar en FORMATO CADENA
				InfoActions = sc.nextLine();//aqui recibiremos la opcion que se insertara

				//⬇⬇mandamos la informacion usando el stream
			
				out.println(InfoActions);
			
				System.out.println("Client-> Waiting for a result from the server");

				//------Ahora esperaremos hasta que el servidor nos responda------
				String result = buffer.readLine();

				if("5".equalsIgnoreCase(InfoActions)) {//en caso de ser 4 nos sacara del programa
					continues = false;
				}else {
					System.out.println(result);
				}

				//------¿Como se cerrara la ejecucion?-------
				//Como no se usa el close automaticamente el objeto socket
				//nos cerrara la ejecucion cuando salgamos del try-catch
			}while(continues);
			//Cerramos la conexion
			socketToServ.close();
		}catch (UnknownHostException e) {
			System.err.println("Client-> Can't find the server at the address" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Client-> Error input/output");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Client-> Error -> " + e);
			e.printStackTrace();
		}
		System.out.println("Client-> Program end");
	}
}
