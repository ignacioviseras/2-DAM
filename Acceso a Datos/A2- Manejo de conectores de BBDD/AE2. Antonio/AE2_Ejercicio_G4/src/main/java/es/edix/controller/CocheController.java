package es.edix.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.edix.modelo.dao.IntCocheDao;
import es.edix.modelo.entidad.Coche;
import es.edix.modelo.entidad.Pasajero;
import es.edix.modelo.persistencia.SqlDaoImpl;
import es.edix.modelo.persistencia.SqlDaoPasajeroImpl;

public class CocheController {
	
	public static final Scanner scanner = new Scanner(System.in);
	
	public static String nextLine() {
		return scanner.nextLine();
		
	}	
	
		
				public static void main(String[] args) {
				System.out.println("===========Iniciando Aplicacion============");
				Boolean continua = true;
				SqlDaoImpl cocheDao = new SqlDaoImpl();
				
				
				
				while (continua == true) {
					
					System.out.println("1. Añadir nuevo coche\n" + "2. Borrar coche por id\n"
							+ "3. Consulta coche por id\n" + "4. Listado de coches\n"
							+ "5. Modificar coche por id\n" + "6. Añadir pasajeros al coche\n"+ "7. Terminar el programa\n" );
					String opcion = nextLine();
					if(opcion.equals("1")) {//Añadir coche
						
						Coche p= new Coche();
						System.out.println("-----------Escribe la matrícula: ----------");
						p.setMatricula(nextLine());
						System.out.println("-----------Escribe el color: ----------");
						p.setColor(nextLine());
						System.out.println("-----------Escribe la marca: ----------");
						p.setMarca(nextLine());
						System.out.println("-----------Escribe el modelo: ----------");
						p.setModelo(nextLine());
						cocheDao.newCar( p);
						
						
						
					}else if(opcion.equals("2")) {
						
						System.out.println("-----------Escribe el ID del coche a borrar: ----------");
						int scaux = Integer.parseInt(nextLine());											
						System.out.println(cocheDao.deleteCar(scaux)+ "\n");
						
					}else if(opcion.equals("3")) {//Buscar coche
						
						System.out.println("-----------Escribe el ID del coche a buscar: ----------");
						int scaux = Integer.parseInt(nextLine());											
						System.out.println(cocheDao.findById(scaux)+ "\n");
						
					}else if(opcion.equals("4")) {
						
						System.out.println("----------Lista de coches completa: ----------");
						System.out.println(cocheDao.listOb()+ "\n");
				       
				        
					}else if(opcion.equals("5")) {
						
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
						
						System.out.println(cocheDao.modifyCar(cocheaux)+ "\n");
						
					}else if(opcion.equals("6")) {
						System.out.println("-----------Escribe el ID del coche a gestionar pasajeros: ----------");
						System.out.println(cocheDao.listOb());
						int scaux = Integer.parseInt(nextLine());	
						if (cocheDao.findById(scaux) != null) {
							
							System.out.println(cocheDao.findById(scaux)+ "\n");
							SqlDaoPasajeroImpl paDao= new SqlDaoPasajeroImpl();
	
							Boolean exitSub = true;
							while (exitSub == true) {
								
								System.out.println(" 8. Añadir nuevo pasajero\n" + " 9. Borrar pasajero por id\n"
										+ "10. Consulta pasajero por id\n" + "11. Listado de pasajeros\n"
										+ "12. Añadir pasajero al coche\n" + "13. Listar pasajeros del coche\n"+ "14. Eliminar pasajero del coche\n" + "15. Terminar el programa\n" );
								String opas = nextLine();
								if(opas.equals("8")) {//Añadir pasajero
									
									Pasajero p= new Pasajero();
									System.out.println("-----------Escribe el nombre: ----------");
									p.setNombre(nextLine());
									System.out.println("-----------Escribe la edad: ----------");
									int se = Integer.parseInt(nextLine());
									p.setEdad(se);
									System.out.println("-----------Escribe el peso: ----------");
									Double sp = Double.parseDouble(nextLine());
									p.setPeso(sp);
									p.setIdCoche(scaux);
									
									paDao.addPassenger(p);
									
									
								}else if(opas.equals("9")) {
									
									System.out.println("-----------Escribe el ID del pasajero a borrar: ----------");
									int opas1 = Integer.parseInt(nextLine());											
									System.out.println(paDao.deletePassenger(opas1)+ "\n");
									
								}else if(opas.equals("10")) {//Buscar coche
									
									System.out.println("-----------Escribe el ID del pasajero a buscar: ----------");
									int opas2 = Integer.parseInt(nextLine());											
									System.out.println(paDao.findById(opas2)+ "\n");
									
								}else if(opas.equals("11")) {
									
									System.out.println("----------Lista de pasajeros completa: ----------");
									System.out.println(paDao.passengerList()+ "\n");
							       
							        
								}else if(opas.equals("12")) { //añadir pasajero al coche
									
									
									
								}else if(opas.equals("13")) { //listar pasajeros del coche
									
									
								}else if(opas.equals("14")) { //eliminar pasajero del coche
									
									
									
								}else if(opas.equals("15")) { //salir al menu principal pasajero del coche
									exitSub = false;
									System.out.println("Saliendo del menu pasajeros");
									
									
								}
							}
								
						}else {
							System.out.println("Inserte un coche que exista");
						}
						
					}else if(opcion.equals("7")) {//terminamos la ejecucion del programa 
						
						continua = false;
						System.out.println("Vuelva cuando quiera");
					}
					
				}
			}
			

}