/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class MainScreen extends JFrame implements ActionListener{
    public static final long serialVersionUID = 1L;
    JLabel portLabel;
    JTextField portText;
    JLabel serverNameLabel;
    JTextField serverNameText;

    static JTable clientTable;
    JButton openCloseButton;
    boolean isSocketOpened = false;
    
    public MainScreen(){
        JPanel mainContent = new JPanel(new GridBagLayout());
        GBCBuilder gbc = new GBCBuilder(1,1).setInsets(5);
        portLabel = new JLabel("Port: ");
        portText = new JTextField();
        serverNameLabel = new JLabel("Ten server: ");
        serverNameText = new JTextField();
        openCloseButton = new JButton("Mo server");
        openCloseButton.addActionListener(this);
        
        clientTable = new JTable(new Object[][] {}, new String[] { "Tên client", "Port client" });
        clientTable.setRowHeight(25);
        JScrollPane clientScrollPane = new JScrollPane(clientTable);
        clientScrollPane.setBorder(BorderFactory.createTitledBorder("Danh sach client dang ket noi"));
        mainContent.add(portLabel, gbc.setGrid(2, 1).setWeight(0, 0).setSpan(1, 1));
        mainContent.add(portText,gbc.setGrid(3, 1).setWeight(1, 0));
        mainContent.add(serverNameLabel,gbc.setGrid(1, 2).setWeight(0, 0).setSpan(1, 1));
        mainContent.add(serverNameText,gbc.setGrid(2, 2).setWeight(1, 0).setSpan(2, 1));
        mainContent.add(clientScrollPane, gbc.setGrid(1, 3).setFill(GridBagConstraints.BOTH).setWeight(1, 1).setSpan(4, 1));
        mainContent.add(openCloseButton, gbc.setGrid(1, 4).setWeight(1, 0).setSpan(4, 1));
        mainContent.setPreferredSize(new Dimension(250, 300));
        
        this.setTitle("Server chat");
        this.setContentPane(mainContent);
        this.getRootPane().setDefaultButton(openCloseButton);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        Main.socketController = new SocketController();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
