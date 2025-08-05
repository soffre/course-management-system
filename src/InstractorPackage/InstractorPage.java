package InstractorPackage;

import MainPackage.MainFrame;
import MainPackage.login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class InstractorPage extends JPanel implements ActionListener, MouseListener, ListSelectionListener {

   // InstructorServer ss = new InstructorServer();


    Interface ss;
    private JToolBar toolBar;
    private JFileChooser fileChooser;
    private JPanel Contianerpanel, breakerpanel,assigncoursepnel, enrolledStudentPanel,addResourcepanel;
    private JTextField searchtxt,cidtxt,tittxt,reftxt;
    private  JTextArea destxt;
    private JLabel imagelabel, searchid,assigncoursetext,textlabel,titele,Description,Reference,CourseId,choose;
    private JButton searchbtn,logout,save,filebtn,AddResource,ShowStudent, assigncourse;
    private MainFrame mf;
    private JTable coursetable,studenttable;
    private String username;
    private  int returnValue;
    private ArrayList<String> recipientList = new ArrayList<>();

    public InstractorPage(MainFrame mf, String username, Interface ss){
        this.ss = ss;
        this.username = username;
        this.mf = mf;


        logout = new JButton("Logout");
        logout.setBounds(15,30,80,30);
        logout.setBackground(Color.DARK_GRAY);
        logout.setForeground(Color.white);
        logout.addActionListener(this);


        searchbtn = new JButton("Search");
        searchbtn.setBounds(600, 50, 100, 30);
        searchbtn.setBackground(Color.DARK_GRAY);
        searchbtn.setForeground(Color.white);
        searchbtn.addActionListener(this);

        searchtxt = new JTextField();
        searchtxt.setBounds(450, 50, 150, 30);
        searchtxt.setFont(new Font("", Font.ITALIC, 15));
        searchtxt.requestFocus();

        searchid = new JLabel("Course ID");
        searchid.setBounds(350, 50, 100, 30);
        searchid.setFont(new Font("Comic Sans MS", Font.ITALIC, 17));


        imagelabel = new JLabel(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\" +
                "CourseManagementSystemUsing_Java_GUI\\src\\Recourse\\image 1.png"));
        imagelabel.setBounds(300,70,550,500);
        imagelabel.setBackground(Color.BLACK);

    Contianerpanel = new JPanel(null);
        Contianerpanel.setBounds(10,70,250,200);
        Contianerpanel.setBackground(Color.LIGHT_GRAY);
        Contianerpanel.addMouseListener(this);

        assigncourse = new JButton("Show Assign Course");
        assigncourse.setBounds(20,50,200,40);
        assigncourse.setBackground(Color.DARK_GRAY);
        assigncourse.setForeground(Color.white);
        assigncourse.addActionListener(this);

        ShowStudent = new JButton("Show Enrolled Student");
        ShowStudent.setBounds(20,100,200,40);
        ShowStudent.setBackground(Color.DARK_GRAY);
        ShowStudent.setForeground(Color.white);
        ShowStudent.addActionListener(this);


        AddResource = new JButton("Add Course Resource");
        AddResource.setBounds(20,150,200,40);
        AddResource.setBackground(Color.DARK_GRAY);
        AddResource.setForeground(Color.white);
        AddResource.addActionListener(this);




        TitledBorder tb = new TitledBorder("Click The Button");
        tb.setTitlePosition(TitledBorder.TOP);
        tb.setTitleJustification(TitledBorder.CENTER);
        tb.setTitleColor(Color.DARK_GRAY);
        tb.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,20));
        Contianerpanel.add(assigncourse);
        Contianerpanel.add(ShowStudent);
        Contianerpanel.add(AddResource);
        Contianerpanel.setBorder(tb);


    breakerpanel = new JPanel(null);
        breakerpanel.setBackground(Color.blue);
        breakerpanel.setBounds(60,370,750,5);
        breakerpanel.hide();


    assigncoursepnel = new JPanel(null);
    assigncoursepnel.setBounds(20,375,830,225);
    assigncoursepnel.setBackground(Color.LIGHT_GRAY);
        assigncoursetext = new JLabel("Courses That Assign To You");
        assigncoursetext.setBounds(0,0,750,25);
        assigncoursetext.setHorizontalAlignment(JLabel.CENTER);
        coursetable = new JTable();
        coursetable.getSelectionModel().addListSelectionListener( this);
        JScrollPane sp = new JScrollPane(coursetable);
        sp.setBounds(0,25,830,200);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assigncoursepnel.add(sp);
    assigncoursepnel.add(assigncoursetext);
    assigncoursepnel.hide();


    enrolledStudentPanel = new JPanel(null);
    enrolledStudentPanel.setBounds(300,80,550,285);
    enrolledStudentPanel.setBackground(Color.LIGHT_GRAY);
        textlabel = new JLabel("Enrolled Student to Specific Course");
        textlabel.setBounds(0,0,550,30);
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        studenttable = new JTable();
        JScrollPane sp2 = new JScrollPane(studenttable);
        sp2.setBounds(0,30,550,255);
        sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    enrolledStudentPanel.add(sp2);
    enrolledStudentPanel.add(textlabel);
    enrolledStudentPanel.hide();

    ////////////////////////////////////////
    //add course resource
    addResourcepanel = new JPanel(null);
    addResourcepanel.setBounds(300,70,550,500);
    addResourcepanel.setBackground(Color.LIGHT_GRAY);
        TitledBorder titledBr = new TitledBorder("Add Course Resource Here:");
        titledBr.setTitleJustification(TitledBorder.CENTER);
        titledBr.setTitlePosition(TitledBorder.TOP);
        titledBr.setTitleColor(Color.DARK_GRAY);
        titledBr.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));

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
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
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


        addResourcepanel.add(filebtn);
        addResourcepanel.add(CourseId);
        addResourcepanel.add(Reference);
        addResourcepanel.add(choose);
        addResourcepanel.add(cidtxt);
        addResourcepanel.add(reftxt);
        addResourcepanel.add(titele);
        addResourcepanel.add(tittxt);
        addResourcepanel.add(Description);
        addResourcepanel.add(destxt);
        addResourcepanel.add(save);
        addResourcepanel.setBorder(titledBr);
        addResourcepanel.hide();

    //////////////////////////////end of add resource panel

        add(logout);
        add(imagelabel);
        add(Contianerpanel);
        add(breakerpanel);
        add(assigncoursepnel);
        add(enrolledStudentPanel);
        add(addResourcepanel);
        setLayout(null);
        TitledBorder titledBorder = new TitledBorder("Welcome Instractor ");
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.DARK_GRAY);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));
        setBorder(titledBorder);
        addMouseListener(this);

        add(searchbtn);
        add(searchid);
        add(searchtxt);
        searchbtn.hide();
        searchtxt.hide();
        searchid.hide();


    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()){
                int SelectedRow  = coursetable.getSelectedRow();
                if (SelectedRow != -1){
                    Object selectedValue = coursetable.getValueAt(SelectedRow , 0);
                    int courseid = (int) selectedValue;
                    enrolledStudentPanel.show(true);
                    searchbtn.show(true);
                    searchtxt.show(true);
                    searchtxt.setText("");
                    searchtxt.setText(String.valueOf(courseid));
                    searchid.show(true);
                    try {
                        studenttable.setModel(ss.populateTable(courseid));
                    }catch (RemoteException re){re.printStackTrace();}

                }
            }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == filebtn) {
            returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                destxt.show(true);
                Description.show(true);
                titele.show(true);
                tittxt.show(true);
            }
        }
        if (ae.getSource() == save) {
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
              if (ss.addResourseToDB(cid,fileBytes,fileType,fileTitle,fileDes,link)){
                  JOptionPane.showMessageDialog(null,"Resource Add Successfully");
                  int a = JOptionPane.showConfirmDialog(null,"Are You Want To Send Notification To Student?");
                  if (a == JOptionPane.YES_OPTION){
                      String emailAddress = JOptionPane.showInputDialog("Enter Your Gmail Address!");
                      String appPassword = JOptionPane.showInputDialog("Enter your App or Gmail Password");
                      ss.sendEmailNotification(cid,emailAddress,appPassword);
                  }
              }else {
                  JOptionPane.showMessageDialog(null,"Resource Adding Failed!");
              }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        if (ae.getSource() == AddResource){
            imagelabel.hide();
            searchbtn.hide();
            searchtxt.hide();
            searchid.hide();
            breakerpanel.hide();
            assigncoursepnel.hide();
            enrolledStudentPanel.hide();
            addResourcepanel.show(true);

        } else if (ae.getSource() == searchbtn) {
            String cid = searchtxt.getText();
            if (!cid.isEmpty()){
               int courseid = Integer.parseInt(cid);
                enrolledStudentPanel.show(true);

                try {
                    studenttable.setModel(ss.populateTable(courseid));
                }catch (RemoteException re){re.printStackTrace();}

            }else {
                JOptionPane.showMessageDialog(null,"Course ID Field Is Empty First Fill IT");
            }
            searchtxt.setText("");

        } else if (ae.getSource() == ShowStudent){
            searchtxt.setText("");
            imagelabel.hide();
            addResourcepanel.hide();
            searchid.show(true);
            searchtxt.show(true);
            searchbtn.show(true);
        }

       else if (ae.getSource() == assigncourse) {
            imagelabel.hide();
            addResourcepanel.hide();
            breakerpanel.show(true);
            assigncoursepnel.show(true);
            try {
                coursetable.setModel(ss.populateAssignCoursesToInstructor(username));
            }catch (RemoteException re){re.printStackTrace();}

        }  else if (ae.getSource() == logout){
            login lg = new login(mf);
            mf.remove(this);
            mf.add(lg);
            mf.revalidate();
            mf.repaint();
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
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!addResourcepanel.getBounds().contains(e.getPoint())
                || !enrolledStudentPanel.getBounds().contains(e.getPoint())
                ||!assigncoursepnel.getBounds().contains(e.getPoint())
                ||!breakerpanel.getBounds().contains(e.getPoint())
        )
        {
            addResourcepanel.hide();
            searchbtn.hide();
            searchtxt.hide();
            searchid.hide();
            breakerpanel.hide();
            assigncoursepnel.hide();
            enrolledStudentPanel.hide();
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
