/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Exercici_2;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author jpc
 */
public class Main {

    static Scanner sc=new Scanner(System.in);
    
    public static int menu(){
        
        System.out.println("1: Seleccionar departamentos");
        System.out.println("2: Crear departamentos");
        System.out.println("3: Actualizar departamentos por ID");
        System.out.println("4: Eliminar departamentos por ID");
        System.out.println("5: Buscar departamentos por ID");
        System.out.println("6: Seleccionar empleados");
        System.out.println("7: Crear empleados");
        System.out.println("8: Actualizar empleados por ID");
        System.out.println("9: Eliminar empleados por ID");
        System.out.println("10: Buscar empleados por ID");   
        System.out.println("99: Salir"); 
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        
        System.out.println("Selecciona una opción:");
        
        int opt =sc.nextInt();

        return opt;
    
    }
    
    /*
    
    Crear un mantenimiento de la tabla Departamentos. Opciones para crear, modificar, eliminar y buscar departemantos.
2. Crear un mantenimiento de la tabla Empleados. Opciones para crear, modificar, eliminar y
buscar departemantos.
3. Otras opciones:
Dado el código de un empleado ver su información
Dado el código de un departamento ver su información
Dado el código de un departamento ver los empleados que tiene (y ha tenido)
Dado el código de un empleado ver a qué departamento pertenece actualmente.
Dado el código de un empleado y un departamento ver si el empleado ha pertenecido a ese
departamento y si es afirmativo entre qué fechas (histórico de empleados)
Dado el código de un departamento ver los empleados (actuales) y el histórico (empleados que
trabajaron allí pero que ahora no lo hacen)
    */
    
    public static void menuGeneral() throws SQLException{
    
        int opt = -1;
        while(opt!=4){ 
            
            System.out.println("1: Mantenimiento departamento");
            System.out.println("2: Mantenimiento empleados");
            System.out.println("3: Otras consultas");
            System.out.println("4: Salir");

            System.out.println("Selecciona una opción:");
            opt =sc.nextInt();

            switch(opt){
                case 1:
                    menuDepartamento();
                    break;
                case 2:
                    menueEmpleados();
                    break;
                case 3: 
                    menuConsultas();
                    break;  
                case 4:
                    System.out.println("Saliendo del programa");
            }
        }
    }
    
    public static void menuDepartamento() throws SQLException{
        
        ConnexioBD conn = new ConnexioBD();
    
        Scanner sc2= new Scanner(System.in);
        String busc_dep, num_dep, nombre_dep;
        int resultado;
        
        System.out.println("1: Seleccionar departamentos");
        System.out.println("2: Crear departamentos");
        System.out.println("3: Actualizar departamentos");
        System.out.println("4: Borrar departamentos");
        System.out.println("5: Buscar departamentos");
        System.out.println("6: Atrás");
        
        System.out.println("Selecciona una opción:");
        int opt =sc.nextInt();
        
        switch(opt){
            case 1:
                conn.seleccionarDepartamentos();
                break;
            case 2:
                System.out.println("Inserta código//Nombre de departamento");
                    
                num_dep=sc2.nextLine();
                nombre_dep=sc2.nextLine();

                resultado = conn.crearDepartamentos(num_dep,nombre_dep);

                System.out.println("Filas añadidas: "+resultado);
                break;
            case 3: 
                System.out.println("Selecciona un Nº de departamento:"); 

                num_dep=sc2.nextLine();                    

                System.out.println("Escribe su nombre para actualizarlo");
                nombre_dep=sc2.nextLine();

                resultado = conn.actualizarDepartamentos(num_dep,nombre_dep);   

                System.out.println("Filas actualizadas: "+resultado);
                break; 
                
            case 4: 
                System.out.println("Selecciona un Nº de departamento:"); 

                num_dep=sc2.nextLine();                   

                resultado = conn.eliminarDepartamentos(num_dep);   

                System.out.println("Filas borrada: "+resultado);
                break;
                
            case 5: 
                System.out.println("Selecciona un Nº de departamento:"); 

                busc_dep =sc2.nextLine();

                conn.buscarDepartamentos(busc_dep);
            case 6:
                menuGeneral();
                break;    
        }
        
    }
    
    public static void menueEmpleados() throws SQLException{
        ConnexioBD conn = new ConnexioBD();
    
        Scanner sc2= new Scanner(System.in);
        String num_dep, num_emp;
        String emp_no, birth_date, first_name, last_name, gender, hire_date;
        int resultado;
        
        System.out.println("1: Seleccionar empleados");
        System.out.println("2: Crear empleados");
        System.out.println("3: Actualizar empleados");
        System.out.println("4: Borrar empleados");
        System.out.println("5: Buscar empleados");
        System.out.println("6: Atrás");
        
        System.out.println("Selecciona una opción:");
        int opt =sc.nextInt();
        
        switch(opt){
            case 1:
                conn.seleccionarEmpleados();
                break;
            case 2:
                System.out.println("Inserta código//Fecha nacimiento//Nombre//Apellidos//Genero//Fecha contratación");

                emp_no=sc2.nextLine();
                birth_date=sc2.nextLine();
                first_name=sc2.nextLine();
                last_name=sc2.nextLine();
                gender=sc2.nextLine();
                hire_date=sc2.nextLine();

                resultado = conn.crearEmpleados(emp_no, birth_date, first_name, last_name, gender, hire_date);

                System.out.println("Filas añadidas: "+resultado);

                break;
            case 3:
                System.out.println("Selecciona un Nº de empleado:"); 

                num_emp=sc2.nextLine(); 

                System.out.println("Fecha nacimiento//Nombre//Apellidos//Genero//Fecha contratación");                    

                birth_date=sc2.nextLine();
                first_name=sc2.nextLine();
                last_name=sc2.nextLine();
                gender=sc2.nextLine();
                hire_date=sc2.nextLine();

                resultado = conn.actualizarEmpleados(num_emp, birth_date, first_name, last_name, gender, hire_date);   

                System.out.println("Filas actualizadas: "+resultado);
                break;
            case 4:
                System.out.println("Selecciona un Nº de empleado:"); 

                num_dep=sc2.nextLine();                   

                resultado = conn.eliminarEmpleados(num_dep);   

                System.out.println("Filas borrada: "+resultado);

                break;
            case 5:
                System.out.println("Selecciona un Nº de empleado:"); 

                emp_no =sc2.nextLine();

                conn.buscarEmpleados(emp_no);
                break;
            case 6:
                menuGeneral();
                break;
    
        }
    }
    
    public static void menuConsultas() throws SQLException{
        
        /*
        1 Dado el código de un empleado ver su información
        2 Dado el código de un departamento ver su información
        3 Dado el código de un departamento ver los empleados que tiene (y ha tenido)
        4 Dado el código de un empleado ver a qué departamento pertenece actualmente.
        5 Dado el código de un empleado y un departamento ver si el empleado ha pertenecido a ese departamento y si es afirmativo entre qué fechas (histórico de empleados)
        6 Dado el código de un departamento ver los empleados (actuales) y el histórico (empleados que trabajaron allí pero que ahora no lo hacen)
        7 Realizar un cambio de departamento a un empleado dado. Debe actualizarse la fecha de finaliza-ción, y insertarse el nuevo departamento.
        
        */
        
        
        ConnexioBD conn = new ConnexioBD();
    
        Scanner sc2= new Scanner(System.in);
        String num_dep, num_emp,busc_dep;
        String emp_no, birth_date, first_name, last_name, gender, hire_date;
        int resultado;
        
        int opt = -1;
        while(opt!=8){ 
            
            System.out.println("1: Dado el código de un empleado ver su información");
            System.out.println("2: Dado el código de un departamento ver su información");
            System.out.println("3: Dado el código de un departamento ver los empleados que tiene");
            System.out.println("4: Dado el código de un empleado ver a qué departamento pertenece actualmente.");
            System.out.println("5: Dado el código de un empleado y un departamento ver si el empleado ha pertenecido a ese departamento y si es afirmativo entre qué fechas");
            System.out.println("6: Dado el código de un departamento ver los empleados (actuales) y el histórico (empleados que trabajaron allí pero que ahora no lo hacen)");
            System.out.println("7: Realizar un cambio de departamento a un empleado dado. Debe actualizarse la fecha de finalización, e insertarse el nuevo departamento");
            System.out.println("8: Salir");

            System.out.println("Selecciona una opción:");
            opt =sc.nextInt();

            switch(opt){
                case 1:
                    System.out.println("Selecciona un Nº de empleado:"); 

                    emp_no =sc2.nextLine();

                    conn.buscarEmpleados(emp_no);
                    
                    break;
                case 2:
                    System.out.println("Selecciona un Nº de departamento:"); 

                    busc_dep =sc2.nextLine();

                    conn.buscarDepartamentos(busc_dep);
                    break;
                case 3: 
                    System.out.println("Selecciona un Nº de departamento:"); 

                    busc_dep =sc2.nextLine();
                    conn.seleccionarEmpleadosByDepartamento(busc_dep);
                    break;
                case 4:
                    
                    System.out.println("Selecciona un Nº de empleado:"); 

                    emp_no =sc2.nextLine();

                    conn.buscarDepartamentoPorEmpleadoActual(emp_no);
                    
                    break;
                case 5:
                    System.out.println("Selecciona un Nº de empleado:"); 

                    emp_no =sc2.nextLine();

                    System.out.println("Selecciona un Nº de departamento:"); 

                    busc_dep =sc2.nextLine();
                    
                    conn.buscarPeriodoByDepartamentoByEmpleado(emp_no,busc_dep);
                    break;
                case 6: 
                    System.out.println("Selecciona un Nº de departamento:"); 

                    busc_dep =sc2.nextLine();
                    
                    conn.seleccionarEmpleadosActualesHistorico(busc_dep);
                    break;
                case 7: 
                    System.out.println("Selecciona un Nº de empleado:"); 

                    emp_no =sc2.nextLine();

                    System.out.println("Selecciona un Nº de departamento:"); 

                    busc_dep =sc2.nextLine();
                    
                    conn.actualizarContratoEmpleadoDepartamento(emp_no,busc_dep);
                    break;
                case 8:
                    System.out.println("Saliendo del programa");
                    break;
            }
        }
    
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        
        menuGeneral();
        
    }
    
}
