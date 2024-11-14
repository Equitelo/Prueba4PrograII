package mypart;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClasePrograII {
    
    static MiArchivo ma = new MiArchivo();;
    static Scanner lea = new Scanner(System.in).useDelimiter("\n");
    
    public static void main(String[] args) {
        
        int options = 0;

        do {
            System.out.println("""
                           1 - Set Archivo/Folder
                           2 - Ver Informacion
                           3 - Crear Archivo
                           4 - Crear Folder
                           5 - Borrar
                           6 - comando DIR
                           7 - TREE
                           8 - Escribir en dicho archivo.
                           9 - Leer dicho archivo.
                           10 - Salir
                           """);
            System.out.print("Escoge una de las opciones: ");
            try {
                options = lea.nextInt();
                switch (options) {
                    case 1:
                        set();
                        break;
                    case 2:
                        ma.info();
                        break;
                    case 3:
                        if (ma.crearFile()) {
                            System.out.println("Se creo el archivo!");
                        } else {
                            System.out.println("No se creo el archivo!");
                        }
                        break;
                    case 4:
                        if (ma.crearFolder()) {
                            System.out.println("Se creo el folder!");
                        } else {
                            System.out.println("No se creo el folder!");
                        }
                        break;
                    case 5:
                        ma.borrar();
                        break;
                    case 6:
                        ma.dir();
                        break;
                    case 7:
                        ma.tree();
                        break;
                    case 8:
                        writeInFile();
                        break;
                    case 9:
                        readFile();
                        break;
                    default:
                        System.out.println("Sistema fuera");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresar una opcion valida!");
                lea.next();
            } catch (NullPointerException e) {
                System.out.println("Por favor, Sleccionar primero la opcion 1");
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
        } while (options != 10);

    }
    
    private static void set() {
        System.out.println("Direccion: ");
        ma.setFile(lea.next());
    }
    
    private static void writeInFile() throws IOException {
        System.out.println("Escriba el contenido que desea agregar o reemplazar:");
        String content = lea.nextLine();
        System.out.println("Desea reemplazar el contenido existente o agregar al final? (R/A):");
        String option = lea.next();
        lea.nextLine();
        if (option.equalsIgnoreCase("R")) {
            ma.rewriteInfo(content);
            System.out.println("Contenido reemplazado exitosamente.");
        } else if (option.equalsIgnoreCase("A")) {
            ma.addInfo(content);
            System.out.println("Contenido agregado exitosamente.");
        } else {
            System.out.println("no valido");
        }
    }

    private static void readFile() throws IOException {
        String content = ma.readFile();
        System.out.println("Contenido del archivo:");
        System.out.println(content);
    }
    
}
