/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sangraj.lar.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.sangraj.lar.UI.LoginUI;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Song Grudge Ranjit
 */
public class AdminUI extends JFrame {

    JButton btnUsers, btnLogout, btnSearch, btnRemoveUser;

    public AdminUI() {
        setTitle("Administrator");
        setSize(500, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);

    }

    private void initComponents() {
        btnRemoveUser = new JButton("Remove user");
        btnRemoveUser.setPreferredSize(new Dimension(200, 100));
        btnUsers = new JButton("Users");
        btnUsers.setPreferredSize(new Dimension(100, 100));
        btnUsers.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(new Dimension(100, 100));
        btnLogout.addActionListener(new ActionListener() {
// LOGSOUT OF ADMIN PAGE
            @Override
            public void actionPerformed(ActionEvent lo) {
                //SHOW OPTION IF THE USER REALLY WANTS TO LOGOUT OR NOT
                int ch=JOptionPane.showOptionDialog(null, "Do you really want to log out?", "Logging Out", JOptionPane.YES_NO_OPTION, WIDTH, null,null, "yes");
                if(ch==0){
                LoginUI Lui = new LoginUI();
                setVisible(false);
                } else{
                    
                }
            }
        });
        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(100, 100));
        add(btnUsers);
        add(btnSearch);
        add(btnRemoveUser);
        add(btnLogout);

    }

}
