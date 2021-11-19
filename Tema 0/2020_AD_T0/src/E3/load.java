/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E3;

import Utils.Leer;

/**
 *
 * @author joange&
 */
public class load {
    public static void main(String[] args) throws InterruptedException {
        Tablero joc=new Tablero();
        
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
            
            switch(joc.bomba(fila, columna)){
                case -1:System.out.println("Celda fuera de rango.");break;
                case 0:System.out.println("Aigua");break;
                case 2:System.out.println("Tocado");break;
                case 3:System.out.println("Afonat");joc.imprimir();break;       
            }
            
            if (joc.fi()){
                System.out.println("Joc acabat");
                break;
            }
        }
        
    }
}
