/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controllers.LoginController;
import views.LoginForm;
import views.AdminDashboard;

/**
 *
 * @author Admin
 */
public class Application {
    public static void main(String[] args) {
        // Views Declaration
        LoginForm loginForm = new LoginForm();      
        
        // Controllers Declaration
        LoginController loginController = new LoginController(loginForm);
    }
}
