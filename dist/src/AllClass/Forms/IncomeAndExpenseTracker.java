package AllClass.Forms;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

/**
 *
 * @author Basnet
 */
public class IncomeAndExpenseTracker extends JFrame {

    private JPanel contentPane;
    private JLabel lblIncome, lblExpense, lblNetincome, 
            txtIncome, txtExpense, txtNet, lblHeader;
    private String username;
    private Double in, out, net;

    public IncomeAndExpenseTracker(Double income, Double outcome, String username) {
        this.in = income;
        this.out = outcome;
        this.net = in - out;
        this.username = username;
        setTitle("IncomeAndExpenseCalculations");
        setBounds(100, 100, 512, 340);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(79, 167, 160));

        lblIncome = new JLabel("Income");
        lblIncome.setBounds(122, 106, 103, 37);
        contentPane.add(lblIncome);

        lblExpense = new JLabel("Expense");
        lblExpense.setBounds(122, 159, 103, 37);
        contentPane.add(lblExpense);

        lblNetincome = new JLabel("NetIncome");
        lblNetincome.setBounds(122, 221, 103, 37);
        contentPane.add(lblNetincome);

        txtIncome = new JLabel(income.toString());
        txtIncome.setBounds(248, 106, 145, 37);
        contentPane.add(txtIncome);

        txtExpense = new JLabel(outcome.toString());
        txtExpense.setBounds(248, 159, 145, 37);
        contentPane.add(txtExpense);

        txtNet = new JLabel(net.toString());
        txtNet.setBounds(248, 221, 145, 37);
        contentPane.add(txtNet);

        lblHeader = new JLabel("    Net Savings of username:    " + this.username);
        lblHeader.setFont(new Font("Tw Cen MT", Font.BOLD, 29));
        lblHeader.setBounds(34, 29, 433, 71);
        contentPane.add(lblHeader);
    }
}
