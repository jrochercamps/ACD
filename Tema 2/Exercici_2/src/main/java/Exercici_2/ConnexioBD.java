/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercici_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose P.
 */
public class ConnexioBD{

    Connection Connexio = null;

    public void connect() {
            
        String connectionUrl = "jdbc:mysql://localhost:3308/employeesMini?useUnicode=true&useSSL=false&characterEncoding=UTF-8&user=root&password=root";
        try {
            Connexio = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

         //System.out.println("Connection to SQLite has been established.");

       
    }

    public void disConnect() throws SQLException {
        
        if(Connexio !=null)         
            Connexio.close();
        
    }    

    public void seleccionarDepartamentos() throws SQLException {
       
        connect();

        ResultSet result= Connexio.prepareStatement("select * from departments").executeQuery(); 

        System.out.format("%16s%16s\n", "Nº", "Name");

        while(result.next()){                        

            System.out.format("%16s%16s\n", result.getString(1), result.getString(2));
        }

        disConnect();            
       
    }
    
    public int crearDepartamentos(String num_dep, String nombre_dep) throws SQLException {
        connect();

        int res=-1;
        String sql = "INSERT INTO departments VALUES ('"+num_dep+"','"+nombre_dep+"');";

        PreparedStatement pst=Connexio.prepareStatement(sql);
        res = pst.executeUpdate();

        disConnect();

        return res;
    }

    public int eliminarDepartamentos(String num_dep) throws SQLException {
        connect();

        int res=-1;
        String sql = "delete from departments where dept_no like '"+num_dep+"'";

        PreparedStatement pst=Connexio.prepareStatement(sql);
        res = pst.executeUpdate();

        disConnect();

        return res;
    }

    public void buscarDepartamentos(String busc_dep) throws SQLException {
        connect();

        ResultSet result= Connexio.prepareStatement("select * from departments where dept_no like '"+busc_dep+"'").executeQuery();

        System.out.format("%16s%16s\n", "Nº", "Name");

        while(result.next()){                        

            System.out.format("%16s%16s\n", result.getString(1), result.getString(2));
        }

        disConnect(); 
    }

    public int actualizarDepartamentos(String num_dep, String nombre_dep) throws SQLException {
        connect();

        int res=-1;
        String sql = "update departments set dept_name = '"+nombre_dep+"' where dept_no like '"+num_dep+"'";

        PreparedStatement pst=Connexio.prepareStatement(sql);
        res = pst.executeUpdate();

        disConnect();

        return res;
    }

    public void seleccionarEmpleados() throws SQLException {
        connect();

        ResultSet result= Connexio.prepareStatement("select * from employees").executeQuery(); 

        System.out.format("%16s%16s%16s%16s%16s%16s\n", "Nº", "Fecha nacimiento", "Nombre", "Apellidos", "Sexo", "Fecha de contratación");

        while(result.next()){                        

            System.out.format("%16s%16s%16s%16s%16s%16s\n", result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
        }

        disConnect();
    }
    
    public int crearEmpleados(String emp_no, String birth_date, String first_name, String last_name, String gender, String hire_date) throws SQLException {
        connect();

        int res=-1;
        
        String sql = "INSERT INTO employees VALUES (?,?,?,?,?,?);";

        PreparedStatement pst=Connexio.prepareStatement(sql);
        
        pst.setString(1, emp_no);
        pst.setDate(2, java.sql.Date.valueOf(birth_date));
        pst.setString(3, first_name);
        pst.setString(4, last_name);
        pst.setString(5, gender);
        pst.setDate(6, java.sql.Date.valueOf(hire_date));
        
        
        res = pst.executeUpdate();

        disConnect();

        return res;
    }
    
    public int actualizarEmpleados(String emp_no, String birth_date, String first_name, String last_name, String gender, String hire_date) throws SQLException {
        connect();

        int res=-1;
        
        String sql = "update employees set birth_date = ?,first_name = ?, last_name = ?, gender = ?, hire_date = ? where emp_no = ?";
        
        PreparedStatement pst=Connexio.prepareStatement(sql); 
        
        pst.setDate(1, java.sql.Date.valueOf(birth_date));
        pst.setString(2, first_name);
        pst.setString(3, last_name);
        pst.setString(4, gender);
        pst.setDate(5, java.sql.Date.valueOf(hire_date));
        pst.setString(6, emp_no);
        
        res = pst.executeUpdate();
        
        disConnect();
        
        return res;
        
    }

    public int eliminarEmpleados(String emp_no) throws SQLException {
        connect();

        int res=-1;
        String sql = "delete from employees where emp_no like '"+emp_no+"'";

        PreparedStatement pst=Connexio.prepareStatement(sql);
        res = pst.executeUpdate();

        disConnect();

        return res;
    }

    public void buscarEmpleados(String num_empl) throws SQLException {
        connect();

        ResultSet result = Connexio.prepareStatement("select * from employees where emp_no like '"+num_empl+"'").executeQuery();            


        System.out.format("%16s%16s%16s%16s%16s%16s\n", "Nº", "Fecha nacimiento", "Nombre", "Apellidos", "Sexo", "Fecha de contratación");

        while(result.next()){                        

            System.out.format("%16s%16s%16s%16s%16s%16s\n", result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
        }

        disConnect();
    }

    public void seleccionarEmpleadosByDepartamento(String num_empl) throws SQLException {
        connect();

        ResultSet result = Connexio.prepareStatement("select e.*,dept_emp.dept_no "
                + "from employees as e, dept_emp "
                + "where dept_emp.emp_no = e.emp_no and dept_emp.dept_no = '"+num_empl+"'").executeQuery();            


        System.out.format("%16s%16s%16s%16s%16s%16s%16s\n", "Nº", "Fecha nacimiento", "Nombre", "Apellidos", "Sexo", "Fecha de contratación","Departamento");

        while(result.next()){                        

            System.out.format("%16s%16s%16s%16s%16s%16s%16s\n", 
                    result.getString(1), 
                    result.getString(2), 
                    result.getString(3), 
                    result.getString(4), 
                    result.getString(5), 
                    result.getString(6),
                    result.getString(7));
        }

        disConnect();
    }

    public void buscarDepartamentoPorEmpleadoActual(String emp_no) throws SQLException {
        //select e.* from employees as e, dept_emp where dept_emp.emp_no = e.emp_no  and dept_emp.to_date = '9999-01-01'
        connect();

        PreparedStatement pst= Connexio.prepareStatement(""
                + "select d.*,e.emp_no "
                + "from employees as e, dept_emp, departments as d "
                + "where d.dept_no=dept_emp.dept_no "
                + "and dept_emp.emp_no = e.emp_no  "
                + "and dept_emp.to_date = '9999-01-01'"
                + "and e.emp_no = ?"); 
        pst.setString(1, emp_no);
        ResultSet result = pst.executeQuery();
        
        
        System.out.format("%16s%16s%16s\n", "Nº Departamento", "Nombre departamento", "Nº empleado");

        while(result.next()){                        

            System.out.format("%16s%16s%16s\n", result.getString(1), result.getString(2), result.getString(3));
        }

        disConnect();
        
    }

    public void buscarPeriodoByDepartamentoByEmpleado(String emp_no, String busc_dep) throws SQLException {
         //select de.from_date, de.to_date from employees as e, dept_emp as de where e.emp_no = de.emp_no and e.emp_no = 10017 and de.dept_no = 'd001'
        connect();

        PreparedStatement pst= Connexio.prepareStatement(""
                + "select de.from_date, de.to_date "
                + "from employees as e, dept_emp as de "
                + "where e.emp_no = de.emp_no and e.emp_no = ? and de.dept_no = ?"); 
        pst.setString(1, emp_no);
        pst.setString(2, busc_dep);
        ResultSet result = pst.executeQuery();
        
        //# dept_no, dept_name, emp_no


        
        System.out.format("%16s%16s\n", "Desde la fecha", "Hasta la fecha");

        while(result.next()){                        

            System.out.format("%16s%16s\n", result.getString(1), result.getString(2));
        }

        disConnect();
    }

    public void seleccionarEmpleadosActualesHistorico(String busc_dep) throws SQLException {
   
        connect();
        
        String listado_actual ="";
        String listado_historico = "";
        
        
        PreparedStatement pst = Connexio.prepareStatement(""
                + "select e.*,de.to_date  "
                + "from employees as e, dept_emp as de "
                + "where e.emp_no = de.emp_no and de.dept_no=?");
        pst.setString(1, busc_dep);  
        
        ResultSet result = pst.executeQuery();

        while(result.next()){
            String fecha_fin_contrato = result.getString(7);
            
            if(fecha_fin_contrato.compareTo("9999-01-01")==0) {
                listado_actual+=
                        String.format("%16s%16s%16s%16s%8s%20s\n", 
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6));                
            }
            else
                listado_historico+=
                        String.format("%16s%16s%16s%16s%8s%20s%20s\n", 
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7));              
        }        
        
        //Mostrar actuales
        System.out.println("EMPLEADOS ACTUALES");
        System.out.println("==============================================");
        System.out.format("%16s%16s%16s%16s%8s%20s\n", "Nº", "Fecha nacimiento", "Nombre", "Apellidos", "Sexo", "Fecha de contratación");
           
        System.out.print(listado_actual);
       
        
        //Mostrar histórico
        System.out.println("EMPLEADOS HISTÓRICOS");
        System.out.println("==============================================");
        System.out.format("%16s%16s%16s%16s%16s%8s%20s\n", "Nº", "Fecha nacimiento", "Nombre", "Apellidos", "Sexo", "Fecha de contratación", "Fecha fin de contrato");
        
        System.out.print(listado_historico);

        
        disConnect();
    }

    public void actualizarContratoEmpleadoDepartamento(String emp_no, String dept_no) throws SQLException {
        try {
            connect();
            
            java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
            
            //Transacción
            Connexio.setAutoCommit(false);
            
            //Búscamos el contrado del empleado en el otro departamento
            PreparedStatement pst= Connexio.prepareStatement(""
                    + "select de.dept_no "
                    + "from dept_emp as de "
                    + "where de.to_date like '9999-01-01' and "
                    + "de.emp_no = ?");
            
            pst.setString(1,emp_no);
            ResultSet result = pst.executeQuery();
            
            //Obtenemos el nº del departamento al que esta activo
            String dept_no_anterior="";
            if(result.next()){
                dept_no_anterior = result.getString(1);
            }
            
            //Comprobamos que se va a cambiar a otro departamento
            
            if(dept_no_anterior.compareTo(dept_no)!=0){
                
                //finalizamos el contrato
                String sql = "update dept_emp set to_date = ? where emp_no = ? and dept_no= ? ";

                pst=Connexio.prepareStatement(sql);

                pst.setDate(1,java.sql.Date.valueOf(today.toLocalDate()));
                pst.setString(2,emp_no);
                pst.setString(3,dept_no_anterior);            

                pst.executeUpdate();

                //Añadimos el nuevo contrato            
                sql = "INSERT INTO dept_emp VALUES (?,?,?,?)";

                pst=Connexio.prepareStatement(sql); 

                pst.setString(1,emp_no);
                pst.setString(2,dept_no);
                pst.setDate(3,java.sql.Date.valueOf(today.toLocalDate()));
                pst.setString(4,"9999-01-01");

                pst.executeUpdate();
                
                //Finalizamos la transacción
                Connexio.commit();
                Connexio.setAutoCommit(true);
               
            }else{
                Connexio.rollback();
            }    
            
            
            
            disConnect();
        } catch (SQLException ex) {
            Connexio.rollback();
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 

    
    
    
}
