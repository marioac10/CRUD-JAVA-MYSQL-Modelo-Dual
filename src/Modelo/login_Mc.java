/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario Ac
 */
public class login_Mc extends Conexion{
    
    public login_M iniciarsesion(login_M user){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT nombre_usuario,contraseña FROM usuario WHERE nombre_usuario= ? AND contraseña= ?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getUsuario());
            ps.setString(2,user.getContraseña());
            rs=ps.executeQuery();
            
            login_M usuario = new login_M();
            while(rs.next()){
                usuario.setUsuario(rs.getString("nombre_usuario"));
                usuario.setContraseña(rs.getString("contraseña")); 
            }
            //JOptionPane.showMessageDialog(null,String.valueOf(usuario.getUsuario()));
            return usuario;
           
        }catch (SQLException e){
            System.err.println(e);
            return null;
            
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            }
        }
    }
    
}
