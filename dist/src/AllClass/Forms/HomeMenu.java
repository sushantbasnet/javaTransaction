package AllClass.Forms;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Basnet
 */
public class HomeMenu extends JFrame implements ActionListener {

    private JPanel Panel;
    private JButton btnNewTransaction, btnNewTrigger,
            btnViewTransactionHistory, btnDeleteAcc,
            btnViewTriggerHistory, btnEstimatedIncome,
            btnNetIncome;
    public JTextArea txt_UserName, txt_fullName,
            txt_AccountType, txt_Income, txt_Outcome, txt_NetIncome;
    private JLabel lblUsername, lblAccountType, lblFullName;
    public String username;
    TableTrigger tblTrigger;
    TriggerHolder trigHolder;
    TableTransaction tblTransaction;
    TransactionHolder tranHolder;
    public double totalIncome, totalOutcome, netsum,sum;
    public int count;

    public HomeMenu(String fullname, String username, String accounttype) {
        tranHolder = new TransactionHolder();
        this.username = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 703, 358);
        setTitle("Budgets Incorporated");
        Panel = new JPanel();
        Panel.setBackground(new Color(79, 167, 160));
        Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(Panel);
        setLocationRelativeTo(null);
        Panel.setLayout(null);

        btnNewTransaction = new JButton("New Transaction");
        btnNewTransaction.setBounds(10, 97, 196, 46);
        Panel.add(btnNewTransaction);

        btnNewTrigger = new JButton("New Trigger");
        btnNewTrigger.setBounds(243, 97, 196, 46);
        Panel.add(btnNewTrigger);

        btnViewTransactionHistory = new JButton("View Transaction History");
        btnViewTransactionHistory.setBounds(10, 163, 196, 46);
        Panel.add(btnViewTransactionHistory);

        btnViewTriggerHistory = new JButton("View Pending Triggers");
        btnViewTriggerHistory.setBounds(243, 163, 196, 46);
        Panel.add(btnViewTriggerHistory);

        btnDeleteAcc = new JButton("Delete Account");
        btnDeleteAcc.setBounds(486, 11, 186, 46);
        Panel.add(btnDeleteAcc);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(169, 44, 86, 27);
        Panel.add(lblUsername);

        lblFullName = new JLabel("Full Name");
        lblFullName.setBounds(10, 11, 86, 27);
        Panel.add(lblFullName);

        lblAccountType = new JLabel("Account Type");
        lblAccountType.setBounds(255, 11, 86, 27);
        Panel.add(lblAccountType);

        txt_UserName = new JTextArea();
        txt_UserName.setBounds(87, 16, 130, 22);
        Panel.add(txt_UserName);
        txt_UserName.setText(fullname);
        txt_UserName.setEditable(false);
        txt_UserName.setBackground(new Color(79, 167, 160));

        txt_fullName = new JTextArea();
        txt_fullName.setBounds(265, 50, 130, 22);
        Panel.add(txt_fullName);
        txt_fullName.setText(username);
        txt_fullName.setEditable(false);
        txt_fullName.setBackground(new Color(79, 167, 160));

        txt_AccountType = new JTextArea();
        txt_AccountType.setBounds(340, 16, 130, 22);
        Panel.add(txt_AccountType);
        txt_AccountType.setText(accounttype);
        txt_AccountType.setEditable(false);
        txt_AccountType.setBackground(new Color(79, 167, 160));

        btnEstimatedIncome = new JButton("Estimated Mothnly Balances");
        btnEstimatedIncome.setBounds(461, 97, 196, 112);
        Panel.add(btnEstimatedIncome);

        btnNetIncome = new JButton("Net Income");
        btnNetIncome.setBounds(10, 218, 647, 78);
        Panel.add(btnNetIncome);

        btnDeleteAcc.addActionListener(this);
        btnEstimatedIncome.addActionListener(this);
        btnNetIncome.addActionListener(this);
        btnNewTransaction.addActionListener(this);
        btnNewTrigger.addActionListener(this);
        btnViewTransactionHistory.addActionListener(this);
        btnViewTriggerHistory.addActionListener(this);

        tblTrigger = new TableTrigger(username);
        tblTransaction = new TableTransaction(username);
        setTransHolder();
        setTrigHolder();
        setIncomeAndExpence("Income");
        setIncomeAndExpence("Expence");

    }

    private void setTransHolder() {
        tranHolder = tblTransaction.getTransHolder();
    }

    private void setTrigHolder() {
        trigHolder = tblTrigger.getTrigHolder();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnSource = (JButton) ae.getSource();
        setIncomeAndExpence("Income");
        setIncomeAndExpence("Expence");
        if (btnSource == btnViewTransactionHistory) {
            TableTransaction tblTran = new TableTransaction(username);
            tblTran.setVisible(true);

        } else if (btnSource == btnViewTriggerHistory) {
            TableTrigger tblTrig = new TableTrigger(username);
            tblTrig.setVisible(true);

        } else if (btnSource == btnNewTransaction) {
            setTransHolder();
            newTransaction newTran = new newTransaction(tranHolder, 
                    tblTransaction, username);
            newTran.setVisible(true);

        } else if (btnSource == btnNetIncome) {
            setIncomeAndExpence("Income");
            setIncomeAndExpence("Expence");

            IncomeAndExpenseTracker iet = new IncomeAndExpenseTracker(totalIncome, 
                    totalOutcome, username);
            iet.setVisible(true);
        } else if (btnSource == btnNewTrigger) {
            newTrigger ntri = new newTrigger(trigHolder, 
                    tblTrigger, username);
            ntri.setVisible(true);
        } else if (btnSource == btnDeleteAcc) {
            setIncomeAndExpence("Income");
        setIncomeAndExpence("Expence");
            AccountDelete ad = new AccountDelete(username, netsum);
            ad.setVisible(true);
        }
        else if(btnSource == btnEstimatedIncome){
            sum=0;
            count = 0;
            for (int i = 0; i < tblTransaction.getTableTransaction().
                    getRowCount(); i++) {
                 if (tblTransaction.getTableTransaction().getValueAt(i, 4).
                         equals("Expence")) {
                     sum = sum + Double.parseDouble((String) tblTransaction.
                             getTableTransaction().getValueAt(i, 3));
                    count++;
                }
            }
            
          Estimation es = new Estimation(netsum,count,sum);
          es.setVisible(true);
        }

    }

    private void setIncomeAndExpence(String type) {
        double sum = 0;
        for (int i = 0; i < tblTransaction.getTableTransaction().
                getRowCount(); i++) {
            if (tblTransaction.getTableTransaction().getValueAt(i, 4).
                    equals((type))) {
                sum = sum + Double.parseDouble((String) tblTransaction.
                        getTableTransaction().getValueAt(i, 3));
            }
        }
        if (type.equals("Income")) {
            totalIncome = sum;
        } else if (type.equals("Expence")) {
            totalOutcome = sum;

        }
        netsum = totalIncome - totalOutcome;

    }
}


