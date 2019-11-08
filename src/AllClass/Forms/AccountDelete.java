package AllClass.Forms;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Basnet
 */
public class AccountDelete extends JFrame implements ActionListener {

    private JPanel main;
    private JTextField txtSurplus;
    private JButton btnDeleteOnly;
    private JButton btnDeleteWithSurplus;
    private JComboBox cmbusernames;
    private JLabel lblSelectUser, lblSurplusAmount;
    private String username;
    public TableTransaction tblTransaction;
    private LinkedList<AccountAndUserCreation> allUsers;
    private ArrayList<String> listNames;
    private Double net;
    private String surUsername;
    private String date;

    public AccountDelete(String username, Double net) {
        this.username = username;
        this.net = net;
        listNames = new ArrayList<String>();
        tblTransaction = new TableTransaction(username);
        allUsers = new LinkedList<>();
        setBounds(100, 100, 413, 356);
        setLocationRelativeTo(null);
        main = new JPanel();
        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(main);
        main.setLayout(null);
       
        main.setBackground(new Color(79, 167, 160));
        cmbusernames = new JComboBox();
        cmbusernames.setBounds(162, 96, 136, 31);
        main.add(cmbusernames);

        btnDeleteOnly = new JButton("Delete Account Only ");
        btnDeleteOnly.setBounds(63, 156, 250, 54);
        main.add(btnDeleteOnly);
        btnDeleteOnly.addActionListener(this);

        lblSelectUser = new JLabel("Transfer Surplus To: ");
        lblSelectUser.setBounds(15, 104, 125, 14);
        main.add(lblSelectUser);

        btnDeleteWithSurplus = new JButton("Delete Account Transferring Surplus");
        btnDeleteWithSurplus.setBounds(63, 225, 250, 54);
        main.add(btnDeleteWithSurplus);
        btnDeleteWithSurplus.addActionListener(this);

        lblSurplusAmount = new JLabel("Surplus Amount");
        lblSurplusAmount.setBounds(26, 52, 100, 14);
        main.add(lblSurplusAmount);

        txtSurplus = new JTextField();
        txtSurplus.setBounds(147, 49, 86, 20);
        main.add(txtSurplus);
        txtSurplus.setColumns(10);
        txtSurplus.setEditable(false);
        txtSurplus.setText(this.net.toString());
        CallUsernames();
        AddAllUsernamesAsList();

        for (String usernames : listNames) {
            cmbusernames.addItem(usernames);
            cmbusernames.removeItem(this.username);
        }
    }

    private void CallUsernames() {
        allUsers.clear();
        String readLine;
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        try {
            File file = new File("src//UsernameAndPasswordDetails//Account.txt");
            fileReader = new FileReader(file);
            bufferReader = new BufferedReader(fileReader);
            while ((readLine = bufferReader.readLine()) != null) {
                String[] splliter = readLine.split("%");
                AccountAndUserCreation accAndUserCreation = new AccountAndUserCreation
                  (splliter[0], splliter[3], splliter[1], splliter[2], false);
                allUsers.add(accAndUserCreation);
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                bufferReader.close();
                fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void AddAllUsernamesAsList() {
        listNames.clear();
        for (AccountAndUserCreation eachusername : allUsers) {
            listNames.add(eachusername.username);
        }

    }
     private void getcurrentdate() {
        LocalDate localDate = LocalDate.now();
        date = (DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate));    
     }



    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnSource = (JButton) ae.getSource();

        if (btnSource == btnDeleteOnly) {
            removeDir(username);
            removeFromUsers();
            JOptionPane.showMessageDialog(null, "Account Deleted");
            System.exit(0);

        } else if (btnSource == btnDeleteWithSurplus) {
            if(net>=1){
            surUsername = (cmbusernames.getSelectedItem().toString());
            getcurrentdate();
            ClassTransaction classTransaction=  new ClassTransaction(username, date, 
                    "Surplus Transfer: " + username, net, "Income");
            writetosurplus(classTransaction);
            btnDeleteOnly.doClick();
            

        }
            else{
            JOptionPane.showMessageDialog(null,"Surplus Amount is in Negative Cannot"
                    + " Transfer Surplus" + "\n"
                    + "So Deleting Account Without Trarnsferring Surplus");
            btnDeleteOnly.doClick();
            }
        }
            
    }

    private void removeDir(String username) {
        File Transaction = new File("src//Users//" + username + "//Transaction.txt");
        File Trigger = new File("src//Users//" + username + "//Trigger.txt");
        Trigger.delete();
        Transaction.delete();
        String path = "src//Users//" + username;
        File dir = new File(path);
        dir.delete();
    }

    private void removeFromUsers () {
        File fileInHold = new File("src/UsernameAndPasswordDetails/Account.tmp");
        File fileInBackUp = new File("src/UsernameAndPasswordDetails/Account.txt");
        FileWriter fileWriter = null;
        BufferedWriter bufferWriter = null;
        PrintWriter printWriter = null;
        BufferedReader bufferReader = null;
        try {
            fileWriter = new FileWriter(fileInHold, true);
            bufferWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferWriter, true);
            bufferReader = new BufferedReader(new FileReader(fileInBackUp));
            String replaceby = "";
            while ((replaceby = bufferReader.readLine()) != null) {
                if (!replaceby.contains(username)) {
                    printWriter.println(replaceby);
                    printWriter.flush(); } }
            printWriter.close();
            bufferReader.close();
            fileInBackUp.delete();
            fileInHold.renameTo(fileInBackUp);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                fileWriter.close();
                bufferReader.close();
            } catch (IOException ioex) {
                ioex.printStackTrace();
            } }
    }
    
    private void writetosurplus(ClassTransaction transaction) {
        FileWriter fileWriter = null;
        BufferedWriter bufferWriter = null;
        PrintWriter printWriter = null;
        File file = null;
        try {
            file = new File("src/Users//" + surUsername + "//Transaction.txt");
            fileWriter = new FileWriter(file, true);
            bufferWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferWriter);
            printWriter.println(transaction.getTransactionID() + "%" + transaction.getDate() +
                    "%" + transaction.getDescription() + "%" + transaction.getAmount() + "%" + 
                    transaction.getTransactionType());
            printWriter.close();
        } catch (IOException iOException) {
            System.out.println(iOException.getMessage());
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}

   
