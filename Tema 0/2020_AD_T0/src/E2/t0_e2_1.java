/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E2;

import Utils.Leer;
import java.util.Random;



/**
 *
 * @author joange
 */
public class t0_e2_1 {

    /**
     * @param args the command line arguments
     */
    
    public static final int TAM=10;
    public static void main(String[] args) {
        VectorNumeros vn=new VectorNumeros(TAM);
        Random r= new Random(System.currentTimeMillis());
        for (int i = 0; i < TAM; i++) {
            
            while (!vn.add(r.nextInt(1001)))
                ;
            
        }
        
        System.out.println(vn);
        int num=Leer.leerEntero("Dis-me un numero per buscar-lo: ");
        
        int pos=vn.masCercano(num);
        
        System.out.println("La posició del més proper es la " + pos);
    }
    

    
}
