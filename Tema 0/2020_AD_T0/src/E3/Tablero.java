/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E3;

import java.util.Random;

/**
 *
 * @author joange
 */
public class Tablero {

    private int[][] elTablero;
    public final int TAM = 10;
    private final int tamanys[] = {2, 2, 3, 4, 5};

    private Random r;

    public Tablero() throws InterruptedException {
        // creem el tablero. Inicialment tot 0 (agua)
        elTablero = new int[TAM][TAM];

        r = new Random(System.currentTimeMillis());

        for (int tamany : tamanys) {
            colocar(tamany);
        }

    }

    private void colocar(int tamany) {
        // sorteamos la orientación del barco (H o V)

        boolean colocat = false;
        while (!colocat) {
            int ori = r.nextInt(2);
            int x = r.nextInt(TAM);
            int y = r.nextInt(TAM);
            //System.out.println("Posant barco  de tamany "+tamany+" en (" + x + ","+y+") " + "ori= " + ori);
            if (capBarco(x, y, ori, tamany)) {
                //   System.out.println("Posem barco");
                colocat = true;
                if (ori == 0) // hori
                {
                    for (int j = y; j < y + tamany; j++) {
                        elTablero[x][j] = 1;
                    }
                } else // vertical
                {
                    for (int i = x; i < x + tamany; i++) {
                        elTablero[i][y] = 1;
                    }
                }
            }

        }
    }

    private boolean capBarco(int x, int y, int orientacion, int tamany) {
        int min_x, min_y, max_x, max_y;

        if (x + tamany > TAM || y + tamany > TAM) {
            // System.out.println("No cap físicament");
            return false;
        }

        min_x = x - 1;
        min_y = y - 1;
        if (orientacion == 0) {// hori
            max_x = x + 1;
            max_y = y + tamany;
        } else {
            max_x = x + tamany;
            max_y = y + 1;
        }
        // System.out.printf("De %d,%d a %d,%d\n",min_x,min_y,max_x,max_y);
        // En la submatriu que hem triat ha d'estar tot a zeros

        for (int i = min_x; i <= max_x; i++) {
            for (int j = min_y; j <= max_y; j++) {
                if (i < 0 || j < 0 || i >= TAM || j >= TAM) {
                    continue;
                }
                if (elTablero[i][j] != 0) {
                    //  System.out.println("adjacent");
                    return false;
                }
            }
        }
        return true;
    }

    public int bomba(int i, int j) {
        System.out.printf("Comprovant %d-%d\n", i, j);
        if (i < 0 || j < 0 || i >= TAM || j >= TAM) {
            return -1;
        }
        // si hi ha barco el marquen
        if (elTablero[i][j] == 1) {
            System.out.println("Marquen a 2");
            elTablero[i][j] = 2;

            // comprovem si està afonat, a partir d'ell, les 4 direccions per si cal 
            // marcar-lo com afonat
            boolean afonat = true;    // suposem que està afonat
            // no sabem en quina direcció està 

            // cap a dalt, fins trobar un 1 o un 0 o eixir-nos
            for (int k = i; k >= 0 && elTablero[k][j] != 0; k--) {
                if (elTablero[k][j] == 1) {
                    afonat = false;
                    break;
                }
            }

            // cap a baix, fins trobar un 1 o un 0 o eixir-nos
            for (int k = i; k < TAM && elTablero[k][j] != 0; k++) {
                if (elTablero[k][j] == 1) {
                    afonat = false;
                    break;
                }
            }

            // cap a dreta, fins trobar un 1 o un 0 o eixir-nos
            for (int k = j; k < TAM && elTablero[i][k] != 0; k++) {
                if (elTablero[i][k] == 1) {
                    afonat = false;
                    break;
                }
            }

            // cap a esquerre, fins trobar un 1 o un 0 o eixir-nos
            for (int k = j; k >= 0 && elTablero[i][k] != 0; k--) {
                if (elTablero[i][k] == 1) {
                    afonat = false;
                    break;
                }
            }

            if (afonat) {
                //Si està afonat, he de repetir els bucles d'abans i posar un 3
                // DALT
                for (int k = i; k >= 0 && elTablero[k][j] != 0; k--) {
                    elTablero[k][j] = 3;
                }

                // BAIX
                for (int k = i; k < TAM && elTablero[k][j] != 0; k++) {
                    elTablero[k][j] = 3;
                }

                // DRETA
                for (int k = j; k < TAM && elTablero[i][k] != 0; k++) {
                    elTablero[i][k] = 3;
                }

                // ESQ
                for (int k = j; k >= 0 && elTablero[i][k] != 0; k--) {
                    elTablero[i][k] = 3;
                }
            }
        }
        
        return elTablero[i][j];
    }

    public boolean fi() {
        for (int i = 0; i < elTablero.length; i++) {
            for (int j = 0; j < elTablero.length; j++) {
                if (elTablero[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void imprimir() {
        // Cabecera ...
        System.out.print(" <-- ");
        for (int k = 0; k < TAM; k++) {
            System.out.print(k + " ");
        }
        System.out.println(" -->");

        for (int i = 0; i < TAM; i++) {
            System.out.print(i + "<-- ");
            for (int j = 0; j < TAM; j++) {
                System.out.print(elTablero[i][j] + " ");
            }
            System.out.println(" -->");
        }
        // Cola ...
        System.out.print(" <-- ");
        for (int k = 0; k < TAM; k++) {
            System.out.print(k + " ");
        }
        System.out.println(" -->");
    }
}
