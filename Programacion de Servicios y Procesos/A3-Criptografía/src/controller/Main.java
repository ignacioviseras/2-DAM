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
import dao.UsersDaoImpl;

public class Main {
	
	private static UsersDaoImpl udao = new UsersDaoImpl();
	public static final Scanner scanner = new Scanner(System.in);
	
	public static String nextLine() {
		return scanner.nextLine();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		int intento = 0;
		while(intento < 3) {
			System.out.println("----introduce un nombre----");
			String nombre = nextLine();
			
			MessageDigest nuevo = MessageDigest.getInstance("SHA-512");
			System.out.println("----introduce una contraseña----");
			String passwd = nextLine();
			byte[] passwdByte = passwd.getBytes();
			nuevo.update(passwdByte);
			byte[] hashPasswd = nuevo.digest();
			String limpio = new String(hashPasswd);
			System.out.println("hash contraseña escrita " + limpio);
			System.out.println("hash contraseña del user " + udao.getCon(nombre));
			System.out.println("");
			if(limpio.equals(udao.getCon(nombre))) {
				try {
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
					int flag = 0;
					
					System.out.println("======Bienvenido "+ udao.findByName(nombre) +"======");
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
							flag = 1;
						}
						if(opcion.equals("2")) {
							if(flag != 0) {
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
							}else {
								System.out.println("---Tienes que encriptar una frase antes---");
							}
						}
						if(opcion.equals("3")) {
							System.out.println("******Saliendo del programa******");
							salir = true;
						}
					}
				} catch (GeneralSecurityException e) {
					System.out.println("Error->  " + e.getMessage());
					e.printStackTrace();
				}
			}else {
				intento++;
				System.out.println("Contraseña erronea intento nº "+ intento +"/3");
			}
		
		}
		System.out.println("Numero de intentos superado");	
		
		
	}
			
}
