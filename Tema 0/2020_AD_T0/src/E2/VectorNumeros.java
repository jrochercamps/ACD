/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E2;

import java.util.Arrays;
import javax.management.MBeanAttributeInfo;

/**
 *
 * @author joange
 */
public class VectorNumeros {

    private int[] elsNumeros;
    int pos;

    public VectorNumeros(int n) {
        elsNumeros = new int[n];
        pos = 0;
    }

    public int esta(int num) {
        for (int i = 0; i < this.pos; i++) {
            if (elsNumeros[i] == num) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(int n) {
        int d = esta(n);
        if (d == -1) { // no esta
            elsNumeros[pos++] = n;
            return true;
        } else {
            return false;
        }
    }

    public int masCercano(int num) {
        int distMin = 9999;
        int posCercano = -1;
        for (int i = 0; i < this.pos; i++) {
            int dist = Math.abs(num - elsNumeros[i]);
            if (dist < distMin) {
                posCercano = i;
            }
        }
        return posCercano;
    }
    
    public String toString(){
        return(Arrays.toString(elsNumeros));
    }
}
