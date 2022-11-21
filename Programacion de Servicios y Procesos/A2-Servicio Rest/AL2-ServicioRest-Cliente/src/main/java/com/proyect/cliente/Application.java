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

	public static final Scanner scanner = new Scanner(System.in);
	
	public String nextLine() {
		return scanner.nextLine();
	}
	
	//arrancamos la aplicacion
	@Override
	public void run(String... args) throws Exception {
		System.out.println(spm.obtenerCadena("inicio"));
		System.out.println("===========Iniciando Cliente Rest============");
		Boolean isRun = true;
		while (isRun == true) {
			System.out.println("1. Dar de alta un videojuego" + "\n" + "2. Dar de baja un videojuego por ID" + "\n"
					+ "3. Modificar un videojuego por ID" + "\n" + "4. Obtener un videojuego por ID" + "\n"
					+ "5. Listar todos los videojuegos" + "\n" + "6. Salir");

			String option = nextLine();
	
			// -----Alta-----
			if (option.equals("1")) {
				Game game = new Game();
				System.out.println("***Introducir el id***");
				String id = nextLine();
				game.setId(Integer.parseInt(id));
				System.out.println("***Introducir el nombre***");
				String name = nextLine();
				game.setName(name);
				System.out.println("***Introducir la Compañia***");
				String company = nextLine();
				game.setCompany(company);
				System.out.println("***Introducir la nota***");
				String score = nextLine();
				game.setScore(Double.parseDouble(score));
				Game gNuevo = spg.newGame(game);
				
				System.out.println("Juego dado de Alta: " + gNuevo);
				System.out.println("");

			}
			// -----Borrar-----
			if (option.equals("2")) {
				System.out.println("***Introducir el id***");
				String id = nextLine();
				
				Boolean gBorrar = spg.deleteGame(Integer.parseInt(id));
				System.out.println("Juego eliminado: " + gBorrar + "\n");
			}
			// -----Modificar-----
			if (option.equals("3")) {
				System.out.println("***Introducir el id del juego a modificar***");
				String id = nextLine();
				
				Game gamemodif = new Game();
				gamemodif = spg.findById(Integer.parseInt(id));
				gamemodif.setId(Integer.parseInt(id));
				System.out.println("***Introducir un nuevo nombre***");
				String name = nextLine();
				if (name.equals(""))
					gamemodif.getName();
				gamemodif.setName(name);

				System.out.println("***Introducir la Compañia***");
				String company = nextLine();
				
				if (company.equals(""))
					gamemodif.getCompany();
				gamemodif.setCompany(company);

				System.out.println("***Introducir la nota***");
				String score = nextLine();
				
				if (score.equals(""))
					gamemodif.getScore();
				gamemodif.setScore(Double.parseDouble(score));

				Boolean gmodificar = spg.modifyGame(gamemodif);
				System.out.println("Juego Modificado: " + gmodificar + "\n");
			}
			// -----Listar uno-----
			if (option.equals("4")) {
				System.out.println("***Introducir el id***");
				String id = nextLine();
				
				Game gBuscarUno = spg.findById(Integer.parseInt(id));
				System.out.println("Juego Solicitado: " + gBuscarUno + "\n");
			}
			// -----Listar todos-----
			if (option.equals("5")) {
				System.out.println("******Los juegos que tenemos son******");
				List<Game> listGame = spg.findAll();
				
				listGame.forEach((pos) -> System.out.println(pos));
				System.out.println("");
			}
			// -----Salir-----
			if (option.equals("6")) {
				isRun = false;
				closeApp();
			}
		}
	}
	
	//Paramos la ejecucion de la app
	public void closeApp() {
		System.out.println("=====Se esta cerrando la App=====");
		SpringApplication.exit(context, () -> 0);
	}
}
