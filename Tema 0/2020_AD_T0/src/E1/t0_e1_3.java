/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E1;

/**
 *
 * @author joange
 */
public class t0_e1_3 {

    private String texto;
    
    public void arrancar() {
        System.out.println("El text es "+this.texto);
    }
    
    public t0_e1_3(String t){
        this.texto=t;
    }

    public static void main(String[] args) {
        t0_e1_3 t=new t0_e1_3(args[0]);
        t.arrancar();
    }
    
}


