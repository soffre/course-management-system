package InstractorPackage;

import DriverPackage.GlobalVariable;
import MainPackage.MainFrame;
import MainPackage.login;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class addStudentGrade extends JPanel implements ActionListener {
    private JPanel paneltop,panelRight,panelLeft;
    private JLabel title,courseid;
    private JButton backbtn,lgoutbtn,searchbtn;
    private JTextField cidtxt;
    private MainFrame mf;
    private JTable addGradetable;
    private   DefaultTableModel model;
    private String characters[] = {"A","D","F"};
    private JComboBox  characterComboBox;


    public addStudentGrade(MainFrame mf){
        this.mf = mf;
        paneltop = new JPanel(null);
        title = new JLabel("Add Student Grades");
        title.setBounds(300,20,300,50);
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
    panelRight = new JPanel(null);
        courseid = new JLabel("Course ID:");
        courseid.setBounds(50,50,100,30);

        cidtxt = new JTextField();
        cidtxt.setBounds(20,85,150,30);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(40,150,100,30);
        searchbtn.addActionListener(this);

        panelRight.add(searchbtn);
        panelRight.add(cidtxt);
        panelRight.add(courseid);
        panelRight.setBounds(0,80,200,540);
        panelRight.setBackground(Color.LIGHT_GRAY);

    panelLeft = new JPanel(null);
         addGradetable = new JTable(0,7);
       //  JScrollPane sp = new JScrollPane(addGradetable);
         //addGradetable.setBounds(5,5,645,520);

         panelLeft.add(addGradetable);
         panelLeft.setBounds(210,80,660,535);
         panelLeft.setBackground(Color.LIGHT_GRAY);

        add(panelLeft);
        add(panelRight);
        add(paneltop);
        setLayout(null);

    }

    public void populateEnrolledStudent(int cid) {
        try {
            String columns[] = {"Student ID", "First Name", "Last Name", "pre_req_CID", "Course ID", "Course name", "Grade"};
            model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);
            addGradetable = new JTable(0,7);
            JComboBox<String> gradeComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});
            addGradetable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(gradeComboBox));


            GlobalVariable gv = new GlobalVariable();
            gv.conn = gv.c1.connect();

            String sql = "SELECT s.studID, s.First_name, s.Last_name, c.CID, c.course_name, c.pre_req_CID " +
                    "FROM student s INNER JOIN studentenroll se ON s.studID = se.SID LEFT JOIN course c " +
                    "ON se.CID = c.CID WHERE se.enroll_status = ? and se.CID = ?;";

            gv.Stm = gv.conn.prepareStatement(sql);
            gv.Stm.setString(1, "Enrolled");
            gv.Stm.setInt(2, cid);

            ResultSet rs = gv.Stm.executeQuery();

            while (rs.next()) {
                // Add a combobox item to the "Grade" column
                model.addRow(new Object[]{
                        rs.getInt("s.studID"),
                        rs.getString("s.First_name"),
                        rs.getString("s.Last_name"),
                        rs.getString("c.pre_req_CID"),
                        rs.getString("c.CID"),
                        rs.getString("c.course_name"),
                        "A" // Leave "Grade" column empty initially
                });
            }

            addGradetable.setModel(model);

            gv.Stm.close();
            gv.conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lgoutbtn){
            login lg = new login(mf);
            mf.remove(this);
            mf.add(lg);
            mf.revalidate();
            mf.repaint();
        }
        if (e.getSource() == searchbtn){
            String cid = cidtxt.getText();
            if (!cid.isEmpty()){
                int coid = Integer.parseInt(cid);
                populateEnrolledStudent(coid);
            }else {
                JOptionPane.showMessageDialog(null,"Enter the course ID first!");
            }
        }
    }
}

class ComboBoxCellRenderer extends DefaultTableCellRenderer {
    JComboBox<String> comboBox;

    public ComboBoxCellRenderer(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        comboBox.setSelectedItem(value);
        return comboBox;
    }
}