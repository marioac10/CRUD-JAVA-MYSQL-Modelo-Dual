/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.actividad_M;
import Modelo.actividad_Mc;
import Modelo.alumnos_M;
import Modelo.alumnos_Mc;
import Vista.Act_alumnos;
import Vista.Actividades;
import Vista.Actualizar_alumno;
import Vista.Agregar_alumno;
import Vista.Agregar_unidad;
import Vista.Alumnos;
import Vista.Correo;
import Vista.Home;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Mario Ac
 */
public class home_C implements ActionListener{
    private Home viewHome;
    
    public home_C(Home viewHome){
        this.viewHome=viewHome;
        this.viewHome.btnAlumnos.addActionListener(this);
        this.viewHome.btnAct.addActionListener(this);
        this.viewHome.btnMensaje.addActionListener(this);
    }
    
    public void iniciar(){
        viewHome.setTitle("Home");
        viewHome.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==viewHome.btnAlumnos){
            Alumnos alumno= new Alumnos();
            Agregar_alumno viewAgregar= new Agregar_alumno();
            Actualizar_alumno viewActualizar= new Actualizar_alumno();
            alumnos_M alumno_M= new alumnos_M();
            alumnos_Mc alumno_Mc= new alumnos_Mc();
            alumnos_C ctrl = new alumnos_C(alumno,viewAgregar,viewActualizar,alumno_M,alumno_Mc);
            ctrl.iniciar();
            alumno.setVisible(true);
        }
        if(e.getSource()==viewHome.btnAct){
            Actividades act= new Actividades();
            Agregar_unidad viewNueva= new Agregar_unidad();
            Act_alumnos viewAct= new Act_alumnos();
            actividad_M act_M= new actividad_M();
            actividad_Mc act_Mc= new actividad_Mc();
            
            actividad_C ctrl = new actividad_C(act,viewNueva,viewAct,act_M,act_Mc);
            ctrl.iniciar();
            act.setVisible(true);
        }
        
        if(e.getSource()==viewHome.btnMensaje){
            //Actividades act= new Actividades();
            //Agregar_unidad viewNueva= new Agregar_unidad();
            //Act_alumnos viewAct= new Act_alumnos();
            //actividad_M act_M= new actividad_M();
            //actividad_Mc act_Mc= new actividad_Mc();
            
            //actividad_C ctrl = new actividad_C(act,viewNueva,viewAct,act_M,act_Mc);
            //ctrl.iniciar();
            //act.setVisible(true);
            Correo viewCorreo = new Correo();
            viewCorreo.setVisible(true);
            viewCorreo.setLocationRelativeTo(null);
            
            
        }
    }
}
