package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import beans.Coche;
import daos.CocheDaoImpl;

public class CocheMain implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String FICHERO = "coches.dat";
	public static final Scanner scanner = new Scanner(System.in);
	
	public static String nextLine() {
		return scanner.nextLine();
	}
	
	
	public static void main(String[] args) throws IOException {
		
		CocheDaoImpl cdao = new CocheDaoImpl();
		
		File fl = new File("coches.dat");
		
		if (!fl.exists()){ //NO existe
			
			fl.createNewFile();
		    
		}else {// si existe 
			
			try (FileReader fr = new FileReader(CocheMain.FICHERO); //ojo al fallo.
					 BufferedReader br = new BufferedReader(fr);) {
					String archivo = br.readLine();
					while(archivo != null){
						System.out.println("Frase del fichero: " + archivo);
						archivo = br.readLine();
					}
					System.out.println("Fichero leido correctamente");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Boolean salir = true;
			//String opcion = "";
			while(salir == true) {
				System.out.println("Escribe una opción: ");
				System.out.println("1. Añadir nuevo coche ");
				System.out.println("2. Borrar coche por id ");
				System.out.println("3. Consultar coche por id ");
				System.out.println("4. Listado de coches");
				System.out.println("5. Exportar fichero en formato CSV");
				System.out.println("6. Salir \n");
				
				
				//Scanner sc = new Scanner (System.in);
				String opcion = nextLine();
				String aux;
				
				if (opcion.equals("1")) {
					Coche cocheAux = new Coche();
					Scanner sco = new Scanner (System.in);
					System.out.println("Escribe la matrícula");
					aux = sco.nextLine();
					cocheAux.setMatricula(aux);
					
					System.out.println("Escribe la marca");
					aux = sco.nextLine();
					cocheAux.setMarca(aux);
					
					System.out.println("Escribe el color");
					aux = sco.nextLine();
					cocheAux.setColor(aux);
					
					System.out.println("Escribe la modelo");
					aux = sco.nextLine();
					cocheAux.setModelo(aux);
					
					
					if(cdao.newCar(cocheAux) == 1){
						System.out.println("Este coche ya existe" + "\n");
					}else {
						System.out.println("Coche guardado" + "\n");
					}
					opcion = "";
					sco.close();
					
				}
				if (opcion.equals("2")) {
					
				}
				if (opcion.equals("3")) {
					
				}
				if (opcion.equals("4")) {
					
				}
				if (opcion.equals("5")) {
					
				}
				if (opcion.equals("6")) {
					System.out.println("Vuelva pronto");
					//sc.close();
					salir = false;
				}
			}
			
		}

	
	

}
