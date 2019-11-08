package AllClass.Forms;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class TableTransaction extends JFrame implements ActionListener {

    private JPanel c;
    private JLabel lblDate, lblAmount, lblDes, lblTransactionType;
    public JTextField txt_date, txt_des, txt_amount;
    public String TransactionID, username;
    private JButton btnEdit, btnDelete;
    private ButtonGroup grp;
    public JRadioButton rdbtmIncome, rdbtmExpence;
    public JTable tblTransaction;
    public TableModel tableModel;
    public JScrollPane scrollPane;
    TransactionUpdater transactionUpdater;
    TransactionHolder tranHolder;

    TableTransaction(String username) {
        tranHolder = new TransactionHolder();
        this.username = username;
        Container c = getContentPane();
        c.setBackground(new Color(79, 167, 160));
        transactionUpdater = new TransactionUpdater(tranHolder, this);
        setBounds(100, 100, 823, 622);
        setTitle("Transactions of user: " + username);
        setLocationRelativeTo(null);
        c.setLayout(null);
        String[] tableHeading = {"Id", "Date", "Description", "Amount",
            "Type[Income/Expence]"};
        tableModel = new DefaultTableModel(tableHeading, 50);

        tblTransaction = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int data, int column) {
                return false;
            }
        };
        tblTransaction.setPreferredScrollableViewportSize(new Dimension(700, 400));
        tblTransaction.setFillsViewportHeight(true);
        tblTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaction(evt);
            }

            private void tblTransaction(MouseEvent evt) {
                int selectedrow = tblTransaction.getSelectedRow();
                txt_date.setText(tblTransaction.getValueAt(selectedrow, 1).toString());
                txt_des.setText(tblTransaction.getValueAt(selectedrow, 2).toString());
                txt_amount.setText(tblTransaction.getValueAt(selectedrow, 3).toString());
                if (tableModel.getValueAt(selectedrow, 4).toString().equals("Income")) {
                    rdbtmIncome.doClick();
                } else {
                    rdbtmExpence.doClick();
                }
            }
        });
        scrollPane = new JScrollPane(tblTransaction);

        scrollPane.setBounds(38, 24, 700, 400);
        c.add(scrollPane);
        c.setVisible(true);
        transactionUpdater.read();

        btnEdit = new JButton("EDIT");
        btnEdit.setBounds(414, 482, 115, 57);
        c.add(btnEdit);
        btnEdit.addActionListener(this);

        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(560, 482, 115, 57);
        c.add(btnDelete);
        btnDelete.addActionListener(this);

        lblDate = new JLabel("Date");
        lblDate.setBounds(60, 446, 80, 18);
        c.add(lblDate);

        lblDes = new JLabel("Description");
        lblDes.setBounds(60, 473, 80, 18);
        c.add(lblDes);

        lblAmount = new JLabel("Amount");
        lblAmount.setBounds(60, 498, 61, 16);
        c.add(lblAmount);

        lblTransactionType = new JLabel("Transaction Type");
        lblTransactionType.setBounds(60, 519, 99, 32);
        c.add(lblTransactionType);

        rdbtmIncome = new JRadioButton("Income");
        rdbtmIncome.setBounds(179, 519, 109, 23);
        c.add(rdbtmIncome);

        rdbtmExpence = new JRadioButton("Expence");
        rdbtmExpence.setBounds(179, 553, 109, 23);
        c.add(rdbtmExpence);

        grp = new ButtonGroup();
        grp.add(rdbtmIncome);
        grp.add(rdbtmExpence);

        txt_date = new JTextField();
        txt_date.setBounds(179, 446, 109, 20);
        c.add(txt_date);
        txt_date.setColumns(10);

        txt_des = new JTextField();
        txt_des.setColumns(10);
        txt_des.setBounds(179, 472, 109, 20);
        c.add(txt_des);

        txt_amount = new JTextField();
        txt_amount.setColumns(10);
        txt_amount.setBounds(179, 496, 109, 20);
        c.add(txt_amount);
    }

    String getUsername() {
        return username;
    }

    public TransactionHolder getTransHolder() {
        return tranHolder;
    }

    public JTable getTableTransaction() {
        return tblTransaction;
    }

    public TableModel getModelTable() {
        return tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnSource = (JButton) ae.getSource();
        int selectedRow;
        selectedRow = tblTransaction.getSelectedRow();
        if (selectedRow > -1) {//valdiate for empty row
            if (btnSource == btnEdit) {
                if (!isThisDateValid(txt_date.getText())) {
                    JOptionPane.showMessageDialog(null, "Invalid Date");
                    txt_date.requestFocus();
                    return;
                }
                if (!isNumeric(txt_amount.getText())) {
                    JOptionPane.showMessageDialog(null, "Enter Only Numeric Value");
                    txt_date.requestFocus();
                    return;
                }

                if (txt_des.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Description Is Empty");
                    txt_des.requestFocus();
                    return;
                }
                String[] invalidChars = new String[]{"%", ",", " ", ":", "@", "-", "/"};
                for (int i = 0; i < invalidChars.length; i++) {
                    if (txt_des.getText().contains(invalidChars[i])) {
                        JOptionPane.showMessageDialog(null, " symbols used are used by system.");
                        txt_des.setText("");
                        return;
                    }

                }

                tranHolder.updateHolder((tblTransaction.getValueAt(selectedRow, 0).toString()), this);
                transactionUpdater.write();
                transactionUpdater.read();
                JOptionPane.showMessageDialog(null, "Selected transaction updated successfully!");
                txt_date.setText(null);
                txt_amount.setText(null);
                txt_des.setText(null);

            } else if (btnSource == btnDelete) {
                tranHolder.deleteFromHolder(tblTransaction.getValueAt(selectedRow, 0).toString());
                transactionUpdater.write();
                ((DefaultTableModel) tblTransaction.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Transaction deleted!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Select a Row To take action");
        }
    }

    String getSelectedTransactionType() {
        if (rdbtmIncome.isSelected()) {
            return "Income";
        } else {
            return "Expence";
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
