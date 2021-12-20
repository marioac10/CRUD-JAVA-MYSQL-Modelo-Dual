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
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario Ac
 */
public class actividad_Mc extends Conexion{
    
    public boolean agregar(actividad_M actividad){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql= "INSERT INTO tema_unidad (nombre_tema,entregable,id_unidad) VALUES (?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,actividad.getNombretema());
            ps.setString(2,actividad.getEntregable());
            ps.setInt(3,actividad.getIdUnidad());
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
    
    public boolean agregar_Unidad(actividad_M actividad){
        PreparedStatement ps;
        String sql="INSERT INTO unidad_aprendizaje (nombre_unidad) VALUES (?)";
        Connection con=getConexion();
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,actividad.getNombreUnidad());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
            }
        }
    }
    
    public boolean buscar(actividad_M actividad,DefaultTableModel modeloTabla){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        ResultSet rsA = null;
        ResultSetMetaData rsmt;
        Connection con = getConexion();
        
        String sql= "SELECT nombre_unidad, COUNT(tema_unidad.id_unidad) AS numeroAct FROM unidad_aprendizaje INNER JOIN tema_unidad ON unidad_aprendizaje.id_unidad=tema_unidad.id_unidad WHERE tema_unidad.id_unidad=?";
        String sqlA="SELECT * FROM tema_unidad WHERE id_unidad=?";
        
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,actividad.getIdUnidad());
            rs=ps.executeQuery();
            
            ps=con.prepareStatement(sqlA);
            ps.setInt(1,actividad.getIdUnidad());
            rsA=ps.executeQuery();
            rsmt=rsA.getMetaData();
            int columnas=rsmt.getColumnCount();
            
            if(rs.next()){
                actividad.setNombreUnidad(rs.getString("nombre_unidad"));
                actividad.setNumeroAct(rs.getString("numeroAct"));
                
                while(rsA.next()){
                    Object[] fila = new Object[columnas];
                    for(int indice=0; indice<columnas; indice++){
                        fila[indice] = rsA.getObject(indice + 1);
                    }
                    modeloTabla.addRow(fila);
                }
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
            String sql= "SELECT * FROM tema_unidad";
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
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString());
        }
    }
    
    public ArrayList<actividad_M> comboUnidad(){
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<actividad_M> actividad=new ArrayList<>();
        String sql="SELECT id_unidad FROM unidad_aprendizaje";
        
        try{
            Connection con= getConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                actividad_M act=new actividad_M();
                act.setIdUnidad(rs.getInt("id_unidad"));
                actividad.add(act);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        return actividad;
    }
    
    
    
    //----METODOS DE LA CLASE Act_alumnos
    public boolean buscar_Act_Alum(DefaultTableModel modeloTabla,alumnos_M alum){
        PreparedStatement ps;
        ResultSet rs;
        ResultSet rsAlum;
        ResultSetMetaData rsmd;
        int columnas;
        
        try{
            Connection con = getConexion();
            String sqlAlum= "SELECT id_alumno,nombre,apellido_p,apellido_m FROM alumno WHERE matricula=?";
            ps=con.prepareStatement(sqlAlum);
            ps.setString(1,alum.getMatricula());
            rsAlum=ps.executeQuery();
            if(rsAlum.next()){
                alum.setNombre(rsAlum.getString("nombre"));
                alum.setPaterno(rsAlum.getString("apellido_p"));
                alum.setMaterno(rsAlum.getString("apellido_m"));
                alum.setId(rsAlum.getInt("id_alumno"));
            }
         
            String sql= "SELECT id_tema,nombre_tema,entregable,id_unidad FROM tema_unidad INNER JOIN actividad_alumno ON tema_unidad.id_tema=actividad_alumno.id_actividad WHERE id_alumno=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,String.valueOf(alum.getId()));
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
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.toString());
            return false;
        }
    }
    
    public Vector<actividad_M> act_comboUnidad(){
        PreparedStatement ps;
        ResultSet rs;
        Vector<actividad_M> actividad=new Vector<actividad_M>();
        String sql="SELECT id_unidad FROM unidad_aprendizaje";
        
        try{
            Connection con= getConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                actividad_M act=new actividad_M();
                act.setIdUnidad(rs.getInt("id_unidad"));
                actividad.add(act);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        return actividad;
    }
    
    public Vector<actividad_M> act_comboUnidadAct(int id_Unidad){
        PreparedStatement ps;
        ResultSet rs;
        Vector<actividad_M> actividad=new Vector<actividad_M>();
        String sql="SELECT id_tema,nombre_tema FROM tema_unidad WHERE id_unidad="+id_Unidad;
        
        try{
            Connection con= getConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            
            while(rs.next()){
                actividad_M act=new actividad_M();
                act.setIdTema(rs.getInt("id_tema"));
                act.setNombretema(rs.getString("nombre_tema"));
                actividad.add(act);
            }
        }catch(SQLException e){
            System.err.println(e);
        }
        return actividad;
    }
    
    public boolean agregarActAlum(int idAlumno, int idTema){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql= "INSERT INTO actividad_alumno (id_alumno,id_actividad) VALUES (?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1,idAlumno);
            ps.setInt(2,idTema);
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

    public boolean eliminar_Actalum(int idalumno,int idTema){
        PreparedStatement ps;
       
        try{
            Conexion con = new Conexion();
            Connection conn= con.getConexion();
        
            String sql= "DELETE FROM actividad_alumno WHERE id_alumno=? AND id_actividad=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,idalumno);
            ps.setInt(2,idTema);
            ps.execute();
            return true;
            
        }catch(SQLException ex){
            System.err.println();
            return false;
        }
    }
    
}
