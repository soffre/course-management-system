package CoursePackage;

import AdminPackage.AdminPage;
import InstractorPackage.InstractorPage;
import InstractorPackage.Interface;
import MainPackage.MainFrame;
import MainPackage.login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ManageCourse extends JPanel implements ActionListener, MouseListener {

    UpdateCourse UP ;
    CreateCourse CC ;
    deleteCourse dc;
    showCourse sc ;
    private JPanel contianerpanel;
    private JLabel imagelabel;
    private JButton Updatebtn,Deletebtn,showStu,Createcourse,backbtn,lgoutbtn;
    private MainFrame mf;
    Interface ss;

    public ManageCourse(MainFrame mf, Interface ss){
        this.ss = ss;
        this.mf = mf;

       UP = new UpdateCourse(ss);
        CC =new CreateCourse(ss);
       dc = new deleteCourse(ss);
       sc = new showCourse(ss);

    contianerpanel = new JPanel(null);

        Createcourse = new JButton("Create Course:");
        Createcourse.setBounds(20,100,130,30);
        Createcourse.setBackground(Color.DARK_GRAY);
        Createcourse.setForeground(Color.white);
        Createcourse.setFont(new Font("",Font.ITALIC,13));
        Createcourse.addActionListener(this);


        Updatebtn = new JButton("Update Course");
        Updatebtn.setBounds(20,140,130,30);
        Updatebtn.setBackground(Color.DARK_GRAY);
        Updatebtn.setForeground(Color.white);
        Updatebtn.setFont(new Font("",Font.ITALIC,13));
        Updatebtn.addActionListener(this);

        Deletebtn = new JButton("Delete Course");
        Deletebtn.setBounds(20,180,130,30);
        Deletebtn.setBackground(Color.DARK_GRAY);
        Deletebtn.setForeground(Color.white);
        Deletebtn.setFont(new Font("",Font.ITALIC,13));
        Deletebtn.addActionListener(this);

        showStu = new JButton("Show Course");
        showStu.setBounds(20,220,130,30);
        showStu.setBackground(Color.DARK_GRAY);
        showStu.setForeground(Color.white);
        showStu.setFont(new Font("",Font.ITALIC,13));
        showStu.addActionListener(this);

        TitledBorder tb = new TitledBorder("Click The Button");
        tb.setTitleJustification(TitledBorder.CENTER);
        tb.setTitlePosition(TitledBorder.TOP);
        tb.setTitleColor(Color.DARK_GRAY);
        tb.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,15));

        contianerpanel.setBounds(20,120,180,350);
        contianerpanel.setBackground(Color.LIGHT_GRAY);
        contianerpanel.setBorder(tb);
        contianerpanel.add(Createcourse);
        contianerpanel.add(Updatebtn);
        contianerpanel.add(Deletebtn);
        contianerpanel.add(showStu);
        contianerpanel.addMouseListener(this);

        backbtn = new JButton("Back");
        backbtn.setBounds(0,20,70,30);
        backbtn.setBackground(Color.DARK_GRAY);
        backbtn.setForeground(Color.white);
        backbtn.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        backbtn.addActionListener(this);

        lgoutbtn = new JButton("Logout");
        lgoutbtn.setBounds(780,20,80,30);
        lgoutbtn.setBackground(Color.DARK_GRAY);
        lgoutbtn.setForeground(Color.white);
        lgoutbtn.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        lgoutbtn.addActionListener(this);

        imagelabel = new JLabel(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\" +
                "CourseManagementSystemUsing_Java_GUI\\src\\Recourse\\image 1.png"));
        imagelabel.setBounds(210,70,650,500);
        imagelabel.setBackground(Color.BLACK);
        TitledBorder titledBorder = new TitledBorder("Managing Course Page");
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.DARK_GRAY);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));
        setBorder(titledBorder);
        setLayout(null);
        addMouseListener(this);
        add(contianerpanel);
        add(imagelabel);
        add(lgoutbtn);
        add(backbtn);
        UP.hide();
        CC.hide();
        dc.hide();
        sc.hide();
        add(sc);
        add(dc);
        add(CC);
        add(UP);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lgoutbtn) {
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

       else if (e.getSource() == Updatebtn){
           imagelabel.hide();
           sc.hide();
           CC.hide();
           dc.hide();
           UP.show(true);
        }
       else if (e.getSource() == Createcourse){
            imagelabel.hide();
            UP.hide();
            sc.hide();
            dc.hide();
            CC.show(true);
        }
       else if (e.getSource() == Deletebtn){
            imagelabel.hide();
            UP.hide();
            CC.hide();
            sc.hide();
            dc.show(true);
        }else if (e.getSource() == showStu){
            imagelabel.hide();
            UP.hide();
            CC.hide();
            dc.hide();
            sc.populateTable();
            sc.show(true);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!CC.getBounds().contains(e.getPoint())
                ||!UP.getBounds().contains(e.getPoint())
                || !dc.getBounds().contains(e.getPoint())
        )
        {
            CC.hide();
            UP.hide();
            sc.hide();
            dc.hide();
            imagelabel.show(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
