/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sangraj.lar.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.sangraj.lar.UI.RegistrationUI;
import com.sangraj.lar.UI.AdminUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Song Grudge Ranjit
 */
public class LoginUI extends JFrame {

    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername, txtPassword;
    private JButton btnLogin, btnRegister;

    public LoginUI() {
        setTitle("Login Form");
        setSize(500, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        
        lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(15);
        lblPassword = new JLabel("Password:");
        txtPassword = new JTextField(20);
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
//LOGIN AS ADMINISTRATOR
            @Override
            public void actionPerformed(ActionEvent li) {
                String u = "admin";
                if (txtUsername.getText().isEmpty() && txtPassword.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Username and Password");
                } else if (!txtUsername.getText().equals(u) && !txtPassword.getText().equals(u)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                    txtUsername.setText("");
                    txtPassword.setText("");
                } else if ( !txtPassword.getText().equals(u)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                    txtUsername.setText("");
                    txtPassword.setText("");
                } else if (!txtUsername.getText().equals(u)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                    txtUsername.setText("");
                    txtPassword.setText("");
                } else if (txtUsername.getText().equals(u) && txtPassword.getText().equals(u)) {
                    AdminUI Aui = new AdminUI();
                    setVisible(false);
                }
            }
        });

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
//OPENS REGISTRATION FORM
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationUI Rui = new RegistrationUI();
                setVisible(false);

            }
        });

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnLogin);
        add(btnRegister);

    }
}
