package com.proyect.rest;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.proyect.beans.Game;
import com.proyect.service.ServiceProxyGame;
import com.proyect.service.ServiceProxyMensaje;

@SpringBootApplication
public class Application {

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
		SpringApplication.run(Application.class, args);
	}

	
	//arrancamos la aplicacion
	public void run(String... args) throws Exception{
		System.out.println("===========Iniciando Cliente Rest============");
		System.out.println("1. Dar de alta un videojuego" + "\n" + "2. Dar de baja un videojuego por ID"
							+ "\n" + "3. Modificar un videojuego por ID" + "\n" + "4. Obtener un videojuego por ID" 
							+ "\n" + "5. Listar todos los videojuegos" + "\n" + "6. Salir");
		
		Scanner sc = new Scanner(System.in);
		int nOpcion = sc.nextInt();
		Game game = new Game();
		if(nOpcion == 1) {
			String mensaje = spm.obtenerCadena("newGame");
			System.out.println("***Introducir el nombre***");
			String name = sc.nextLine();
			game.setName(name);
			System.out.println("***Introducir la Compañia***");
			String company = sc.nextLine();
			game.setCompany(company);
			System.out.println("***Introducir la nota***");
			String score = sc.nextLine();
			game.setScore(Double.parseDouble(score));

			System.out.println("Persona Alta: "+ mensaje);
		}
		if(nOpcion == 2) {
			String mensaje = spm.obtenerCadena("deleteGame");
			System.out.println("***Introducir el id***");
			String id = sc.nextLine();
			game.setId(Integer.parseInt(id));

			System.out.println("Persona eliminada: "+ mensaje);
		}
		if(nOpcion == 3) {
			String mensaje = spm.obtenerCadena("modifyGame");
			System.out.println("***Introducir el id del juego a modificar***");
			String id = sc.nextLine();
			Game gamemodif = new Game();
			gamemodif = spg.findById(Integer.parseInt(id));
			
			System.out.println("***Introducir un nuevo nombre***");
			String name = sc.nextLine();
			if(name.equals(""))
				gamemodif.getName();
			gamemodif.setName(name);
			
			System.out.println("***Introducir la Compañia***");
			String company = sc.nextLine();
			if(company.equals(""))
				gamemodif.getCompany();
			gamemodif.setCompany(company);
			
			System.out.println("***Introducir la nota***");
			String score = sc.nextLine();
			if(score.equals(""))
				gamemodif.getScore();
			gamemodif.setScore(Double.parseDouble(score));
			System.out.println("Persona Alta: "+ mensaje);
		}
		if(nOpcion == 4) {
			String mensaje = spm.obtenerCadena("findById");
			System.out.println("Persona Alta: "+ mensaje);
		}
		if(nOpcion == 5) {
			String mensaje = spm.obtenerCadena("findAll");
			System.out.println("Persona Alta: "+ mensaje);
		}
		if(nOpcion == 6) {
			paraApp();
		}
	}
	
	
	//Paramos la ejecucion de la app
	public void paraApp() {
		System.out.println("===Se esta cerrando la App===");
		SpringApplication.exit(context, () -> 0);
	}
}
