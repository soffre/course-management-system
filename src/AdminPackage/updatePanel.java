package AdminPackage;

import DriverPackage.GlobalVariable;
import InstractorPackage.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class updatePanel extends JPanel implements  ActionListener {
    private JLabel searchid,Fnamel,Lnamel,gender,studentDept,studentBatch,studentSems,studentYear,Dobl,Addressl,Emaill,Nationalityl;

    private JTextField  fname,lname,email,address,nationality,searchtxt,studept;
    private JSpinner dob;
    private JButton update,searchl;
    private Date current;
    private JRadioButton female,male;
    private ButtonGroup bg;
    private SpinnerDateModel dateModel;
    private JComboBox batch,semester,year;
    Interface ss;
    public updatePanel(Interface ss){
        this.ss = ss;
        searchl = new JButton("Search");
        searchl.setBounds(400,10,100,30);
        searchl.addActionListener(this);

        searchtxt = new JTextField();
        searchtxt.setBounds(250,10,150,30);
        searchtxt.setFont(new Font("",Font.ITALIC,15));
        searchtxt.requestFocus();

        searchid = new JLabel("ID");
        searchid.setBounds(220,10,30,30);
        searchid.setFont(new Font("Comic Sans MS",Font.ITALIC,17));


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




        Fnamel.setBounds(60,110,110,35);
        Lnamel.setBounds(60,150,110,35);
        Dobl.setBounds(60,190,110,35);
        gender.setBounds(60,230,110,35);
        Addressl.setBounds(60,270,110,35);
        Emaill.setBounds(60,310,110,35);

        Nationalityl.setBounds(370,110,110,35);
        studentDept.setBounds(370,150,110,35);
        studentBatch.setBounds(370,190,110,35);
        studentSems.setBounds(370,230,110,35);
        studentYear.setBounds(370,270,110,35);


        fname = new JTextField();
        lname = new JTextField();
        female = new JRadioButton("Female");
        female.setBackground(Color.LIGHT_GRAY);
        male = new JRadioButton("Male");
        male.setBackground(Color.LIGHT_GRAY);
        bg = new ButtonGroup();
        bg.add(female);
        bg.add(male);
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
        current =cal.getTime();
        int steps = Calendar.DAY_OF_WEEK;

         dateModel = new SpinnerDateModel(current,min,max,steps);
        dob = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dob, "yyyy-MM-dd");
        dob.setEditor(dateEditor);



        fname.setBounds(170,110,150,35);
        lname.setBounds(170,150,150,35);
        dob.setBounds(170,190,150,35);
        female.setBounds(170,230,80,35);
        male.setBounds(250,230,80,35);
        address.setBounds(170,270,150,35);
        email.setBounds(170,310,150,35);

        nationality.setBounds(480,110,150,35);
        studept.setBounds(480,150,150,35);
        batch.setBounds(480,190,150,35);
        semester.setBounds(480,230,150,35);
        year.setBounds(480,270,150,35);

        update =new JButton("Update");
        update.setBounds(300,370,100,30);
        update.setBackground(Color.DARK_GRAY);
        update.setForeground(Color.white);
        update.setFont(new Font("Comic Sans MS",Font.BOLD,17));
        update.addActionListener(this);

        setBackground(Color.LIGHT_GRAY);
        setBounds(200,50,640,470);
        add(fname);
        add(lname);
        add(female);
        add(male);
        add(dob);
        add(address);
        add(email);
        add(nationality);
        add(studentDept);
        add(studentBatch);
        add(studentSems);
        add(studentYear);



        add(studept);
        add(batch);
        add(semester);
        add(year);
        add(searchl);
        add(searchtxt);
        add(searchid);
        add(Fnamel);
        add(Lnamel);
        add(gender);
        add(Dobl);
        add(Addressl);
        add(Emaill);
        add(Nationalityl);
        add(update);
        setLayout(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchl) {
            String inputtext = searchtxt.getText();

            if (!inputtext.isEmpty()) {
                try {
                    int stuid = Integer.parseInt(inputtext);
                    studentPojo obj = ss.fetchStudentList(stuid);
                        fname.setText(obj.getFname());
                        lname.setText(obj.getLname());
                        if (obj.getBatch().equals("Female")) {
                            female.setSelected(true);
                        }
                        if (obj.getGender().equals("Male")) {
                            male.setSelected(true);
                        }
                        address.setText(obj.getAddress());
                        email.setText(obj.getEmail());
                        nationality.setText(obj.getNation());
                        current = obj.getDob();
                        SpinnerDateModel dateModel = (SpinnerDateModel) dob.getModel();
                        dateModel.setValue(current);

                        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dob, "yyyy-MM-dd");
                        dob.setEditor(dateEditor);
                        studept.setText(obj.getDept());
                        batch.setSelectedItem(obj.getBatch());
                        semester.setSelectedItem(obj.getSemester());
                        year.setSelectedItem(obj.getId());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Search field is null");
            }
        }

        if (e.getSource() == update) {
            String inputtext = searchtxt.getText();

            if (!inputtext.isEmpty()) {
                try {
                    int stuid = Integer.parseInt(inputtext);
                    String stufname = fname.getText();
                    String stulname = lname.getText();
                    String stuaddress = address.getText();
                    String stuemail = email.getText();
                    Date studob = dateModel.getDate();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(studob);

                    String stunatio = nationality.getText();
                    String stugender = "";
                    if (male.isSelected()) {
                        stugender = male.getText();
                    }
                    if (female.isSelected()) {
                        stugender = female.getText();
                    }
                    String dept = studept.getText();
                    String btch = batch.getSelectedItem().toString();
                    String sms = semester.getSelectedItem().toString();
                    int yr = Integer.parseInt(year.getSelectedItem().toString());
                    String message = ss.updateStudent(stuid,stufname,stulname,formattedDate,stugender,stuaddress,stuemail,stunatio,
                                                     dept,btch,sms,yr);

                    JOptionPane.showMessageDialog(null,message);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        }
        }
    }
