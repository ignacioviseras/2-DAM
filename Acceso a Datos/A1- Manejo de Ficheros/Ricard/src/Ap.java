import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.DocFlavor.BYTE_ARRAY;

import com.opencsv.CSVWriter;

public class Ap{

    private static File file;
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;


    private static ArrayList<Car> concessionaire;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        try {
            init();
            load();
            start();
            save(true);

        } catch (IOException e) { // from load() or loadCars() or save()
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void init() {
        file = new File("coches.dat");
        concessionaire = new ArrayList<>();

        //concessionaire.add(new Car(0, "REGISTER", "BRAND", "MODEL", "COLOR"));
        concessionaire.add(new Car(1, "B12345", "TOYOTA", "FAMILIAR", "VERDE"));
        concessionaire.add(new Car(2, "C12345", "TOYOTA", "CHOCOLAT", "AMARILLO"));

    }

    public static void load() throws IOException {

        try {
            // we catch FileNotFoundException in this try
            fis = new FileInputStream(file);

            // skip a stating EOFException checking the file size before the "ois" creation 
            if (fis.available() != 0) {

                // The ObjectInputStream constructor blocks until it 
                // completes reading the serialization stream header.
                ois = new ObjectInputStream(fis);
                
                // recursive object load method
                loadCars(ois);

                // ObjectInputStream.close()
                ois.close();
            }

            // FileInputStream.close()
            fis.close();
    

        } catch (FileNotFoundException e) {

            // if file dosn't exist, create it and reload this method
            if (file.createNewFile()) {
                load();
            }
        }
    }

    static void loadCars(ObjectInputStream ois) throws IOException {
        try {
            
            concessionaire.add((Car) ois.readObject());
            if (ois.available() > 0) loadCars(ois); // recursive

        } catch (ClassNotFoundException e) {
            System.out.println("[loadCars()] class not found");

        } catch (EOFException e) {
            System.out.println("[loadCars()] no more bytes into the file to read");
        }
    }

    private static void save(boolean append) throws IOException {
        
        // initalize an output data stream
        fos = new FileOutputStream(file, append);
        oos = new ObjectOutputStream(fos);

        // save the cars into the file
        for (Car car : concessionaire) {
            oos.writeObject(car);
        }

        // close the output data stream
        oos.close();
        fos.close();
    }

    static void start() {

        boolean run = true;
        while (run) {
            menu();
            run = play(scanner.nextLine());
        }

    }

    static void menu() {
        System.out.println(
                "\n 1 Añadir nuevo coche\n 2 Borrar coche por id \n 3 Consulta coche por id\n 4 Listado de coches\n 5 Exportar coches a archivo CSV \n 6 Terminar el programa");
    }

    static boolean play(String line) {
        if (line.equals("1"))
            addCar();
        if (line.equals("2"))
            deleteCar();
        if (line.equals("3"))
            selectCar();
        if (line.equals("4"))
            listCars();
        if (line.equals("5"))
            exportOpenCSV();
        if (line.equals("6"))
            return false; // stop runtime while
        return true;
    }

    /**
     * Unicode data exporter/writer
     */
    static void exportOpenCSV() {

        try {
            FileWriter endpoint = new FileWriter(file);
            CSVWriter writer = new CSVWriter(endpoint);

            // adding headers row
            String[] headers = { "ID", "REGISTRATION", "BRAND", "MODEL", "COLOR" };
            writer.writeNext(headers);

            // adding data rows
            for (Car car : concessionaire) {
                // String[] string = Arrays.copyOf()
                System.out.println(car.getId() + "\t" + car.getRegistre() + "\t" + car.getBrand() + "\t"
                        + car.getModel() + "\t" + car.getColor());
            }

            writer.close();
            endpoint.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void exportApacheCommonsCSV() {

    }

    static void listCars() {
        for (Car car : concessionaire) {
            System.out.println(car.getId() + "\t" + car.getRegistre() + "\t" + car.getBrand() + "\t" + car.getModel()
                    + "\t" + car.getColor());
        }
    }

    static void selectCar() {

        System.out.print("\n\n 3 Consulta coche por id \n 3.1 Seleccione el id: ");
        String line = scanner.nextLine();

        try {
            System.out.println(concessionaire.get(Integer.parseInt(line)).toString());

        } catch (NumberFormatException e) {
            System.out.println("Formato introducido no valido.");
        }
    }

    static void deleteCar() {

        System.out.print("\n\n 2 Borrar coche por id \n 2.1 Seleccione el id: ");
        String line = scanner.nextLine();

        try {
            System.out.println(concessionaire.set(Integer.parseInt(line), null));

        } catch (NumberFormatException e) {
            System.out.println("Formato introducido no valido.");
        }
    }

    static void addCar() {

        System.out.print("\n\n 1 Añadir nuevo coche \n 1.1 Seleccione el id: ");
        String id = scanner.nextLine();
        System.out.print(" 1.2 Seleccione la matricula: ");
        String register = scanner.nextLine();
        System.out.print(" 1.3 Seleccione la marca: ");
        String brand = scanner.nextLine();
        System.out.print(" 1.4 Seleccione el modelo: ");
        String model = scanner.nextLine();
        System.out.print(" 1.5 Seleccione el color: ");
        String color = scanner.nextLine();

        try {
            Car car = new Car(Integer.parseInt(id), register, brand, model, color);
            concessionaire.add(car);

        } catch (NumberFormatException e) {
            System.out.println("Formato introducido no valido.");
        }
    }
}
