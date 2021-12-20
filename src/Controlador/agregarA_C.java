/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.alumnos_M;
import Modelo.alumnos_Mc;
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
public class agregarA_C implements ActionListener{
    private Agregar_alumno view;
    //private Alumnos viewAlum;
    private alumnos_C viewA;
    private alumnos_M model;
    private alumnos_Mc modelC;
    
    public agregarA_C(Agregar_alumno view,alumnos_M model,alumnos_Mc modelC,alumnos_C viewA){
        this.view=view;
        this.viewA=viewA;
        this.model=model;
        this.modelC=modelC;
        this.view.btnGuardar.addActionListener(this);
        this.view.btnCancelar.addActionListener(this);  
    }
    
    public void iniciar(){
        view.setTitle("Alumno");
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
            
            if(modelC.insertar(model)){
                JOptionPane.showMessageDialog(null,"¡Registro exitoso!");
                /*Alumnos alum=new Alumnos();
                alumnos_C ctr=new alumnos_C(alum,model,modelC);
                ctr.iniciar();*/
                
                view.dispose();
                //NewClass obj=new NewClass();
                viewA.listar();
                //viewA.iniciar();
                //viewA.setVisible(true);

            }else{
                JOptionPane.showMessageDialog(null,"¡Error al guardar!");
                view.dispose();
            }
        }
        
        if(e.getSource()==view.btnCancelar){
            view.dispose();
        }
    }
    
}
