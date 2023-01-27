package Controller;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import com.csvreader.CsvWriter;
import beans.Coche;
import dao.CocheDaoImpl;


public class MainCoche {
	public static final String NOMBRE_FICHERO = "agenda.dat";
	public static final Scanner scanner = new Scanner(System.in);
	public static CocheDaoImpl cdao = new CocheDaoImpl();
	
	public static String nextLine() {
		return scanner.nextLine();
		
	}	
	
	public static void main(String[] args) {
		System.out.println("===========Iniciando Aplicacion============");
		ftloadFile();//llamamos a esta funcion para que nos cargue los coches del fichero .dat
		Boolean continua = true;
		while (continua == true) {
			System.out.println("1. A�adir nuevo coche\n" + "2. Borrar coche por id\n"
					+ "3. Consulta coche por id\n" + "4. Listado de coches\n"
					+ "5. Exportar coches a archivo CSV\n" + "6. Terminar el programa\n" );
			String opcion = nextLine();
			if(opcion.equals("1")) {//A�adir coche
				ftnewCar();
			}else if(opcion.equals("2")) {//Borrar coche
				ftdeleteCar();
			}else if(opcion.equals("3")) {//Buscar coche
				ftfindById();
			}else if(opcion.equals("4")) {//Listar coche
				cdao.listCar();
			}else if(opcion.equals("5")) {//Exportar a .csv
				ftexportToCsv();
			}else if(opcion.equals("6")) {//terminamos la ejecucion del programa y exportamos datos a .dat
				ftexit();
				continua = false;
			}
			
		}
	}
	
	/**
	 * Metodo que utilizaremos para cargar 
	 * los coches que hay en el fichero .dat en caso de que exista
	 */
	public static void ftloadFile() {
		//Cargar datos en array
		File fichero = new File("agenda.dat");
		if(fichero.exists()){//si existe el fichero agenda.dat
			System.out.println("Cargando fichero...");
			try(FileInputStream file = new FileInputStream("agenda.dat");
					ObjectInputStream buffer = new ObjectInputStream(file);){
				
				boolean eof = false;
				Coche c;
				while(!eof) {
					try {
						c = (Coche) buffer.readObject();
						cdao.newCar(c);//vamos a�adiendo los coches a nuestro arrayList
					}catch (EOFException e) {//si salta la excepcion termino el fichero
						System.out.println("Se cargaron los datos con exito");
						eof = true;
					}catch(IOException e2){
						System.out.println("Error al leer los contactos de la agenda");
						System.out.println(e2.getMessage());
					}catch(ClassNotFoundException e3){
						System.out.println("La clase de contacto no se cargo en memoria");
						System.out.println(e3.getMessage());
					}
				}
			}catch (IOException e) {
				System.out.println("No se ha podido abrir la agenda de contactos");
				System.out.println(e.getMessage());
				return;
			}
		}
	}
	
	/**
	 * Metodo para a�adir los coches
	 */
	public static void ftnewCar() {
		Coche cAux = new Coche();
		
		System.out.println("======ID======");
		cAux.setId(Integer.parseInt(nextLine()));
		System.out.println("======Numero de matricula======");
		cAux.setMatricula(nextLine());
		System.out.println("======Marca======");
		cAux.setMarca(nextLine());
		System.out.println("======Modelo======");
		cAux.setModelo(nextLine());
		System.out.println("======Color======");
		cAux.setColor(nextLine());
		
		System.out.println();
		if (cdao.newCar(cAux) == 1) {//en caso de que no se cree
			System.out.println("No se pudo crear ya que hay un coche "
								+ "con el mismo id/matricula\n");
		}else
			System.out.println("Creado con exito " + cAux);
	}
	
	/**
	 * Metodo que nos permitira borrar
	 * un coche de la lista el cual tenga 
	 * el mismo id que le pasamos
	 */
	public static void ftdeleteCar() {
		System.out.println("======Inserte el ID======");
		if(cdao.deleteCar(Integer.parseInt(nextLine())) == true) {
			System.out.println("Se borro exitosamente\n");
		}else
			System.out.println("No se pudo borrar el coche\n");
	}
	
	/**
	 * Metodo que nos permitira buscar 
	 * un coche en la lista mediante un id
	 */
	public static void ftfindById() {
		System.out.println("======Inserta el ID======");
		System.out.println(cdao.findById(Integer.parseInt(nextLine())));
	}
	
	/**
	 * Metodo que utilizaremos para crear un archivo .dat
	 * el cual contendra los elementos del arrayList
	 */
	public static void ftexit() {
		System.out.println("Vuelva pronto");
		if(!(cdao.listOb() == null || cdao.listOb().size() == 0)) {//en caso de que el  arrayList tenga contenido
			try(FileOutputStream file = new FileOutputStream(NOMBRE_FICHERO);//asi estariamos sobreescribiendo el contenido del archivo (por si existe)
					ObjectOutputStream buffer = new ObjectOutputStream(file)){
					for (Coche c : cdao.listOb()) {
						buffer.writeObject(c);
					}
			}catch (IOException e){
				System.out.println("No se pudo abrir el fichero");
				System.out.println(e.getMessage());
				return;
			}
		}
	}
    
	
	}


	

