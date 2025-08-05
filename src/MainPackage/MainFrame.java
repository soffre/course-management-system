package MainPackage;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame implements ActionListener {
    static MainFrame mainFrame;
    private JButton login;
    private JPanel panel;
    private JLabel iml;
    public MainFrame(){


     panel = new JPanel(null);
        login =  new JButton("Login");
        login.setBounds(770,30,75,30);
        login.setFont(new Font("",Font.ITALIC,15));
        login.setBackground(Color.DARK_GRAY);
        login.setForeground(Color.white);
        login.addActionListener(this);

        iml = new JLabel(new ImageIcon("C:\\Users\\admin\\IdeaProjects\\CourseManagementSystemUsing_Java_GUI" +
                "\\src\\Recourse\\coursemanagementsystemcoverpage-resize.png"));
        iml.setBounds(5,10,850,585);


        TitledBorder titledBorder = new TitledBorder("Course Management System");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleColor(Color.DARK_GRAY);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));
        panel.add(login);
        panel.add(iml);
       // panel.setBorder(titledBorder);
        setSize(880,650);
        setVisible(true);
        setResizable(false);
        setTitle("Course Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        //add(es);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
         if (ae.getSource() == login){
             panel.hide();
             login lg=  new login(mainFrame);
             this.add(lg);
         }
    }
    public static void main(String[] args){

         mainFrame = new MainFrame();
    }
}

