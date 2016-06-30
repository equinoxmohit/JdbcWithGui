/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohit.jdbcwithgui;

/**
 *
 * @author Mohit
 */
import com.mohit.jdbcwithgui.connection.JdbcConnector;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Mohit
 */
public class MainProgram extends JFrame {

    JdbcConnector db = new JdbcConnector();

    JMenuBar menuBar;
    JMenu file, help;
    JMenuItem clear, about;
    JButton insertbtn, clearbtn;

    JLabel course_name, course_description, duration, duration_type, fees, status;
    JTextField txtCoName, txtDe, txtDu, txtDuT, txtFe, txtSt;

    public MainProgram() {
        initComponents();
    }

    public void initComponents() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        file = new JMenu("File");
        help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(help);
        clear = new JMenuItem("Clear");
        about = new JMenuItem("About...");
        file.add(clear);
        help.add(about);

        course_name = new JLabel("Course Name", SwingConstants.LEFT);
        course_description = new JLabel("Course Description", SwingConstants.LEFT);
        duration = new JLabel("Duration", SwingConstants.LEFT);
        duration_type = new JLabel("Duration Type", SwingConstants.LEFT);
        fees = new JLabel("Fees", SwingConstants.LEFT);
        status = new JLabel("Status", SwingConstants.LEFT);

        txtCoName = new JTextField();
        txtCoName.setColumns(10);
        txtDe = new JTextField();
        txtDe.setColumns(10);
        txtDu = new JTextField();
        txtDu.setColumns(10);
        txtDuT = new JTextField();
        txtDuT.setColumns(10);
        txtFe = new JTextField();
        txtFe.setColumns(10);
        txtSt = new JTextField();
        txtSt.setColumns(10);
        insertbtn = new JButton("INSERT");
        clearbtn = new JButton("CLEAR");

        Container container = getContentPane();
        container.add(course_name);
        container.add(txtCoName);
        container.add(course_description);
        container.add(txtDe);
        container.add(duration);
        container.add(txtDu);
        container.add(duration_type);
        container.add(txtDuT);
        container.add(fees);
        container.add(txtFe);
        container.add(status);
        container.add(txtSt);
        container.add(insertbtn);
        container.add(clearbtn);

        setTitle("Java Database Connection in Gui");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(1200, 120);
        setVisible(true);

        clear.addActionListener(new Clear());
        clearbtn.addActionListener(new Clear());
        insertbtn.addActionListener(new Insert());

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainProgram program = new MainProgram();
    }

    public class Clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtCoName.setText("");
            txtDe.setText("");
            txtDu.setText("");
            txtDuT.setText("");
            txtFe.setText("");
            txtSt.setText("");

        }

    }

    public class Insert implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                db.open();
                String sql = "INSERT INTO tbl_courses(course_name,course_description,duration,duration_type,fees,status)" + "VALUES(?,?,?,?,?,?)";
                PreparedStatement stmnt = db.initStatements(sql);
                stmnt.setString(1, txtCoName.getText());
                stmnt.setString(2, txtDe.getText());
                stmnt.setInt(3, Integer.parseInt(txtDu.getText()));
                stmnt.setString(4, txtDuT.getText());
                stmnt.setInt(5, Integer.parseInt(txtFe.getText()));
                stmnt.setBoolean(6, Boolean.parseBoolean(txtSt.getText()));
                int result = db.update();
                System.out.println(result);
                db.close();

            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println(ex.getLocalizedMessage());

            }
        }

    }

}
