package controllers;

import java.util.Scanner;

import daos.IntBookDao;



public class BookController {
	public static IntBookDao bdao;
	
	
	public static void main(String[] args) {
		
		//pedimos al cliente que introduzca un numero
		System.out.println("por favor introduce el numero de la opcion deseada:\n"
				+ " 1. Buscar todos los libros\n"
				+ " 2. Buscar un libro por ISBN\n"
				+ " 3. Buscar uno por titulo\n"
				+ " 4. Salir");
		
		//guardamos el numero en la variable orden
		Scanner sc= new Scanner (System.in);
		int number = sc.nextInt();
		int isbn=sc.nextInt();
		
		
		
		switch (number) {
		
		case 1:
			bdao.booksList();
			break;
		case 2:
			bdao.searchOne(isbn);
			break;
		case 3:
			bdao.searchTittle();
			break;
		case 4:
			break;
		}
		
	}

}
