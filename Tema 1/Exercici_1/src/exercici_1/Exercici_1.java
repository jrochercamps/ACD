/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercici_1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author jpc
 */
public class Exercici_1 {

    static int MAX_FILES_BY_COLUMN = 5;
        
    static Scanner sc = new Scanner(System.in);    
   
    
    private static void modoTablas(File[] listadoArchivos) {  
        for (File archivo : listadoArchivos) {         
          
            System.out.printf("%s%s%s%s%s\t%-10s\t%-10d\t%s\n",
                    archivo.isDirectory()?"D":"-",
                    archivo.isFile()?"F":"-",
                    archivo.canRead()?"R":"-",
                    archivo.canWrite()?"W":"-",                  
                    archivo.isHidden()?"H":"-",
                    archivo.getName(),
                    (int)(archivo.length()/1024),
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(archivo.lastModified())
            );
            
        }
       
    }

    private static void modoColumnas(String[] filenames) {
        
        int filas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;
        String[][] salida = new String[filas][MAX_FILES_BY_COLUMN];

        for (int i = 0; i < filenames.length; i++) {
            salida[i / MAX_FILES_BY_COLUMN][i % MAX_FILES_BY_COLUMN] = filenames[i];
        }

        //bucle para mostrar salida
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < MAX_FILES_BY_COLUMN; j++) {
                System.out.printf("%25.25s",ConsoleColors.CYAN + salida[i][j] + " - ");
            }
            System.out.println(ConsoleColors.CYAN + " /");
        }
        
    }

    private static void modoLista(String[] listaDirectorio) {
        for (String directorio : listaDirectorio) {
            System.out.println(directorio);
        }
    }
    
    public void ListaColumnas(String[] filenames) {
        int filas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;
        String[][] salida = new String[filas][MAX_FILES_BY_COLUMN];

        for (int i = 0; i < filenames.length; i++) {
            salida[i / MAX_FILES_BY_COLUMN][i % MAX_FILES_BY_COLUMN] = filenames[i];
        }

        //bucle para mostrar salida
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < MAX_FILES_BY_COLUMN; j++) {
                System.out.printf("%25.25s",ConsoleColors.CYAN + salida[i][j] + " - ");
            }
            System.out.println(ConsoleColors.CYAN + " /");
        }
    }
    
    
    public static void menuDirectorio(File files){
        
        System.out.println("1 - Directorio en modo lista");
        System.out.println("2 - Directorio en modo columnas");
        System.out.println("3 - Directorio en modo tablas");
        System.out.println("4 - Salir");
        
        int menu=sc.nextInt();
        
        while(menu != 0) {

            //switch para las opciones
            switch (menu) {
                case 1:
                    modoLista(files.list());
                    break;
                case 2:
                    modoColumnas(files.list());
                    break;
                case 3:
                    modoTablas(files.listFiles());
                    break;
                case 4:
                    System.out.println("Saliendo del menú");
            }

            //volvemos a mostar el menu
            System.out.println("lista:1 columnas:2 tabla:3 salir:0");
            System.out.println("que quieres hacer?");
            menu=sc.nextInt();
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
       
        String rutadirectorio = ""; 
        
        File rutaDirectorioFile = null;  
 
        while( rutaDirectorioFile == null||rutaDirectorioFile.isDirectory()==false) {
            System.out.println("Escribe la ruta de un directorio");
            rutadirectorio=sc.nextLine();
            rutaDirectorioFile = new File(rutadirectorio);
        }

        //Obtenemos los ficheros del directorio
        menuDirectorio(rutaDirectorioFile);
		
	}
    
}
