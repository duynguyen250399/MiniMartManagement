/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.user.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.LoginForm;
import views.AdminDashboard;
import views.UserDashboard;

/**
 *
 * @author Admin
 */
public class LoginController {
    private LoginForm loginForm;
    private JFrame dashboard;
    

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;    
        setListeners();
    }
    
    private void setListeners(){
        this.loginForm.getBtnLogin().addActionListener(new BtnLoginListener());
    }
    
    private void login() throws ClassNotFoundException, SQLException{
        String username = this.loginForm.getTxtUsername().getText().trim();
        String password = this.loginForm.getTxtPassword().getText().trim();
        UserDAO dao = new UserDAO();
        String role = dao.authenticate(username, password);
      
        if(role.equals("admin")){
            this.dashboard = new AdminDashboard();
        }
        else if(role.equals("user")){
            this.dashboard = new UserDashboard();
        }
        else{
            JOptionPane.showMessageDialog(this.loginForm, "Invalid username or password!");
        }
    }
    
    
    private class BtnLoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                login();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
