package AdminPackage;


import javax.swing.*;
import javax.swing.border.TitledBorder;
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

public class InstractorPanel extends JTabbedPane implements ActionListener, MouseListener {
    updateInstructor ui ;
    deleteInstructor di;
    private SpinnerDateModel dateModel;
    private int currentID = 1;
    private JPanel addp,continerpanal,updatep;
    private JRadioButton female,male;
    private ButtonGroup bg;
    private JComboBox qulif;
    private JTextField  fname,lname,email,address,nationality;
    private JSpinner dob;
    private JLabel Fnamel,Lnamel,Dobl,gender,Addressl,Emaill,Nationalityl,Qualification,imlabel,titleLabel,titelabel2,iml;
    private JButton savebtn,Deletebtn,Updatebtn,backbtn,backbt,lgoutbtn,lgoutbt,showinst;
    private showInstructor si;
    private MainFrame mf;
    Interface in;
    public InstractorPanel(showInstructor si, MainFrame mf, Interface in){
        ui = new updateInstructor(in);
        di = new deleteInstructor(in);
        this.in = in;
        this.si = si;
        this.mf = mf;

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
        Qualification = new JLabel("Qualification:");
        Qualification.setFont(new Font("Comic Sans MS",Font.ITALIC,15));




        Fnamel.setBounds(110,110,110,35);
        Lnamel.setBounds(110,150,110,35);
        Dobl.setBounds(110,190,110,35);
        gender.setBounds(110,230,110,35);
        Addressl.setBounds(110,270,110,35);
        Emaill.setBounds(110,310,110,35);

        Nationalityl.setBounds(440,110,110,35);
        Qualification.setBounds(440,150,110,35);


        fname = new JTextField();
        lname = new JTextField();
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
        qulif.setBounds(550,150,150,35);

        savebtn = new JButton("Save");
        savebtn.setBounds(400,380,100,30);
        savebtn.setBackground(Color.DARK_GRAY);
        savebtn.setForeground(Color.white);
        savebtn.addActionListener(this);
        savebtn.setFont(new Font("Comic Sans MS",Font.BOLD,15));


        imlabel = new JLabel(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\CourseManagementSystemUsing_Java_GUI\\src\\Recourse\\courseManagementSystemIcon.png"));
        imlabel.setBounds(380,25,400,500);

        addp.add(titleLabel);
      //  addp.add(imlabel);
        addp.add(savebtn);
        addp.add(Fnamel);
        addp.add(Lnamel);
        addp.add(Dobl);
        addp.add(gender);
        addp.add(Addressl);
        addp.add(Emaill);
        addp.add(Nationalityl);
        addp.add(Qualification);



        addp.add(fname);
        addp.add(lname);
        addp.add(dob);
        addp.add(female);
        addp.add(male);
        addp.add(address);
        addp.add(email);
        addp.add(nationality);
        addp.add(qulif);
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
        titelabel2 = new JLabel("Update, Show & Delete Instructor Information:");
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
        Updatebtn = new JButton("Update Instructor");
        Updatebtn.setBounds(15,100,130,30);
        Updatebtn.setBackground(Color.DARK_GRAY);
        Updatebtn.setForeground(Color.white);
        Updatebtn.setFont(new Font("",Font.ITALIC,13));
        Updatebtn.addActionListener(this);

        Deletebtn = new JButton("Delete Instructor");
        Deletebtn.setBounds(15,160,130,30);
        Deletebtn.setBackground(Color.DARK_GRAY);
        Deletebtn.setForeground(Color.white);
        Deletebtn.setFont(new Font("",Font.ITALIC,13));
        Deletebtn.addActionListener(this);

        showinst = new JButton("Show Instructor");
        showinst.setBounds(15,220,130,30);
        showinst.setBackground(Color.DARK_GRAY);
        showinst.setForeground(Color.white);
        showinst.setFont(new Font("",Font.ITALIC,13));
        showinst.addActionListener(this);

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
        continerpanal.add(showinst);
        continerpanal.addMouseListener(this);




        ui.hide();
        di.hide();
        si.hide();
        updatep.add(iml);
        updatep.add(si);
        updatep.add(ui);
        updatep.add(di);
        updatep.add(titelabel2);
        updatep.add(backbtn);
        updatep.add(lgoutbtn);
        updatep.add(continerpanal);
        updatep.addMouseListener(this);
        updatep.setLayout(null);




///////////////////////////////////////////////////////////////////////
        //adding the panel and titleborder to the main tappendpane.

        TitledBorder titledBorder = new TitledBorder("Welcome To Manage Instructor Page!");
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.PINK);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));
        this.setBorder(titledBorder);
        this.add("Add Instructor",addp);
        this.add("Update/Delete/Show Instructor",updatep);



    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == Updatebtn){
            si.hide();
            di.hide();
            iml.hide();
            ui.show(true);
        }
        if (ae.getSource() == Deletebtn){
            si.hide();
            ui.hide();
            iml.hide();
            di.show(true);
        }
        if (ae.getSource() == showinst){
            ui.hide();
            di.hide();
            iml.hide();
            si.show(true);
        }
        if(ae.getSource() == savebtn){
            int insid =0;
            try {
                insid = in.generateID(2);
            }catch (RemoteException rs){rs.printStackTrace();}
            String insfname = fname.getText();
            String inslname = lname.getText();
            UsernameAndPasswordGenerator up = new UsernameAndPasswordGenerator();
            String uname = up.createUsername(insfname,inslname);
            String pass = up.generatePassword(insfname);
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
            if (isValidEmail(insemail)  && !insnatio.isEmpty()
                    && !insaddress.isEmpty() && !insfname.isEmpty() && !inslname.isEmpty()){
                try {
                    String message = in.registerInstructor(insid, insfname, inslname, formattedDate, insgender, insaddress, insemail, insnatio
                            , insqulif, uname, pass);
                    JOptionPane.showMessageDialog(null,message);
                }catch (RemoteException re){re.printStackTrace();}

            }else {
                JOptionPane.showMessageDialog(null,"Invalid Input!");
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

    }
    private boolean isValidEmail(String email) {
        // Use a regular expression for basic email validation
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!ui.getBounds().contains(e.getPoint())
                || !di.getBounds().contains(e.getPoint())
                || !si.getBounds().contains(e.getPoint())
        )
        {
            ui.hide();
            di.hide();
            si.hide();
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

