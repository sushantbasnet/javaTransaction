package AllClass.Forms;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Basnet
 */
public class Index extends JFrame implements ActionListener {

    private JPanel main;
    private JButton btnContinue;
    private JLabel lblLogo;

    public static void main(String[] args) {
        Index frame = new Index();
        frame.setVisible(true);
    }

    public Index() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        main = new JPanel();
        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(main);
        setTitle("Budgets Incorporated");
        main.setBackground(Color.BLUE);
        main.setLayout(null);
        setLocationRelativeTo(null);

        lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("src\\Images\\welcome.jpg"));
        lblLogo.setBounds(10, 10, 600, 300);
        main.add(lblLogo);
        btnContinue = new JButton("Continue");
        btnContinue.addActionListener(this);
        btnContinue.setBounds(125, 325, 312, 76);
        main.add(btnContinue);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        LoginorRegistration lor = new LoginorRegistration();
        lor.setVisible(true);
        dispose();
    }

}
