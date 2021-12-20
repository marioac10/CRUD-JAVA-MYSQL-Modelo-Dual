/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.alumnos_M;
import Modelo.alumnos_Mc;
import Vista.Actualizar_alumno;
import Vista.Agregar_alumno;
import Vista.Alumnos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario Ac
 */
public class alumnos_C implements ActionListener{
    private Alumnos view;
    private Agregar_alumno viewAgregar;
    private Actualizar_alumno viewActualizar;
    private alumnos_M model;
    private alumnos_Mc modelC; 
    
    public alumnos_C(Alumnos view,Agregar_alumno viewAgregar,Actualizar_alumno viewActualizar,alumnos_M model,alumnos_Mc modelC){
        this.view = view;
        this.viewAgregar = viewAgregar;
        this.viewActualizar = viewActualizar;
        this.model = model;
        this.modelC = modelC;
        //Botones de la vista Alumnos
        this.view.btnAgregar.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnModificar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnListar.addActionListener(this);
        //Botones de la vista Agregar_alumno
        this.viewAgregar.btnGuardar.addActionListener(this);
        this.viewAgregar.btnCancelar.addActionListener(this);
        //Botones de la vista Actualizar_alumno
        this.viewActualizar.btnGuardar.addActionListener(this);
        this.viewActualizar.btnCancelar.addActionListener(this);
        
    }
    
    public void iniciar(){
        view.setTitle("Alumnos Del Dual");
        view.setLocationRelativeTo(null);
        view.lblIdalumno.setVisible(false);
        listar();
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        //Botones de la vista principal Alumnos
        if(e.getSource()==view.btnAgregar){
            //Agregar_alumno agregarA=new Agregar_alumno();
            //alumnos_C alumC=new alumnos_C(view,model,modelC);
            //agregarA_C crt=new agregarA_C(agregarA,model,modelC,alumC);
            viewAgregar.setVisible(true);
        }
        
        if(e.getSource()==view.btnModificar){
            //int fila=view.tblAlumnos.getSelectedRow();
            if(view.lblIdalumno.getText()==""){
                JOptionPane.showMessageDialog(null,"No ha seleccionado o buscado un registro");
            }else{
                model.setId(Integer.parseInt(view.lblIdalumno.getText()));
                if(modelC.buscar_Id(model)){
                    viewActualizar.txtMatricula.setText(model.getMatricula());
                    viewActualizar.txtNombre.setText(model.getNombre());
                    viewActualizar.txtPaterno.setText(model.getPaterno());
                    viewActualizar.txtMaterno.setText(model.getMaterno());
                    viewActualizar.txtTelefono.setText(model.getTelefono());
                    viewActualizar.txtCorreo.setText(model.getCorreo());
                    viewActualizar.txtCarrera.setText(model.getCarrera());
                    
                    viewActualizar.setVisible(true);
                    viewActualizar.setLocationRelativeTo(null);
                }else{
                    JOptionPane.showMessageDialog(null,"Error al actualizar");
                }
            }
        }
        
        if(e.getSource()==view.btnEliminar){
            if(view.lblIdalumno.getText()==""){
                JOptionPane.showMessageDialog(null,"Seleccione o busque un registro");
            }else{
                if (JOptionPane.showConfirmDialog(null, "Se eliminará el registro, ¿Desea continuar?",
        "Eliminar Registro",JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    model.setId(Integer.parseInt(view.lblIdalumno.getText()));
                    if(modelC.eliminar(model)){
                        JOptionPane.showMessageDialog(null,"¡Eliminación exitosa!");
                        limpiar();
                        listar();
                        //view.loadtable();
                    }else{
                        JOptionPane.showMessageDialog(null,"¡Error al eliminar!");
                    }
                }
            }
        }
        
        if(e.getSource()==view.btnBuscar){
            model.setMatricula(view.txtMatricula.getText());
            if(modelC.buscar(model)){
                view.lblNombrecompleto.setText(model.getNombre()+" "+model.getPaterno()+" "+model.getMaterno());
                view.lblIdalumno.setText(String.valueOf(model.getId()));
                view.lblCorreo.setText(model.getCorreo());
                view.lblTelefono.setText(model.getTelefono());
                view.lblCarrera.setText(model.getCarrera());
            }else{
                JOptionPane.showMessageDialog(null,"¡Registro no encontrado!");
            }
        }
        
        if(e.getSource()==view.btnLimpiar){
            limpiar();
        }
        
        if(e.getSource()==view.btnListar){
            listar();
        }
        
        
        //Botones de la vista Agregar_alumno
        if(e.getSource()==viewAgregar.btnGuardar){
            model.setMatricula(viewAgregar.txtMatricula.getText());
            model.setNombre(viewAgregar.txtNombre.getText());
            model.setPaterno(viewAgregar.txtPaterno.getText());
            model.setMaterno(viewAgregar.txtMaterno.getText());
            model.setTelefono(viewAgregar.txtTelefono.getText());
            model.setCorreo(viewAgregar.txtCorreo.getText());
            model.setCarrera(viewAgregar.txtCarrera.getText());
            
            if(modelC.insertar(model)){
                JOptionPane.showMessageDialog(null,"¡Registro exitoso!");
                viewAgregar.dispose();
                limpiarAgregar();
                listar();
            }else{
                JOptionPane.showMessageDialog(null,"¡Error al guardar!");
                viewAgregar.dispose();
            }
        }
        
        if(e.getSource()==viewAgregar.btnCancelar){
            viewAgregar.dispose();
        }
        
        //Botones de la vista Actualizar_alumno
        if(e.getSource()==viewActualizar.btnGuardar){
            model.setMatricula(viewActualizar.txtMatricula.getText());
            model.setNombre(viewActualizar.txtNombre.getText());
            model.setPaterno(viewActualizar.txtPaterno.getText());
            model.setMaterno(viewActualizar.txtMaterno.getText());
            model.setTelefono(viewActualizar.txtTelefono.getText());
            model.setCorreo(viewActualizar.txtCorreo.getText());
            model.setCarrera(viewActualizar.txtCarrera.getText());
            
            if(modelC.modificar(model)){
                JOptionPane.showMessageDialog(null,"¡Registro exitoso!");
                viewActualizar.dispose();
                listar();
            }else{
                JOptionPane.showMessageDialog(null,"¡Error al guardar!");
                viewActualizar.dispose();
            }
        }
        
        if(e.getSource()==viewActualizar.btnCancelar){
            viewActualizar.dispose();
        }
        
        
        
 
    }
    
    //Listar lso registro de la tabla
    public void listar(){
        //System.out.println("Hola");
        DefaultTableModel modeloTabla = (DefaultTableModel) view.tblAlumnos.getModel();
        modeloTabla.setRowCount(0);
        //alumnos_Mc alum= new alumnos_Mc();
        modelC.getTable(modeloTabla);   
    }
    
    //Limpiar la tabla
    public void limpiarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        for (int i = 0; i < view.tblAlumnos.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    //Limpiar los campos de la vista Alumnos
    public void limpiar(){
        view.lblIdalumno.setText("");
        view.txtMatricula.setText(null);
        view.lblNombrecompleto.setText(null);
        view.lblCarrera.setText(null);
        view.lblCorreo.setText(null);
        view.lblTelefono.setText(null);
    }
    
    //Limpiar los campos de la vista Agregar_alumno
    public void limpiarAgregar(){
        viewAgregar.txtMatricula.setText(null);
        viewAgregar.txtNombre.setText(null);
        viewAgregar.txtPaterno.setText(null);
        viewAgregar.txtMaterno.setText(null);
        viewAgregar.txtTelefono.setText(null);
        viewAgregar.txtCorreo.setText(null);
        viewAgregar.txtCarrera.setText(null);
    }
    

    
}
