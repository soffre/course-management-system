package CoursePackage;

import DriverPackage.GlobalVariable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import MainPackage.*;

public class AddCourseResource extends JPanel implements ActionListener {
    private JFileChooser fileChooser;
    private JLabel titele,Description,Reference,CourseId,choose;
    private JTextField cidtxt,tittxt,reftxt;
    private  JTextArea destxt;
    private JButton save,filebtn;
    private  int returnValue;
    private ArrayList<String> recipientList = new ArrayList<>();
    String cname ="";

    public AddCourseResource(){
        TitledBorder titledBorder = new TitledBorder("Add Course Resource Here:");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleColor(Color.DARK_GRAY);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));

        CourseId = new JLabel("Course ID:");
        CourseId.setBounds(50,100,100,30);
        CourseId.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        Reference = new JLabel("Enter References Link:");
        Reference.setBounds(50,150,200,30);
        Reference.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        choose = new JLabel("Choose File:");
        choose.setBounds(50,200,150,30);
        choose.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        titele = new JLabel("File Title:");
        titele.setBounds(110,250,150,30);
        titele.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        titele.hide();

        Description = new JLabel("File Description:");
        Description.setBounds(110,300,150,30);
        Description.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Description.hide();


        cidtxt = new JTextField();
        cidtxt.setBounds(250,100,250,30);
        cidtxt.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        reftxt = new JTextField();
        reftxt.setBounds(250,150,250,30);
        reftxt.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        fileChooser = new JFileChooser();
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Supported Files", "pdf", "txt", "ppt", "doc");
        fileChooser.setFileFilter(fileFilter);
        filebtn = new JButton("Click Here!:");
        filebtn.setBounds(250,200,250,30);
        filebtn.setBackground(Color.gray);
        filebtn.setForeground(Color.black);
        filebtn.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        filebtn.addActionListener(this);

        tittxt = new JTextField();
        tittxt.setBounds(250,250,250,30);
        tittxt.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        tittxt.hide();

        destxt = new JTextArea();
        destxt.setBounds(250,300,250,100);
        destxt.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        destxt.hide();

        save = new JButton("Save");
        save.setBounds(150,420,100,30);
        save.setBackground(Color.PINK);
        save.setFont(new Font("Comic Sans MS",Font.BOLD,15));
        save.addActionListener(this);


        add(filebtn);
        add(CourseId);
        add(Reference);
        add(choose);
        add(cidtxt);
        add(reftxt);
        add(titele);
        add(tittxt);
        add(Description);
        add(destxt);
        add(save);
        setBorder(titledBorder);
        setBackground(Color.LIGHT_GRAY);
        setBounds(300,70,550,500);
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filebtn) {
            returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                destxt.show(true);
                Description.show(true);
                titele.show(true);
                tittxt.show(true);
            }
        }
        if (e.getSource() == save) {
            try {
                int cid =0;
                String link = null;
                String fileTitle = null;
                String fileDes = null;
                String fileType = null;
                byte[] fileBytes = null;

                if (!tittxt.getText().isEmpty()) {
                    fileTitle = tittxt.getText();
                }
                if (!destxt.getText().isEmpty()) {
                    fileDes = destxt.getText();
                }

                String cidInput = cidtxt.getText();
                if (!cidInput.isEmpty()) {
                    try {
                        cid = Integer.parseInt(cidInput);
                    } catch (NumberFormatException en) {
                        JOptionPane.showMessageDialog(null, "Invalid Course ID. Please enter a valid number.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Course ID is required.");
                    return;
                }
                link = reftxt.getText();
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fileType = getFileType(selectedFile);
                    FileInputStream fileInputStream = new FileInputStream(selectedFile);
                    fileBytes = new byte[(int) selectedFile.length()];
                    fileInputStream.read(fileBytes);
                    fileInputStream.close();
                }

                GlobalVariable gv = new GlobalVariable();
                gv.conn = gv.c1.connect();
                String sql = "insert into courseresource(CID,file,file_title,file_desc,reference,fileType) values(?,?,?,?,?,?)";
                gv.Stm = gv.conn.prepareStatement(sql);
                gv.Stm.setInt(1, cid);
                gv.Stm.setBytes(2, fileBytes);
                gv.Stm.setString(3, fileTitle);
                gv.Stm.setString(4, fileDes);
                gv.Stm.setString(5, link);
                gv.Stm.setString(6, fileType);
                int rowaff = gv.Stm.executeUpdate();
                if (rowaff > 0) {
                    JOptionPane.showMessageDialog(null, "Resource Add Successfully!");
                    int a = JOptionPane.showConfirmDialog(null,"Are You Want To Send Notification To Student?");
                    if (a == JOptionPane.YES_OPTION){
                        fetchEmail();
                        String emailAddress = JOptionPane.showInputDialog("Enter Your Gmail Address!");
                        String appPassword = JOptionPane.showInputDialog("Enter your App or Gmail Password");
                        String text = "A new resource for "+cname+" is added! you can download it from the website page!";
                        EmailSender.sendEmail(recipientList,emailAddress,appPassword,text);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Resource Add Failed!");
                }
                gv.Stm.close();
                gv.conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    private static String getFileType(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        }
        return ""; // File type not found
    }
    public void fetchEmail(){
        try {
            int cid = Integer.parseInt(cidtxt.getText());
            GlobalVariable gv = new GlobalVariable();
            gv.conn = gv.c1.connect();
           String sql = "SELECT s.email, c.course_name FROM student s INNER JOIN studentenroll" +
                   " se ON s.studID = se.SID LEFT JOIN course c ON se.CID = c.CID WHERE se.CID = ? " +
                   "AND se.enroll_status = ?;";
           gv.Stm = gv.conn.prepareStatement(sql);
           gv.Stm.setInt(1,cid);
           gv.Stm.setString(2,"Enrolled");
            ResultSet rs = gv.Stm.executeQuery();
            while(rs.next()){
                recipientList.add(rs.getString("s.email"));
                cname = rs.getString("c.course_name");
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
