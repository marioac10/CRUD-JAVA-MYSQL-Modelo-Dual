/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.actividad_M;
import Modelo.actividad_Mc;
import Modelo.alumnos_M;
import Vista.Act_alumnos;
import Vista.Actividades;
import Vista.Agregar_unidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario Ac
 */
public class actividad_C implements ActionListener{
    private Actividades view;
    private Agregar_unidad viewNuevaU;
    private Act_alumnos viewActAlum;
    private actividad_M model;
    private actividad_Mc modelC;
    
    public actividad_C(Actividades view,Agregar_unidad viewNuevaU,Act_alumnos viewActAlum,actividad_M model, actividad_Mc modelC){
        this.view = view;
        this.viewNuevaU=viewNuevaU;
        this.viewActAlum=viewActAlum;
        this.model = model;
        this.modelC = modelC;
        this.view.btnBuscar.addActionListener(this);
        this.view.btnNuevaUnidad.addActionListener(this);
        this.view.btnVerAlumno.addActionListener(this);
        this.view.btnVertodo.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        //BOTONES DE LA VENTANA Agregar_unidad
        this.viewNuevaU.btnGuardar.addActionListener(this);
        this.viewNuevaU.btnCancelar.addActionListener(this);
        //BOTONES DE LA VENTANA Act_alumnos
        this.viewActAlum.btnBuscar.addActionListener(this);
        this.viewActAlum.btnEliminar.addActionListener(this);
        this.viewActAlum.btnGuardar.addActionListener(this);
        //this.viewActAlum.comboUnidad.addActionListener(this);
    }
    
    public void iniciar(){
        view.setTitle("Actividades");
        view.setLocationRelativeTo(null);
        viewActAlum.lblId_alumno.setVisible(false);
        listar();
        llenarComboUnidad();
        viewActAlum_ComboUnidad();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        //----BOTONES DE LA VISTA Actividades
        if(e.getSource()==view.btnBuscar){
            //System.out.println(view.txtUnidad.getText());
            if(view.txtUnidad.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"El campo matrícula esta vacío");
            }else{
                model.setIdUnidad(Integer.parseInt(view.txtUnidad.getText()));
                DefaultTableModel modeloTabla = (DefaultTableModel) view.tblActividades.getModel();
                modeloTabla.setRowCount(0);
                if(modelC.buscar(model,modeloTabla)){
                    view.lblUnidad.setText(model.getNombreUnidad());
                    view.lblNumerobloques.setText(model.getNumeroAct());
                }else{
                    JOptionPane.showMessageDialog(null,"¡Registro no encontrado!");
                }
            }
        }
        
        if(e.getSource()==view.btnNuevaUnidad){
            viewNuevaU.setVisible(true);
            viewNuevaU.setLocationRelativeTo(null);
        }
        
        if(e.getSource()==view.btnVertodo){
            listar();   
        }
       
        if(e.getSource()==view.btnGuardar){
            model.setNombretema(view.txtTema.getText());
            model.setEntregable(view.txtEntregable.getText());
            model.setIdUnidad(Integer.parseInt( (String) view.comboUnidad.getSelectedItem()));
            System.out.println(view.comboUnidad.getSelectedItem());
            if(modelC.agregar(model)){
                JOptionPane.showMessageDialog(null,"Guardado con éxito");
                listar();
                limpiarAct();
            }
        }
        
        if(e.getSource()==view.btnVerAlumno){
            viewActAlum.setVisible(true);
            viewActAlum.setLocationRelativeTo(null);
        }
        
        
        //----BOTONES DE LA VISTA Agregar_unidad
        if(e.getSource()==viewNuevaU.btnGuardar){
            model.setNombreUnidad(viewNuevaU.txtNuevaUnidad.getText());
            if(modelC.agregar_Unidad(model)){
                JOptionPane.showMessageDialog(null,"!Actividad guardada!");
                viewNuevaU.dispose();
                llenarComboUnidad();
            }else{
                JOptionPane.showMessageDialog(null,"¡Error al agregar!");
            }
        }
        
        if(e.getSource()==viewNuevaU.btnCancelar){
            viewNuevaU.dispose();
        }
        
        //----BOTONES DE LA VISTA Act_alumnos
        if(e.getSource()==viewActAlum.btnBuscar){
            if(viewActAlum.txtMatricula.getText()==" "){
                JOptionPane.showMessageDialog(null,"El campo Matrícula está vacío");
            }else{
                DefaultTableModel modeloTabla = (DefaultTableModel) viewActAlum.tblActalumno.getModel();
                modeloTabla.setRowCount(0);
                
                alumnos_M alum=new alumnos_M();
                alum.setMatricula(viewActAlum.txtMatricula.getText());
                if(modelC.buscar_Act_Alum(modeloTabla,alum)){ 
                    viewActAlum.lblNombre.setText(alum.getNombre()+" "+alum.getPaterno()+" "+alum.getMaterno());
                    viewActAlum.lblId_alumno.setText(String.valueOf(alum.getId()));
                }else{
                    JOptionPane.showMessageDialog(null,"Alumno no encontrado");
                }
            } 
        }
        
        if(e.getSource()==viewActAlum.btnGuardar){
            if(viewActAlum.lblId_alumno.getText()==""){
                JOptionPane.showMessageDialog(null,"Error, primero debe buscar un alumno");
            }else{
                actividad_M act_M=(actividad_M) viewActAlum.comboActividad.getSelectedItem();
                int idTema= act_M.getIdTema();
                int idalumno= Integer.parseInt(viewActAlum.lblId_alumno.getText());
                //System.out.println(idalumno+"----"+idTema);
                if(modelC.agregarActAlum(idalumno,idTema)){
                    JOptionPane.showMessageDialog(null,"Actividad asignada con éxito");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al guardar");
                }
            }
        }
        
        if(e.getSource()==viewActAlum.btnEliminar){

            if(viewActAlum.tblActalumno.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null,"Error, debe seleccionar una actividad de la tabla");
            }else{
                int fila = viewActAlum.tblActalumno.getSelectedRow();
                int idtema= Integer.parseInt(viewActAlum.tblActalumno.getValueAt(fila,0).toString());
                int idalumno=Integer.parseInt(viewActAlum.lblId_alumno.getText());
                if(modelC.eliminar_Actalum(idalumno,idtema)){
                    JOptionPane.showMessageDialog(null,"Registro eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"Error al eliminar");
                }
            }
        }
        
    }
    
    public void listar(){
        DefaultTableModel modeloTabla = (DefaultTableModel) view.tblActividades.getModel();
        modeloTabla.setRowCount(0);
        modelC.getTable(modeloTabla);   
    }
    
    /*public void listar_Act_lum(){
        DefaultTableModel modeloTabla = (DefaultTableModel) viewActAlum.tblActalumno.getModel();
        modeloTabla.setRowCount(0);
        modelC.getTable_Act_Alum(modeloTabla);   
    }*/
    public void llenarComboUnidad(){
        actividad_Mc act= new actividad_Mc();
        ArrayList<actividad_M> listaUnidades= act.comboUnidad();
        
        view.comboUnidad.removeAllItems();
        for(int i=0; i<listaUnidades.size();i++){
            view.comboUnidad.addItem(String.valueOf(listaUnidades.get(i).getIdUnidad()));
        }
    }
    
    public void viewActAlum_ComboUnidad(){
        actividad_Mc act= new actividad_Mc();
        Vector<actividad_M> listaUnidades= act.act_comboUnidad();
        
        viewActAlum.comboUnidad.removeAllItems();
        //DefaultComboBoxModel combo= new DefaultComboBoxModel(act.act_comboUnidad());
        //viewActAlum.comboUnidad.setModel(combo);
        
        //viewActAlum.comboUnidad.addItem("Seleccione una unidad");
        for(int i=0; i<listaUnidades.size();i++){
            viewActAlum.comboUnidad.addItem(String.valueOf(listaUnidades.get(i).getIdUnidad()));
        }
        /*for(int i=0; i<listaUnidades.size();i++){
            combo.addElement(listaUnidades.get(i).getIdUnidad());
        }*/
        
    }
    
    
    public void limpiar(){
        view.lblNumerobloques.setText(null);
        view.lblUnidad.setText(null);
    }
    
    public void limpiarAct(){
        view.txtTema.setText(null);
        view.txtEntregable.setText(null);
    }
    
}
