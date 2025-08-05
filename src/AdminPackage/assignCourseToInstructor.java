package AdminPackage;

import InstractorPackage.Interface;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.Vector;

public class assignCourseToInstructor extends JPanel implements ListSelectionListener, ItemListener, ActionListener {

    Interface ss;
    private JTable courseTable;
    private JTextField courseIDtxt, instructorIdtxt;
    private JLabel searchCouDept, courseIDLabel,instructorIDLabel;
    private JComboBox searchCouDeptCom;
    private JButton assign;
    public assignCourseToInstructor(Interface ss){

        this.ss = ss;

        courseTable = new JTable();
        JScrollPane sp = new JScrollPane(courseTable);
        courseTable.getSelectionModel().addListSelectionListener(this);
        sp.setBounds(0, 60, 600, 250);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setBounds(10,330,100,30);

        courseIDtxt = new JTextField();
        courseIDtxt.setBounds(110,330,130,30);

        instructorIDLabel = new JLabel("Instructor ID:");
        instructorIDLabel.setBounds(270, 330,130,30);

        instructorIdtxt = new JTextField();
        instructorIdtxt.setBounds(380,330,130,30);

        assign = new JButton("Assign");
        assign.setBounds(220,400,100,30);
        assign.setBackground(Color.DARK_GRAY);
        assign.setForeground(Color.white);
        assign.addActionListener(this);


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

        add(searchCouDept);
        add(searchCouDeptCom);
        add(instructorIdtxt);
        add(instructorIDLabel);
        add(courseIDLabel);
        add(courseIDtxt);
        add(assign);
        add(sp);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(290,100,600,500);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int SelectedRow = courseTable.getSelectedRow();
            if (SelectedRow != -1) {
                Object cid = courseTable.getValueAt(SelectedRow, 0);
                courseIDtxt.setText(String.valueOf(cid));
            }
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
    public void actionPerformed(ActionEvent e) {
if (e.getSource() == assign){

    String cid = courseIDtxt.getText();
    String instid = instructorIdtxt.getText();
    int c_id =0;
    int i_id =0;

    if (!cid.isEmpty() && !instid.isEmpty()){
        c_id = Integer.parseInt(cid);
        i_id = Integer.parseInt(instid);
    }
    try{
        String message  = ss.assignCourses(c_id,i_id);
        JOptionPane.showMessageDialog(null , message);
    }catch (RemoteException rs){
        rs.printStackTrace();
    }

}
    }
}
