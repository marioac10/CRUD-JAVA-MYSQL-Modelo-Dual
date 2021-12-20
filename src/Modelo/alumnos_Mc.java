/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario Ac
 */
public class alumnos_Mc extends Conexion {
    
    public boolean insertar(alumnos_M alumno){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql= "INSERT INTO alumno (matricula, nombre, apellido_p, apellido_m, telefono, correo, carrera) VALUES(?,?,?,?,?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,alumno.getMatricula());
            ps.setString(2,alumno.getNombre());
            ps.setString(3,alumno.getPaterno());
            ps.setString(4,alumno.getMaterno());
            ps.setString(5,String.valueOf(alumno.getTelefono()));
            ps.setString(6,alumno.getCorreo());
            ps.setString(7,alumno.getCarrera());
            ps.execute();
            return true;
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        } 
    }
    
    public boolean modificar(alumnos_M alumno){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql= "UPDATE alumno SET matricula=?, nombre=?, apellido_p=?, apellido_m=?, telefono=?, correo=?,carrera=? WHERE id_alumno=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,alumno.getMatricula());
            ps.setString(2,alumno.getNombre());
            ps.setString(3,alumno.getPaterno());
            ps.setString(4,alumno.getMaterno());
            ps.setString(5,alumno.getTelefono());
            ps.setString(6,alumno.getCorreo());
            ps.setString(7,alumno.getCarrera());
            ps.setInt(8,alumno.getId());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        } 
    }
    
    public boolean eliminar(alumnos_M alumno){
        PreparedStatement ps;
        Connection con = getConexion();
        
        String sql= "DELETE FROM alumno WHERE id_alumno=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,alumno.getId());
            ps.execute();
            return true;
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        } 
    }
    
    public boolean buscar(alumnos_M alumno){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT * FROM alumno WHERE matricula=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,alumno.getMatricula());
            rs=ps.executeQuery();
            
            if(rs.next()){
                alumno.setId(Integer.parseInt(rs.getString("id_alumno")));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setPaterno(rs.getString("apellido_p"));
                alumno.setMaterno(rs.getString("apellido_m"));
                alumno.setTelefono(rs.getString("telefono"));
                alumno.setCorreo(rs.getString("correo"));
                alumno.setCarrera(rs.getString("carrera"));
                return true;
            }
            return false;
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        } 
    }
    
    public boolean buscar_Id(alumnos_M alumno){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql= "SELECT * FROM alumno WHERE id_alumno=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,alumno.getId());
            rs=ps.executeQuery();
            
            if(rs.next()){
                alumno.setId(Integer.parseInt(rs.getString("id_alumno")));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setPaterno(rs.getString("apellido_p"));
                alumno.setMaterno(rs.getString("apellido_m"));
                alumno.setTelefono(rs.getString("telefono"));
                alumno.setCorreo(rs.getString("correo"));
                alumno.setCarrera(rs.getString("carrera"));
                return true;
            }
            return false;
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        } 
    }
    
    public void getTable(DefaultTableModel modeloTabla){
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;
        
        try{
            Connection con = getConexion();
            String sql= "SELECT matricula,nombre,apellido_p,apellido_m,telefono,correo FROM alumno";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();
           
            while(rs.next()){
                Object[] fila = new Object[columnas];
                for(int indice=0; indice<columnas; indice++){
                    fila[indice] = rs.getObject(indice + 1);
                }
                modeloTabla.addRow(fila);
            }
            //System.out.println("tabla");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString());
        }
    }
    
}
