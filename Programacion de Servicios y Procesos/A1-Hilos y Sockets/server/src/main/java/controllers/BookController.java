package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import daos.BookDaoImpl;
import daos.IntBookDao;

public class BookController {
	
	public static void main(String[] args) {

		// pedimos al cliente que introduzca un numero
		System.out.println("por favor introduce el numero de la opcion deseada:\n" + " 1. Buscar todos los libros\n"
				+ " 2. Buscar un libro por ISBN\n" + " 3. Buscar uno por titulo\n" + " 4. Salir");

		// guardamos el numero en la variable orden
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();

		switch (number) {

		case 1:
			break;
		}

	}

}
