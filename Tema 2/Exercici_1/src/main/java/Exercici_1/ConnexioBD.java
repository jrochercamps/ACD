/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercici_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joange
 */
public class ConnexioBD{

    Connection Connexio = null;

    public void connect() {
            
        String connectionUrl = "jdbc:mysql://localhost:3308/usuarios?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";
        try {
            Connexio = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

         System.out.println("Connection to SQLite has been established.");

       
    }

    public void disConnect() throws SQLException {
        
         Connexio.close();
        
    }
    
    
    public boolean validaUser(String user) throws SQLException{
         
        boolean exiteUser = false;
        var result= Connexio.prepareStatement("select count(*) from users where username like '"+user+"'").executeQuery();
        
        if(result.next()){
            int numberRows = result.getInt(1);            
            
            return numberRows > 0 ? true: false;
        }
        else{
            return exiteUser;
        }

    }
    
    /**
     * 
     * @param user
     * @return 0 if all correct
     * @return 1 if wrong user
     * @return 2 if wrong password
     */
    public int validaPass(String user,String pass) throws SQLException{
        int res=-1;
        
        boolean exiteUser = false;
        var result= Connexio.prepareStatement("select * from users where username like '"+user+"'").executeQuery();
        
        if(result.next()){
            String passMD5_BD = result.getString(2);            
            String passMD5 = Xifrar.md5(pass);
            return passMD5.compareTo(passMD5_BD)==0?0:2;
        }
        else{
            return 1;
            
        }
        
        //return -1;

    }
    
    public int insertUser(String user,String pass) throws SQLException{
        int res=-1;
        String sql = "insert into users (username,password) values ('"+user+"','"+Xifrar.md5(pass)+"')";
        //var result= Connexio.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS).executeUpdate();
        PreparedStatement pst=Connexio.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        
        ResultSet generatedKeys = pst.getGeneratedKeys();
        if(generatedKeys.next()){
            int numberRows = generatedKeys.getInt(1);
            return numberRows;
        }
        else{
            return res;
        }     

    }
    
}
