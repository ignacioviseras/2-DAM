import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

/**
 * Esta aplicacion gestiona un concesionario(ArrayList) de coches(Cars.java)
 * dentro de un fichero .DAT leido y sobrescrito al cerrar el progrma(save())
 * 
 * Podemos decir que el programa utiliza un flujos de bytes para generar la BD
 * binaria
 * (información binaria: derivados de las clases abstractas InputStream y
 * OutputStream)
 * 
 * Pero podemos exportar la información a un .CSV utilizando fujos de datos en
 * formato unicode
 * (16/32/64 bits: derivados de las clases abstractas Reader y Writer)
 */
public class App {

    private static final String FILENAME = "Coches";
    private static final char CSV_SEPARATOR = ';'; // for csv files
    private static final int CONSOLE_TABULATION = 4;

    // java.io
    private static File file = new File(FILENAME);
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;

    // java.util
    private static ArrayList<Car> concessionaire = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * 
     * @param args
     * @throws IOException porque el control de esta excepcion no es un requisito
     *                     explicito.
     */
    public static void main(String[] args) throws IOException {
        // concessionaire.add(new Car(0, "0000-HSK", "PEUGEOT", "RCZ", "AMARILLO"));
        // concessionaire.add(new Car(1, "1111-UWU", "TOYOTA", "KAWAI", "ROSA"));

        if (file.exists())
            load();

        work();
        save();

    }

    private static void save() throws IOException {

        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);

        for (Car car : concessionaire) {
            oos.writeObject(car);
        }

        if (oos != null)
            oos.close();
        if (fos != null)
            fos.close();

    }

    /**
     * Método recursivo para leer objetos Coche
     * 
     * @throws IOException
     * @throws InvalidClassException    problemas de integridad @see
     *                                  Serializable.class
     * @throws StreamCorruptedException normalmente esto ocurre cuando abrimos un
     *                                  stream de entrada y salida al mismo tiempo
     * @throws ClassNotFoundException   cuando la clase de objeto que intenta leer
     *                                  no esta en el CLASSPATH
     */
    private static void loadCars()
            throws IOException, InvalidClassException, StreamCorruptedException, ClassNotFoundException {
        // eskivo esquivo una EOFException
        while (fis.available() > 0) {
            concessionaire.add((Car) ois.readObject());
        }
    }

    private static void load() throws IOException {

        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);

        try {
            loadCars();

        } catch (ClassNotFoundException e) { // ¿ la clase esta añadida en el classpath ?
            System.out.println("readCars() -> " + e.getMessage());
            System.out.println(
                    "\t\tThrown when an application tries to load in a class through its string name using:\n" +
                            "\t\t + The forName method in class Class.\n" +
                            "\t\t + The findSystemClass method in class ClassLoader .\n" +
                            "\t\t + The loadClass method in class ClassLoader.\n");

        } catch (StreamCorruptedException e) { // ¿ esta la array list vacia ? ¿el fis(file, true) esta intentado añadir
                                               // lineas que ya existen?
            System.out.println("read() -> " + e.getMessage());
            System.out.println(
                    "\t\tThrown when control information that was read from an object stream violates internal consistency checks.");
            /*
             * In the Java Forums thread cited by @trashgod, I should have left out the part
             * about 'anew for each object at both ends': that's just wasteful. Use a single
             * OOS and OIS for the life of the socket, and don't use any other streams on
             * the socket.
             * 
             * If you want to forget what you've written or readded, use
             * ObjectOutputStream.reset().
             */

        } catch (InvalidClassException e) { // ¿ el UID Version es diferente; has tocado Car.java ?
            System.out.println("readCars() -> " + e.getMessage());

        } finally {
            ois.close();
            fis.close();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void work() throws IOException {

        System.out.println(" press enter ");
        scanner.nextLine();

        clear();
        menu(7);

        if (run(scanner.nextLine()))
            work(); // recursive loop
        else
            scanner.close();
    }

    private static void menu(int option) {
        if (option >= 0)
            System.out.println("0 Terminar el programa");
        if (option >= 1)
            System.out.println("1 Añadir nuevo coche");
        if (option >= 2)
            System.out.println("2 Borrar coche por id");
        if (option >= 3)
            System.out.println("3 Consulta coche por id");
        if (option >= 4)
            System.out.println("4 Listar todos los coches");
        if (option >= 5)
            System.out.println("5 Exportar coches.csv por OpenCSV (ICSWriter)");
        if (option >= 6)
            System.out.println("6 Exportar coches.csv por ApacheCommons (CSVPrinter) ");
        if (option >= 7)
            System.out.println("7 Exportar coches.csv por JavaIO (PrintWritter)");
    }

    /*
     * Las opciones del menú 1 a 5 trabajarán sobre la colección de tipo ArrayList
     * para añadir, borrar, consultar o listar, y no sobre el fichero coches.dat.
     */
    private static boolean run(String line) throws IOException {
        clear();
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
            exportApacheCommonsCSV();
        if (line.equals("7"))
            exportPrintWriter();
        if (line.equals("0"))
            return false;
        return true;
    }

    private static void addCar() {

        menu(1);

        System.out.print("\t\t1.1 Seleccione la matricula: ");
        String register = scanner.nextLine();

        System.out.print("\t\t1.2 Seleccione la marca: ");
        String brand = scanner.nextLine();

        System.out.print("\t\t1.3 Seleccione el modelo: ");
        String model = scanner.nextLine();

        System.out.print("\t\t1.4 Seleccione el color: ");
        String color = scanner.nextLine();

        Integer id = Integer.valueOf(getFirstEmptyRowIndex(0));

        try {

            Car car = new Car(id, register, brand, model, color);
            concessionaire.add(car);
            System.out.println("Burrocracia admitida");


        } catch (NumberFormatException e) {
            System.out.println("Burrocracia no admitida.");
        }
    }

    private static int getFirstEmptyRowIndex(int carId) {
        boolean isAlive = false;
        for (int j = 0; j < concessionaire.size(); j++) {
                
            if ((int) concessionaire.get(j).getId() == carId) {

                isAlive = true;
            }
        }
        if (isAlive) return getFirstEmptyRowIndex(carId + 1);
        else return carId;
   
    }

    private static void deleteCar() {
        menu(2);

        System.out.print("\t\t2.1 Seleccione el id: ");
        String line = scanner.nextLine();

        try {
            int id = (int) Integer.parseInt(line);

            for (int i = 0; i < concessionaire.size(); i++) {

                if ((int) concessionaire.get(i).getId() == (int) id) {
                    concessionaire.remove(i);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Burrocracia no admitida; formato introducido no valido");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Burrocracia no admitida; indice no encontrado");
        }
    }

    private static void selectCar() {
        menu(3);

        System.out.print("\t\t3.1 Seleccione el id: ");
        String line = scanner.nextLine();

        try {

            System.out.println(
                    tab("ID") + tab("REGISTRE") +
                            tab("BRAND") + tab("MODEL") +
                            tab("COLOR"));

            Car car = concessionaire.get(Integer.parseInt(line));
            System.out.println(
                    tab(car.getId().toString()) + tab(car.getRegistre()) +
                            tab(car.getBrand()) + tab(car.getModel()) +
                            tab(car.getColor()));

        } catch (NumberFormatException e) {
            System.out.println("Burrocracia no admitida.");
        } catch (IndexOutOfBoundsException e) {
            selectCar();
        }
    }

    private static void listCars() {
        menu(4);
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        System.out.println(
                tab("ID") + tab("REGISTRE") +
                        tab("BRAND") + tab("MODEL") +
                        tab("COLOR"));

        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        for (Car car : concessionaire) {

            System.out.println(
                    tab(car.getId().toString()) + tab(car.getRegistre()) +
                            tab(car.getBrand()) + tab(car.getModel()) +
                            tab(car.getColor()));

        }
    }

    private static void exportOpenCSV() throws IOException {
        menu(5);

        try (ICSVWriter writer = new CSVWriterBuilder(
                new FileWriter(FILENAME + ".csv"))
                .withSeparator(CSV_SEPARATOR)
                .build()) {

            String[] strings = new String[5];

            for (Car car : concessionaire) {
                strings[0] = car.getId().toString();
                strings[1] = car.getRegistre();
                strings[2] = car.getBrand();
                strings[3] = car.getModel();
                strings[4] = car.getColor();
                writer.writeNext(strings);
            }

        }
    }

    // apache
    private static void exportApacheCommonsCSV() throws IOException {

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(FILENAME + ".csv"),
                CSVFormat.DEFAULT.withQuote('\"'))) {

            String id, reg, brand, model, color;
            for (Car car : concessionaire) {
                id = car.getId().toString();
                reg = car.getRegistre();
                brand = car.getBrand();
                model = car.getModel();
                color = car.getColor();

                printer.printRecord(id, reg, brand, model, color);
            }
        }
    }

    // java io
    private static void exportPrintWriter() throws IOException {

        try (PrintWriter printer = new PrintWriter(new File(FILENAME + ".csv"))) {

            String id, reg, brand, model, color;
            for (Car car : concessionaire) {
                id = car.getId().toString();
                reg = car.getRegistre();
                brand = car.getBrand();
                model = car.getModel();
                color = car.getColor();

                printer.println("\"" + id + "\";\"" + reg + "\";\"" + brand + "\";\"" + model + "\";\"" + color + "\"");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String tab(String string) {
        char[] chars = string.toCharArray();
        String returns = "";

        //// //// //// ///- && //// //// //// ---- = \t
        if (chars.length >= 12) {
            for (int i = 0; i <= 10; i++) {
                returns += String.valueOf(chars[i]);
            }

            if (chars.length > 10) {
                return returns += "...\t\t";
            } else {
                return returns += "\t";
            }

        }

        //// //// ///- ---- && //// //// ---- ---- = \t\t
        if (chars.length < 12 && chars.length >= 8) {
            for (int i = 0; i < chars.length; i++) {
                returns += String.valueOf(chars[i]);
            }

            return returns += "\t\t";
        }

        //// ///- ---- ---- && //// ---- ---- ---- = \t\t\t
        if (chars.length < 8 && chars.length >= 0) {
            for (int i = 0; i < chars.length; i++) {

                returns += String.valueOf(chars[i]);
            }
            return returns += "\t\t\t";
        }

        /// - ---- ---- ---- && ---- ---- ---- ---- = \t\t\t\t
        if (chars.length < 4 && chars.length >= 0) {
            for (int i = 0; i < chars.length; i++) {

                returns += String.valueOf(chars[i]);
            }
            return returns += "\t\t\t";
        }

        return returns;

    }
}