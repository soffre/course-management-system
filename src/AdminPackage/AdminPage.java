package AdminPackage;

import CoursePackage.ManageCourse;
import InstractorPackage.Interface;
import MainPackage.MainFrame;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JPanel implements ActionListener {

    private JPanel centerp,paneltop;
    private JLabel title;
    private JButton student,instractor,enrollStudentbtn,manageCourse,assignCourse,addDrop;
    private MainFrame mf;
    Interface in;
    assignCourseToInstructor assignC;
    public AdminPage(MainFrame mf){
        try{
            Context ctx = new InitialContext();
            Interface ii = (Interface) ctx.lookup("rmi://localhost:5555/InstructorService");
            this.in = ii;
        }catch ( NamingException e){
            e.printStackTrace();
        }
        this.mf = mf;
        assignC = new assignCourseToInstructor(in);
        paneltop = new JPanel(null);
        title = new JLabel("Admin Home Page");
        title.setBounds(300,20,350,50);
        title.setFont(new Font("Comic Sans MS",Font.ITALIC,40));
        title.setForeground(Color.white);
        paneltop.add(title);
        paneltop.setBounds(0,0,870,80);
        paneltop.setBackground(Color.darkGray);
   centerp =  new JPanel(null);
        TitledBorder titledBorder = new TitledBorder("Click The Button");
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.DARK_GRAY);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,15));

         centerp.setBounds(30,120,250,400);
         centerp.setBackground(Color.LIGHT_GRAY);

         student = new JButton("Add/Delete Student");
         student.setBackground(Color.DARK_GRAY);
         student.setForeground(Color.WHITE);
         student.setBounds(40,40,180,40);
         student.addActionListener(this);

        instractor = new JButton("Add/Delete Instractor");
        instractor.setBackground(Color.DARK_GRAY);
        instractor.setForeground(Color.WHITE);
        instractor.setBounds(40,90,180,40);
        instractor.addActionListener(this);

        manageCourse = new JButton("Manage Course");
        manageCourse.setBackground(Color.DARK_GRAY);
        manageCourse.setForeground(Color.WHITE);
        manageCourse.setBounds(40,140,180,40);
        manageCourse.addActionListener(this);

        enrollStudentbtn = new JButton("Enroll Student");
        enrollStudentbtn.setBackground(Color.DARK_GRAY);
        enrollStudentbtn.setForeground(Color.WHITE);
        enrollStudentbtn.setBounds(40,190,180,40);
        enrollStudentbtn.addActionListener(this);


        assignCourse = new JButton("Assign Course");
        assignCourse.setBackground(Color.DARK_GRAY);
        assignCourse.setForeground(Color.WHITE);
        assignCourse.setBounds(40,240,180,40);
        assignCourse.addActionListener(this);

        addDrop = new JButton("Add/Drop");
        addDrop.setBackground(Color.DARK_GRAY);
        addDrop.setForeground(Color.WHITE);
        addDrop.setBounds(40,290,180,40);
        addDrop.addActionListener(this);

        centerp.add(student);
        centerp.add(instractor);
        centerp.add(enrollStudentbtn);
        centerp.add(manageCourse);
        centerp.add(assignCourse);
        centerp.add(addDrop);
        centerp.setBorder(titledBorder);

        assignC.hide();
        add(assignC);
        add(paneltop);
        add(centerp);
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == student){
            showStudent ss= new showStudent(in);
            StudentPanel sp = new StudentPanel(ss,mf,in);
            mf.remove(this);
            mf.add(sp);
            mf.revalidate();
            mf.repaint();

        }
        if (e.getSource() == addDrop){
            addDropp ap = new addDropp(mf,in);
            mf.remove(this);
            mf.add(ap);
            mf.revalidate();
            mf.repaint();
        }
        if (e.getSource() == instractor){
            showInstructor si = new showInstructor(in);
           InstractorPanel ip = new InstractorPanel(si,mf,in);
            mf.remove(this);
            mf.add(ip);
            mf.revalidate();
            mf.repaint();
        }
        if (e.getSource() == enrollStudentbtn){
            enrollStudent es = new enrollStudent(in,mf);
            mf.remove(this);
            mf.add(es);
            mf.revalidate();
            mf.repaint();
        }
        if (e.getSource() == assignCourse){
            assignC.show(true);
        }
        if (e.getSource() == manageCourse){
            ManageCourse mc = new ManageCourse(mf,in);
            mf.remove(this);
            mf.add(mc);
            mf.revalidate();
            mf.repaint();
        }
    }
}
