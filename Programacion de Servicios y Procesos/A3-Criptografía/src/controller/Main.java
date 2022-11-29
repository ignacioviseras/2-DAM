package controller;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main {
	
	public static final Scanner scanner = new Scanner(System.in);
	
	public static String nextLine() {
		return scanner.nextLine();
	}
	
	public static void main(String[] args) {
		/*Boolean salir = false;
		while(salir == false) {
			System.out.println("===========Menu==========");
			System.out.println("1. Encriptar frase" + "\n" +"2. Desencriptar frase" + "\n" +"3. Salir del programa" + "\n");
			
			String opcion = nextLine();
			if(opcion.equals("1")) {
				System.out.println("******Introduce una frase******");
				String frase = nextLine();
				
			}
			if(opcion.equals("2")) {
				
				System.out.println("Desencriptando frase ->");
				
			}
			if(opcion.equals("3")) {
				System.out.println("******Saliendo del programa******");
				salir = true;
			}
			
		}*/
		
		try {
			//creamos un generador de claves
			KeyGenerator genKey = KeyGenerator.getInstance("AES");
			
			//creamos la clave
			SecretKey key = genKey.generateKey();
			
			//el cifrador nos permitira cifrar y descifrar
			Cipher cipher = Cipher.getInstance("AES");
			
			String message = "Hola como estas";
			
			byte[] bytesMessage = message.getBytes();
			
			//----Cifrar----
			//le de decimos al cifrador que queremos hacer la accion de CIFRAR Cipher.ENCRYPT_MODE
			//y que la clave que usaremos sera la de la variable key
			cipher.init(Cipher.ENCRYPT_MODE, key);
			//como el cipher trabaja solo con bytes tenemos que convertir el string a byte
			byte[] bytesEncryptedMessage = cipher.doFinal(bytesMessage);
			//una vez cifrado lo volvemos a pasar a string para asi poder mostrarlos
			String EncryptedMessage = new String(bytesEncryptedMessage);
			
			System.out.println("mensaje sin cifrar-> " + message);
			System.out.println("mensaje cifrado-> " + EncryptedMessage);
			
			System.out.println("=========================");
			//----Descifrar----
			//le de decimos al cifrador que queremos hacer la accion de DESCIFRAR Cipher.DECRYPT_MODE
			//y que la clave que usaremos sera la de la variable key
			cipher.init(Cipher.DECRYPT_MODE, key);
			//como el cipher trabaja solo con bytes tenemos que convertir el string a byte
			byte[] bytesDecryptedMessage = cipher.doFinal(bytesEncryptedMessage);
			//una vez cifrado lo volvemos a pasar a string para asi poder mostrarlos
			String DecryptedMessage = new String(bytesDecryptedMessage);
			
			
			System.out.println("mensaje sin cifrar-> " + EncryptedMessage);
			System.out.println("mensaje cifrado-> " + DecryptedMessage);
		} catch (GeneralSecurityException e) {
			System.out.println("Error->  " + e.getMessage());
			e.printStackTrace();
		}
			
	}

}
