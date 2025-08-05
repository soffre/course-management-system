package AdminPackage;

import DriverPackage.GlobalVariable;
import InstractorPackage.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class updateInstructor extends JPanel implements  ActionListener {
    private JLabel searchid,Fnamel,Lnamel,gender,Dobl,Addressl,Emaill,Nationalityl,Qualifcation;

    private JTextField  fname,lname,email,address,nationality,searchtxt;
    private JSpinner dob;
    private JComboBox qulif;
    private JButton update,searchl;
    private Date current;
    private JRadioButton female,male;
    private ButtonGroup bg;
    private SpinnerDateModel dateModel;
    Interface ss;
    public updateInstructor(Interface ss){
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
        gender = new JLabel("Gender:");
        gender.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Dobl = new JLabel("Data Of Birth:");
        Dobl.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Addressl = new JLabel("Address:");
        Addressl.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Emaill = new JLabel("Email:");
        Emaill.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Nationalityl = new JLabel("Nationality:");
        Nationalityl.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        Qualifcation = new JLabel("Qualification");
        Qualifcation.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

        Fnamel.setBounds(50,160,100,35);
        Lnamel.setBounds(50,200,100,35);
        gender.setBounds(50,240,100,35);
        Dobl.setBounds(50,280,100,35);

        Addressl.setBounds(350,160,100,35);
        Emaill.setBounds(350,200,100,35);
        Nationalityl.setBounds(350,240,100,35);
        Qualifcation.setBounds(350,280,100,35);



        fname = new JTextField();
        lname = new JTextField();
        female = new JRadioButton("Female");
        male = new JRadioButton("Male");
        bg = new ButtonGroup();
        bg.add(female);
        bg.add(male);
        address = new JTextField();
        email = new JTextField();
        nationality = new JTextField();
        String qualification[] = {"BA","MA","Dr","PHD"};
        qulif = new JComboBox(qualification);


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



        fname.setBounds(160,160,150,35);
        fname.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        lname.setBounds(160,200,150,35);
        lname.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        female.setBounds(160,240,80,35);
        female.setBackground(Color.LIGHT_GRAY);
        male.setBounds(270,240,80,35);
        male.setBackground(Color.LIGHT_GRAY);
        dob.setBounds(160,280,150,35);
        dob.setFont(new Font("Comic Sans MS",Font.ITALIC,15));


        address.setBounds(460,160,150,35);
        address.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        email.setBounds(460,200,150,35);
        email.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        nationality.setBounds(460,240,150,35);
        nationality.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        qulif.setBounds(460,280,150,35);
        qulif.setFont(new Font("Comic Sans MS",Font.ITALIC,15));

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
        add(Qualifcation);

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
        add(qulif);
        add(update);
        setLayout(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchl) {
            String inputtext = searchtxt.getText();

            if (!inputtext.isEmpty()) {
                try {
                    int instid = Integer.parseInt(inputtext);
                    instructorPojo ob = ss.fetchInstructorList(instid);
                        fname.setText(ob.getlName());
                        lname.setText(ob.getlName());
                        if (ob.getGender().equals("Female")) {
                            female.setSelected(true);
                        }
                        if (ob.getGender().equals("Male")) {
                            male.setSelected(true);
                        }
                        address.setText(ob.getAddress());
                        email.setText(ob.getEmail());
                        nationality.setText(ob.getNation());
                        current = ob.getDob();
                        SpinnerDateModel dateModel = (SpinnerDateModel) dob.getModel();
                        dateModel.setValue(current);

                        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dob, "yyyy-MM-dd");
                        dob.setEditor(dateEditor);
                        String ql = ob.getQualif();
                        qulif.setSelectedItem(ql);
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
                    int instid = Integer.parseInt(inputtext);
                    String insfname = fname.getText();
                    String inslname = lname.getText();
                    String insaddress = address.getText();
                    String insemail = email.getText();
                    Date insdob =  dateModel.getDate();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(insdob);

                    String insnatio = nationality.getText();
                    String insqulif = qulif.getSelectedItem().toString();
                    String insgender = "";
                    if (male.isSelected()){
                        insgender = male.getText();
                    }
                    if (female.isSelected()){
                        insgender = female.getText();
                    }
                    String message = ss.updateInstructor(instid,insfname,inslname,formattedDate,insgender,insaddress,insemail,insnatio,insqulif);
                    JOptionPane.showMessageDialog(null,message);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        }
    }
}
