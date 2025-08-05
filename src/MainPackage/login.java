package MainPackage;

import AdminPackage.*;
import InstractorPackage.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class login extends JPanel implements ActionListener {
    private JLabel username,password;
    private JTextField uname;
    private JPasswordField pass;
    private JPanel loginpanel;
    private JButton save;
    private MainFrame mf;
    Interface ss;

    public login(MainFrame mainFrame){


        try{
            Context ctx = new InitialContext();
            InetAddress ip = InetAddress.getLocalHost();
            String ipaddress = ip.getHostAddress();
            Interface ii = (Interface) ctx.lookup("rmi://"+ipaddress+":5555/InstructorService");
            this.ss = ii;
        }catch ( NamingException e){
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        this.mf = mainFrame;
    loginpanel = new JPanel(null);
        loginpanel.setBounds(250,70,450,500);
        loginpanel.setBackground(Color.LIGHT_GRAY);

        TitledBorder tb = new TitledBorder("Login Here:");
        tb.setTitleJustification(TitledBorder.CENTER);
        tb.setTitlePosition(TitledBorder.TOP);
        tb.setTitleColor(Color.DARK_GRAY);
        tb.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));

        username = new JLabel("User Name:");
        username.setBounds(50,150,150,50);
        username.setFont(new Font("Comic Sans MS",Font.ITALIC,25));
        username.setForeground(Color.DARK_GRAY);

        uname = new JTextField();
        uname.setBounds(205,150,200,50);
        uname.setFont(new Font("Comic Sans MS",Font.PLAIN,20));

        password = new JLabel("Password:");
        password.setBounds(50,250,150,50);
        password.setFont(new Font("Comic Sans MS",Font.ITALIC,25));
        password.setForeground(Color.DARK_GRAY);

        pass = new JPasswordField();
        pass.setBounds(205,250,200,50);
        pass.setEchoChar('*');
        pass.setFont(new Font("Comic Sans MS",Font.PLAIN,20));

        save = new JButton("Login");
        save.setBounds(160,370,100,30);
        save.setBackground(Color.PINK);
        save.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        save.addActionListener(this);



        loginpanel.add(username);
        loginpanel.add(password);
        loginpanel.add(pass);
        loginpanel.add(uname);
        loginpanel.add(save);
        loginpanel.setBorder(tb);

        TitledBorder titledBorder = new TitledBorder("Login Page:");
        titledBorder.setTitleJustification(TitledBorder.LEFT);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleColor(Color.DARK_GRAY);
        titledBorder.setTitleFont(new Font("Comic Sans MS",Font.ITALIC,30));
        this.add(loginpanel);
        this.setLayout(null);
        this.setBorder(titledBorder);

    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == save);
        String un = uname.getText();
        String ps = pass.getText();
          try{
            String role = ss.getRoleOfUser(un,ps);
             if (role.equals("admin")) {
                 AdminPage ap = new AdminPage(mf);
                  mf.remove(this);
                  mf.add(ap);
                  mf.revalidate();
                  mf.repaint();
              }
              else if (role.equals("instructor") ) {
                  InstractorPage ip = new InstractorPage(mf,un,ss);
                  mf.remove(this);
                  mf.add(ip);
                  mf.revalidate();
                  mf.repaint();
              }
              else {
                  JOptionPane.showMessageDialog(null,"Invalid Credential ");
              }
          } catch (Exception e) {
             JOptionPane.showMessageDialog(null,e);
          }

    }
}
