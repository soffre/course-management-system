package AdminPackage;

import InstractorPackage.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.Vector;

public class showStudent extends JPanel implements ItemListener {
  private JTable studenttable;
  private JScrollPane sp;
  private JLabel studentDeptLabel,studentBatchLabel;
  private JComboBox studentDeptCom,studentBatchCom;
  Interface ss;
  private boolean flag1 = false;
  private boolean flag2 =false;

public showStudent(Interface ss){
    this.ss = ss;

    this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);
        this.setBounds(200, 50, 640, 400);
        this.setBackground(Color.LIGHT_GRAY);
        studenttable = new JTable();

    studentDeptLabel = new JLabel("Student Department:");
    studentDeptLabel.setBounds(0, 20, 150, 30);

    Vector v1 = new Vector();
    try {
        v1 = ss.getStudentDepartment();
    } catch (RemoteException re) {
        re.printStackTrace();
    }
    studentDeptCom = new JComboBox(v1);
    studentDeptCom.setBounds(150,20,150,30);
    studentDeptCom.addItemListener(this);

    studentBatchLabel = new JLabel("Student Batch:");
    studentBatchLabel.setBounds(320, 20, 150, 30);

    String[] batchs = {"1st Year","2nd Year","3rd Year","4th Year","5th Year"};
    studentBatchCom = new JComboBox(batchs);
    studentBatchCom.setBounds(470,20,150,30);
    studentBatchCom.addItemListener(this);
    sp = new JScrollPane(studenttable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(0,60,640,350);
        this.add(sp);
        this.add(studentDeptLabel);
        this.add(studentDeptCom);
        this.add(studentBatchCom);
        this.add(studentBatchLabel);
}


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == studentBatchCom ){
            flag1 = true;
        } if (e.getSource() == studentDeptCom){
            flag2 = true;
        }
        if(flag2 && flag1){
            String dept = studentDeptCom.getSelectedItem().toString();
            String batch = studentBatchCom.getSelectedItem().toString();
        try {
            DefaultTableModel model = ss.populateStudentByDepartement(dept,batch);
            studenttable.setModel(model);
        }catch (RemoteException re){re.printStackTrace();}
        }
    }
}
