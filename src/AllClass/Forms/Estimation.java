package AllClass.Forms;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Estimation extends JFrame {

    private JPanel main;
    private JTextField txtBalance;
    private JTextField txtAverageExp;
    private JTextArea all;
    private JLabel lblBalancetillDate, lblAverageExpencemonthly;
    private Double net;
    private int count;
    private Double permonth, sum, avgSpendingPerMonth, currentBalance;

    public Estimation(Double net, int count, Double sum) {
        this.sum = sum;
        this.net = net;
        this.count = count;
        setBounds(100, 100, 400, 600);
        setLocationRelativeTo(null);
        setTitle("Estimation Monthly");

        main = new JPanel();
        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(main);
        main.setLayout(null);
        main.setBackground(new Color(79, 167, 160));
        lblBalancetillDate = new JLabel("Balance (Till Date)");
        lblBalancetillDate.setBounds(52, 34, 150, 21);
        main.add(lblBalancetillDate);

        txtBalance = new JTextField();
        txtBalance.setBounds(235, 34, 120, 20);
        main.add(txtBalance);
        txtBalance.setColumns(10);
        txtBalance.setEditable(false);
        txtBalance.setText(net.toString());

        lblAverageExpencemonthly = new JLabel("Average Expence (Monthly)");
        lblAverageExpencemonthly.setBounds(52, 81, 175, 20);
        main.add(lblAverageExpencemonthly);

        permonth = this.sum / this.count;
        txtAverageExp = new JTextField();
        txtAverageExp.setBounds(235, 81, 120, 20);
        main.add(txtAverageExp);
        txtAverageExp.setColumns(10);
        txtAverageExp.setEditable(false);
        txtAverageExp.setText(permonth.toString());
        all = new JTextArea();
        all.setBounds(48, 131, 300, 400);
        all.setBackground(new Color(79, 167, 160));
        all.setEditable(false);
        all.setLineWrap(true);
        all.setWrapStyleWord(true);
        main.add(all);
        avgSpendingPerMonth = sum / count;
        currentBalance = net;

        for (int i = 1; i <= 12; i++) {
            String msg = " ";
            msg = ("Estimated balance in Another Month : "
                    + (currentBalance - (avgSpendingPerMonth * (i))));
            all.setText(all.getText() + "\n" + msg + "\n");
        }

    }
}
