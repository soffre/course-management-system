package AdminPackage;

import DriverPackage.*;
import InstractorPackage.Interface;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteInstructor extends JPanel implements ActionListener {
    private JPanel depanel;
    private JTextField Id;
    private JLabel Enterid;
    private JButton delete;
    Interface ss;
    public deleteInstructor(Interface ss){
        this.ss = ss;
        depanel = new JPanel(null);
        TitledBorder tb = new TitledBorder("Delete Instructor Record:");
        tb.setTitleJustification(TitledBorder.CENTER);
        tb.setTitlePosition(TitledBorder.TOP);
        tb.setTitleColor(Color.DARK_GRAY);
        tb.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,20));

        Enterid = new JLabel("Enter Instructor Id:");
        Enterid.setBounds(15,100,150,30);
        Enterid.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        Id = new JTextField();
        Id.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Id.setBounds(165,100,150,30);

        delete = new JButton("Delete");
        delete.setBounds(110,190,100,30);
        delete.setFont(new Font("Comic Sans MS",Font.ITALIC,17));
        delete.setBackground(Color.DARK_GRAY);
        delete.setForeground(Color.white);
        delete.addActionListener(this);

        depanel.setBounds(130,100,330,300);
        depanel.add(Enterid);
        depanel.add(Id);
        depanel.add(delete);
        depanel.setBorder(tb);

        add(depanel);
        setBackground(Color.LIGHT_GRAY);
        setBounds(200,50,640,470);
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete){

            String id = Id.getText();
            if (!id.isEmpty()){
            int instid = Integer.parseInt(id);
            int a = JOptionPane.showConfirmDialog(null,"Are You Sure To Delete ?","Alert",JOptionPane.WARNING_MESSAGE);
                    if (a == JOptionPane.YES_OPTION) {
                        try {
                            String message = ss.deleteInstructor(instid);
                            JOptionPane.showMessageDialog(null, message);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Instructor Id is empty");
                }


        }
    }
}
