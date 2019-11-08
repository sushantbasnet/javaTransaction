package AllClass.Forms;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Basnet
 */
public class LoginorRegistration extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JButton btnLogin, btnRegistration;


    public LoginorRegistration() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
        setTitle("Budgets Incorporated");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(79, 167, 160));
        setLocationRelativeTo(null);

        btnLogin = new JButton("Login Here!!!");
        btnLogin.addActionListener(this);
        btnLogin.setBounds(37, 10, 222, 98);
        contentPane.add(btnLogin);

        btnRegistration = new JButton("Register New!!");
        btnRegistration.setBounds(37, 150, 222, 98);
        contentPane.add(btnRegistration);
        btnRegistration.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton btnSource = (JButton) ae.getSource();
        if(btnSource == btnLogin){
            LoginForm login = new LoginForm();
            login.setVisible(true);
        
        }
        else if(btnSource == btnRegistration){
             FormAccountRegistration far = new FormAccountRegistration();
              far.setVisible(true);
        
        }
    }
  }
