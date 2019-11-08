package AllClass.Forms;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
/**
 * 
 *
 * @author Basnet
 */

public class LoginForm extends JFrame implements ActionListener {
    private String username,accounttype,fullname;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblEnterAccountName,lblEnterPassword;
    private JButton btnLogin;
    LoginForm lf;
    boolean valid = false;
    LinkedList<AccountAndUserCreation> ListUsernameAndPassword;

    public LoginForm() {
        Container c = getContentPane();
        setTitle("Budgets Incorporated");
        setSize(450,350);
        setLocationRelativeTo(null);
        c.setLayout(null);
        c.setBackground(new Color(79, 167, 160));
        
        lblEnterAccountName = new JLabel("Enter Username:");
        lblEnterAccountName.setBounds(100, 107, 113, 14);
        c.add(lblEnterAccountName);

        txtUsername = new JTextField();
        txtUsername.setBounds(250, 104, 138, 20);
        c.add(txtUsername);
        txtUsername.setColumns(10);

        lblEnterPassword = new JLabel("Enter Password:");
        lblEnterPassword.setBounds(100, 161, 102, 14);
        c.add(lblEnterPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(250, 158, 138, 20);
        c.add(txtPassword);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(175, 213, 113, 42);
        c.add(btnLogin);
        btnLogin.addActionListener(this);

        ListUsernameAndPassword = new LinkedList<AccountAndUserCreation>();
        fillListByEachUserObject();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        JButton btnSource = (JButton) ae.getSource();
        if (btnSource == btnLogin) {
            for (AccountAndUserCreation acc : ListUsernameAndPassword) {
                if (checkUsernameMatches(username, acc.username) == true || checkPasswordMatches(password, acc.password) == true ) {
                        valid = true;
                        this.username = acc.username;
                        this.accounttype = acc.accountType;
                        this.fullname = acc.name;
                    } else {
                        valid = false;
                    }
              
            }
                    if(valid==true){
                      JOptionPane.showMessageDialog(null, "Login Granted as : " + fullname);
                      dispose();
                      HomeMenu hm = new HomeMenu(fullname,username,accounttype);
                      hm.setVisible(true);
                    }
                 else{
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password ");
                }

            }
        }

   private  void fillListByEachUserObject() {
        String readLine;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            File TextFile = new File("src\\UsernameAndPasswordDetails\\Account.txt");
            fr = new FileReader(TextFile);
            br = new BufferedReader(fr);
            ListUsernameAndPassword.clear();
            while ((readLine = br.readLine()) != null) {
                String[] eachLine = readLine.split("%");
                AccountAndUserCreation acc = new AccountAndUserCreation(eachLine[0],
                        eachLine[3], eachLine[1], eachLine[2], true);
                ListUsernameAndPassword.add(acc);
            }
        } catch (FileNotFoundException fileex) {
            System.out.println(fileex.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

   private boolean checkUsernameMatches(String username, String username1) {
        if (username.equals(username1)) {
            return true;
        } else {
            return false;
        }

    }

   private  boolean checkPasswordMatches(String password, String password1) {
        if (password.equals(password1)) {

            return true;
        } else {
            return false;
        }
    }

}
