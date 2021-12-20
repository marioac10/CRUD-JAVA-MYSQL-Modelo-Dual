/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.alumnos_M;
import Modelo.alumnos_Mc;
import Modelo.login_M;
import Modelo.login_Mc;
import Vista.Alumnos;
import Vista.Home;
import Vista.Login;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;


/**
 *
 * @author Mario Ac
 */
public class login_C implements ActionListener {
    private Login view;
    private login_M model;
    private login_Mc modelC;
    private String usuario;
    //public Alumnos_dual viewAlumnos;
    int a;
    Timer contador;
    //boolean status=false;
    
    
    public login_C(Login view,login_M model, login_Mc modelC){
        this.view = view;
        this.model = model;
        this.modelC = modelC;
        this.view.btnLogin.addActionListener(this);
    }
    
    public void iniciar(){
        view.setTitle("INICIAR SESIÓN");
        view.setLocationRelativeTo(null);
        //view.setSize(603, 467);
    }
    
    public class claseTimer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            a = view.BarraDeProgreso.getValue();
            if (a < 100) 
                {
                    a++;
                    view.BarraDeProgreso.setValue(a);
                } 
            else 
                {
                    contador.stop();
                    JOptionPane.showMessageDialog(null,"¡BIENVENIDO (A) "+usuario+"!");
                            Home home=new Home();
                            home_C homeC=new home_C(home);
                            homeC.iniciar();
                            home.setVisible(true);
                            view.setVisible(false);
                }
        }
    }
  
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btnLogin){
            model.setUsuario(view.txtuser.getText());
            model.setContraseña(view.txtpass.getText());
            
            login_M user=new login_M();
            user=modelC.iniciarsesion(model);
            
            if(user.getUsuario().equals(model.getUsuario()) && user.getContraseña().equals(model.getContraseña())){
                view.btnLogin.setEnabled(false);
                usuario=user.getUsuario();
                //contador = new Timer(30, new claseTimer());
                //contador.start();
                Thread t=new Thread(new Runnable(){
           
                    public void run(){
                        int a=0;
                        while(a<=100){
                            try{
                                view.BarraDeProgreso.setValue(a);
                                Thread.sleep(25);
                                a++;
                            }catch(InterruptedException e){
                            }  
                        }
                        if(a>100){
                            JOptionPane.showMessageDialog(null,"¡BIENVENIDO (A) "+usuario+"!");
                            Home home=new Home();
                            home_C homeC=new home_C(home);
                            homeC.iniciar();
                            home.setVisible(true);
                            /*try{
                                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                                SwingUtilities.updateComponentTreeUI(home);
                            }catch(Exception ex){
                            }*/
                            view.setVisible(false);
                        }
                    }  
                });
                t.start();
                
            }else{
                JOptionPane.showMessageDialog(null,"¡USUARIO O/Y PASSWORD INCORRECTO"); 
                limpiar();
            }
        }
    }
    
    public void limpiar(){
        view.txtuser.setText(null);
        view.txtpass.setText(null);
    }
}
