package CoursePackage;

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

public class UpdateCourse extends JPanel implements ActionListener {
    private JLabel CourseName, PrerequisiteCourseId, CourseDescription, CourseStartSchedule, CourseEndSchedule, CrediteHR, CourseDepartment, CourseStatus, CourseCapacity, searchid;
    private JTextField Cname, PCID, CDept, ccap, searchtxt;
    private JTextArea CDes;
    private JComboBox CSta;
    private JSpinner CHR, CStart, CEnd;
    private JButton save, searchbtn;
    private SpinnerDateModel endDateModel, startDateModel;
    private Date curr, current;
    private SpinnerNumberModel numberModel;
    Interface ss;

    public UpdateCourse(Interface ss) {
        this.ss= ss;
        searchbtn = new JButton("Search");
        searchbtn.setBounds(400, 10, 100, 30);
        searchbtn.addActionListener(this);

        searchtxt = new JTextField();
        searchtxt.setBounds(250, 10, 150, 30);
        searchtxt.setFont(new Font("", Font.ITALIC, 15));
        searchtxt.requestFocus();

        searchid = new JLabel("ID");
        searchid.setBounds(220, 10, 30, 30);
        searchid.setFont(new Font("Comic Sans MS", Font.ITALIC, 17));


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
        current = new Date();
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
        curr = new Date();
        endDateModel = new SpinnerDateModel(curr, minv, maxv, step);
        CEnd = new JSpinner(endDateModel);
        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(CEnd, "yyyy-MM-dd");
        CEnd.setEditor(dateEditor2);


        numberModel = new SpinnerNumberModel(1, 1, 30, 1);
        CHR = new JSpinner(numberModel);

        CDes = new JTextArea();
        ccap = new JTextField();

        save = new JButton("Update");
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

        setLayout(null);
        setBounds(210, 70, 650, 500);
        setBackground(Color.LIGHT_GRAY);
        add(searchbtn);
        add(searchid);
        add(searchtxt);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchbtn) {
            String inputtext = searchtxt.getText();

            if (!inputtext.isEmpty()) {
                try {
                    int couid = Integer.parseInt(inputtext);
                       coursePojo cp = ss.fetchCourseList(couid);

                        Cname.setText(cp.getName());
                        numberModel.setValue(cp.getcHR());
                        CSta.setSelectedItem(cp.getcStatus());
                        CDept.setText(cp.getcDept());
                        PCID.setText(String.valueOf(cp.getcPre()));
                        current = cp.getcStart();
                        SpinnerDateModel dateModel = (SpinnerDateModel) CStart.getModel();
                        dateModel.setValue(current);
                        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(CStart, "yyyy-MM-dd");
                        CStart.setEditor(dateEditor);

                        curr = cp.getcEnd();
                        SpinnerDateModel dateModel2 = (SpinnerDateModel) CEnd.getModel();
                        dateModel2.setValue(current);
                        JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(CEnd, "yyyy-MM-dd");
                        CEnd.setEditor(dateEditor2);

                        ccap.setText(String.valueOf(cp.getcCap()));
                        CDes.setText(cp.getcDesc());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Search field is null");
            }
        }

        if (e.getSource() == save){
            String inputtext = searchtxt.getText();
            if (!inputtext.isEmpty()) {
                try {
                    int cid = Integer.parseInt(inputtext);
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
                    String message = ss.updateCourse(cid,cname,cCHR,Cstat,cDepart,preCID,formattedDate1,formattedDate2
                                                    ,cdescr,coucap);
                    JOptionPane.showMessageDialog(null,message);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
        }else {
                JOptionPane.showMessageDialog(null,"Search field empty!");
            }
    }
}}
