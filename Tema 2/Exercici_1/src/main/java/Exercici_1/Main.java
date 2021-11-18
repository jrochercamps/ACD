/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exercici_1;

import java.sql.SQLException;

/**
 *
 * @author joange
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        //Class.forName("com.mysql.cj.jdbc.Driver");
        
      ConnexioBD conn = new ConnexioBD();
      conn.connect();
      /*
        if(conn.validaUser("Paco"))
            System.out.println("s'ha trobat!");
        else
            System.out.println("No s'ha trobat");
       */
       Validacio vld=new Validacio();
       vld.setVisible(true);
    }
    
}
