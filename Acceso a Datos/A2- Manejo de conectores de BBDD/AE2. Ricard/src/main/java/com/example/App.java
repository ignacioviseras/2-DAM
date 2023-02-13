package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import com.example.persistence.*;
import com.example.persistence.postgreesql.PgCarDAO;
import com.example.persistence.postgreesql.PgPassangerDAO;
import com.example.models.*;

/**
 * Requerimiento 1
 * 
 * Se desea hacer un CRUD completo de la entidad Coche. Esta vez no se trabajará
 * con ningún fichero y se deberá realizar la opción de modificar coche por ID.
 * Es muy importante usar el patrón DAO visto en clase. El menú mostrado será de
 * la siguiente forma:
 * 
 * Añadir nuevo coche (El ID lo incrementará automáticamente la base de datos)
 * Borrar coche por ID
 * Consulta coche por ID
 * Modificar coche por ID
 * Listado de coches
 * Terminar el programa
 * Valoración: 5 puntos sobre 10
 * 
 * Requerimiento 2
 * 
 * Se pide añadir la siguiente funcionalidad.
 * 
 * Los coches, tendrán asociados N pasajeros en él (habrá que crear la tabla
 * pasajeros y hacer la relación pertinente). Los pasajeros tendrán los
 * siguientes atributos, id, nombre, edad y peso. Se añadirá la opción “gestión
 * de pasajeros” al programa principal, dicha opción nos mostrará un submenú
 * como el que sigue
 * 
 * Añadir nuevo pasajero
 * Borrar pasajero por id
 * Consulta pasajero por id
 * Listar todos los pasajeros
 * Añadir pasajero a coche, el programa nos pedirá un id de un pasajero y el id
 * de un coche, y lo añadirá al coche a nivel de base de datos. Sería una buena
 * opción mostrar todos los coches disponibles.
 * Eliminar pasajero de un coche, el programa nos pedirá un id de un pasajero y
 * el id de un coche, y lo eliminará del coche a nivel de base de datos. Sería
 * una buena opción mostrar todos los coches y sus pasajeros asociados.
 * Listar todos los pasajeros de un coche, el programa pedirá el id de un coche,
 * y nos mostrará todos los pasajeros asociados a él.
 * Valoración: 5 puntos sobre 10
 *
 * 
 * Mis fuentes externas:
 * {@link} https://www.baeldung.com/java-dao-pattern
 * {@link} https://www.youtube.com/watch?v=ld_YaQXxX1M&ab_channel=MitoCode
 * {@link} https://www.youtube.com/watch?v=NjY-WA-jeJ8&t=88s&ab_channel=makigas%3Atutorialesdeprogramaci%C3%B3n
 * 
 */
public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static PgCarDAO pgCarDAO;
    private static PgPassangerDAO pgPassangerDAO;

    public static void main(String[] args) throws DAOException, SQLException {

        // auto close connection
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/edix", "postgres", "1234")) {

            if (conn != null) {
                System.out.println("Connected to the database!");

                // initialize the data access objects
                pgCarDAO = new PgCarDAO(conn);
                pgPassangerDAO = new PgPassangerDAO(conn);

                while (carsRun(scanner.nextLine())) {
                    try {
                        scanner.nextLine(); // pause to view the result
                        clear(); // clear the screen
                        carsMenu(7); // print first menu
                    } catch (NumberFormatException e) {
                        System.out.println("Formato numérico no valido.");
                    }
                }

            } else {
                System.out.println("Failed to make connection!");
            }
        } finally {
            scanner.close();
        }
    }

    private static boolean carsRun(String line) throws DAOException, NumberFormatException {
        clear();

        // Añadir nuevo coche (El ID lo incrementará automáticamente la base de datos)
        if (line.equals("1")) {
            carsMenu(1);
            String reg, brand, model, color;

            System.out.print("Matrícula: ");
            reg = scanner.nextLine();

            System.out.print("Marca: ");
            brand = scanner.nextLine();

            System.out.print("Modelo: ");
            model = scanner.nextLine();

            System.out.print("Color: ");
            color = scanner.nextLine();

            pgCarDAO.insert(new Car(null, reg, brand, model, color));
        }

        // Borrar coche por ID
        if (line.equals("2")) {
            carsMenu(2);

            System.out.print("Identificador del coche: ");
            Integer id = Integer.parseInt(scanner.nextLine());

            pgCarDAO.delete(new Car(id, "", "", "", ""));

        }

        // Consulta coche por ID
        if (line.equals("3")) {
            carsMenu(3);

            System.out.print("Identificador del coche: ");
            Integer id = Integer.parseInt(scanner.nextLine());

            System.out.println(pgCarDAO.get((int) id).get().toString());

        }

        // Modificar coche por ID
        if (line.equals("4")) {
            carsMenu(4);

            System.out.print("Identificador del coche: ");
            Integer id = Integer.parseInt(scanner.nextLine());

            Car car = pgCarDAO.get((int) id).get();
            System.out.println("Datos actuales: \t" + car.toString());

            String[] params = new String[5];
            System.out.println("Datos a modificar: ");

            params[0] = car.getId().toString();
            System.out.print("\tIdentificador: " + params[0]);

            System.out.print("\tMatrícula: ");
            params[1] = scanner.nextLine();

            System.out.print("\tMarca: ");
            params[2] = scanner.nextLine();

            System.out.print("\tModelo: ");
            params[3] = scanner.nextLine();

            System.out.print("\tColor: ");
            params[4] = scanner.nextLine();

            pgCarDAO.update(pgCarDAO.get((int) id).get(), params);

        }

        // Listado de coches
        if (line.equals("5")) {
            carsMenu(5);
            for (Car car : pgCarDAO.getAll()) {
                System.out.println(car.toString());
            }
        }

        // “gestión de pasajeros”
        if (line.equals("7")) {
            carsMenu(7);
            passMenu(7);
            return passRun(scanner.nextLine());
        }

        // Terminar el programa
        if (line.equals("6"))
            return false;
        return true;
    }

    public static void carsMenu(int at) {
        if (at >= 1)
            System.out.println("1 Añadir nuevo coche ");
        if (at >= 2)
            System.out.println("2 Borrar coche por ID ");
        if (at >= 3)
            System.out.println("3 Consulta coche por ID ");
        if (at >= 4)
            System.out.println("4 Modificar coche por ID ");
        if (at >= 5)
            System.out.println("5 Listado de coches ");
        if (at >= 6)
            System.out.println("6 Terminar el programa ");
        if (at >= 7)
            System.out.println("7 Gestión de pasajeros");
    }

    public static boolean passRun(String line) throws DAOException, NumberFormatException {
        clear();

        // Añadir nuevo pasajero
        if (line.equals("1")) {
            carsMenu(7);
            passMenu(1);

            System.out.print("   Nombre: ");
            String name = scanner.nextLine();

            System.out.print("   Edad: ");
            Integer age = Integer.parseInt(scanner.nextLine());

            System.out.print("   Peso: ");
            Integer weight = Integer.parseInt(scanner.nextLine());

            pgPassangerDAO.insert(new Passanger(null, null, name, age, weight));

        }

        // Borrar pasajero por id
        if (line.equals("2")) {
            carsMenu(7);
            passMenu(2);

            System.out.print("   Identificador: ");
            Integer id = Integer.parseInt(scanner.nextLine());

            pgPassangerDAO.delete(new Passanger(id, null, null, null, null));

        }

        // Consulta pasajero por id
        if (line.equals("3")) {
            carsMenu(7);
            passMenu(3);

            System.out.print(" Identificador: ");
            Optional<Passanger> p = pgPassangerDAO.get(Integer.parseInt(scanner.nextLine()));

            System.out.println(p.get().toString());

        }

        // Listar todos los pasajeros
        if (line.equals("4")) {
            carsMenu(7);
            passMenu(4);

            for (Passanger p : pgPassangerDAO.getAll()) {
                System.out.println(" " + p.toString());
            }

        }

        // Añadir pasajero a coche, el programa nos pedirá un id de un pasajero y el id
        // de un coche, y lo añadirá al coche a nivel de base de datos. Sería una buena
        // opción mostrar todos los coches disponibles.
        if (line.equals("5")) {
            carsMenu(7);
            passMenu(5);

            for (Passanger p : pgPassangerDAO.getAll()) {
                System.out.println("   " + p.toString());
            }
            System.out.print("   Identificador del pasajero: ");
            Integer id = Integer.parseInt(scanner.nextLine());
            Optional<Passanger> passanger = pgPassangerDAO.get(id);

            for (Car c : pgCarDAO.getAll()) {
                System.out.println("   " + c.toString());
            }
            System.out.print("   Identificador del coche: ");
            Integer carId = Integer.parseInt(scanner.nextLine());

            String[] params = new String[5];
            params[0] = passanger.get().getId().toString(); // id
            params[1] = carId.toString(); // car_id
            params[2] = passanger.get().getName(); // name
            params[3] = passanger.get().getAge().toString(); // age
            params[4] = passanger.get().getWeight().toString(); // weight

            pgPassangerDAO.update(passanger.get(), params);

        }

        // Eliminar pasajero de un coche, el programa nos pedirá un id de un pasajero y
        // el id de un coche, y lo eliminará del coche a nivel de base de datos. Sería
        // una buena opción mostrar todos los coches y sus pasajeros asociados.
        if (line.equals("6")) {
            carsMenu(7);
            passMenu(6);

            for (Passanger p : pgPassangerDAO.getAll()) {
                System.out.println("   " + p.toString());
            }
            System.out.print("   Identificador del pasajero: ");
            Integer id = Integer.parseInt(scanner.nextLine());
            Optional<Passanger> passanger = pgPassangerDAO.get(id);

            for (Car c : pgCarDAO.getAll()) {
                System.out.println("   " + c.toString());
            }
            System.out.print("   Identificador del coche: ");
            Integer carId = Integer.parseInt(scanner.nextLine());

            String[] params = new String[5];
            params[0] = passanger.get().getId().toString(); // id
            params[1] = "0"; // car_id
            params[2] = passanger.get().getName(); // name
            params[3] = passanger.get().getAge().toString(); // age
            params[4] = passanger.get().getWeight().toString(); // weight

            pgPassangerDAO.update(passanger.get(), params);
        }

        // Listar todos los pasajeros de un coche, el programa pedirá el id de un coche,
        // y nos mostrará todos los pasajeros asociados a él.
        if (line.equals("7")) {
            carsMenu(7);
            passMenu(7);

            for (Car c : pgCarDAO.getAll()) {
                System.out.println("   " + c.toString());
            }
            System.out.print("   Identificador del coche: ");
            Integer carId = Integer.parseInt(scanner.nextLine());

            clear();
            for (Passanger p : pgPassangerDAO.getAll()) {
                if (p.getCar_id() == carId) {
                    System.out.println(p.toString());
                }
            }
        }

        return true;

    }

    public static void passMenu(int at) {
        if (at >= 1)
            System.out.println(" 1 Añadir nuevo pasajero ");
        if (at >= 2)
            System.out.println(" 2 Borrar pasajero por id ");
        if (at >= 3)
            System.out.println(" 3 Consulta pasajero por id ");
        if (at >= 4)
            System.out.println(" 4 Listar todos los pasajeros");
        if (at >= 5)
            System.out.println(" 5 Añadir pasajero a coche ");
        if (at >= 6)
            System.out.println(" 6 Eliminar pasajero de un coche ");
        if (at >= 7)
            System.out.println(" 7 Listar todos los pasajeros de un coche");
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}