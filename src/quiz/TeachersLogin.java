package quiz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TeachersLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserID;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeachersLogin frame = new TeachersLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the frame.
	 */
	public TeachersLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(236, 10, 1, 1);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(207, 23, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(51, 130, 89, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(51, 188, 89, 20);
		contentPane.add(lblNewLabel_1_1);
		
		txtUserID = new JTextField();
		txtUserID.setBounds(207, 127, 170, 23);
		contentPane.add(txtUserID);
		txtUserID.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(207, 188, 170, 23);
		contentPane.add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = txtPassword.getText();
				String userid = txtUserID.getText();
				
				if(password.contains("admin") && userid.contains("admin")) {
					txtPassword.setText(null);
					txtUserID.setText(null);
		
					Teacher tFrame = new Teacher();
					tFrame.setVisible(true);
					//tFrame.show();
					dispose();
					
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Invalid Login Deatails", "Login Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
		btnLogin.setBounds(51, 336, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserID.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setFont(new Font("Arial", Font.BOLD, 14));
		btnReset.setBounds(182, 336, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane = new JPanel();
				if(JOptionPane.showConfirmDialog(contentPane,"Confirm if you want to exit","Login",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					//System.exit(0);
					Home hm = new Home();
					hm.setVisible(true);
					dispose();
				}
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 14));
		btnExit.setBounds(314, 336, 89, 23);
		contentPane.add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(51, 81, 352, 1);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(51, 267, 352, 1);
		contentPane.add(separator_1);
	}
}
