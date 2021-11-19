/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercici_2;

import Utils.Leer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joange&
 */
public class load {
    
    static int board_tam;
    static int num_boats;
    static int max_jugadas;
    static String boat_out_ficher_name="boat_out.txt";
    static String movimiento_out_boat_ficher_name="moviments_out.txt";
    static String boat_in_ficher_name="boat_in.txt";
    static String movimiento_in_boat_ficher_name="moviments_in.txt";
    
    public static Persistencia per = null;
    
    public static void main(String[] args) throws InterruptedException {
        
        //Se puede implementar un flag, para usar o no persistencia
        per = new Persistencia();
        
        per.cargarPropiedadesTablero(); 
        
        //Cargar archivos in de barcos y movimientos
        String[] barcosTXT = per.cargarBarcosFichero(boat_in_ficher_name);
        
        Tablero joc=new Tablero(barcosTXT);
        
        int nMovimiento = cargarMovimientosFichero(movimiento_in_boat_ficher_name, joc);
        
        //guardarBarcos(joc);
        
        joc.imprimir();
        
        String jugada;
        
        while (true){
            jugada=Leer.leerTexto("Dime la jugada en dos letras A3, B5... de A0 a J9: ").toUpperCase();
            if (jugada.equalsIgnoreCase("00")){
                System.out.println("Jugador rendit");
                break;
            }
            
            int fila=jugada.charAt(0)-'A';
            int columna=jugada.charAt(1)-'0';
            
            jugadaTablero(joc,nMovimiento,fila,columna);   
            
            if (joc.fi()){
                System.out.println("Joc acabat");
                break;
            }
            
            nMovimiento++;
        }
        
    }

    private static void jugadaTablero(Tablero joc, int nMovimiento, int fila, int columna) {
        int resultado = joc.bomba(fila, columna);
            
            switch(resultado){
                case -1:System.out.println("Celda fuera de rango.");break;
                case 0:System.out.println("Aigua");break;
                case 2:System.out.println("Tocado");break;
                case 3:System.out.println("Afonat");joc.imprimir();break;       
            }    
            
            per.guardarJugadaFichero(nMovimiento,fila,columna,resultado);
    }

    private static int cargarMovimientosFichero(String movimiento_in_boat_ficher_name, Tablero joc) {
    try {

        //Como cargamos el fichero de salida, ya dejamos un fichero de entrada preparado
        FileWriter fw= new FileWriter(movimiento_out_boat_ficher_name);
        fw.close();

        BufferedReader fich = new BufferedReader(new FileReader(movimiento_in_boat_ficher_name));

        int lineas = 0;
        String[] movimientosTXT = new String[num_boats];
        String linea;
        int nMovimiento, fila, columna;
        while((linea = fich.readLine())!=null){
            String[] movimientos = linea.split(";");
            nMovimiento = Integer.parseInt(movimientos[0]);
            fila = Integer.parseInt(movimientos[1]);
            columna = Integer.parseInt(movimientos[2]);
           jugadaTablero(joc,nMovimiento, fila, columna);
           lineas++;
        }
        return lineas;

    } catch (FileNotFoundException ex) {
        Logger.getLogger(load.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(load.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1;

    }
}
