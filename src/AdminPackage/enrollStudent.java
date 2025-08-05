package AdminPackage;

import InstractorPackage.Interface;
import MainPackage.MainFrame;
import MainPackage.login;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Vector;

public class enrollStudent extends JPanel implements ActionListener, ItemListener, ListSelectionListener {
    private JPanel paneltop, centralpanel, paneleft, panelright;
    private JLabel title, courseidLabel, YearLabel, studentDeptLabel, studentBatchLabel, studentSemsLabel, enrollStudentTiltle,
            searchCouDept;
    private JButton backbtn, lgoutbtn, enroll, course;
    private JTextField courseIdtxt;
    private JComboBox studentDeptCom, studentBatchCom, studentSemsCom, searchCouDeptCom,YearCom;
    private JTable courseTable;
    Interface ss;
    MainFrame mf;

    public enrollStudent(Interface ss, MainFrame mf) {
       /* try {
            Context ctx = new InitialContext();
            InetAddress ip = InetAddress.getLocalHost();
            String ipaddress = ip.getHostAddress();
            Interface ii = (Interface) ctx.lookup("rmi://" + ipaddress + ":5555/InstructorService");
            this.ss = ii;
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }*/

        this.ss = ss;
        this.mf = mf;
        paneltop = new JPanel(null);
        title = new JLabel("Student Enrollment Page");
        title.setBounds(300, 20, 350, 50);
        title.setFont(new Font("Comic Sans MS", Font.ITALIC, 30));
        title.setForeground(Color.white);

        backbtn = new JButton("Back");
        backbtn.setBounds(0, 10, 70, 30);
        backbtn.setBackground(Color.white);
        backbtn.setForeground(Color.black);
        backbtn.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
        backbtn.addActionListener(this);

        lgoutbtn = new JButton("Logout");
        lgoutbtn.setBounds(765, 10, 80, 30);
        lgoutbtn.setBackground(Color.white);
        lgoutbtn.setForeground(Color.black);
        lgoutbtn.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
        lgoutbtn.addActionListener(this);

        paneltop.add(lgoutbtn);
        paneltop.add(backbtn);
        paneltop.add(title);
        paneltop.setBounds(0, 0, 870, 80);
        paneltop.setBackground(Color.darkGray);

        centralpanel = new JPanel(null);
        centralpanel.setBounds(380, 170, 5, 350);
        centralpanel.setBackground(Color.blue);

        paneleft = new JPanel();
        paneleft.setLayout(null);
        paneleft.setBackground(Color.LIGHT_GRAY);
        paneleft.setBounds(10, 130, 350, 450);

        enrollStudentTiltle = new JLabel("Enroll Student Here:");
        enrollStudentTiltle.setBounds(100, 20, 200, 40);
        enrollStudentTiltle.setFont(new Font("Comic Sans MS", Font.BOLD, 17));

        courseidLabel = new JLabel("Course ID:");
        courseidLabel.setBounds(30, 100, 150, 30);

        courseIdtxt = new JTextField();
        courseIdtxt.setBounds(185, 100, 150, 30);

        studentDeptLabel = new JLabel("Student Department:");
        studentDeptLabel.setBounds(30, 140, 150, 30);

        Vector v1 = new Vector();
        try {
            v1 = ss.getStudentDepartment();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        studentDeptCom = new JComboBox(v1);
        studentDeptCom.setBounds(185, 140, 150, 30);

        studentBatchLabel = new JLabel("Student Batch:");
        studentBatchLabel.setBounds(30, 180, 150, 30);

        String[] batchs = {"1st Year","2nd Year","3rd Year","4th Year","5th Year"};
        studentBatchCom = new JComboBox(batchs);
        studentBatchCom.setBounds(185, 180, 150, 30);

        studentSemsLabel = new JLabel("Student Semester:");
        studentSemsLabel.setBounds   (30, 220, 150, 30);

        String[] semister ={"First Semester","Second Semester"};
        studentSemsCom = new JComboBox(semister);
        studentSemsCom.setBounds(185, 220, 150, 30);

        YearLabel = new JLabel("Year:");
        YearLabel.setBounds(30, 260, 150, 30);

        String[] years = {"2012","2013","2014","2015","2016"};
        YearCom = new JComboBox(years);
        YearCom.setBounds(185, 260, 150, 30);

        enroll = new JButton("Enroll");
        enroll.setBounds(120, 350, 100, 30);
        enroll.setBackground(Color.DARK_GRAY);
        enroll.setForeground(Color.white);
        enroll.addActionListener(this);


        paneleft.add(enrollStudentTiltle);
        paneleft.add(courseidLabel);
        paneleft.add(YearCom);
        paneleft.add(studentBatchLabel);
        paneleft.add(studentDeptLabel);
        paneleft.add(studentSemsLabel);
        paneleft.add(YearLabel);
        paneleft.add(courseIdtxt);
        paneleft.add(studentSemsCom);
        paneleft.add(studentDeptCom);
        paneleft.add(studentBatchCom);
        paneleft.add(enroll);




        course = new JButton("Course");
        course.setBounds(610, 100, 100, 30);
        course.setBackground(Color.DARK_GRAY);
        course.setForeground(Color.white);


        panelright = new JPanel();
        panelright.setLayout(null);
        panelright.setBackground(Color.LIGHT_GRAY);
        panelright.setBounds(400, 130, 450, 450);
        courseTable = new JTable();
        JScrollPane sp = new JScrollPane(courseTable);
        courseTable.getSelectionModel().addListSelectionListener(this);
        sp.setBounds(0, 60, 450, 390);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        searchCouDept = new JLabel("Select Course Department:");
        searchCouDept.setBounds(30, 20, 200, 30);


        Vector v = new Vector();
        try {
            v = ss.getCourseDepartment();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        searchCouDeptCom = new JComboBox(v);
        searchCouDeptCom.setBounds(230, 20, 200, 30);
        searchCouDeptCom.addItemListener(this);


        panelright.add(searchCouDept);
        panelright.add(searchCouDeptCom);
        panelright.add(sp);


        //add(course);
        // add(student);
        add(paneleft);
        add(paneltop);
        add(panelright);
        add(centralpanel);
        setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getSource() == enroll){
    String sdept = studentDeptCom.getSelectedItem().toString();
    String sbatch = studentBatchCom.getSelectedItem().toString();
    String sSems = studentSemsCom.getSelectedItem().toString();
    int year =Integer.parseInt(YearCom.getSelectedItem().toString());
    String c_id = courseIdtxt.getText();
    int id =0;
    if (!c_id.isEmpty()) {
       id = Integer.parseInt(c_id);
    }else {
        JOptionPane.showMessageDialog(null,"Course Id Is Null!");
    }
    try {
        String message = ss.enrollStudentToCourse(id,sdept,sbatch,sSems,year);
        JOptionPane.showMessageDialog(null,message);
    }catch (RemoteException re){re.printStackTrace();}
}
if (e.getSource() == backbtn){
    AdminPage ap = new AdminPage(mf);
    mf.remove(this);
    mf.add(ap);
    mf.revalidate();
    mf.repaint();
}
if (e.getSource() == lgoutbtn){
    login lg = new login(mf);
    mf.remove(this);
    mf.add(lg);
    mf.revalidate();
    mf.repaint();
}

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == searchCouDeptCom) {
            try {
                DefaultTableModel model = ss.courseByDepartement(searchCouDeptCom.getSelectedItem().toString());
                courseTable.setModel(model);
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int SelectedRow = courseTable.getSelectedRow();
            if (SelectedRow != -1) {
                Object cid = courseTable.getValueAt(SelectedRow, 0);
                courseIdtxt.setText(String.valueOf(cid));
            }
        }
    }
}
