package AdminPackage;

import AdminPackage.AdminPage;
import DriverPackage.GlobalVariable;
import InstractorPackage.Interface;
import MainPackage.EmailSender;
import MainPackage.MainFrame;
import MainPackage.login;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class addDropp extends JPanel implements ActionListener, ListSelectionListener {
private JPanel paneltop,paneleft,panelleftDropp,panelrightad,centralpanel;
private JLabel title,searchid,stuidtoadd,stuidtodropp,couidtoadd,couidtodropp;
private JButton ViewDropp,ViewEnrolled,dropp,backbtn,lgoutbtn,searchbtn,add,ADDSTUDENT,DROPPSTUDENT;
private JTextField searchtxt,stuidtoaddtxt,stuidtodropptxt,couidtodroptxt,couidtoaddtxt;
private JTable studenttable;
private JScrollPane sp;
private   DefaultTableModel model;
private ArrayList<String> recipientList = new ArrayList<>();
private boolean flag = false;
private MainFrame mf;

Interface ss;
    public addDropp(MainFrame mf, Interface ss){
        this.ss = ss;
this.mf = mf;
        paneltop = new JPanel(null);
             title = new JLabel("Add Drop Page");
             title.setBounds(350,20,250,50);
             title.setFont(new Font("Comic Sans MS",Font.ITALIC,30));
             title.setForeground(Color.white);
        backbtn = new JButton("Back");
        backbtn.setBounds(0,10,70,30);
        backbtn.setBackground(Color.white);
        backbtn.setForeground(Color.black);
        backbtn.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        backbtn.addActionListener(this);

        lgoutbtn = new JButton("Logout");
        lgoutbtn.setBounds(765,10,80,30);
        lgoutbtn.setBackground(Color.white);
        lgoutbtn.setForeground(Color.black);
        lgoutbtn.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        lgoutbtn.addActionListener(this);

        paneltop.add(lgoutbtn);
        paneltop.add(backbtn);
        paneltop.add(title);
        paneltop.setBounds(0,0,870,80);
        paneltop.setBackground(Color.darkGray);

        ViewDropp = new JButton("View Dropped Student");
        ViewDropp.setBounds(650,90,200,30);
        ViewDropp.addActionListener(this);
        ViewDropp.setFont(new Font("Comic Sans MS",Font.BOLD,13));

        ViewEnrolled = new JButton("View Enrolled Student");
        ViewEnrolled.setBounds(440,90,200,30);
        ViewEnrolled.addActionListener(this);
        ViewEnrolled.setFont(new Font("Comic Sans MS",Font.BOLD,13));

        ADDSTUDENT = new JButton("Add Course");
        ADDSTUDENT.setBounds(20,90,150,30);
        ADDSTUDENT.addActionListener(this);
        ADDSTUDENT.setFont(new Font("Comic Sans MS",Font.BOLD,13));

        DROPPSTUDENT = new JButton("Dropp Course");
        DROPPSTUDENT.setBounds(200,90,150,30);
        DROPPSTUDENT.addActionListener(this);
        DROPPSTUDENT.setFont(new Font("Comic Sans MS",Font.BOLD,13));



        centralpanel = new JPanel(null);
        centralpanel.setBounds(380,170,5,300);
        centralpanel.setBackground(Color.blue);






   paneleft = new JPanel();
   paneleft.setLayout(null);
   paneleft.setBackground(Color.LIGHT_GRAY);
   paneleft.setBounds(400,130,450,450);
        searchid = new JLabel("CourseID");
        searchid.setBounds(60,10,70,30);

        searchtxt = new JTextField();
        searchtxt.setBounds(130,10,100,30);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(230,10,100,30);
        searchbtn.addActionListener(this);

        studenttable = new JTable(model);
        studenttable.getSelectionModel().addListSelectionListener(this);
        sp = new JScrollPane(studenttable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(0,50,450,400);
        paneleft.add(sp);
        paneleft.add(searchbtn);
        paneleft.add(searchid);
        paneleft.add(searchtxt);



        panelleftDropp   = new JPanel(null);
        JLabel titlerightdr = new JLabel("Dropp student in course");
        titlerightdr.setBounds(50,30,300,40);
        titlerightdr.setBackground(Color.DARK_GRAY);
        titlerightdr.setFont(new Font("Comic Sans MS",Font.ITALIC,18));
        stuidtodropp = new JLabel("Student ID:");
        stuidtodropp.setBounds(20,150,150,30);

        stuidtodropptxt = new JTextField();
        stuidtodropptxt.setBounds(180,150,150,30);

        couidtodropp = new JLabel("Course ID:");
        couidtodropp.setBounds(20,200,150,30);

        couidtodroptxt = new JTextField();
        couidtodroptxt.setBounds(180,200,150,30);

        dropp = new JButton("Dropp");
        dropp.setBounds(100,300,100,30);
        dropp.addActionListener(this);

        panelleftDropp.add(stuidtodropp);
        panelleftDropp.add(stuidtodropptxt);
        panelleftDropp.add(couidtodroptxt);
        panelleftDropp.add(couidtodropp);
        panelleftDropp.add(dropp);
        panelleftDropp.add(titlerightdr);
        panelleftDropp.setBounds(10,130,350,450);
        panelleftDropp.setBackground(Color.LIGHT_GRAY);
        panelleftDropp.hide();


        panelrightad = new JPanel(null);
        JLabel titlerightad = new JLabel("Add Dropped student To Course");
        titlerightad.setBounds(50,30,300,40);
        titlerightad.setBackground(Color.DARK_GRAY);
        titlerightad.setFont(new Font("Comic Sans MS",Font.ITALIC,18));
        stuidtoadd = new JLabel("Student ID:");
        stuidtoadd.setBounds(20,150,150,30);

        stuidtoaddtxt = new JTextField();
        stuidtoaddtxt.setBounds(180,150,150,30);

        couidtoadd = new JLabel("Course ID:");
        couidtoadd.setBounds(20,200,150,30);

        couidtoaddtxt = new JTextField();
        couidtoaddtxt.setBounds(180,200,150,30);

        add = new JButton("Add");
        add.setBounds(100,300,100,30);
        add.addActionListener(this);

        panelrightad.add(stuidtoadd);
        panelrightad.add(stuidtoaddtxt);
        panelrightad.add(couidtoaddtxt);
        panelrightad.add(couidtoadd);
        panelrightad.add(add);
        panelrightad.add(titlerightad);
        panelrightad.setBounds(10,130,350,450);
        panelrightad.setBackground(Color.LIGHT_GRAY);
        panelrightad.hide();


        add(panelrightad);
        add(panelleftDropp);
        add(ViewDropp);
        add(ADDSTUDENT);
        add(DROPPSTUDENT);
        add(ViewEnrolled);
        add(paneltop);
        add(centralpanel);
        paneleft.hide();
        add(paneleft);
        setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ViewEnrolled) {
            paneleft.show(true);
            DefaultTableModel nullmodel = new DefaultTableModel();
            studenttable.setModel(nullmodel);
            flag = true;

        }
        if (e.getSource() == ViewDropp) {
            paneleft.show(true);
            DefaultTableModel nullmodel = new DefaultTableModel();
            studenttable.setModel(nullmodel);
            flag = false;


        }if (e.getSource() == ADDSTUDENT){
            panelleftDropp.hide();
            panelrightad.show(true);
        }
        if (e.getSource()==DROPPSTUDENT){
            panelrightad.hide();
            panelleftDropp.show(true);
        }
        if (e.getSource() == searchbtn) {
            String courseid = searchtxt.getText();
            if (!courseid.isEmpty()) {
                int id = Integer.parseInt(courseid);
                if (flag) {
                    try {
                        DefaultTableModel model = ss.populateEnrolledStudentByCID(id);
                        studenttable.setModel(model);
                    }catch (RemoteException re){re.printStackTrace();}

                }else {
                    try {
                        DefaultTableModel model = ss.populateDroppedStudentByCID(id);
                        studenttable.setModel(model);
                    }catch (RemoteException re){re.printStackTrace();}

                }
            } else {
                JOptionPane.showMessageDialog(null, "Enter Course Id Please!");
            }
        }

if (e.getSource() == add){
 String course_id = couidtoaddtxt.getText();
 String student_id = stuidtoaddtxt.getText();
 if (!course_id.isEmpty() && !student_id.isEmpty()){
     int cid = Integer.parseInt(course_id);
     int sid = Integer.parseInt(student_id);
     try {
         String message = ss.addDroppedStudent(cid,sid);
         JOptionPane.showMessageDialog(null,message);
     }catch (RemoteException re){re.printStackTrace();}

 }else {
     JOptionPane.showMessageDialog(null,"Fill the two text field first!");
 }
}
if (e.getSource() == dropp){
    String course_id = couidtodroptxt.getText();
    String student_id = stuidtodropptxt.getText();
    if (!course_id.isEmpty() && !student_id.isEmpty()){
        int cid = Integer.parseInt(course_id);
        int sid = Integer.parseInt(student_id);
        try {
            String message = ss.DroppedStudent(cid,sid);
            JOptionPane.showMessageDialog(null,message);
        }catch (RemoteException re){re.printStackTrace();}

    }else {
        JOptionPane.showMessageDialog(null,"Fill the two text field first!");
    }
}

if (e.getSource() == lgoutbtn){
    login lg = new login(mf);
    mf.remove(this);
    mf.add(lg);
    mf.revalidate();
    mf.repaint();
}
if (e.getSource() == backbtn){
    AdminPage ap = new AdminPage(mf);
    mf.remove(this);
    mf.add(ap);
    mf.revalidate();
    mf.repaint();
}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int SelectedRow = studenttable.getSelectedRow();
            if (SelectedRow != -1) {
                Object selectedValue = studenttable.getValueAt(SelectedRow, 0);
                Object selectedValue2 = studenttable.getValueAt(SelectedRow, 4);
                int stuid = (int) selectedValue;
                int c_id = Integer.parseInt((String) selectedValue2);

                couidtodroptxt.setText(String.valueOf(c_id));
                couidtoaddtxt.setText(String.valueOf(c_id));

                stuidtoaddtxt.setText(String.valueOf(stuid));
                stuidtodropptxt.setText(String.valueOf(stuid));
}}}}
