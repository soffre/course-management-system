package InstractorPackage;

import DriverPackage.GlobalVariable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;


public class studentEnrollement extends JPanel implements ActionListener, Serializable {

   private JLabel courseID,instructorID,selectid,EnrollPeriod;
   private JTextField cID,sID,iID;
   private JButton save;
   private JPanel listpanel,formpanel,centerpanel;
   private JComboBox enrolestatus,enrolperiod;
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    public List<String> droppedStudents = new ArrayList<>();
    public List<String> alreadyEnrollStudents = new ArrayList<>();
    public studentEnrollement(){
        courseID = new JLabel("Course ID:");
        courseID.setBounds(20,150,100,40);

        EnrollPeriod = new JLabel("Enrollment Period:");
        EnrollPeriod.setBounds(20,200,100,40);

        cID = new JTextField();
        cID.setBounds(130,150,150,40);

        String lsit2[] = {"semester","academic year"};
        enrolperiod = new JComboBox(lsit2);
        enrolperiod.setBounds(130,200,150,40);

        save = new JButton("Save");
        save.setBounds(100,400,100,30);
        save.setBackground(Color.pink);
        save.addActionListener(this);

        selectid = new JLabel("Select Batch Of Student Here!");
        selectid.setBounds(305,60,230,30);
        selectid.setFont(new Font("Comic Sans MS",Font.ITALIC,14));
        listModel = new DefaultListModel<>();
        userList = new JList<String>(listModel);
        userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        userList.setCellRenderer(new FGradeRenderer(droppedStudents,alreadyEnrollStudents));

        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBounds(300,90,220,380);
        populateStudentToList();

        TitledBorder tb = new TitledBorder("Enroll Student to course here:");
        tb.setTitleJustification(TitledBorder.CENTER);
        tb.setTitlePosition(TitledBorder.TOP);
        tb.setTitleColor(Color.DARK_GRAY);
        tb.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,20));
        setLayout(null);
        add(scrollPane);
        setBounds(300,70,550,500);
        setBackground(Color.LIGHT_GRAY);
        setBorder(tb);
        add(courseID);
        add(cID);
        add(EnrollPeriod);
        add(selectid);
        add(enrolperiod);
        add(save);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save){
            droppedStudents.clear();
            alreadyEnrollStudents.clear();
            addSelectedItemsToAnotherTable();

}
}
    public void populateStudentToList() {
        try {
            GlobalVariable gv = new GlobalVariable();
            gv.conn = gv.c1.connect();
            String sql = "SELECT studID, First_name, Last_name FROM student";
            gv.Stm = gv.conn.prepareStatement(sql);
            ResultSet rs = gv.Stm.executeQuery();
            // Clear the list model before adding data
            listModel.clear();
            while (rs.next()) {
                int studID = rs.getInt("studID");
                String firstName = rs.getString("First_name");
                String lastName = rs.getString("Last_name");
                listModel.addElement("ID: " + studID + " - " + firstName + " " + lastName);
            }

            gv.Stm.close();
            gv.conn.close();
        } catch (Exception e) {
            e.printStackTrace(); // It's better to log the exception for debugging purposes
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void addSelectedItemsToAnotherTable() {
        String cd = cID.getText();

        if (!cd.isEmpty()) {

            try {

                int courseid = Integer.parseInt(cID.getText());
                String eper = enrolperiod.getSelectedItem().toString();
                GlobalVariable gv = new GlobalVariable();
                gv.conn = gv.c1.connect();
                String coursql = "Select course_capacity,pre_req_CID,course_status, course_name from course where CID = ? ";
                gv.Stm3 = gv.conn.prepareStatement(coursql);
                gv.Stm3.setInt(1, courseid);
                ResultSet courseRS = gv.Stm3.executeQuery();
                int courseCapacity = 0;
                int pre_req_cid = 0;
                String cstat = "";
                String cname = "";
                if (courseRS.next()) {
                    courseCapacity = courseRS.getInt("course_capacity");
                    pre_req_cid = courseRS.getInt("pre_req_CID");
                    cstat = courseRS.getString("course_status");
                    cname = courseRS.getString("course_name");

                        int selectedStudentCount = userList.getSelectedValuesList().size();
                        if (selectedStudentCount <= courseCapacity) {
                            if (cstat.equals("Active")) {
                                String insertSQL = "INSERT INTO studentenroll (SID,CID,enroll_status,enroll_period) VALUES (?,?,?,?)";
                                // check weather this student id is already enroll in this course
                                String checkenrollstu = "Select SID from studentenroll where SID = ? and CID = ? ";
                                for (String selectedValue : userList.getSelectedValuesList()) {
                                    gv.Stm = gv.conn.prepareStatement(insertSQL);
                                    gv.Stm4 = gv.conn.prepareStatement(checkenrollstu);
                                    int studID = Integer.parseInt(selectedValue.split("-")[0].trim().substring(4));
                                    gv.Stm4.setInt(1,studID);
                                    gv.Stm4.setInt(2,courseid);
                                    ResultSet enrollrs = gv.Stm4.executeQuery();
                                    if(!enrollrs.next()) {
                                        if (pre_req_cid > 0) {
                                            gv.Stm2 = gv.conn.prepareStatement("select grade from studentgrade where studID = ? and CID = ?");
                                            gv.Stm2.setInt(1, studID);
                                            gv.Stm2.setInt(2, pre_req_cid);
                                            ResultSet rs = gv.Stm2.executeQuery();
                                            String grade = "";
                                            if (rs.next()) {
                                                grade = rs.getString("grade");
                                            }
                                            if (grade.equals("F")) {
                                                droppedStudents.add(selectedValue);
                                                gv.Stm.setString(3, "Dropped");
                                            } else {
                                                gv.Stm.setString(3, "Enrolled");
                                            }
                                            gv.Stm2.close();
                                        } else {
                                            gv.Stm.setString(3, "Enrolled");
                                        }
                                    }else {
                                        alreadyEnrollStudents.add(selectedValue);
                                        continue;
                                    }
                                    gv.Stm.setInt(1, studID);
                                    gv.Stm.setInt(2, courseid);
                                    gv.Stm.setString(4, eper);
                                   int row =  gv.Stm.executeUpdate();
                                   if (row > 0){
                                       JOptionPane.showMessageDialog(null,"Successfully Enrolled!");
                                   }
                                    gv.Stm.close();
                                    gv.Stm4.close();
                                }
                            }else {
                                JOptionPane.showMessageDialog(null,"This Course('"+cname+"') Is '"+cstat+"' Right Now Not Active!","Alert",JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Course Capacity exceed! It Can Hold Only " + courseCapacity + " Students");
                            return;
                        }
            }else{
              JOptionPane.showMessageDialog(null,"Course Not Found! Course ID is Incorrect! Try Again","Alert",JOptionPane.WARNING_MESSAGE);
              return;
            }

                if (!droppedStudents.isEmpty()) {
                    StringBuilder message = new StringBuilder("Dropped Students (Red Color):\n");
                    for (String student : droppedStudents) {
                        message.append(student).append("\n");
                    }

                    JOptionPane.showMessageDialog(null, message.toString(), "Dropped Students", JOptionPane.WARNING_MESSAGE);
                }
                if (!alreadyEnrollStudents.isEmpty()){
                    StringBuilder message = new StringBuilder("Already Enroll Students (Blue Color):\n");
                    for (String student : alreadyEnrollStudents) {
                        message.append(student).append("\n");
                    }

                    JOptionPane.showMessageDialog(null, message.toString(), "Already Enroll Students", JOptionPane.WARNING_MESSAGE);

                }
                gv.conn.close();
                gv.Stm3.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }else {
            JOptionPane.showMessageDialog(null,"Fill the all text field first!","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }
}
class FGradeRenderer extends DefaultListCellRenderer implements Serializable{
    private List<String> droppedStudents;// A reference to the droppedStudents ArrayList
    private List<String> alreadyEnrollStudents;

    public FGradeRenderer(List<String> droppedStudents, List<String> alreadyEnrollStudents) {
        this.droppedStudents = droppedStudents;
        this.alreadyEnrollStudents = alreadyEnrollStudents;
    }
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String text = value.toString();
        if (droppedStudents.contains(text)) {
            c.setForeground(Color.RED);
        } else if (alreadyEnrollStudents.contains(text)) {
            c.setForeground(Color.blue);
        } else {
            c.setForeground(Color.BLACK);
        }
        return c;
    }

}








