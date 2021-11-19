/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercici_5;

import LibrariesProject.FileFunctionsEmpleados;
import Models.Empleado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Jose P.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
private static String ficheroAleatorioempleado = "AleatorioEmple.dat";
      
    public static void main(String[] args) throws IOException, ParserConfigurationException {      
        Empleado[] empleados = null;
        Scanner sc = new Scanner(System.in);
        int idBuscar;      
        
        File fichero = new File(ficheroAleatorioempleado);
        RandomAccessFile file;
        //declara el fichero de acceso aleatorio
   
        while(true){
            System.out.println("Escoge una opción:");
            System.out.println("1: Importar fichero");
            System.out.println("2: Mostrar empleados");
            System.out.println("3: Añadir empleado");
            System.out.println("4: Eliminar empleado (ID)");
            System.out.println("5: Guardar fichero empleados");
            System.out.println("6: Cargar fichero empleados");
            System.out.println("7: Guardar ficheroXML empleados");
            System.out.println("8: Cargar ficheroXML empleados");
            int opt= sc.nextInt();


            switch(opt)
            {
                // declaración case
                // los valores deben ser del mismo tipo de la expresión
                case 1 :
                    file = new RandomAccessFile(fichero, "r");
                    
                    empleados = new FileFunctionsEmpleados().importarFichero(empleados,file); 

                   break;

                case 2 :
                   // Declaraciones
                    mostrarEmpleados(empleados);
                   break;
                case 3 :
                    int id=99;
                    String apellido="Nuevo";
                    int dep = 10;
                    double salario = 1500;
                    empleados = anyadirEmpleado(id, apellido, dep, salario,empleados);
                   break;

                 case 4 :       
                    empleados = eliminarEmpleado(2,empleados);
                 break;
                case 5:

                     new FileFunctionsEmpleados().serializarEmpleados2Fichero(empleados);

                     break;

                case 6:                    
                        try {
                            empleados = new FileFunctionsEmpleados().serializarFichero2Empleado();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    break;
                case 7:
                {
                    try {
                        new FileFunctionsEmpleados().serializarEmpleados2FicheroXML(empleados);
                    } catch (TransformerConfigurationException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (TransformerException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                 break;

                case 8:
                {
                    try {
                        empleados = new FileFunctionsEmpleados().serializarFicheroXML2Empleado(empleados);
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;


            }
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

    private static void mostrarEmpleados(Empleado[] empleados) {
        if(empleados == null || empleados.length == 0){
            System.out.println("No hay empleados");
        
        }
        else{
            for (Empleado empleado : empleados) {
                System.out.printf("%d, %s, %d, %f \n", empleado.id, empleado.apellidos, empleado.departamento, empleado.salario);
            }
        
        }
    }

    private static Empleado[] anyadirEmpleado(int id, String apellido, int dep, double salario, Empleado[] empleados) {
        
        Empleado[] aux = new Empleado[empleados.length+1];
        boolean insertado= false;
        
        for(int i=0;i < empleados.length;i++){
            if(empleados[i].id == id){
                System.err.println("No se puede añadir un empleado con Id repetido");
                return empleados;
            }
            
            if(empleados[i].id < id){
                aux[i] = empleados[i];              
            } 
            
        }
        
        aux[empleados.length] = new Empleado();
        aux[empleados.length].apellidos = apellido;
        aux[empleados.length].departamento = dep;
        aux[empleados.length].id = id;
        aux[empleados.length].salario = salario;
        
        return aux;   
        
    }

    private static Empleado[] eliminarEmpleado(int id, Empleado[] empleados) {
        Empleado[] aux = new Empleado[empleados.length-1];
        boolean encontrado = false;
        
        for(int i=0;i<empleados.length;i++){            
           
            if(empleados[i].id == id){
                empleados[i]= null;
                encontrado = true;
            }
        }
        
        for(int i=0,j=0;i<empleados.length && encontrado;i++,j++){
            if(empleados[i]==null){
                j--;
                continue;
            }
            aux[j] = empleados[i];
        }
        
        return encontrado?aux:empleados;
    }

    
}
