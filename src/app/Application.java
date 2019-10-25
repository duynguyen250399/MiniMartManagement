/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controllers.LoginController;
import javax.swing.UnsupportedLookAndFeelException;
import views.LoginForm;
import views.AdminDashboard;

/**
 *
 * @author Admin
 */
public class Application {
    public static void main(String[] args) {
        
        // Change look and feel mode of UI
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Error while changing UI style");
        } 
        
        // Views Declaration
        LoginForm loginForm = new LoginForm();      
        
        // Controllers Declaration
        LoginController loginController = new LoginController(loginForm);
    }
}
