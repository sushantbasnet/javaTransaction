package AllClass.Forms;
/**
 *
 * @author Basnet
 */
import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

public class newTransaction extends JFrame implements ActionListener {

    private JTextField txt_date, txtdes, txt_amount;
    private ButtonGroup grpTransactionType;
    private JRadioButton rdbtnIncome, rdbtnExpence;
    private JButton btnNew, btnReset;
    private JLabel lblDate, lblDescription, lblAmount, lblTransactionType;
    TableTransaction tblTransaction;
    TransactionHolder tranHolder;
    TransactionUpdater tranUpdater;
    private String username;

    public newTransaction(TransactionHolder tranHolder, TableTransaction tblTransaction, String username) {
        this.tranHolder = new TransactionHolder();
        this.username = username;
        this.tblTransaction = tblTransaction;
        tranUpdater = new TransactionUpdater(this.tranHolder, this.tblTransaction);
        Container c = getContentPane();
        c.setLayout(null);
        setLocationRelativeTo(null);
        setSize(375, 200);
        setTitle("New Transaction Form");
        c.setBackground(new Color(79, 167, 160));
        lblDate = new JLabel("Date");
        lblDate.setBounds(10, 26, 46, 14);
        add(lblDate);

        lblDescription = new JLabel("Description");
        lblDescription.setBounds(10, 49, 80, 14);
        add(lblDescription);

        lblAmount = new JLabel("Amount");
        lblAmount.setBounds(10, 74, 46, 14);
        add(lblAmount);

        lblTransactionType = new JLabel("Transaction Type");
        lblTransactionType.setBounds(10, 101, 99, 32);
        add(lblTransactionType);

        txt_date = new JTextField();
        txt_date.setBounds(137, 26, 118, 20);
        txt_date.setText((getCurrentDate()));
        add(txt_date);
        txt_date.setColumns(10);

        txtdes = new JTextField();
        txtdes.setBounds(137, 49, 118, 20);
        add(txtdes);
        txtdes.setColumns(10);

        txt_amount = new JTextField();
        txt_amount.setBounds(137, 74, 118, 20);
        add(txt_amount);
        txt_amount.setColumns(10);

        rdbtnIncome = new JRadioButton("Income");
        rdbtnIncome.setBounds(141, 100, 109, 23);
        add(rdbtnIncome);

        rdbtnExpence = new JRadioButton("Expence");
        rdbtnExpence.setBounds(141, 129, 109, 23);
        add(rdbtnExpence);

        grpTransactionType = new ButtonGroup();
        grpTransactionType.add(rdbtnIncome);
        grpTransactionType.add(rdbtnExpence);
        rdbtnIncome.doClick();
        btnNew = new JButton("Submit");
        btnNew.setBounds(265, 22, 89, 23);
        add(btnNew);
        btnNew.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setBounds(265, 56, 89, 23);
        add(btnReset);
        btnReset.addActionListener(this);

    }

    private String getSelectedTransactionType() {
        String TransactionType = null;
        if (rdbtnIncome.isSelected()) {
            TransactionType = "Income";
        } else if (rdbtnExpence.isSelected()) {
            TransactionType = "Expence";
        }
        return TransactionType;

    }

    private String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        return (DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate));

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnSource = (JButton) ae.getSource();
        if (btnSource == btnNew) {
            String[] invalidChars = new String[]{"%", ",", " ", ":", "@", "-", "/"};
            if (txt_date.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Date Is Empty");
                txt_date.requestFocus();
                return;
            }
            if (txtdes.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Description Is Empty");
                txtdes.requestFocus();
                return;
            }
            if (txt_amount.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Amount Is Empty");
                txt_amount.requestFocus();
                return;
            }
            for (int i = 0; i < invalidChars.length; i++) {
                if (txtdes.getText().contains(invalidChars[i])) {
                    JOptionPane.showMessageDialog(null, " symbols used are used by system.");
                    txtdes.setText("");
                    return;
                }

            }

            if (!isNumeric(txt_amount.getText())) {
                JOptionPane.showMessageDialog(null, "Amount must be numeric!");
                txt_amount.setText("");
                txt_amount.requestFocus();
                return;
            }

            if (!isThisDateValid(txt_date.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid date! Enter valid date.");
                txt_date.setText("");
                txt_date.requestFocus();
                return;
            }

            Double amount = Double.parseDouble(txt_amount.getText());
            ClassTransaction transactionRecord = new ClassTransaction(username, txt_date.getText(), txtdes.getText(), 
                    amount, getSelectedTransactionType());
            tranUpdater.read();
            tranHolder.add(transactionRecord);
            tranHolder.UpdateTableValues(tblTransaction);
            tranUpdater.write();
            JOptionPane.showMessageDialog(null, "New transaction added successfully!");
            dispose();
        }
    }

    private boolean isNumeric(String text) {
        try {
            double d = Double.parseDouble(text);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isThisDateValid(String input) {

        if (input.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
            return true;
        } else {
            return false;
        }

    }
}
