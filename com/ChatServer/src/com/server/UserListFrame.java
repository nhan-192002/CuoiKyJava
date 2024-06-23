package server;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.AdminDAO;

import java.awt.*;

public class UserListFrame extends JFrame {

    private JTable userTable;
    private AdminDAO adminDAO;

    public UserListFrame(java.util.List<String[]> users, AdminDAO adminDAO) {
        this.adminDAO = adminDAO;

        setTitle("Danh sách người dùng");
        setSize(400, 300);
        setLocationRelativeTo(null);

        Object[][] userData = new Object[users.size()][2];
        for (int i = 0; i < users.size(); i++) {
            userData[i][0] = users.get(i)[0];
            userData[i][1] = "Xóa";
        }

        userTable = new JTable(new DefaultTableModel(userData, new String[]{"Tên người dùng", "Thao tác"}) {
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        });

        userTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(userTable);


        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

}
