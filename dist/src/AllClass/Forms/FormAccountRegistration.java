package AllClass.Forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Basnet
 */
public class FormAccountRegistration extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField txtName, txtUserName, txtLastName;
    private JPasswordField txtPassword, txtConfirmPass;
    private ButtonGroup grp_accountype;
    private JButton btnRegisterClose, btnReset, btnRegisterAndReset;
    private JRadioButton rdbtnHome, rdbtnShared, rdbtnDiscretionary;
    LinkedList<AccountAndUserCreation> userAccountStack;
    private JLabel lblUsername, lblFirstName, lblPassword, lblAccountType,
            lblReenterPassword, lblLastName;

    public FormAccountRegistration() {
        setBounds(100, 100, 467, 396);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setLayout(null);
        contentPane.setBackground(new Color(79, 167, 160));

        lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(107, 11, 71, 14);
        contentPane.add(lblFirstName);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(107, 57, 71, 14);
        contentPane.add(lblUsername);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(107, 82, 71, 14);
        contentPane.add(lblPassword);

        lblAccountType = new JLabel("Account Type");
        lblAccountType.setBounds(107, 136, 80, 14);
        contentPane.add(lblAccountType);

        txtName = new JTextField();
        txtName.setBounds(235, 11, 145, 14);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtUserName = new JTextField();
        txtUserName.setBounds(235, 54, 145, 14);
        txtUserName.setColumns(10);
        contentPane.add(txtUserName);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(235, 79, 145, 14);
        contentPane.add(txtPassword);

        lblReenterPassword = new JLabel("Re-Enter Password");
        lblReenterPassword.setBounds(107, 110, 110, 14);
        contentPane.add(lblReenterPassword);

        txtConfirmPass = new JPasswordField();
        txtConfirmPass.setBounds(235, 107, 145, 14);
        contentPane.add(txtConfirmPass);

        rdbtnHome = new JRadioButton("Home");
        rdbtnHome.setBounds(245, 132, 71, 23);
        rdbtnHome.setBackground(new Color(79, 167, 160));
        contentPane.add(rdbtnHome);
       
        rdbtnShared = new JRadioButton("Shared");
        rdbtnShared.setBounds(245, 158, 71, 23);
        rdbtnShared.setBackground(new Color(79, 167, 160));
        contentPane.add(rdbtnShared);

        rdbtnDiscretionary = new JRadioButton("Discretionary");
        rdbtnDiscretionary.setBounds(245, 181, 109, 23);
        rdbtnDiscretionary.setBackground(new Color(79, 167, 160));
        contentPane.add(rdbtnDiscretionary);

        grp_accountype = new ButtonGroup();
        grp_accountype.add(rdbtnHome);
        grp_accountype.add(rdbtnShared);
        grp_accountype.add(rdbtnDiscretionary);
        rdbtnHome.doClick();

        btnRegisterClose = new JButton("Register And Close");
        btnRegisterClose.setBounds(107, 222, 300, 58);
        contentPane.add(btnRegisterClose);
        btnRegisterClose.addActionListener(this);

        btnRegisterAndReset = new JButton("Register And Reset For Another Registration");
        btnRegisterAndReset.setBounds(107, 281, 300, 65);
        contentPane.add(btnRegisterAndReset);
        btnRegisterAndReset.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setBounds(107, 174, 71, 37);
        contentPane.add(btnReset);
        btnReset.addActionListener(this);

        lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(107, 36, 71, 14);
        contentPane.add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setColumns(10);
        txtLastName.setBounds(235, 33, 145, 14);
        contentPane.add(txtLastName);

        userAccountStack = new LinkedList<AccountAndUserCreation>();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnSource = (JButton) ae.getSource();

        if (btnSource == btnRegisterAndReset) {
            String[] invalidChars = new String[]{ ",", " ", ":", "@", "-", "/"};
            //null validate
            if (txtName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "First Name Is Empty");
                txtName.requestFocus();
                return;
            }
            if (txtLastName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Last Name Is Empty");
                txtLastName.requestFocus();
                return;
            }
            if (txtUserName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Username cannot be left Empty");
                txtUserName.requestFocus();
                return;
            }
            if (new String(txtPassword.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Password Is Empty");
                txtPassword.requestFocus();
                return;
            }

            for (int i = 0; i < invalidChars.length; i++) {
                if (txtName.getText().contains(invalidChars[i])) {
                    JOptionPane.showMessageDialog(null, " '%' ,',' , ' ' are used by system.");
                    txtName.setText("");
                    return;
                }
                if (txtLastName.getText().contains(invalidChars[i])) {
                    JOptionPane.showMessageDialog(null, " '%' ,',' , ' ' are used by system.");
                    txtLastName.setText("");
                    return;
                }
                if (txtUserName.getText().contains(invalidChars[i])) {
                    JOptionPane.showMessageDialog(null, " '%' ,',' , ' ' are used by system. Choose another username");
                    txtUserName.setText("");
                    return;
                }
                if (new String(txtPassword.getPassword()).contains(invalidChars[i])) {
                    JOptionPane.showMessageDialog(null, " '%' ,',' , ' ' are used by system. Choose another Password");
                    txtPassword.setText("");
                    return;
                }
            }
            String FullName = txtName.getText() + " " + txtLastName.getText();
            String password = (new String(txtPassword.getPassword()));
            String repassword = (new String(txtConfirmPass.getPassword()));
            String UserName = txtUserName.getText();
            if (checkPasswordMatch(password, repassword) == true) {
                if (usernameisvalid(UserName)) {
                    AccountAndUserCreation acc = new AccountAndUserCreation(FullName,
                            checkRadioButtonStatus(),
                            UserName, password);
                    userAccountStack.add(acc);
                    createfiles();
                    backup();
                    JOptionPane.showMessageDialog(null, "Registration Complete");
                    btnReset.doClick();
                } else {
                    JOptionPane.showMessageDialog(null, "Username Already Taken!!! Try New");
                    txtUserName.requestFocus();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Password MisMatched!");
            }

        } else if (btnSource == btnRegisterClose) {
            btnRegisterAndReset.doClick();
            dispose();

        } else if (btnSource == btnReset) {
            txtName.setText(null);
            txtLastName.setText(null);
            txtUserName.setText(null);
            txtPassword.setText(null);
            txtConfirmPass.setText(null);
        }
    }

    public LinkedList<String> namesusername() {
        LinkedList<String> ListingUserNames = new LinkedList();
        String readLine;
        FileReader readfile = null;
        BufferedReader bufferreader = null;
        String path = "src\\UsernameAndPasswordDetails\\Account.txt";
        try {
            File textFile = new File(path);
            readfile = new FileReader(textFile);
            bufferreader = new BufferedReader(readfile);
            while ((readLine = bufferreader.readLine()) != null) {
                String[] eachLine = readLine.split("%");
                ListingUserNames.add(eachLine[1]);
            }
        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                readfile.close();
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }
        return ListingUserNames;
    }

    private boolean usernameisvalid(String txtUserName) {
        for (String eachUserName : namesusername()) {
            if ((eachUserName.equals(txtUserName))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPasswordMatch(String password, String repassword) {
        if (password.equals(repassword)) {
            return true;
        } else {
            return false;

        }
    }

    private String checkRadioButtonStatus() {
        if (rdbtnHome.isSelected()) {
            return "Home";
        } else if (rdbtnShared.isSelected()) {
            return "Shared";
        } else {
            return "Discretionary";
        }
    }

    private void createfiles() {
        File folderName = null;
        File Transaction = null;
        File Trigger = null;
        FileWriter fileWriter = null;
        folderName = new File("src//Users//" + txtUserName.getText());
        folderName.mkdir();
        for (AccountAndUserCreation acc : userAccountStack) {
            Transaction = new File("src/Users//" + acc.username + "//" + "Transaction" + ".txt");
            Trigger = new File("src/Users//" + acc.username + "//" + "Trigger" + ".txt");
            try {
                Transaction.createNewFile();
                Trigger.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void backup() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter("src/UsernameAndPassWordDetails/Account.txt", 
                    true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            for (AccountAndUserCreation acc : userAccountStack) {
                printWriter.println(acc.name + "%" + acc.username + "%" + acc.password +
                        "%" + acc.accountType);
            }
            printWriter.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                fileWriter.close();
                printWriter.close();
                printWriter.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
