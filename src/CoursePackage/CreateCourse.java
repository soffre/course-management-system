package CoursePackage;



import InstractorPackage.Interface;


import javax.swing.*;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateCourse extends JPanel implements ActionListener {
    private JLabel CourseName, PrerequisiteCourseId, CourseDescription, CourseStartSchedule, CourseEndSchedule,
             CrediteHR, CourseDepartment, CourseStatus,CourseCapacity;
    private JTextField Cname, PCID, CDept,ccap;
    private JTextArea CDes;
    private JComboBox CSta;
    private JSpinner CHR, CStart, CEnd;
    private JButton save ;
    private SpinnerDateModel endDateModel, startDateModel;
Interface ss;
    public CreateCourse(Interface ss) {

        this.ss = ss;

////////////////////////////////////////////////////
        // call the label class
        CourseName = new JLabel("Course Name: ");
        CrediteHR = new JLabel("Credit Hour:");
        CourseStatus = new JLabel("Course Status:");
        CourseDepartment = new JLabel("Course Department:");
        PrerequisiteCourseId = new JLabel("P_Requisite CourseId:");
        CourseDescription = new JLabel("Course Description: ");
        CourseStartSchedule = new JLabel("Course Start:");
        CourseEndSchedule = new JLabel("Course End:");
        CourseCapacity = new JLabel("Course Capacity:");

        CourseName.setBounds(10, 120, 150, 30);
        CrediteHR.setBounds(10, 160, 150, 30);
        CourseStatus.setBounds(10, 200, 150, 30);
        CourseDepartment.setBounds(10, 240, 150, 30);
        PrerequisiteCourseId.setBounds(10, 280, 155, 30);
        CourseStartSchedule.setBounds(340, 120, 150, 30);
        CourseEndSchedule.setBounds(340, 160, 150, 30);
        CourseCapacity.setBounds(340, 200, 150, 30);
        CourseDescription.setBounds(340, 240, 150, 60);

        CourseName.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CrediteHR.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CourseStatus.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CourseDepartment.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        PrerequisiteCourseId.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CourseStartSchedule.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CourseEndSchedule.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CourseCapacity.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CourseDescription.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));

        Cname = new JTextField();
        PCID = new JTextField();
        CDept = new JTextField();

        String status[] = {"Active", "Canceled", "Hold"};
        CSta = new JComboBox(status);

        Calendar cal = Calendar.getInstance();
        cal.set(2023, 9, 10);
        Date min = cal.getTime();
        cal.set(2050, 10, 10);
        Date max = cal.getTime();
        Date current = new Date();
        int step = Calendar.DAY_OF_WEEK;
        startDateModel = new SpinnerDateModel(current, min, max, step);
        CStart = new JSpinner(startDateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(CStart, "yyyy-MM-dd");
        CStart.setEditor(dateEditor);

        Calendar caln = Calendar.getInstance();
        caln.set(2023, 9, 10);
        Date minv = caln.getTime();
        caln.set(2050, 10, 10);
        Date maxv = caln.getTime();
        Date curr = new Date();
        endDateModel = new SpinnerDateModel(curr, minv, maxv, step);
        CEnd = new JSpinner(endDateModel);
        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(CEnd, "yyyy-MM-dd");
        CEnd.setEditor(dateEditor2);


        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 30, 1);
        CHR = new JSpinner(numberModel);

        CDes = new JTextArea();
        ccap = new JTextField();

        save = new JButton("Save");
        save.addActionListener(this);
        save.setBounds(250, 420, 100, 30);
        save.setBackground(Color.PINK);
        save.setFont(new Font("", Font.BOLD, 15));



        Cname.setBounds(165, 120, 150, 30);
        Cname.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CHR.setBounds(165, 160, 150, 30);
        CHR.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CSta.setBounds(165, 200, 150, 30);
        CSta.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CDept.setBounds(165, 240, 150, 30);
        CDept.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        PCID.setBounds(165, 280, 150, 30);
        PCID.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CStart.setBounds(490, 120, 150, 30);
        CStart.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CEnd.setBounds(490, 160, 150, 30);
        CEnd.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        ccap.setBounds(490, 200, 150, 30);
        ccap.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        CDes.setBounds(490, 240, 150, 60);
        CDes.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));

        TitledBorder titledBorder = new TitledBorder("Create Course");
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.DARK_GRAY);
        titledBorder.setTitleFont(new Font("Comic Sans MS", Font.ITALIC, 25));
        setBorder(titledBorder);
        setLayout(null);
        setBounds(210,70,650,500);
        setBackground(Color.LIGHT_GRAY);
        add(CourseName);
        add(Cname);
        add(CrediteHR);
        add(CHR);
        add(CourseStatus);
        add(CSta);
        add(CourseDepartment);
        add(CDept);
        add(PrerequisiteCourseId);
        add(PCID);
        add(CDes);
        add(CourseStartSchedule);
        add(CStart);
        add(CourseEndSchedule);
        add(CEnd);
        add(CourseCapacity);
        add(ccap);
        add(CourseDescription);
        add(save);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == save) {
            try {
                int cid =0;
                try {
                    cid =ss.generateID(1);
                }catch (RemoteException rs){rs.printStackTrace();}
                String cname = Cname.getText();
                int cCHR = (int) CHR.getValue();
                String Cstat = CSta.getSelectedItem().toString();
                String cDepart = CDept.getText();
                int preCID = Integer.parseInt(PCID.getText());
                Date strt = startDateModel.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate1 = dateFormat.format(strt);
                Date end = endDateModel.getDate();
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate2 = dateFormat2.format(end);
                int coucap = Integer.parseInt(ccap.getText());
                String cdescr = CDes.getText();
                    try {
                        String message = ss.createCourse(cid,cname,cCHR,Cstat,cDepart,preCID,formattedDate1,formattedDate2,cdescr,coucap);
                        JOptionPane.showMessageDialog(null,message);
                    }catch (RemoteException re){re.printStackTrace();}

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
}