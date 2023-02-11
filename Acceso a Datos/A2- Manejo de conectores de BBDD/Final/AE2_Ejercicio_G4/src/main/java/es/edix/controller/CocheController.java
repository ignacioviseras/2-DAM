package es.edix.controller;


import java.util.Scanner;
import es.edix.modelo.entidad.Coche;
import es.edix.modelo.entidad.Pasajero;
import es.edix.modelo.persistencia.SqlDaoImpl;
import es.edix.modelo.persistencia.SqlDaoPasajeroImpl;

public class CocheController {
	
	public static SqlDaoImpl cocheDao = new SqlDaoImpl();
	public static final Scanner scanner = new Scanner(System.in);
	public static SqlDaoPasajeroImpl paDao= new SqlDaoPasajeroImpl();
	
	public static String nextLine() {
		return scanner.nextLine();
		
	}	
	
	
	public static void main(String[] args) {
		System.out.println("===========Iniciando Aplicacion============");
		Boolean continua = true;
		
		while (continua == true) {
			System.out.println("1. Añadir nuevo coche\n" + "2. Borrar coche por id\n"
					+ "3. Consulta coche por id\n" + "4. Listado de coches\n"
					+ "5. Modificar coche por id\n" + "6. Añadir pasajeros al coche\n"
					+ "7. Terminar el programa\n" );
			
			String opcion = nextLine();
			if(opcion.equals("1")) {//Añadir coche
				ftAddCar();
				
			}else if(opcion.equals("2")) {//Borrar coche por id
				ftDeleteCarById();
				
			}else if(opcion.equals("3")) {//Buscar coche
				ftFindCarById();
				
			}else if(opcion.equals("4")) {//listar coches
				ftListCar();		       
		        
			}else if(opcion.equals("5")) {//modificar coche por id
				ftModifyCar();
				
				
			}else if(opcion.equals("6")) {

					Boolean exitSub = true;
					while (exitSub == true) {
						System.out.println("8. Añadir nuevo pasajero\n" + "9. Borrar pasajero por id\n"
								+ "10. Consulta pasajero por id\n" + "11. Listado de pasajeros\n"
								+ "12. Añadir pasajero al coche\n" + "13. Listar pasajeros del coche\n"
								+ "14. Eliminar pasajero del coche\n" + "15. Salir del menu pasajeros\n" );
						String opas = nextLine();
						if(opas.equals("8")) {//Añadir pasajero
							ftAddPas();
														
						}else if(opas.equals("9")) {//Borrar pasajero por id
							ftDeletePasById();							
							
						}else if(opas.equals("10")) {//Buscar pasajero
							ftFindPasById();
							
						}else if(opas.equals("11")) {//Listar pasajeros
							ftListPas();       
					        
						}else if(opas.equals("12")) { //añadir pasajero al coche
							ftAddPasInCar();
														
						}else if(opas.equals("13")) {//listar pasajero de un coche			
							ftListOfPasInCar();
							
						}else if(opas.equals("14")) { //eliminar pasajero del coche
							ftDeletePasOfCar();
							
						}else if(opas.equals("15")) { //salir al menu principal pasajero del coche
							exitSub = false;
							ftExitMenuPas();
						}
					}
				
				
			}else if(opcion.equals("7")) {//terminamos la ejecucion del programa 
				
				continua = false;
				System.out.println("Vuelva cuando quiera");
			}
			
		}
	}
	

	public static void ftAddCar() {
		Coche p= new Coche();
		System.out.println("-----------Escribe la matrícula: ----------");
		p.setMatricula(nextLine());
		System.out.println("-----------Escribe el color: ----------");
		p.setColor(nextLine());
		System.out.println("-----------Escribe la marca: ----------");
		p.setMarca(nextLine());
		System.out.println("-----------Escribe el modelo: ----------");
		p.setModelo(nextLine());
		cocheDao.newCar(p);
	}
	
	public static void ftDeleteCarById() {
		System.out.println("-----------Escribe el ID del coche a borrar: ----------");
		int scaux = Integer.parseInt(nextLine());											
		cocheDao.deleteCar(scaux);
	}
	
	public static void ftFindCarById() {
		System.out.println("-----------Escribe el ID del coche a buscar: ----------");
		System.out.println(cocheDao.findById(Integer.parseInt(nextLine()))+ "\n");
	}
	
	public static void ftListCar() {
		System.out.println("----------Lista de coches completa: ----------");
		cocheDao.listOb();
	}
	
	public static void ftModifyCar() {
		System.out.println("-----------Escribe el ID del coche a modificar: ----------");
		int scaux = Integer.parseInt(nextLine());
		Coche cocheaux= cocheDao.findById(scaux);
		System.out.println("-----------Escribe la matrícula: ----------");
		cocheaux.setMatricula(nextLine());
		System.out.println("-----------Escribe el color: ----------");
		cocheaux.setColor(nextLine());
		System.out.println("-----------Escribe la marca: ----------");
		cocheaux.setMarca(nextLine());
		System.out.println("-----------Escribe el modelo: ----------");
		cocheaux.setModelo(nextLine());
		
		cocheDao.modifyCar(cocheaux);
	}
	
	public static void ftAddPas() {
		Pasajero p = new Pasajero();
		System.out.println("-----------Escribe el nombre: ----------");
		p.setNombre(nextLine());
		System.out.println("-----------Escribe la edad: ----------");
		int se = Integer.parseInt(nextLine());
		p.setEdad(se);
		System.out.println("-----------Escribe el peso: ----------");
		Double sp = Double.parseDouble(nextLine());
		p.setPeso(sp);
		
		paDao.addPassenger(p);
	}
	
	public static void ftDeletePasById() {
		System.out.println("-----------Escribe el ID del pasajero a borrar: ----------");
		paDao.deletePassenger(Integer.parseInt(nextLine()));
	}
	
	public static void ftFindPasById() {
		System.out.println("-----------Escribe el ID del pasajero a buscar: ----------");
		System.out.println(paDao.findById(Integer.parseInt(nextLine()))+ "\n");
	}
	
	public static void ftListPas() {
		System.out.println("----------Lista de pasajeros completa: ----------");
		paDao.passengerList();
	}
	
	public static void ftAddPasInCar() {
		cocheDao.listOb();
		System.out.println("---------- ID del coche  ----------");
		int idCoche = Integer.parseInt(nextLine());
		paDao.passengerList();
		System.out.println("---------- ID del pasajero  ----------");
		int idPasaj = Integer.parseInt(nextLine());
		paDao.setCar(idPasaj, idCoche);
	}
	
	public static void ftListOfPasInCar() {
		System.out.println("---------- ID del coche  ----------");
		paDao.passengerCarList(Integer.parseInt(nextLine()));
	}
	
	public static void ftDeletePasOfCar() {
		paDao.passengerList();
		System.out.println("---------- ID del pasajero  ----------");
		paDao.deleteCarPassenger(Integer.parseInt(nextLine()));
	}
	
	public static void ftExitMenuPas() {
		System.out.println("Saliendo del menu pasajeros");
	}
	
	
}
