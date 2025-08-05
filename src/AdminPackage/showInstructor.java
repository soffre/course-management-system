package AdminPackage;

import DriverPackage.*;
import InstractorPackage.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public class showInstructor extends JPanel implements ItemListener {
    private JTable instructortable;
    private JScrollPane sp;
    private JComboBox qulif;
    private JLabel quqlifLabel;
    Interface ss;
    public showInstructor(Interface ss){
        this.ss = ss;
        instructortable = new JTable();
        this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(200,50,640,470);
        this.setLayout(null);



        quqlifLabel = new JLabel("Select Qualification");
        quqlifLabel.setBounds(50,20,200,30);
        String qualification[] = {"BA","MA","Dr","PHD"};
        qulif = new JComboBox(qualification);
        qulif.addItemListener(this);
        qulif.setBounds(250,20,150,30);
        sp = new JScrollPane(instructortable);
        sp.setBounds(10, 60, 620, 390);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(sp);
        this.add(qulif);
        this.add(quqlifLabel);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == qulif){
            String qul = qulif.getSelectedItem().toString();

            try {
                DefaultTableModel model = ss.populateInstructorByQualification(qul);
                instructortable.setModel(model);
            }catch (RemoteException re){re.printStackTrace();}
        }
    }
}

