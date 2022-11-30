package controller;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import beans.Users;

public class Main {
	
	private Users user;
	private static List<Users> list;
	
	public static final Scanner scanner = new Scanner(System.in);
	
	public static String nextLine() {
		return scanner.nextLine();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println("lilsta " + list);
		/*int intento = 0;
		byte[] texto = "asd".getBytes();
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(texto);
		
		byte[] resumen = md.digest();
		String mensaje = new String(resumen);
		System.out.println("hash 512 " + mensaje);
		
		MessageDigest nuevo = MessageDigest.getInstance("SHA-512");
		String passwd = nextLine();
		byte[] passwdByte = passwd.getBytes();
		nuevo.update(passwdByte);
		byte[] hashPasswd = nuevo.digest();
		String limpio = new String(hashPasswd);
		System.out.println("hash scanner " + limpio);
		if(intento <= 3) {
			if(limpio.equals(mensaje)) {
				System.out.println("son iguales");
			}else {
				intento++;
				System.out.println("Contraseña erronea intento nº "+ intento +"/3");
			}
		}else {
			System.out.println("Numero de intentos superado");	
			
		}*/
		
		/*try {
			//creamos un generador de claves
			KeyGenerator genKey = KeyGenerator.getInstance("AES");
			//creamos la clave
			SecretKey key = genKey.generateKey();
			//el cifrador nos permitira cifrar y descifrar
			Cipher cipher = Cipher.getInstance("AES");
			
			Boolean salir = false;
			String EncryptedMessage = "";
			String DecryptedMessage;
			String message;
			byte[] bytesMessage;
			byte[] bytesDecryptedMessage = new byte [0];
			byte[] bytesEncryptedMessage = new byte [0];

			while(salir == false) {
				System.out.println("===========Menu==========");
				System.out.println("1. Encriptar frase" + "\n" +"2. Desencriptar frase" + "\n" +"3. Salir del programa" + "\n");
				

				String opcion = nextLine();
				if(opcion.equals("1")) {
					System.out.println("******Introduce una frase******");
					message = nextLine();
					bytesMessage = message.getBytes();
					//----Cifrar----
					//le de decimos al cifrador que queremos hacer la accion de CIFRAR Cipher.ENCRYPT_MODE
					//y que la clave que usaremos sera la de la variable key
					cipher.init(Cipher.ENCRYPT_MODE, key);
					//como el cipher trabaja solo con bytes tenemos que convertir el string a byte
					bytesEncryptedMessage = cipher.doFinal(bytesMessage);
					//una vez cifrado lo volvemos a pasar a string para asi poder mostrarlos
					EncryptedMessage = new String(bytesEncryptedMessage);
					
					System.out.println("mensaje sin cifrar-> " + message);
					System.out.println("mensaje cifrado-> " + EncryptedMessage);
				}
				if(opcion.equals("2")) {
					//----Descifrar----					
					//le de decimos al cifrador que queremos hacer la accion de DESCIFRAR Cipher.DECRYPT_MODE
					//y que la clave que usaremos sera la de la variable key
					cipher.init(Cipher.DECRYPT_MODE, key);
					//como el cipher trabaja solo con bytes tenemos que convertir el string a byte
					bytesDecryptedMessage = cipher.doFinal(bytesEncryptedMessage);
					//una vez cifrado lo volvemos a pasar a string para asi poder mostrarlos
					DecryptedMessage = new String(bytesDecryptedMessage);
					
					
					System.out.println("mensaje cifrado-> " + EncryptedMessage);
					System.out.println("mensaje descifrado-> " + DecryptedMessage);
				}
				if(opcion.equals("3")) {
					System.out.println("******Saliendo del programa******");
					salir = true;
				}
			}
		} catch (GeneralSecurityException e) {
			System.out.println("Error->  " + e.getMessage());
			e.printStackTrace();
		}*/
	}
			
}
