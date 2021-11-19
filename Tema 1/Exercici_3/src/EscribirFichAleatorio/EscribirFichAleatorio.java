/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EscribirFichAleatorio;

/**
 *
 * @author JPC
 */
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EscribirFichAleatorio {

    private static int _registro = 36;

    public static void consultar(int identificador){

    }

    public static void main(String[] args) throws IOException {      

        Scanner sc = new Scanner(System.in);
        int idBuscar;
        double salario;

        File fichero = new File("AleatorioEmple.dat");
        RandomAccessFile file;
        //declara el fichero de acceso aleatorio

        System.out.println("Escoge una opción:");
        System.out.println("1: Buscar trabajador (ID)");
        System.out.println("2: Actualizar trabajador (ID y salario)");
        System.out.println("3: Calcular salarios por departamento");

        int opt= sc.nextInt();
        switch(opt){
             // declaración case
             // los valores deben ser del mismo tipo de la expresión
             case 1 :
                  file = new RandomAccessFile(fichero, "r");
                  System.out.println("Dime el ID del empleado a buscar:");
                  idBuscar = sc.nextInt();        
                  consultar(idBuscar,file);
                  file.close();
                  break; // break es opcional

             case 2 :
                // Declaraciones
                 file = new RandomAccessFile(fichero, "rw");

                 System.out.println("Dime el ID del empleado a buscar:");
                 idBuscar = sc.nextInt();

                 System.out.println("Dime la modificación del salario:");
                 salario = sc.nextDouble();

                 actualizarSalario(idBuscar,salario,file);
                 file.close();
                break; // break es opcional
             case 3 :
                 file = new RandomAccessFile(fichero, "r");
                 System.out.println("Dime el ID del departamento:");
                 idBuscar = sc.nextInt();
                 sumaAgrupadaPorDepartamento(idBuscar,file);
                 break; // break es opcional

         }

/*
//arrays con los datos
   String apellido[] = {"FERNANDEZ","GIL","LOPEZ","RAMOS",
                        "SEVILLA","CASILLA", "REY"};//apellidos 
   int dep[] = {10, 20, 10, 10, 30, 30, 20};       //departamentos
   Double salario[]={1000.45, 2400.60, 3000.0, 1500.56, 
                     2200.0, 1435.87, 2000.0};//salarios

   StringBuffer buffer = null;//buffer para almacenar apellido
   int n=apellido.length;//numero de elementos del array

   for (int i=0;i<n; i++){ //recorro los arrays          	  
         file.writeInt(i+1); //uso i+1 para identificar empleado
     buffer = new StringBuffer( apellido[i] );      
     buffer.setLength(10); //10 caracteres para el apellido
     file.writeChars(buffer.toString());//insertar apellido
         file.writeInt(dep[i]);       //insertar departamento
         file.writeDouble(salario[i]);//insertar salario
   }
 */
     //cerrar fichero 
   }

    private static void consultar(int id, RandomAccessFile file) {
        int posicion = (id -1) * _registro, dep;
        char apellido[] = new char[10], aux;
        Double salario;

        try {
            if(posicion>=file.length())
                System.out.println("ID: "+id+" no existe empleado...");
            else{
                file.seek(posicion);
                id = file.readInt();

                //recorro uno a uno los caracteres del apellido
                for(int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux; //los voy guardando en el array                    
                }

                String apellidos = new String(apellido);
                dep = file.readInt(); //obtengo dep
                salario = file.readDouble(); //obtengo salario

                System.out.printf("ID: %s, Apellido: %10s,Departamento: %d, Salario: %.2f %n",id, apellidos.trim(), dep, salario);
            }
        } catch (IOException ex) {
            Logger.getLogger(EscribirFichAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void actualizarSalario(int id, double sumarSalario, RandomAccessFile file) {
        double salario_antiguo,salario_actual;
        int posicion = (id -1) * _registro, dep;
        char apellido[] = new char[10], aux;

        try {
            if(posicion>=file.length())
                System.out.println("ID: "+id+" no existe empleado...");
            else{
                file.seek(posicion);
                id = file.readInt();

                //recorro uno a uno los carácteres del apellido
                for(int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux; //los voy guardando en el array                    
                }

                String apellidos = new String(apellido);
                dep = file.readInt(); //obtengo dep
                salario_antiguo = file.readDouble(); //obtengo salario
                file.seek(posicion+4+20+4);
                salario_actual = salario_antiguo+sumarSalario;
                file.writeDouble(salario_actual);            

                System.out.printf("ID: %s, Apellido: %10s,Departamento: %d, Salario antiguo: %.2f %n, Salario actualizado:%.2f %n",id, apellidos.trim(), dep, salario_antiguo, salario_actual);
            }
        } catch (IOException ex) {
            Logger.getLogger(EscribirFichAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void sumaAgrupadaPorDepartamento(int idBuscar, RandomAccessFile file) {

        //int posicion = (id -1) * _registro, dep;
        int posicion = 0, dep, idEmpleado;
        char apellido[] = new char[10], aux;
        Double salario, sumaSalarios=0.0;

        try {
            for(;;){
                if(posicion>=file.length())
                    System.out.println("No hay empleados");
                else{
                    file.seek(posicion);
                    idEmpleado = file.readInt();

                    //recorro uno a uno los caracteres del apellido
                    for(int i = 0; i < apellido.length; i++) {
                        aux = file.readChar();
                        apellido[i] = aux; //los voy guardando en el array                    
                    }

                    String apellidos = new String(apellido);
                    dep = file.readInt(); //obtengo dep
                    salario = file.readDouble(); //obtengo salario

                    if(idBuscar == dep){
                        System.out.printf("ID: %s, Apellido: %10s,Departamento: %d, Salario: %.2f %n",idEmpleado, apellidos.trim(), dep, salario);                     
                        sumaSalarios+=salario;
                    }                        

                }

                posicion += _registro;
                    //Si he recorrido todos los bytes salgo del for
                    if (file.getFilePointer() == file.length()) {
                        break;
                    }

            }
            System.out.printf("El salario total del departamento %d es %.2f %n",idBuscar,sumaSalarios);


        } catch (IOException ex) {
            Logger.getLogger(EscribirFichAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}