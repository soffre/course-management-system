package CoursePackage;

import DriverPackage.GlobalVariable;
import InstractorPackage.Interface;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public class deleteCourse extends JPanel implements ActionListener {

    private JPanel depanel;

    private JTextField Id;
    private JLabel Enterid;
    private JButton delete;
    Interface ss;
    public deleteCourse(Interface ss){
        this.ss =ss;
        TitledBorder tb = new TitledBorder("Delete Student Record:");
        tb.setTitleJustification(TitledBorder.CENTER);
        tb.setTitlePosition(TitledBorder.TOP);
        tb.setTitleColor(Color.DARK_GRAY);
        tb.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,20));

        Enterid = new JLabel("Enter Student Id:");
        Enterid.setBounds(20,100,150,30);
        Enterid.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        Id = new JTextField();
        Id.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Id.setBounds(160,100,150,30);

        delete = new JButton("Delete");
        delete.setBounds(110,190,100,30);
        delete.setFont(new Font("Comic Sans MS",Font.ITALIC,17));
        delete.setBackground(Color.pink);
        delete.addActionListener(this);

        depanel = new JPanel(null);
        depanel.setBounds(130,100,330,300);
        depanel.add(Enterid);
        depanel.add(Id);
        depanel.add(delete);
        depanel.setBorder(tb);

        add(depanel);
        setBackground(Color.LIGHT_GRAY);
        setBounds(210,70,650,500);
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            String couid = Id.getText();
            if (!couid.isEmpty()){
            int cid = Integer.parseInt(couid);
                int a = JOptionPane.showConfirmDialog(null, "Are You Sure To Delete ?", "Alert", JOptionPane.WARNING_MESSAGE);
                if (a == JOptionPane.YES_OPTION) {
                    try {
                        String message = ss.deleteCourse(cid);
                        JOptionPane.showMessageDialog(null,message);
                    }catch (RemoteException re){re.printStackTrace();}
                }
            }else{
                JOptionPane.showMessageDialog(null, "Input Field Empty");
    }}}}
