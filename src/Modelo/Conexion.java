/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario Ac
 */
public class Conexion {
    private final String database="modelo_dual";
    private final String USUARIO = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mysql://localhost:3306/"+database;
    private Connection con = null;
    
    private boolean status = false;
    
    
    public Connection getConexion(){
        status = false;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.URL,this.USUARIO,this.PASSWORD);
           
        }catch (SQLException e){
            System.err.println(e);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    
    /*public static boolean getstatus(){
        return  status;
    }*/
    
    
}
