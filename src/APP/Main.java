/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP;

import Controlador.login_C;
import Modelo.login_M;
import Modelo.login_Mc;
import Vista.Login;


/**
 *
 * @author Mario Ac
 */
public class Main {
    
    public static void main(String[] args){
        login_M login_m = new login_M();
        login_Mc login_mc= new login_Mc();
        Login login_view = new Login();
        
        login_C ctrl = new login_C(login_view, login_m, login_mc);
        ctrl.iniciar();
        login_view.setVisible(true);
        
        /*try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(login_view);
        }catch(Exception ex){
        }*/
    }
}
