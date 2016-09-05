/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sangraj.lar.UI;

import java.sql.PreparedStatement;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author Song Grudge Ranjit
 */
public class RegistrationUI extends JFrame {
    
    private Pattern regexPattern;
    private Matcher regMatcher;
    JLabel lblUsername, lblFirstName, lblMiddleName, lblLastName, lblEmail, lblPassword, lblConPassword;
    JTextField txtUsername, txtFirstName, txtMiddleName, txtLastName, txtEmail, txtPassword, txtConPassword;
    JButton btnRegister, btnClear,btnBack;

    public RegistrationUI() {
        setTitle("Registration");
        setSize(500, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField(12);       
        lblFirstName = new JLabel("First Name:");
        txtFirstName = new JTextField(12);
        lblMiddleName = new JLabel("Middle Name(Optional):");
        txtMiddleName = new JTextField(12);
        lblLastName = new JLabel("Last Name:");
        txtLastName = new JTextField(20);
        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(30);
        lblPassword = new JLabel("Password:");
        txtPassword = new JTextField(12);
        lblConPassword = new JLabel("Confirm Password:");
        txtConPassword = new JTextField(12);
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(new RegisterButtonListener());
        btnBack=new JButton("Back");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginUI Lui =new LoginUI();
                setVisible(false);
            }
        });
        btnClear = new JButton("Clear");
        btnClear.addActionListener(new ClearButtonListener());
//             btnClear.addActionListener(new ActionListener() {
//
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 txtUsername.setText("");
//                 txtFirstName.setText("");
//                txtMiddleName.setText("");
//                txtLastName.setText("");
//                txtEmail.setText("");
//                txtPassword.setText("");
//                txtConPassword.setText("");
//             }
//         });

        add(lblUsername);
        add(txtUsername);
        add(lblFirstName);
        add(txtFirstName);
        add(lblMiddleName);
        add(txtMiddleName);
        add(lblLastName);
        add(txtLastName);
        add(lblEmail);
        add(txtEmail);
        add(lblPassword);
        add(txtPassword);
        add(lblConPassword);
        add(txtConPassword);
        add(btnBack);
        add(btnRegister);
        add(btnClear);

    }

    private class ClearButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtUsername.setText("");
            txtFirstName.setText("");
            txtMiddleName.setText("");
            txtLastName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtConPassword.setText("");
        }

    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {                 
                regexPattern=Pattern.compile("^[(a-zA-Z-0-9-\\_\\.)]+@[(gmail||yahoo||hotmail||outlook||live)]+\\.[(com?)]{2,3}$");
                regMatcher=regexPattern.matcher(txtEmail.getText());
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/registration", "root", null);
                String sql = "INSERT INTO tbl_registered(username,firstname,middlename,lastname,email,password) VALUES (?,?,?,?,?,?)";                             
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stm = conn.prepareStatement("SELECT * FROM tbl_registered where email=?");
                stm.setString(1, txtEmail.getText());
                ResultSet rs =stm.executeQuery();
                PreparedStatement st=conn.prepareStatement("SELECT * FROM tbl_registered where username=?");
                st.setString(1, txtUsername.getText());
                ResultSet r=st.executeQuery();
                stmt.setString(1, txtUsername.getText());
                stmt.setString(2, txtFirstName.getText());
                stmt.setString(3, txtMiddleName.getText());
                stmt.setString(4, txtLastName.getText());
                stmt.setString(5, txtEmail.getText());
                stmt.setString(6, txtPassword.getText());
                //stmt.setString(7, txtConPassword.getText());
                if (txtUsername.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Username");
                } else if (txtFirstName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter your First Name");
                } else if (txtLastName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter your Last Name");
                } else if (txtEmail.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter your Email Address");
                } else if (txtPassword.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password Required");
                } else if (txtConPassword.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password Confirmation Required");
                }else if(txtEmail.getText()!=null && r.next()){     
                    JOptionPane.showMessageDialog(null, "Username not available");
                    txtPassword.setText("");
                    txtConPassword.setText("");
                }else if(txtEmail.getText()!=null && !regMatcher.matches()){
                    JOptionPane.showMessageDialog(null, "Email Address is not correct");
                    txtEmail.setText("");
                    txtPassword.setText("");
                    txtConPassword.setText("");
                } else if(txtEmail.getText()!=null && rs.next()){     
                    JOptionPane.showMessageDialog(null, "Email Address already exists");
                    txtEmail.setText("");
                    txtPassword.setText("");
                   txtConPassword.setText("");
                }else if (txtPassword.getText() != null && !txtPassword.getText().equals(txtConPassword.getText())) {
                    JOptionPane.showMessageDialog(null, "Password Confirmation doesnot match");
                    txtPassword.setText("");
                    txtConPassword.setText("");
                } else if (stmt.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Registered Successfully");
                    txtUsername.setText("");
                    txtFirstName.setText("");
                    txtMiddleName.setText("");
                    txtLastName.setText("");
                    txtEmail.setText("");
                    txtPassword.setText("");
                    txtConPassword.setText("");
                }
            } catch (ClassNotFoundException | SQLException ce) {

            }
        }
    }
}
