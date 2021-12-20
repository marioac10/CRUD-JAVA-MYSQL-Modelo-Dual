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
public class actualizarA_C implements ActionListener{
    private Actualizar_alumno view;
    private alumnos_M model;
    private alumnos_Mc modelC;
    public Alumnos viewalum;
   
    
    public actualizarA_C(Actualizar_alumno view,alumnos_M model,alumnos_Mc modelC){
        this.view=view;
        this.model=model;
        this.modelC=modelC;
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);  
    }
    
    public void iniciar(){
        view.setTitle("Actualizar Alumno");
        view.setLocationRelativeTo(null); 
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btnGuardar){
            model.setMatricula(view.txtMatricula.getText());
            model.setNombre(view.txtNombre.getText());
            model.setPaterno(view.txtPaterno.getText());
            model.setMaterno(view.txtMaterno.getText());
            model.setTelefono(view.txtTelefono.getText());
            model.setCorreo(view.txtCorreo.getText());
            model.setCarrera(view.txtCarrera.getText());
            
            if(modelC.modificar(model)){
                JOptionPane.showMessageDialog(null,"¡Registro exitoso!");
                Alumnos alum=new Alumnos();
                view.dispose();
                //alumnos_C ctr=new alumnos_C(alum,model,modelC);
                //ctr.iniciar();
                alum.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"¡Error al guardar!");
                view.dispose();
            }
        }
        
        if(e.getSource()==view.btnCancelar){
            view.dispose();
        }
    }
    
    /*public void listar(){
        DefaultTableModel modeloTabla = (DefaultTableModel) viewalum.tblAlumnos.getModel();
        modeloTabla.setRowCount(0);
        modelC.getTable(modeloTabla);
        
    }*/
    
}
