package AllClass.Forms;

import java.awt.Color;
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

/**
 *
 * @author Basnet
 */

public class newTrigger extends JFrame implements ActionListener {
    private JLabel lblDate,lblDescription,lblAmount,lblTriggerType;
    private JTextField txt_date;
    private JTextField txtdes;
    private JTextField txt_amount;
    private ButtonGroup grpTriggerType;
    private JRadioButton rdbtnIncome, rdbtnExpence;
    private JButton btnNew, btnReset;
    TableTrigger tblTrigger;
    TriggerUpdater trigUpdater;
    TriggerHolder trigHolder;
    String username;

    public newTrigger(TriggerHolder trigHolder, TableTrigger tblTrigger,String username) {
        this.username = username;
        this.tblTrigger = tblTrigger;
        this.trigHolder = trigHolder;
        trigUpdater = new TriggerUpdater(trigHolder, tblTrigger);
        setLayout(null);
        setLocationRelativeTo(null);
        setSize(375, 200);
        getContentPane().setBackground(new Color(79, 167, 160));
        setTitle("New Trigger Form");
        setBackground(new Color(79, 167, 160));
        
        lblDate = new JLabel("Date");
        lblDate.setBounds(10, 26, 46, 14);
        add(lblDate);

        lblDescription = new JLabel("Description");
        lblDescription.setBounds(10, 49, 80, 14);
        add(lblDescription);

        lblAmount = new JLabel("Amount");
        lblAmount.setBounds(10, 74, 46, 14);
        add(lblAmount);

        lblTriggerType = new JLabel("Trigger Type");
        lblTriggerType.setBounds(10, 101, 99, 32);
        add(lblTriggerType);

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

        grpTriggerType = new ButtonGroup();
        grpTriggerType.add(rdbtnIncome);
        grpTriggerType.add(rdbtnExpence);
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

    private String getSelectedTriggerType() {
        String TriggerType = null;
        if (rdbtnIncome.isSelected()) {
            TriggerType = "Income";
        } else if (rdbtnExpence.isSelected()) {
            TriggerType = "Expence";
        }
        return TriggerType;

    }

    private String getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        return (DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate));

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnSource = (JButton) ae.getSource();
        if (btnSource == btnNew) {
             String[] invalidChars = new String[]{"%", ",", " " , ":" , "@", "-","/"};
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
            ClassTrigger trigger = new ClassTrigger(username, txt_date.getText(),
                    txtdes.getText(), amount, getSelectedTriggerType());
            trigUpdater.readFromFile();
            trigHolder.add(trigger);
            trigHolder.UpdateTableValues(tblTrigger);
            trigUpdater.writeToFile();
            JOptionPane.showMessageDialog(null, "New Trigger added successfully!");
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
        
       if (input.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")){
    return  true;
       }
else
   return false;
       
    }
}
