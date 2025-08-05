package CoursePackage;

import DriverPackage.*;
import InstractorPackage.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public class showCourse extends JPanel {
    private JTable coursetable;
    private JScrollPane sp;
    Interface ss;
    public showCourse(Interface ss){
        this.ss = ss;
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        this. setBounds(210, 70, 650, 500);
        coursetable = new JTable();
        sp = new JScrollPane(coursetable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(sp, BorderLayout.CENTER); // Add the scroll pane to the center of the panel
    }

    public void populateTable() {
        try {
            DefaultTableModel model = ss.populateCourse();
            coursetable.setModel(model);
        }catch (RemoteException re){
            re.printStackTrace();
        }
    }
}

