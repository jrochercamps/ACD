package Exercici_2;

import static Exercici_2.load.board_tam;
import static Exercici_2.load.boat_out_ficher_name;
import static Exercici_2.load.max_jugadas;
import static Exercici_2.load.movimiento_out_boat_ficher_name;
import static Exercici_2.load.num_boats;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author JPC
 */
public class Persistencia {
    
    //Aquí cargamos la configuración a partir de un fichero dado
    public static void cargarPropiedadesTablero() {
        Properties configuracion = new Properties();
        try {
          configuracion.load(new FileInputStream("warship.propierties"));
          board_tam = Integer.parseInt(configuracion.getProperty("board_tam"));
          num_boats = Integer.parseInt(configuracion.getProperty("num_boats"));
          max_jugadas = Integer.parseInt(configuracion.getProperty("max_jugadas"));
        
        } catch (FileNotFoundException fnfe ) { 

        } catch (IOException ioe) { 
        }
    }
    
    public static void guardarBarcosFichero(Tablero joc) {
        FileWriter fw;
        try {
            fw= new FileWriter(boat_out_ficher_name);
                        
            var listadoBarcos = joc.getBarcosTXT();
            for (String barco : listadoBarcos) {
                fw.write(barco + "\n");
            }            
            
            fw.flush();
        } catch (IOException ex) {
            Logger.getLogger(load.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void guardarJugadaFichero(int nMovimiento, int fila, int columna, int resultado) {
       
        FileWriter fw;
        try {
            fw= new FileWriter(movimiento_out_boat_ficher_name,true);             
            fw.write(nMovimiento+";"+fila+";"+columna+";"+resultado + "\n");
            fw.flush();
        } catch (IOException ex) {
            Logger.getLogger(load.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String[]  cargarBarcosFichero(String boat_in_ficher_name) {
        try {
            BufferedReader fich = new BufferedReader(new FileReader(boat_in_ficher_name));
            
            int lineas = 0;
            String[] barcosTXT = new String[num_boats];
            String linea;
            while((linea = fich.readLine())!=null){
               barcosTXT[lineas++]=linea;
            }
            
            return barcosTXT;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(load.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(load.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
