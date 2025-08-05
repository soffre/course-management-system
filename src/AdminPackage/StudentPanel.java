package AdminPackage;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import java.util.Calendar;
import java.util.Date;
import InstractorPackage.Interface;
import MainPackage.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.SimpleDateFormat;

public class StudentPanel extends JTabbedPane implements ActionListener, MouseListener {
    updatePanel up ;
    deletePanel dp ;
    private SpinnerDateModel dateModel;
    private int currentID = 1;
    private JPanel addp,continerpanal,updatep,excelPanel;
    private JRadioButton female,male;
    private ButtonGroup bg;
    private JTextField  fname,lname,email,address,nationality,studept;
    private JSpinner dob;
    private JLabel Fnamel,Lnamel,Dobl,gender,Addressl,Emaill,Nationalityl,imlabel,titleLabel,titelabel2,iml,studentDept,studentBatch,studentSems,studentYear;
    private JButton savebtn,Deletebtn,Updatebtn,backbtn,backbt,lgoutbtn,lgoutbt,showStu,browse,export;
    private showStudent ss;
    private JComboBox batch,semester,year;
    private MainFrame mf;
    private JTable excelTable;
    private DefaultTableModel tableModel;

    Interface in;
    public StudentPanel(showStudent ss, MainFrame mf, Interface in){
        this.ss = ss;
        this.mf = mf;
        this.in = in;
        up =  new updatePanel(in);
        dp = new deletePanel(in);

//////////////////////////////////////////////////////////////////////////////////////
    //a panel for adding student into the system.
    addp = new JPanel();
               titleLabel = new JLabel("Fill The Form Below:");
               titleLabel.setBounds(300,50,300,30);
               titleLabel.setForeground(Color.DARK_GRAY);
               titleLabel.setFont(new Font("Comic Sans MS",Font.BOLD,20));


               Fnamel = new JLabel("First Name:");
        Fnamel.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
               Lnamel = new JLabel("Last Name:");
        Lnamel.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
               Dobl = new JLabel("Data Of Birth:");
        Dobl.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
               gender = new JLabel("Gender:");
        gender.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
               Addressl = new JLabel("Address:");
        Addressl.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
               Emaill = new JLabel("Email:");
        Emaill.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
               Nationalityl = new JLabel("Nationality:");
        Nationalityl.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        studentDept = new JLabel("Department:");
        studentDept.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        studentBatch = new JLabel("Batch:");
        studentBatch.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        studentSems = new JLabel("Semester");
        studentSems.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        studentYear = new JLabel("Year:");
        studentYear.setFont(new Font("Comic Sans MS",Font.ITALIC,15));




        Fnamel.setBounds(110,110,110,35);
        Lnamel.setBounds(110,150,110,35);
        Dobl.setBounds(110,190,110,35);
        gender.setBounds(110,230,110,35);
        Addressl.setBounds(110,270,110,35);
        Emaill.setBounds(110,310,110,35);

        Nationalityl.setBounds(440,110,110,35);
        studentDept.setBounds(440,150,110,35);
        studentBatch.setBounds(440,190,110,35);
        studentSems.setBounds(440,230,110,35);
        studentYear.setBounds(440,270,110,35);

               fname = new JTextField();
               lname = new JTextField();
               address = new JTextField();
               email = new JTextField();
               nationality = new JTextField();
               studept = new JTextField();

        String[] batchs = {"1st Year","2nd Year","3rd Year","4th Year","5th Year"};
        batch = new JComboBox(batchs);

        String[] semister ={"First Semester","Second Semester"};
        semester = new JComboBox(semister);

        String[] years = {"2012","2013","2014","2015","2016"};
        year = new JComboBox(years);

        Calendar cal = Calendar.getInstance();
        cal.set(1888,1,1);
        Date min = cal.getTime();
        cal.set(2010,1,1);
        Date max = cal.getTime();
        cal.set(2001,1,1);
        Date current =cal.getTime();
        int steps = Calendar.DAY_OF_WEEK;

        dateModel = new SpinnerDateModel(current,min,max,steps);
        dob = new JSpinner(dateModel);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dob, "yyyy-MM-dd");
        dob.setEditor(dateEditor);
        female = new JRadioButton("Female");
        male = new JRadioButton("Male");
        male.setSelected(true);
        bg = new ButtonGroup();
        bg.add(female);
        bg.add(male);



        fname.setBounds(230,110,150,35);
        lname.setBounds(230,150,150,35);
        dob.setBounds(230,190,150,35);
        female.setBounds(230,230,80,35);
        male.setBounds(310,230,80,35);
        address.setBounds(230,270,150,35);
        email.setBounds(230,310,150,35);

        nationality.setBounds(550,110,150,35);
        studept.setBounds(550,150,150,35);
        batch.setBounds(550,190,150,35);
        semester.setBounds(550,230,150,35);
        year.setBounds(550,270,150,35);


        savebtn = new JButton("Save");
        savebtn.setBounds(400,380,100,30);
        savebtn.setBackground(Color.DARK_GRAY);
        savebtn.setForeground(Color.white);
        savebtn.addActionListener(this);
        savebtn.setFont(new Font("Comic Sans MS",Font.BOLD,15));


        imlabel = new JLabel(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\CourseManagementSystemUsing_Java_GUI\\src\\Recourse\\courseManagementSystemIcon.png"));
        imlabel.setBounds(380,25,400,500);

        excelPanel = new JPanel(null);
        //excelPanel.setBounds();

        addp.add(titleLabel);
        //addp.add(imlabel);
        addp.add(savebtn);
        addp.add(Fnamel);
        addp.add(Lnamel);
        addp.add(Dobl);
        addp.add(gender);
        addp.add(Addressl);
        addp.add(Emaill);
        addp.add(Nationalityl);
        addp.add(studentDept);
        addp.add(studentBatch);
        addp.add(studentSems);
        addp.add(studentYear);



        addp.add(studept);
        addp.add(batch);
        addp.add(semester);
        addp.add(year);
        addp.add(fname);
        addp.add(lname);
        addp.add(dob);
        addp.add(female);
        addp.add(male);
        addp.add(address);
        addp.add(email);
        addp.add(nationality);
        addp.setLayout(null);


        backbt = new JButton("Back");
        backbt.setBounds(0,10,70,30);
        backbt.setBackground(Color.DARK_GRAY);
        backbt.setForeground(Color.white);
        backbt.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        backbt.addActionListener(this);



        lgoutbt = new JButton("Logout");
        lgoutbt.setBounds(765,10,80,30);
        lgoutbt.setBackground(Color.DARK_GRAY);
        lgoutbt.setForeground(Color.white);
        lgoutbt.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        lgoutbt.addActionListener(this);

        addp.add(backbt);
        addp.add(lgoutbt);


////////////////////////////////////////////////////////////////////
        //update and delete student information panel.
    updatep = new JPanel();
        titelabel2 = new JLabel("Update, Show & Delete Student Information:");
        titelabel2.setBounds(150,5,550,30);
        titelabel2.setFont(new Font("Comic Sans MS",Font.ITALIC,25));
        titelabel2.setForeground(Color.DARK_GRAY);

        iml = new JLabel(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\CourseManagementSystemUsing_Java_GUI\\" +
                "src\\Recourse\\coursemanagement.jpg"));
        iml.setBounds(200,50,640,470);

        backbtn = new JButton("Back");
        backbtn.setBounds(0,10,70,30);
        backbtn.setBackground(Color.DARK_GRAY);
        backbtn.setForeground(Color.white);
        backbtn.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        backbtn.addActionListener(this);

        lgoutbtn = new JButton("Logout");
        lgoutbtn.setBounds(765,10,80,30);
        lgoutbtn.setBackground(Color.DARK_GRAY);
        lgoutbtn.setForeground(Color.white);
        lgoutbtn.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
        lgoutbtn.addActionListener(this);


///////////////////////////////////////////////////////////////////
        //cotianer panel for carry the update and delete student button.
    continerpanal = new JPanel(null);
        Updatebtn = new JButton("Update Student");
        Updatebtn.setBounds(15,100,130,30);
        Updatebtn.setBackground(Color.DARK_GRAY);
        Updatebtn.setForeground(Color.white);
        Updatebtn.setFont(new Font("",Font.ITALIC,13));
        Updatebtn.addActionListener(this);

        Deletebtn = new JButton("Delete Student");
        Deletebtn.setBounds(15,160,130,30);
        Deletebtn.setBackground(Color.DARK_GRAY);
        Deletebtn.setForeground(Color.white);
        Deletebtn.setFont(new Font("",Font.ITALIC,13));
        Deletebtn.addActionListener(this);

        showStu = new JButton("Show Student");
        showStu.setBounds(15,220,130,30);
        showStu.setBackground(Color.DARK_GRAY);
        showStu.setForeground(Color.white);
        showStu.setFont(new Font("",Font.ITALIC,13));
        showStu.addActionListener(this);

        TitledBorder tb = new TitledBorder("Click The Button");
        tb.setTitleJustification(TitledBorder.CENTER);
        tb.setTitlePosition(TitledBorder.TOP);
        tb.setTitleColor(Color.DARK_GRAY);
        tb.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,15));

        continerpanal.setBounds(40,70,150,320);
        continerpanal.setBackground(Color.LIGHT_GRAY);
        continerpanal.setBorder(tb);
        continerpanal.add(Updatebtn);
        continerpanal.add(Deletebtn);
        continerpanal.add(showStu);
        continerpanal.addMouseListener(this);




        up.hide();
        dp.hide();
        ss.hide();
        updatep.add(iml);
        updatep.add(ss);
        updatep.add(up);
        updatep.add(dp);
        updatep.add(titelabel2);
        updatep.add(backbtn);
        updatep.add(lgoutbtn);
        updatep.add(continerpanal);
        updatep.addMouseListener(this);
        updatep.setLayout(null);


        excelPanel = new JPanel(null);
            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return true; // Enable cell editing
                }
            };
            excelTable = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(excelTable);
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            sp.setBounds(0,50,850,480);

            browse = new JButton("Browse");
            browse.setBounds(100,18,100,30);
            browse.setBackground(Color.DARK_GRAY);
            browse.setForeground(Color.white);
            browse.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
            browse.addActionListener(this);

            export = new JButton("Save To DB");
            export.setBounds(400,18,150,30);
            export.setBackground(Color.DARK_GRAY);
            export.setForeground(Color.white);
            export.setFont(new Font("Comic Sans MS",Font.ITALIC,13));
            export.addActionListener(this);

            excelPanel.add(export);
            excelPanel.add(browse);
            excelPanel.add(sp);





///////////////////////////////////////////////////////////////////////
        //adding the panel and titleborder to the main tappendpane.

        TitledBorder titledBorder = new TitledBorder("Welcome To Manage Student Page!");
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.PINK);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));
        this.setBorder(titledBorder);
        this.add("Add Student",addp);
        this.add("Update/Delete/Show Student",updatep);
        //this.add("Load Excel File", excelPanel);



    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Updatebtn){
            ss.hide();
            dp.hide();
            iml.hide();
            up.show(true);
        }
        if (ae.getSource() == Deletebtn){
           ss.hide();
           up.hide();
           iml.hide();
           dp.show(true);
        }
        if (ae.getSource() == showStu){
            up.hide();
            dp.hide();
            iml.hide();
            ss.show(true);
        }
        if(ae.getSource() == savebtn){
            int studid =0;
            try {
               studid =in.generateID(3);
            }catch (RemoteException rs){rs.printStackTrace();}

           String stufname = fname.getText();
           String stulname = lname.getText();
           UsernameAndPasswordGenerator up = new UsernameAndPasswordGenerator();
           String stuuname = up.createUsername(stufname,stulname);
            String stupass = up.generatePassword(stufname);
           String stuaddress = address.getText();
           String stuemail = email.getText();
           Date studob =  dateModel.getDate();
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           String formattedDate = dateFormat.format(studob);

           String stunatio = nationality.getText();
           String stugender = "";
           if (male.isSelected()){
               stugender = male.getText();
           }
           if (female.isSelected()){
               stugender = female.getText();
           }
           String sdept = studept.getText();
           String sBatch = batch.getSelectedItem().toString();
           String sSems = semester.getSelectedItem().toString();
           int syear = Integer.parseInt(year.getSelectedItem().toString());
           if (isValidEmail(stuemail) && !sdept.isEmpty() && !stunatio.isEmpty()
                   && !stuaddress.isEmpty() && !stufname.isEmpty() && !stulname.isEmpty()){
               try{
                   String message = in.registerStudent(studid,stufname,stulname,formattedDate,stugender,
                                       stuemail,stuaddress,stunatio,stuuname,stupass,sdept,sBatch,sSems,syear);
                   JOptionPane.showMessageDialog(null,message);
               }catch (RemoteException e){
                   JOptionPane.showMessageDialog(null,e);
               }
           }else {
               JOptionPane.showMessageDialog(null,"Check All Input First!");
           }

        }
        if (ae.getSource() == lgoutbtn || ae.getSource() == lgoutbt){
            login lg = new login(mf);
            mf.remove(this);
            mf.add(lg);
            mf.revalidate();
            mf.repaint();
        }
        if (ae.getSource() == backbt || ae.getSource() == backbtn){
            AdminPage ap = new AdminPage(mf);
            mf.remove(this);
            mf.add(ap);
            mf.revalidate();
            mf.repaint();
        }
       /* if (ae.getSource() == browse){
            try {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    workbook = new XSSFWorkbook(fileChooser.getSelectedFile());
                    Sheet sheet = workbook.getSheetAt(0);

                    tableModel.setColumnCount(0);
                    tableModel.setRowCount(0);
                    for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                        Row row = sheet.getRow(i);
                        if (row != null) {
                            if (i == 0) {
                                for (int j = 0; j < row.getLastCellNum(); j++) {
                                    Cell cell = row.getCell(j);
                                    if (cell != null) {
                                        tableModel.addColumn(cell.getStringCellValue());
                                    }
                                }
                            } else {
                                Vector<Object> rowData = new Vector<>();
                                for (int j = 0; j < row.getLastCellNum(); j++) {
                                    Cell cell = row.getCell(j);
                                    if (cell != null) {
                                        switch (cell.getCellType()) {
                                            case STRING:
                                                rowData.add(cell.getStringCellValue());
                                                break;
                                            case NUMERIC:
                                                rowData.add(cell.getNumericCellValue());
                                                break;

                                            default:
                                                rowData.add(null);
                                        }
                                    } else {
                                        rowData.add(null);
                                    }
                                }
                                tableModel.addRow(rowData);
                            }}}}

                // Save JTable data to database (implement this part)
                // Use tableModel.getDataVector() to get data from JTable
                // Insert this data into your database
                // Example: For each row in tableModel.getDataVector(), form an INSERT statement to save it in the database

                JOptionPane.showMessageDialog(this, "Data loaded and saved to database.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }}
            */}

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!up.getBounds().contains(e.getPoint())
                || !dp.getBounds().contains(e.getPoint())
                || !ss.getBounds().contains(e.getPoint())
        )
        {
            up.hide();
            dp.hide();
            ss.hide();
            iml.show(true);
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
