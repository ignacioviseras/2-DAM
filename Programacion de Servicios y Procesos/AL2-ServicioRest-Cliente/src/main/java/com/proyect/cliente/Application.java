package com.proyect.cliente;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.proyect.cliente.beans.Game;
import com.proyect.cliente.service.ServiceProxyGame;
import com.proyect.cliente.service.ServiceProxyMensaje;

@SpringBootApplication
public class Application implements CommandLineRunner{

	//utilizaremos esto para poder acceder a los metodos
	@Autowired
	private ServiceProxyGame spg;
	
	//enviaremos los mensajes 
	@Autowired
	private ServiceProxyMensaje spm;
	
	//esto se usara para la aplicacion
	@Autowired
	private ApplicationContext context;
	
	//objeto que se utiliza por el servicio rest para las peticiones
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	//main q lanzara la app que se encuentra en run
	public static void main(String[] args) {
		System.out.println("Accede??");		
		SpringApplication.run(Application.class, args);
	}

	
	//arrancamos la aplicacion
	@Override
	public void run(String... args) throws Exception {
		System.out.println(spm.obtenerCadena("inicio"));
		System.out.println("===========Iniciando Cliente Rest============");
		
		Scanner sc = new Scanner(System.in);
		//Boolean exit = false;
		//while(exit != true) {
			System.out.println("1. Dar de alta un videojuego" + "\n" + "2. Dar de baja un videojuego por ID"
					+ "\n" + "3. Modificar un videojuego por ID" + "\n" + "4. Obtener un videojuego por ID" 
					+ "\n" + "5. Listar todos los videojuegos" + "\n" + "6. Salir");
			int nOpcion = sc.nextInt();
			//-----Alta-----
			if(nOpcion == 1) {
				Game game = new Game();
				Scanner scAlta = new Scanner(System.in);
	
				//String mensaje = spm.obtenerCadena("newGame");
				System.out.println("***Introducir el id***");
				String id = scAlta.nextLine();
				game.setId(Integer.parseInt(id));
				System.out.println("***Introducir el nombre***");
				String name = scAlta.nextLine();
				game.setName(name);
				System.out.println("***Introducir la Compañia***");
				String company = scAlta.nextLine();
				game.setCompany(company);
				System.out.println("***Introducir la nota***");
				String score = scAlta.nextLine();
				game.setScore(Double.parseDouble(score));
				Game gNuevo = spg.newGame(game);
				System.out.println("Persona Alta: "+ gNuevo);
				scAlta.close();
			}
			//-----Borrar-----
			if(nOpcion == 2) {
				Scanner scBorar = new Scanner(System.in);
				//String mensaje = spm.obtenerCadena("deleteGame");
				System.out.println("***Introducir el id***");
				String id = scBorar.nextLine();
				Boolean gBorrar = spg.deleteGame(Integer.parseInt(id));
				System.out.println("Persona eliminada: "+ gBorrar);
				scBorar.close();
			}
			//-----Modificar-----
			if(nOpcion == 3) {
				Scanner scModif = new Scanner(System.in);
	
				//String mensaje = spm.obtenerCadena("modifyGame");
				System.out.println("***Introducir el id del juego a modificar***");
				String id = scModif.nextLine();
				Game gamemodif = new Game();
				gamemodif = spg.findById(Integer.parseInt(id));
				
				System.out.println("***Introducir un nuevo nombre***");
				String name = scModif.nextLine();
				if(name.equals(""))
					gamemodif.getName();
				gamemodif.setName(name);
				
				System.out.println("***Introducir la Compañia***");
				String company = scModif.nextLine();
				if(company.equals(""))
					gamemodif.getCompany();
				gamemodif.setCompany(company);
				
				System.out.println("***Introducir la nota***");
				String score = scModif.nextLine();
				if(score.equals(""))
					gamemodif.getScore();
				gamemodif.setScore(Double.parseDouble(score));
				
				Boolean gmodificar = spg.modifyGame(gamemodif);
				System.out.println("Persona Modificada: "+ gmodificar);
				scModif.close();
			}
			//-----Buscar uno-----
			if(nOpcion == 4) {
				Scanner scBusc1 = new Scanner(System.in);
	
				System.out.println("***Introducir el id***");
				String id = scBusc1.nextLine();
				Game gBuscarUno = spg.findById(Integer.parseInt(id));
				//String mensaje = spm.obtenerCadena("findById");
				System.out.println("Persona Solicitada: "+ gBuscarUno);
				scBusc1.close();
			}
			//-----Listar todos-----
			if(nOpcion == 5) {
				//System.out.println(spm.obtenerCadena("findAll"));
				System.out.println("******Los juegos que tenemos son******");
				List<Game> listGame = spg.findAll();
				listGame.forEach((pos) -> System.out.println(pos));
			}
			//-----Salir-----
			if(nOpcion == 6) {
				//exit = true;
				paraApp();
			}
		//}
		sc.close();
	}
	
	
	//Paramos la ejecucion de la app
	public void paraApp() {
		System.out.println("=====Se esta cerrando la App=====");
		SpringApplication.exit(context, () -> 0);
	}
}
